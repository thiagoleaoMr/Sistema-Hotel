package br.ufla.gac111.grupo2;

import java.util.Calendar;

/**
 * Classe que implementa os relatórios do hotel.
 * 
 * É a classe que possui os métodos necessários para gerar relatórios referentes
 * às reservas do hotel.
 * 
 * @author LUÍS FILIPE DESTÉFANI PENHA e PETHRUS DOTI MODESTO
 * @version 2023.02.17
 */
public class Relatorios {

    /**
     * Método que gera um relatório mostrando o dia com mais reservas em um
     * determinado mês do ano.
     * Esse método utiliza o mês e o ano que se deseja analisar, passados por
     * parâmetro.
     * 
     * @param mes
     * @param ano
     */
    public void relatorioDiaMaisReservasMes(String mes, String ano) {
        Calendar calendario = Calendar.getInstance();
        calendario.set(Integer.parseInt(ano), Integer.parseInt(mes) - 1, 1);
        int numDiasNoMes = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
        int[] reservas = new int[numDiasNoMes];
        for (int dia = 1; dia <= numDiasNoMes; dia++) {
            int numReservas = getNumReservas(Integer.parseInt(ano), Integer.parseInt(mes), dia);
            reservas[dia - 1] = numReservas;
        }

        int maxIndice = 0;
        for (int i = 1; i < reservas.length; i++) {
            if (reservas[i] > reservas[maxIndice]) {
                maxIndice = i;
            }
        }

        int maxIndice2 = reservas.length;
        for (int i = 0; i < reservas.length - 1; i++) {
            if (reservas[i] > reservas[maxIndice2 - 1]) {
                maxIndice2 = i;
            }
        }

        if (maxIndice == 0 && maxIndice2 == reservas.length) {
            System.out.println("Não há dia com mais reservas para esse mês e ano.");
        } else {
            System.out.println("O dia do mês " + mes + " do ano de " + ano + " com mais reservas é " + (maxIndice + 1));
        }
    }

    /**
     * Método que recebe como parâmetro uma determinada data (xx/xx/xxxx) e retorna
     * o dia.
     * 
     * @param d
     * @return Integer.parseInt(data[0])
     */
    public int splitDataDia(String d) {
        String[] data = d.split("/");
        return Integer.parseInt(data[0]);
    }

    /**
     * Método que recebe como parâmetro uma determinada data (xx/xx/xxxx) e retorna
     * o mês.
     * 
     * @param d
     * @return Integer.parseInt(data[1])
     */
    public int splitDataMes(String d) {
        String[] data = d.split("/");
        return Integer.parseInt(data[1]);
    }

    /**
     * Método que recebe como parâmetro uma determinada data (xx/xx/xxxx) e retorna
     * o ano.
     * 
     * @param d
     * @return Integer.parseInt(data[2])
     */
    public int splitDataAno(String d) {
        String[] data = d.split("/");
        return Integer.parseInt(data[2]);
    }

    /**
     * Método que recebe como parâmetro um dia, mês e ano e retorna a quantidade de
     * reservas existentes nesse dia
     * 
     * @param ano
     * @param mes
     * @param dia
     * @return cont
     */
    public int getNumReservas(int ano, int mes, int dia) {
        int cont = 0;
        for (String s : Sistema.getHotel().getColecaoReservas().keySet()) {
            String[] aux = new String[Sistema.getHotel().getColecaoReservas().get(s).getQtdDiarias()];
            aux = Sistema.getHotel().getColecaoReservas().get(s).diasReservados(
                    Sistema.getHotel().getColecaoReservas().get(s).getDataEntrada(),
                    Sistema.getHotel().getColecaoReservas().get(s).getQtdDiarias());
            for (int i = 0; i < Sistema.getHotel().getColecaoReservas().get(s).getQtdDiarias(); i++) {
                if (splitDataMes(aux[i]) == mes && splitDataAno(aux[i]) == ano) {
                    if (splitDataDia(aux[i]) == dia) {
                        cont++;
                    }
                }
            }
        }
        return cont;
    }

    /**
     * Método que exibe os dados referentes a uma reserva específica.
     * Esse método recebe como parâmetro uma opção, junto com o ID de reserva ou o
     * CPF utilizado no ato da reserva que se deseja pesquisar.
     * 
     * @param opcao
     * @param idReservaouCpf
     */
    public void relatorioReservaEspecifica(int opcao, String idReservaouCpf) {
        if (opcao == 1) {
            for (String i : Sistema.getHotel().getColecaoReservas().keySet()) {
                if (Sistema.getHotel().getColecaoReservas().get(i).getIdReserva().equals(idReservaouCpf)) {
                    System.out.println(Sistema.getHotel().getColecaoReservas().get(i));
                    break;
                } else {
                    System.out.println("Número de reserva inexistente.");
                    break;
                }
            }

        } else if (opcao == 2) {
            int aux = 0;
            for (String ide : Sistema.getHotel().getColecaoReservas().keySet()) {
                for (String s : Sistema.getHotel().getColecaoReservas().get(ide).getColecaoHospedes().keySet()) {
                    if (Sistema.getHotel().getColecaoReservas().get(ide).getColecaoHospedes().get(s)
                            .getHospedeResponsavel() == true) {
                        if (Sistema.getHotel().getColecaoReservas().get(ide).getColecaoHospedes().get(s).getCpf()
                                .equals(idReservaouCpf)) {
                            aux++;
                            System.out.println(Sistema.getHotel().getColecaoReservas().get(ide));

                        }
                    }
                }
            }
            if (aux == 0) {
                System.out.println("CPF não encontrado!");
            }

        } else {
            System.out.println("Opção Inválida");
        }
    }

    /**
     * Método que exibe as reservas existentes no hotel em uma data específica
     * (xx/xx/xxxx).
     * 
     * @param data
     */
    public void relatorioReservasDataEspecifica(String data) {
        for (String ide : Sistema.getHotel().getColecaoReservas().keySet()) {
            if (Sistema.getHotel().getColecaoReservas().get(ide).getDataEntrada().equals(data)) {
                System.out.println(Sistema.getHotel().getColecaoReservas().get(ide));
                System.out.println("------------------------------------------------------");
            }
        }
    }

    /**
     * Método que percorre a lista de reservas e exibe o número de quartos que foram
     * reservados de cada tipo.
     */
    public void relatorioQtdTiposQuartoReservados() {
        int[] tiposQuarto = new int[3];
        for (String ide : Sistema.getHotel().getColecaoReservas().keySet()) {
            for (Integer i : Sistema.getHotel().getColecaoReservas().get(ide).getColecaoQuartosReservados().keySet()) {
                if (Sistema.getHotel().getColecaoReservas().get(ide).getColecaoQuartosReservados()
                        .get(i) instanceof Standard) {
                    tiposQuarto[0]++;
                }
                if (Sistema.getHotel().getColecaoReservas().get(ide).getColecaoQuartosReservados()
                        .get(i) instanceof Master) {
                    tiposQuarto[1]++;
                }
                if (Sistema.getHotel().getColecaoReservas().get(ide).getColecaoQuartosReservados()
                        .get(i) instanceof Deluxe) {
                    tiposQuarto[2]++;
                }
            }
        }
        System.out.println("Quantidade de quartos Standard reservados até o momento: " + tiposQuarto[0]);
        System.out.println("Quantidade de quartos Master reservados até o momento: " + tiposQuarto[1]);
        System.out.println("Quantidade de quartos Deluxe reservados até o momento: " + tiposQuarto[2]);

    }
}
