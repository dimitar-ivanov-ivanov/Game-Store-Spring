package gamestore.utils.annotations.passwordMatcher;

import org.springframework.stereotype.Component;

import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation Password matches.
 *
 * @author Dimitar Ivanov
 * @see
 */
@Component
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatches {

    /**
     * Error string.
     *
     * @return the error message
     */
    String message()
            default "Password don't match.";

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
