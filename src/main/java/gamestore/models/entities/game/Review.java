package gamestore.models.entities.game;

import gamestore.utils.constants.TextConstants;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * The type Review.
 */
@Entity
@Table(name = "reviews")
public class Review implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private long reviewId;

    @NotBlank(message = TextConstants.DESCRIPTION_CANNOT_BE_BLANK)
    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    /**
     * Instantiates a new Review.
     *
     * @param description the description of the review
     * @param game        the game the review is about
     */
    public Review(String description, Game game) {
        this.description = description;
        this.game = game;
    }
}
