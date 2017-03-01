package holder.domain.service.holder.movie;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import holder.domain.model.movie.Movie;
import holder.domain.model.movie.MovieCategory;
import holder.domain.repository.common.CounterRepository;
import holder.domain.repository.movie.MovieCategoryRepository;
import holder.domain.repository.movie.MovieRepository;
import holder.domain.service.AbstractService;

@Service
public class MovieCategoryService extends AbstractService {
	@Autowired
	private MovieRepository movieRepo;
	
	@Autowired
	private MovieCategoryRepository movieCategoryRepo;
	
	@Autowired
	private CounterRepository counterRepo;
	
	public List<Movie> getMovieList(String systemUserId, String categoryId) throws DataAccessException, IOException {
		return movieRepo.findList(systemUserId, categoryId);
	}
	
	public void registerMovieCategory(MovieCategory movieCategory) throws DataAccessException, IOException {
		movieCategoryRepo.register(movieCategory);
	}
	
	public String makeMovieCategoryId() throws DataAccessException, IOException {
		int movieIDCount = counterRepo.getCounter(CounterRepository.COUNTER_ID_MOVIE);
		return "MOVIE" + movieIDCount;
	}
}
