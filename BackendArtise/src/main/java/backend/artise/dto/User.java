package backend.artise.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "user")
@ToString
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//
//    @Column(name = "nickname", unique = true)
//    @NotEmpty
//    @Length(min = 3, max = 30)
    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email", unique = true)
    @NotEmpty
    @Length(min = 5, max = 30)
    private String email;

    @Column(name = "password")
    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Length(min = 3, max = 255)
    private String password;

    @Column(name = "name")
    @NotEmpty
    @Length(min = 3, max = 20)
    private String name;

    @Column(name = "surname")
    @NotEmpty
    @Length(min = 3, max = 20)
    private String surname;

    @Column(name = "phone")
    @Length(min = 7, max = 20)
    private String phone;

    @Column(name = "info")
    @Length(max = 255)
    private String info;

    @Column
    private String profile_image;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Review> reviews;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<UserCategory> userCategories;

    @JsonIgnore
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<UserService> userServices;

}