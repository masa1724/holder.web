package holder.domain.service.account;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import holder.app.utils.date.DateTime;
import holder.domain.model.User;
import holder.domain.repository.common.CounterManager;
import holder.domain.repository.login.UserRepository;
import holder.domain.service.AbstractService;

@Service
public class AccounRegistertService extends AbstractService {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CounterManager counterManager;
	
	public boolean alreadyUsedEmail(String email) throws DataAccessException, IOException {		
		User user = userRepo.find(email);
		if(user != null) {
			return true;
		}
		
		return false;
	}
	
	public void registerUser(String email, String password, String name) throws DataAccessException, IOException {
		// ログインIDの生成
		String systemUserId = counterManager.createSystemUserId();
		String oprationNo = counterManager.createOperationNo();
		LocalDateTime dateTime = DateTime.getDateTime();
		
		// ユーザー情報組み立て
		User user = User.builder()
				.systemUserId(systemUserId)
				.email(email)
				.password(password)
				.name(name)
				.status(UserRepository.STATUS_ENABLED)
				.loginCount(0)
				.errorCount(0)
				.lastLoginTs(dateTime)
				.operationNo(oprationNo)
				.created(dateTime)
				.modified(null)
				.build();
		
		userRepo.register(user);
	}
}
