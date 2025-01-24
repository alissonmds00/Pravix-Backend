package com.alissonmds.pravix.CRUD.Clientes.domain.cliente;

import jakarta.validation.constraints.Pattern;

public record DadosBuscaCliente(
        String nome,
        @Pattern(regexp = "\\d{11}") String cpf,
        @Pattern(regexp = "[0-9]{11}") String telefone
) {
}
