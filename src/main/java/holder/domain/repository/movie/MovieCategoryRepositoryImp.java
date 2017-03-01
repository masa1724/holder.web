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

import holder.app.util.DateTimeUtils;
import holder.domain.model.movie.MovieCategory;
import holder.domain.repository.common.SQL;

@Repository
public class MovieCategoryRepositoryImp implements MovieCategoryRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public List<MovieCategory> findList(String systemUserId) throws DataAccessException, IOException {
		SqlParameterSource parameter = new MapSqlParameterSource()
		         .addValue(SYSTEM_USER_ID, systemUserId);
		return jdbcTemplate.query(SQL.getSQL("MovieCategory.findList"), parameter, new MovieCategoryRowMapper());
	}
	
	public List<MovieCategory> findList(String systemUserId, String groupId) throws DataAccessException, IOException {
		SqlParameterSource parameter = new MapSqlParameterSource()
		         .addValue(SYSTEM_USER_ID, systemUserId)
		         .addValue(GROUP_ID, groupId);
		return jdbcTemplate.query(SQL.getSQL("MovieCategory.findList2"), parameter, new MovieCategoryRowMapper());
	}
	
	public MovieCategory find(String categoryId) throws DataAccessException, IOException {
		SqlParameterSource parameter = new MapSqlParameterSource()
		         .addValue(CATEGORY_ID, categoryId);
		return jdbcTemplate.queryForObject(SQL.getSQL("MovieCategory.find"), parameter, new MovieCategoryRowMapper());
	}

	public int update(MovieCategory movieCategory) throws DataAccessException, IOException {
		SqlParameterSource parameter = new BeanPropertySqlParameterSource(movieCategory);
		return jdbcTemplate.update(SQL.getSQL("MovieCategory.update"), parameter);		
	}
	
	public int register(MovieCategory movieCategory) throws DataAccessException, IOException {
		SqlParameterSource parameter = new BeanPropertySqlParameterSource(movieCategory);
		return jdbcTemplate.update(SQL.getSQL("MovieCategory.register"), parameter);		
	}
		
	public int delete(String categoryId, String systemUserId) throws DataAccessException, IOException {
		SqlParameterSource parameter = new MapSqlParameterSource()
		         .addValue(CATEGORY_ID, categoryId)
		         .addValue(SYSTEM_USER_ID, systemUserId);
		int count = jdbcTemplate.update(SQL.getSQL("MovieCategory.delete"), parameter);
		return count;
	}
	
	class MovieCategoryResultSetExtractor implements ResultSetExtractor<MovieCategory> {
		public MovieCategory extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(!rs.next()) return null;
			return buildMovieCategory(rs);
		}
	}
	
	class MovieCategoryRowMapper implements RowMapper<MovieCategory> {
		public MovieCategory mapRow(ResultSet rs, int rowNum) throws SQLException{
			return buildMovieCategory(rs);
		}
	}
	
	private MovieCategory buildMovieCategory(ResultSet rs) throws SQLException {
		MovieCategory movieCategory = MovieCategory.builder()
					.categoryId(rs.getString(CATEGORY_ID))
					.groupId(rs.getString(GROUP_ID))
					.systemUserId(rs.getString(SYSTEM_USER_ID))
					.name(rs.getString(NAME))
					.status(rs.getString(STATUS))
					.created(DateTimeUtils.toLocalDateTime(rs.getTimestamp(CREATED)))
					.modified(DateTimeUtils.toLocalDateTime(rs.getTimestamp(MODIFIED)))
				.build();
		return movieCategory;
	}
}
