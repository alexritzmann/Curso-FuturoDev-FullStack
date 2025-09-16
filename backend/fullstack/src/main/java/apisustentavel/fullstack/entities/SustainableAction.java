package apisustentavel.fullstack.entities;

import apisustentavel.fullstack.enums.ActionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sustainable_actions")
public class SustainableAction
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "action_type")
    private ActionType type;

    @NotBlank
    @Column(nullable = false, name = "title", length = 254)
    private String title;

    @NotBlank
    @Column(nullable = false, name = "description", length = 1023)
    private String description;

    @NotNull
    @PastOrPresent(message = "A data da ação não pode ser uma data futura")
    @Column(name = "date_realization")
    private LocalDate date;

    @NotBlank
    @Column(name = "responsible", nullable = false, length = 127)
    private String responsible;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client promoter;

}