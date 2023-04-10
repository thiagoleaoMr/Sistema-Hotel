package br.ufla.gac111.grupo2;

import java.io.*;

/**
 * Classe que implementa os arquivos do hotel.
 * 
 * É a classe que possui os métodos necessários para salvar e carregar os dados
 * do hotel, dos quartos e das reservas.
 * 
 * @author THIAGO LEÃO MARRA e PETHRUS DOTI MODESTO
 * @version 2023.02.17
 */
public class Arquivo {

    /**
     * Método que salva os dados do hotel em um arquivo de texto, chamado de
     * DadosHotel.txt.
     * Esse método utiliza o hotel como parâmetro e salva no arquivo o número de
     * andares do hotel e a quantidade de cada tipo de quartos que ele possui.
     * Ao ser salvo, as informações do hotel no arquivo texto são separadas por ",".
     * 
     * @param h
     */
    public void salvarDadosHotel(Hotel h) {
        try (FileWriter arquivo = new FileWriter("DadosHotel.txt")) {
            arquivo.write(h.getQtdAndares() + "," + h.getQtdQuartosStandard() + "," + h.getQtdQuartosMaster() + ","
                    + h.getQtdQuartosDeluxe());
            System.out.println("Arquivo salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Falha ao salvar o arquivo.");
        }
    }

    /**
     * Método que carrega os dados do hotel que esteja salvo em um arquivo de texto.
     * Esse método utiliza o nome do arquivo que deseja ser carregado, passado por
     * parâmetro.
     * Ao ler o arquivo texto, as informações são separadas por "," e seus valores
     * são atribuidos ao método getInstance, da classe Hotel.
     * 
     * @param nomeArquivo
     */
    public void carregarDadosHotel(String nomeArquivo) {
        try (BufferedReader arquivo = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha = arquivo.readLine();

            while (linha != null) {
                String[] campos = linha.split(",");

                Hotel.getInstance(Integer.parseInt(campos[0]), Integer.parseInt(campos[1]), Integer.parseInt(campos[2]),
                        Integer.parseInt(campos[3]));
                linha = arquivo.readLine();
            }
            System.out.println("Hotel carregado com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("Impossivel abrir o arquivo!");
        } catch (IOException e) {
            System.out.println("Problema na leitura do arquivo!");
        }
    }

    /**
     * Método que salva os dados dos quartos em um arquivo de texto, chamado de
     * DadosQuartos.txt.
     * Esse método utiliza o hotel como parâmetro e salva no arquivo a coleção de
     * quartos existentes no hotel e as características de cada um.
     * Ao ser salvo, as informações dos quartos no arquivo texto são separadas por
     * ",".
     * 
     * @param h
     */
    public void salvarDadosQuartos(Hotel h) {
        try (FileWriter arquivo = new FileWriter("DadosQuartos.txt")) {
            for (Integer num : h.getColecaoQuartos().keySet()) {
                if (h.getColecaoQuartos().get(num) instanceof Standard) {
                    Standard s = (Standard) h.getColecaoQuartos().get(num);
                    arquivo.write(s.getTemTV() + "," + s.getAndarQuarto() + "," + s.getNumeroQuarto() + ","
                            + s.getTipoCama() + "\n");
                }
                if (h.getColecaoQuartos().get(num) instanceof Master) {
                    Master m = (Master) h.getColecaoQuartos().get(num);
                    arquivo.write(m.getAndarQuarto() + "," + m.getTemVp() + "," + m.getNumeroQuarto() + ","
                            + m.getTipoCama() + "\n");
                }
                if (h.getColecaoQuartos().get(num) instanceof Deluxe) {
                    Deluxe d = (Deluxe) h.getColecaoQuartos().get(num);
                    arquivo.write(d.getAndarQuarto() + "," + d.getNumeroQuarto() + "," + d.getTemVaranda() + ","
                            + d.getTipoCama() + "\n");
                }

            }
            System.out.println("Arquivo salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Falha ao salvar o arquivo.");
        }
    }

    /**
     * Método que carrega os dados dos quartos que estejam salvos em um arquivo de
     * texto.
     * Esse método utiliza o nome do arquivo e o hotel instanciado, passados por
     * parâmetro.
     * Ao ler o arquivo texto, as informações são separadas por "," e seus valores
     * são atribuidos ao método construtor do quarto a que se referem.
     * 
     * @param nomeArquivo
     * @param hotel
     */
    public void carregarDadosQuartos(String nomeArquivo, Hotel hotel) {
        try (BufferedReader arquivo = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha = arquivo.readLine();
            Quarto quarto = null;
            while (linha != null) {
                String[] campos = linha.split(",");
                if (campos[0].equals("false") || campos[0].equals("true")) {
                    quarto = new Standard(Boolean.parseBoolean(campos[0]), Integer.parseInt(campos[1]),
                            Integer.parseInt(campos[2]), campos[3]);
                    hotel.adicionarQuarto(Integer.parseInt(campos[2]), quarto);
                }
                if (campos[1].equals("false") || campos[1].equals("true")) {
                    quarto = new Master(Integer.parseInt(campos[0]), Boolean.parseBoolean(campos[1]),
                            Integer.parseInt(campos[2]), campos[3]);
                    hotel.adicionarQuarto(Integer.parseInt(campos[2]), quarto);
                }
                if (campos[2].equals("false") || campos[2].equals("true")) {
                    quarto = new Deluxe(Integer.parseInt(campos[0]), Integer.parseInt(campos[1]),
                            Boolean.parseBoolean(campos[2]), campos[3]);
                    hotel.adicionarQuarto(Integer.parseInt(campos[1]), quarto);
                }
                linha = arquivo.readLine();
            }
            System.out.println("Quartos carregados com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("Impossivel abrir o arquivo!");
        } catch (IOException e) {
            System.out.println("Problema na leitura do arquivo!");
        }
    }

    /**
     * Método que salva todos os dados do hotel em um arquivo binário.
     * Esse método utiliza o hotel e o nome do arquivo binário de destino, passados
     * por parâmetro.
     * 
     * @param h
     * @param arquivoDestino
     */
    public static void salvarEmArquivoSistema(Hotel h, String arquivoDestino) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivoDestino))) {
            oos.writeObject(h);
            System.out.println("Arquivo salvo com sucesso!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método que carrega todos os dados do hotel que estejam salvos em um arquivo
     * binário.
     * Esse método utiliza o nome do arquivo binário a ser carregado, passado como
     * parâmetro, e retorna os dados referentes ao hotel.
     * 
     * @param arquivoOrigem
     * @return h
     */
    public static Hotel carregarEmArquivoSistema(String arquivoOrigem) {
        Hotel h = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoOrigem))) {
            h = (Hotel) ois.readObject();
            System.out.println("Sistema carregado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return h;
    }
}