package apisustentavel.fullstack.entities;

import apisustentavel.fullstack.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "display_name", nullable = false, length = 123)
    private String displayName;

    @NotBlank
    @Column(name = "username", nullable = false, unique = true, length = 63)
    private String username;

    @NotBlank
    @Column(name = "password", nullable = false, length = 63)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 31)
    private UserRole role;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
