package holder.app.controller.holder.movie;

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

import holder.app.SessionKey;
import holder.app.controller.AbstractController;
import holder.app.controller.holder.movie.form.MovieUploadForm;
import holder.app.util.ValidatorMessages;
import holder.domain.model.User;
import holder.domain.model.movie.MovieCategory;
import holder.domain.service.holder.movie.MovieUploadService;

@Controller
@SessionAttributes(value = { "movieUploadForm" })
public class MovieUploadController extends AbstractController {
	@Autowired
	private MovieUploadService service;
		
    @RequestMapping(value = "/movie/upload", method = RequestMethod.GET)
    public String index(@ModelAttribute("form") MovieUploadForm form,
    					Model model, HttpSession session) throws DataAccessException, IOException {
    	
    	User user = (User) session.getAttribute(SessionKey.USER_INFO);
    	String systemUserId = user.getSystemUserId();
    	
    	List<MovieCategory> movieCategoryList = service.getMovieCategoryList(systemUserId);
    	model.addAttribute(MovieUploadForm.MOVIE_CATEGORY_LIST, movieCategoryList);
    	
        return "movie/movie_upload";
    }
    
    @RequestMapping(value = "/movie/upload/add", method = RequestMethod.POST, headers=("content-type=multipart/*"))
    public String index(@Validated @ModelAttribute("form") MovieUploadForm form, BindingResult result,
			RedirectAttributes attributes, HttpSession session) throws DataAccessException, IOException {
    	
        if (result.hasErrors()) {
        	return "movie/movie_upload";
        }

    	// セッションからシステムユーザID取得
    	User user = (User) session.getAttribute(SessionKey.USER_INFO);
    	String systemUserId = user.getSystemUserId();
    	
        List<MovieCategory> movieCategoryList = service.getMovieCategoryList(systemUserId);
      
        String categoryId = form.getCategoryId();
        
        if(!service.chkIllegalValue(movieCategoryList, categoryId)) {
        	result.rejectValue(MovieUploadForm.CATEGORY_ID, ValidatorMessages.ILLEGALVALUEINPUT);
        	return "redirect:/movie/movie_upload";
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
    		return "movie/movie_upload";
    	}
    	
    	attributes.addFlashAttribute(MovieUploadForm.MULTI_PART_FILES, ValidatorMessages.FILE_UPLOADSUCCESS);
    	return "movie/movie_upload";
    }
    
    /**
    @ModelAttribute(value = "movieUploadForm")
    public MovieUploadForm setUpWizardForm() {
        return new MovieUploadForm();
    }*/
}