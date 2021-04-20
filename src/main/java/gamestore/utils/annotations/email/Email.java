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

/**
 * The interface Email.
 *
 * @author Dimitar Ivanov
 * @see EmailValidator
 */
@Component
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {

    /**
     * Message string.
     *
     * @return the invalid email message
     * @see TextConstants#INVALID_EMAIL_FORMAT
     */
    String message()
            default TextConstants.INVALID_EMAIL_FORMAT;

    /**
     * Min user name length int.
     *
     * @return the min length for the username of the email
     * @see NumberConstants#MIN_EMAIl_USERNAME_LENGTH
     */
    int minUserNameLength()
            default NumberConstants.MIN_EMAIl_USERNAME_LENGTH;

    /**
     * Max user name length int.
     *
     * @return the max length for the username of the email
     * @see NumberConstants#MAX_EMAIl_USERNAME_LENGTH
     */
    int maxUserNameLength()
            default NumberConstants.MAX_EMAIl_USERNAME_LENGTH;

    /**
     * Max host name length int.
     *
     * @return the max length for the hostname of the email
     * @see NumberConstants#MAX_EMAIl_HOSTNAME_LENGTH
     */
    int maxHostNameLength()
            default NumberConstants.MAX_EMAIl_HOSTNAME_LENGTH;


    /**
     * Min host name length int.
     *
     * @return the min length for the hostname of the email
     * @see NumberConstants#MIN_EMAIl_HOSTNAME_LENGTH
     */
    int minHostNameLength()
            default NumberConstants.MIN_EMAIl_HOSTNAME_LENGTH;

    /**
     * Regex string.
     *
     * @return the regex pattern for valid email
     * @see RegexConstants#EMAIl_REGEX
     */
    String regex()
            default RegexConstants.EMAIl_REGEX;

    /**
     * Groups class [ ].
     *
     * @return the class [ ]
     */
    Class<?>[] groups() default {};

    /**
     * Payload class [ ].
     *
     * @return the class [ ]
     */
    Class<? extends Payload>[] payload() default {};


}
