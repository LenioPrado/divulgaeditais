package mobile.divulga.editais.ifsuldeminas.edu.br.model;

public class PrefeituraUser {

    private String nome;
    private String email;
    private String senha;
    private String setor;
    private Integer cep;
    private String endereco;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String telefone;
    private String celular;
    private String responsavel;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }


    public PrefeituraUser() {
        // Default constructor required for calls to DataSnapshot.getValue(PrefeituraUser.class)

    }

    public PrefeituraUser(String username, String email, String senha,String setor, Integer cep, String endereco, Integer numero, String complemento, String bairro, String cidade, String estado, String telefone, String celular, String responsavel ) {
        this.nome = username;
        this.email = email;
        this.senha = senha;
        this.setor = setor;
        this.cep = cep;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.celular = celular;
        this.responsavel = responsavel;

    }


    public static class Construir{

        private String nome;
        private String email;
        private String senha;
        private String setor;
        private Integer cep;
        private String endereco;
        private Integer numero;
        private String complemento;
        private String bairro;
        private String cidade;
        private String estado;
        private String telefone;
        private String celular;
        private String responsavel;

        public Construir(String nome, String senha){
        this.nome = nome;
        this.senha = senha;


        }

        public static  void comEmail(String email){}

        public static void comCidade(String cidade){}
        public static void comCelular(String celular){}


        public PrefeituraUser build(){
            PrefeituraUser user = new PrefeituraUser();
            user.setBairro(this.bairro);
            user.setCelular(this.celular);




            return user;


        }





    }



}
