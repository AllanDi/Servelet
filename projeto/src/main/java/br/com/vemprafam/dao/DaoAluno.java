package br.com.vemprafam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.vemprafam.pojo.Aluno;

public class DaoAluno {

    private String user = "SA";
    private String password = "";
    private String url = "jdbc:hsqldb:hsql://localhost/";
    private Connection connection = null;

    public DaoAluno() {
        super();
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void inserir(Aluno aluno) {
        try {
            String sql = "INSERT INTO ALUNO VALUES(?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, aluno.getRa());
            pstmt.setString(2, aluno.getNome());
            pstmt.setDate(3, new java.sql.Date(aluno.getDataNascimento().getTime()));
            pstmt.setDouble(4, aluno.getSalario());

            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Aluno> getLista() {
        List<Aluno> result = new ArrayList<Aluno>();
        try {
            String sql = "SELECT RA, NOME, DATANASCIMENTO, SALARIO FROM ALUNO"; // Correção no SQL
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int ra = rs.getInt("RA");
                String nome = rs.getString("NOME");
                Date dataNascimento = rs.getDate("DATANASCIMENTO");
                double salario = rs.getDouble("SALARIO");

                Aluno aluno = new Aluno(ra, nome, dataNascimento, salario);
                result.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Aluno buscaPeloRa(int ra) {        
        Aluno aluno = null; 
        try {
            String sql = "SELECT RA, NOME, DATANASCIMENTO, SALARIO FROM ALUNO WHERE RA = ?"; 
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, ra);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int raResultado = rs.getInt("RA");
                String nome = rs.getString("NOME");
                Date dataNascimento = rs.getDate("DATANASCIMENTO");
                double salario = rs.getDouble("SALARIO");
                
                aluno = new Aluno(raResultado, nome, dataNascimento, salario); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aluno; 
    }

    public void atualizar(Aluno aluno) {
        try {
            String sql = "UPDATE ALUNO SET NOME=?, DATANASCIMENTO=?, SALARIO=? WHERE RA=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, aluno.getNome());
            pstmt.setDate(2, new java.sql.Date(aluno.getDataNascimento().getTime()));
            pstmt.setDouble(3, aluno.getSalario());
            pstmt.setInt(4, aluno.getRa());

            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void excluir(Aluno aluno) {
        try {
            String sql = "DELETE FROM ALUNO WHERE RA=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, aluno.getRa());

            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    	DaoAluno dao = new DaoAluno();
    	
    	dao.inserir(new Aluno(0, "João Silva0", new Date(), 2500.00));
    	dao.inserir(new Aluno(1, "João Silva1", new Date(), 2500.01));
    	dao.inserir(new Aluno(2, "João Silva2", new Date(), 2500.02));
    	dao.inserir(new Aluno(3, "João Silva3", new Date(), 2500.03));
    	dao.inserir(new Aluno(4, "João Silva4", new Date(), 2500.04));

    	System.out.println(dao.getLista());
    	
    	System.out.println(dao.buscaPeloRa(4));
    	System.out.println(dao.buscaPeloRa(3));
    	System.out.println(dao.buscaPeloRa(2));
    	System.out.println(dao.buscaPeloRa(1));
    	System.out.println(dao.buscaPeloRa(0));

    	dao.excluir(new Aluno(0, "João Silva0", new Date(), 2500.00));
    	dao.excluir(new Aluno(1, "João Silva1", new Date(), 2500.01));
    	dao.excluir(new Aluno(2, "João Silva2", new Date(), 2500.02));
    	dao.excluir(new Aluno(3, "João Silva3", new Date(), 2500.03));
    	dao.excluir(new Aluno(4, "João Silva4", new Date(), 2500.04));
    }
}
