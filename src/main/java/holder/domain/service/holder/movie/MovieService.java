package holder.domain.service.holder.movie;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import holder.domain.model.movie.MovieCategory;
import holder.domain.model.movie.MovieGroup;
import holder.domain.repository.movie.MovieCategoryRepository;
import holder.domain.repository.movie.MovieGroupRepository;
import holder.domain.service.AbstractService;

@Service
public class MovieService extends AbstractService {
	@Autowired
	private MovieCategoryRepository movieCategoryRepo;

	@Autowired
	private MovieGroupRepository movieGroupRepo;
	
	public List<MovieCategory> getMovieCategoryList(String systemUserId, String groupId) throws DataAccessException, IOException {
		return movieCategoryRepo.findList(systemUserId, groupId);
	}
	
	public List<MovieGroup> getMovieGroupList(String systemUserId) throws DataAccessException, IOException {
		return movieGroupRepo.findList(systemUserId);
	}
}
