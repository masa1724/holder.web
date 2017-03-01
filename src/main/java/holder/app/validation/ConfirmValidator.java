package holder.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public class ConfirmValidator implements ConstraintValidator<Confirm, Object> {
    private String[] fields;
    private String[] confirmFields;
    
    private String defalutMessage;

    public void initialize(Confirm constraintAnnotation) {
    	defalutMessage = constraintAnnotation.message();
    	fields = constraintAnnotation.fields();
    	
    	confirmFields = new String[fields.length];
    			
    	for(int i = 0; i < fields.length; i++) {
    		confirmFields[i] = "confirm" + StringUtils.capitalize(fields[i]);
    	}
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
    	// 入力エラーフラグ
    	boolean isValid = true;
    	
    	for(int i = 0; i < fields.length; i++) {
	        BeanWrapper beanWrapper = new BeanWrapperImpl(value);
	        Object fieldValue = beanWrapper.getPropertyValue(fields[i]); // (2)
	        Object confirmFieldValue = beanWrapper.getPropertyValue(confirmFields[i]);
	        boolean matched = ObjectUtils.nullSafeEquals(fieldValue,
	                confirmFieldValue);
	        
	        // 不一致の場合
	        if (!matched) {
	        	context.disableDefaultConstraintViolation();
	        	
		        // 該当フィールドへエラーメッセージを設定
		        context.buildConstraintViolationWithTemplate(defalutMessage)
			        .addNode(fields[i])
			        .addConstraintViolation();

		        isValid = false;
	        } 
    	}
    	
        return isValid;
    }
}