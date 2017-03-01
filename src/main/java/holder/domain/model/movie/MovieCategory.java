package holder.domain.model.movie;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieCategory implements Serializable {
	private static final long serialVersionUID = 325026172589949799L;
	private String categoryId;
	private String groupId;
	private String systemUserId;
	private String name;
	private String status;
	private String operationNo;
	private LocalDateTime created;
	private LocalDateTime modified;
}
