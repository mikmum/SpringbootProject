package spring.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "chairmen")
@Table(name = "chairmen")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "pesel")
public class Chairman extends Person{

    @Column(name = "Salary", nullable = false)
    private int salary;

    @OneToOne(mappedBy = "chairman")
    private Studio studio;

    public Chairman() {}

    public Chairman(String PESEL, String forename, String surname, int salary) {
        super(PESEL, forename, surname);
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    @PreRemove
    public void removeChairmanfromStudio() {
        if ( studio!=null ){ studio.setChairman(null); } ;
    }
}
