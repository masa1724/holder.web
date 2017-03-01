package holder.app.controller.holder.movie;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import holder.app.SessionKey;
import holder.app.controller.AbstractController;
import holder.app.controller.holder.movie.form.MovieCategoryForm;
import holder.app.util.DateTime;
import holder.domain.model.User;
import holder.domain.model.movie.MovieCategory;
import holder.domain.repository.movie.MovieCategoryRepository;
import holder.domain.service.holder.movie.MovieCategoryService;

@Controller
public class MovieCategoryAddController extends AbstractController {	
	@Autowired
	private MovieCategoryService service;
	
    @RequestMapping(value = "/movie/category/add", method = RequestMethod.GET)
	public String index() {
    	return "/movie/movie_category";
    }
    
    @RequestMapping(value = "/movie/category/add", method = RequestMethod.POST)
	public String show2(@Validated @ModelAttribute("form") MovieCategoryForm form, BindingResult bindingResult,
			RedirectAttributes attributes, HttpSession session) throws DataAccessException, IOException {
		if (bindingResult.hasErrors()){
			return "/movie/movie_category";
		}
		
		// 動画IDの生成
		String categoryId = service.makeMovieCategoryId();
    	
    	// セッションからシステムユーザID取得
    	User user = (User) session.getAttribute(SessionKey.USER_INFO);
    	String systemUserId = user.getSystemUserId();
    	
		MovieCategory movieCategory = MovieCategory.builder()
				.categoryId(categoryId)
				.systemUserId(systemUserId)
				.name(form.getName())
				.status(MovieCategoryRepository.STATUS_ENABLE)
				.operationNo(getOperationNo())
				.created(DateTime.getDateTime())
				.modified(null)
				.build();
		
		service.registerMovieCategory(movieCategory);
		
		return "/movie/movie_category";
    }
}