package CompuDIA.modelo.persistencia;

import CompuDIA.modelo.Computador;

import java.sql.*;
import java.util.*;

public class Conector {

    private Computador computador;
    String url = "jdbc:sqlite:computador.db";
    Connection connect = null;

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connect = DriverManager.getConnection(url);
            if (connect != null) {
                System.out.println("Conectado");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("No se ha podido conectar a la base de datos\n" + ex.getMessage());
        }
    }

    public void close() {
        try {
            connect.close();
        } catch (SQLException ex) {
            System.err.println("Error al cerrar la BD\n" + ex.getMessage());

        }
    }

    public void saveComputador(Computador computador) {
        try {
            PreparedStatement st = connect.prepareStatement("INSERT INTO computador(codigo,memoria,marca) values (?,?,?)");
            st.setString(1, computador.getCodigo());
            st.setString(2, computador.getMemoria());
            st.setString(3, computador.getMarca());
            st.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void mostrarComputador() {
        ResultSet result = null;
        try {
            PreparedStatement st = connect.prepareStatement("select * from computador");
            result = st.executeQuery();
            while (result.next()) {
                // String mostrarDB;
                System.out.print("Codigo: ");
                System.out.println(result.getString("codigo"));

                System.out.print("Memoria: ");
                System.out.println(result.getString("memoria"));

                System.out.print("Marca:");
                System.out.println(result.getString("marca"));

                System.out.println("=======================");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void delComputador(Computador computador) {
        try {
            PreparedStatement st = connect.prepareStatement("DELETE FROM computador WHERE codigo = ?");
            st.setString(1, computador.getCodigo());
            System.out.println("Computador eliminado en DB");
            st.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


}
