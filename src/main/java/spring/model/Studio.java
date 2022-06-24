package spring.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "studios")
@Table(name = "studios")
/*@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")*/
public class Studio {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studio_seq")
    @Column(name = "ID")
    private int id;

    @Column(name = "Studio_Name", nullable = false)
    private String name;

    @Column(name = "Address")
    private String address;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Chairman_PESEL")
    Chairman chairman;

    @OneToMany(mappedBy = "studio", fetch = FetchType.LAZY)
    private Set<Film> films = new HashSet<Film>();

    public Studio() {}

    public Studio(String name, Chairman chairman) {
        this.name = name;
        this.chairman = chairman;
    }

    public void addFilm(Film film) { this.films.add(film); }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Chairman getChairman() {
        return chairman;
    }

    public void setChairman(Chairman chairman) {
        this.chairman = chairman;
    }

    @JsonIgnore
    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    @PreRemove
    public void removeReferences() {
        for (Film f: films){
            f.setStudio(null);
        }
        if (chairman != null) {chairman.setStudio(null);};
    }


}
