package gamestore.utils.annotations;

import javax.validation.ConstraintValidatorContext;

public class AnnotationsUtil {
    public AnnotationsUtil() {
    }

    public static void setErrorMessage(final ConstraintValidatorContext context,
                                       final String errorMessage) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage)
                .addConstraintViolation();
    }
}
