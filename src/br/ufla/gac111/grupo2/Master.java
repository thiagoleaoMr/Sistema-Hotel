package br.ufla.gac111.grupo2;

/**
 * Classe que implementa o quarto Master.
 * 
 * É uma subclasse de Quarto. Essa classe herda atributos gerais de sua classe
 * pai, de modo a sobrescrevê-los, e adiciona outros
 * atributos que são específicos dos quartos Master.
 * 
 * @author THIAGO LEÃO MARRA e NATHAN HENRIQUE RIBEIRO DE ASSIS
 * @version 2023.02.11
 **/
public class Master extends Quarto {
    private boolean temVistaPrivilegiada;

    /**
     * Método construtor.
     * Cria o Master e atribui suas caracterísicas, passadas por parâmetro.
     * Sobrescreve os atributos da superclasse Quarto.
     * 
     * @param andarQuarto          Define o andar do hotel em que o quarto Master se
     *                             encontra.
     * @param temVistaPrivilegiada Define se o quarto Master tem vista privilegiada
     *                             ou não.
     * @param numeroQuarto         Define o número do quarto Master.
     * @param tipoCama             Define o tipo de cama existente no quarto Master.
     */
    public Master(int andarQuarto, boolean temVistaPrivilegiada, int numeroQuarto, String tipoCama) {
        super(andarQuarto, numeroQuarto, tipoCama);
        this.temVistaPrivilegiada = temVistaPrivilegiada;
        precodiaria();
        setQtdMaxPessoas(4);
    }

    /**
     * Método que retorna, com valor booleano, se o quarto Master tem vista
     * privilegiada ou não.
     * 
     * @return temVistaPrivilegiada
     */
    public boolean getTemVp() {
        return temVistaPrivilegiada;
    }

    /**
     * Método que altera o valor do atributo referente ao preço da diária do quarto
     * Master.
     * O valor da diária desse quarto modifica caso ele possua vista privilegiada ou
     * não.
     * 
     * @param precoDiaria
     */
    public void precodiaria() {
        if (getTemVp() == true) {
            setPrecoDiaria(250.00);
        } else {
            setPrecoDiaria(200.00);
        }
    }

    /**
     * Método polimórfico.
     * Sobrescreve o método toString() e retorna a descrição completa do quarto
     * Master.
     * Esse método concatena a descrição existente na superclasse com as
     * características atribuidas na classe Master.
     * Retorna a descrição completa com o número, o andar, o tipo de cama, a
     * quantidade máxima de pessoas, se tem vista privilegiada ou não e o preço da
     * diária do quarto.
     * 
     * @return super.toString() + super.getQtdMaxPessoas() + super.getPrecoDiaria()
     */
    @Override
    public String toString() {
        if (getTemVp() == true) {
            return "Quarto Master número " + super.toString()
                    + "\nO quarto Master tem 10 m² e capacidade para " + super.getQtdMaxPessoas()
                    + " hóspedes. Inclui banheiro privativo, cama, armário, criado mudo, mesa média, abajur, armário e um par de poltronas.\nEste quarto conta com uma vista privilegiada, de frente para a praça municipal e com visão para grande parte da cidade.\nO preço da diária é R$ "
                    + super.getPrecoDiaria();
        } else {
            return "Quarto Master número " + super.toString()
                    + "\nO quarto Master tem 10 m² e capacidade para " + super.getQtdMaxPessoas()
                    + " hóspedes. Inclui banheiro privativo, cama, armário, criado mudo, mesa média, abajur, armário e um par de poltronas.\nEste quarto não possui vista privilegiada.\nO preço da diária é R$ "
                    + super.getPrecoDiaria();
        }
    }
}
