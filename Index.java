import java.io.FileNotFoundException;

public class Index {
    
    public static void main(String[] args) {
        
        try {

            RequestAPI.implementation();
            ManipulatorSQL.conection();

        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }


    }
}
