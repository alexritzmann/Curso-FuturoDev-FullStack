package apisustentavel.fullstack.mappers;

import apisustentavel.fullstack.dtos.requests.SustainableActionRequestDto;
import apisustentavel.fullstack.dtos.responses.SustainableActionResponseDto;
import apisustentavel.fullstack.entities.SustainableAction;
import apisustentavel.fullstack.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SustainableActionMapper
{
    private final ClientService clientService;

    public SustainableAction toEntity(SustainableActionRequestDto dto)
    {
        return SustainableAction.builder()
                .type(dto.getType())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .date(dto.getDate())
                .responsible(dto.getResponsible())
                .promoter(clientService.getClientById(dto.getClientId()))
                .build();
    }

    public SustainableActionResponseDto toDto(SustainableAction entity) {
        return SustainableActionResponseDto.builder()
                .id(entity.getId())
                .type(entity.getType())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .date(entity.getDate())
                .responsible(entity.getResponsible())
                .promoter(clientService.getClientResponseDto(entity.getPromoter()))
                .build();
    }

    public void updateEntityFromDto(SustainableActionRequestDto dto, SustainableAction entity) {
        entity.setType(dto.getType());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDate(dto.getDate());
        entity.setResponsible(dto.getResponsible());
        entity.setPromoter(clientService.getClientById(dto.getClientId()));
    }

}