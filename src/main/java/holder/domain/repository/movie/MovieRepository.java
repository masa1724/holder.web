package holder.domain.repository.movie;

import java.io.IOException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import holder.domain.model.movie.Movie;
import holder.domain.model.movie.MovieInfo;

public interface MovieRepository {
	public static final String MOVIE_ID = "movie_id";
	public static final String CATEGORY_ID = "category_id";
	public static final String SYSTEM_USER_ID = "system_user_id";
	public static final String TITLE = "title";
	public static final String DESCRIPTION = "description";
	public static final String PATH = "path";
	public static final String STATUS = "status";
	public static final String OPERATION_NO = "operation_no";
	public static final String CREATED = "created";
	public static final String MODIFIED = "modified";
	
	/** join table columns **/
	// movie_category
	public static final String CATEGORY_NAME = "category_name";
	public static final String CATEGORY_STATUS = "category_status";
	// movie_group
	public static final String GROUP_ID = "group_id";
	public static final String GROUP_NAME = "group_name";
	public static final String GROUP_STATUS = "group_status";
	
	/** 表示 **/
	public static final String STATUS_ENABLE = "10";
	/** 非表示 **/
	public static final String STATUS_DISABLE = "90";
	
	public List<Movie> findList(String systemUserId, String categoryId) throws DataAccessException, IOException;
	public Movie find(String movieId) throws DataAccessException, IOException;
	public MovieInfo findMovieInfo(String movieId) throws DataAccessException, IOException;
	public int register(Movie movie) throws DataAccessException, IOException;
	public int update(Movie movie) throws DataAccessException, IOException;
	public int delete(String movieId) throws DataAccessException, IOException;
}



