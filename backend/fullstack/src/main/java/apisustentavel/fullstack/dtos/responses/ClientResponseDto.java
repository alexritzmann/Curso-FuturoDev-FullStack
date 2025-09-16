package apisustentavel.fullstack.dtos.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientResponseDto
{
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String cnpj;
    private String razaoSocial;
}
