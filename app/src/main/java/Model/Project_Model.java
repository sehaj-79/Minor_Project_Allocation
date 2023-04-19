package Model;

public class Project_Model {

    private String Id,Name,Desc,Fname,Seats,Fid,Sname;

    public Project_Model(String Id, String Fid, String Name, String Desc, String Fname,String Seats, String Sname){
        this.Id = Id;
        this.Name = Name;
        this.Desc = Desc;
        this.Fname = Fname;
        this.Sname = Sname;
        this.Seats = Seats;
        this.Fid = Fid;
    }

    public Project_Model(){}

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getSeats() {
        return Seats;
    }

    public void setSeats(String seats) {
        Seats = seats;
    }

    public String getFid() {
        return Fid;
    }

    public void setFid(String fid) {
        Fid = fid;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }
}

