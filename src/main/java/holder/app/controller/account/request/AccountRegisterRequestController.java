package holder.app.controller.account.request;

import java.io.IOException;

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
import holder.app.controller.account.AccountController;
import holder.app.controller.account.form.AccountRegisterForm;
import holder.app.utils.message.ValidatorMessages;
import holder.domain.service.account.AccounRegistertService;

@Controller
@RequestMapping(value = "/account/register")
public class AccountRegisterRequestController extends AbstractController {
	@Autowired
	private AccounRegistertService service;

	@RequestMapping(method = RequestMethod.POST)
	public String show2(@ModelAttribute("form") @Validated AccountRegisterForm form, BindingResult bindingResult,
			RedirectAttributes attributes, HttpServletRequest request) throws DataAccessException, IOException {
		
		if (bindingResult.hasErrors()){
			return getFlowURL(AccountController.class);
		}
		
		String email = form.getEmail();
		String password = form.getPassword();
		String name = form.getPassword();
		
		// ID登録済チェック
		if(service.alreadyUsedEmail(email)) {
			bindingResult.rejectValue(AccountRegisterForm.EMAIL, ValidatorMessages.ALREADYUSEDEMAIL);
			return getFlowURL(AccountController.class);
		}
		
		// 登録
		service.registerUser(email, password, name);
		
		// ログイン画面へ遷移
		bindingResult.rejectValue(AccountRegisterForm.EMAIL, ValidatorMessages.ALREADYUSEDEMAIL);
		return getFlowURL();
	}
}