package holder.domain.repository.movie;

import java.io.IOException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import holder.domain.model.movie.MovieGroup;

public interface MovieGroupRepository {
	public static final String GROUP_ID = "group_id";
	public static final String CATEGORY_ID = "category_id";
	public static final String SYSTEM_USER_ID = "system_user_id";
	public static final String NAME = "name";
	public static final String STATUS = "status";
	public static final String OPERATION_NO = "operation_no";
	public static final String CREATED = "created";
	public static final String MODIFIED = "modified";

	/** 表示 **/
	public static final String STATUS_ENABLE = "10";
	/** 非表示 **/
	public static final String STATUS_DISABLE = "90";

	public List<MovieGroup> findList(String systemUserId) throws DataAccessException, IOException;
	public MovieGroup find(String groupId) throws DataAccessException, IOException;
	public int register(MovieGroup movieGroup) throws DataAccessException, IOException;
	public int update(MovieGroup movieGroup) throws DataAccessException, IOException;
	public int delete(String groupId) throws DataAccessException, IOException;
}
