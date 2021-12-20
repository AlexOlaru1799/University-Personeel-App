package mta.universitate.Model;

import com.microsoft.sqlserver.jdbc.SQLServerException;


import javax.print.Doc;
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
    public boolean add(Course C){
        if (this.execute(String.format("INSERT INTO Courses(Credits, Professor, Name) VALUES" +
                "(%d, '%s', '%s')", C.getCredits(), C.getProfessor().getId(), C.getName())))
            return true;
        return false;
    }
    // public boolean add(Major M){ return true;}
    // public boolean add(Feature F) {return true;}

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
    public boolean add(Document D) {
        if(this.execute(String.format("INSERT INTO Documents(Title, Content, [[User]]]) VALUES" +
                "('%s','%s',%d)", D.getTitle(), D.getContent(), D.getUser().getId())))
            return true;

        return false;
    }
    public boolean add(Request R) {
        if(this.execute(String.format("INSERT INTO Requests(Kind,[[Date]]],Issuer,Supervisor,Approved)   VALUES " +
                "(%d,%d,%d,%d,0)", R.getKind(), R.getDate(), R.getIssuer() ,R.getSupervisor())))
            return true;

        return false;
    }
    public boolean add(Grade G) {
        if(this.execute(String.format("INSERT INTO Grades(Value, Course, [[Date]]], Student) VALUES " +
                "(%d, %d, '%s', %d", G.getValue(), G.getCourse().getId(), G.getDate(), G.getStudent().getId())))
            return true;

        return false;
    }
    public boolean add(Module M) {
        if(this.execute(String.format("INSERT INTO Modules(Kind, Course, Professor) VALUES " +
                "(%d, %d, %d)", M.getKind(), M.getCourse(), M.getProfessor())))
            return true;

        return false;
    }
    public boolean add(Feature F) {
        if(this.execute(String.format("INSERT INTO Features(Description) VALUES " +
                "('%s')", F.getDescription())))
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
            to_return.setDate(rs.getDate("[Date]").toLocalDate());

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

    public ArrayList<Student> getAllStudents(){
        ArrayList<Student> students = new ArrayList<Student>();

        try{

            ResultSet rs = executeQuery("" +
                    "SELECT " +
                    "S.Name AS S_Name, S.Surname AS S_Surname, S.Pay AS S_Pay, " +
                    "M.Name AS M_Name, " +
                    "F.Name AS F_Name, " +
                    "SG.Name AS SG_Name, SG.StudyYear AS SG_StudyYear, " +
                    "E.Name AS Secretary_Name, E.Surname AS Secretary_Surname, " +
                    "X.Name AS Mentor_Name , X.Surname AS Mentor_Surname, " +
                    "U.ID AS U_ID " +
                    "FROM Students AS S " +
                    "INNER JOIN StudyGroups AS SG ON S.StudyGroup = SG.ID " +
                    "INNER JOIN Majors AS M ON S.Major = M.ID " +
                    "INNER JOIN Employees AS E ON M.Secretary = E.ID " +
                    "INNER JOIN Employees AS X ON SG.Mentor = X.ID " +
                    "INNER JOIN Faculties AS F ON M.Faculty = F.ID " +
                    "INNER JOIN Users as U ON S.User_ID = U.ID"
            );


            while(rs.next())
            {
                Student S = new Student();
                S.setName(rs.getString("S_Name"));
                S.setSurname(rs.getString("S_Surname"));
                S.setIncome(rs.getInt("S_Pay"));

                User U = new User();
                U.setId(rs.getInt("U_ID"));

                StudyGroup SG = new StudyGroup();
                SG.setName(rs.getString("SG_Name"));
                SG.setStudy_year(rs.getInt("SG_StudyYear"));

                Major M = new Major();
                M.setName(rs.getString("M_Name"));

                Faculty F = new Faculty();
                F.setName(rs.getString("F_Name"));
                M.setFaculty(F);

                Employee secretary = new Employee();
                secretary.setName(rs.getString("Secretary_Name"));
                secretary.setSurname(rs.getString("Secretary_Surname"));
                M.setSecretary(new Secretary(secretary));

                Employee mentor = new Employee();
                mentor.setName(rs.getString("Mentor_Name"));
                mentor.setSurname(rs.getString("Mentor_Surname"));
                SG.setMentor(new Professor(mentor));

                S.setMajor(M);
                S.setStudyGroup(SG);
                S.setUser(U);

                students.add(S);
            }
            return students;
        }
        catch (SQLException e){}

        return null;
    }
    public ArrayList<Grade> getAllGrades(){
        ArrayList<Grade> grades = new ArrayList<Grade>();

        try{

            ResultSet rs = executeQuery("" +
                    "SELECT " +
                    "G.Value AS G_Value, G.[[Date]]] AS G_Date,\n" +
                    "C.Credits AS C_Credits, C.Name AS C_Name,\n" +
                    "E.Name AS Professor_Name, E.Surname AS Professor_Surname,\n" +
                    "S.Name AS Student_Name, S.Surname AS Student_Surname,\n" +
                    "SG.Name AS SG_Name, SG.StudyYear AS SG_StudyYear,\n" +
                    "M.Name AS M_Name, M.Surname AS M_Surname\n" +
                    "FROM Grades AS G\n" +
                    "INNER JOIN Courses AS C ON C.ID=G.Course\n" +
                    "INNER JOIN Employees AS E ON C.Professor=E.ID\n" +
                    "INNER JOIN Students AS S ON G.Student=S.ID\n" +
                    "INNER JOIN StudyGroups AS SG ON S.StudyGroup=SG.ID\n" +
                    "INNER JOIN Employees AS M ON SG.Mentor=M.ID"
            );


            while(rs.next())
            {
                Grade G = new Grade();
                G.setValue(rs.getInt("G_Value"));
                G.setDate(rs.getDate("G_Date").toLocalDate());

                Course C=new Course();
                C.setCredits(rs.getInt("C_Credits"));
                C.setName(rs.getString("C_Name"));
                G.setCourse(C);

                Employee professor=new Employee();
                professor.setName(rs.getString("Professor_Name"));
                professor.setSurname(rs.getString("Professor_Surname"));
                C.setProfessor(new Professor(professor));

                Student S=new Student();
                S.setName(rs.getString("Student_Name"));
                S.setSurname(rs.getString("Student_Surname"));
                G.setStudent(S);

                StudyGroup SG = new StudyGroup();
                SG.setName(rs.getString("SG_Name"));
                SG.setStudy_year(rs.getInt("SG_StudyYear"));
                S.setStudyGroup(SG);

                Employee mentor = new Employee();
                mentor.setName(rs.getString("M_Name"));
                mentor.setSurname(rs.getString("M_Surname"));
                SG.setMentor(new Professor(mentor));

                grades.add(G);
            }
            return grades;
        }
        catch (SQLException e){}

        return null;
    }
    public ArrayList<Document> getAllDocuments() {
        ArrayList<Document> documents = new ArrayList<Document>();

        try{

            ResultSet rs = executeQuery("" +
                    "SELECT D.Title as D_Title, D.Content as D_Content, U.ID as U_ID " +
                    "FROM Documents as D " +
                    "INNER JOIN Users as U ON D.[[User]]]=U.ID"
            );


            while(rs.next())
            {
                Document D = new Document();
                D.setContent(rs.getString("D_content"));
                D.setTitle(rs.getString("D_Title"));

                User U = new User();
                U.setId(rs.getInt("U_ID"));

                D.setUser(U);

                documents.add(D);
            }
            return documents;
        }
        catch (SQLException e){}

        return null;
    }
    public ArrayList<Schedule> getAllSchedule(){
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();

        try{

            ResultSet rs = executeQuery("" +
                    "SELECT S.ID AS S_ID, S.[[Time]]] AS S_Time,\n" +
                    "SG.Name AS SG_Name, SG.StudyYear AS SG_StudyYear,\n" +
                    "C.Name AS C_Name, C.Capacity AS C_Capacity,\n" +
                    "M.Kind AS M_Kind,    \n" +
                    "C2.Name AS C2_Name, C2.Credits AS C2_Credits,  \n" +
                    "E.Name AS E_Name, E.Surname AS E_Surname   \n" +
                    "FROM Schedule AS S   \n" +
                    "INNER JOIN Modules AS M ON M.ID=S.Module   \n" +
                    "INNER JOIN StudyGroups AS SG ON SG.ID=S.StudyGroup   \n" +
                    "INNER JOIN Classrooms AS C ON C.ID=S.Classroom   \n" +
                    "INNER JOIN Courses AS C2 ON M.Course=C2.ID   \n" +
                    "INNER JOIN Employees AS E ON M.Professor=E.ID \n" +
                    "INNER JOIN Employees AS E2 ON SG.Mentor=E2.ID "
            );


            while(rs.next())
            {
                Module M=new Module();
                M.setKind(rs.getString("M_Kind"));

                StudyGroup SG=new StudyGroup();
                SG.setName(rs.getString("SG_Name"));
                SG.setStudy_year(rs.getInt("SG_StudyYear"));

                Classroom C=new Classroom();
                C.setName(rs.getString("C_Name"));
                C.setCapacity(rs.getInt("C_Capacity"));

                Course C2=new Course();
                C2.setName(rs.getString("C2_Name"));
                C2.setCredits(rs.getInt("C2_Credits"));
                M.setCourse(C2);

                Employee E=new Employee();
                E.setName(rs.getString("E_Name"));
                E.setSurname(rs.getString("E_Surname"));
                M.setProfessor(new Professor(E));
                C2.setProfessor(new Professor(E));

                Schedule S=new Schedule();
                S.setTime(rs.getTime("S_Time"));
                S.setDate(rs.getDate("S_Time"));
                S.setId(rs.getInt("S_ID"));

                S.setModule(M);
                S.setClassroom(C);
                S.setStudy_group(SG);

                schedules.add(S);
            }
            return schedules;
        }
        catch (SQLException e){}

        return null;
    }
    public ArrayList<Classroom> getAllClassrooms() {
        ArrayList<Classroom> classrooms = new ArrayList<Classroom>();

        try{

            ResultSet rs = executeQuery("" +
                    "SELECT Name, Capacity, Kind " +
                    "FROM Classrooms"
            );


            while(rs.next())
            {
                Classroom C = new Classroom();
                C.setName(rs.getString("Name"));
                C.setCapacity(rs.getInt("Capacity"));
                C.setKind(rs.getBoolean("Kind"));



                classrooms.add(C);
            }
            return classrooms;
        }
        catch (SQLException e){}

        return null;
    }
    public ArrayList<Course> getAllCourses() {
        ArrayList<Course> courses = new ArrayList<Course>();

        try{

            ResultSet rs = executeQuery("" +
                    "SELECT C.Name AS C_Name, C.Credits as C_Credits, E.Name as P_Name, E.Surname as P_Surname " +
                    "FROM Courses AS C " +
                    "INNER JOIN Employees AS E ON E.ID = C.Professor"
            );


            while(rs.next())
            {
                Course C = new Course();
                C.setName(rs.getString("C_Name"));
                C.setCredits(rs.getInt("C_Credits"));

                Professor P = new Professor(new Employee());
                P.setName(rs.getString("P_Name"));
                P.setSurname(rs.getString("P_Surname"));

                C.setProfessor(P);

                courses.add(C);
            }
            return courses;
        }
        catch (SQLException e){}

        return null;
    }

    public ArrayList<StudyGroup> getAllStudyGroups()
    {
        ArrayList<StudyGroup> studyGroups = new ArrayList<StudyGroup>();


        try{

            ResultSet rs = executeQuery("" +
                    "SELECT * " +
                    "FROM StudyGroups"
            );


            while(rs.next())
            {
                StudyGroup S = new StudyGroup();


                S.setStudy_year(rs.getInt("StudyYear"));
                S.setName("Name");

                Professor P = Professor.fromEmployee(Employee.fromDB(rs.getInt("Mentor")));
                S.setMentor(P);

                studyGroups.add(S);
            }
            return studyGroups;
        }
        catch (SQLException e){}

        return null;
    }

    public boolean update(User U){
        if (this.execute(String.format("UPDATE Users SET Username = '%s', Password = '%s', User_Role = %d WHERE ID = %d",
                U.getUsername(), U.getPassword(), U.getRole().getId(), U.getId())))
            return true;
        return false;
    }
    public boolean update(Classroom C) {
        int type = 0;
        if (C.isKind() == true)
            type = 1;

        try
        {
            if (this.execute(String.format("UPDATE Classrooms SET Capacity = %d, Kind = %d WHERE ID = %d",
                    C.getCapacity(), type, getClassroomID(C.getName()))))

                return true;
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

        return false;
    }


    public int getCourseID(String course) throws SQLException {
        ResultSet rs = this.executeQuery(String.format("SELECT ID FROM Courses WHERE Name = '%s'", course));
        rs.next();
        return rs.getInt("ID");
    }
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


}
