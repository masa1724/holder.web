package holder.app.controller.movie;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import holder.app.controller.AbstractController;
import holder.app.controller.movie.form.MoviePlayForm;
import holder.domain.model.movie.MovieInfo;
import holder.domain.service.holder.movie.MovieViewService;

@Controller
public class MoviePlayController extends AbstractController {
	@Autowired
	private MovieViewService service;
	
    @RequestMapping(value = "/movie/view", method = RequestMethod.GET)
    public String index(Model model, 
    					@RequestParam("movie_id") String movieId) throws DataAccessException, IOException {
    	
    	// movieIdから動画情報を取得
    	MovieInfo movieInfo = service.getMovieInfo(movieId);
    	
    	// Modelへ動画情報を設定
    	model.addAttribute(MoviePlayForm.MOVIE_INFO, movieInfo);
    	
        return getFlowURL();
    }
}