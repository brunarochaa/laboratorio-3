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
        // verifica se a posição é válida
        if (posicao < 1 || posicao > this.filmes.length) {
            return "POSIÇÃO INVÁLIDA!";
        }
    // verifica se já existe um filme igual em filmes
        Filme novoFilme = new Filme(nome, ano, local);

        for (int i = 0; i < this.filmes.length; i++) {
            if (this.filmes[i] != null && this.filmes[i].equals(novoFilme)) {
                return "FILME JÁ ADICIONADO!";
            }
        }

        this.filmes[posicao - 1] = novoFilme;
        return "FILME ADICIONADO!";
    }

    /**
     * Adiciona um novo local associado a um filme.
     * @param posicaoFilme Posição do filme.
     * @param local Novo local.
     * @return Status da operação.
     */
    public String adicionaLocal(int posicaoFilme, String local) {
        // verifica se a posição é válida
        if (posicaoFilme < 1 || posicaoFilme > this.filmes.length || this.filmes[posicaoFilme - 1] == null) {
            return "POSIÇÃO DE FILME INVÁLIDA!";
        }
        // verifica se ainda há espaço disponível para um local
        Filme filme = this.filmes[posicaoFilme - 1];

        if (filme.contaLocais() == 5) {
            return "LIMITE DE LOCAIS ATINGIDO!";
        }
        // verifica se o local já foi adicionado antes
        for (int i = 0; i < filme.getLocais().length; i++) {
            String localAtual = filme.getLocais()[i];
            if (localAtual != null && localAtual.equalsIgnoreCase(local)) {
                return "LOCAL JÁ ADICIONADO!";
            }
        }
        int posicao = filme.contaLocais();

        filme.getLocais()[posicao] = local;
        return "LOCAL ADICIONADO!";
    }

    /**
     * Remove um local adicionado posteriormente ao cadastro do filme.
     * @param posicaoFilme Posição do filme.
     * @param posicaoLocal Posição do local.
     * @return Status da operação.
     */
    public String removeLocal(int posicaoFilme, int posicaoLocal) {
        // verifica se a posição do filme é válida (dentro do intervalo e não nula)
        if (posicaoFilme < 1 || posicaoFilme > this.filmes.length || this.filmes[posicaoFilme - 1] == null) {
            return "POSIÇÃO DE FILME INVÁLIDA!";
        }

        Filme filme = this.filmes[posicaoFilme - 1];
        // verifica se a posição do local é válida (dentro do intervalo que permite ser removido - de 2 a 5)
        if (posicaoLocal < 2 || posicaoLocal >= filme.getLocais().length) {
            return "POSIÇÃO DE LOCAL INVÁLIDA!";
        }

        filme.getLocais()[posicaoLocal - 1] = null;
        return "LOCAL REMOVIDO!";
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
        // verifica se a posição é válida (dentro do intervalo e não nulo)
        if (posicao < 1 || posicao > 100 || this.filmes[posicao - 1] == null) {
            return "POSIÇÃO INVÁLIDA!";
        }
        Filme filme = this.filmes[posicao - 1];

        // verifica se o filme está na hotlist
        boolean isHot = false;
        for (int i = 0; i < this.hotList.length; i++) {
            if (this.hotList[i] != null && this.hotList[i].equals(filme)) {
                isHot = true;
                break;
            }
        }

        // define o detalhamento para os dois casos: o filme está ou não na hotlist
        if (isHot== true) {
            return "\uD83D\uDD25" + " " + filme.toString() + "\n" + filme.exibeLocais();
        }

        return filme.toString() + "\n" + filme.exibeLocais();
        }

    /**
     * Adiciona um filme à hotlist.
     * @param posicaoFilme Posição do filme no array de filmes.
     * @param posicaoHot Posição em que o filme será adiconado na hotlist.
     * @return String que atualiza o usuário se a operação foi bem sucedida ou não.
     */

    public String adicionaHot(int posicaoFilme, int posicaoHot) {
        // verifica se a posição do filme é válida (dentro do intervalo e não nula)
        if (posicaoFilme < 1 || posicaoFilme >= filmes.length || this.filmes[posicaoFilme - 1] == null) {
            return "POSIÇÃO DE FILME INVÁLIDA!";
        }
        // verifica se a posição da hotlist é válida (dentro do intervalo)
        if (posicaoHot < 1 || posicaoHot > this.hotList.length) {
            return "POSIÇÃO DA HOTLIST INVÁLIDA!";
        }

        Filme filmeAAdicionar = this.filmes[posicaoFilme - 1];

        // verifica se o filme já foi adicionado à hotlist
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
        // verifica se a posição é válida (dentro do intervalo e não nulo)
        if (posicaoHot < 1 || posicaoHot > 10 || this.hotList[posicaoHot - 1] == null) {
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