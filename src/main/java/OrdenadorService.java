import java.sql.SQLException;
public class OrdenadorService {
    static Ordenador pc = null;
    static java.sql.Connection con;
    public static java.sql.Connection getConnection(){
        String host = "jdbc:sqlite:src/main/resources/tiendapcs";
        if (con == null) {
            try {
                con = java.sql.DriverManager.getConnection(host);
            }catch (SQLException sql){
                System.out.println(sql.getMessage());
                System.exit(0);
            }
        }
        return con;
    }
}
