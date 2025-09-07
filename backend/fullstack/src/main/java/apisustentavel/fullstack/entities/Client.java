package apisustentavel.fullstack.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "clients")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Client
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

}
