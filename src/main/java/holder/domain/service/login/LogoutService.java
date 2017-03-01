package holder.domain.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import holder.domain.repository.login.UserRepository;
import holder.domain.service.AbstractService;

@Service
public class LogoutService extends AbstractService {
	
	@Autowired
	private UserRepository userInfoRepository;
	
	public void logout(String systemUserId) {
		System.out.println("logout  systemUserId:" + systemUserId);
	}
}
