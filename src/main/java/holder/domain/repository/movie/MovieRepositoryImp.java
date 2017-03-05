package holder.domain.repository.movie;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import holder.app.utils.date.DateTimeUtils;
import holder.domain.model.movie.Movie;
import holder.domain.model.movie.MovieInfo;
import holder.domain.repository.common.SQL;

@Repository
public class MovieRepositoryImp implements MovieRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<Movie> findList(String systemUserId, String categoryId) throws DataAccessException, IOException {
		SqlParameterSource parameter = new MapSqlParameterSource()
		         .addValue(SYSTEM_USER_ID, systemUserId)
		         .addValue(CATEGORY_ID, categoryId);
		return jdbcTemplate.query(SQL.getSQL("Movie.findList"), parameter, new MovieRowMapper());
	}
	
	public Movie find(String movieId) throws DataAccessException, IOException {
		Movie movie = Movie.builder()
				.movieId(movieId).build();
		SqlParameterSource parameter = new BeanPropertySqlParameterSource(movie);
		return jdbcTemplate.query(SQL.getSQL("Movie.find"), parameter, new MovieResultSetExtractor());
	}
	
	public MovieInfo findMovieInfo(String movieId) throws DataAccessException, IOException {
		SqlParameterSource parameter = new MapSqlParameterSource()
		         .addValue(MOVIE_ID, movieId);
		return jdbcTemplate.query(SQL.getSQL("MovieInfo.find"), parameter, new MovieInfoResultSetExtractor());
	}
	
	public int update(Movie movie) throws DataAccessException, IOException {
		SqlParameterSource parameter = new BeanPropertySqlParameterSource(movie);
		return jdbcTemplate.update(SQL.getSQL("Movie.update"), parameter);		
	}
	
	public int register(Movie movie) throws DataAccessException, IOException {
		SqlParameterSource parameter = new BeanPropertySqlParameterSource(movie);
		return jdbcTemplate.update(SQL.getSQL("Movie.register"), parameter);		
	}
		
	public int delete(String movieId) throws DataAccessException, IOException {
		SqlParameterSource parameter = new MapSqlParameterSource()
		         .addValue(MOVIE_ID, movieId);
		int count = jdbcTemplate.update(SQL.getSQL("Movie.delete"), parameter);
		return count;
	}
	
	class MovieResultSetExtractor implements ResultSetExtractor<Movie> {
		public Movie extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(!rs.next()) return null;
			return buildMovie(rs);
		}
	}
	
	class MovieRowMapper implements RowMapper<Movie> {
		public Movie mapRow(ResultSet rs, int rowNum) throws SQLException{
			return buildMovie(rs);
		}
	}
	
	private Movie buildMovie(ResultSet rs) throws SQLException {
		Movie movie = Movie.builder()
					.movieId(rs.getString(MOVIE_ID))
					.categoryId(rs.getString(CATEGORY_ID))
					.systemUserId(rs.getString(SYSTEM_USER_ID))
					.title(rs.getString(TITLE))
					.description(rs.getString(DESCRIPTION))
					.path(rs.getString(PATH))
					.status(rs.getString(STATUS))
					.created(DateTimeUtils.toLocalDateTime(rs.getTimestamp(CREATED)))
					.modified(DateTimeUtils.toLocalDateTime(rs.getTimestamp(MODIFIED)))
				.build();
		return movie;
	}
	
	
	class MovieInfoResultSetExtractor implements ResultSetExtractor<MovieInfo> {
		public MovieInfo extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(!rs.next()) return null;
			return buildMovieInfo(rs);
		}
	}
	
	class MovieInfoRowMapper implements RowMapper<MovieInfo> {
		public MovieInfo mapRow(ResultSet rs, int rowNum) throws SQLException{
			return buildMovieInfo(rs);
		}
	}
	
	private MovieInfo buildMovieInfo(ResultSet rs) throws SQLException {
		MovieInfo movieInfo = MovieInfo.builder()
					.movieId(rs.getString(MOVIE_ID))
					.categoryId(rs.getString(CATEGORY_ID))
					.systemUserId(rs.getString(SYSTEM_USER_ID))
					.title(rs.getString(TITLE))
					.description(rs.getString(DESCRIPTION))
					.path(rs.getString(PATH))
					.status(rs.getString(STATUS))
					.created(DateTimeUtils.toLocalDateTime(rs.getTimestamp(CREATED)))
					.modified(DateTimeUtils.toLocalDateTime(rs.getTimestamp(MODIFIED)))
					.categoryName(rs.getString(CATEGORY_NAME))
					.categoryStatus(rs.getString(CATEGORY_STATUS))
					.groupId(rs.getString(GROUP_ID))
					.groupName(rs.getString(GROUP_NAME))
					.groupStatus(rs.getString(GROUP_STATUS))
					
				.build();
		return movieInfo;
	}
}
