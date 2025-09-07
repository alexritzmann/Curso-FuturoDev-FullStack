package apisustentavel.fullstack.entities;

import apisustentavel.fullstack.validation.annotations.ValidCNPJ;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoas_juridicas")
@PrimaryKeyJoinColumn(name = "client_id")
public class PessoaJuridica extends Client
{
    @ValidCNPJ
    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    private String razaoSocial;

}