package holder.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User implements Serializable {
	private static final long serialVersionUID = 2878963870538278639L;
	private String systemUserId;
	private String email;
	private String password;
	private String name;
	private String status;
	private int loginCount;
	private int errorCount;
	private String operationNo;
	private LocalDateTime lastLoginTs;
	private LocalDateTime created;
	private LocalDateTime modified;
}
