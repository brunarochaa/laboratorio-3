package filmnow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de testes responsáveis por validar o comportamento dos dados da classe {@link FilmNow}.
 * Verifica se os métodos criados retornam, quando existentes, os valores corretamente.
 *
 * @see FilmNow
 * @author Bruna de Medeiros Rocha
 */
class FilmNowTest {

    private FilmNow fs;

    /**
     * Definição de um objeto FilmNow que será usado para os testes.
     */
    @BeforeEach
    void antesDeTudo() {
        this.fs = new FilmNow();
    }

    /**
     * Testa o método {@link FilmNow#cadastraFilme(int, String, String, String)} para avaliar se um filme é adicionado corretamente ao sistema.
     */
    @Test
    void cadastraFilme() {
        // Cenário: Filme com todos o parâmetros definidos, inclusive posição válida.
        // Resultado esperado: "FILME ADICIONADO".
        assert fs.cadastraFilme(1, "Avatar", "2009", "Disney+").equals("FILME ADICIONADO");
        // Cenário: dois testes com filmes com posições válidas, mas mesmo nome e mesmo ano, em um deles, mesmo local.
        // Resultado esperado: "FILME JÁ ADICIONADO" em ambos os casos.
        fs.cadastraFilme(1, "Avatar", "2009", "Disney+");
        assert fs.cadastraFilme(3, "Avatar", "2009", "Disney+").equals("FILME JÁ ADICIONADO");
        assert fs.cadastraFilme(3, "Avatar", "2009", "Netflix").equals("FILME JÁ ADICIONADO");
        // Cenário: cadastro na última posição válida e em posições inválida (menor que 1 e maior que 100).
        // Resultado esperado: para a última posição "FILME ADICIONADO", para as posições inválidas "POSIÇÃO INVÁLIDA".
        assert fs.cadastraFilme(100, "Coraline", "2009", "PrimeVideo").equals("FILME ADICIONADO");
        assert fs.cadastraFilme(101, "A viagem de Chihiro", "2001", "PrimeVideo").equals("POSIÇÃO INVÁLIDA");
        assert fs.cadastraFilme(0, "Coraline", "2009", "PrimeVideo").equals("POSIÇÃO INVÁLIDA");
        // Cenário: cadastro de filmes, respectivamente, sem local, sem ano e sem nome.
        // Resultado esperado: "FILME ADICIONADO" apenas para o filme sem ano, e "FILME INVÁLIDO" para o resto.
        assert fs.cadastraFilme(1, "O Grinch", "2000", "").equals("FILME INVÁLIDO");
        assert fs.cadastraFilme(1, "O Grinch", "", "Telecine").equals("FILME ADICIONADO");
        assert fs.cadastraFilme(1, "", "2000", "Telecine").equals("FILME INVÁLIDO");
    }

    /**
     * Testa o método {@link FilmNow#detalhaFilme(int)} para avaliar se o retorno corresponde ao detalhamento esperado.
     */
    @Test
    void detalhaFilme() {
        // Cenário: filme não adicionado à hotlist.
        // Resultado esperado: "nome, ano\nlocal", sem emoji indicativo de pertencimento à hotList.
        fs.cadastraFilme(1, "Avatar", "2009", "Disney+");
        assert fs.detalhaFilme(1).equals("Avatar, 2009\nDisney+");
        // Cenário: filme não adiconado à hotlist sem ano de lançamento.
        // Resultado esperado: "nome\nlocal" sem emoji indicativo de pertencimento à hotlist.
        fs.cadastraFilme(2, "20 dias em Mariupol", "", "Cinema");
        assert fs.detalhaFilme(2).equals("20 dias em Mariupol\nCinema");
        // Cenário: chamada de método para um filme não cadastrado (posição vazia ou fora do intervalo).
        // Resultado esperado: "POSIÇÃO INVÁLIDA!".
        assert fs.detalhaFilme(3).equals("POSIÇÃO INVÁLIDA!");
        assert fs.detalhaFilme(0).equals("POSIÇÃO INVÁLIDA!");
        // Cenário: filme com todos os parâmetros adiconado à hotlist.
        // Resultado esperado: ""\uD83D\uDD25 nome, ano\nlocal".
        fs.cadastraFilme(3, "Coraline", "2009", "PrimeVideo");
        fs.adicionaHot(3, 1);
        assert fs.detalhaFilme(3).equals("\uD83D\uDD25 Coraline, 2009\nPrimeVideo");
    }

