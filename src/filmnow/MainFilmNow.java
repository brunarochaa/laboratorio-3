package filmnow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

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

    private static void mostrarFilmes(FilmNow fn) {

        System.out.println(fn.mostrarTodos());
    }

    private static void detalharFilme(FilmNow fn, Scanner scanner) {

        System.out.print("\nQual filme> ");
        int posicao = scanner.nextInt();
        Filme filme = fn.getFilme(posicao);
        System.out.println("Dados do filme:");
        System.out.println(fn.detalhaFilme(posicao));
    }

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

    private static void sai() {
        System.out.println("\nVlw flw o/");
        System.exit(0);
    }

    private static void carregaFilmes(String arquivoFilmes, FilmNow fn) throws FileNotFoundException, IOException {
        LeitorFilmNow leitor = new LeitorFilmNow();

        int carregados =  leitor.carregaContatos(arquivoFilmes, fn);
        System.out.println("Carregamos " + carregados + " registros.");
    }

    private static void exibirHot(FilmNow fn) {

        System.out.println(fn.exibeHotList());
    }

    private static void atribuirHot(FilmNow fn, Scanner scanner) {
        System.out.print("\nFilme> ");
        int posicaoFilme = scanner.nextInt();
        System.out.print("\nPosicao> ");
        int posicaoHot = scanner.nextInt();
        System.out.println(fn.adicionaHot(posicaoFilme, posicaoHot));
    }

    public static void removerHot(FilmNow fn, Scanner scanner) {
        System.out.println("Posicao> ");
        int posicaoHot = scanner.nextInt();
        fn.removeHot(posicaoHot);
    }
}