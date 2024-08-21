package DAO;

import Entity.Autor;
import Entity.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    private Connection connection;
    public LivroDAO()throws SQLException {
        this.connection = ConexaoBD.getInstance().getConexao();
    }

    public void inserirLivro(Livro livro)throws SQLException{
        String sql = "INSERT INTO Livro (Titulo, Ano_Publicacao, Id_Autor) VALUES (?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, livro.getTitulo());
            statement.setInt(2, livro.getAnoPublicado());
            statement.setInt(3, livro.getAutor().getIdAutor());
            statement.executeUpdate();
        }
    }

    public void atualizarLivro(Livro livro) throws SQLException {
        String sql = "UPDATE Livro SET Titulo = ?, Ano_Publicacao = ?, Id_Autor = ? WHERE ID_Livro = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, livro.getTitulo());
            statement.setInt(2, livro.getAnoPublicado());
            statement.setInt(3, livro.getAutor().getIdAutor());
            statement.setInt(4, livro.getIdLivro());
            statement.executeUpdate();
        }
    }

    public void excluirLivro(int idLivro) throws SQLException {
        String sql = "DELETE FROM Livro WHERE ID_Livro = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idLivro);
            statement.executeUpdate();
        }
    }


    public List<Livro>listarLivros ()throws SQLException{
        List<Livro>livros = new ArrayList<>();
        String sql = "Select * From Livro";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet set = statement.executeQuery();
            while (set.next()){
                Livro livro = new Livro();
                livro.setIdLivro(set.getInt("ID_Livro"));
                livro.setTitulo(set.getString("Titulo"));
                livro.setAnoPublicado(set.getInt("Ano_Publicacao"));
                Autor autor = new Autor();
                autor.setIdAutor(set.getInt("ID_Autor"));
                livro.setAutor(autor);

                livros.add(livro);

            }
        }
        return livros;
    }

    public List<Livro> listarLivrosDeAutor(int idAutor) throws SQLException {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM Livro WHERE Id_Autor = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idAutor);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Livro livro = new Livro();
                livro.setIdLivro(set.getInt("ID_Livro"));
                livro.setTitulo(set.getString("Titulo"));
                livro.setAnoPublicado(set.getInt("Ano_Publicacao"));

                Autor autor = new Autor();
                autor.setIdAutor(set.getInt("ID_Autor"));
                livro.setAutor(autor);

                livros.add(livro);
            }
        }
        return livros;
    }
}
