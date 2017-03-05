package holder.app.controller.movie;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import holder.app.controller.AbstractController;
import holder.app.controller.movie.form.MovieForm;
import holder.app.session.SessionKey;
import holder.app.session.UserInfo;
import holder.domain.model.movie.MovieCategory;
import holder.domain.model.movie.MovieGroup;
import holder.domain.service.holder.movie.MovieService;

@Controller
public class MovieController extends AbstractController {
	@Autowired
	private MovieService service;

    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public String index(Model model, 
    					@RequestParam(defaultValue = "") String groupId,
    					@ModelAttribute(SessionKey.USER_INFO) UserInfo userInfo) throws DataAccessException, IOException {
    	
    	String systemUserId = userInfo.getSystemUserId();
    	List<MovieGroup> movieGroupList = service.getMovieGroupList(systemUserId);
    	
    	// 初回表示時は最初のグループのカテゴリ情報を表示させる
    	if ("".equals(groupId) &&
    		movieGroupList.size() != 0) {
    		groupId = movieGroupList.get(0).getGroupId();
    	}
    	
    	List<MovieCategory> movieCategoryList = service.getMovieCategoryList(systemUserId, groupId);
    	
    	model.addAttribute(MovieForm.MOVIE_CATEGORY_LIST, movieCategoryList);
    	model.addAttribute(MovieForm.MOVIE_GROUP_LIST, movieGroupList);
    	
        return getFlowURL();
    }
}