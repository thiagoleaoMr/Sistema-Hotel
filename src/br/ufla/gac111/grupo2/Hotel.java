package br.ufla.gac111.grupo2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe Hotel - um Hotel para ser gerenciado e administrado.
 *
 * Esta classe eh parte da aplicacao "Sistema de gerenciamento de hotel".
 * "Sistema de gerenciamento de hotel" eh um sistema que controla e administra
 * um hotel.
 *
 * Representa um hotel localizado em uma edificação característica, com quartos
 * de diferentes tipos, com reservas sendo realizadas e quartos sendo
 * reservados.
 * Para que sejam lidos e gravados arquivos desta classe, é implementada a
 * interface "Serializable", que se trata de uma interface pronta, disponível
 * pelo pacote Java, que serve
 * para indicar para a JVM que os objetos daquela classe podem ser serializados
 * e arquivados.
 * 
 * @author THIAGO LEÃO MARRA, NATHAN HENRIQUE RIBEIRO DE ASSIS e PETHRUS DOTI
 *         MODESTO
 * @version 2023.02.20
 */
public class Hotel implements Serializable {
    private int qtdAndares;
    private int qtdQuartosStandard;
    private int qtdQuartosMaster;
    private int qtdQuartosDeluxe;
    private Map<String, Reserva> colecaoReservas;
    private Map<Integer, Quarto> colecaoQuartos;
    private static Hotel instanciaUnica;
    private static final long serialVersionUID = 1L;

    /**
     * Cria um hotel com a "quantidade de andares", "quantidade de quartos Standard,
     * "quantidade de quartos Master" e a "quantidade de quartos Deluxe" passados
     * por parâmetro.
     * 
     * @param qtdAndares         A quantidade de andares do hotel.
     * @param qtdQuartosStandard A quantidade de quartos Standard do hotel.
     * @param qtdQuartosMaster   A quantidade de quartos Master do hotel.
     * @param qtdQuartosDeluxe   A quantidade de quartos Deluxe do hotel.
     */
    private Hotel(int qtdAndares, int qtdQuartosStandard, int qtdQuartosMaster, int qtdQuartosDeluxe) {
        this.qtdAndares = qtdAndares;
        this.qtdQuartosStandard = qtdQuartosStandard;
        this.qtdQuartosMaster = qtdQuartosMaster;
        this.qtdQuartosDeluxe = qtdQuartosDeluxe;
        colecaoReservas = new HashMap<>();
        colecaoQuartos = new HashMap<>();
    }

    /**
     * Garante que somente uma instância de Hotel seja criada (Padrão de projeto
     * Singleton).
     * 
     * @param qtdAndares         A quantidade de andares do hotel.
     * @param qtdQuartosStandard A quantidade de quartos Standard do hotel.
     * @param qtdQuartosMaster   A quantidade de quartos Master do hotel.
     * @param qtdQuartosDeluxe   A quantidade de quartos Deluxe do hotel.
     * @return Hotel - O objeto instanciado do hotel.
     */
    public static Hotel getInstance(int qtdAndares, int qtdQuartosStandard, int qtdQuartosMaster,
            int qtdQuartosDeluxe) {
        if (instanciaUnica == null) {
            instanciaUnica = new Hotel(qtdAndares, qtdQuartosStandard, qtdQuartosMaster, qtdQuartosDeluxe);
        }
        return instanciaUnica;
    }

    /**
     * Retorna o objeto de Hotel referenciado.
     * 
     * @return Hotel - O objeto instanciado do hotel.
     */
    public static Hotel getInstanciaUnica() {
        return instanciaUnica;
    }

    /**
     * Define o Hotel que a variável instanciaUnica irá referenciar.
     * 
     * @param instanciaUnica Objeto hotel a ser referenciado.
     */
    public static void setInstanciaUnica(Hotel instanciaUnica) {
        Hotel.instanciaUnica = instanciaUnica;
    }

