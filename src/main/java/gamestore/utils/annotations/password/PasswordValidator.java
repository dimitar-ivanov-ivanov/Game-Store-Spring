package gamestore.utils.annotations.password;

import gamestore.utils.annotations.AnnotationsUtil;
import gamestore.utils.constants.NumberConstants;
import gamestore.utils.constants.RegexConstants;
import gamestore.utils.constants.TextConstants;
import org.springframework.stereotype.Component;
import org.w3c.dom.Text;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.AnnotatedArrayType;
import java.util.regex.Pattern;

@Component
public class PasswordValidator implements
        ConstraintValidator<Password, CharSequence> {

    private static final Pattern PATTERN_LOWER = Pattern.compile(RegexConstants.PASSWORD_LOWER_LETTER_REGEX);
    private static final Pattern PATTERN_UPPER = Pattern.compile(RegexConstants.PASSWORD_UPPER_LETTER_REGEX);
    private static final Pattern PATTERN_DIGIT = Pattern.compile(RegexConstants.PASSWORD_DIGIT_REGEX);

    private int minLength;
    private int maxLength;

    @Override
    public void initialize(Password password) {
        this.minLength = password.minLength();
        this.maxLength = password.maxLength();
    }

    @Override
    public boolean isValid(CharSequence value,
                           ConstraintValidatorContext context) {
        if (value == null) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    String.format(
                            TextConstants.DATA_CANNOT_BE_NULL,
                            "Password"
                    ));

            return false;
        }

        if (value.length() < this.minLength) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    String.format(
                            TextConstants.PASSWORD_TOO_SHORT,
                            this.minLength
                    ));

            return false;
        }

        if (value.length() > this.maxLength) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    String.format(
                            TextConstants.PASSWORD_TOO_LONG,
                            this.maxLength
                    ));

            return false;
        }

        String password = value.toString();

        if (!PATTERN_LOWER.matcher(password).find()) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    TextConstants.PASSWORD_SHOULD_CONTAIN_LOWERCASE_LETTER
            );

            return false;
        }

        if (!PATTERN_UPPER.matcher(password).find()) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    TextConstants.PASSWORD_SHOULD_CONTAIN_UPPERCASE_LETTER
            );

            return false;
        }

        if (!PATTERN_DIGIT.matcher(password).find()) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    TextConstants.PASSWORD_SHOULD_CONTAIN_DIGIT
            );

            return false;
        }

        return true;
    }
}
