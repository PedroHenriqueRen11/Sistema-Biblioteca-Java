package Livros;

import java.util.List;
import java.util.Scanner;
import Livros.LivroFisico;
import Livros.LivroDigital; 

public class Main {
    private static Biblioteca biblioteca = new Biblioteca();
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n=== Sistema Biblioteca ===");
            System.out.println("1 - Adicionar Livro");
            System.out.println("2 - Listar Acervo");
            System.out.println("3 - Pesquisar Livro");
            System.out.println("4 - Remover Livro");
            System.out.println("5 - Atualizar Livro");
            System.out.println("6 - Contar Livros");
            System.out.println("7 - Mostrar Livro mais Antigo e Mais Novo");
            System.out.println("0 - Sair");

            opcao = Input.scanInt("Digite sua escolha: ", scan);
            switch (opcao) {
                case 1: cadastrarLivro(); break;
                case 2: listarAcervo(); break;
                case 3: pesquisarLivro(); break;
                case 4: removerLivro(); break;
                case 5: atualizarLivro(); break;
                case 6: System.out.println("Total de livros: " + biblioteca.contarLivros()); break;
                case 7:
                    Livro maisAntigo = biblioteca.maisAntigo();
                    Livro maisNovo = biblioteca.maisNovo();

                    if (maisAntigo != null) {
                        System.out.println("Mais antigo: " + maisAntigo.getTitulo() + " (" + maisAntigo.getAnoPublicacao() + ")");
                    } else {
                        System.out.println("Nenhum livro no acervo para mostrar o mais antigo.");
                    }
                    
                    if (maisNovo != null) {
                         System.out.println("Mais novo: " + maisNovo.getTitulo() + " (" + maisNovo.getAnoPublicacao() + ")");
                    } else {
                        System.out.println("Nenhum livro no acervo para mostrar o mais novo.");
                    }
                    break;
                case 0: System.out.println("Saindo..."); break;
                default: System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void cadastrarLivro() {
        System.out.println("Cadastrar Livro");
        System.out.println("1 - Físico");
        System.out.println("2 - Digital");
        int tipo = Input.scanInt("Escolha o tipo: ", scan);

        String titulo = Input.scanString("Título: ", scan);
        String autor = Input.scanString("Autor: ", scan);
        int ano = Input.scanInt("Ano de Publicação: ", scan);
        int paginas = Input.scanInt("Número de Páginas: ", scan);

        try {
            Livro livro;
            if (tipo == 1) {
                livro = new LivroFisico(titulo, autor, ano, paginas);
            } else if (tipo == 2) {
                livro = new LivroDigital(titulo, autor, ano, paginas);
            } else {
                System.out.println("Tipo de livro inválido. O livro não será cadastrado.");
                return;
            }

            biblioteca.adicionar(livro);
            System.out.println("Livro cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void listarAcervo() {
        List<Livro> lista = biblioteca.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum livro no acervo.");
        } else {
            for (Livro l : lista) {
                System.out.println(l.toString());
            }
        }
    }

    private static void pesquisarLivro() {
        String titulo = Input.scanString("Digite o título para pesquisa: ", scan);
        List<Livro> resultado = biblioteca.pesquisarPorTitulo(titulo);
        if (resultado.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            for (Livro l : resultado) {
                System.out.println(l.toString());
            }
        }
    }

    private static void removerLivro() {
        String titulo = Input.scanString("Digite o título do livro a remover: ", scan);
        if (biblioteca.remover(titulo)) {
            System.out.println("Livro removido com sucesso!");
        } else {
            System.out.println("Livro não encontrado.");
        }
    }
    
    private static void atualizarLivro() {
        System.out.println("Atualizar Livro");
    
        String tituloAntigo = Input.scanString("Digite o título do livro que deseja atualizar: ", scan);
        
        System.out.println("Digite os novos dados do livro:");
        String tituloNovo = Input.scanString("Novo Título: ", scan);
        String autorNovo = Input.scanString("Novo Autor: ", scan);
        int anoNovo = Input.scanInt("Novo Ano de Publicação: ", scan);
        int paginasNovas = Input.scanInt("Novo Número de Páginas: ", scan);
        
        System.out.println("Escolha o tipo do novo livro (o tipo atual será substituído):");
        System.out.println("1 - Físico");
        System.out.println("2 - - Digital");
        int tipoNovo = Input.scanInt("Escolha o tipo: ", scan);

        try {
            Livro livroNovo;
            if (tipoNovo == 1) {
                livroNovo = new LivroFisico(tituloNovo, autorNovo, anoNovo, paginasNovas);
            } else if (tipoNovo == 2) {
                livroNovo = new LivroDigital(tituloNovo, autorNovo, anoNovo, paginasNovas);
            } else {
                System.out.println("Tipo de livro inválido. O livro não será atualizado.");
                return;
            }

            if (biblioteca.atualizar(tituloAntigo, livroNovo)) {
                System.out.println("Livro atualizado com sucesso!");
            } else {
                System.out.println("Livro não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar o livro: " + e.getMessage());
        }
    }
}
