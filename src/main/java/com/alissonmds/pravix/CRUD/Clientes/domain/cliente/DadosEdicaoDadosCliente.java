package com.alissonmds.pravix.CRUD.Clientes.domain.cliente;

import com.alissonmds.pravix.CRUD.Clientes.domain.endereco.DadosEdicaoEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

public record DadosEdicaoDadosCliente(
        @Pattern(regexp = "[0-9]{11}") String telefone,
        @Valid DadosEdicaoEndereco endereco
) {
}
