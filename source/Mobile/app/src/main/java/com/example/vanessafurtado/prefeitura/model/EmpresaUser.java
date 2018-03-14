package com.example.vanessafurtado.prefeitura.model;


public class EmpresaUser {

    public String nome;
    public String email;
    public String senha;
    public String cnae;
    public String setor;
    public Integer cep;
    public String endereco;
    public Integer numero;
    public String complemento;
    public String bairro;
    public String cidade;
    public String estado;
    public String telefone;
    public String celular;
    public String responsavel;
    public String identificacao;
    public String tipo;

    public EmpresaUser() {
        // Default constructor required for calls to DataSnapshot.getValue(PrefeituraUser.class)

    }

    public EmpresaUser(String username, String email) {
        this.nome = username;
        this.email = email;
    }

}
