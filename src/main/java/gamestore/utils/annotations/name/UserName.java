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

/**
 * The annotation User name.
 *
 * @author Dimitar Ivanov
 * @see UserNameValidator
 */
@Component
@Constraint(validatedBy = UserNameValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserName {

    /**
     * Message string.
     *
     * @return the string
     */
    String message()
            default TextConstants.INVALID_USERNAME_FORMAT;

    /**
     * Min length of user name.
     *
     * @return the min length of the user name
     * @see NumberConstants#MIN_USERNAME_LENGTH
     */
    int minLength()
            default NumberConstants.MIN_USERNAME_LENGTH;

    /**
     * Max length of user name.
     *
     * @return the max length of the user name
     * @see NumberConstants#MAX_USERNAME_LENGTH
     */
    int maxLength()
            default NumberConstants.MAX_USERNAME_LENGTH;

    /**
     * Lower letter regex string.
     *
     * @return the regex pattern finding lower letter
     * @see RegexConstants#LOWER_LETTER_REGEX
     */
    String lowerLetterRegex()
            default RegexConstants.LOWER_LETTER_REGEX;

    /**
     * Upper letter regex string.
     *
     * @return the regex pattern finding upper letter
     * @see RegexConstants#UPPER_LETTER_REGEX
     */
    String upperLetterRegex()
            default RegexConstants.UPPER_LETTER_REGEX;

    /**
     * Regex string for valid user name.
     *
     * @return the regex pattern for valid user name
     * @see RegexConstants#USERNAME_REGEX
     */
    String regex()
            default RegexConstants.USERNAME_REGEX;

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
