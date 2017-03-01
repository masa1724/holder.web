package holder.app.controller.holder.settings.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import holder.app.validation.Confirm;
import lombok.Data;

@Data
@Confirm(fields = "newPassword")
public class PassChangeForm implements Serializable {
	private static final long serialVersionUID = -4566341440560415782L;
	
	public static final String PASSWORD = "password";
	public static final String NEW_PASSWORD = "newPassword";
	public static final String CONFIRM_NEW_PASSWORD = "confirmNewPassword";

	@NotNull
	@NotEmpty
	private String password;
	
	@NotNull
	@NotEmpty
	private String newPassword;
	
	@NotNull
	@NotEmpty
	private String confirmNewPassword;
}
