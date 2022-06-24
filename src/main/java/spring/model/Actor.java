package spring.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "actors")
@Table(name = "actors")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "pesel")
public class Actor extends Person{

    @Column(name = "Main_Genre")
    private String mainGenre;

    @ManyToMany(cascade = { CascadeType.PERSIST })
    @JoinTable(
            name = "Actor_Appearances",
            joinColumns = { @JoinColumn(name = "Actor_PESEL") },
            inverseJoinColumns = { @JoinColumn(name = "Film_ID") }
    )
    Set<Film> films = new HashSet<Film>();

    public Actor() {}

    public Actor(String PESEL, String forename, String surname) {
        super(PESEL, forename, surname);
    }

    @JsonIgnore
    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    public void addFilms(Film film) { this.films.add(film); }

    public void setMainGenre(String mainGenre) {
        this.mainGenre = mainGenre;
    }

    public String getMainGenre() {
        return this.mainGenre;
    }
}
