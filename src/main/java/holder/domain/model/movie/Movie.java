package holder.domain.model.movie;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Movie implements Serializable {
	private static final long serialVersionUID = 6523645885587172189L;
	private String movieId;
	private String categoryId;
	private String systemUserId;
	private String title;
	private String description;
	private String path;
	private String status;
	private String operationNo;
	private LocalDateTime created;
	private LocalDateTime modified;
}
