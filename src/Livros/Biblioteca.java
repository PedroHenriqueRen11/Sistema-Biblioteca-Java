package Livros;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> acervo;
    public static final int ANO_PUBLICACAO_MINIMO = 1900;

    public Biblioteca() {
        this.acervo = new ArrayList<>();
    }

    public Livro adicionar(Livro livro) throws Exception {
        if (livro == null) throw new Exception("Livro não pode ser nulo");

        if (livro.getTitulo() == null || livro.getTitulo().isEmpty())
            throw new Exception("Título não pode ser em branco");

        if (livro.getAutor() == null || livro.getAutor().isEmpty())
            throw new Exception("Autor não pode ser em branco");

        int anoAtual = LocalDate.now().getYear();
        if (livro.getAnoPublicacao() < ANO_PUBLICACAO_MINIMO || livro.getAnoPublicacao() > anoAtual)
            throw new Exception("Ano de publicação inválido");

        for (Livro l : acervo) {
            if (l.getTitulo().equalsIgnoreCase(livro.getTitulo())
                && l.getAutor().equalsIgnoreCase(livro.getAutor())
                && l.getAnoPublicacao() == livro.getAnoPublicacao()) {
                throw new Exception("Livro já existe no acervo!");
            }
        }

        acervo.add(livro);
        return livro;
    }

    public List<Livro> listar() {
        return new ArrayList<>(acervo);
    }

    public List<Livro> pesquisarPorTitulo(String titulo) {
        List<Livro> resultado = new ArrayList<>();
        for (Livro l : acervo) {
            if (l.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultado.add(l);
            }
        }
        return resultado;
    }

    public boolean remover(String titulo) {
        return acervo.removeIf(l -> l.getTitulo().equalsIgnoreCase(titulo));
    }

    public int contarLivros() {
        return acervo.size();
    }

    public Livro maisAntigo() {
        if (acervo.isEmpty()) return null;
        Livro maisAntigo = acervo.get(0);
        for (Livro l : acervo) {
            if (l.getAnoPublicacao() < maisAntigo.getAnoPublicacao())
                maisAntigo = l;
        }
        return maisAntigo;
    }

    public Livro maisNovo() {
        if (acervo.isEmpty()) return null;
        Livro maisNovo = acervo.get(0);
        for (Livro l : acervo) {
            if (l.getAnoPublicacao() > maisNovo.getAnoPublicacao())
                maisNovo = l;
        }
        return maisNovo;
    }
    
    public boolean atualizar(String tituloAntigo, Livro livroNovo) {
        for (int i = 0; i < acervo.size(); i++) {
            Livro livroExistente = acervo.get(i);
            if (livroExistente.getTitulo().equalsIgnoreCase(tituloAntigo)) {
                acervo.set(i, livroNovo);
                return true;
            }
        }
        return false;
    }
}