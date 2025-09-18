package apisustentavel.fullstack.services;

import apisustentavel.fullstack.dtos.requests.SustainableActionRequestDto;
import apisustentavel.fullstack.dtos.responses.SustainableActionResponseDto;
import apisustentavel.fullstack.entities.SustainableAction;
import apisustentavel.fullstack.enums.ActionType;
import apisustentavel.fullstack.errors.exceptions.SustainableActionNotFoundException;
import apisustentavel.fullstack.mappers.SustainableActionMapper;
import apisustentavel.fullstack.repositories.SustainableActionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SustainableActionServiceImpl implements SustainableActionService {

    private final SustainableActionRepository repository;
    private final SustainableActionMapper mapper;

    @Override
    @Transactional
    public SustainableActionResponseDto create(SustainableActionRequestDto request) {
        SustainableAction action = mapper.toEntity(request);
        return mapper.toDto(repository.save(action));
    }

    @Override
    public List<SustainableActionResponseDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SustainableActionResponseDto findById(Long id) {
        SustainableAction action = repository.findById(id)
                .orElseThrow(() -> new SustainableActionNotFoundException("Ação sustentável não encontrada com ID: " + id));
        return mapper.toDto(action);
    }

    @Override
    @Transactional
    public SustainableActionResponseDto update(Long id, SustainableActionRequestDto request) {
        SustainableAction existingAction = repository.findById(id)
                .orElseThrow(() -> new SustainableActionNotFoundException("Ação sustentável não encontrada com ID: " + id));

        // Atualiza a entidade existente com os novos dados
        mapper.updateEntityFromDto(request, existingAction);

        return mapper.toDto(repository.save(existingAction));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new SustainableActionNotFoundException("Ação sustentável não encontrada com ID: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public List<SustainableActionResponseDto> findByType(ActionType type) {
        return repository.findByType(type).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}

