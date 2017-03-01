package holder.domain.repository.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class CounterRepositoryImp implements CounterRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public int getCounter(String counterId) throws DataAccessException, IOException {
		SqlParameterSource parameter = new MapSqlParameterSource().addValue("counter_id", counterId);
		return jdbcTemplate.queryForObject(SQL.getSQL("Counter.GetCounter"), parameter, Integer.class);
	}
	
	public void updateCounter(String counterId, int count) {
		SqlParameterSource parameter = new MapSqlParameterSource()
		         .addValue("counter_id", counterId)
		         .addValue("count", count);
		
		String sql = "";
		jdbcTemplate.update(sql, parameter);
	}
}