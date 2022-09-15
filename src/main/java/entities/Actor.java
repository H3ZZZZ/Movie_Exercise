package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "actor")
@Getter
@Setter
@NamedQuery(name = "Actor.deleteAllRows", query = "delete from Actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "actor_age", nullable = false)
    private Integer actorAge;

    @Size(max = 45)
    @NotNull
    @Column(name = "actor_name", nullable = false, length = 45)
    private String actorName;

    @NotNull
    @Column(name = "actor_sex", nullable = false)
    private Character actorSex;

    @ManyToMany
    @JoinTable(name = "actor_movie",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> movies = new LinkedHashSet<>();

    public Actor() {
    }

    public Actor(Integer actorAge, String actorName, Character actorSex) {
        this.actorAge = actorAge;
        this.actorName = actorName;
        this.actorSex = actorSex;
    }
}