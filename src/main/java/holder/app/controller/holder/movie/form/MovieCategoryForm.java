package holder.app.controller.holder.movie.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class MovieCategoryForm implements Serializable{
	private static final long serialVersionUID = -339146155840800833L;
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 7)
	private String name;
}
