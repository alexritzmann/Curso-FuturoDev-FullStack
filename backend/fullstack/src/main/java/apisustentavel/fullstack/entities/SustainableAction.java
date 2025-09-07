package apisustentavel.fullstack.entities;

import apisustentavel.fullstack.enums.ActionType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "sustainable_actions")
public class SustainableAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActionType type;

    @Column(nullable = false)
    private String description;

    private LocalDate date;

    private String location;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client promoter;
}