package holder.domain.service.account;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import holder.domain.model.User;
import holder.domain.repository.common.CounterRepository;
import holder.domain.repository.login.UserRepository;
import holder.domain.service.AbstractService;

@Service
public class AccounRegistertService extends AbstractService {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CounterRepository counterRepo;
	
	public boolean alreadyUsedEmail(String email) throws DataAccessException, IOException {		
		User user = userRepo.find(email);
		if(user != null) {
			return true;
		}
		
		return false;
	}
	
	public void registerUser(User user) throws DataAccessException, IOException {
		userRepo.register(user);
	}
	
	public String makeSystemUserId() throws DataAccessException, IOException {
		int loginIDCount = counterRepo.getCounter(CounterRepository.COUNTER_ID_LOGIN);
		return "system" + loginIDCount;
	}
}
