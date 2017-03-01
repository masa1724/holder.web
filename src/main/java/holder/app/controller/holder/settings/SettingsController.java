package holder.app.controller.holder.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import holder.app.controller.AbstractController;
import holder.domain.service.holder.settings.SettingsService;

@Controller
public class SettingsController extends AbstractController {
	@Autowired
	private SettingsService service;
	
	/**
	 * 
	 * @return
	 */
    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String index() {
    	service.run();
        return "settings/settings";
    }
}