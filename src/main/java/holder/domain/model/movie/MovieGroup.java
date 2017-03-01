package holder.domain.model.movie;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieGroup implements Serializable {
	private static final long serialVersionUID = 8020305771657352426L;
	private String groupId;
	private String categoryId;
	private String systemUserId;
	private String name;
	private String status;
	private String operationNo;
	private LocalDateTime created;
	private LocalDateTime modified;

}
