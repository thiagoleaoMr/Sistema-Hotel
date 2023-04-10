package br.ufla.gac111.grupo2;

import java.sql.Date;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Classe Sistema - responsável por gerenciar o hotel e interagir com o usuário.
 *
 * Esta classe eh parte da aplicacao "Sistema de gerenciamento de hotel".
 * "Sistema de gerenciamento de hotel" eh um sistema que controla e administra
 * um hotel.
 *
 * Sistema é caracterizado por apresentar menus de interação com o usuário,
 * requisições de respostas, relacionamento com as classes Hotel, Reserva,
 * Arquivos e Relatórios.
 * 
 * @author LUÍS FILIPE DESTÉFANI PENHA, PETHRUS DOTI MODESTO, NATHAN HENRIQUE
 *         RIBEIRO DE ASSIS e THIAGO LEÃO MARRA
 * @version 2023.02.20
 */
public class Sistema {

    private static Scanner entrada;
    private static Hotel hotel;
    private static Reserva r;
    private static Arquivo arq = new Arquivo();
    private static Relatorios rel = new Relatorios();
    private static boolean aux = false;

    /**
     * Main: Loop que apresenta o menu inicial e espera uma opção do usuário.
     * 
     * @param args
     */
    public static void main(String[] args) {
        entrada = new Scanner(System.in);
        int opcao = 0;
        while (opcao != 3) {
            menuInicial();
            opcao = entrada.nextInt();
            entrada.nextLine();
            executarOpcaoInicial(opcao);
        }
    }

    /**
     * Imprime o menu inicial.
     */
    public static void menuInicial() {
        System.out.println("\n\nSEJA BEM VINDO AO HOTEL PENALUTHI\n");
        System.out.println("Como você deseja acessar o sistema?");
        System.out.println("1 - Modo Usuário");
        System.out.println("2 - Modo Administrador");
        System.out.println("3 - Sair");
        System.out.printf("Digite sua opção: ");
    }

    /**
     * Executa a opção passada por parâmetro do menu inicial.
     * 
     * @param opcao Opção referente às apresentadas no menu inicial.
     */
    public static void executarOpcaoInicial(int opcao) {
        switch (opcao) {
            case 1:
                menuUsuario();
                break;
            case 2:
                menuAdministrador2();
                break;
            case 3:
                break;
            default:
                System.out.println("Opção Inválida");

        }
    }

    /**
     * Retorna o objeto Hotel referenciado pela variável hotel.
     * 
     * @return Hotel - Variável hotel.
     */
    public static Hotel getHotel() {
        return hotel;
    }

    /**
     * Imprime o menu usuário e solicita uma opção.
     */
    public static void menuUsuario() {
        System.out.println("\n\n____________ MODO USUÁRIO ____________");
        System.out.println("\nOlá, Usuário!");
        System.out.println("O que você deseja fazer?");
        System.out.println("1 - Conheça o Hotel");
        System.out.println("2 - Realizar reserva");
        System.out.println("3 - Cancelar reserva");
        System.out.println("4 - Voltar");
        System.out.printf("Digite sua opção: ");
        int opcao = entrada.nextInt();
        executarOpcaoUsuario(opcao);
    }

    /**
     * Executa a opção passada por parâmetro do menu usuário.
     * 
     * @param opcao Opção referente às apresentadas no menu usuário.
     */
    public static void executarOpcaoUsuario(int opcao) {
        switch (opcao) {
            case 1:
                conhecerHotel();
                break;
            case 2:
                cadastrarReserva();
                break;
            case 3:
                int a = 0;
                removerReserva(a);
                break;
            case 4:
                break;
            default:
                System.out.println("Opção Inválida");
                menuUsuario();
        }
    }

    /**
     * Imprime uma descrição do hotel.
     */
    public static void conhecerHotel() {
        System.out.println(
                "\nO Hotel Penaluthi tem 13 anos de existência, é situado na cidade de Lavras-MG e é referência em toda a região devido ao seu conforto e hospitalidade.");
        System.out.println(
                "Está situado no Centro da cidade na Praça Dr. Augusto Silva, próximo aos bancos, restaurantes e comércios.");
        System.out.println("Venha se hospedar conosco e aproveitar o melhor em hotelaria do sul de Minas!");
        menuUsuario();
    }

