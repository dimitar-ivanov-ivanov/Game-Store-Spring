package gamestore.utils.annotations.name;

import gamestore.utils.annotations.AnnotationsUtil;
import gamestore.utils.constants.TextConstants;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class UserNameValidator implements
        ConstraintValidator<UserName, CharSequence> {

    private final String keyWord = "User name";
    private int minLength;
    private int maxLength;
    private Pattern lowerLetterPattern;
    private Pattern upperLetterPattern;
    private Pattern namePattern;

    @Override
    public void initialize(UserName name) {
        this.minLength = name.minLength();
        this.maxLength = name.maxLength();
        this.lowerLetterPattern = Pattern.compile(name.lowerLetterRegex());
        this.upperLetterPattern = Pattern.compile(name.upperLetterRegex());
        this.namePattern = Pattern.compile(name.regex());
    }

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

        String name = value.toString();

        if (!lowerLetterPattern.matcher(name).find()) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    String.format(
                            TextConstants.DATA_SHOULD_CONTAIN_LOWERCASE_LETTER,
                            keyWord
                    ));

            return false;
        }

        if (!upperLetterPattern.matcher(name).find()) {
            AnnotationsUtil.setErrorMessage(
                    context,
                    String.format(
                            TextConstants.DATA_SHOULD_CONTAIN_UPPERCASE_LETTER,
                            keyWord
                    ));

            return false;
        }

        return this.namePattern.matcher(name)
                .matches();
    }
}
