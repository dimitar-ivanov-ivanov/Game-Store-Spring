package gamestore.validators;

import gamestore.constants.Messages;
import gamestore.exceptions.DataOutOfRangeException;

import java.time.LocalDate;

public class DateValidator {

    //validate date -> possible error: day is 31 but month only has 30 days
    public static void validateSeparateDate(int year, int month, int day) {
        boolean yearInRange = NumberValidator.numberInRange(year, 1900, LocalDate.now().getYear());
        boolean monthInRange = NumberValidator.numberInRange(month, 1, 12);
        boolean dayInRange = NumberValidator.numberInRange(day, 1, 31);

        isDataInRange(yearInRange, 1900, LocalDate.now().getYear(), "year");
        isDataInRange(monthInRange, 1, 12, "month");
        isDataInRange(dayInRange, 1, 31, "day");
    }

    private static void isDataInRange(boolean dataValidity, int min, int max, String dataName) {
        if (!dataValidity) {
            throw new DataOutOfRangeException(String.format(
                    Messages.NOT_IN_RANGE, dataName, min, max
            ));
        }
    }

}
