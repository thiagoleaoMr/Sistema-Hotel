package br.ufla.gac111.grupo2;

/**
 * Classe que implementa o quarto Deluxe.
 * 
 * É uma subclasse de Quarto. Essa classe herda atributos gerais de sua classe
 * pai, de modo a sobrescrevê-los, e adiciona outros
 * atributos que são específicos dos quartos Deluxe.
 * 
 * @author THIAGO LEÃO MARRA e NATHAN HENRIQUE RIBEIRO DE ASSIS
 * @version 2023.02.11
 **/
public class Deluxe extends Quarto {
    private boolean temVaranda;

    /**
     * Método construtor.
     * Cria o Deluxe e atribui suas caracterísicas, passadas por parâmetro.
     * Sobrescreve os atributos da superclasse Quarto.
     * 
     * @param andarQuarto  Define o andar do hotel em que o quarto Deluxe se
     *                     encontra.
     * @param numeroQuarto Define o número do quarto Deluxe.
     * @param temVaranda   Define se o quarto Deluxe tem varanda ou não.
     * @param tipoCama     Define o tipo de cama existente no quarto Deluxe.
     */
    public Deluxe(int andarQuarto, int numeroQuarto, boolean temVaranda, String tipoCama) {
        super(andarQuarto, numeroQuarto, tipoCama);
        this.temVaranda = temVaranda;
        precodiaria();
        setQtdMaxPessoas(6);
    }

    /**
     * Método que retorna, com valor booleano, se o quarto Deluxe tem varanda ou
     * não.
     * 
     * @return temVaranda
     */
    public boolean getTemVaranda() {
        return temVaranda;
    }

    /**
     * Método que altera o valor do atributo referente ao preço da diária do quarto
     * Deluxe.
     * O valor da diária desse quarto modifica caso ele possua varanda ou não.
     * 
     * @param precoDiaria
     */
    public void precodiaria() {
        if (getTemVaranda() == true) {
            setPrecoDiaria(350.00);
        } else {
            setPrecoDiaria(300.00);
        }
    }

    /**
     * Método polimórfico.
     * Sobrescreve o método toString() e retorna a descrição completa do quarto
     * Deluxe.
     * Esse método concatena a descrição existente na superclasse com as
     * características atribuidas na classe Deluxe.
     * Retorna a descrição completa com o número, o andar, o tipo de cama, a
     * quantidade máxima de pessoas, se tem varanda ou não e o preço da diária do
     * quarto.
     * 
     * @return super.toString() + super.getQtdMaxPessoas() + super.getPrecoDiaria()
     */
    @Override
    public String toString() {
        if (getTemVaranda() == true) {
            return "Quarto Deluxe número " + super.toString()
                    + "\nO quarto Deluxe tem 14 m² e capacidade para " + super.getQtdMaxPessoas()
                    + " hóspedes. Inclui banheiro privativo com banheira, cama, armário, criado mudo, mesa grande, abajur, armário, um par de poltronas e um sofá.\nEste quarto conta com uma grande varanda e espaço para leitura.\nO preço da diária é R$ "
                    + super.getPrecoDiaria();
        } else {
            return "Quarto Deluxe número " + super.toString()
                    + "\nO quarto Deluxe tem 14 m² e capacidade para " + super.getQtdMaxPessoas()
                    + " hóspedes. Inclui banheiro privativo com banheira, cama, armário, criado mudo, mesa grande, abajur, armário, um par de poltronas e um sofá.\nEste quarto nao possui varanda.\nO preço da diária é R$ "
                    + super.getPrecoDiaria();
        }
    }
}
