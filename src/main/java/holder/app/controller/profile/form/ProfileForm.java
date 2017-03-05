package holder.app.controller.profile.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import holder.app.validation.Confirm;
import lombok.Data;

//@Data
public class ProfileForm implements Serializable {
	public static final String PASSWORD = "password";
}
