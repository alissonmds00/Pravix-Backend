package com.alissonmds.pravix.CRUD.Clientes.controller;



import com.alissonmds.pravix.CRUD.Clientes.domain.cliente.*;
import com.alissonmds.pravix.CRUD.Clientes.infra.exceptions.ValidacaoException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<DadosDetalhamentoCliente> cadastrarCliente(@RequestBody @Valid DadosCadastramentoCliente dados, UriComponentsBuilder uriBuilder) {
        try {
            var cliente = new Cliente(dados);
            clienteRepository.save(cliente);
            var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
            return ResponseEntity.created(uri).body(new DadosDetalhamentoCliente(cliente));
        } catch (Exception e) {
            throw new ValidacaoException("Erro ao cadastrar cliente. Já existe um usuário cadastrado com esse número de telefone.");
        }
    }

    @GetMapping()
    public ResponseEntity<Page<DadosDetalhamentoCliente>> listarClientes(@PageableDefault(size = 20, sort = {"nome"}) Pageable pagina) {
        var clientes = clienteRepository.findAll(pagina).map(DadosDetalhamentoCliente::new);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoCliente> buscarCliente(@PathVariable Long id) {
        var cliente = clienteRepository.findById(id).orElseThrow(() -> new ValidacaoException("Cliente não encontrado na nossa base de dados."));
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Null> deletarCliente(@PathVariable Long id) {
        var cliente = clienteRepository.findById(id).orElseThrow(() -> new ValidacaoException("Cliente não encontrado na nossa base de dados."));
        clienteRepository.delete(cliente);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoCliente> alterarDadosCliente(@PathVariable Long id, @RequestBody @Valid DadosEdicaoDadosCliente dados) {
        var cliente = clienteRepository.findById(id).orElseThrow(() -> new ValidacaoException("Cliente não encontrado na nossa base de dados."));
        cliente.alterarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }
}

