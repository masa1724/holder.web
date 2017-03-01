package holder.app.controller.login;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import holder.app.SessionKey;
import holder.app.controller.AbstractController;
import holder.app.controller.login.form.LoginForm;
import holder.app.util.ValidatorMessages;
import holder.domain.model.User;
import holder.domain.service.login.LoginService;

@Controller
public class LoginController extends AbstractController {
	@Autowired
	private LoginService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String index(@ModelAttribute("form") LoginForm form) {
		return "login/login_form";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String show2(@Validated @ModelAttribute("form") LoginForm form, BindingResult bindingResult,
			RedirectAttributes attributes, HttpServletRequest request) throws DataAccessException, IOException {
		if (bindingResult.hasErrors()){
			return "login/login_form";
		}
		
		String email = form.getEmail();
		String password = form.getPassword();
		
		// ログイン認証
		if(!service.authentication(email, password)) {
			bindingResult.rejectValue(LoginForm.EMAIL, ValidatorMessages.LOGIN_FAILED);
			return "login/login_form";
		}
		
		User user = service.findUser(email, password);	
		attributes.addFlashAttribute("user_name", user.getName());
		
		HttpSession session = request.getSession();
		session.setAttribute(SessionKey.USER_INFO, user);
		
		return "redirect:/home";
	}
}