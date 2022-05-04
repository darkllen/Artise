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
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "category")
@ToString
public class Category {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "name")
    @NotEmpty
    @Length(min = 3, max = 50)
    private String name;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            resolver = EntityIdResolver.class,
            property = "id",
            scope= Profession.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @ToString.Exclude
    @JoinColumn(name="profession_id", nullable=false)
    private Profession profession;

    @JsonIgnore
    @OneToMany(mappedBy="category", fetch = FetchType.LAZY)
    private List<UserCategory> userCategories;

}

