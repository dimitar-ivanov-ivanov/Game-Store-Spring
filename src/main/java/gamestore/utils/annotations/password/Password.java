package gamestore.utils.annotations.password;

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
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    String message()
            default TextConstants.INVALID_PASSWORD_FORMAT;

    int minLength()
            default NumberConstants.MIN_PASSWORD_LENGTH;

    int maxLength()
            default NumberConstants.MAX_PASSWORD_LENGTH;

    String lowerLetterRegex()
            default RegexConstants.LOWER_LETTER_REGEX;

    String upperLetterRegex()
            default RegexConstants.UPPER_LETTER_REGEX;

    String digitRegex()
            default RegexConstants.DIGIT_REGEX;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}