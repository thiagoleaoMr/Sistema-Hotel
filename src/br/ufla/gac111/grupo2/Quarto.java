package br.ufla.gac111.grupo2;

import java.io.Serializable;

/**
 * Classe que implementa os quartos do hotel.
 * 
 * É a classe que possui todos os atributos comuns a todos os quartos e funciona
 * como a superclasse destes.
 * Para que sejam lidos e gravados arquivos desta classe, é implementada a
 * interface "Serializable", que se trata de uma interface pronta,
 * disponível pelo pacote Java, que serve para indicar para a JVM que os objetos
 * daquela classe podem ser serializados e arquivados.
 * 
 * @autor THIAGO LEÃO MARRA e NATHAN HENRIQUE RIBEIRO DE ASSIS
 * @version 2023.02.11
 **/
public class Quarto implements Serializable {
    private int andarQuarto;
    private int numeroQuarto;
    private int qtdMaxPessoas;
    private String tipoCama;
    private double precoDiaria;

    /**
     * Método construtor de Quartos.
     * Cria os quartos e atribui suas características, passadas por parâmetro.
     * 
     * @param andarQuarto  Define o andar do hotel em que o quarto se encontra.
     * @param numeroQuarto Define o número do quarto.
     * @param tipoCama     Define o tipo de cama existente no quarto.
     */
    public Quarto(int andarQuarto, int numeroQuarto, String tipoCama) {
        this.andarQuarto = andarQuarto;
        this.numeroQuarto = numeroQuarto;
        this.tipoCama = tipoCama;
    }

    /**
     * Método que retorna o andar do quarto.
     * 
     * @return andarQuarto
     */
    public int getAndarQuarto() {
        return andarQuarto;
    }

    /**
     * Método que retorna o número do quarto.
     * 
     * @return numeroQuarto
     */
    public int getNumeroQuarto() {
        return numeroQuarto;
    }

    /**
     * Método que retorna o tipo de cama do quarto.
     * 
     * @return tipoCama
     */
    public String getTipoCama() {
        return tipoCama;
    }

    /**
     * Método que altera o valor do atributo referente ao preço da diária do quarto.
     * 
     * @param precoDiaria
     */
    public void setPrecoDiaria(double precoDiaria) {
        this.precoDiaria = precoDiaria;
    }

    /**
     * Método que retorna o preço da diária do quarto.
     * 
     * @return precoDiaria
     */
    public double getPrecoDiaria() {
        return precoDiaria;
    }

    /**
     * Método que retorna a quantidade máxima de pessoas permitidas no quarto.
     * 
     * @return qtdMaxPessoas
     */
    public int getQtdMaxPessoas() {
        return qtdMaxPessoas;
    }

    /**
     * Método que altera o valor do atributo referente à quantidade máxima de
     * pessoas permitidas no quarto.
     * 
     * @param qtdMaxPessoas
     */
    public void setQtdMaxPessoas(int qtdMaxPessoas) {
        this.qtdMaxPessoas = qtdMaxPessoas;
    }

    /**
     * Método polimórfico.
     * Sobrescreve o método toString() e retorna a descrição do quarto, concatenando
     * o número, o andar e o tipo de cama do mesmo.
     * 
     * @return geNumeroQuarto() + getAndarQuarto() + getTipoCama()
     */
    @Override
    public String toString() {
        return +getNumeroQuarto() + " localizado no " + getAndarQuarto()
                + "° andar do Hotel Penaluthi.\nO tipo da cama deste quarto é " + getTipoCama();
    }
}