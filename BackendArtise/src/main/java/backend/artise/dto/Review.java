package backend.artise.dto;


import backend.artise.EntityIdResolver;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "review")
@ToString
public class Review {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "text")
    @Length(min = 0, max = 255)
    private String text;

    @Column(name = "rating")
    @NotNull
    private Integer rating;

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
            scope= UserCategory.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_category_id", nullable=false)
    private UserCategory userCategory;

}