    /**
     * Solicita e retorna a data de entrada passada pelo usuário.
     * 
     * @return String - A data de entrada.
     */
    public static String lerDataEntrada() {
        System.out.println("Entre com a data de entrada (xx/xx/xxxx)");
        entrada.nextLine();
        String dataEntrada = entrada.nextLine();
        return dataEntrada;
    }

    /**
     * Solicita e retorna a quatidade de diárias passada pelo usuário.
     * 
     * @return int - A quantidade de diárias.
     */
    public static int lerQtdDiarias() {
        System.out.println("Entre com a quantidade de diárias:");
        return entrada.nextInt();
    }

    /**
     * Solicita e retorna a quatidade de hóspedes passada pelo usuário.
     * 
     * @return int - A quantidade de hóspedes.
     */
    public static int lerQtdHospedes() {
        System.out.println("Entre com a quantidade de Hóspedes:");
        return entrada.nextInt();
    }

    /**
     * Solicita e retorna a quatidade de quartos passada pelo usuário.
     * 
     * @return int - A quantidade de quartos.
     */
    public static int lerQtdQuartos() {
        System.out.println("Entre com a quantidade de quartos desejados:");
        int qtdQuartos = entrada.nextInt();
        entrada.nextLine();
        return qtdQuartos;
    }

    /**
     * Solicita e retorna o tipo de pagamento passado pelo usuário.
     * 
     * @return String - O tipo de pagamento.
     */
    public static String lerTipoDePagamento() {
        System.out.println("Entre com o tipo de pagamento (Dinheiro/Crédito/Débito/PIX):");
        return entrada.nextLine();
    }

    /**
     * Cadastra (instancia) uma nova reserva analisando se já existe um hotel
     * cadastrado.
     */
    public static void cadastrarReserva() {
        if (Hotel.getInstanciaUnica() == null) {
            System.out.println("Não é possível cadastrar Reservas pois o administrador não cadastrou Hotel e quartos.");
        } else {
            r = null;
            System.out
                    .println(
                            "\nAtenção: A diária deve ser respeitada com check-in ao meio-dia e check-out às 10 horas.");
            System.out.println("\nInserir informações da Reserva:");
            r = new Reserva(lerDataEntrada(), lerQtdDiarias(), lerQtdHospedes(),
                    lerQtdQuartos(), lerTipoDePagamento());
            conferirReserva();
        }
        menuUsuario();
    }

    /**
     * Confere se há quartos disponíveis para determinada reserva.
     */
    public static void conferirReserva() {
        String aux[] = r.diasReservados(r.getDataEntrada(), r.getQtdDiarias());
        if (r.getQtdQuartos() > hotel.gerarListaQuartosLivres(aux, r.getQtdDiarias()).size()) {
            System.out.println("Não há quartos suficientes para sua reserva nos dias de estadia escolhidos.");
        } else {
            cadastrarHospede();
            exibirQuartos();
            escolherQuartos(r.getQtdQuartos());
        }
    }

    /**
     * Solicita e retorna o nome do hóspede passada pelo usuário.
     * 
     * @return String - O nome do hóspede.
     */
    public static String lerNome() {
        System.out.println("Entre com o nome do Hóspede:");
        return entrada.nextLine();
    }

    /**
     * Solicita e retorna o CPF do hóspede passada pelo usuário.
     * 
     * @return String - O CPF do hóspede.
     */
    public static String lerCpf() {
        System.out.println("Entre com o CPF do Hóspede (xxx.xxx.xxx-xx):");
        return entrada.nextLine();
    }

    /**
     * Solicita e retorna o RG do hóspede passada pelo usuário.
     * 
     * @return String - O RG do hóspede.
     */
    public static String lerRg() {
        System.out.println("Entre com o RG do Hóspede:");
        return entrada.nextLine();
    }

    /**
     * Solicita e retorna o telefone do hóspede passada pelo usuário.
     * 
     * @return String - O telefone do hóspede.
     */
    public static String lerTelefone() {
        System.out.println("Entre com o telefone do Hóspede:");
        return entrada.nextLine();
    }

