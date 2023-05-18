package DBAccessUserManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyService {
    CompanyDao companyDao;

    CompanyService(){
        Connection connection = DbUtil.getConnection();
        this.companyDao = new CompanyDao(connection);
    }
    public List<companyInformation> getCompany(){
        try{
            return companyDao.getCompany();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
