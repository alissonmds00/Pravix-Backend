package com.alissonmds.pravix.CRUD.Clientes.domain.cliente;

import com.alissonmds.pravix.CRUD.Clientes.domain.endereco.DadosCadastramentoEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastramentoCliente(
        @NotBlank String nome,
        @NotBlank @Pattern(regexp = "\\d{11}") String cpf,
        @NotBlank @Pattern(regexp = "[0-9]{11}") String telefone,
        @Valid DadosCadastramentoEndereco endereco) {
}
