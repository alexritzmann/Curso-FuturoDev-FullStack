package apisustentavel.fullstack.mappers;

import apisustentavel.fullstack.dtos.requests.SustainableActionRequestDto;
import apisustentavel.fullstack.dtos.responses.SustainableActionResponseDto;
import apisustentavel.fullstack.entities.SustainableAction;

public class SustainableActionMapper
{

    private final ClientMapper clientService;
    public SustainableActionMapper(ClientMapper clientService) {
        this.clientService = clientService;
    }

}
