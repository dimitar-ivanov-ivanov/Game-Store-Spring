package gamestore.utils.validators;

public class NumberValidator {

    public static boolean numberInRange(int num, int min, int max) {
        return num >= min && num <= max;
    }
}
