package backend.artise.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "profession")
@ToString
public class Profession {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "name", unique = true)
    @NotEmpty
    @Length(min = 3, max = 50)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy="profession", fetch = FetchType.LAZY)
    private List<Category> categories;
}