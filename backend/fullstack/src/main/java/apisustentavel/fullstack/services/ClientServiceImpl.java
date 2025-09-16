package apisustentavel.fullstack.services;

import apisustentavel.fullstack.dtos.responses.ClientResponseDto;
import apisustentavel.fullstack.entities.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService
{

    @Override
    public Client getClientById(Long id) {
        return null;
    }

    @Override
    public ClientResponseDto getClientResponseDto(Client client) {
        return null;
    }
}
