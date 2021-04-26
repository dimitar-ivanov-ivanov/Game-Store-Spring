package gamestore.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * The User game badge dto.
 *
 * @author Dimitar Ivanov
 * @see gamestore.models.entities.user.UserGameBadge
 */
@AllArgsConstructor
public class UserGameBadgeDto {

    private String userName;

    private String gameName;

    private String badgeName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate earnedOn;

    private BigDecimal price;

}
