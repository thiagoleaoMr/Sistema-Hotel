package br.ufla.gac111.grupo2;

/**
 * Classe que implementa o quarto Standard.
 * 
 * É uma subclasse de Quarto. Essa classe herda atributos gerais de sua classe
 * pai, de modo a sobrescrevê-los, e adiciona outros
 * atributos que são específicos dos quartos Standard.
 * 
 * @author THIAGO LEÃO MARRA e NATHAN HENRIQUE RIBEIRO DE ASSIS
 * @version 2023.02.11
 **/
public class Standard extends Quarto {
    private boolean temTV;

    /**
     * Método construtor.
     * Cria o Standard e atribui suas caracterísicas, passadas por parâmetro.
     * Sobrescreve os atributos da superclasse Quarto.
     * 
     * @param temTV        Define se o quarto Standard tem TV ou não.
     * @param andarQuarto  Define o andar do hotel em que o quarto Standard se
     *                     encontra.
     * @param numeroQuarto Define o número do quarto Standard.
     * @param tipoCama     Define o tipo de cama existente no quarto Standard.
     */
    public Standard(boolean temTV, int andarQuarto, int numeroQuarto, String tipoCama) {
        super(andarQuarto, numeroQuarto, tipoCama);
        this.temTV = temTV;
        precodiaria();
        setQtdMaxPessoas(2);
    }

    /**
     * Método que retorna, com valor booleano, se o quarto Standard tem TV ou não.
     * 
     * @return temTV
     */
    public boolean getTemTV() {
        return temTV;
    }

    /**
     * Método que altera o valor do atributo referente ao preço da diária do quarto
     * Standard.
     * O valor da diária desse quarto modifica caso ele possua TV ou não.
     * 
     * @param precoDiaria
     */
    public void precodiaria() {
        if (getTemTV() == true) {
            setPrecoDiaria(150.00);
        } else {
            setPrecoDiaria(100.00);
        }
    }

    /**
     * Método polimórfico.
     * Sobrescreve o método toString() e retorna a descrição completa do quarto
     * Standard.
     * Esse método concatena a descrição existente na superclasse com as
     * características atribuidas na classe Standard.
     * Retorna a descrição completa com o número, o andar, o tipo de cama, a
     * quantidade máxima de pessoas, se tem TV ou não e o preço da diária do quarto.
     * 
     * @return super.toString() + super.getQtdMaxPessoas() + super.getPrecoDiaria()
     */
    @Override
    public String toString() {
        if (getTemTV() == true) {
            return "Quarto Standard número " + super.toString()
                    + "\nO quarto Standard tem 8 m² e capacidade para " + super.getQtdMaxPessoas()
                    + " hóspedes. Inclui banheiro privativo, cama, armário, criado mudo, mesa pequena, abajur e armário.\nConta com TV a cabo, acesso ao Youtube, Netflix e HBO.\nO preço da diária é R$ "
                    + super.getPrecoDiaria();
        } else {
            return "Quarto Standard número " + super.toString()
                    + "\nO quarto Standard tem 8 m² e capacidade para " + super.getQtdMaxPessoas()
                    + " hóspedes. Inclui banheiro privativo, cama, armário, criado mudo, mesa pequena, abajur e armário.\nEste quarto não possui TV.\nO preço da diária é R$ "
                    + super.getPrecoDiaria();
        }
    }
}
