package holder.app.controller.movie.form;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import holder.app.validation.NotSelectedFile;
import holder.domain.model.movie.MovieCategory;
import lombok.Data;

@Data
public class MovieUploadForm implements Serializable{
	private static final long serialVersionUID = -6565180923454189138L;
	
	public static final String MOVIE_CATEGORY_LIST = "movieCategoryList";
	public static final String CATEGORY_ID = "categoryId";
	public static final String GROUP_ID = "groupId";
	public static final String MULTI_PART_FILES = "multipartFiles";
	
	@NotSelectedFile
	private List<MultipartFile> multipartFiles;
	
	@NotNull
	@NotEmpty
	private String groupId;
	
	@NotNull
	@NotEmpty
	private String categoryId;
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 7)
	private String name;
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 100)
	private String description;
}
