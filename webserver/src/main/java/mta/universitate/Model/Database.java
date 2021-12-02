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
        String ConnectionUrl="jdbc:sqlserver://pituserver.database.windows.net:1433;database=secretariatatm1;user=pituAdmin@pituserver;password=1q2w3e4rT;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
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

    public ResultSet getStudentInfo(String nume, String prenume) throws SQLException {
        String query= "SELECT [dbo].[studenti].[Nume] + ' ' + [dbo].[studenti].[Prenume] as 'Student',[dbo].[studenti].[ID_Student],[dbo].[studenti].[An_de_Studiu],[dbo].[studenti].[Solda],[dbo].[grupe_studiu].[denumire_grupa], [dbo].[specializari].[Denumire] as 'Specializare',[dbo].[facultati].[Denumire] as 'Facultate'\n" +
                        "FROM [dbo].[studenti]\n" +
                        "inner join [dbo].[grupe_studiu]\n" +
                        "on [dbo].[grupe_studiu].[ID_Grupa]=[dbo].[studenti].[FK_Grupa]\n" +
                        "inner join [dbo].[specializari]\n" +
                        "on [dbo].[specializari].[ID_Specializare]=[dbo].[studenti].[FK_Specializare]\n" +
                        "inner join [dbo].[facultati]\n" +
                        "on [dbo].[facultati].[ID_Facultate]=[dbo].[specializari].[FK_Facultate]\n" +
                        "where [dbo].[studenti].[Nume]='"+nume+"' and [dbo].[studenti].[Prenume]='"+prenume+"'\n";

        return executeQuery(query);
    }

    public ResultSet getGradesforStudent(String nume, String prenume) throws SQLException {
        String query= "select [dbo].[note_studenti].[Valoare], [dbo].[note_studenti].[Data_calendar], [dbo].[materii].[NumeMaterie], [dbo].[angajati].[Nume] + ' ' + [dbo].[angajati].[Prenume] as 'Profesor', [dbo].[grupe_studiu].[denumire_grupa], [dbo].[studenti].[An_de_Studiu], [dbo].[specializari].[Denumire] as 'Specializare', [dbo].[facultati].[Denumire] as 'Facultate'\n" +
                "from [dbo].[note_studenti]\n" +
                "inner join [dbo].[materii]\n" +
                "on [dbo].[materii].[ID_Materie] = [dbo].[note_studenti].[FK_Materie]\n" +
                "inner join [dbo].[angajati]\n" +
                "on [dbo].[angajati].[ID_Angajat]=[dbo].[materii].[FK_Profesor]\n" +
                "inner join [dbo].[studenti]\n" +
                "on [dbo].[studenti].[ID_Student]=[dbo].[note_studenti].[FK_Student]\n" +
                "inner join [dbo].[grupe_studiu]\n" +
                "on [dbo].[grupe_studiu].[ID_Grupa]=[dbo].[studenti].[FK_Grupa]\n" +
                "inner join [dbo].[specializari]\n" +
                "on [dbo].[specializari].[ID_Specializare]=[dbo].[studenti].[FK_Specializare]\n" +
                "inner join [dbo].[facultati]\n" +
                "on [dbo].[facultati].[ID_Facultate]=[dbo].[specializari].[FK_Facultate]" +
                "where [dbo].[studenti].[Nume]='"+nume+"' and [dbo].[studenti].[Prenume]='"+prenume+"'\n";

        return executeQuery(query);
    }

    public ResultSet getStudentClasses(String nume, String prenume) throws SQLException {
        String query= "SELECT [dbo].[materii].[NumeMaterie],[dbo].[materii].[Nr_Credite],[dbo].[studenti].[An_de_Studiu],[dbo].[grupe_studiu].[denumire_grupa], [dbo].[specializari].[Denumire] as 'Specializare',[dbo].[facultati].[Denumire] as 'Facultate',[dbo].[angajati].[Nume] +' '+[dbo].[angajati].[Prenume] as 'Profesor'\n "+
        "FROM [dbo].[studenti]\n" +
        "inner join [dbo].[grupe_studiu]\n" +
        "on [dbo].[grupe_studiu].[ID_Grupa]=[dbo].[studenti].[FK_Grupa]\n" +
        "inner join [dbo].[orar]\n" +
        "on [dbo].[grupe_studiu].[ID_Grupa]=[dbo].[orar].[FK_Grupa]\n" +
        "inner join [dbo].[ore]\n" +
        "on [dbo].[orar].[FK_ore]=[dbo].[ore].[ID_ora]\n" +
        "inner join [dbo].[materii]\n" +
        "on [dbo].[ore].[FK_Materie]=[dbo].[materii].[ID_Materie]\n" +
        "inner join [dbo].[specializari]\n" +
        "on [dbo].[specializari].[ID_Specializare]=[dbo].[studenti].[FK_Specializare]\n" +
        "inner join [dbo].[facultati]\n" +
        "on [dbo].[facultati].[ID_Facultate]=[dbo].[specializari].[FK_Facultate]\n" +
        "inner join [dbo].[angajati]\n" +
        "on [dbo].[angajati].[ID_Angajat]=[dbo].[ore].[FK_Titular]\n" +
        "where [dbo].[studenti].[Nume]='"+nume+"' and [dbo].[studenti].[Prenume]='"+prenume+"'\n";

        return executeQuery(query);
    }

    public ResultSet getStudentSchedule(String nume, String prenume) throws SQLException {
        String query= "SELECT [dbo].[orar].[Ora],[dbo].[grupe_studiu].[denumire_grupa],[dbo].[materii].[NumeMaterie],[dbo].[sali_de_clasa].[Denumire],[dbo].[studenti].[An_de_Studiu], [dbo].[specializari].[Denumire] as 'Specializare',[dbo].[facultati].[Denumire] as 'Facultate',[dbo].[angajati].[Nume] +' '+[dbo].[angajati].[Prenume] as 'Profesor'\n" +
        "FROM [dbo].[orar]\n" +
        "inner join [dbo].[grupe_studiu]\n" +
        "on [dbo].[grupe_studiu].[ID_Grupa]=[dbo].[orar].[FK_Grupa]\n" +
        "inner join [dbo].[studenti]\n" +
        "on [dbo].[grupe_studiu].[ID_Grupa]=[dbo].[studenti].[FK_Grupa]\n" +
        "inner join [dbo].[ore]\n" +
        "on [dbo].[orar].[FK_ore]=[dbo].[ore].[ID_ora]\n" +
        "inner join [dbo].[materii]\n" +
        "on [dbo].[ore].[FK_Materie]=[dbo].[materii].[ID_Materie]\n" +
        "inner join [dbo].[sali_de_clasa]\n" +
        "on [dbo].[orar].[FK_Sala]=[dbo].[sali_de_clasa].[ID_Sala]\n" +
        "inner join [dbo].[specializari]\n" +
        "on [dbo].[specializari].[ID_Specializare]=[dbo].[studenti].[FK_Specializare]\n" +
        "inner join [dbo].[facultati]\n" +
        "on [dbo].[facultati].[ID_Facultate]=[dbo].[specializari].[FK_Facultate]\n" +
        "inner join [dbo].[angajati]\n" +
        "on [dbo].[angajati].[ID_Angajat]=[dbo].[ore].[FK_Titular]\n" +
        "where [dbo].[studenti].[Nume]='"+nume+"' and [dbo].[studenti].[Prenume]='"+prenume+"'\n";

        return executeQuery(query);
    }

    public ResultSet uploadReport(int FKTipRaport, String data, int FK_User, int FK_Supervizor) throws SQLException {
        String query= "Insert into [dbo].[rapoarte]([FK_TipRaport],[Data_calendar],[FK_User],[FK_Supervizor]) values("
                +FKTipRaport+", CAST(N'"+data+"' AS DateTime), "+FK_User+", "+FK_Supervizor+")";

        return executeQuery(query);
    }

    public Connection getConnection() {
        return con;
    }
}
