package holder.domain.service.login;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import holder.app.util.DateTime;
import holder.domain.model.User;
import holder.domain.repository.login.UserRepository;
import holder.domain.service.AbstractService;

@Service
public class LoginService extends AbstractService {
	@Autowired
	private UserRepository userRepo;
	
	public boolean authentication(String email, String password) throws DataAccessException, IOException {
		// Emailまたはパスワードが未入力
		if(StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
			return false;
		}
		
		// ステータスが有効な会員を検索
		User user = userRepo.findForStatus(email, UserRepository.STATUS_ENABLED);
		
		// 有効な会員が見つからない
		if(user == null) {
			return false;
		}
		
		String systemUserId = user.getSystemUserId();
			
		// パスワードの不一致
		if (!password.equals(user.getPassword())) {
			int errorCount = user.getErrorCount();
			String status = user.getStatus();
				
			// エラーカウントの上限に達した場合、ステータスを失効にする
			if (errorCount >= UserRepository.ERROR_COUNT_MAX) {
				status = UserRepository.STATUS_DISABLED;
			}
				
			// エラーカウントをカウントアップ
			userRepo.updateLoginStatusFailed(systemUserId, status, errorCount + 1);
			return false;
		}
			
		int loginCount = user.getLoginCount();
		LocalDateTime nowTs = DateTime.getDateTime();
			
		// 最終ログイン時間を更新
		// エラーカウントをリセット
		userRepo.updateLoginStatusSuccess(systemUserId, loginCount + 1, 0, nowTs);
		
		return true;
	}
	
	public User findUser(String email, String password) throws DataAccessException, IOException {
		return userRepo.find(email, password);
	}
}

