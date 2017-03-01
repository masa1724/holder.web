package holder.app.controller.holder.movie;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import holder.app.SessionKey;
import holder.app.controller.AbstractController;
import holder.domain.model.User;
import holder.domain.model.movie.Movie;
import holder.domain.service.holder.movie.MovieCategoryService;

@Controller
public class MovieCategoryController extends AbstractController {	
	@Autowired
	private MovieCategoryService service;
	
    @RequestMapping(value = "/movie/category", method = RequestMethod.GET)
    public String index(HttpSession session, Model model, 
    					@RequestParam("category_id") String categoryId) throws DataAccessException, IOException {
    	
    	// セッションからシステムユーザID取得
    	User user = (User) session.getAttribute(SessionKey.USER_INFO);
    	String systemUserId = user.getSystemUserId();
    	
    	List<Movie> movieList = service.getMovieList(systemUserId, categoryId);
    	model.addAttribute("movieList", movieList);
    	
        return "/movie/movie_category";
    }
}