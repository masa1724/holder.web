package holder.app.controller.movie;

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
import holder.app.controller.movie.form.MovieUploadForm;
import holder.app.session.SessionKey;
import holder.app.session.UserInfo;
import holder.app.utils.message.ValidatorMessages;
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
    					Model model,
    					@ModelAttribute(SessionKey.USER_INFO) UserInfo userInfo) throws DataAccessException, IOException {
    	
    	String systemUserId = userInfo.getSystemUserId();
    	
    	List<MovieCategory> movieCategoryList = service.getMovieCategoryList(systemUserId);
    	model.addAttribute(MovieUploadForm.MOVIE_CATEGORY_LIST, movieCategoryList);
    	
        return getFlowURL();
    }
}