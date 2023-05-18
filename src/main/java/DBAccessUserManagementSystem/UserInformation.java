package DBAccessUserManagementSystem;

public class UserInformation {
    private int id;
    private String affiliatedCompany; //所属企業
    private int affiliatedCompanyId;//所属企業ID
    private String name;
    private int score; //0~100

    public UserInformation(int id,String name,int affiliatedCompanyId,int score){
        this.id = id;
        this.affiliatedCompanyId = affiliatedCompanyId;
        this.name = name;
        this.score = score;
    }

    public UserInformation(String name,int affiliatedCompanyId,int score){
        this.affiliatedCompanyId = affiliatedCompanyId;
        this.name = name;
        this.score = score;
    }

    public UserInformation(int id,String name,int affiliatedCompanyId, String affiliatedCompany,int score){
        this.id = id;
        this.affiliatedCompanyId = affiliatedCompanyId;
        this.affiliatedCompany = affiliatedCompany;
        this.name = name;
        this.score = score;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAffiliatedCompany(String affiliatedCompany) {this.affiliatedCompany = affiliatedCompany;}

    public void setAffiliatedCompanyId(int affiliatedCompanyId) {this.affiliatedCompanyId = affiliatedCompanyId;}

    public void setName(String name) {this.name = name;}

    public void setScore(int score) {this.score = score < 0 || score > 100 ? 0 : score;}

    public int getId(){return this.id;}


    public String getName(){return this.name;}

    public String getAffiliatedCompany(){return this.affiliatedCompany;}

    public int getScore(){return this.score;}

    public int getAffiliatedCompanyId(){return this.affiliatedCompanyId;}
}
