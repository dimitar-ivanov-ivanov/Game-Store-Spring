package gamestore.utils.annotations.email;

import gamestore.utils.annotations.AnnotationsUtil;
import gamestore.utils.constants.NumberConstants;
import gamestore.utils.constants.TextConstants;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements
        ConstraintValidator<Email, CharSequence> {

    private int minUserNameLength;
    private int maxUserNameLength;
    private int maxHostNameLength;
    private Pattern pattern;

    @Override
    public void initialize(Email email) {
        this.minUserNameLength = email.minUserNameLength();
        this.maxUserNameLength = email.maxUserNameLength();
        this.maxHostNameLength = email.maxHostNameLength();
        this.pattern = Pattern.compile(email.regex());
    }

    @Override
    public boolean isValid(CharSequence value,
                           ConstraintValidatorContext context) {

        if (value == null) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    TextConstants.EMAIL_CANNOT_BE_NULL
            );
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
                            NumberConstants.MIN_EMAIl_USERNAME_LENGTH
                    ));

            return false;
        }

        if (userNameLength > this.maxUserNameLength) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    String.format(
                            TextConstants.EMAIL_DETAIL_TOO_LONG,
                            "username",
                            NumberConstants.MAX_EMAIl_USERNAME_LENGTH
                    ));

            return false;
        }

        if (hostNameLength > this.maxHostNameLength) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    String.format(
                            TextConstants.EMAIL_DETAIL_TOO_LONG,
                            "hostname",
                            NumberConstants.MAX_EMAIl_HOSTNAME_LENGTH
                    ));

            return false;
        }

        return this.pattern.matcher(email)
                .matches();
    }
}
