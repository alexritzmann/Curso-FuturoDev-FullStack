package apisustentavel.fullstack.dtos.responses;


import apisustentavel.fullstack.enums.ActionType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SustainableActionResponseDto
{
    private Long id;
    private ActionType type;
    private String title;
    private String description;
    private LocalDate date;
    private String responsible;
    private ClientResponseDto promoter;
}