    /**
     * Solicita e retorna o email do hóspede passada pelo usuário.
     * 
     * @return String - O email do hóspede.
     */
    public static String lerEmail() {
        System.out.println("Entre com o email do Hóspede:");
        return entrada.nextLine();
    }

    /**
     * Solicita e retorna a data de nascimento do hóspede passada pelo usuário.
     * 
     * @return String - A data de nascimento do hóspede.
     */
    public static String lerDataNascimento() {
        System.out.println("Entre com a data de nascimento do Hóspede (xx/xx/xxxx):");
        return entrada.nextLine();
    }

    /**
     * Cadastra hóspedes de uma determinada reserva. Se for o primeiro hóspede será
     * considerado como o responsável.
     */
    public static void cadastrarHospede() {
        System.out.println("\nInserir informações dos hóspedes:");
        System.out.println("----------------------------------------------------------------------------");
        for (int i = 0; i < r.getQtdHospedes(); i++) {
            if (i == 0) {
                System.out.println("Inserir informações do hóspede responsável:");
            } else if (i == 1) {
                System.out.println("Inserir informações dos hóspedes dependentes:");
            }
            r.cadastrarHospede(lerNome(), lerCpf(), lerRg(), lerTelefone(),
                    lerEmail(), lerDataNascimento());
            System.out.println("----------------------------------------------------------------------------");
        }
    }

    /**
     * Imprime os quartos disponiveis para determinada reserva de um usuário.
     */
    public static void exibirQuartos() {
        String aux[] = r.diasReservados(r.getDataEntrada(), r.getQtdDiarias());
        System.out.println("Aqui estão os quartos disponiveis:");
        System.out.println("----------------------------------------------------------------------------");
        for (Integer n : hotel.gerarListaQuartosLivres(aux, r.getQtdDiarias()).keySet()) {
            System.out.println(hotel.gerarListaQuartosLivres(aux, r.getQtdDiarias()).get(n));
            System.out.println("----------------------------------------------------------------------------");
        }
    }

    /**
     * Espera o usuário entrar com os quartos a serem reservados e confere se são
     * válidos.
     */
    public static void escolherQuartos(int qtdQuartos) {
        int aux = 0;
        int[] aux2 = new int[qtdQuartos];
        System.out.println("O usuário escolheu reservar " + qtdQuartos + " quarto/quartos.");
        for (int i = 0; i < qtdQuartos; i++) {
            System.out.println("Digite o número do " + (i + 1) + "° quarto a ser reservado:");
            int numero = entrada.nextInt();
            for (Integer num : hotel.getColecaoQuartos().keySet()) {
                if (numero == hotel.getColecaoQuartos().get(num).getNumeroQuarto()) {
                    aux2[aux] = numero;
                    aux++;
                }
            }
        }
        if (aux == qtdQuartos) {
            for (int i = 0; i < qtdQuartos; i++) {
                r.adicionarColecaoQuartosReservados(hotel.getColecaoQuartos().get(aux2[i]).getNumeroQuarto(),
                        hotel.getColecaoQuartos().get(aux2[i]));
                hotel.adicionarReserva(r.getIdReserva(), r);
            }
            System.out.println("\nQuartos reservados com sucesso!\n");
            System.out.println("----------------------------------------------------------------------------");
            System.out.println(
                    "Informações necessárias concluídas. Reserva realizada! Seu número de Reserva é "
                            + r.getIdReserva());
            System.out.println("O valor total com a taxa de serviço incluida a ser pago por " + r.getTipoDePagamento()
                    + " é R$ " + r.getPrecoTotal());
            System.out.println("----------------------------------------------------------------------------");
        } else {
            System.out.println("Número de quarto digitado inexistente. Reserva não concluída!");
        }
    }

