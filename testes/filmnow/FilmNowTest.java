package filmnow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilmNowTest {

    private FilmNow fs;

    @BeforeEach
    void antesDeTudo() {
        this.fs = new FilmNow();
    }

    @Test
    void cadastraFilme() {
        //teste de cadastro bem sucedido
        assert fs.cadastraFilme(1, "Avatar", "2009", "Disney+").equals("FILME ADICIONADO");
        //teste de cadastro de filmes iguais a partir dos parâmetros identificadores
        fs.cadastraFilme(1, "Avatar", "2009", "Disney+");
        assert fs.cadastraFilme(3, "Avatar", "2009", "Disney+").equals("FILME JÁ ADICIONADO");
        assert fs.cadastraFilme(3, "Avatar", "2009", "Netflix").equals("FILME JÁ ADICIONADO");
        //testes de posições limite e inválida
        assert fs.cadastraFilme(100, "Coraline", "2009", "PrimeVideo").equals("FILME ADICIONADO");
        assert fs.cadastraFilme(101, "A viagem de Chihiro", "2001", "PrimeVideo").equals("POSIÇÃO INVÁLIDA");
        assert fs.cadastraFilme(0, "Coraline", "2009", "PrimeVideo").equals("POSIÇÃO INVÁLIDA");
        //testes de cadastro com parâmetro ausente
        assert fs.cadastraFilme(1, "O Grinch", "2000", "").equals("FILME INVÁLIDO");
        assert fs.cadastraFilme(1, "O Grinch", "", "Telecine").equals("FILME ADICIONADO");
        assert fs.cadastraFilme(1, "", "2000", "Telecine").equals("FILME INVÁLIDO");
    }

    @Test
    void detalhaFilme() {
        //teste de detalhamento (sem hot) bem sucedido
        fs.cadastraFilme(1, "Avatar", "2009", "Disney+");
        assert fs.detalhaFilme(1).equals("Avatar, 2009\nDisney+");
        //teste  de detalhamento sem ano de lançamento
        fs.cadastraFilme(2, "20 dias em Mariupol", "", "Cinema");
        assert fs.detalhaFilme(2).equals("20 dias em Mariupol\nCinema");
        //testes de posições inválidas (sem filme cadastrado ou fora do intervalo)
        assert fs.detalhaFilme(3).equals("POSIÇÃO INVÁLIDA!");
        assert fs.detalhaFilme(0).equals("POSIÇÃO INVÁLIDA!");
        //teste de detalhamento de filme na hotList
        fs.cadastraFilme(3, "Coraline", "2009", "PrimeVideo");
        fs.adicionaHot(3, 1);
        assert fs.detalhaFilme(3).equals("\uD83D\uDD25 Coraline, 2009\nPrimeVideo");
    }

    @Test
    void mostrarTodos() {
        //teste com o array vazio
        fs.mostrarTodos().equals("");
        //teste com três filmes já adicionados
        fs.cadastraFilme(1, "Avatar", "2009", "Disney+");
        fs.cadastraFilme(2, "Coraline", "2009", "PrimeVideo");
        fs.cadastraFilme(3, "O Estranho Mundo de Jack", "1993", "Disney+");

        assert fs.mostrarTodos().equals("1 - Avatar, 2009\n2 - Coraline, 2009\n3 - O Estranho Mundo de Jack, 1993\n");
    }

    @Test
    void adicionaHot() {
        //teste de cadastro bem sucedido
        fs.cadastraFilme(1, "O Estranho Mundo de Jack", "1993", "Disney+");
        assert fs.adicionaHot(1, 1).equals("ADICIONADO À HOTLIST NA POSIÇÃO 1");
        //teste de cadastro de filme já adicionado
        fs.adicionaHot(1, 1);
        assert fs.adicionaHot(1, 2).equals("FILME JÁ ESTÁ NA HOTLIST!");
        //testes de posições de filme e de hot inválidas
        fs.removeHot(1);
        assert fs.adicionaHot(10, 1).equals("POSIÇÃO DE FILME INVÁLIDA!");
        assert fs.adicionaHot(1, 20).equals("POSIÇÃO DA HOTLIST INVÁLIDA!");
    }

    @Test
    void exibeHot() {
        //teste de exibição da hotlist vazia
        fs.exibeHotList().equals("");
        //teste de exibição da hotlist com dois filmes adicionados
        fs.cadastraFilme(1, "Avatar", "2009", "Disney+");
        fs.cadastraFilme(2, "Coraline", "2009", "PrimeVideo");
        fs.adicionaHot(1, 1);
        fs.adicionaHot(2, 2);
        assert fs.exibeHotList().equals("1 - Avatar, 2009\n2 - Coraline, 2009\n");
    }

    @Test
    void removeHot() {
        fs.cadastraFilme(1, "Avatar", "2009", "Disney+");
        fs.adicionaHot(1, 1);
        fs.removeHot(1);
        assert fs.exibeHotList().equals("");
    }
}