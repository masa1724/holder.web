package holder.domain.service.holder.movie;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import holder.app.utils.date.DateTime;
import holder.app.utils.message.MessageId;
import holder.domain.model.movie.Movie;
import holder.domain.model.movie.MovieCategory;
import holder.domain.repository.common.CounterRepository;
import holder.domain.repository.movie.MovieCategoryRepository;
import holder.domain.repository.movie.MovieRepository;
import holder.domain.service.AbstractService;

@Service
public class MovieUploadService extends AbstractService {
	
	@Autowired
	private MovieRepository  movieRepo;
	
	@Autowired
	private CounterRepository counterRepo;
	
	@Autowired
	private Properties fileProperties;
	
	@Autowired
	private MovieCategoryRepository movieCategoryRepo;
	
	public List<MovieCategory> getMovieCategoryList(String systemUserId) throws DataAccessException, IOException {
		return movieCategoryRepo.findList(systemUserId);
	}
	
	public boolean upload(List<MultipartFile> multipartFiles, String categoryId,
						  String systemUserId, String title, String description) throws IllegalStateException, IOException {    	
		String saveDir = fileProperties.getProperty("movie.savedir") + "dir1\\";
		
		// 保存先ディレクトリがなければ作成
    	File dir = new File(saveDir); 
    	if(!dir.exists()) {
    		dir.mkdir();
    	}
    	
    	infoLog(MessageId.FILE_SAVE_DIR, saveDir);
    	
		// ファイルを保存
		for(MultipartFile file : multipartFiles) {
			File f = new File(saveDir, file.getOriginalFilename());
			file.transferTo(f);
			infoLog(MessageId.FILE_SAVE_SUCCESS, new String[]{file.getOriginalFilename()});
		}
		
    	String movieId  = "M" + counterRepo.getCounter(CounterRepository.COUNTER_ID_MOVIE);
    	LocalDateTime dateTime = DateTime.getDateTime();
    	
		Movie movie = Movie.builder()
				.movieId(movieId)
				.categoryId(categoryId)
				.systemUserId(systemUserId)
				.title(title)
				.description(description)
				.status(MovieRepository.STATUS_ENABLE)
				.path(saveDir)
				.created(dateTime)
				.modified(dateTime)
				.build();
		
		movieRepo.register(movie);
		return true;
	}
	
	public boolean chkIllegalValue(List<MovieCategory> movieCategoryList, String categoryId) {
        for(MovieCategory category : movieCategoryList) {
        	if (category.getCategoryId().equals(categoryId)) {
        		return true;
        	}
        }
        
		return false;
	}
}
