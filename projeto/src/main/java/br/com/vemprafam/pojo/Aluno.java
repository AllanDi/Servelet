package br.com.vemprafam.pojo;

import java.util.Date;

public class Aluno {
	private int ra;
	private String nome;
	private Date dataNascimento;
	private double salario;
	
	
	
	public Aluno() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Aluno(int ra, String nome, Date dataNascimento, double salario) {
		super();
		this.ra = ra;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.salario = salario;
	}

	public int getRa() {
		return ra;
	}

	public void setRa(int ra) {
		this.ra = ra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Aluno [ra=" + ra + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", salario=" + salario
				+ "]";
	}
	
	public static void main(String[] args) {
		Aluno aluno = new Aluno();
		System.out.println(aluno);
		
	}
	
//	CREATE TABLE Aluno (
//		    ra INT PRIMARY KEY,
//		    nome VARCHAR(255),
//		    dataNascimento DATE,
//		    salario DECIMAL(10, 2)
//		);
	
//	INSERT INTO Aluno (ra, nome, dataNascimento, salario) 
//	VALUES (001, 'João Silva', '2000-01-01', 1500.00);
	
//	DELETE FROM Aluno;

	
	
	
	
}
