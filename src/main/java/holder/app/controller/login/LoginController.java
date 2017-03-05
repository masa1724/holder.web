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

import holder.app.controller.AbstractController;
import holder.app.controller.login.form.LoginForm;
import holder.app.session.SessionKey;
import holder.app.utils.message.ValidatorMessages;
import holder.domain.model.User;
import holder.domain.service.login.LoginService;

@Controller
public class LoginController extends AbstractController {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String index(@ModelAttribute("form") LoginForm form) throws IOException {
		return getFlowURL();
	}
}