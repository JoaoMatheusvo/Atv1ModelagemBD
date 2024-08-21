import DAO.AutorDAO;
import DAO.LivroDAO;
import Entity.Autor;
import Entity.Livro;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try{
            AutorDAO autorDAO = new AutorDAO();
            LivroDAO livroDAO = new LivroDAO();


            // Inserir o primeiro autor
            Autor autor1 = new Autor();
            autor1.setNomeAutor("Machado de Assis");
            autor1.setNacionalidade("Brasil");
            autorDAO.inserirAutor(autor1);

            // Inserir o segundo autor
            Autor autor2 = new Autor();
            autor2.setNomeAutor("George Orwell");
            autor2.setNacionalidade("Britânico");
            autorDAO.inserirAutor(autor2);

            // Inserir 3 livros para o primeiro autor
            Livro livro1 = new Livro();
            livro1.setTitulo("A Carteira");
            livro1.setAnoPublicado(1884);
            livro1.setAutor(autor1);
            livroDAO.inserirLivro(livro1);

            Livro livro2 = new Livro();
            livro2.setTitulo("Dom Casmurro");
            livro2.setAnoPublicado(1899);
            livro2.setAutor(autor1);
            livroDAO.inserirLivro(livro2);

            Livro livro3 = new Livro();
            livro3.setTitulo("O Alienista");
            livro3.setAnoPublicado(1882);
            livro3.setAutor(autor1);
            livroDAO.inserirLivro(livro3);

            // Inserir 3 livros para o segundo autor
            Livro livro4 = new Livro();
            livro4.setTitulo("1984");
            livro4.setAnoPublicado(1949);
            livro4.setAutor(autor2);
            livroDAO.inserirLivro(livro4);

            Livro livro5 = new Livro();
            livro5.setTitulo("A Revolução dos Bichos");
            livro5.setAnoPublicado(1945);
            livro5.setAutor(autor2);
            livroDAO.inserirLivro(livro5);

            Livro livro6 = new Livro();
            livro6.setTitulo("A Política e a Língua Inglesa");
            livro6.setAnoPublicado(1946);
            livro6.setAutor(autor2);
            livroDAO.inserirLivro(livro6);

            // Listar todos os autores
            List<Autor> autores = autorDAO.listarAutores();
            System.out.println("\nAutores:");
            for (Autor a : autores) {
                System.out.println(a.getIdAutor() + ": " + a.getNomeAutor() + " (" + a.getNacionalidade() + ")");
            }

            // Listar todos os livros
            List<Livro> livros = livroDAO.listarLivros();
            System.out.println("\nTodos os livros:");
            for (Livro l : livros) {
                System.out.println(l.getIdLivro() + ": " + l.getTitulo() + " (" + l.getAnoPublicado() + ") - Autor ID: " + l.getAutor().getIdAutor());
            }

            // Listar todos os livros do primeiro autor
            List<Livro> livrosDeAutor1 = livroDAO.listarLivrosDeAutor(autor1.getIdAutor());
            System.out.println("\nLivros do autor " + autor1.getNomeAutor() + ":");
            for (Livro l : livrosDeAutor1) {
                System.out.println(l.getIdLivro() + ": " + l.getTitulo() + " (" + l.getAnoPublicado() + ")");
            }

            // Listar todos os livros do segundo autor
            List<Livro> livrosDeAutor2 = livroDAO.listarLivrosDeAutor(autor2.getIdAutor());
            System.out.println("\nLivros do autor " + autor2.getNomeAutor() + ":");
            for (Livro l : livrosDeAutor2) {
                System.out.println(l.getIdLivro() + ": " + l.getTitulo() + " (" + l.getAnoPublicado() + ")");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}