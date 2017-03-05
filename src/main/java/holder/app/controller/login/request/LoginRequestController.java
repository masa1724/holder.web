package holder.app.controller.login.request;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import holder.app.controller.AbstractController;
import holder.app.controller.login.LoginController;
import holder.app.controller.login.form.LoginForm;
import holder.app.session.SessionKey;
import holder.app.session.UserInfo;
import holder.app.utils.message.ValidatorMessages;
import holder.domain.model.User;
import holder.domain.service.login.LoginService;

@Controller
@SessionAttributes(value = SessionKey.USER_INFO)
public class LoginRequestController extends AbstractController {
	@Autowired
	private LoginService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String show2(@Validated @ModelAttribute("form") LoginForm form,
						BindingResult bindingResult,
						RedirectAttributes attributes, Model model) throws DataAccessException, IOException {
		
		if (bindingResult.hasErrors()){
			return getFlowURL(LoginController.class);
		}
		
		String email = form.getEmail();
		String password = form.getPassword();
		
		// ログイン認証
		if(!service.authentication(email, password)) {
			bindingResult.rejectValue(LoginForm.EMAIL, ValidatorMessages.LOGIN_FAILED);
			return getFlowURL(LoginController.class);
		}
		
		User user = service.findUser(email, password);
		attributes.addFlashAttribute("user_name", user.getName());
		
		UserInfo userInfo = new UserInfo(user);
        model.addAttribute(SessionKey.USER_INFO, userInfo);
		        
		return getFlowURL();
	}
}