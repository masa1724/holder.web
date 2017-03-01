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
import holder.domain.model.movie.MovieGroup;
import holder.domain.repository.common.SQL;

@Repository
public class MovieGroupRepositoryImp implements MovieGroupRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public List<MovieGroup> findList(String systemUserId) throws DataAccessException, IOException {
		SqlParameterSource parameter = new MapSqlParameterSource()
		         .addValue(SYSTEM_USER_ID, systemUserId);
		return jdbcTemplate.query(SQL.getSQL("MovieGroup.findList"), parameter, new MovieGroupRowMapper());
	}
	
	public MovieGroup find(String groupId) throws DataAccessException, IOException {
		SqlParameterSource parameter = new MapSqlParameterSource()
		         .addValue(GROUP_ID, groupId);
		return jdbcTemplate.queryForObject(SQL.getSQL("MovieGroup.find"), parameter, new MovieGroupRowMapper());
	}

	public int update(MovieGroup movieGroup) throws DataAccessException, IOException {
		SqlParameterSource parameter = new BeanPropertySqlParameterSource(movieGroup);
		return jdbcTemplate.update(SQL.getSQL("MovieGroup.update"), parameter);		
	}
	
	public int register(MovieGroup movieGroup) throws DataAccessException, IOException {
		SqlParameterSource parameter = new BeanPropertySqlParameterSource(movieGroup);
		return jdbcTemplate.update(SQL.getSQL("MovieGroup.register"), parameter);		
	}
		
	public int delete(String groupId) throws DataAccessException, IOException {
		SqlParameterSource parameter = new MapSqlParameterSource()
		         .addValue(GROUP_ID, groupId);
		int count = jdbcTemplate.update(SQL.getSQL("MovieGroup.delete"), parameter);
		return count;
	}
	
	class MovieGroupResultSetExtractor implements ResultSetExtractor<MovieGroup> {
		public MovieGroup extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(!rs.next()) return null;
			return buildMovieGroup(rs);
		}
	}
	
	class MovieGroupRowMapper implements RowMapper<MovieGroup> {
		public MovieGroup mapRow(ResultSet rs, int rowNum) throws SQLException{
			return buildMovieGroup(rs);
		}
	}
	
	private MovieGroup buildMovieGroup(ResultSet rs) throws SQLException {
		MovieGroup movieGroup = MovieGroup.builder()
					.groupId(rs.getString(GROUP_ID))
					.systemUserId(rs.getString(SYSTEM_USER_ID))
					.name(rs.getString(NAME))
					.status(rs.getString(STATUS))
					.created(DateTimeUtils.toLocalDateTime(rs.getTimestamp(CREATED)))
					.modified(DateTimeUtils.toLocalDateTime(rs.getTimestamp(MODIFIED)))
				.build();
		return movieGroup;
	}
}
