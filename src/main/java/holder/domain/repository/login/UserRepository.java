package holder.domain.repository.login;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.dao.DataAccessException;

import holder.domain.model.User;

public interface UserRepository {
	public static final String SYSTEM_USER_ID = "system_user_id";
	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";
	public static final String NAME = "name";
	public static final String STATUS = "status";
	public static final String LOGIN_COUNT = "login_count";
	public static final String ERROR_COUNT = "error_count";
	public static final String LAST_LOGIN_TS = "last_login_ts";
	public static final String OPERATION_NO = "operation_no";
	public static final String CREATED = "created";
	public static final String MODIFIED = "modified";
	
	public static final String STATUS_ENABLED = "10";
	public static final String STATUS_DISABLED = "20";
	public static final int ERROR_COUNT_MAX = 5;
	
	public List<User> findList(User user) throws DataAccessException, IOException;
	public User find(String email, String password) throws DataAccessException, IOException;
	public User find(String email) throws DataAccessException, IOException;
	public User findForStatus(String email, String status) throws DataAccessException, IOException;
	
	public int update(User user) throws DataAccessException, IOException;
	public int register(User user) throws DataAccessException, IOException;
	public int delete(String systemUserId) throws DataAccessException, IOException;
	public int updateLoginAccount(User user) throws DataAccessException, IOException;
	public int updateLoginStatusSuccess(String systemUserId, int loginCount, int errorCount, LocalDateTime lastLoginTs) throws DataAccessException, IOException;
	public int updateLoginStatusFailed(String systemUserId , String status, int errorCount) throws DataAccessException, IOException;
	
	public void updatePassword(String systemUserId, String newPassword, LocalDateTime modified) throws DataAccessException, IOException;
}
