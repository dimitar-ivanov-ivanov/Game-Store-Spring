package gamestore.utils.annotations;

import javax.validation.ConstraintValidatorContext;

/**
 * The type Annotations util.
 * Utility class used to set error message for violations
 *
 * @author Dimitar Ivanov
 */
public class AnnotationsUtil {

    /**
     * Instantiates a new Annotations util.
     */
    public AnnotationsUtil() {
    }

    /**
     * Sets error message.
     *
     * @param context      the context for validation
     * @param errorMessage the error message
     */
    public static void setErrorMessage(final ConstraintValidatorContext context,
                                       final String errorMessage) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage)
                .addConstraintViolation();
    }
}
