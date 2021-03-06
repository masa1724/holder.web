package holder.domain.repository.common;

import java.io.IOException;

import org.springframework.dao.DataAccessException;

public interface CounterRepository {
	public static final String COUNTER_ID = "counter_id";
	public static final String COUNT = "count";
	public static final String CREATED = "created";
	public static final String MODIFIED = "modified";
	
	public static final String COUNTER_ID_LOGIN = "I01";
	public static final String COUNTER_ID_BOOK = "B01";
	public static final String COUNTER_ID_MOVIE = "M01";
	public static final String COUNTER_ID_OPERATION = "O01";
	
	public int getCounter(String counterId) throws DataAccessException, IOException;
	public void updateCounter(String counterId, int count) throws DataAccessException, IOException;
}
