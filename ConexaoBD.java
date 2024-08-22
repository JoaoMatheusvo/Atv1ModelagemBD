package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static ConexaoBD instancia;
    private Connection conexao;
    private static final String URL = "jdbc:mysql://localhost:3306/javasqlbd";
    private static final String USUARIO = "root";
    private static final String SENHA = "12345";

    // Construtor privado para evitar instanciação externa
    private ConexaoBD() {
        try {
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao conectar com o banco de dados", e);
        }
    }

    // Método para obter a instância única da classe
    public static synchronized ConexaoBD getInstance() {
        if (instancia == null) {
            instancia = new ConexaoBD();
        }
        return instancia;
    }

    // Método para obter a conexão
    public Connection getConexao() {
        return conexao;
    }

    // Método para fechar a conexão
    public void fecharConexao() {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
