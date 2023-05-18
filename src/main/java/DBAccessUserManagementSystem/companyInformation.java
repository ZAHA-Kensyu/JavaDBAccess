package DBAccessUserManagementSystem;

public class companyInformation {
    private int id;
    private String name;

    companyInformation(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public void setId(int id){
        if(id < 0){
            this.id = 0;
        }
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }
}
