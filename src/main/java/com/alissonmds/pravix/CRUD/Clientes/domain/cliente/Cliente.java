package com.alissonmds.pravix.CRUD.Clientes.domain.cliente;


import com.alissonmds.pravix.CRUD.Clientes.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clientes")
@Entity(name = "Cliente")
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    @Embedded
    private Endereco endereco;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Cliente(DadosCadastramentoCliente dados) {
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
    }

    public Cliente() {

    }

    public void alterarInformacoes(DadosEdicaoDadosCliente dados) {
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.alterarEndereco(dados.endereco());
        }
    }
}
