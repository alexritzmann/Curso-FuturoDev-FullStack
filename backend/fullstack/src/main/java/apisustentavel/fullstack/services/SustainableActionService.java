package apisustentavel.fullstack.services;


import apisustentavel.fullstack.dtos.requests.SustainableActionRequestDto;
import apisustentavel.fullstack.dtos.responses.SustainableActionResponseDto;

import java.util.List;

public interface SustainableActionService
{
    SustainableActionResponseDto create(SustainableActionRequestDto request);
    List<SustainableActionResponseDto> findAll();
    SustainableActionResponseDto findById(Long id);
    SustainableActionResponseDto update(Long id, SustainableActionRequestDto request);
    void delete(Long id);
}
