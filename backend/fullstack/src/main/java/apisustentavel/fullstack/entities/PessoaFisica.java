package apisustentavel.fullstack.entities;

import apisustentavel.fullstack.validation.annotations.ValidCPF;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoas_fisicas")
@PrimaryKeyJoinColumn(name = "client_id")
public class PessoaFisica extends Client
{
    @ValidCPF
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;


}
