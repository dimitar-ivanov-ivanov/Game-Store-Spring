package gamestore.utils.annotations.password;

import gamestore.utils.annotations.AnnotationsUtil;
import gamestore.utils.constants.TextConstants;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;


/**
 * The type Password validator.
 *
 * @author Dimitar Ivanov
 * @see Password
 */
@Component
public class PasswordValidator implements
        ConstraintValidator<Password, CharSequence> {

    private final String keyWord = "Password";
    private int minLength;
    private int maxLength;
    private Pattern lowerLetterPattern;
    private Pattern upperLetterPattern;
    private Pattern digitPattern;

    @Override
    public void initialize(Password password) {
        this.minLength = password.minLength();
        this.maxLength = password.maxLength();
        this.lowerLetterPattern = Pattern.compile(password.lowerLetterRegex());
        this.upperLetterPattern = Pattern.compile(password.upperLetterRegex());
        this.digitPattern = Pattern.compile(password.digitRegex());
    }

    /**
     * Method for validating a password
     *
     * @param value   the password
     * @param context the context for validation
     * @return whether the password is valid
     * @see AnnotationsUtil#setErrorMessage(ConstraintValidatorContext, String)
     * @see TextConstants
     */
    @Override
    public boolean isValid(CharSequence value,
                           ConstraintValidatorContext context) {
        if (value == null) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    String.format(
                            TextConstants.DATA_CANNOT_BE_NULL,
                            keyWord
                    ));

            return false;
        }

        if (value.length() < this.minLength) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    String.format(
                            TextConstants.DATA_TOO_SHORT,
                            keyWord,
                            this.minLength
                    ));

            return false;
        }

        if (value.length() > this.maxLength) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    String.format(
                            TextConstants.DATA_TOO_LONG,
                            keyWord,
                            this.maxLength
                    ));

            return false;
        }

        String password = value.toString();

        if (!lowerLetterPattern.matcher(password).find()) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    String.format(
                            TextConstants.DATA_SHOULD_CONTAIN_LOWERCASE_LETTER,
                            keyWord
                    ));

            return false;
        }

        if (!upperLetterPattern.matcher(password).find()) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    String.format(
                            TextConstants.DATA_SHOULD_CONTAIN_UPPERCASE_LETTER,
                            keyWord
                    ));

            return false;
        }

        if (!digitPattern.matcher(password).find()) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    String.format(
                            TextConstants.DATA_SHOULD_CONTAIN_DIGIT,
                            keyWord
                    ));

            return false;
        }

        return true;
    }
}
