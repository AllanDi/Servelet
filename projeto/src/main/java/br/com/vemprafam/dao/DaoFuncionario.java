package br.com.vemprafam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import br.com.vemprafam.pojo.Funcionario;

public class DaoFuncionario {

    private String user = "SA";
    private String password = "";
    private String url = "jdbc:hsqldb:hsql://localhost/";
    private Connection connection = null;

    public DaoFuncionario() {
        super();
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Funcionario funcionario) {
        try {
            String sql = "INSERT INTO FUNCIONARIO (RE, NOME, DATADEADMISSAO, SALARIO) VALUES(?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, funcionario.getRe());
            pstmt.setString(2, funcionario.getNome());
            pstmt.setDate(3, new java.sql.Date(funcionario.getDataDeAdmissao().getTime()));
            pstmt.setDouble(4, funcionario.getSalario());

            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Funcionario> getLista() {
        List<Funcionario> result = new ArrayList<Funcionario>();
        try {
            String sql = "SELECT RE, NOME, DATADEADMISSAO, SALARIO FROM FUNCIONARIO";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int re = rs.getInt("RE");
                String nome = rs.getString("NOME");
                Date dataDeAdmissao = rs.getDate("DATADEADMISSAO");
                double salario = rs.getDouble("SALARIO");

                Funcionario funcionario = new Funcionario(re, nome, dataDeAdmissao, salario);
                result.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Funcionario buscaPeloRe(int re) {
        Funcionario funcionario = null; 
        try {
            String sql = "SELECT RE, NOME, DATADEADMISSAO, SALARIO FROM FUNCIONARIO WHERE RE = ?"; 
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, re); 
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int raResultado = rs.getInt("RE");
                String nome = rs.getString("NOME");
                Date dataDeAdmissao = rs.getDate("DATADEADMISSAO");
                double salario = rs.getDouble("SALARIO");
                
                funcionario = new Funcionario(raResultado, nome, dataDeAdmissao, salario); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionario; 
    }

    public void atualizar(Funcionario funcionario) {
        try {
            String sql = "UPDATE FUNCIONARIO SET NOME=?, DATADEADMISSAO=?, SALARIO=? WHERE RE=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, funcionario.getNome());
            pstmt.setDate(2, new java.sql.Date(funcionario.getDataDeAdmissao().getTime()));
            pstmt.setDouble(3, funcionario.getSalario());
            pstmt.setInt(4, funcionario.getRe());

            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Funcionario funcionario) {
        try {
            String sql = "DELETE FROM FUNCIONARIO WHERE RE=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, funcionario.getRe());

            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    //criação de um metodo que exclui diretamente pelo RE
    public void excluir2(int re) {
        try {
            String sql = "DELETE FROM FUNCIONARIO WHERE RE=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, re); 
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
    	
    	//criação objeto dao
        DaoFuncionario dao = new DaoFuncionario();
        
        //inserção de alguns objetos
        dao.inserir(new Funcionario(0, "João Silva0", new Date(), 2500.00));
        dao.inserir(new Funcionario(1, "João Silva1", new Date(), 2500.01));
        dao.inserir(new Funcionario(2, "João Silva2", new Date(), 2500.02));
        dao.inserir(new Funcionario(3, "João Silva3", new Date(), 2500.03));
        dao.inserir(new Funcionario(4, "João Silva4", new Date(), 2500.04));
        
        //impressão lista
        System.out.println(dao.getLista());
        
        //buscar por re
        System.out.println(dao.buscaPeloRe(4));
        System.out.println(dao.buscaPeloRe(3));
        System.out.println(dao.buscaPeloRe(2));
        System.out.println(dao.buscaPeloRe(1));
        System.out.println(dao.buscaPeloRe(0));
        
        //excluir por objeto
        dao.excluir(new Funcionario(0, "João Silva0", new Date(), 2500.00));
        dao.excluir(new Funcionario(1, "João Silva1", new Date(), 2500.01));
        dao.excluir(new Funcionario(2, "João Silva2", new Date(), 2500.02));
        dao.excluir(new Funcionario(3, "João Silva3", new Date(), 2500.03));
        dao.excluir(new Funcionario(4, "João Silva4", new Date(), 2500.04));
        
        //excluir por re        
        dao.inserir(new Funcionario(0, "João Silva0", new Date(), 2500.00));
        System.out.println(dao.buscaPeloRe(0));
        dao.excluir2(0);
        
        //retorna null
        System.out.println(dao.buscaPeloRe(0));
    }
}
