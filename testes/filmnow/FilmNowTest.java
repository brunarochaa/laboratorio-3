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

        assert fs.cadastraFilme(1, "Avatar", "2009", "Disney+").equals("FILME ADICIONADO");
        fs.cadastraFilme(1, "Avatar", "2009", "Disney+");
        assert fs.cadastraFilme(3, "Avatar", "2009", "Disney+").equals("FILME JÁ ADICIONADO");
        assert fs.cadastraFilme(3, "Avatar", "2009", "Netflix").equals("FILME JÁ ADICIONADO");
    }
}