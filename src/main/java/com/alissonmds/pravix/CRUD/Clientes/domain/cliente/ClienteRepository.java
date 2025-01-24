package com.alissonmds.pravix.CRUD.Clientes.domain.cliente;

import jakarta.validation.constraints.Pattern;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Page<Cliente> findAll(Pageable pagina);

    @Query("SELECT c FROM Cliente c WHERE c.nome ILIKE :nome")
    List<Cliente> buscarPeloNome(String nome);

    @Query("SELECT c FROM Cliente c WHERE c.cpf = :cpf")
    List<Cliente> buscarPeloCpf(String cpf);

    @Query("SELECT c FROM Cliente c WHERE c.telefone = :telefone")
    List<Cliente> buscarPeloTelefone(String telefone);

    @Query("SELECT COUNT(c) FROM Cliente c")
    Long contarClientesCadastrados();
}
