package gamestore.utils.annotations.passwordMatcher;

import gamestore.models.bindings.UserRegisterBindingModel;
import gamestore.utils.annotations.AnnotationsUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * The Password matcher validator.
 *
 * @author Dimitar Ivanov
 * @see PasswordMatches
 * @see UserRegisterBindingModel
 */
public class PasswordMatcherValidator implements
        ConstraintValidator<PasswordMatches, UserRegisterBindingModel> {

    private String errorMessage;

    @Override
    public void initialize(PasswordMatches passwordMatches) {
        this.errorMessage = passwordMatches.message();
    }

    /**
     * Check if the passwords match
     *
     * @param model   the binding model
     * @param context
     * @return whether the passwords' of the model match
     */
    @Override
    public boolean isValid(UserRegisterBindingModel model,
                           ConstraintValidatorContext context) {

        boolean isValid = model.getPassword().
                equals(model.getMatchingPassword());

        if (!isValid) {
            AnnotationsUtil.setErrorMessage(context, errorMessage);
        }

        return isValid;
    }
}
