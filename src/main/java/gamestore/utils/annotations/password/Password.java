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

/**
 * The interface Password.
 *
 * @author Dimitar Ivanov
 * @see PasswordValidator
 */
@Component
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    /**
     * Message for bad password format.
     *
     * @return the error message for bad format
     */
    String message()
            default TextConstants.INVALID_PASSWORD_FORMAT;

    /**
     * Min length for password
     *
     * @return the min length for a password
     * @see NumberConstants#MIN_PASSWORD_LENGTH
     */
    int minLength()
            default NumberConstants.MIN_PASSWORD_LENGTH;

    /**
     * Max length for password
     *
     * @return the max length for a password
     * @see NumberConstants#MAX_PASSWORD_LENGTH
     */
    int maxLength()
            default NumberConstants.MAX_PASSWORD_LENGTH;

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
     * Digit regex string.
     *
     * @return the regex pattern finding a digit
     * @see RegexConstants#DIGIT_REGEX
     */
    String digitRegex()
            default RegexConstants.DIGIT_REGEX;

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
