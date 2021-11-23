package mta.universitate.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;



public class Database {
    private static Database dbObject;
    private Connection con;
    private Database() {
        String ConnectionUrl="jdbc:sqlserver://pituserver.database.windows.net:1433;database=secretariatatm;user=pituAdmin@pituserver;password=1q2w3e4rT;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        try {
            con = DriverManager.getConnection(ConnectionUrl);
        }
        catch (SQLException e) {
               System.out.println(e.getMessage());
        }
    }

    public static synchronized Database getInstance() {

        // create object if it's not already created
        if(dbObject == null) {
            dbObject = new Database();
        }

        // returns the singleton object
        return dbObject;
    }

    public ResultSet executeQuery(String query)
    {
        try {
            if(con!=null){
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("S-a facut conexiunea.Prima coloana este:");
           /* while (resultSet.next()) {
                System.out.println(resultSet.getString(2)); //nu stiu daca aici ia doar prima coloana
            }*/
            System.out.println("Incerc sa afisez toate coloanele:");
            ResultSetMetaData metadata = resultSet.getMetaData();
            int columnCount = metadata.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metadata.getColumnName(i) + "\t");
                }
            System.out.println();
                while (resultSet.next()) {
                    String row = "";
                    for (int i = 1; i <= columnCount; i++) {
                        row += resultSet.getString(i) + "\t";
                    }
                    System.out.println();
                    System.out.print(row);
                }
            return resultSet;}
            else
                System.out.println("Conexiunea nu s-a putut face");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection getConnection() {
        return con;
    }
}
