package holder.app.controller.login.request;

import java.io.IOException;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import holder.app.controller.AbstractController;

@Controller
public class LogoutRequestController extends AbstractController {
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String show2(SessionStatus status) throws DataAccessException, IOException {
		status.setComplete();
		return getFlowURL();
	}
}