package spring.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity(name = "ratings")
@Table(name = "ratings")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Ratings {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ratings_seq")
    @Column(name = "ID")
    private int id;

    @Column(name = "User_Ratings")
    private double userRatings;

    @Column(name = "Reviewer_Ratings")
    private double reviewerRatings;

    @Column(name = "UserVoteNumber")
    private int userVoteNumber;

    @OneToOne(mappedBy = "ratings")
    private Film film;

    public Ratings() {}

    public Ratings(double userRatings, double reviewerRatings, int userVoteNumber, Film film) {
        this.userRatings = userRatings;
        this.reviewerRatings = reviewerRatings;
        this.userVoteNumber = userVoteNumber;
        this.film = film;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(float userRatings) {
        this.userRatings = userRatings;
    }

    public double getReviewerRatings() {
        return reviewerRatings;
    }

    public void setReviewerRatings(float reviewerRatings) {
        this.reviewerRatings = reviewerRatings;
    }

    public int getUserVoteNumber() {
        return userVoteNumber;
    }

    public void setUserVoteNumber(int userVoteNumber) {
        this.userVoteNumber = userVoteNumber;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @PreRemove
    private void removeRatingsFromFilm() {
        if (film!=null) { film.setRatings(null); }
    }

}
