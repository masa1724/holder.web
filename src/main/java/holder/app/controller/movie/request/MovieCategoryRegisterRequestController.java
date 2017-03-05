package holder.app.controller.movie.request;

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

import holder.app.controller.AbstractController;
import holder.app.controller.movie.MovieCategoryController;
import holder.app.controller.movie.form.MovieCategoryForm;
import holder.app.session.SessionKey;
import holder.app.session.UserInfo;
import holder.app.utils.date.DateTime;
import holder.domain.model.User;
import holder.domain.model.movie.MovieCategory;
import holder.domain.repository.common.CounterManager;
import holder.domain.repository.movie.MovieCategoryRepository;
import holder.domain.service.holder.movie.MovieCategoryService;

@Controller
public class MovieCategoryRegisterRequestController extends AbstractController {	
	@Autowired
	private MovieCategoryService service;
	
	@Autowired
	private CounterManager counterManager;
	
    @RequestMapping(value = "/movie/category/register", method = RequestMethod.POST)
	public String show2(@Validated @ModelAttribute("form") MovieCategoryForm form,
						@ModelAttribute(SessionKey.USER_INFO) UserInfo userInfo,
						BindingResult bindingResult,
						RedirectAttributes attributes, HttpSession session) throws DataAccessException, IOException {
		if (bindingResult.hasErrors()){
			return getFlowURL(MovieCategoryController.class);
		}
		
		// 動画IDの生成
		String categoryId = service.makeMovieCategoryId();
    	
    	// セッションからシステムユーザID取得
    	String systemUserId = userInfo.getSystemUserId();
    	
		MovieCategory movieCategory = MovieCategory.builder()
				.categoryId(categoryId)
				.systemUserId(systemUserId)
				.name(form.getName())
				.status(MovieCategoryRepository.STATUS_ENABLE)
				.operationNo(counterManager.createOperationNo())
				.created(DateTime.getDateTime())
				.modified(null)
				.build();
		
		service.registerMovieCategory(movieCategory);
		
		return getFlowURL();
    }
    
    //@RequestMapping(value = "/movie/category/edit", method = RequestMethod.POST)
    //@RequestMapping(value = "/movie/category/delete", method = RequestMethod.POST)
}