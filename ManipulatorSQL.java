
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ManipulatorSQL {

    static Connection con;

    public static void conection() throws FileNotFoundException {
        
        try {
            String nomeJDBC = "jdbc:mysql://localhost/bancodeddados";
            String option = "?useTimezone=true&serverTimezone=UTC";
            String nomeUser = "root";
            String password = "";
    
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(nomeJDBC + option, nomeUser, password);
            
            Statement st = con.createStatement();
            st.executeUpdate("USE " + "bancodeddados");
            
            InsereBancodeDados();

        } catch (ClassNotFoundException | SQLException e) {
            
            e.printStackTrace();
        }
    }

    public static void InsereBancodeDados(){

        try{
            
            String pedido = "INSERT INTO informacoes(nome, tagLocalizacao) VALUES (?, ?)";
            
            PreparedStatement st = con.prepareStatement(pedido);
            
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("Informations.json"));

            String nome = (String) jsonObject.get("nome");
            String tag = (String) jsonObject.get("tagLocalizacao");

            st.setString(1, nome);
            st.setString(2, tag);

            st.close();
            con.close();

        }catch(IOException | ParseException | SQLException e){
            
            e.printStackTrace();
        }
    }
}
