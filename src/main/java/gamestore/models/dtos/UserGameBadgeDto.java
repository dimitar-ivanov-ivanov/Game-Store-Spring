package gamestore.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * The User game badge dto.
 *
 * @author Dimitar Ivanov
 */
public class UserGameBadgeDto {

    private String userName;

    private String gameName;

    private String badgeName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate earnedOn;

    private BigDecimal price;

}
