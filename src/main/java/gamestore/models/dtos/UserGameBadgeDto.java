package gamestore.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * The User game badge dto.
 *
 * @author Dimitar Ivanov
 * @see gamestore.models.entities.user.UserGameBadge
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserGameBadgeDto implements Serializable {

    private String username;

    private String gameName;

    private String badgeName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate earnedOn;

    private BigDecimal price;

}
