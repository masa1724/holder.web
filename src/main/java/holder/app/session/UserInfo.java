package holder.app.session;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import holder.domain.model.User;
import lombok.Data;

@Data
@Component
@Scope("session")
public class UserInfo implements Serializable {
	private static final long serialVersionUID = -15284026000257877L;

	private String systemUserId;
	private String email;
	private String name;
	
	public UserInfo(User user) {
		this.systemUserId = user.getSystemUserId();
		this.email = user.getEmail();
		this.name = user.getName();
	}
	
	public UserInfo(String s , String e, String n) {
		this.systemUserId = s;
		this.email = e;
		this.name = n;
	}
}
