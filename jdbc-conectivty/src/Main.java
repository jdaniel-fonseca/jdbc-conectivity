import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        //Cria a conexão com o banco de dados.
        Connection conn = null;

        //Serve para montar um comando SQL a ser executado.
        Statement st = null;

        //Representará um objeto contendo o resultado da consulta em forma de tabela.
        ResultSet rs = null;

        try {
            //Abrindo a conexão com o banco.
            conn = DB.open();

            //Criando o Statment a partir da conexão com o banco de dados
            st = conn.createStatement();

            //Montando o comando SQL e atribuindo o valor que retornará ao ResultSet.
            rs = st.executeQuery("select * from seller");

            //Percorrendo o ResultSet com o comando rs.next() e definindo quais campos da tabela quero pegar.
            while (rs.next()) {
                System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            //Fechando o statment, o resultSet e o banco
            DB.closeStatment(st);
            DB.closeResultSet(rs);
            DB.close();
        }
    }
}