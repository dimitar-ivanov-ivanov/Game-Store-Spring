package gamestore.utils.annotations.name;

import gamestore.utils.constants.NumberConstants;
import gamestore.utils.constants.RegexConstants;
import gamestore.utils.constants.TextConstants;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Constraint(validatedBy = UserNameValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserName {

    String message()
            default TextConstants.INVALID_USERNAME_FORMAT;

    int minLength()
            default NumberConstants.MIN_USERNAME_LENGTH;

    int maxLength()
            default NumberConstants.MAX_USERNAME_LENGTH;

    String lowerLetterRegex()
            default RegexConstants.LOWER_LETTER_REGEX;

    String upperLetterRegex()
            default RegexConstants.UPPER_LETTER_REGEX;

    String regex()
            default RegexConstants.USERNAME_REGEX;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
