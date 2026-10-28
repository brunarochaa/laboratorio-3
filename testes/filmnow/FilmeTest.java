package filmnow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe de testes responsáveis por validar o comportamento dos dados da classe {@link Filme}.
 * Verifica se os métodos getters retornam os valores corretamente. Importante ressaltar que alguns testes foram feitos apenas com intuito educativo, uma vez que são métodos triviais.
 *
 * @see Filme
 * @author Bruna de Medeiros Rocha
 */
class FilmeTest {

    private Filme f;

    /**
     * Definição inicial de um objeto Filme que será usado para teste.
     */

    @BeforeEach
        void antesDeTudo() {

        this.f = new Filme("AVATAR", "2007", "Cinema");
    }

    /**
     * Testa o método {@link Filme#getNome()} para avaliar se o nome do Filme é retornado corretamente.
     *
     * Cenário: Filme com o nome "AVATAR".
     * Resultado esperado: "AVATAR".
     */
    @Test
    void getNome() {

        assert this.f.getNome().equals("AVATAR");
    }

    /**
     * Testa o método {@link Filme#getAno()} para avaliar se o ano do Filme é retornado corretamente.
     *
     * Cenário: Filme lançado em 2007.
     * Resultado esperado: "2007".
     */
    @Test
    void getAno() {

        assert this.f.getAno().equals("2007");
    }

    /**
     * Testa o método {@link Filme#getAno()} para avaliar se o local do Filme é retornado corretamente.
     *
     * Cenário: Filme em "Cinema".
     * Resultado esperado: "Cinema".
     */
    @Test
    void getLocais() {
        assertArrayEquals(
                new String[]{"Cinema", null, null, null, null},
                this.f.getLocais());


    }

    /**
     * Testa o método {@link Filme#contaLocais()} para avaliar se o número de locais é retornado corretamente.
     */
    @Test
    void contaLocais() {
        // Cenário: Conta o número inicial de locais.
        // Resultado esperado: 1.
        assert f.contaLocais() == 1;
        // Cenário: Conta o número de locais após um local ser adicionado.
        // Resultado esperado: 2.
        FilmNow fs = new FilmNow();
        fs.cadastraFilme(1, "Coraline", "2009", "Netflix");
        fs.adicionaLocal(1, "PrimeVideo");
        assert fs.getFilme(1).contaLocais() == 2;
    }

    /**
     * Testa o método {@link Filme#toString()} para avaliar se a representação textual é retornada como esperado.
     *
     * Cenário: Filme com nome "AVATAR" e ano "2009".
     * Resultado esperado: "AVATAR, 2009".
     */
    @Test
    void testToString() {
        assert this.f.toString().equals("AVATAR, 2007");
    }

    /**
     * Testa se o construtor {@link Filme} lança corretamente as exceções implementadas.
     */
    @Test
    void Filme() {
        // Cenário: o nome ou o local é uma String vazia.
        // Resultado esperado: o programa deve lançar "IllegalArgumentException".
        assertThrows(IllegalArgumentException.class, () -> {
            new Filme("", "2026", "Cinema");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Filme("Coraline", "2009", "");
        });
        // Cenário: qualquer um dos parâmetros é nula.
        // Resultado esperado: o programa deve lançar "NullPointerException".
        assertThrows(NullPointerException.class, () -> {
            new Filme(null, "2000", "Prime Video");
        });
        assertThrows(NullPointerException.class, () -> {
            new Filme("Coraline", null, "Disney+");
        });
        assertThrows(NullPointerException.class, () -> {
            new Filme("Coraline", "2009", null);
        });
        assertThrows(NullPointerException.class, () -> {
            new Filme(null, null, null);
        });
    }
}

