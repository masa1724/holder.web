package holder.app.controller.movie.form;

import java.io.Serializable;
import java.util.List;

import holder.domain.model.movie.MovieCategory;
import holder.domain.model.movie.MovieGroup;
import lombok.Data;

@Data
public class MoviePlayForm implements Serializable {
	private static final long serialVersionUID = -339146155840800833L;
	
	public static final String MOVIE_INFO = "movieInfo";
}
