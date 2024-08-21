package DAO;

import Entity.Autor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {
    private Connection connection;
    public AutorDAO()throws SQLException{
        this.connection = ConexaoBD.getInstance().getConexao();
    }
    public void inserirAutor(Autor autor)throws SQLException{
        String sql = "INSERT INTO Autor (Nome, Nacionalidade) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
            statement.setString(1, autor.getNomeAutor());
            statement.setString(2, autor.getNacionalidade());
            int affectedRows = statement.executeUpdate();

            //Verifica se a inserção foi bem-sucedida e recupera o ID gerado
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        autor.setIdAutor(generatedKeys.getInt(1));
                    }
                }
            }

        }
    }

    public void atualizarAutor(Autor autor) throws SQLException {
        String sql = "UPDATE Autor SET Nome = ?, Nacionalidade = ? WHERE ID_Autor = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, autor.getNomeAutor());
            statement.setString(2, autor.getNacionalidade());
            statement.setInt(3, autor.getIdAutor());
            statement.executeUpdate();
        }
    }

    public void excluirAutor(int idAutor) throws SQLException {
        String sql = "DELETE FROM Autor WHERE ID_Autor = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idAutor);
            statement.executeUpdate();
        }
    }

    public List<Autor>listarAutores() throws SQLException{
        List<Autor>autores = new ArrayList<>();
        String sql = "Select * From Autor";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet set = statement.executeQuery();
            while (set.next()){
                Autor autor = new Autor();
                autor.setIdAutor(set.getInt("ID_Autor"));
                autor.setNomeAutor(set.getString("Nome"));
                autor.setNacionalidade(set.getString("Nacionalidade"));
                autores.add(autor);
            }
        }
        return autores;
    }
}
