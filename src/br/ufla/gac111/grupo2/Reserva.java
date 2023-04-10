package br.ufla.gac111.grupo2;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;
import java.util.Collections;

/**
 * Classe Reserva - uma reserva que pode ser realizada pelo sistema do hotel.
 *
 * Esta classe eh parte da aplicacao "Sistema de gerenciamento de hotel".
 * "Sistema de gerenciamento de hotel" eh um sistema que controla e administra
 * um hotel.
 *
 * Uma "Reserva" representa um documento que é responsável por armazenar as
 * informações de uma reserva específica dentro do sistema. Cada reserva possui
 * um número de
 * identificação, uma coleção de hóspedes e uma coleção de quartos reservados.
 * Para que sejam lidos e gravados arquivos desta classe, é implementada a
 * interface "Serializable", que se trata de uma interface pronta, disponível
 * pelo pacote Java, que serve
 * para indicar para a JVM que os objetos daquela classe podem ser serializados
 * e arquivados.
 * 
 * @author NATHAN HENRIQUE RIBEIRO DE ASSIS, PETHRUS DOTI MODESTO e THIAGO LEÃO
 *         MARRA
 * @version 2023.02.20
 */
public class Reserva implements Serializable {
    private String idReserva;
    private String dataEntrada;
    private int qtdDiarias;
    private int qtdHospedes;
    private int qtdQuartos;
    private static final double taxaDeServico = 49.90;
    private String tipoDePagamento;
    private Map<String, Hospede> colecaoHospedes;
    private Map<Integer, Quarto> colecaoQuartosReservados;
    private static Hospede h;
    private int aux = 0;
    private static int cont = 0;

    /**
     * Cria uma reserva com a "data de entrada", "quantidade de diárias",
     * "quantidade de hóspedes",
     * "quantidade de quartos" e o "tipo de pagamento" passados por parâmetro.
     * 
     * @param dataEntrada     A data de entrada no hotel.
     * @param qtdDiarias      A quantidade de diarias que os hóspedes ficaram no
     *                        hotel.
     * @param qtdHospedes     A quantidade de hóspedes dessa reserva que ficarão no
     *                        hotel.
     * @param qtdQuartos      A quantidade de quartos que ficarão reservados durante
     *                        a reserva.
     * @param tipoDePagamento O tipo de pagamento escolhido pelo hóspede responsável
     *                        para a reserva.
     */
    public Reserva(String dataEntrada, int qtdDiarias, int qtdHospedes, int qtdQuartos, String tipoDePagamento) {
        int cont2 = cont;
        this.idReserva = dataEntrada + "@" + cont2;
        this.dataEntrada = dataEntrada;
        this.qtdDiarias = qtdDiarias;
        this.qtdHospedes = qtdHospedes;
        this.qtdQuartos = qtdQuartos;
        this.tipoDePagamento = tipoDePagamento;
        colecaoHospedes = new HashMap<>();
        colecaoQuartosReservados = new HashMap<>();
        cont++;
    }

    /**
     * Retorna o número de reserva.
     * 
     * @return String - O número de reserva.
     */
    public String getIdReserva() {
        return idReserva;
    }

    /**
     * Define o valor do contador utilizado para formar o número de reserva.
     * 
     * @param cont O valor do contador.
     */
    public static void setCont(int cont) {
        Reserva.cont = cont;
    }

    /**
     * Retorna a data de entrada
     * 
     * @return String - A data de entrada.
     */
    public String getDataEntrada() {
        return dataEntrada;
    }

    /**
     * Retorna a quantidade de diárias.
     * 
     * @return int - A quantidade de diárias.
     */
    public int getQtdDiarias() {
        return qtdDiarias;
    }

    /**
     * Retorna a quantidade de hóspedes.
     * 
     * @return int - A quantidade de hóspedes.
     */
    public int getQtdHospedes() {
        return qtdHospedes;
    }

    /**
     * Retorna a quantidade de quartos.
     * 
     * @return int - A quantidade de quartos.
     */
    public int getQtdQuartos() {
        return qtdQuartos;
    }

