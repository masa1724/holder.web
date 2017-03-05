package holder.domain.repository.login;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
import holder.domain.model.User;
import holder.domain.repository.common.SQL;

@Repository
public class UserRepositoryImp implements UserRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public List<User> findList(User user) throws DataAccessException, IOException {
		SqlParameterSource parameter = new BeanPropertySqlParameterSource(user);
		return jdbcTemplate.query(SQL.getSQL("User.findList"), parameter, new UserRowMapper());
	}
	
	public User find(String email, String password) throws DataAccessException, IOException {
		SqlParameterSource parameter = new MapSqlParameterSource()
				 .addValue(EMAIL, email)
		         .addValue(PASSWORD, password);
		
		return jdbcTemplate.query(SQL.getSQL("User.find"), parameter, new UserResultSetExtractor());
	}

	public User find(String email) throws DataAccessException, IOException {
		SqlParameterSource parameter = new MapSqlParameterSource()
		         .addValue(EMAIL, email);
		return jdbcTemplate.query(SQL.getSQL("User.find2"), parameter, new UserResultSetExtractor());
	}
	
	public User findForStatus(String email, String status) throws DataAccessException, IOException {
		SqlParameterSource parameter = new MapSqlParameterSource()
		         .addValue(EMAIL, email)
		         .addValue(STATUS, status);
		return jdbcTemplate.query(SQL.getSQL("User.findForStatus"), parameter, new UserResultSetExtractor());
	}
	
	public int update(User user) throws DataAccessException, IOException {
		SqlParameterSource parameter = new BeanPropertySqlParameterSource(user);
		return jdbcTemplate.update(SQL.getSQL("User.update"), parameter);		
	}
	
	public int register(User user) throws DataAccessException, IOException {
		 SqlParameterSource parameter = parseParameter(user);
		return jdbcTemplate.update(SQL.getSQL("User.register"), parameter);		
	}
		
	public int delete(String systemUserId) throws DataAccessException, IOException {
		SqlParameterSource parameter = new MapSqlParameterSource()
		         .addValue(SYSTEM_USER_ID, systemUserId);
		int count = jdbcTemplate.update(SQL.getSQL("User.delete"), parameter);
		return count;
	}
	
	public int updateLoginAccount(User user) throws DataAccessException, IOException {
		SqlParameterSource parameter = new BeanPropertySqlParameterSource(user);
		return jdbcTemplate.update(SQL.getSQL("User.updateLoginAccount"), parameter);
	}

	public int updateLoginStatusSuccess(String systemUserId, int loginCount, int errorCount, LocalDateTime lastLoginTs)
				throws DataAccessException, IOException {
		 SqlParameterSource parameter = new MapSqlParameterSource()
		            .addValue(SYSTEM_USER_ID, systemUserId)
		            .addValue(LOGIN_COUNT, loginCount)
		            .addValue(ERROR_COUNT, errorCount)
		            .addValue(LAST_LOGIN_TS, DateTimeUtils.toDate(lastLoginTs));
		return jdbcTemplate.update(SQL.getSQL("User.updateLoginStatusSuccess"), parameter);
	}
	
	public int updateLoginStatusFailed(String systemUserId , String status, int errorCount) throws DataAccessException, IOException {
		 SqlParameterSource parameter = new MapSqlParameterSource()
		            .addValue(SYSTEM_USER_ID, systemUserId)
		            .addValue(STATUS, status)
		            .addValue(ERROR_COUNT, errorCount);
		return jdbcTemplate.update(SQL.getSQL("User.updateLoginStatusFailed"), parameter);
	}
	
	public void updatePassword(String systemUserId, String newPassword, LocalDateTime modified) throws DataAccessException, IOException {
		 SqlParameterSource parameter = new MapSqlParameterSource()
		            .addValue(SYSTEM_USER_ID, systemUserId)
		            .addValue(PASSWORD, newPassword)
		            .addValue(MODIFIED, DateTimeUtils.toDate(modified));
		 jdbcTemplate.update(SQL.getSQL("User.updatePassword"), parameter);
	}
	
	class UserResultSetExtractor implements ResultSetExtractor<User> {
		public User extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(!rs.next()) return null;
			return buildUser(rs);
		}
	}
	
	class UserRowMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException{
			return buildUser(rs);
		}
	}
	
	private User buildUser(ResultSet rs) throws SQLException {
		User user = User.builder()
				.systemUserId(rs.getString(SYSTEM_USER_ID))
				.email(rs.getString(EMAIL))
				.password(rs.getString(PASSWORD))
				.name(rs.getString(NAME))
				.status(rs.getString(STATUS))
				.loginCount(rs.getInt(LOGIN_COUNT))
				.errorCount(rs.getInt(ERROR_COUNT))
				.lastLoginTs(DateTimeUtils.toLocalDateTime(rs.getTimestamp(LAST_LOGIN_TS)))
				.created(DateTimeUtils.toLocalDateTime(rs.getTimestamp(CREATED)))
				.modified(DateTimeUtils.toLocalDateTime(rs.getTimestamp(MODIFIED)))
				.build();
		
		return user;
	}
	
	private SqlParameterSource parseParameter(User user) {
		return new MapSqlParameterSource()
		.addValue(SYSTEM_USER_ID, user.getSystemUserId())
		.addValue(EMAIL, user.getEmail())
		.addValue(PASSWORD, user.getPassword())
		.addValue(NAME, user.getName())
		.addValue(STATUS, user.getStatus())
		.addValue(LOGIN_COUNT, user.getLoginCount())
		.addValue(ERROR_COUNT, user.getErrorCount())
		.addValue(LAST_LOGIN_TS, DateTimeUtils.toDate(user.getLastLoginTs()))
		.addValue(OPERATION_NO, user.getOperationNo())
		.addValue(CREATED, DateTimeUtils.toDate(user.getCreated()))
		.addValue(MODIFIED, DateTimeUtils.toDate(user.getModified()));
	}
}
