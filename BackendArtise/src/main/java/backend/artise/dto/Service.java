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
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "service")
@ToString
public class Service {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "name")
    @NotEmpty
    @Length(min = 3, max = 50)
    private String name;

    @Column(name = "description")
    @Length(min = 3, max = 255)
    private String description;

    @Column(name = "price")
    @NotNull
    private Float price;

    @Column(name = "duration_minutes")
    @NotNull
    private Integer duration_minutes;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            resolver = EntityIdResolver.class,
            property = "id",
            scope= UserCategory.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_category_id", nullable=false)
    private UserCategory userCategory;

            @JsonIgnore
    @OneToMany(mappedBy="service", fetch = FetchType.EAGER)
    private List<UserService> userServices;
}