    /**
     * Retorna a quantidade de andares.
     * 
     * @return int - A quantidade de andares.
     */
    public int getQtdAndares() {
        return qtdAndares;
    }

    /**
     * Retorna a quantidade de quartos Standard.
     * 
     * @return int - A quantidade de quartos Standard.
     */
    public int getQtdQuartosStandard() {
        return qtdQuartosStandard;
    }

    /**
     * Retorna a quantidade de quartos Master.
     * 
     * @return int - A quantidade de quartos Master.
     */
    public int getQtdQuartosMaster() {
        return qtdQuartosMaster;
    }

    /**
     * Retorna a quantidade de quartos Deluxe.
     * 
     * @return int - A quantidade de quartos Deluxe.
     */
    public int getQtdQuartosDeluxe() {
        return qtdQuartosDeluxe;
    }

    /**
     * Adiciona 1 na quantidade de quartos Standard.
     */
    public void addQtdQuartosStandard() {
        this.qtdQuartosStandard++;
    }

    /**
     * Adiciona 1 na quantidade de quartos Master.
     */
    public void addQtdQuartosMaster() {
        this.qtdQuartosMaster++;
    }

    /**
     * Adiciona 1 na quantidade de quartos Deluxe.
     */
    public void addQtdQuartosDeluxe() {
        this.qtdQuartosDeluxe++;
    }

    /**
     * Decrementa 1 na quantidade de quartos Standard.
     */
    public void subQtdQuartosStandard() {
        this.qtdQuartosStandard--;
    }

    /**
     * Decrementa 1 na quantidade de quartos Master.
     */
    public void subQtdQuartosMaster() {
        this.qtdQuartosMaster--;
    }

    /**
     * Decrementa 1 na quantidade de quartos Deluxe.
     */
    public void subQtdQuartosDeluxe() {
        this.qtdQuartosDeluxe--;
    }

    /**
     * Adiciona uma Reserva na coleção de Reservas do hotel.
     * 
     * @param idReserva A chave (número da reserva) do HashMap.
     * @param r         O objeto reserva a ser adiconado.
     */
    public void adicionarReserva(String idReserva, Reserva r) {
        colecaoReservas.put(idReserva, r);
    }

    /**
     * Adiciona um quarto na coleção de Quartos do hotel.
     * 
     * @param numQuarto A chave (número da reserva) do HashMap.
     * @param q         O objeto reserva a ser adiconado.
     */
    public void adicionarQuarto(int numQuarto, Quarto q) {
        colecaoQuartos.put(numQuarto, q);
    }

    /**
     * Imprime as informaçóes das reservas adicionadas na coleção de reservas.
     */
    public void getColecaoReservasString() {
        for (String id : colecaoReservas.keySet()) {
            System.out.println(colecaoReservas.get(id));
            System.out.println("------------------------------------------------------");
        }
    }

    /**
     * Retorna a coleção de Reservas.
     * 
     * @return Map - Coleção de Reservas.
     */
    public Map<String, Reserva> getColecaoReservas() {
        return Collections.unmodifiableMap(colecaoReservas);
    }

    /**
     * Retorna a coleção dos Quartos.
     * 
     * @return Map - Coleção dos Quartos.
     */
    public Map<Integer, Quarto> getColecaoQuartos() {
        return Collections.unmodifiableMap(colecaoQuartos);
    }

    /**
     * Imprime as informaçóes dos quartos adicionadas na coleção de quartos.
     */
    public void getColecaoQuartosString() {
        for (Integer n : colecaoQuartos.keySet()) {
            System.out.println(colecaoQuartos.get(n));
            System.out.println("------------------------------------------------------");
        }
    }

