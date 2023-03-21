package Model;

public class Project_Model {

    private String Id,Name,Desc;

    public Project_Model(String Id, String Name, String Desc){
        this.Id = Id;
        this.Name = Name;
        this.Desc = Desc;
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

}

