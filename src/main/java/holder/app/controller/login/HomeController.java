package holder.app.controller.login;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import holder.app.controller.AbstractController;
import holder.domain.service.holder.HomeService;

@Controller
public class HomeController extends AbstractController {
	@Autowired
	private HomeService service;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
    public String index() throws IOException {
    	service.run();
        return getFlowURL();
    }
}