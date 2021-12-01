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

    public Connection getConnection() {
        return con;
    }

    public ResultSet getTeacherSchedule(String nume, String prenume) throws SQLException {
        String query= "SELECT [dbo].[orar].[Ora], [dbo].[grupe_studiu].[denumire_grupa], [dbo].[materii].[NumeMaterie], [dbo].[sali_de_clasa].[Denumire] as 'Sala', [dbo].[ore].[Tip_Ora], [dbo].[angajati].[Nume] + ' ' + [dbo].[angajati].[Prenume] as 'Profesor' \n" +
                        "FROM [dbo].[angajati]\n" +
                        "INNER JOIN [dbo].[functii]\n" +
                        "ON [dbo].[functii].[ID_Functie]=[dbo].[angajati].[ID_Angajat]\n" +
                        "INNER JOIN [dbo].[ore]\n" +
                        "ON [dbo].[ore].[FK_Titular]=[dbo].[angajati].[ID_Angajat]\n" +
                        "INNER JOIN [dbo].[orar]\n" +
                        "ON [dbo].[orar].[FK_Ore]=[dbo].[ore].[ID_Ora]\n" +
                        "INNER JOIN [dbo].[grupe_studiu]\n" +
                        "ON [dbo].[grupe_studiu].[ID_Grupa]=[dbo].[orar].[FK_Grupa]\n" +
                        "INNER JOIN [dbo].[materii]\n" +
                        "ON [dbo].[materii].[FK_Profesor]=[dbo].[angajati].[ID_Angajat]\n" +
                        "inner join [dbo].[sali_de_clasa]\n" +
                        "on [dbo].[sali_de_clasa].[ID_Sala]=[dbo].[orar].[FK_Sala]\n"+
                        "WHERE [dbo].[functii].[Denumire]='Profesor' and [dbo].[angajati].[Nume]='" + nume + "' and [dbo].[angajati].[Prenume]='"+prenume+"'";


        return executeQuery(query);
    }

    public ResultSet getGroupSchedule(String grupa) throws SQLException {
      String query= "select [dbo].[orar].[Ora], [dbo].[sali_de_clasa].[Denumire] as 'Sala', [dbo].[ore].[Tip_Ora], [dbo].[materii].[NumeMaterie], [dbo].[angajati].[Nume] + ' ' + [dbo].[angajati].[Prenume] as 'Profesor', [dbo].[grupe_studiu].[denumire_grupa]\n" +
                        "from [dbo].[orar]\n" +
                        "inner join [dbo].[sali_de_clasa]\n" +
                        "on [dbo].[sali_de_clasa].[ID_Sala]=[dbo].[orar].[FK_Sala]\n" +
                        "inner join [dbo].[ore]\n" +
                        "on [dbo].[ore].[ID_Ora]=[dbo].[orar].[FK_Ore]\n" +
                        "inner join [dbo].[materii]\n" +
                        "on [dbo].[materii].[ID_Materie]=[dbo].[ore].[FK_Materie]\n" +
                        "inner join [dbo].[angajati]\n" +
                        "on [dbo].[angajati].[ID_Angajat]=[dbo].[ore].[FK_Titular]\n" +
                        "inner join [dbo].[grupe_studiu]\n" +
                        "on [dbo].[grupe_studiu].[ID_Grupa]=[dbo].[orar].[FK_Grupa]\n" +
                        "where [dbo].[grupe_studiu].[denumire_grupa]='"+grupa+"'";

      return executeQuery(query);
    }

    public ResultSet getStudentsGrades() throws SQLException {
        String query= "select [dbo].[note_studenti].[Valoare], [dbo].[note_studenti].[Data_calendar], [dbo].[materii].[NumeMaterie], [dbo].[angajati].[Nume] + ' ' + [dbo].[angajati].[Prenume] as 'Profesor', [dbo].[studenti].[Nume] + ' ' + [dbo].[studenti].[Prenume] as 'Student', [dbo].[studenti].[ID_Student], [dbo].[grupe_studiu].[denumire_grupa], [dbo].[studenti].[An_de_Studiu], [dbo].[specializari].[Denumire] as 'Specializare', [dbo].[facultati].[Denumire] as 'Facultate'\n" +
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
                "on [dbo].[facultati].[ID_Facultate]=[dbo].[specializari].[FK_Facultate]\n";

        return executeQuery(query);
    }

    public ResultSet getStudentGradess(String nume, String prenume) throws SQLException {
        String query= "select [dbo].[note_studenti].[Valoare], [dbo].[note_studenti].[Data_calendar], [dbo].[materii].[NumeMaterie], [dbo].[angajati].[Nume] + ' ' + [dbo].[angajati].[Prenume] as 'Profesor', [dbo].[studenti].[Nume] + ' ' + [dbo].[studenti].[Prenume] as 'Student', [dbo].[studenti].[ID_Student], [dbo].[grupe_studiu].[denumire_grupa], [dbo].[studenti].[An_de_Studiu], [dbo].[specializari].[Denumire] as 'Specializare', [dbo].[facultati].[Denumire] as 'Facultate'\n" +
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

    public ResultSet addStudentGrade(int nota, int FKMaterie, String data, int FK_Student) throws SQLException {
        String query= "INSERT [dbo].[note_studenti] ([Valoare], [FK_Materie], [Data_calendar], [FK_Student]) VALUES " +
                        "("+nota+", "+FKMaterie+", CAST(N'"+data+"' AS DateTime), "+FK_Student+")";

        return executeQuery(query);
    }

    public ResultSet getRequests() throws SQLException {
        String query= "select [dbo].[rapoarte].[ID_Raport], [dbo].[rapoarte].[Data_calendar], [dbo].[tip_rapoarte].[Tip_Raport], [dbo].[utilizatori].[Username], [dbo].[angajati].[Nume] + ' ' + [dbo].[angajati].[Prenume] as 'Supervizor', [dbo].[functii].[Denumire] as 'Functie supervizor'\n" +
                        "from [dbo].[rapoarte]\n" +
                        "inner join [dbo].[tip_rapoarte]\n" +
                        "on [dbo].[tip_rapoarte].[ID_TipRaport]=[dbo].[rapoarte].[FK_TipRaport]\n" +
                        "inner join [dbo].[utilizatori]\n" +
                        "on [dbo].[utilizatori].[ID_User]=[dbo].[rapoarte].[FK_User]\n" +
                        "inner join [dbo].[angajati]\n" +
                        "on [dbo].[angajati].[ID_Angajat]=[dbo].[rapoarte].[FK_Supervizor]\n" +
                        "inner join [dbo].[functii]\n" +
                        "on [dbo].[functii].[ID_Functie]=[dbo].[angajati].[FK_Functia]\n" +
                        "where [dbo].[tip_rapoarte].[Tip_Raport]='Adeverinta Salariat'";

      return executeQuery(query);
    }

    public ResultSet askForTeacherSalaryCertificate(int tipRaport, String data, int FK_User,int FK_Supervizor) throws SQLException {
        String query= "INSERT [dbo].[rapoarte] ([FK_TipRaport], [Data_calendar], [FK_User], [FK_Supervizor]) " +
                        "VALUES ("+tipRaport+", CAST(N'"+data+"' AS DateTime), "+FK_User+", "+FK_Supervizor+")";

        return executeQuery(query);
    }
}
