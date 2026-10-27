package filmnow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Classe de testes responsáveis por validar o comportamento dos dados da classe {@link Filme}.
 * Verifica se os métodos getters retornam os valores corretamente. Importante ressaltar que os testes foram feitos apenas com intuito educativo, uma vez que são métodos triviais.
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
}