    /**
     * Testa o método {@link FilmNow#mostrarTodos()} para avaliar se o retorno corresponde à lista de filmes esperada.
     */
    @Test
    void mostrarTodos() {
        // Cenário: nenhum filme cadastrado.
        // Resultado esperado: retorno de String vazia.
        fs.mostrarTodos().equals("");
        // Cenário: três filmes adicionados (todos com os parâmetros completos).
        // Resultado: lista de três linhas com os três filmes, no formato "posicao - nome, ano\n", um filme por linha.
        fs.cadastraFilme(1, "Avatar", "2009", "Disney+");
        fs.cadastraFilme(2, "Coraline", "2009", "PrimeVideo");
        fs.cadastraFilme(3, "O Estranho Mundo de Jack", "1993", "Disney+");

        assert fs.mostrarTodos().equals("1 - Avatar, 2009\n2 - Coraline, 2009\n3 - O Estranho Mundo de Jack, 1993\n");
    }

    /**
     * Testa do método {@link FilmNow#adicionaHot(int, int)} para avaliar se um filme está sendo adiconado à hotlist de forma correta e se seu retorno corresponde ao esperado.
     */
    @Test
    void adicionaHot() {
        // Cenário: cadastro de filme não adicionado à hotlist.
        // Resultado esperado: "ADICIONADO À HOTLIST NA POSIÇÃO posicao".
        fs.cadastraFilme(1, "O Estranho Mundo de Jack", "1993", "Disney+");
        assert fs.adicionaHot(1, 1).equals("ADICIONADO À HOTLIST NA POSIÇÃO 1");
        // Cenário: cadastro de filme já adicionado.
        // Resultado esperado: "FILME JÁ ESTÁ NA HOTLIST!".
        fs.adicionaHot(1, 1);
        assert fs.adicionaHot(1, 2).equals("FILME JÁ ESTÁ NA HOTLIST!");
        // Cenário: no primeiro caso, cadastro de filme com posição inválida numa posição válida da hotlist; no segundo caso, cadastro de filme com posição válida numa posição inválida da hotlist.
        // Resultado esperado: "POSIÇÃO DA HOTLIST INVÁLIDA!" em ambos os casos.
        fs.removeHot(1);
        assert fs.adicionaHot(10, 1).equals("POSIÇÃO DE FILME INVÁLIDA!");
        assert fs.adicionaHot(1, 20).equals("POSIÇÃO DA HOTLIST INVÁLIDA!");
    }

    /**
     * Teste do método {@link FilmNow#exibeHotList()} para avaliar se o retorno é como o esperado.
     */
    @Test
    void exibeHotList() {
        // Cenário: hotlist vazia.
        // Resultado esperado: String vazia.
        fs.exibeHotList().equals("");
        // Cenário: hotlist com dois filmes adicionados.
        // Resultado esperado: lista de duas linhas com os dois filmes no formato "nome, ano", cada filme em uma linha.
        fs.cadastraFilme(1, "Avatar", "2009", "Disney+");
        fs.cadastraFilme(2, "Coraline", "2009", "PrimeVideo");
        fs.adicionaHot(1, 1);
        fs.adicionaHot(2, 2);
        assert fs.exibeHotList().equals("1 - Avatar, 2009\n2 - Coraline, 2009\n");
    }

    /**
     * Teste do método {@link FilmNow#removeHot(int)} para avaliar se o a remoção de filmes está sendo efetivada.
     * Teste realizado com um único filme na lista, quando removido a list fica vazia, fazendo com que o método exibeHotList retorne uma String vazia.
     * O teste do detalhamento não precisa ser realizado dentro desse método, pois dentro do próprio método detalhaFilme() há uma checagem da hotlist.
     */
    @Test
    void removeHot() {
        fs.cadastraFilme(1, "Avatar", "2009", "Disney+");
        fs.adicionaHot(1, 1);
        fs.removeHot(1);
        assert fs.exibeHotList().isEmpty();
    }
}