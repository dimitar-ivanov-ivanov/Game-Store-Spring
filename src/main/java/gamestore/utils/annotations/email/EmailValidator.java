package gamestore.utils.annotations.email;

import gamestore.utils.annotations.AnnotationsUtil;
import gamestore.utils.constants.TextConstants;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * The type Email validator.
 *
 * @author Dimitar Ivanov
 * @see Email
 */
@Component
public class EmailValidator implements
        ConstraintValidator<Email, CharSequence> {

    private int minUserNameLength;
    private int maxUserNameLength;
    private int maxHostNameLength;
    private int minHostNameLength;
    private Pattern pattern;

    @Override
    public void initialize(Email email) {
        this.minUserNameLength = email.minUserNameLength();
        this.maxUserNameLength = email.maxUserNameLength();
        this.maxHostNameLength = email.maxHostNameLength();
        this.minHostNameLength = email.minHostNameLength();
        this.pattern = Pattern.compile(email.regex());
    }

    /**
     * Validate email
     *
     * @param value   the email
     * @param context the context that throw violations for wrong email
     * @return if the email is valid
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
                            "Email"
                    ));

            return false;
        }

        String email = value.toString();
        int userNameLength = email.indexOf("@");
        int hostNameLength = email.length() - userNameLength - 1;

        if (userNameLength < this.minUserNameLength) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    String.format(
                            TextConstants.EMAIL_USERNAME_TOO_SHORT,
                            this.minUserNameLength
                    ));

            return false;
        }

        if (userNameLength > this.maxUserNameLength) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    String.format(
                            TextConstants.EMAIL_DETAIL_TOO_LONG,
                            "username",
                            this.maxUserNameLength
                    ));

            return false;
        }

        if (hostNameLength > this.maxHostNameLength) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    String.format(
                            TextConstants.DATA_TOO_LONG,
                            "hostname",
                            this.maxHostNameLength
                    ));

            return false;
        }

        if (hostNameLength < this.minHostNameLength) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    String.format(
                            TextConstants.DATA_TOO_SHORT,
                            "hostname",
                            this.minHostNameLength
                    ));

            return false;
        }


        return this.pattern.matcher(email)
                .matches();
    }
}
