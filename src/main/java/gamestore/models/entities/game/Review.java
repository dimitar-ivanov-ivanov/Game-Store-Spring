package gamestore.models.entities.game;

import gamestore.models.entities.user.User;
import gamestore.utils.constants.TextConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * The type Review.
 *
 * @author Dimitar Ivanov
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private long reviewId;

    /**
     * The content of the review.
     * Must not be empty or null.
     *
     * @see TextConstants#DESCRIPTION_CANNOT_BE_BLANK
     */
    @NotBlank(message = TextConstants.DESCRIPTION_CANNOT_BE_BLANK)
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * The review the game was written about.
     *
     * @see Game
     */
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    /**
     * The author of the review.
     *
     * @see User
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Instantiates a new Review.
     *
     * @param description the description of the review
     * @param game        the game the review is about
     * @param user        the author of the review
     */
    public Review(String description, Game game, User user) {
        this.description = description;
        this.game = game;
        this.user = user;
    }
}
