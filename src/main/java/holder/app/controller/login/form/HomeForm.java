package holder.app.controller.login.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class HomeForm implements Serializable {
	public static final String NAME = "name";
	
	private String name;
}
