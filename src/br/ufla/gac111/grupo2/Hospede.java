package br.ufla.gac111.grupo2;

import java.io.Serializable;

/**
 * É a classe que cadastra os atributos das pessoas que realizam reservas no
 * hotel, sejam elas os hóspedes responsáveis pela reserva ou dependentes.
 * Para que sejam lidos e gravados arquivos desta classe, é implementada a
 * interface "Serializable", que se trata de uma interface pronta,
 * disponível pelo pacote Java, que serve para indicar para a JVM que os objetos
 * daquela classe podem ser serializados e arquivados.
 * 
 * @author LUÍS FILIPE DESTÉFANI PENHA e PETHRUS DOTI MODESTO
 * @version 2023.02.17
 */
public class Hospede implements Serializable {
    private String nome;
    private String cpf;
    private String rg;
    private String telefone;
    private String email;
    private String dataNascimento;
    private boolean hospedeResponsavel;

    /**
     * Método construtor de Hospede.
     * Cria um hóspede e define seus atributos, passados por parâmetro.
     * 
     * @param nome           Define o nome do hóspede.
     * @param cpf            Define o CPF do hóspede.
     * @param rg             Define o RG do hóspede.
     * @param telefone       Define oTelefone do hóspede.
     * @param email          Define o email do hóspede.
     * @param dataNascimento Define a data de nascimento do hóspede.
     */
    public Hospede(String nome, String cpf, String rg, String telefone, String email, String dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
        hospedeResponsavel = false;
    }

    /**
     * Método que retorna o nome do hóspede.
     * 
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método que retorna o CPF do hóspede.
     * 
     * @return cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Método que retorna o RG do hóspede.
     * 
     * @return rg
     */
    public String getRg() {
        return rg;
    }

    /**
     * Método que retorna o telefone do hóspede.
     * 
     * @return telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Método que retorna o email do hóspede.
     * 
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Método que retorna a data de nascimento do hóspede.
     * 
     * @return dataNascimento
     */
    public String getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Método que retorna, com valor booleano, se o hóspede é o hóspede responsável
     * pela reserva ou não.
     * 
     * @return
     */
    public boolean getHospedeResponsavel() {
        return hospedeResponsavel;
    }

    /**
     * Método que altera o valor do atributo referente ao hóspede responsável pela
     * reserva.
     * O valor booleano que diz se o hóspede é o responsável pela reserva ou não é
     * passado por parâmetro e é utilizado para alterar o valor inicial do atributo.
     * 
     * @param HospedeResponsavel
     */
    public void setHospedeResponsavel(boolean HospedeResponsavel) {
        this.hospedeResponsavel = HospedeResponsavel;
    }

    /**
     * Método que exibe os dados do hóspede.
     * Sobrescreve o método toString() e retorna a descrição completa do hóspede
     * cadastrado.
     * Esse método concatena e retorna os valores atribuidos aos hóspedes
     * responsáveis e aos hóspedes dependentes.
     * 
     * @return getNome() + getCpf() + getRg() + getTelefone() + getEmail() +
     *         getDataNascimento()
     */
    @Override
    public String toString() {
        if (getHospedeResponsavel() == true) {
            return "Hóspede responsável: Nome:" + getNome() + ", CPF:" + getCpf() + "\nRG:" + getRg() + ", Telefone:"
                    + getTelefone() + "\nEmail:"
                    + getEmail() + ", DataNascimento:" + getDataNascimento();
        } else {
            return "Hóspede dependente: Nome:" + getNome() + ", CPF:" + getCpf() + "\nRG:" + getRg() + ", Telefone:"
                    + getTelefone() + "\nEmail:"
                    + getEmail() + ", DataNascimento:" + getDataNascimento();

        }
    }
}