    /**
     * Cria um objeto do tipo estático Quarto e dinâmico Standard.
     * 
     * @param temTV        Tem ou não TV.
     * @param andarQuarto  O número do andar do quarto.
     * @param numeroQuarto O número do quarto.
     * @param tipoCama     O tipo da cama.
     */
    public void cadastrarQuarto(boolean temTV, int andarQuarto, int numeroQuarto, String tipoCama) {
        Quarto q = new Standard(temTV, andarQuarto, numeroQuarto, tipoCama);
        adicionarQuarto(numeroQuarto, q);
    }

    /**
     * Cria um objeto do tipo estático Quarto e dinâmico Master.
     * 
     * @param andarQuarto  O número do andar do quarto.
     * @param temVP        Tem ou não vista privilegiada.
     * @param numeroQuarto O número do quarto.
     * @param tipoCama     O tipo da cama.
     */
    public void cadastrarQuarto(int andarQuarto, boolean temVP, int numeroQuarto, String tipoCama) {
        Quarto q = new Master(andarQuarto, temVP, numeroQuarto, tipoCama);
        adicionarQuarto(numeroQuarto, q);
    }

    /**
     * Cria um objeto do tipo estático Quarto e dinâmico Deluxe.
     * 
     * @param andarQuarto  O número do andar do quarto.
     * @param numeroQuarto O número do quarto.
     * @param temVA        Tem ou não Varanda.
     * @param tipoCama     O tipo da cama.
     */
    public void cadastrarQuarto(int andarQuarto, int numeroQuarto, boolean temVA, String tipoCama) {
        Quarto q = new Deluxe(andarQuarto, numeroQuarto, temVA, tipoCama);
        adicionarQuarto(numeroQuarto, q);
    }

    /**
     * Remove uma reserva da coleção de Reservas do hotel.
     * 
     * @param idReservaouCPF A chave (número da reserva ou CPF do Hóspede
     *                       responsável) do HashMap.
     */
    public void removerReserva(String idReservaouCPF) {
        colecaoReservas.remove(idReservaouCPF);
    }

    /**
     * Remove um quarto da coleção de Quartos do hotel.
     * 
     * @param num A chave (número do quarto) do HashMap.
     */
    public void removerQuarto(int num) {
        colecaoQuartos.remove(num);
    }

    /**
     * Retorna uma coleção de quartos que estarão disponíveis para uma reserva com
     * datas definidas.
     * 
     * @param dataEntrada Data de entrada.
     * @return Map - Coleção de quartos que estarão disponíveis para as datas que a
     *         reserva ocupa.
     */
    public Map<Integer, Quarto> gerarListaQuartosLivres(String aux[], int diarias) {
        List<Integer> listaQuartosIguais = new ArrayList<>();
        Map<Integer, Quarto> colecaoQuartos2 = new HashMap<>();
        colecaoQuartos2.putAll(getColecaoQuartos());
        if (getColecaoReservas().isEmpty()) {
            return colecaoQuartos2;
        } else {
            for (String id : getColecaoReservas().keySet()) {
                for (int i = 0; i < getColecaoReservas().get(id).getQtdDiarias(); i++) {
                    for (int j = 0; j < diarias; j++) {
                        if (aux[j].equals(getColecaoReservas().get(id).diasReservados(
                                getColecaoReservas().get(id).getDataEntrada(),
                                getColecaoReservas().get(id).getQtdDiarias())[i])) {
                            for (Integer num : getColecaoReservas().get(id).getColecaoQuartosReservados().keySet()) {
                                int a = getColecaoReservas().get(id).getColecaoQuartosReservados().get(num)
                                        .getNumeroQuarto();
                                listaQuartosIguais.add(a);
                            }
                        }
                    }
                }
            }

            for (int a : listaQuartosIguais) {
                for (Integer num : getColecaoQuartos().keySet()) {
                    if (getColecaoQuartos().get(num).getNumeroQuarto() == a) {
                        colecaoQuartos2.remove(num);
                    }
                }
            }
            return colecaoQuartos2;
        }
    }
}