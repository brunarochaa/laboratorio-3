package filmnow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Classe responsável pela interface do usuário.
 */
public class MainFilmNow {

    public static void main(String[] args) {
        FilmNow fn = new FilmNow();

        System.out.println("Carregando filmes ...");
        try {
            carregaFilmes("filmes_inicial.csv", fn);
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro lendo arquivo: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        String escolha = "";
        while (true) {
            escolha = menu(scanner);
            comando(escolha, fn, scanner);
        }

    }

    /**
     * Menu exibido ao usuário que informa quais ações podem ser executadas e captura sua escolha.
     * @param scanner Para ler a opção escolhida pelo usuário.
     * @return O comando escolhido.
     */
    private static String menu(Scanner scanner) {
        System.out.println(
                "\n---\nMENU\n" +
                        "(A)Adicionar filme\n" +
                        "(M)Mostrar todos\n" +
                        "(D)Detalhar filme\n" +
                        "(E) Exibir HotList\n" +
                        "(H) Atribuit Hot\n" +
                        "(R) Remover Hot\n" +
                        "(S)air\n" +
                        "\n" +
                        "Opção> ");
        return scanner.next().toUpperCase();
    }

    /**
     * Interpreta a opção escolhida pelo usuário.
     * @param opcao Opção digitada.
     * @param fn O sistema FilmNow que está sendo manipulado.
     * @param scanner Objeto para o caso do comando precisar de mais input.
     */
    private static void comando(String opcao, FilmNow fn, Scanner scanner) {
        switch (opcao) {
            case "A":
                adicionaFilme(fn, scanner);
                break;
            case "M":
                mostrarFilmes(fn);
                break;
            case "D":
                detalharFilme(fn, scanner);
                break;
            case "E":
                exibirHot(fn);
                break;
            case "H":
                atribuirHot(fn, scanner);
                break;
            case "R":
                removerHot(fn, scanner);
                break;
            case "S":
                sai();
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    /**
     * Imprime todos os filmes adicionados no formato "Nome, ano".
     * @param fn O sistema FilmNow que está sendo manipulado.
     */

    private static void mostrarFilmes(FilmNow fn) {

        System.out.println(fn.mostrarTodos());
    }

    /**
     * Imprime os detalhes de um filme específico.
     * @param fn O sitema FilmNow que está sendo manipulado.
     * @param scanner Objeto para leitura da posição do filme a ser detalhado.
     */

    private static void detalharFilme(FilmNow fn, Scanner scanner) {

        System.out.print("\nQual filme> ");
        int posicao = scanner.nextInt();
        Filme filme = fn.getFilme(posicao);
        System.out.println("Dados do filme:");
        System.out.println(fn.detalhaFilme(posicao));
    }

    /**
     * Cadastra um filme no sistema a partir de parâmetros informados pelo usuário.
     * @param fn O sistema FilmNow que está sendo manipulado.
     * @param scanner Objeto utilizado para ler os dados fonecidos pelo usuário.
     */
    private static void adicionaFilme(FilmNow fn, Scanner scanner) {
        System.out.print("\nPosição no sistema> ");
        int posicao = scanner.nextInt();
        scanner.nextLine();
        System.out.print("\nNome> ");
        String nome = scanner.nextLine();
        System.out.print("\nAno> ");
        String ano = scanner.nextLine();
        System.out.print("\nLocal> ");
        String local = scanner.nextLine();
        System.out.println(fn.cadastraFilme(posicao, nome, ano, local));
    }

    /**
     * Encerra o programa.
     */
    private static void sai() {
        System.out.println("\nVlw flw o/");
        System.exit(0);
    }

    /**
     * Lê carga de filmes de um arquivo csv.
     * @param arquivoFilmes O caminho para o arquivo.
     * @param fn O sistema FilmNow que está sendo manipulado.
     * @throws FileNotFoundException Caso o arquivo não seja encontrado.
     * @throws IOException Caso ocorra algum erro de leitura do arquivo.
     */
    private static void carregaFilmes(String arquivoFilmes, FilmNow fn) throws FileNotFoundException, IOException {
        LeitorFilmNow leitor = new LeitorFilmNow();

        int carregados =  leitor.carregaContatos(arquivoFilmes, fn);
        System.out.println("Carregamos " + carregados + " registros.");
    }

    /**
     * Exibe todos os filmes que estão na hotlist no formato "Nome, Ano".
     * @param fn O sistema FilmNow que está sendo manipulado.
     */
    private static void exibirHot(FilmNow fn) {

        System.out.println(fn.exibeHotList());
    }

    /**
     * Define um filme como hot a partir de sua posição, o adicionando à hotlist em uma posição específica.
     * @param fn O sistema FilmNow que está sendo manipulado.
     * @param scanner Objeto utilizado para ler os dados fornecidos pelo usuário.
     */
    private static void atribuirHot(FilmNow fn, Scanner scanner) {
        System.out.print("\nFilme> ");
        int posicaoFilme = scanner.nextInt();
        System.out.print("\nPosicao> ");
        int posicaoHot = scanner.nextInt();
        System.out.println(fn.adicionaHot(posicaoFilme, posicaoHot));
    }

    /**
     * Remove um filme da hotlist.
     * @param fn O sistema FilmNow que está sendo manipulado.
     * @param scanner Objeto utilizado para ler os dados fornecidos pelo usuário.
     */
    public static void removerHot(FilmNow fn, Scanner scanner) {
        System.out.println("Posicao> ");
        int posicaoHot = scanner.nextInt();
        fn.removeHot(posicaoHot);
    }
}