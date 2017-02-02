package spittr.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "SPITTLES")
public class Spittle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 20)
    private String title;

    @NotNull
    @Size(max = 120)
    private String message;

    @NotNull
    private Date time;

    public Spittle() {
    }

    public Spittle(String title) {
        this(title, " ", null);
    }

    public Spittle(String title, String message, Date time) {
        this.title = title;
        this.message = message;
        this.time = time;
    }

    public Long getId() {

        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public Date getTime() {
        return time;
    }
}