package spittr.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table( name = "SPITTERS" )
public class Spitter {

    public static final String DEFAULT_FIRSTNAME = "Jack";
    public static final String DEFAULT_LASTNAME = "Bauer";
    public static final String DEFAULT_USERNAME = "jbauer";
    public static final String DEFAULT_PASSWORD = "24hours";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 5, max = 16)
    private String username;

    @NotNull
    @Size(min = 5, max = 25)
    private String password;

    @NotNull
    @Size(min = 2, max = 30)
    private String firstname;

    @NotNull
    @Size(min = 2, max = 30)
    private String lastname;

    public Spitter() {
        this(DEFAULT_FIRSTNAME, DEFAULT_LASTNAME, DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

    public Spitter(String firstName, String lastName, String username, String password) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.username = username;
        this.password = password;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Spitter spitter = ( Spitter ) o;

        if (getId() != null ? !getId().equals(spitter.getId()) : spitter.getId() != null) {
            return false;
        }
        if (!getUsername().equals(spitter.getUsername())) {
            return false;
        }
        if (!getPassword().equals(spitter.getPassword())) {
            return false;
        }
        if (!firstname.equals(spitter.firstname)) {
            return false;
        }
        return lastname.equals(spitter.lastname);
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getUsername().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + firstname.hashCode();
        result = 31 * result + lastname.hashCode();
        return result;
    }
}