    /**
     * Define o preço total das diárias baseado na quantidade de quartos reservados.
     * 
     * @return double - O preço das díarias de uma reserva.
     */
    public double calculoPrecoTotalDiarias() {
        double valor = 0.00;
        for (Integer num : colecaoQuartosReservados.keySet()) {
            valor += colecaoQuartosReservados.get(num).getPrecoDiaria();
        }
        return valor;
    }

    /**
     * Retorna o valor da taxa de serviço.
     * 
     * @return double - O valor da taxa de serviço.
     */
    public double getTaxaDeServico() {
        return taxaDeServico;
    }

    /**
     * Retorna o preço total da reserva, sendo o valor das diárias dos quartos
     * reservados mais a taxa de serviço por quarto reservado.
     * 
     * @return double - O preço total da reserva.
     */
    public double getPrecoTotal() {
        return ((getTaxaDeServico() * getQtdQuartos()) + calculoPrecoTotalDiarias());
    }

    /**
     * Retorna o tipo de pagamento da reserva.
     * 
     * @return String - O tipo de pagamento.
     */
    public String getTipoDePagamento() {
        return tipoDePagamento;
    }

    /**
     * Gera uma String contendo as informações de todos hóspedes da reserva.
     * 
     * @return String - Informações dos hóspedes.
     */
    public String gerarColecaoHospedes() {
        StringBuffer infoHospedes = new StringBuffer("");
        for (String s : getColecaoHospedes().keySet()) {
            infoHospedes.append(getColecaoHospedes().get(s).toString() + "\n");
        }
        return infoHospedes.toString();
    }

    /**
     * Gera uma String contendo as informações dos quartos reservados da reserva.
     * 
     * @return String - Informações dos quartos reservados.
     */
    public String gerarColecaoQuartos() {
        StringBuffer infoQuartos = new StringBuffer("");
        for (Integer i : getColecaoQuartosReservados().keySet()) {
            infoQuartos.append(getColecaoQuartosReservados().get(i).toString() + "\n");
        }
        return infoQuartos.toString();
    }

    /**
     * Adiciona um quarto na coleção de quartos reservados da reserva.
     * 
     * @param num A chave (número do quarto) do HashMap.
     * @param q   O objeto quarto a ser adiconado.
     */
    public void adicionarColecaoQuartosReservados(int num, Quarto q) {
        colecaoQuartosReservados.put(num, q);
    }

    /**
     * Retorna a coleção dos quartos reservados.
     * 
     * @return Map - Coleção dos quartos reservados.
     */
    public Map<Integer, Quarto> getColecaoQuartosReservados() {
        return Collections.unmodifiableMap(colecaoQuartosReservados);
    }

    /**
     * Retorna a coleção dos hóspedes da reserva.
     * 
     * @return Map - Coleção dos hóspedes.
     */
    public Map<String, Hospede> getColecaoHospedes() {
        return Collections.unmodifiableMap(colecaoHospedes);
    }

    /**
     * Cria um objeto do tipo hóspede e define se tal é responsável ou não.
     * 
     * @param nome           O nome do hóspede.
     * @param cpf            O CPF do hóspede.
     * @param rg             O RG do hóspede.
     * @param telefone       O telefone do hóspede.
     * @param email          O email do hóspede.
     * @param dataNascimento A data de nascimento do hóspede.
     */
    public void cadastrarHospede(String nome, String cpf, String rg, String telefone, String email,
            String dataNascimento) {
        if (aux == 0) {
            h = new Hospede(nome, cpf, rg, telefone, email, dataNascimento);
            h.setHospedeResponsavel(true);
            aux++;
        } else {
            h = new Hospede(nome, cpf, rg, telefone, email, dataNascimento);
        }
        adicionarHospede(h.getCpf(), h);
    }

    /**
     * Adiciona um hóspede na coleção de hóspedes da reserva.
     * 
     * @param cpf A chave (CPF) do HashMap.
     * @param h   O objeto hóspede a ser adicionado.
     */
    public void adicionarHospede(String cpf, Hospede h) {
        colecaoHospedes.put(cpf, h);
    }

    /**
     * Divide a data passada por parâmetro e retorna o dia da data.
     * 
     * @param d Data.
     * @return int - Dia da data passada por parâmetro.
     */
    public int splitDataDia(String d) {
        String[] data = d.split("/");
        return Integer.parseInt(data[0]);
    }

