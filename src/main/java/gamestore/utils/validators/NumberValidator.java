package gamestore.utils.validators;

/**
 * The Number validator used for basic operations with numbers.
 *
 * @author Dimitar Ivanov
 */
public class NumberValidator {

    /**
     * Find number in range.
     *
     * @param num the num
     * @param min the min
     * @param max the max
     * @return whether a number is in the given range
     */
    public static boolean numberInRange(int num, int min, int max) {
        return num >= min && num <= max;
    }
}
