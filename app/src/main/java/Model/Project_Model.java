package Model;

public class Project_Model {

    private String Id,Name,Desc,Fname,Seats;

    public Project_Model(String Id, String Name, String Desc, String Fname,String Seats){
        this.Id = Id;
        this.Name = Name;
        this.Desc = Desc;
        this.Fname = Fname;
        this.Seats = Seats;
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
}

