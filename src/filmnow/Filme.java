package filmnow;

import java.util.Objects;

/**
 * Repesenta um filme com nome, ano de lançamento e local associado ao filme.
 * @author Bruna de Medeiros Rocha
 */

public class Filme {

    private final String nome;
    private final String ano;
    private final String local;

    /**
     * Constrói um objeto Filme.
     * @param nome Nome do filme.
     * @param ano Ano de lançamento do filme.
     * @param local Local associado ao filme.
     */
    public Filme(String nome, String ano, String local) {
        this.nome = nome;
        this.ano = ano;
        this.local = local;
    }

    /**
     * Possibilita acesso ao nome do filme.
     * @return Nome do filme.
     */
    public String getNome() {

        return this.nome;
    }

    /**
     * Possibilita acesso ao ano de lançamento do filme.
     * @return Ano de lançamento do filme.
     */
    public String getAno() {

        return this.ano;
    }

    /**
     * Possibilita acesso ao local associado ao filme.
     * @return O local associado ao filme.
     */
    public String getLocal() {

        return this.local;
    }

    /**
     * Permite comparar filmes a partir do seu nome e do seu ano de lançmamento com um outro objeto.
     * @param o  O objeto a ser comparado.
     * @return true se forem iguais; false se não forem.
     */
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

    /**
     * Calcula o código hash do filme a partir dos atributos identificadores.
     * @return O valor do código hash do filme.
     */
    @Override
    public int hashCode() {

        return Objects.hash(nome, ano);
    }

    /**
     * Retorna uma representação textual do filme.
     * @return String do objeto Filme.
     */
    @Override
    public String toString() {

        if (!this.ano.isEmpty()) {
            return this.nome + ", " + this.ano;
        }
        return this.nome;
    }
}