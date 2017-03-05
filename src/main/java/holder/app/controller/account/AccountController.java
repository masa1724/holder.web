package holder.app.controller.account;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import holder.app.controller.AbstractController;
import holder.app.controller.account.form.AccountRegisterForm;

@Controller
@RequestMapping(value = "/account")
public class AccountController extends AbstractController {
	@RequestMapping(method = RequestMethod.GET)
	public String index(@ModelAttribute("form") AccountRegisterForm form) throws IOException {
		return getFlowURL();
	}
}