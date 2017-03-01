package holder.app.controller.account;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import holder.app.validation.Confirm;
import lombok.Data;

@Data
@Confirm(fields = "password")
public class AccountRegisterForm implements Serializable {
	private static final long serialVersionUID = 1847221705445775561L;
	
	
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 30)
	private String name;
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 30)
	private String email;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 30)
	private String password;
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 30)
	private String confirmPassword;
}
