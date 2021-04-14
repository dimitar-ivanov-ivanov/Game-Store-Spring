package gamestore.utils.annotations.email;

import gamestore.utils.constants.RegexConstants;
import gamestore.utils.constants.TextConstants;
import gamestore.utils.constants.NumberConstants;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {

    String message()
            default TextConstants.INVALID_EMAIL_FORMAT;

    int minUserNameLength()
            default NumberConstants.MIN_EMAIl_USERNAME_LENGTH;

    int maxUserNameLength()
            default NumberConstants.MAX_EMAIl_USERNAME_LENGTH;

    int maxHostNameLength()
            default NumberConstants.MAX_EMAIl_HOSTNAME_LENGTH;

    String regex()
            default RegexConstants.EMAIl_REGEX;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
