package holder.app.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class NotSelectedFileValidator implements ConstraintValidator<NotSelectedFile,List<MultipartFile>>{
	public void initialize(NotSelectedFile nsf) {
	}
	
	public boolean isValid(List<MultipartFile> multipartFiles,ConstraintValidatorContext cxt){
		System.out.println("multipartFiles" + multipartFiles);
		if(multipartFiles == null || multipartFiles.size() == 0){
			return false;
		}
		return true;
	}
}