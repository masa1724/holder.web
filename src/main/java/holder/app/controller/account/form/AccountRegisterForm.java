package holder.app.controller.account.form;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import holder.app.validation.Confirm;
import lombok.Data;

@Data
@Confirm(fields = "password")
public class AccountRegisterForm implements Serializable {
	private static final long serialVersionUID = 1847221705445775561L;
	
	public static final String EMAIL = "email";
	public static final String NAME = "name";
	public static final String PASSWORD = "password";
	public static final String CONFIRM_PASSWORD = "confirmPassword";
	
	@NotEmpty
	@Size(min = 1, max = 30)
	private String name;
	
	@NotEmpty
	@Size(min = 1, max = 30)
	private String email;

	@NotEmpty
	@Size(min = 1, max = 30)
	private String password;
	
	@NotEmpty
	@Size(min = 1, max = 30)
	private String confirmPassword;
}
