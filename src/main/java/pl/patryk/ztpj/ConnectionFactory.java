package pl.patryk.ztpj;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
//    private static final String URL = "jdbc:mysql://localhost:3306/ztpj_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//    private static final String USER = "ztpj";
//    private static final String PASS = "patryk";

//    private static final String URL = "jdbc:mysql://pp32818@db.zut.edu.pl:3306/pp32818?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//    private static final String USER = "pp32818";
//    private static final String PASS = "lGKlnHJe";

    private static final String URL = "jdbc:mysql://db4free.net:3306/ztpj_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "ztpj12";
    private static final String PASS = "patryk12";

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
        public static void main(String[] args) {
            Connection connection = ConnectionFactory.getConnection();
        }
    }