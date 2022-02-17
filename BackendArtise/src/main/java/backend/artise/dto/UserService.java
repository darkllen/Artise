package backend.artise.dto;

import backend.artise.EntityIdResolver;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user_service")
@ToString
public class UserService {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @Column(name = "time")
    @NotNull
    private Date time;

    @Column(name = "is_cancelled")
    @NotNull
    private Boolean isCancelled;

    @Column(name = "is_confirmed")
    @NotNull
    private Boolean isConfirmed;

    @Column(name = "price")
    @NotNull
    private Float price;


    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            resolver = EntityIdResolver.class,
            property = "id",
            scope= User.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            resolver = EntityIdResolver.class,
            property = "id",
            scope= Service.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="service_id", nullable=false)
    private Service service;

        @JsonIgnore
    @OneToMany(mappedBy="userService", fetch = FetchType.EAGER)
    private List<Notes> notes;

}


