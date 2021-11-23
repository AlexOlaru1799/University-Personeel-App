package mta.universitate.Model;


public class Main {
    public static void main(String[] args) {
        Student s= new Student("Razvan","Marian","2000");
        Administrator A = new Administrator("Togan", "Mihai","1970");
        System.out.print(s.getNume() + "\n");
        A.adaugaPersoana(s);
        System.out.print("\n");
        A.afiseazaStudent(s);

        Database db1;
        // refers to the only object of Database
        db1= Database.getInstance();
        System.out.print(db1.getConnection());
        db1.executeQuery("select * from studenti ");
    }
}
