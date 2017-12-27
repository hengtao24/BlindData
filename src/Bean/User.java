package Bean;

public class User {

    private String StudentId;
    private String Password;

    public User(){}

    public User(String studentId,String password){
        this.StudentId = studentId;
        this.Password = password;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
