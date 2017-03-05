package holder.app.controller.profile;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import holder.app.controller.AbstractController;
import holder.domain.service.holder.profile.ProfileService;

@Controller
public class ProfileController extends AbstractController {
	@Autowired
	private ProfileService service;
	
	/**
	 * 
	 * @return
	 * @throws IOException 
	 */
    @RequestMapping(value = "profile", method = RequestMethod.GET)
    public String index() throws IOException {
    	service.run();
        return getFlowURL();
    }
}