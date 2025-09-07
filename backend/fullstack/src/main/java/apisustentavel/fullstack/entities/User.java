package apisustentavel.fullstack.entities;

import apisustentavel.fullstack.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    @Column(name = "display_name", nullable = false, length = 100)
    private String displayName;

    @NotNull
    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name = "role", nullable = false, length = 20)
    private UserRole role;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
