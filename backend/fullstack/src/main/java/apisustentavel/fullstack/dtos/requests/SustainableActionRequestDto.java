package apisustentavel.fullstack.dtos.requests;


import apisustentavel.fullstack.entities.Client;
import apisustentavel.fullstack.enums.ActionType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SustainableActionRequestDto
{

    @NotNull(message = "Perfil é obrigatório. Escolha entre ADMIN ou USER")
    private ActionType type;

    @NotBlank(message = "Título é obrigatório")
    @Size(min = 3, max = 254, message = "Título deve ter entre 3 e 254 caracteres")
    private String title;

    @NotEmpty(message = "Descrição é obrigatória")
    @Size(min = 10, max = 1023, message = "Descrição deve ter entre 10 e 1023 caracteres")
    private String description;

    @NotNull(message = "Data é obrigatória")
    @PastOrPresent(message = "A data não pode ser futura")
    private LocalDate date;

    @NotBlank(message = "Responsável é obrigatório")
    @Size(min = 3, max = 127, message = "Responsável deve ter entre 3 e 127 caracteres")
    private String responsible;

    @Valid
    @NotNull
    @Positive(message = "ID do material deve ser positivo")
    private Long clientId;


}
