package holder.domain.repository.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CounterManager {
	@Autowired
	private CounterRepository counterRepo;
	
	public String createSystemUserId() throws DataAccessException, IOException {
		int loginIdCount = counterRepo.getCounter(CounterRepository.COUNTER_ID_LOGIN);
		counterRepo.updateCounter(CounterRepository.COUNTER_ID_LOGIN, loginIdCount + 1);
		return "SYSTEM" + loginIdCount;
	}
	
	public String createOperationNo() throws DataAccessException, IOException{
		int optCount = counterRepo.getCounter(CounterRepository.COUNTER_ID_OPERATION);
		counterRepo.updateCounter(CounterRepository.COUNTER_ID_OPERATION, optCount + 1);
		return "OPERATE" + optCount;
	}
}
