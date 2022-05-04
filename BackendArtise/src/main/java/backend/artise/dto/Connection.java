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
@Table(name = "connection")
@ToString
public class Connection {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status")
    @NotNull
    private Integer status;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            resolver = EntityIdResolver.class,
            property = "id",
            scope= User.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_init_id", nullable=false)
    private User user_init;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            resolver = EntityIdResolver.class,
            property = "id",
            scope= User.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_to_connect_id", nullable=false)
    private User user_to_connect;
}

