package mta.universitate.Model;
import mta.universitate.Utils.Hasher;

import com.microsoft.sqlserver.jdbc.SQLServerException;


import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;

public class Database {
    private static Database dbObject;
    private Connection con;
    private Database() {
        String ConnectionUrl="jdbc:sqlserver://pituserver.database.windows.net:1433;database=MTA;user=pituAdmin@pituserver;password=1q2w3e4rT;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
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

    // public boolean add(Student S){return true;}
    // public boolean add(Employee E){ return true;}
    // public boolean add(Course C){ return true;}
    // public boolean add(Major M){ return true;}
    // public boolean add(Feature F) {return true;}
    // public boolean add(Report R){ return true;}
    public boolean add(Role R){
        if(this.execute(String.format("INSERT INTO Roles(ID, Description) VALUES (%d,'%s')", R.getId(), R.getDescription())))
            return true;
        return false;
    }
    public boolean add(User U){
        if (this.execute(String.format("INSERT INTO Users(Username, Password, [Role]) VALUES" +
                "('%s', '%s', %d)", U.getUsername(), U.getPassword(), U.getRole().getId())))
            return true;
        return false;
    }

    // public boolean delete(Student S){ return true;}
    // public boolean delete(Employee E){ return true;}
    // public boolean delete(Course C){ return true;}
    // public boolean delete(Major M){ return true;}
    // public boolean delete(Feature F) {return true;}
    // public boolean delete(Report R){ return true;}
    public boolean delete(Role R){
        if (this.execute(String.format("DELETE FROM Roles WHERE ID = %d", R.getId())))
            return true;
        return false;
    }
    public boolean delete(User U){
        if (this.execute(String.format("DELETE FROM Users WHERE ID = %d", U.getId())))
            return true;
        return false;
    }

    public Student get(Student S){ return null;}
    public Employee get(Employee E){
        ResultSet rs = this.executeQuery(String.format("SELECT * FROM Employees WHERE ID = %d", E.getId()));

        try{
            User U = new User();
            Position P = new Position();

            Employee to_return = new Employee();

            rs.next();
            to_return.setId(rs.getInt("ID"));
            to_return.setName(rs.getString("Name"));
            to_return.setSurname(rs.getString("Surname"));
            to_return.setSalary(rs.getInt("Salary"));

            U.setId(rs.getInt("[User]"));
            U = this.get(U);
            to_return.setUser(U);

            P.setId(rs.getInt("[Position]"));
            P = this.get(P);
            to_return.setPosition(P);


            return to_return;
        }
        catch (SQLException e) {
            return null;
        }

    }
    public Course get(Course C){ return null;}
    public Major get(Major M){ return null;}
    public Feature get(Feature F) {return null;}
    public Request get(Request R){ return null;}
    public Position get(Position P) {
        Position to_return = new Position();
        ResultSet rs = this.executeQuery(String.format("SELECT * FROM Positions WHERE ID = %d", P.getId()));

        try{
            rs.next();
            to_return.setId(rs.getInt("ID"));
            to_return.setDescription(rs.getString("Description"));

            return to_return;
        }
        catch (SQLException e) {
            return null;
        }
    }
    public Faculty get(Faculty F) {return null;}
    public Document get(Document D) {return null;}
    public Classroom get(Classroom C) {return null;}
    public Grade get(Grade G) {return null;}
    public StudyGroup get(StudyGroup SG) {return null;}
    public Schedule get(Schedule S) {return null;}
    public RequestType get(RequestType RT) {return null;}
    public Role get(Role R){
        Role to_return = new Role();
        ResultSet rs = this.executeQuery(String.format("SELECT * FROM Roles WHERE ID = %d", R.getId()));

        try{
            rs.next();
            to_return.setId(rs.getInt("ID"));
            to_return.setDescription(rs.getString("Description"));

            return to_return;
        }
        catch (SQLException e) {
            return null;
        }

    }
    public User get(User U) {
        User to_return = new User();
        ResultSet rs = this.executeQuery(String.format("SELECT * FROM Users WHERE ID = %d", U.getId()));

        try{
            rs.next();
            to_return.setId(rs.getInt("ID"));
            to_return.setUsername(rs.getString("Username"));
            to_return.setPassword(rs.getString("Password"));


            Role R = new Role();
            R.setId(rs.getInt("[Role]"));
            R = this.get(R);
            to_return.setRole(R);

            return to_return;
        }
        catch (SQLException e) {
            return null;
        }
    }





    //
    public ResultSet getStudentInfoByName(String nume, String prenume) throws SQLException {
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

    public ResultSet getStudentGradesByName(String nume, String prenume) throws SQLException {
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


    public boolean createStudent(String name, String surname, String password, String major, String study_group, int income, int study_year) throws NoSuchAlgorithmException, SQLException {
        String username = name.toLowerCase(Locale.ROOT) + "." + surname.toLowerCase(Locale.ROOT)+"@mta.ro";
        String hashedPassword = Hasher.getHash(password);


        if(createUser(username, hashedPassword, "student"))
        {
            int userID = getUserID("username");
            int majorID = getMajorID(major);
            int studyGroupID = getStudyGroupID(study_group);

            if (this.execute(String.format("INSERT INTO studenti VALUES('%s','%s', %d, %d, %d, %d)", name, surname, majorID, studyGroupID, income, study_year, userID)))
                return true;
        }

        return false;
    }

    public boolean deleteStudent(String name, String surname) throws SQLException {
        // TODO: Update constraint to cascade delete

        String username = name.toLowerCase(Locale.ROOT) + "." + surname.toLowerCase(Locale.ROOT)+"@mta.ro";

        if(this.execute(String.format("DELETE FROM studenti WHERE Nume = '%s' AND Prenume = '%s'", name, surname))){
            if(this.deleteUser(username)){
                return true;
            }
        }

        return false;
    }


    public boolean createEmployee(String name, String surname, String password, String role, String position, int salary) throws NoSuchAlgorithmException, SQLException {
        // Returns: TRUE on success, FALSE on fail

        String username = name.toLowerCase(Locale.ROOT) + "." + surname.toLowerCase(Locale.ROOT)+"@mta.ro";
        String hashedPassword = Hasher.getHash(password);

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

    public int getStudyGroupID(String study_group) throws SQLException, SQLServerException{
        ResultSet rs = this.executeQuery(String.format("SELECT ID_Grupa FROM grupe_studiu AS GS WHERE GS.denumire_grupa = '%s'", study_group));
        rs.next();
        return rs.getInt("IG_Grupa");
    }

    public int getMajorID(String major) throws SQLException, SQLServerException{
        ResultSet rs = this.executeQuery(String.format("SELECT ID_Specializare FROM specializari AS S WHERE S.Denumire = '%s'", major));
        rs.next();
        return rs.getInt("ID_Specializare");
    }


    public ResultSet getTeacherSchedule(String nume, String prenume) throws SQLException {
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
                        "inner join [dbo].[functii]\n" +
                        "on [dbo].[functii].[ID_Functie]=[dbo].[angajati].[FK_Functia]\n"+
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

    public ResultSet askForTeacherSalaryCertificate(int tipRaport, String data, int FK_User,int FK_Supervizor) throws SQLException {
        String query= "INSERT [dbo].[rapoarte] ([FK_TipRaport], [Data_calendar], [FK_User], [FK_Supervizor]) " +
                        "VALUES ("+tipRaport+", CAST(N'"+data+"' AS DateTime), "+FK_User+", "+FK_Supervizor+")";

        return executeQuery(query);
    }

    public ResultSet getSubjects() throws SQLException {
        String query= "select [dbo].[materii].[NumeMaterie], [dbo].[materii].[Nr_Credite], [dbo].[angajati].[Nume] + ' ' + [dbo].[angajati].[Prenume] as 'Profesor', [dbo].[specializari].[Denumire] as 'Specializare', [dbo].[facultati].[Denumire] as 'Facultate'\n" +
                "from [dbo].[materii]\n" +
                "inner join [dbo].[angajati]\n" +
                "on [dbo].[angajati].[ID_Angajat]=[dbo].[materii].[FK_Profesor]\n" +
                "inner join [dbo].[specializari_materii]\n" +
                "on [dbo].[specializari_materii].[FK_Materie]=[dbo].[materii].[ID_Materie]\n" +
                "inner join [dbo].[specializari]\n" +
                "on [dbo].[specializari].[ID_Specializare]=[dbo].[specializari_materii].[FK_Specializare]\n" +
                "inner join [dbo].[facultati]\n" +
                "on [dbo].[facultati].[ID_Facultate]=[dbo].[specializari].[FK_Facultate]";

        return executeQuery(query);
    }

    public ResultSet getSubjectsbySpecialization(String Name) throws SQLException {
        String query= "select [dbo].[materii].[NumeMaterie], [dbo].[materii].[Nr_Credite], [dbo].[angajati].[Nume] + ' ' + [dbo].[angajati].[Prenume] as 'Profesor', [dbo].[specializari].[Denumire] as 'Specializare', [dbo].[facultati].[Denumire] as 'Facultate'\n" +
                "from [dbo].[materii]\n" +
                "inner join [dbo].[angajati]\n" +
                "on [dbo].[angajati].[ID_Angajat]=[dbo].[materii].[FK_Profesor]\n" +
                "inner join [dbo].[specializari_materii]\n" +
                "on [dbo].[specializari_materii].[FK_Materie]=[dbo].[materii].[ID_Materie]\n" +
                "inner join [dbo].[specializari]\n" +
                "on [dbo].[specializari].[ID_Specializare]=[dbo].[specializari_materii].[FK_Specializare]\n" +
                "inner join [dbo].[facultati]\n" +
                "on [dbo].[facultati].[ID_Facultate]=[dbo].[specializari].[FK_Facultate]" +
                "where [dbo].[specializari].[Denumire]='"+Name+"'";

        return executeQuery(query);
    }

    public ResultSet getSubjectsForTeacher(String surname,String name) throws SQLException {
        String query= "select [dbo].[materii].[NumeMaterie], [dbo].[materii].[Nr_Credite], [dbo].[angajati].[Nume] + ' ' + [dbo].[angajati].[Prenume] as 'Profesor', [dbo].[specializari].[Denumire] as 'Specializare', [dbo].[facultati].[Denumire] as 'Facultate'\n" +
                "from [dbo].[materii]\n" +
                "inner join [dbo].[angajati]\n" +
                "on [dbo].[angajati].[ID_Angajat]=[dbo].[materii].[FK_Profesor]\n" +
                "inner join [dbo].[specializari_materii]\n" +
                "on [dbo].[specializari_materii].[FK_Materie]=[dbo].[materii].[ID_Materie]\n" +
                "inner join [dbo].[specializari]\n" +
                "on [dbo].[specializari].[ID_Specializare]=[dbo].[specializari_materii].[FK_Specializare]\n" +
                "inner join [dbo].[facultati]\n" +
                "on [dbo].[facultati].[ID_Facultate]=[dbo].[specializari].[FK_Facultate]" +
                "where [dbo].[angajati].[Nume]='"+surname+"' and [dbo].[angajati].[Prenume]='"+name+"'";

        return executeQuery(query);
    }

    public ResultSet addSubject(int credits, int IDTeacher, String name, String specialization) throws SQLException {
        String query1= "INSERT into [dbo].[materii] ([dbo].[materii].[Nr_Credite], [dbo].[materii].[FK_Profesor], [dbo].[materii].[NumeMaterie]) VALUES " +
                "("+credits+", "+IDTeacher+", '"+name+"')";

        executeQuery(query1);

        String query2="select ID_Materie\n" +
                      "from materii\n" +
                     "where NumeMaterie='"+name+"'";

        ResultSet result = executeQuery(query2);

        if(result == null)
        {
            return result;
        }
        result.next();
        String idMaterie = result.getString("ID_Materie");

        String query3="select ID_Specializare\n" +
                "from specializari\n" +
                "where Denumire='"+specialization+"'";

        ResultSet result2 = executeQuery(query3);

        if(result2 == null)
        {
            return result2;
        }
        result2.next();
        String idSpecializare = result2.getString("ID_Specializare");


        String query= "INSERT into [dbo].[specializari_materii] ([dbo].[specializari_materii].[FK_Specializare], [dbo].[specializari_materii].[FK_Materie]) VALUES " +
                "("+idSpecializare+", "+idMaterie+")";

        return executeQuery(query);
    }
}
