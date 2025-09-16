package apisustentavel.fullstack.services;

import apisustentavel.fullstack.dtos.responses.ClientResponseDto;
import apisustentavel.fullstack.entities.Client;

public interface ClientService
{
    Client getClientById(Long id);
    ClientResponseDto getClientResponseDto(Client client);
}
