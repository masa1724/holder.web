package holder.domain.service.holder.movie;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import holder.domain.model.movie.MovieInfo;
import holder.domain.repository.movie.MovieRepository;
import holder.domain.service.AbstractService;

@Service
public class MovieViewService extends AbstractService {
	@Autowired
	private MovieRepository movieRepo;
	
	public MovieInfo getMovieInfo(String movieId) throws DataAccessException, IOException {
		return movieRepo.findMovieInfo(movieId);
	}
}
