package mta.universitate.Model;
import mta.universitate.Utils.Hasher;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.jetbrains.annotations.NotNull;


import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

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
            e.printStackTrace();
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


    public boolean add(Student S){
        try{
            if (this.add(S.getUser()))
            {
                S.getUser().setId(this.getUserID(S.getUser().getUsername()));

                if (this.execute(String.format("INSERT INTO Students(Name, Surname, Major, StudyGroup, Pay, User_ID) " +
                                "VALUES('%s','%s', %d, %d, %d, %d)",
                        S.getName(), S.getSurname(), S.getMajor().getId(), S.getStudyGroup().getId(),
                        S.getIncome(), S.getUser().getId())))
                    return true;
            }
            else {
                this.delete(S.getUser());
            }
        }
        catch (SQLServerException e)
        {
            return false;
        }
        catch (SQLException e)
        {
            return false;
        }

        return false;


    }
    public boolean add(Employee E){
        try{
            if (this.add(E.getUser()))
            {
                E.getUser().setId(this.getUserID(E.getUser().getUsername()));

                if (this.execute(String.format("INSERT INTO Employees(Position_ID, Name, Surname, Salary, User_ID) " +
                                "VALUES ( %d, '%s', '%s', %d, %d)",
                        E.getPosition().getId(), E.getName(), E.getSurname(), E.getSalary(),
                        E.getUser().getId())))
                    return true;
            }
            else {
                this.delete(E.getUser());
            }
        }
        catch (SQLServerException e)
        {
            return false;
        }
        catch (SQLException e)
        {
            return false;
        }

        return false;
    }
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
        if (this.execute(String.format("INSERT INTO Users(Username, Password, User_Role) VALUES" +
                "('%s', '%s', %d)", U.getUsername(), U.getPassword(), U.getRole().getId())))
            return true;
        return false;
    }

    public boolean delete(Student S){

        if (this.execute(String.format("DELETE FROM Students WHERE ID = %d", S.getId())))
            if (this.delete(S.getUser())) {
                return true;
        }

        return false;
    }
    public boolean delete(Employee E){
        if (this.execute(String.format("DELETE FROM Employees WHERE ID = %d", E.getId())))
                if (this.delete(E.getUser()))
                    return true;

        return false;
    }
    // public boolean delete(Course C){ return true;}
    // public boolean delete(Major M){ return true;}
    public boolean delete(Feature F) {
        if (this.execute(String.format("DELETE FROM Features WHERE ID = %d", F.getId())))
            return true;
        return false;
    }
    public boolean delete(Request R){
        if (this.execute(String.format("DELETE FROM Requests WHERE ID = %d", R.getId())))
            return true;
        return false;
    }
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

    public Student get(Student S){
        ResultSet rs = this.executeQuery(String.format("SELECT * FROM Students WHERE ID = %d", S.getId()));

        try{

            Student to_return = new Student();

            rs.next();

            to_return.setId(rs.getInt("ID"));
            to_return.setName(rs.getString("Name"));
            to_return.setSurname(rs.getString("Surname"));
            to_return.setIncome(rs.getInt("Pay"));
            to_return.setUser(User.fromDB(rs.getInt("User_ID")));
            to_return.setStudyGroup(StudyGroup.fromDB(rs.getInt("StudyGroup")));
            to_return.setMajor(Major.fromDB(rs.getInt("Major")));

            return to_return;
        }
        catch (SQLException e) {
            return null;
        }

    }
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

            U.setId(rs.getInt("User_ID"));
            U = this.get(U);
            to_return.setUser(U);

            P.setId(rs.getInt("Position_ID"));
            P = this.get(P);
            to_return.setPosition(P);


            return to_return;
        }
        catch (SQLException e) {
            return null;
        }

    }
    public Course get(Course C){
        Employee E=new Employee();

        Course to_return = new Course();
        ResultSet rs = this.executeQuery(String.format("SELECT * FROM Courses WHERE ID = %d", C.getId()));

        try{
            rs.next();
            to_return.setId(rs.getInt("ID"));
            to_return.setCredits(rs.getInt("Credits"));
            to_return.setName(rs.getString("Name"));

            E.setId(rs.getInt("Professor"));
            E=this.get(E);

            Professor prof=new Professor(E);
            to_return.setProfessor(prof);

            return to_return;
        }
        catch (SQLException e) {
            return null;
        }
    }
    public Major get(Major M){
        Major to_return = new Major();
        ResultSet rs = this.executeQuery(String.format("SELECT * FROM Majors WHERE ID = %d", M.getId()));

        try{
            rs.next();
            to_return.setId(rs.getInt("ID"));
            to_return.setName(rs.getString("Name"));
            to_return.setFaculty(Faculty.fromDB(rs.getInt("Faculty")));
            to_return.setSecretary(Secretary.fromDB(rs.getInt("Secretary")));

            return to_return;
        }
        catch (SQLException e) {
            return null;
        }
    }
    public Feature get(Feature F) {
        Feature to_return = new Feature();
        ResultSet rs = this.executeQuery(String.format("SELECT * FROM Features WHERE ID = %d", F.getId()));

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
    public Request get(Request R){
        ResultSet rs = this.executeQuery(String.format("SELECT * FROM Requests WHERE ID = %d", R.getId()));

        try{
            User U = new User();
            Employee E=new Employee();
            RequestType T=new RequestType();

            Request to_return = new Request();

            rs.next();
            to_return.setId(rs.getInt("ID"));
            to_return.setDate(rs.getDate("[Date]"));
            to_return.setApproved(rs.getBoolean("Approved"));

            U.setId(rs.getInt("Issuer"));
            U = this.get(U);
            to_return.setIssuer(U);

            E.setId(rs.getInt("Supervisor"));
            E = this.get(E);
            to_return.setSupervisor(E);

            T.setId(rs.getInt("Kind"));
            T = this.get(T);
            to_return.setKind(T);

            return to_return;
        }
        catch (SQLException e) {
            return null;
        }
    }
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
    public Faculty get(Faculty F) {
        Faculty to_return = new Faculty();
        ResultSet rs = this.executeQuery(String.format("SELECT * FROM Faculties WHERE ID = %d", F.getId()));

        try{
            rs.next();
            to_return.setId(rs.getInt("ID"));
            to_return.setName(rs.getString("Name"));

            return to_return;
        }
        catch (SQLException e) {
            return null;
        }
    }
    public Document get(Document D) {
        User U = new User();

        Document to_return = new Document();
        ResultSet rs = this.executeQuery(String.format("SELECT * FROM Documents WHERE ID = %d", D.getId()));

        try{
            rs.next();
            to_return.setId(rs.getInt("ID"));
            to_return.setTitle(rs.getString("Title"));
            to_return.setContent(rs.getString("Content"));

            U.setId(rs.getInt("[User]"));
            U=this.get(U);
            to_return.setUser(U);

            return to_return;
        }
        catch (SQLException e) {
            return null;
        }
    }
    public Classroom get(Classroom C) {

        Classroom to_return = new Classroom();
        ResultSet rs = this.executeQuery(String.format("SELECT * FROM Classrooms WHERE ID = %d", C.getId()));

        try{
            rs.next();

            to_return.setId(rs.getInt("ID"));
            to_return.setName(rs.getString("Name"));
            to_return.setCapacity(rs.getInt("Capacity"));
            to_return.setKind(rs.getBoolean("Kind"));

            List<Feature> features = new ArrayList<Feature>();
            rs = this.executeQuery(String.format("SELECT * FROM ClassroomsFeatures WHERE Classroom = %d", C.getId()));

            while(rs.next())
                features.add(Feature.fromDB(rs.getInt("Feature")));

            to_return.setFeatures(features);
            return to_return;
        }
        catch (SQLException e) {}

        return null;

    }
    public Grade get(Grade G) {
        Course C=new Course();
        Student S=new Student();

        Grade to_return = new Grade();
        ResultSet rs = this.executeQuery(String.format("SELECT * FROM Grades WHERE ID = %d", G.getId()));

        try{
            rs.next();
            to_return.setId(rs.getInt("ID"));
            to_return.setValue(rs.getInt("Value"));
            to_return.setDate(rs.getDate("[Date]"));

            C.setId(rs.getInt("Course"));
            C=this.get(C);
            to_return.setCourse(C);

            S.setId(rs.getInt("Student"));
            S=this.get(S);
            to_return.setStudent(S);

            return to_return;
        }
        catch (SQLException e) {
            return null;
        }
    }
    public StudyGroup get(StudyGroup SG) {
        StudyGroup to_return = new StudyGroup();
        ResultSet rs = this.executeQuery(String.format("SELECT * FROM StudyGroups WHERE ID = %d", SG.getId()));

        try{
            rs.next();
            to_return.setId(rs.getInt("ID"));
            to_return.setName(rs.getString("Name"));
            to_return.setStudy_year(rs.getInt("StudyYear"));
            to_return.setMentor(Professor.fromDB(rs.getInt("Mentor")));

            return to_return;
        }
        catch (SQLException e) {
            return null;
        }
    }
    public Module get(Module M){
        Course C=new Course();
        Employee E=new Employee();

        Module to_return = new Module();
        ResultSet rs = this.executeQuery(String.format("SELECT * FROM Modules WHERE ID = %d", M.getId()));

        try{
            rs.next();
            to_return.setId(rs.getInt("ID"));
            to_return.setKind(rs.getString("Kind"));

            C.setId(rs.getInt("Course"));
            C=this.get(C);
            to_return.setCourse(C);

            E.setId(rs.getInt("Professor"));
            E=this.get(E);

            Professor P=new Professor(E);
            to_return.setProfessor(P);

            return to_return;
        }
        catch (SQLException e) {
            return null;
        }
    }
    public Schedule get(Schedule S) {
        ResultSet rs = this.executeQuery(String.format("SELECT * FROM Schedule WHERE ID = %d", S.getId()));

        try{
            Classroom C = new Classroom();
            StudyGroup SG = new StudyGroup();
            Module M=new Module();

            Schedule to_return = new Schedule();

            rs.next();
            to_return.setId(rs.getInt("ID"));
            to_return.setDate(rs.getDate("[Time]"));

            C.setId(rs.getInt("Classroom"));
            C = this.get(C);
            to_return.setClassroom(C);

            SG.setId(rs.getInt("StudyGroup"));
            SG = this.get(SG);
            to_return.setStudy_group(SG);

            M.setId(rs.getInt("Module"));
            M = this.get(M);
            to_return.setModule(M);

            return to_return;
        }
        catch (SQLException e) {
            return null;
        }
    }
    public RequestType get(RequestType RT) {
        ResultSet rs = this.executeQuery(String.format("SELECT * FROM RequestTypes WHERE ID = %d", RT.getId()));

        try{
            RequestType to_return = new RequestType();

            rs.next();
            to_return.setId(rs.getInt("ID"));
            to_return.setDescription(rs.getString("Kind"));

            return to_return;
        }
        catch (SQLException e) {
            return null;
        }
    }
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
            R.setId(rs.getInt("User_Role"));
            R = this.get(R);
            to_return.setRole(R);

            return to_return;
        }
        catch (SQLException e) {
            return null;
        }
    }

    public boolean update(User U){
        if (this.execute(String.format("UPDATE Users SET Username = '%s', Password = '%s', User_Role = %d WHERE ID = %d", U.getUsername(), U.getPassword(), U.getRole().getId(), U.getId())))
            return true;
        return false;
    }

    //


    public int getRoleID(String role) throws SQLException, SQLServerException{
        ResultSet rs = this.executeQuery(String.format("SELECT ID FROM Roles WHERE Description = '%s'", role));
        rs.next();
        return rs.getInt("ID");
    }
    public int getPositionID(String position) throws SQLException, SQLServerException{
        ResultSet rs = this.executeQuery(String.format("SELECT ID FROM Positions AS F WHERE Description = '%s'", position));
        rs.next();
        return rs.getInt("ID");
    }
    public int getUserID(String username) throws SQLException, SQLServerException{
        ResultSet rs = this.executeQuery(String.format("SELECT ID FROM Users AS U WHERE U.Username = '%s'", username));
        rs.next();
        return rs.getInt("ID");
    }
    public int getEmployeeID(String name, String surname) throws  SQLException, SQLServerException{
        ResultSet rs = this.executeQuery(String.format("SELECT ID FROM Employees WHERE Name = '%s' AND Surname = '%s'", name, surname));
        rs.next();
        return rs.getInt("ID");
    }
    public int getStudentID(String name, String surname) throws  SQLException, SQLServerException{
        ResultSet rs = this.executeQuery(String.format("SELECT ID FROM Students WHERE Name = '%s' AND Surname = '%s'", name, surname));
        rs.next();
        return rs.getInt("ID");
    }
    public int getStudyGroupID(String study_group) throws SQLException, SQLServerException{
        ResultSet rs = this.executeQuery(String.format("SELECT ID FROM StudyGroups WHERE Name = '%s'", study_group));
        rs.next();
        return rs.getInt("ID");
    }
    public int getMajorID(String major) throws SQLException, SQLServerException{
        ResultSet rs = this.executeQuery(String.format("SELECT ID FROM Majors WHERE Name = '%s'", major));
        rs.next();
        return rs.getInt("ID");
    }
    public int getClassroomID(String classroom) throws SQLException, SQLServerException{
        ResultSet rs = this.executeQuery(String.format("SELECT ID FROM Classrooms WHERE Name = '%s'", classroom));
        rs.next();
        return rs.getInt("ID");
    }




    //
    public ResultSet getStudentInfoByName(String nume, String prenume) throws SQLException {
        String query="SELECT Students.Name + ' ' + Students.Surname as 'Student',Students.ID,StudyGroups.StudyYear,Students.Pay,StudyGroups.Name, Majors.Name as 'Specializare',Faculties.Name as 'Facultate'\n" +
                "FROM Students\n" +
                "inner join StudyGroups\n" +
                "on StudyGroups.ID=Students.StudyGroup\n" +
                "inner join Majors\n" +
                "on Majors.ID=Students.Major\n" +
                "inner join Faculties\n" +
                "on Faculties.ID=Majors.Faculty\n" +
                "where Students.Name = '"+ nume +"' and Students.Surname = '" + prenume + "'";
        return executeQuery(query);
    }

    public ResultSet getStudentInfo(String id) {
        String query="SELECT S.Name, S.Surname,G.Name,SP.Name, G.StudyYear,U.Username " +
                "FROM Students as S " +
                "INNER JOIN Majors as SP " +
                "ON S.Major = SP.ID " +
                "INNER JOIN StudyGroups as G " +
                "ON S.StudyGroup = G.ID " +
                "INNER JOIN Users AS U " +
                "ON S.User_ID = U.ID " +
                "WHERE S.ID = " + id ;

        return executeQuery(query);
    }

    public ResultSet getStudentGradesByName(String nume, String prenume) throws SQLException {
            String query = "select G.Value, G.[[Date]]], C.name, E.Name + ' ' + E.Surname as 'Profesor', SG.Name, SG.StudyYear, M.Name as 'Specializare', F.Name as 'Facultate'\n" +
                    "from Grades as G\n" +
                    "inner join Courses as C\n" +
                    "on C.ID = G.Course\n" +
                    "inner join Employees as E\n" +
                    "on E.ID=C.Professor\n" +
                    "inner join Students as S\n" +
                    "on S.ID=G.Student\n" +
                    "inner join StudyGroups as SG\n" +
                    "on SG.ID=S.StudyGroup\n" +
                    "inner join Majors as M\n" +
                    "on M.ID=S.Major\n" +
                    "inner join Faculties as F\n" +
                    "on F.ID=M.Faculty\n" +
                    "where S.Name='" + nume + "' and S.Surname='" + prenume + "'";
        return executeQuery(query);
    }

    public ResultSet getStudentGrades(String id) {
        String query="SELECT M.Name, NS.Value, NS.[[Date]]]  \n" +
                "FROM Students AS S  \n" +
                "INNER JOIN Grades AS NS  \n" +
                "ON S.ID = NS.Student  \n" +
                "INNER JOIN Courses AS M  \n" +
                "ON M.ID = NS.Course  \n" +
                "WHERE S.ID = " + id ;

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
        String query= "SELECT S.Module,SG.Name,C.Name,CR.Name,SG.StudyYear, M.Name as 'Specializare',F.Name as 'Facultate',E.Name +' '+E.Surname as 'Profesor'\n" +
                "FROM Schedule as S\n" +
                "inner join StudyGroups as SG\n" +
                "on SG.ID=S.StudyGroup\n" +
                "inner join Students as ST\n" +
                "on SG.ID=S.StudyGroup\n" +
                "inner join Modules as MO\n" +
                "on S.Module=MO.ID\n" +
                "inner join Courses as C\n" +
                "on MO.Course=C.ID\n" +
                "inner join Classrooms as CR\n" +
                "on S.Classroom=CR.ID\n" +
                "inner join Majors as M\n" +
                "on M.ID=ST.Major\n" +
                "inner join Faculties as F\n" +
                "on F.ID=M.Faculty\n" +
                "inner join Employees as E\n" +
                "on E.ID=MO.Professor\n" +
                "where ST.Name='" + nume +"' and ST.Surname='" + prenume + "'";

        return executeQuery(query);
    }

    public ResultSet uploadReport(int FKTipRaport, String data, int FK_User, int FK_Supervizor) throws SQLException {
        String query= "Insert into [dbo].[rapoarte]([FK_TipRaport],[Data_calendar],[FK_User],[FK_Supervizor]) values("
                +FKTipRaport+", CAST(N'"+data+"' AS DateTime), "+FK_User+", "+FK_Supervizor+")";

        return executeQuery(query);
    }

    public ResultSet getProfessorInfo(String id) {
        String query="SELECT A.ID, A.Name, A.Surname, A.Salary, U.Username  \n" +
                "FROM Employees AS A  \n" +
                "INNER JOIN Positions AS F  \n" +
                "ON A.Position_ID = F.ID  \n" +
                "INNER JOIN Users AS U  \n" +
                "ON A.User_ID = U.ID  \n" +
                "WHERE a.ID = " + id;
        return executeQuery(query);
    }

    public ResultSet getProfessorClasses(String id) {
        String query="SELECT E.ID,C.name,MO.Kind, ORAR.[[Time]]],G.Name,CR.Name\n" +
                "FROM Employees AS E\n" +
                "INNER JOIN Positions AS P\n" +
                "ON E.Position_ID = P.ID\n" +
                "INNER JOIN Courses AS C\n" +
                "ON E.ID = C.Professor\n" +
                "INNER JOIN Modules AS MO\n" +
                "ON C.ID=MO.Course\n" +
                "INNER JOIN Schedule AS ORAR\n" +
                "ON MO.ID = ORAR.Module\n" +
                "INNER JOIN StudyGroups AS G\n" +
                "ON ORAR.StudyGroup = G.ID\n" +
                "INNER JOIN Classrooms AS CR\n" +
                "ON ORAR.Classroom = CR.ID\n" +
                "WHERE E.ID= " +id;
        return executeQuery(query);
    }

    public ResultSet getTeacherSchedule(String nume, String prenume) throws SQLException {
        String query= "select S.Module, CR.Name as 'Sala', MO.Kind, C.Name, E.Name + ' ' + E.Surname as 'Profesor', SG.Name\n" +
                "from Schedule as S\n" +
                "inner join Classrooms as CR\n" +
                "on CR.ID=S.Classroom\n" +
                "inner join Modules as MO\n" +
                "on MO.ID=S.Module\n" +
                "inner join Courses as C\n" +
                "on C.ID=MO.Course\n" +
                "inner join Employees as E\n" +
                "on E.ID=MO.Professor\n" +
                "inner join StudyGroups as SG\n" +
                "on SG.ID=S.StudyGroup\n" +
                "inner join Positions as P\n" +
                "on E.Position_ID=P.ID\n" +
                "WHERE P.Description='Professor' and E.name='" + nume + "' and E.Surname='" + prenume + "'";


        return executeQuery(query);
    }

    public ResultSet getGroupSchedule(String grupa) throws SQLException {
      String query= "select S.Module, CR.Name as 'Sala', MO.Kind, C.Name, E.Name + ' ' + E.Surname as 'Profesor', SG.Name\n" +
              "from Schedule as S\n" +
              "inner join Classrooms as CR\n" +
              "on CR.ID=S.Classroom\n" +
              "inner join Modules as MO\n" +
              "on MO.ID=S.Module\n" +
              "inner join Courses as C\n" +
              "on C.ID=MO.Course\n" +
              "inner join Employees as E\n" +
              "on E.ID=MO.Professor\n" +
              "inner join StudyGroups as SG\n" +
              "on SG.ID=S.StudyGroup\n" +
              "where SG.Name='" + grupa + "'";

      return executeQuery(query);
    }

    public ResultSet getStudentGradess(String nume, String prenume) throws SQLException {
        String query= "select G.Value, G.[[Date]]], C.Name, E.Name + ' ' + E.Surname as 'Profesor', S.Name + ' ' + S.Surname as 'Student', S.ID, SG.Name, SG.StudyYear, M.Name as 'Specializare', F.Name as 'Facultate'\n" +
                "from Grades as G\n" +
                "inner join Courses as C\n" +
                "on C.ID = G.Course\n" +
                "inner join Employees as E\n" +
                "on E.ID = C.Professor\n" +
                "inner join Students as S\n" +
                "on S.ID=G.Student\n" +
                "inner join StudyGroups as SG\n" +
                "on SG.ID=S.StudyGroup\n" +
                "inner join Majors as M\n" +
                "on M.ID=S.Major\n" +
                "inner join Faculties as F\n" +
                "on F.ID=M.Faculty\n" +
                "where S.Name='" + nume + "' and S.Surname='" + prenume + "'";

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
        String query= "select C.Name, C.Credits, E.Name + ' ' + E.Surname as 'Profesor', M.Name as 'Specializare', F.Name as 'Facultate'\n" +
                "from Courses as C\n" +
                "inner join Employees as E\n" +
                "on E.ID=C.Professor\n" +
                "inner join MajorsCourses as MC\n" +
                "on MC.Course=C.ID\n" +
                "inner join Majors as M\n" +
                "on M.ID=MC.Major\n" +
                "inner join Faculties as F\n" +
                "on F.ID=M.Faculty \n" +
                "where M.Name='" + Name + "'";

        return executeQuery(query);
    }

    public ResultSet getSubjectsForTeacher(String surname,String name) throws SQLException {
        String query= "select C.Name, C.Credits, E.Name + ' ' + E.Surname as 'Profesor', M.Name as 'Specializare', F.Name as 'Facultate'\n" +
                "from Courses as C\n" +
                "inner join Employees as E\n" +
                "on E.ID=C.Professor\n" +
                "inner join MajorsCourses as MC\n" +
                "on MC.Course=C.ID\n" +
                "inner join Majors as M\n" +
                "on M.ID=MC.Major\n" +
                "inner join Faculties as F\n" +
                "on F.ID=M.Faculty \n" +
                "where E.Name='" + name + "' and E.Surname='" + surname + "'";

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

    public ResultSet get4thYearStudents()
    {
        String query = "SELECT S.ID AS ID, S.Name AS Nume, S.Surname AS Prenume, G.Name AS Grupa\n" +
                "FROM Students AS S \n" +
                "INNER JOIN StudyGroups AS G\n" +
                "ON S.StudyGroup = G.ID\n" +
                "WHERE G.StudyYear = 4";

        return executeQuery(query);
    }

}