    /**
     * Divide a data passada por parâmetro e retorna o mês da data.
     * 
     * @param d Data.
     * @return int - Mês da data passada por parâmetro.
     */
    public int splitDataMes(String d) {
        String[] data = d.split("/");
        return Integer.parseInt(data[1]);
    }

    /**
     * Divide a data passada por parâmtro e retorna o ano da data.
     * 
     * @param d Data.
     * @return int - Ano da data passada por parâmetro.
     */
    public int splitDataAno(String d) {
        String[] data = d.split("/");
        return Integer.parseInt(data[2]);
    }

    /**
     * Fornece as datas que contemplam a reserva.
     * 
     * @param dataEntrada Data de entrada.
     * @param diarias     Quantidade de diárias.
     * @return String[] - Vetor com as datas que contemplam aquela reserva.
     */
    public String[] diasReservados(String dataEntrada, int diarias) {
        String aux[] = new String[diarias];
        int b = 0;
        for (int i = 0; i < diarias; i++) {
            if (splitDataMes(dataEntrada) == 1 || splitDataMes(dataEntrada) == 3 || splitDataMes(dataEntrada) == 5
                    || splitDataMes(dataEntrada) == 7 || splitDataMes(dataEntrada) == 8
                    || splitDataMes(dataEntrada) == 10 || splitDataMes(dataEntrada) == 12) {
                if (splitDataMes(dataEntrada) == 12) {
                    if (splitDataDia(dataEntrada) + i <= 31) {
                        int a = splitDataDia(dataEntrada) + i;
                        if (a < 10 && splitDataMes(dataEntrada) < 10)
                            aux[i] = "0" + a + "/" + "0" + splitDataMes(dataEntrada) + "/" + splitDataAno(dataEntrada);
                        else if (a < 10)
                            aux[i] = "0" + a + "/" + splitDataMes(dataEntrada) + "/" + splitDataAno(dataEntrada);
                        else if (splitDataMes(dataEntrada) < 10)
                            aux[i] = +a + "/" + "0" + splitDataMes(dataEntrada) + "/" + splitDataAno(dataEntrada);
                        else
                            aux[i] = +a + "/" + splitDataMes(dataEntrada) + "/" + splitDataAno(dataEntrada);
                    } else {
                        b = diarias - i;
                        if (b < 10)
                            aux[i] = "0" + b + "/" + "01" + "/" + (splitDataAno(dataEntrada) + 1);
                        else
                            aux[i] = +b + "/" + "01" + "/" + (splitDataAno(dataEntrada) + 1);
                    }
                } else {
                    if (splitDataDia(dataEntrada) + i <= 31) {
                        int a = splitDataDia(dataEntrada) + i;
                        if (a < 10 && splitDataMes(dataEntrada) < 10)
                            aux[i] = "0" + a + "/" + "0" + splitDataMes(dataEntrada) + "/" + splitDataAno(dataEntrada);
                        else if (a < 10)
                            aux[i] = "0" + a + "/" + splitDataMes(dataEntrada) + "/" + splitDataAno(dataEntrada);
                        else if (splitDataMes(dataEntrada) < 10)
                            aux[i] = +a + "/" + "0" + splitDataMes(dataEntrada) + "/" + splitDataAno(dataEntrada);
                        else
                            aux[i] = +a + "/" + splitDataMes(dataEntrada) + "/" + splitDataAno(dataEntrada);
                    } else {
                        b = diarias - i;
                        if (b < 10 && (splitDataMes(dataEntrada) + 1) < 10)
                            aux[i] = "0" + b + "/" + "0" + (splitDataMes(dataEntrada) + 1) + "/"
                                    + splitDataAno(dataEntrada);
                        else if (b < 10)
                            aux[i] = "0" + b + "/" + (splitDataMes(dataEntrada) + 1) + "/" + splitDataAno(dataEntrada);
                        else if ((splitDataMes(dataEntrada) + 1) < 10)
                            aux[i] = +b + "/" + "0" + (splitDataMes(dataEntrada) + 1) + "/" + splitDataAno(dataEntrada);
                        else
                            aux[i] = +b + "/" + (splitDataMes(dataEntrada) + 1) + "/" + splitDataAno(dataEntrada);
                    }
                }
            } else if (splitDataMes(dataEntrada) == 4 || splitDataMes(dataEntrada) == 6
                    || splitDataMes(dataEntrada) == 9 || splitDataMes(dataEntrada) == 11) {
                if (splitDataDia(dataEntrada) + i <= 30) {
                    int a = splitDataDia(dataEntrada) + i;
                    if (a < 10 && splitDataMes(dataEntrada) < 10)
                        aux[i] = "0" + a + "/" + "0" + splitDataMes(dataEntrada) + "/" + splitDataAno(dataEntrada);
                    else if (a < 10)
                        aux[i] = "0" + a + "/" + splitDataMes(dataEntrada) + "/" + splitDataAno(dataEntrada);
                    else if (splitDataMes(dataEntrada) < 10)
                        aux[i] = +a + "/" + "0" + splitDataMes(dataEntrada) + "/" + splitDataAno(dataEntrada);
                    else
                        aux[i] = +a + "/" + splitDataMes(dataEntrada) + "/" + splitDataAno(dataEntrada);
                } else {
                    b = diarias - i;
                    if (b < 10 && (splitDataMes(dataEntrada) + 1) < 10)
                        aux[i] = "0" + b + "/" + "0" + (splitDataMes(dataEntrada) + 1) + "/"
                                + splitDataAno(dataEntrada);
                    else if (b < 10)
                        aux[i] = "0" + b + "/" + (splitDataMes(dataEntrada) + 1) + "/" + splitDataAno(dataEntrada);
                    else if ((splitDataMes(dataEntrada) + 1) < 10)
                        aux[i] = +b + "/" + "0" + (splitDataMes(dataEntrada) + 1) + "/" + splitDataAno(dataEntrada);
                    else
                        aux[i] = +b + "/" + (splitDataMes(dataEntrada) + 1) + "/" + splitDataAno(dataEntrada);
                }
            } else {
                if (splitDataDia(dataEntrada) + i <= 28) {
                    int a = splitDataDia(dataEntrada) + i;
                    if (a < 10 && splitDataMes(dataEntrada) < 10)
                        aux[i] = "0" + a + "/" + "0" + splitDataMes(dataEntrada) + "/" + splitDataAno(dataEntrada);
                    else if (a < 10)
                        aux[i] = "0" + a + "/" + splitDataMes(dataEntrada) + "/" + splitDataAno(dataEntrada);
                    else if (splitDataMes(dataEntrada) < 10)
                        aux[i] = +a + "/" + "0" + splitDataMes(dataEntrada) + "/" + splitDataAno(dataEntrada);
                    else
                        aux[i] = +a + "/" + splitDataMes(dataEntrada) + "/" + splitDataAno(dataEntrada);
                } else {
                    b = diarias - i;
                    if (b < 10 && (splitDataMes(dataEntrada) + 1) < 10)
                        aux[i] = "0" + b + "/" + "0" + (splitDataMes(dataEntrada) + 1) + "/"
                                + splitDataAno(dataEntrada);
                    else if (b < 10)
                        aux[i] = "0" + b + "/" + (splitDataMes(dataEntrada) + 1) + "/" + splitDataAno(dataEntrada);
                    else if ((splitDataMes(dataEntrada) + 1) < 10)
                        aux[i] = +b + "/" + "0" + (splitDataMes(dataEntrada) + 1) + "/" + splitDataAno(dataEntrada);
                    else
                        aux[i] = +b + "/" + (splitDataMes(dataEntrada) + 1) + "/" + splitDataAno(dataEntrada);
                }
            }
        }
        return aux;
    }

    /**
     * Fornece as informações de uma reserva.
     * 
     * @return String - Informações completas da reserva.
     */
    @Override
    public String toString() {
        return "IdReserva: " + idReserva + " Data Entrada: " + dataEntrada + " Qtd Diárias: " + qtdDiarias
                + " Qtd Hóspedes: " + qtdHospedes + " Qtd Quartos: " + qtdQuartos + " Tipo de Pagamento: "
                + tipoDePagamento + "\nHóspedes da Reserva:\n" + gerarColecaoHospedes() + "\nQuartos reservados:\n"
                + gerarColecaoQuartos() + "\nValor total da reserva: R$ " + getPrecoTotal();
    }
}