    /**
     * Retorna a data atual.
     * 
     * @return String - Data atual.
     */
    private static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(System.currentTimeMillis());
        return dateFormat.format(date);
    }

    /**
     * Confere se os requisitos mínimos para a remoção de uma reserva são
     * satisfatórios, tais como exixtir um hotel e a coleção de reservas não estar
     * vazia. Pode ser possivel remover pelo número da reserva ou pelo CPF do
     * hóspede responsável. O administrador pode remover qualquer reserva e o
     * usuário é impossibilitado de remover reservas no mesmo dia da entrada.
     * 
     */
    public static void removerReserva(int a) {
        if (Hotel.getInstanciaUnica() == null) {
            System.out.print(
                    "Não é possível remover/cancelar Reservas pois o administrador não cadastrou Hotel e quartos.");
        } else {
            if (hotel.getColecaoReservas().isEmpty()) {
                System.out.print("Não há reservas para serem removidas/canceladas.");
            } else {
                System.out.print(
                        "Deseja cancelar/remover pelo:\n1-Número de Reserva;\n2-CPF do hóspede Responsável.\nDigite uma opção: ");
                int n = entrada.nextInt();
                entrada.nextLine();
                if (n == 1) {
                    System.out.print("Insira o número de Reserva: ");
                    String id = entrada.nextLine();
                    String id2 = "";
                    for (String i : hotel.getColecaoReservas().keySet()) {
                        if (hotel.getColecaoReservas().get(i).getIdReserva().equals(id)) {
                            id2 = id;
                            if (a == 1234) {
                                hotel.removerReserva(id2);
                                System.out.println("Reserva cancelada/removida!");
                                break;
                            } else {
                                if (getDateTime().equals(hotel.getColecaoReservas().get(id2).getDataEntrada())) {
                                    System.out.print(
                                            "Não foi possível cancelar/remover sua Reserva, pedido de cancelamento muito próximo do dia de entrada.");
                                    break;
                                } else {
                                    hotel.removerReserva(id2);
                                    System.out.println("Reserva cancelada/removida!");
                                    break;
                                }
                            }
                        } else {
                            System.out.println("Número de reserva inexistente.");
                            break;
                        }
                    }

                } else if (n == 2) {
                    System.out.print("Insira o CPF do hóspede Responsável: ");
                    String id = entrada.nextLine();
                    String chave = null;
                    ;
                    int aux = 0;
                    for (String ide : hotel.getColecaoReservas().keySet()) {
                        for (String s : hotel.getColecaoReservas().get(ide).getColecaoHospedes().keySet()) {
                            if (hotel.getColecaoReservas().get(ide).getColecaoHospedes().get(s)
                                    .getHospedeResponsavel() == true) {
                                if (hotel.getColecaoReservas().get(ide).getColecaoHospedes().get(s).getCpf()
                                        .equals(id)) {
                                    if (a == 1234) {
                                        chave = hotel.getColecaoReservas().get(ide).getIdReserva();
                                        System.out.println("Reserva cancelada/removida!");
                                        aux++;
                                        break;
                                    } else {
                                        if (getDateTime()
                                                .equals(hotel.getColecaoReservas().get(ide).getDataEntrada())) {
                                            System.out.print(
                                                    "Não foi possível cancelar/remover sua Reserva, pedido de cancelamento muito próximo do dia de entrada.");
                                            aux++;
                                            break;
                                        } else {
                                            chave = hotel.getColecaoReservas().get(ide).getIdReserva();
                                            System.out.println("Reserva cancelada/removida!");
                                            aux++;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (aux == 0)
                        System.out.println("CPF não encontrado!");
                    else
                        hotel.removerReserva(chave);
                } else {
                    System.out.println("Opção Inválida");
                }
            }
        }
        if (a == 1234)
            menuAdministrador();
        else
            menuUsuario();
    }

    /**
     * Requisita e retorna a senha inserida pelo usuário para acesso do menu
     * admnistrador.
     * 
     * @return int - Senha.
     */
    public static int requisitarSenha() {
        int senha;

        System.out.printf("Digite a senha: ");
        senha = entrada.nextInt();
        entrada.nextLine();
        return senha;
    }

    /**
     * Compara a senha inserida com a cadastrada.
     * 
     * @return boolean - true: senha correta.
     */
    public static boolean controleSenha() {
        if (requisitarSenha() == 9872) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Controle das tentativas de acesso ao menu administrador.
     */
    public static void menuAdministrador2() {
        String nome;
        int cont = 0;
        boolean aux = false;

        System.out.printf("\nPara acessar o Modo Administrador, são necessários seu nome e a senha de acesso.");
        System.out.printf("\nDigite seu nome: ");
        nome = entrada.nextLine();
        if (controleSenha() == true) {
            System.out.printf("Administrador " + nome + " logado com sucesso!");
            menuAdministrador();
        } else {
            while (cont < 3) {
                System.out.println("Senha incorreta! Tente novamente.");
                if (controleSenha() == true) {
                    System.out.printf("Administrador " + nome + " logado com sucesso!");
                    menuAdministrador();
                    aux = true;
                    cont = 3;
                }
                cont++;
            }
            if (aux == false)
                System.out.println("Quantidade máxima de tentativas atingida.");
        }
    }

    /**
     * Imprime o menu administrador e solicita uma opção.
     */
    public static void menuAdministrador() {
        int opcao = 0;

        System.out.println("\n\n_____ MODO ADMINISTRADOR _____");
        System.out.println("\nOlá, Administrador!");
        System.out.println("O que você deseja fazer?");
        System.out.println("1 - Cadastrar Hotel");
        System.out.println("2 - Cadastrar Quartos");
        System.out.println("3 - Remover Quarto");
        System.out.println("4 - Remover Reserva");
        System.out.println("5 - Gerar lista de Quartos");
        System.out.println("6 - Gerar lista de Reservas");
        System.out.println("7 - Menu Arquivos (Salvar/Carregar)");
        System.out.println("8 - Menu Relatórios");
        System.out.println("9 - Voltar");
        System.out.printf("Digite sua opção: ");
        opcao = entrada.nextInt();
        entrada.nextLine();

        executarOpcaoAdministrador(opcao);
    }

    /**
     * Executa a opção passada por parâmetro do menu administrador.
     * 
     * @param opcao Opção referente às apresentadas no menu administrador.
     */
    public static void executarOpcaoAdministrador(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarHotel();
                break;
            case 2:
                cadastrarQuartos();
                break;
            case 3:
                removerQuarto();
                break;
            case 4:
                int a = 1234;
                removerReserva(a);
                break;
            case 5:
                if (hotel == null)
                    System.out.println("Não há quartos cadastrados.");
                else if (hotel.getColecaoQuartos().isEmpty())
                    System.out.println("A lista está vazia.");
                else
                    hotel.getColecaoQuartosString();
                menuAdministrador();
                break;
            case 6:
                if (hotel == null)
                    System.out.println("Não há reservas cadastradas.");
                else if (hotel.getColecaoReservas().isEmpty())
                    System.out.println("A lista está vazia.");
                else
                    hotel.getColecaoReservasString();
                menuAdministrador();
                break;
            case 7:
                menuSalvarCarregarArq();
                break;
            case 8:
                if (hotel == null || hotel.getColecaoReservas().isEmpty()) {
                    System.out.println("Não há reservas cadastradas.");
                    menuAdministrador();
                } else
                    menuRelatorios();
                break;
            case 9:
                break;
            default:
                System.out.println("Opção Inválida");
                menuAdministrador();
        }
    }

    /**
     * Cadastra (instancia) um hotel analisando se já existe um hotel
     * cadastrado de outra maneira. Arquivo de hotel é gerado.
     */
    public static void cadastrarHotel() {
        if (Hotel.getInstanciaUnica() == null) {
            System.out.println("Entre com a quantidade de andares:");
            int andares = entrada.nextInt();
            System.out.println("Entre com a quantidade de quartos Standard:");
            int qtdQuartosStandard = entrada.nextInt();
            System.out.println("Entre com a quantidade de quartos Master:");
            int qtdQuartosMaster = entrada.nextInt();
            System.out.println("Entre com a quantidade de quartos Deluxe:");
            int getQtdQuartosDeluxe = entrada.nextInt();
            hotel = Hotel.getInstance(andares, qtdQuartosStandard, qtdQuartosMaster, getQtdQuartosDeluxe);
            System.out.println("Hotel cadastrado com sucesso!");
            arq.salvarDadosHotel(hotel);
        } else
            System.out.println("Hotel já cadastrado!");
        menuAdministrador();
    }

    /**
     * Solicita e retorna o andar do quarto passado pelo administrador.
     * 
     * @return int - O andar do quarto.
     */
    public static int lerAndar() {
        System.out.println("Entre com o andar do quarto:");
        int i = entrada.nextInt();
        if (i <= hotel.getQtdAndares())
            return i;
        else {
            System.out.println("Andar não encontrado! O hotel cadastrado tem " + hotel.getQtdAndares()
                    + " andar/andares. Digite novamente:");
            return entrada.nextInt();
        }
    }

    /**
     * Solicita e retorna o número do quarto passado pelo administrador.
     * 
     * @return int - O número do quarto.
     */
    public static int lerNumeroQuarto() {
        System.out.println("Entre com o número do quarto:");
        return entrada.nextInt();
    }

    /**
     * Solicita e retorna o tipo da cama do quarto passado pelo administrador.
     * 
     * @return int - O tipo da cama do quarto.
     */
    public static String lerTipoCama() {
        entrada.nextLine();
        System.out.println("Entre com o tipo de cama (Single/Twin/Casal):");
        return entrada.nextLine();
    }

    /**
     * Solicita e retorna se o quarto possui ou não TV pelo administrador.
     * 
     * @return boolean - true: tem TV.
     */
    public static boolean lerTemTV() {
        System.out.println("Tem TV (True/False):");
        return entrada.nextBoolean();
    }

    /**
     * Solicita e retorna se o quarto possui ou não Vista Privilegiada pelo
     * administrador.
     * 
     * @return boolean - true: tem Vista Privilegiada.
     */
    public static boolean lerTemVP() {
        System.out.println("Tem Vista Privilegiada (True/False):");
        return entrada.nextBoolean();
    }

    /**
     * Solicita e retorna se o quarto possui ou não Varanda pelo administrador.
     * 
     * @return boolean - true: tem Varanda.
     */
    public static boolean lerTemVA() {
        System.out.println("Tem Varanda (True/False):");
        return entrada.nextBoolean();
    }

    /**
     * Cadastra os quartos de um hotel analisando se já existe um hotel. Caso os
     * quartos já tenham sido cadastrados, uma opção para cadastro individual é
     * liberada. Arquivo de quartos é gerado.
     */
    public static void cadastrarQuartos() {
        if (Hotel.getInstanciaUnica() != null && aux == false) {
            hotel = Hotel.getInstanciaUnica();
            System.out.println("Para os quartos Standard:");
            for (int i = 0; i < hotel.getQtdQuartosStandard(); i++) {
                hotel.cadastrarQuarto(lerTemTV(), lerAndar(), lerNumeroQuarto(), lerTipoCama());
                System.out.println("----------------------------------------------------------");
            }
            System.out.println("Para os quartos Master:");
            for (int i = 0; i < hotel.getQtdQuartosMaster(); i++) {
                hotel.cadastrarQuarto(lerAndar(), lerTemVP(), lerNumeroQuarto(), lerTipoCama());
                System.out.println("----------------------------------------------------------");
            }
            System.out.println("Para os quartos Deluxe:");
            for (int i = 0; i < hotel.getQtdQuartosDeluxe(); i++) {
                hotel.cadastrarQuarto(lerAndar(), lerNumeroQuarto(), lerTemVA(), lerTipoCama());
                System.out.println("----------------------------------------------------------");
            }
            System.out.println("Quartos cadastrados com sucesso!");
            arq.salvarDadosQuartos(hotel);
            aux = true;

        } else if (Hotel.getInstanciaUnica() == null) {
            System.out.println(
                    "Hotel não cadastrado até o momento! Para cadastrar quartos é necessário o cadastro do hotel.");
        } else if (aux == true) {
            System.out.print("Deseja cadastrar um quarto do tipo:\n1-Standard\n2-Master\n3-Deluxe\nDigite uma opção: ");
            cadastrarQuartoSolo();
        }
        menuAdministrador();
    }

    /**
     * Cadastra o quarto de forma individual, atualiza o arquivo de quartos e hotel.
     */
    public static void cadastrarQuartoSolo() {
        int op = entrada.nextInt();
        switch (op) {
            case 1:
                hotel.cadastrarQuarto(lerTemTV(), lerAndar(), lerNumeroQuarto(), lerTipoCama());
                hotel.addQtdQuartosStandard();
                System.out.println("Quarto cadastrado com sucesso!");
                arq.salvarDadosHotel(hotel);
                arq.salvarDadosQuartos(hotel);
                break;
            case 2:
                hotel.cadastrarQuarto(lerAndar(), lerTemVP(), lerNumeroQuarto(), lerTipoCama());
                hotel.addQtdQuartosMaster();
                System.out.println("Quarto cadastrado com sucesso!");
                arq.salvarDadosHotel(hotel);
                arq.salvarDadosQuartos(hotel);
                break;
            case 3:
                hotel.cadastrarQuarto(lerAndar(), lerNumeroQuarto(), lerTemVA(), lerTipoCama());
                hotel.addQtdQuartosDeluxe();
                System.out.println("Quarto cadastrado com sucesso!");
                arq.salvarDadosHotel(hotel);
                arq.salvarDadosQuartos(hotel);
                break;
            default:
                System.out.println("Opção Inválida");
        }
    }

    /**
     * Solicita e retorna o número do quarto a ser removido.
     * 
     * @return int - O número do quarto.
     */
    public static int lerNumtoRmv() {
        System.out.println("Entre com o número do quarto a ser removido");
        int num = entrada.nextInt();
        int num2 = 0;
        for (Integer n : hotel.getColecaoQuartos().keySet()) {
            if (hotel.getColecaoQuartos().get(n).getNumeroQuarto() == num) {
                num2 = num;
            }
        }
        return num2;
    }

    /**
     * Verifica se existem quartos e um hotel cadastrados. Remove o quarto e
     * atualiza o arquivo de quartos e hotel.
     */
    public static void removerQuarto() {
        if (Hotel.getInstanciaUnica() == null) {
            System.out.println("Não é possível remover Quarto pois o administrador não cadastrou Hotel e quartos.");
        } else {
            hotel = Hotel.getInstanciaUnica();
            if (hotel.getColecaoQuartos().isEmpty()) {
                System.out.println("Não há quartos para serem removidos.");
            } else {
                int num = lerNumtoRmv();
                if (num == 0) {
                    System.out.println("Não há quarto com esse número.");
                } else {
                    hotel.removerQuarto(num);
                    System.out.println("Quarto removido com sucesso!");
                    if (hotel.getColecaoQuartos().get(num) instanceof Standard)
                        hotel.subQtdQuartosStandard();
                    else if (hotel.getColecaoQuartos().get(num) instanceof Master)
                        hotel.subQtdQuartosMaster();
                    else
                        hotel.subQtdQuartosDeluxe();
                    arq.salvarDadosQuartos(hotel);
                    arq.salvarDadosHotel(hotel);
                }
            }
        }
        menuAdministrador();
    }

    /**
     * Imprime o menu Arquivos e solicita uma opção.
     */
    public static void menuSalvarCarregarArq() {
        int opcao = 0;

        System.out.println("\n\n_____ MENU ARQUIVOS _____");
        System.out.println("\nOlá, Administrador!");
        System.out.println("O que você deseja fazer?");
        System.out.println("1 - Carregar Hotel Cadastrado");
        System.out.println("2 - Carregar Quartos Cadastrados");
        System.out.println("3 - Salvar Sistema Completo");
        System.out.println("4 - Carregar Sistema Completo");
        System.out.println("5 - Voltar");
        System.out.printf("Digite sua opção: ");
        opcao = entrada.nextInt();
        entrada.nextLine();

        executarOpcaoArquivos(opcao);
    }

    /**
     * Executa a opção passada por parâmetro do menu Arquivos.
     * 
     * @param opcao Opção referente às apresentadas no menu Arquivos.
     */
    public static void executarOpcaoArquivos(int opcao) {
        switch (opcao) {
            case 1:
                carregarHotelCadastrado();
                break;
            case 2:
                if (hotel == null)
                    System.out.println("Hotel não foi cadastrado ou carregado.");
                else if (!hotel.getColecaoQuartos().isEmpty())
                    System.out.println("Quartos já cadastrados de outra forma.");
                else
                    arq.carregarDadosQuartos(lerNomeArq(), hotel);
                menuSalvarCarregarArq();
                break;
            case 3:
                Arquivo.salvarEmArquivoSistema(hotel, "DadosSistema.bin");
                menuSalvarCarregarArq();
                break;
            case 4:
                hotel = Arquivo.carregarEmArquivoSistema(lerNomeArq());
                Hotel.setInstanciaUnica(hotel);
                Reserva.setCont(hotel.getColecaoReservas().size());
                menuSalvarCarregarArq();
                break;
            case 5:
                menuAdministrador();
                break;
            default:
                System.out.println("Opção Inválida");
                menuAdministrador();
        }
    }

    /**
     * Solicita e retorna o nome do arquivo.
     * 
     * @return String - Nome do arquivo.
     */
    public static String lerNomeArq() {
        System.out.println("Digite o nome do arquivo: ");
        return entrada.nextLine();
    }

    /**
     * Verificar se um hotel já foi cadastrado, em caso negativo cadastrar o hotel
     * carregando através do arquivo.
     */
    public static void carregarHotelCadastrado() {
        if (Hotel.getInstanciaUnica() == null) {
            arq.carregarDadosHotel(lerNomeArq());
            hotel = Hotel.getInstanciaUnica();
        } else {
            System.out.println("Hotel já foi cadastrado de forma interativa.");
        }
        menuSalvarCarregarArq();
    }

    /**
     * Imprime o menu Relatórios e solicita uma opção.
     */
    public static void menuRelatorios() {
        int opcao = 0;

        System.out.println("\n\n_____ MENU RELATÓRIOS _____");
        System.out.println("\nOlá, Administrador!");
        System.out.println("O que você deseja fazer?");
        System.out.println("1 - Relatório do dia com mais reservas de determinado mês/ano");
        System.out.println("2 - Relátorio de uma Reserva específica");
        System.out.println("3 - Relatório das reservas de uma data específica");
        System.out.println("4 - Relatório da quantidade de quartos reservados de cada categoria");
        System.out.println("5 - Voltar");
        System.out.printf("Digite sua opção: ");
        opcao = entrada.nextInt();
        entrada.nextLine();

        executarOpcaoRelatorios(opcao);
    }

    /**
     * Solicita e retorna o mês que deseja verificar.
     * 
     * @return String - Mês.
     */
    public static String lerMesRel1() {
        System.out.print(
                "Insira o mês que deseja verificar: ");
        return entrada.nextLine();
    }

    /**
     * Solicita e retorna o ano que deseja verificar.
     * 
     * @return String - Ano.
     */
    public static String lerAnoRel1() {
        System.out.print(
                "Para qual ano: ");
        return entrada.nextLine();
    }

    /**
     * Solicita e retorna a opção correspondente.
     * 
     * @return int - Opção.
     */
    public static int lerOpcaoRel2() {
        System.out.print(
                "Deseja buscar pelo:\n1-Número de Reserva;\n2-CPF do hóspede Responsável.\nDigite uma opção: ");
        int aux = entrada.nextInt();
        entrada.nextLine();
        return aux;
    }

    /**
     * Solicita e retorna o CPF do hóspede responsável ou o número da reserva que
     * deseja verificar.
     * 
     * @return String - CPF do hóspede responsável ou o número da reserva.
     */
    public static String lerIdOuCpfRel2() {
        System.out.print(
                "Insira o CPF do hóspede Responsável ou número da reserva: ");
        return entrada.nextLine();
    }

    /**
     * Solicita e retorna a data para verificar.
     * 
     * @return String - Data.
     */
    public static String lerDataRel3() {
        System.out.print(
                "Insira uma data para obter as reservas iniciadas neste dia: ");
        return entrada.nextLine();
    }

    /**
     * Executa a opção passada por parâmetro do menu Relatórios.
     * 
     * @param opcao Opção referente às apresentadas no menu Relatórios.
     */
    public static void executarOpcaoRelatorios(int opcao) {
        switch (opcao) {
            case 1:
                rel.relatorioDiaMaisReservasMes(lerMesRel1(), lerAnoRel1());
                menuRelatorios();
                break;
            case 2:
                rel.relatorioReservaEspecifica(lerOpcaoRel2(), lerIdOuCpfRel2());
                menuRelatorios();
                break;
            case 3:
                rel.relatorioReservasDataEspecifica(lerDataRel3());
                menuRelatorios();
                break;
            case 4:
                rel.relatorioQtdTiposQuartoReservados();
                menuRelatorios();
                break;
            case 5:
                menuAdministrador();
                break;
            default:
                System.out.println("Opção Inválida");
                menuAdministrador();
        }
    }
}