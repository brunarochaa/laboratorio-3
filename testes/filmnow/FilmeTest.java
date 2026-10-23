package filmnow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilmeTest {

    private Filme f;

    @BeforeEach
        void antesDeTudo() {

        this.f = new Filme("AVATAR", "2007", "Cinema");
    }

    @Test
    void getNome() {

        assert this.f.getNome().equals("AVATAR");
    }

    @Test
    void getAno() {

        assert this.f.getAno().equals("2007");
    }

    @Test
    void getLocal() {

        assert this.f.getLocal().equals("Cinema");
    }
}