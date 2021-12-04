package mta.universitate.Model;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public Connection getConnection() {
        return con;
    }

    private static String getHash(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public boolean execute(String query) {
        try {
            if(con!=null){
                Statement statement = con.createStatement();
                statement.execute(query);
                return true;
            }
            else
                System.out.println("Conexiunea nu s-a putut face");
        }
        catch (Exception e) {
            System.out.printf("Exception caught!");
            //e.printStackTrace();
        }
        return false;
    }

    // Returns a ResultSet
    public ResultSet executeQuery(String query) {
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
            //e.printStackTrace();
        }
        return null;
    }

    public ResultSet getStudentInfobyName(String nume, String prenume) throws SQLException {
        String query = "SELECT [dbo].[studenti].[Nume] + ' ' + [dbo].[studenti].[Prenume] as 'Student',[dbo].[studenti].[ID_Student],[dbo].[studenti].[An_de_Studiu],[dbo].[studenti].[Solda],[dbo].[grupe_studiu].[denumire_grupa], [dbo].[specializari].[Denumire] as 'Specializare',[dbo].[facultati].[Denumire] as 'Facultate'\n" +
                "FROM [dbo].[studenti]\n" +
                "inner join [dbo].[grupe_studiu]\n" +
                "on [dbo].[grupe_studiu].[ID_Grupa]=[dbo].[studenti].[FK_Grupa]\n" +
                "inner join [dbo].[specializari]\n" +
                "on [dbo].[specializari].[ID_Specializare]=[dbo].[studenti].[FK_Specializare]\n" +
                "inner join [dbo].[facultati]\n" +
                "on [dbo].[facultati].[ID_Facultate]=[dbo].[specializari].[FK_Facultate]\n" +
                "where [dbo].[studenti].[Nume]='" + nume + "' and [dbo].[studenti].[Prenume]='" + prenume + "'\n";
        return executeQuery(query);
    }

    public ResultSet getStudentInfo(String id) {
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

    public ResultSet getStudentGradesbyName(String nume, String prenume) throws SQLException {
            String query = "select [dbo].[note_studenti].[Valoare], [dbo].[note_studenti].[Data_calendar], [dbo].[materii].[NumeMaterie], [dbo].[angajati].[Nume] + ' ' + [dbo].[angajati].[Prenume] as 'Profesor', [dbo].[grupe_studiu].[denumire_grupa], [dbo].[studenti].[An_de_Studiu], [dbo].[specializari].[Denumire] as 'Specializare', [dbo].[facultati].[Denumire] as 'Facultate'\n" +
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
                    "where [dbo].[studenti].[Nume]='" + nume + "' and [dbo].[studenti].[Prenume]='" + prenume + "'\n";

            return executeQuery(query);
        }


    public ResultSet getStudentGrades(String id) {
        String query="SELECT M.NumeMaterie,NS.Valoare, NS.Data_calendar " +
                "FROM studenti AS S " +
                "INNER JOIN note_studenti AS NS " +
                "ON S.ID_Student = NS.FK_Student " +
                "INNER JOIN materii AS M " +
                "ON M.ID_Materie = NS.FK_Materie " +
                "WHERE S.ID_Student = " + id ;

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

    public ResultSet getProfessorInfo(String id) {
        String query="SELECT A.ID_Angajat, A.Nume, A.Prenume, A.Salariu, U.Username " +
                "FROM angajati AS A " +
                "INNER JOIN functii AS F " +
                "ON A.FK_Functia = F.ID_Functie " +
                "INNER JOIN utilizatori AS U " +
                "ON A.FK_ID_User = U.ID_User " +
                "WHERE a.ID_Angajat = " + id;
        return executeQuery(query);
    }

    public ResultSet getProfessorClasses(String id) {
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

    public String createStudent(Student S) throws NoSuchAlgorithmException, SQLException {
        String firstName = S.getName(), lastName = S.getSurname();
        int studyYear = S.getStudyYear().getYear();
        int idGroup = S.getStudyGroup().getId();
        Major idSpecialization = S.getMajor();
        int income = S.getIncome();

        String username = firstName.toLowerCase(Locale.ROOT) + "." + lastName.toLowerCase(Locale.ROOT)+"@mta.ro";
        String password = "student2021";

        String hashedPassword = getHash(password);
        
        
        int tipCont=100;

        //Adaugare cont student
        String query = "INSERT INTO utilizatori "+
        "VALUES('" + username + "','" + hashedPassword + "'," + String.valueOf(tipCont) + ")";
        execute(query);

        //Preluare ID cont student
        query = "SELECT ID_User as ID\n" +
                "FROM utilizatori\n" +
                "WHERE Username = '"+ username + "' AND Password ='"+ hashedPassword + "'";

        ResultSet result = executeQuery(query);

        if(result == null)
        {
            System.out.print("Eroare la inserare student");
            return "Error";
        }
        result.next();
        String idUser = result.getString("ID");


        //Adaugare student
        query = "INSERT INTO studenti\n" +
                "VALUES('" + lastName + "','" + firstName + "'," + String.valueOf(idSpecialization)
                + "," + String.valueOf(idGroup) + "," + String.valueOf(income) + ","
                + String.valueOf(studyYear) + ","+ idUser +")";

        execute(query);

        return "OK";
    }

    public String deleteStudent(String id) throws SQLException {
        String query = "DELETE FROM note_studenti\n" +
                "WHERE FK_Student = " + id;

        execute(query);

        query="SELECT U.ID_User, S.ID_Student\n" +
                "FROM studenti AS S\n" +
                "INNER JOIN utilizatori AS U\n" +
                "ON S.FK_ID_User = U.ID_User\n" +
                "WHERE S.ID_Student = " + id;

        ResultSet result = executeQuery(query);

        if(result == null)
        {
            System.out.print("Eroare la inserare student");
            return "Error";
        }

        ResultSetMetaData metadata = result.getMetaData();
        String idUser = result.getString(1);

        query = "DELETE FROM studenti\n" +
                "WHERE ID_Student = " + id;
        execute(query);

        query = "DELETE FROM utilizatori\n" +
                "WHERE ID_User = " + id;

        execute(query);

        return "OK";
    }



    public String createTeacher(Professor P) throws NoSuchAlgorithmException, SQLException {
        String firstName = P.getName(), lastName = P.getSurname();
        int salary = P.getSalary();
        int tipCont = 200;

        String username = firstName.toLowerCase(Locale.ROOT) + "." + lastName.toLowerCase(Locale.ROOT)+"@mta.ro";
        String password = "profesor2021";
        String hashedPassword = getHash(password);

        //Adaugare cont profesor
        String query = "INSERT INTO utilizatori "+
                "VALUES('" + username + "','" + hashedPassword + "'," + String.valueOf(tipCont) + ")";
        execute(query);

        //Preluare ID cont profesor
        query = "SELECT ID_User as ID\n" +
                "FROM utilizatori\n" +
                "WHERE Username = '"+ username + "' AND Password ='"+ hashedPassword + "'";

        ResultSet result = executeQuery(query);

        if(result == null)
        {
            System.out.print("Eroare la inserare profesor");
            return "Error";
        }

        result.next();
        String idUser = result.getString("ID");


        //Adaugare student
        query = "INSERT INTO angajati\n" +
                "VALUES(7,'" + lastName + "','" + firstName + "'," + String.valueOf(salary)
                + "," + String.valueOf(idUser) + ")";

        execute(query);

        return "OK";
    }



    public boolean createEmployee(String name, String surname, String password, String role, String position, int salary) throws NoSuchAlgorithmException, SQLException {
        // Returns: TRUE on success, FALSE on fail

        String username = name.toLowerCase(Locale.ROOT) + "." + surname.toLowerCase(Locale.ROOT)+"@mta.ro";
        String hashedPassword = getHash(password);

        // First creates the User
        if (this.createUser(username, hashedPassword, role))
        {
            int userID = getUserID(username);
            int positionID = getPositionID(position);
            if(this.execute(String.format("INSERT INTO angajati(Nume, Prenume, FK_Functia, Salariu, FK_ID_User) VALUES ('%s', '%s', %d, %d, %d)", name, surname, positionID, salary, userID)))
                return true;
            else
                this.deleteUser(username);
        }

        return false;
    }

    public boolean deleteEmployee(String name, String surname) {
        // Returns: TRUE on success, FALSE on fail
        String username = name.toLowerCase(Locale.ROOT) + "." + surname.toLowerCase(Locale.ROOT)+"@mta.ro";

        if(this.execute(String.format("DELETE FROM angajati WHERE Nume = '%s' AND Prenume = '%s'", name, surname))){
            if(this.deleteUser(username)){
                return true;
            }
        }

        return false;
    }


    public boolean createUser(String username, String password, String role) {
        try
        {
            int role_id = getRoleID(role);
            if(this.execute(String.format("INSERT INTO utilizatori (Username, Password, FK_TipCont) VALUES ('%s', '%s', %d)", username, password, role_id)))
                return true;

            return false;
        }
        catch (Exception exc)
        {
            return false;
        }

    }

    public boolean deleteUser(String username) {
        try
        {
            if(this.execute(String.format("DELETE FROM utilizatori WHERE Username = '%s'", username)))
                return true;

            return false;
        }
        catch (Exception exc)
        {
            return false;
        }

    }

    public boolean resetUserPassword(String username, String new_password){
        // Returns: TRUE on success, FALSE on fail
        this.executeQuery(String.format("UPDATE utilizatori SET Password = %s WHERE Username = %s", new_password, username));
        return true;
    }


    public int getRoleID(String role) throws SQLException, SQLServerException{
        ResultSet rs = this.executeQuery(String.format("SELECT ID_TipCont FROM tipuri_cont AS TC WHERE TC.Denumire_Cont = '%s'", role));
        rs.next();
        return rs.getInt("ID_TipCont");
    }

    public int getPositionID(String position) throws SQLException, SQLServerException{
        ResultSet rs = this.executeQuery(String.format("SELECT ID_Functie FROM functii AS F WHERE F.Denumire = '%s'", position));
        rs.next();
        return rs.getInt("ID_Functie");
    }

    public int getUserID(String username) throws SQLException, SQLServerException{
        ResultSet rs = this.executeQuery(String.format("SELECT ID_User FROM utilizatori AS U WHERE U.Username = '%s'", username));
        rs.next();
        return rs.getInt("ID_User");
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
