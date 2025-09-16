package apisustentavel.fullstack.services;

import apisustentavel.fullstack.dtos.requests.SustainableActionRequestDto;
import apisustentavel.fullstack.dtos.responses.SustainableActionResponseDto;
import apisustentavel.fullstack.entities.SustainableAction;
import apisustentavel.fullstack.repositories.SustainableActionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SustainableActionServiceImpl implements SustainableActionService
{
    private final SustainableActionRepository repository;
    private final ClientService clientService;

    @Override
    @Transactional
    public SustainableActionResponseDto create(SustainableActionRequestDto request) {
        SustainableAction action = new SustainableAction();
        mapDtoToEntity(request, action);
        return mapEntityToDto(repository.save(action));
    }

    @Override
    public List<SustainableActionResponseDto> findAll() {
        return repository.findAll().stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SustainableActionResponseDto findById(Long id) {
        SustainableAction action = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ação não encontrada"));
        return mapEntityToDto(action);
    }

    @Override
    @Transactional
    public SustainableActionResponseDto update(Long id, SustainableActionRequestDto request)
    {
        SustainableAction action = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ação não encontrada"));
        mapDtoToEntity(request, action);
        return mapEntityToDto(repository.save(action));
    }

    @Override
    public void delete(Long id)
    {
        repository.deleteById(id);
    }

    private void mapDtoToEntity(SustainableActionRequestDto dto, SustainableAction entity)
    {
        entity.setType(dto.getType());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDate(dto.getDate());
        entity.setResponsible(dto.getResponsible());
        entity.setPromoter(clientService.getClientById(dto.getClientId()));
    }

    private SustainableActionResponseDto mapEntityToDto(SustainableAction entity)
    {
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

}