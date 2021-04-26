package gamestore.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * The User bought game dto.
 *
 * @author Dimitar Ivanov
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserBoughtGameDto implements Serializable {

    private String userName;

    private String gameName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate boughtOn;

    /**
     * The total hours the user played.
     */
    @Column(name = "hours_played_total")
    private int hoursPlayedTotal;

    /**
     * The hours the user played in the last two weeks.
     */
    @Column(name = "hours_played_last_two_weeks")
    private int hoursPlayerLastTwoWeeks;

}
