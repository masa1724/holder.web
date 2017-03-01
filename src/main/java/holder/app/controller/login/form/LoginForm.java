package holder.app.controller.login.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class LoginForm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String EMAIL = "email";
	
	@NotNull
	@NotEmpty
	//@Size(min = 1, max = 30)
	private String email;

	@NotNull
	@NotEmpty
	//@Size(min = 6, max = 30)
	private String password;
}
