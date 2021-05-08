package gamestore.utils.constants;


import gamestore.utils.annotations.email.Email;
import gamestore.utils.annotations.name.UserName;
import gamestore.utils.annotations.password.Password;

/**
 * The Regex patterns used for validation in annotations.
 *
 * @author Dimitar Ivanov
 * @see Password
 * @see UserName
 * @see Email
 */
public class RegexConstants {

    /**
     * The constant EMAIl_REGEX.
     */
    public static final String EMAIl_REGEX =
            "^[a-zA-Z]+[\\w._-]+[a-zA-Z0-9]+@[a-zA-Z0-9]+[\\w.-_]+[a-zA-Z]+$";

    /**
     * The constant LOWER_LETTER_REGEX.
     */
    public static final String LOWER_LETTER_REGEX =
            "[a-z]";

    /**
     * The constant UPPER_LETTER_REGEX.
     */
    public static final String UPPER_LETTER_REGEX =
            "[A-Z]";

    /**
     * The constant DIGIT_REGEX.
     */
    public static final String DIGIT_REGEX =
            "[0-9]";

    /**
     * The constant USERNAME_REGEX.
     */
    public static final String USERNAME_REGEX =
            "^[a-zA-Z]+[\\w*!#$]+[a-zA-Z0-9]+$";


}
