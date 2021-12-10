package mta.universitate.Model;
import java.util.Date;

public class Request {

        private int id;
        private RequestType kind;
        private Date date;
        private User issuer;
        private Employee supervisor;
        private boolean approved;

    public static Request fromDB(int id)
    {
        Request R = new Request();
        R.id = id;

        return Database.getInstance().get(R);
    }



    public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public RequestType getKind() {
            return kind;
        }

        public void setKind(RequestType kind) {
            this.kind = kind;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public User getIssuer() {
            return issuer;
        }

        public void setIssuer(User issuer) {
            this.issuer = issuer;
        }

        public Employee getSupervisor() {
            return supervisor;
        }

        public void setSupervisor(Employee supervisor) {
            this.supervisor = supervisor;
        }

        public boolean isApproved() {
            return approved;
        }

        public void setApproved(boolean approved) {
            this.approved = approved;
        }
    }


