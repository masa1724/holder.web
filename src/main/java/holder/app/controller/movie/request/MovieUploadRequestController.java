package holder.app.controller.movie.request;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import holder.app.controller.AbstractController;
import holder.app.controller.movie.MovieUploadController;
import holder.app.controller.movie.form.MovieUploadForm;
import holder.app.session.SessionKey;
import holder.app.session.UserInfo;
import holder.app.utils.message.ValidatorMessages;
import holder.domain.model.User;
import holder.domain.model.movie.MovieCategory;
import holder.domain.service.holder.movie.MovieUploadService;

@Controller
@SessionAttributes(value = { "movieUploadForm" })
public class MovieUploadRequestController extends AbstractController {
	@Autowired
	private MovieUploadService service;
	
    @RequestMapping(value = "/movie/upload/upload", method = RequestMethod.POST, headers=("content-type=multipart/*"))
    public String index(@Validated @ModelAttribute("form") MovieUploadForm form,
    					@ModelAttribute(SessionKey.USER_INFO) UserInfo userInfo,
    					BindingResult result,
    					RedirectAttributes attributes) throws DataAccessException, IOException {
    	
        if (result.hasErrors()) {
        	return getFlowURL(MovieUploadController.class);
        }

    	// セッションからシステムユーザID取得
    	String systemUserId = userInfo.getSystemUserId();
    	
        List<MovieCategory> movieCategoryList = service.getMovieCategoryList(systemUserId);
      
        String categoryId = form.getCategoryId();
        
        if(!service.chkIllegalValue(movieCategoryList, categoryId)) {
        	result.rejectValue(MovieUploadForm.CATEGORY_ID, ValidatorMessages.ILLEGALVALUEINPUT);
        	return getFlowURL(MovieUploadController.class);
        }
    	
    	List<MultipartFile> multipartFiles = form.getMultipartFiles();
    	
    	// ファイルのアップロード
    	boolean succeededFileUpload = false;
		try {
			succeededFileUpload = 
					service.upload(
						multipartFiles, 
						form.getCategoryId(), 
						form.getName(), 
						form.getDescription(), 
						systemUserId
					);
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
			succeededFileUpload = false;
		} catch (IOException e) {
			e.printStackTrace();
			succeededFileUpload = false;
		}
		
    	if(!succeededFileUpload) {
    		result.rejectValue(MovieUploadForm.MULTI_PART_FILES, ValidatorMessages.FILE_UPLOADERROR);
    		return getFlowURL(MovieUploadController.class);
    	}
    	
    	attributes.addFlashAttribute(MovieUploadForm.MULTI_PART_FILES, ValidatorMessages.FILE_UPLOADSUCCESS);
    	return getFlowURL();
    }
    
    /**
    @ModelAttribute(value = "movieUploadForm")
    public MovieUploadForm setUpWizardForm() {
        return new MovieUploadForm();
    }*/
}