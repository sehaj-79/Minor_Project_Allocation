package Model;

public class Users
{
    private String Fname,name, username, PhoneNo, password, Email, Fid, search, Designation, course, Department,Expertise, gender, year,section,faculty,school;

    public Users(String Fname,String name,String username, String phoneno, String password, String Email,String Expertise, String Fid, String search, String Designation, String course, String department, String gender, String year, String section, String faculty, String school) {
        this.username = username;
        this.PhoneNo = phoneno;
        this.password = password;
        this.Email = Email;
        this.Fid = Fid;
        this.Fname = Fname;
        this.name = name;
        this.search = search;
        this.Designation = Designation;
        this.course = course;
        this.Department = department;
        this.gender = gender;
        this.year = year;
        this.section = section;
        this.school = school;
        this.faculty = faculty;
        this.Expertise= Expertise;

    }

    public Users() {

    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() { return faculty; }

    public void setFaculty(String faculty) { this.faculty = faculty; }

    public String getSchool() { return school; }

    public void setSchool(String school) { this.school = school; }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        this.Designation = Designation;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = Email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhone(String phone) {
        this.PhoneNo = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFid() {
        return Fid;
    }

    public void setFid(String fid) {
        Fid = fid;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        this.Department = department;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getExpertise() {
        return Expertise;
    }

    public void setExpertise(String expertise) {
        this.Expertise = expertise;
    }





}