package holder.app.binder;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

//@ControllerAdvice
//@Component
//@Order(1)
public class GlobalBindingInitializer implements WebBindingInitializer {
	@InitBinder
	 public void initBinder(WebDataBinder binder, WebRequest req) {
		//-------------------------------------------------------------------------------
		// Custome Editor
		//-------------------------------------------------------------------------------
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss.SSS");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

		//-------------------------------------------------------------------------------
		// Custome Formatter 
		//-------------------------------------------------------------------------------
		
		//-------------------------------------------------------------------------------
		// Custome Validator 
		//-------------------------------------------------------------------------------
		//binder.addValidators(validators);
	 }
}
