package holder.domain.service.holder.settings;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import holder.app.util.DateTime;
import holder.domain.repository.login.UserRepository;
import holder.domain.service.AbstractService;

@Service
public class PassChangeService extends AbstractService {
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * パスワードの妥当性チェックを行う
	 * @param password
	 * @param newPassword
	 * @return
	 * @throws DataAccessException
	 * @throws IOException
	 */
	public boolean validPassword(String password, String newPassword) throws DataAccessException, IOException {
		return true;
	}	
	
	public void updatePassword(String systemUserId, String newPassword) throws DataAccessException, IOException {
		System.out.println("run PassChangeService");
		System.out.println("systemUserId:" + systemUserId + ",password:" + newPassword);
		
		LocalDateTime modified = DateTime.getDateTime();
		
		// パスワードを更新
		userRepository.updatePassword(systemUserId, newPassword, modified);
	}
}
