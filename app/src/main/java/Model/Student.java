package Model;

public class Student {


    private String StudentPhoneNo, Password, StudentEmail, StudentDepartment, Sid ,Sname, Year , Section;
    public Student(String StudentPhoneNo, String Password, String Email,String StudentDepartment, String Sid, String Sname, String Year, String Section) {
        this.StudentPhoneNo = StudentPhoneNo;
        this.Password = Password;
        this.StudentEmail = Email;
        this.Sid = Sid;
        this.Sname = Sname;
        this.StudentDepartment = StudentDepartment;
        this.Year = Year;
        this.Section = Section;

    }

    public Student() {

    }

    public String getStudentPhoneNo() {
        return StudentPhoneNo;
    }

    public void setStudentPhoneNo(String studentPhoneNo) {
        StudentPhoneNo = studentPhoneNo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getStudentEmail() {
        return StudentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        StudentEmail = studentEmail;
    }

    public String getStudentDepartment() {
        return StudentDepartment;
    }

    public void setStudentDepartment(String studentDepartment) {
        StudentDepartment = studentDepartment;
    }

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }
}
