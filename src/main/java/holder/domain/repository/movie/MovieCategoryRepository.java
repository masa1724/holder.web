package holder.domain.repository.movie;

import java.io.IOException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import holder.domain.model.movie.MovieCategory;

public interface MovieCategoryRepository {
	public static final String CATEGORY_ID = "category_id";
	public static final String GROUP_ID = "group_id";
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
	
	public List<MovieCategory> findList(String systemUserId) throws DataAccessException, IOException;
	public List<MovieCategory> findList(String systemUserId, String groupId) throws DataAccessException, IOException;
	public MovieCategory find(String categoryId) throws DataAccessException, IOException;
	public int register(MovieCategory movieCategory) throws DataAccessException, IOException;
	public int update(MovieCategory movieCategory) throws DataAccessException, IOException;
	public int delete(String categoryId, String systemUserId) throws DataAccessException, IOException;
}
