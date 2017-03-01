package holder.app.controller.account;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import holder.app.controller.AbstractController;
import holder.app.util.DateTime;
import holder.domain.model.User;
import holder.domain.repository.login.UserRepository;
import holder.domain.service.account.AccounRegistertService;

@Controller
public class AccountRegisterController extends AbstractController {
	@Autowired
	private AccounRegistertService service;
	
	@RequestMapping(value = "/accountregister", method = RequestMethod.GET)
	public String index(@ModelAttribute("form") AccountRegisterForm form) {
		return "account/account_register";
	}

	@RequestMapping(value = "/accountregister", method = RequestMethod.POST)
	public String show2(@ModelAttribute("form") @Validated AccountRegisterForm form, BindingResult bindingResult,
			RedirectAttributes attributes, HttpServletRequest request) throws DataAccessException, IOException {
		if (bindingResult.hasErrors()){
			return "account/account_register";
		}
		
		String email = form.getEmail();
		
		// ID登録済チェック
		if(service.alreadyUsedEmail(email)) {
			bindingResult.rejectValue("email", "AlreadyUsedEmail");
			return "account/account_register";
		}
		
		// ログインIDの生成
		String systemUserId = service.makeSystemUserId();
		
		String password = form.getPassword();
		String name = form.getPassword();
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
				.operationNo(getOperationNo())
				.created(dateTime)
				.modified(null)
				.build();
		
		// 登録
		service.registerUser(user);
		
		// ログイン画面へ遷移
		return "redirect:/login";
	}
}