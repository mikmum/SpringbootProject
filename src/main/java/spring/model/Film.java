package spring.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "films")
@Table(name = "films")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Film {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "film_seq")
    @Column(name = "ID")
    private int id;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Genre", nullable = false)
    private String genre;

    @Column(name = "Length", nullable = false)
    private int length;

    @Column(name = "Premiere_Date", nullable = false)
    private ZonedDateTime premiere;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Review_ID")
    private Ratings ratings;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Studio")
    private Studio studio;

    @ManyToMany(mappedBy = "films")
    private Set<Actor> actors = new HashSet<Actor>();

    public Film() {}

    public Film(String title, String genre, int length, ZonedDateTime premiere) {
        this.title = title;
        this.genre = genre;
        this.length = length;
        this.premiere = premiere;
    }

    public void addActor(Actor actor) { this.actors.add(actor); }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ratings getRatings() {
        return ratings;
    }

    public void setRatings(Ratings ratings) {
        this.ratings = ratings;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    public ZonedDateTime getPremiere() {
        return premiere;
    }

    public void setPremiere(ZonedDateTime premiere) {
        this.premiere = premiere;
    }

    @PreRemove
    private void removeFilmsFromActors() {
        for (Actor a: actors) {
            a.getFilms().remove(this);
        }
    }
}

