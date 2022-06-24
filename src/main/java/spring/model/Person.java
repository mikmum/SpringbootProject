package spring.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person {
    @Id
    @Column(name = "PESEL")
    private String PESEL;

    @Column(name = "Forename", nullable = false)
    private String forename;

    @Column(name = "Surname", nullable = false)
    private String surname;

    public Person() {}

    public Person(String PESEL, String forename, String surname) {
        this.PESEL = PESEL;
        this.forename = forename;
        this.surname = surname;
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setForename(String forename ) {
        this.forename = forename;
    }

    public void setSurname( String surname ) {
        this.surname = surname;
    }

    public void setPESEL( String PESEL ) {
        this.PESEL = PESEL;
    }

}
