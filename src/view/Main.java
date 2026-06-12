package view;

import model.AnaliseIA;
import model.Cliente;
import model.Insight;
import model.ProdutoTotvs;
import model.Reuniao;
import model.Transcricao;
import model.UsuarioSistema;
import service.AnaliseService;
import service.InsightService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner SC = new Scanner(System.in);
    private static final List<Cliente> CLIENTES = new ArrayList<>();
    private static final List<Reuniao> REUNIOES = new ArrayList<>();
    private static final List<ProdutoTotvs> CATALOGO_PRODUTOS = new ArrayList<>();
    private static final AnaliseService ANALISE_SERVICE = new AnaliseService();
    private static final InsightService INSIGHT_SERVICE = new InsightService();

    private static int proximoIdCliente = 1;
    private static int proximoIdReuniao = 1;
    private static int proximoIdTranscricao = 1;

    public static void main(String[] args) {
        UsuarioSistema responsavel = new UsuarioSistema(1, "Ana Souza", "Gestor Comercial");
        responsavel.exibirDados();

        inicializarCatalogoProdutos();

        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> cadastrarReuniao(responsavel);
                case 3 -> registrarTranscricao();
                case 4 -> executarAnalise();
                case 5 -> gerarInsights();
                case 6 -> consultarHistorico();
                case 0 -> System.out.println("Encerrando o TOTVS Meeting Intelligence...");
                default -> System.out.println("Opcao invalida. Tente novamente.");
            }
        } while (opcao != 0);

        SC.close();
    }

    private static void exibirMenu() {
        System.out.println();
        System.out.println("===== TOTVS MEETING INTELLIGENCE =====");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Cadastrar Reuniao");
        System.out.println("3 - Registrar Transcricao");
        System.out.println("4 - Executar Analise (IA)");
        System.out.println("5 - Gerar Insights");
        System.out.println("6 - Consultar Historico do Cliente");
        System.out.println("0 - Sair");
    }

    // Funcionalidade 1 - Registrar Reuniao (cadastro de cliente, base do fluxo)
    private static void cadastrarCliente() {
        System.out.println("\n--- Cadastro de Cliente ---");
        String nome = lerTexto("Nome do cliente: ");
        String segmento = lerTexto("Segmento (ex: Varejo, Industria, Servicos): ");
        String porte = lerTexto("Porte (Pequeno, Medio, Grande): ");

        Cliente cliente = new Cliente(proximoIdCliente++, nome, segmento, porte);
        CLIENTES.add(cliente);

        System.out.println("Cliente cadastrado com sucesso!");
        cliente.exibirDados();
    }

    // Funcionalidade 1 - Registrar Reuniao
    private static void cadastrarReuniao(UsuarioSistema responsavel) {
        System.out.println("\n--- Cadastro de Reuniao ---");
        Cliente cliente = selecionarCliente();
        if (cliente == null) {
            return;
        }

        String titulo = lerTexto("Titulo da reuniao: ");
        String data = lerTexto("Data (dd/mm/aaaa): ");

        Reuniao reuniao = new Reuniao(proximoIdReuniao++, data, titulo, responsavel.getNome(), cliente);
        reuniao.cadastrarReuniao();

        cliente.adicionarReuniao(reuniao);
        REUNIOES.add(reuniao);
    }

    // Funcionalidade 2 - Registrar Transcricao
    private static void registrarTranscricao() {
        System.out.println("\n--- Registro de Transcricao ---");
        Reuniao reuniao = selecionarReuniao();
        if (reuniao == null) {
            return;
        }

        System.out.println("Digite a transcricao da reuniao (uma linha):");
        String texto = lerTexto("> ");
        String idioma = lerTexto("Idioma (ex: pt-BR): ");

        Transcricao transcricao = new Transcricao(proximoIdTranscricao++, texto, idioma);
        transcricao.cadastrarTranscricao();

        reuniao.setTranscricao(transcricao);
    }

    // Funcionalidade 3 - Executar Analise
    private static void executarAnalise() {
        System.out.println("\n--- Executar Analise (IA) ---");
        Reuniao reuniao = selecionarReuniao();
        if (reuniao == null) {
            return;
        }

        try {
            AnaliseIA analise = ANALISE_SERVICE.executarAnalise(reuniao);
            System.out.println("Analise gerada com sucesso!");
            analise.exibirResultado();
        } catch (IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // Funcionalidade 4 - Gerar Insights
    private static void gerarInsights() {
        System.out.println("\n--- Geracao de Insights ---");
        Reuniao reuniao = selecionarReuniao();
        if (reuniao == null) {
            return;
        }

        if (reuniao.getAnalises().isEmpty()) {
            System.out.println("Esta reuniao ainda nao possui nenhuma analise. Execute a opcao 4 primeiro.");
            return;
        }

        AnaliseIA ultimaAnalise = reuniao.getAnalises().get(reuniao.getAnalises().size() - 1);
        List<Insight> insights = INSIGHT_SERVICE.gerarInsights(ultimaAnalise, reuniao.getTranscricao(), CATALOGO_PRODUTOS);

        System.out.println("\nResumo dos insights gerados:");
        for (Insight insight : insights) {
            insight.exibirInsight();
        }
    }

    // Funcionalidade 5 - Consultar Historico
    private static void consultarHistorico() {
        System.out.println("\n--- Historico do Cliente ---");
        Cliente cliente = selecionarCliente();
        if (cliente == null) {
            return;
        }

        cliente.exibirDados();

        if (cliente.getReunioes().isEmpty()) {
            System.out.println("Este cliente ainda nao possui reunioes cadastradas.");
            return;
        }

        for (Reuniao reuniao : cliente.getReunioes()) {
            System.out.println();
            reuniao.exibirReuniao();

            if (reuniao.getTranscricao() != null) {
                reuniao.getTranscricao().exibirTranscricao();
            }

            for (AnaliseIA analise : reuniao.getAnalises()) {
                analise.exibirResultado();
            }
        }
    }

    private static Cliente selecionarCliente() {
        if (CLIENTES.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado. Cadastre um cliente primeiro (opcao 1).");
            return null;
        }

        System.out.println("Clientes cadastrados:");
        for (Cliente cliente : CLIENTES) {
            System.out.println(cliente.getIdCliente() + " - " + cliente.getNome());
        }

        int id = lerInteiro("Informe o ID do cliente: ");
        for (Cliente cliente : CLIENTES) {
            if (cliente.getIdCliente() == id) {
                return cliente;
            }
        }

        System.out.println("Cliente nao encontrado.");
        return null;
    }

    private static Reuniao selecionarReuniao() {
        if (REUNIOES.isEmpty()) {
            System.out.println("Nenhuma reuniao cadastrada. Cadastre uma reuniao primeiro (opcao 2).");
            return null;
        }

        System.out.println("Reunioes cadastradas:");
        for (Reuniao reuniao : REUNIOES) {
            System.out.println(reuniao.getIdReuniao() + " - " + reuniao.getTitulo()
                    + " (Cliente: " + reuniao.getCliente().getNome() + ")");
        }

        int id = lerInteiro("Informe o ID da reuniao: ");
        for (Reuniao reuniao : REUNIOES) {
            if (reuniao.getIdReuniao() == id) {
                return reuniao;
            }
        }

        System.out.println("Reuniao nao encontrada.");
        return null;
    }

    private static void inicializarCatalogoProdutos() {
        CATALOGO_PRODUTOS.add(new ProdutoTotvs(1, "Protheus", "ERP"));
        CATALOGO_PRODUTOS.add(new ProdutoTotvs(2, "RM", "RH/Folha de Pagamento"));
        CATALOGO_PRODUTOS.add(new ProdutoTotvs(3, "Fluig", "Gestao de Processos"));
        CATALOGO_PRODUTOS.add(new ProdutoTotvs(4, "Datasul", "ERP"));
        CATALOGO_PRODUTOS.add(new ProdutoTotvs(5, "Winthor", "ERP Varejo"));

        System.out.println("\nCatalogo de produtos TOTVS carregado:");
        for (ProdutoTotvs produto : CATALOGO_PRODUTOS) {
            produto.exibirProduto();
        }
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return SC.nextLine().trim();
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = SC.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido. Digite um numero inteiro.");
            }
        }
    }
}
