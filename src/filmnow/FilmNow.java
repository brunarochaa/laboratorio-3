package filmnow;

/**
 * Sistema que mantém os seus filmes prediletos. Podem existir 100 filmes.
 *
 * @author Bruna de Medeiros Rocha
 *
 */
public class FilmNow {

    private static final int TAMANHO = 100;
    private static final int TAMANHOHOT = 10;
    private Filme[] filmes;
    private Filme[] hotList;

    /**
     * Constrói um objeto FilmNow com capacidade de 100 filmes e uma hotlist de até de 10 filmes.
     */
    public FilmNow() {
        this.filmes = new Filme[TAMANHO];
        this.hotList = new Filme[TAMANHOHOT];
    }

    /**
     * Possibilita acesso ao array de filmes.
     * @return Uma cópia do array.
     */
    public Filme[] getFilmes() {

        return this.filmes.clone();
    }

    /**
     * Possibilita o acesso a um filme específico.
     * @param posicao Posição do filme no Array.
     * @return O filme.
     */
    public Filme getFilme(int posicao) {

        return this.filmes[posicao - 1];
    }

    /**
     * Cria um objeto filme e o adiciona ao array de filmes.
     * @param posicao Posição em que o filme será adicionado.
     * @param nome Nome do filme.
     * @param ano Ano de lançamento do filme.
     * @param local Local em que o filme foi assistido.
     * @return String que atualiza o usuário se a operação foi bem sucedida ou não.
     */
    public String cadastraFilme(int posicao, String nome, String ano, String local) {
        if (posicao < 1 || posicao > this.filmes.length) {
            return "POSIÇÃO INVÁLIDA";
        }

        if (nome == null || nome.trim().isEmpty() || local == null || local.trim().isEmpty()) {
            return "FILME INVÁLIDO";
        }

        Filme novoFilme = new Filme(nome, ano, local);

        for (int i = 0; i < this.filmes.length; i++) {
            if (this.filmes[i] != null && this.filmes[i].equals(novoFilme)) {
                return "FILME JÁ ADICIONADO";
            }
        }

        this.filmes[posicao - 1] = novoFilme;
        return "FILME ADICIONADO";
    }

    /**
     * Elenca os filmes adicionados.
     * @return String com a lista de filmes.
     */

    public String mostrarTodos() {
        String resultado = "";

        for (int i = 0; i < this.filmes.length; i++) {
            if (this.filmes[i] != null) {
                resultado += (i + 1) + " - " + this.filmes[i].toString() + "\n";
            }
        }
        return resultado;
    }

    /**
     * Detalha um filme.
     * @param posicao posição do filme a ser detalhado.
     * @return String com as informações do filme ou, em caso de operação mal sucedida, com o que está impossibilitando a execução.
     */
    public String detalhaFilme(int posicao) {
        if (posicao < 1 || posicao > 100) {
            return "POSIÇÃO INVÁLIDA!";
        }

        Filme filme = this.filmes[posicao - 1];
        if (filme == null) {
            return "POSIÇÃO INVÁLIDA!";
        }

        boolean isHot = false;
        for (int i = 0; i < this.hotList.length; i++) {
            if (this.hotList[i] != null && this.hotList[i].equals(filme)) {
                isHot = true;
                break;
            }
        }

        if (isHot== true) {
            return "\uD83D\uDD25" + " " + filme.toString() + "\n" + filme.getLocal();
        }

        return filme.toString() + "\n" + filme.getLocal();
        }

    /**
     * Adiciona um filme à hotlist.
     * @param posicaoFilme Posição do filme no array de filmes.
     * @param posicaoHot Posição em que o filme será adiconado na hotlist.
     * @return String que atualiza o usuário se a operação foi bem sucedida ou não.
     */

    public String adicionaHot(int posicaoFilme, int posicaoHot) {
        if (posicaoFilme < 1 || posicaoFilme >= filmes.length || this.filmes[posicaoFilme - 1] == null) {
            return "POSIÇÃO DE FILME INVÁLIDA!";
        }

        if (posicaoHot < 1 || posicaoHot > this.hotList.length) {
            return "POSIÇÃO DA HOTLIST INVÁLIDA!";
        }

        Filme filmeAAdicionar = this.filmes[posicaoFilme - 1];

        for (int i = 0; i < this.hotList.length; i++) {
            if (this.hotList[i] != null && this.hotList[i].equals(filmeAAdicionar)) {
                return "FILME JÁ ESTÁ NA HOTLIST!";
            }
        }

        this.hotList[posicaoHot - 1] = filmeAAdicionar;
        return "ADICIONADO À HOTLIST NA POSIÇÃO " + (posicaoHot);
    }

    /**
     * Remove um filme da hotlist.
     * @param posicaoHot Posição do filme na hotlist.
     */

    public String removeHot(int posicaoHot) {

        if (posicaoHot < 1 || posicaoHot > 10) {
            return "POSIÇÃO INVÁLIDA!";
        }
        this.hotList[posicaoHot - 1] = null;
        return "FILME REMOVIDO DA HOTLIST!";
    }

    /**
     * Elenca os filmes da hotlist.
     * @return String com a lista de filmes.
     */
    public String exibeHotList() {
        String resultadoHot = "";

        for (int i = 0; i < this.hotList.length; i++) {
            if (hotList[i] != null) {
                resultadoHot += (i + 1) + " - " + hotList[i].getNome() + ", " + hotList[i].getAno() + "\n";
            }
        }

        return resultadoHot;
    }
}