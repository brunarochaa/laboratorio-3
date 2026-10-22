package filmnow;

import java.util.Objects;

public class Filme {

    private final String nome;
    private final String ano;
    private final String local;

    public Filme(String nome, String ano, String local) {
        this.nome = nome;
        this.ano = ano;
        this.local = local;
    }

    public String getNome() {

        return this.nome;
    }

    public String getAno() {

        return this.ano;
    }

    public String getLocal() {

        return this.local;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Filme filme = (Filme) o;

        return nome.equals(filme.nome) && ano.equals(filme.ano);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nome, ano);
    }

    @Override
    public String toString() {

        return this.nome + ", "  + this.ano;
    }
}