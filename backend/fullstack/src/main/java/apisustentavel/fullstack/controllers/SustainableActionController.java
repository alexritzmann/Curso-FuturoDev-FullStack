package apisustentavel.fullstack.controllers;

import apisustentavel.fullstack.dtos.requests.SustainableActionRequestDto;
import apisustentavel.fullstack.dtos.responses.SustainableActionResponseDto;
import apisustentavel.fullstack.services.SustainableActionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sustainable-actions")
public class SustainableActionController
{
    private final SustainableActionService service;

    @GetMapping
    public List<SustainableActionResponseDto> findAll()
    {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public SustainableActionResponseDto findById(@PathVariable Long id)
    {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SustainableActionResponseDto create(@Valid @RequestBody SustainableActionRequestDto dto)
    {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public SustainableActionResponseDto update(@PathVariable Long id, @Valid @RequestBody SustainableActionRequestDto dto)
    {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id)
    {
        service.delete(id);
    }

}
