package holder.app.controller.profile;

import java.io.IOException;

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
import holder.app.controller.profile.form.PassChangeForm;
import holder.app.controller.profile.form.ProfileForm;
import holder.app.session.SessionKey;
import holder.app.utils.message.MessageId;
import holder.domain.model.User;
import holder.domain.service.holder.profile.PassChangeService;

@Controller
public class PassChangeController extends AbstractController {
	@Autowired
	private PassChangeService service;
	
	/**
	 * パスワード変更画面を表示する。
	 * @param form
	 * @return
	 * @throws IOException 
	 */
    @RequestMapping(value = "/profile/passchange", method = RequestMethod.GET)
    public String index(@ModelAttribute("form") PassChangeForm form) throws IOException {
        return getFlowURL();
    }
}