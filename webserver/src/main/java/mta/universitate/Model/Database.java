package mta.universitate.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Locale;


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
            return resultSet;
            }
            else
                System.out.println("Conexiunea nu s-a putut face");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getStudentInfo(String id)
    {
        String query="SELECT S.Nume, S.Prenume,G.denumire_grupa,SP.Denumire, S.An_de_Studiu,U.Username " +
                "FROM studenti as S " +
                "INNER JOIN specializari as SP " +
                "ON S.FK_Specializare = SP.ID_Specializare " +
                "INNER JOIN grupe_studiu as G " +
                "ON S.FK_Grupa = G.ID_Grupa " +
                "INNER JOIN utilizatori AS U " +
                "ON S.FK_ID_User = U.ID_User " +
                "WHERE S.ID_Student = " + id ;

        return executeQuery(query);
    }

    public ResultSet getStudentGrades(String id)
    {
        String query="SELECT M.NumeMaterie,NS.Valoare, NS.Data_calendar " +
                "FROM studenti AS S " +
                "INNER JOIN note_studenti AS NS " +
                "ON S.ID_Student = NS.FK_Student " +
                "INNER JOIN materii AS M " +
                "ON M.ID_Materie = NS.FK_Materie " +
                "WHERE S.ID_Student = " + id ;

        return executeQuery(query);
    }

    public ResultSet getProfessorInfo(String id)
    {
        String query="SELECT A.ID_Angajat, A.Nume, A.Prenume, A.Salariu, U.Username " +
                "FROM angajati AS A " +
                "INNER JOIN functii AS F " +
                "ON A.FK_Functia = F.ID_Functie " +
                "INNER JOIN utilizatori AS U " +
                "ON A.FK_ID_User = U.ID_User " +
                "WHERE a.ID_Angajat = " + id;
        return executeQuery(query);
    }

    public ResultSet getProfessorClasses(String id)
    {
        String query="SELECT A.ID_Angajat,M.NumeMaterie,Tip_Ora, ORAR.Ora,G.denumire_grupa,S.Denumire\n" +
                "FROM angajati AS A\n" +
                "INNER JOIN functii AS F\n" +
                "ON A.FK_Functia = F.ID_Functie\n" +
                "INNER JOIN materii AS M\n" +
                "ON A.ID_Angajat = M.FK_Profesor\n" +
                "INNER JOIN ore AS O\n" +
                "ON M.ID_Materie=O.FK_Materie\n" +
                "INNER JOIN orar AS ORAR\n" +
                "ON O.ID_Ora = ORAR.FK_Ore\n" +
                "INNER JOIN grupe_studiu AS G\n" +
                "ON ORAR.FK_Grupa = G.ID_Grupa\n" +
                "INNER JOIN sali_de_clasa AS S\n" +
                "ON ORAR.FK_Sala = S.ID_Sala\n" +
                "WHERE A.ID_Angajat=" +id;
        return executeQuery(query);
    }


    public void createStudent(Student S)
    {
        String firstName=S.getName(), lastName=S.getSurname();
        int studyYear=S.getStudyYear().getYear();
        int idGroup=S.getStudyGroup().getId();
        int idSpecialization=S.getSpecialization();
        int income=S.getIncome();

        String username=firstName.toLowerCase(Locale.ROOT) + "."+lastName.toLowerCase(Locale.ROOT);
        String password="student2021";
        int tipCont=100;

        String query = "INSERT INTO utilizatori"+
        "VALUES('"+username+"','"+password+"',"+String.valueOf(tipCont)+")";

        executeQuery(query);
        //TO DO adauga studentul
//        query = "INSERT INTO studenti\n" +
//                "VALUES('Marian','Razvan',2000,114,300,3,1010)";
    }

    public Connection getConnection() {
        return con;
    }
}
