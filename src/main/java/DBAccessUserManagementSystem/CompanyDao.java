package DBAccessUserManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDao {
    private Connection connection;

    CompanyDao(Connection connection){
        this.connection = DbUtil.getConnection();
    }

    public List<companyInformation> getCompany(){
        List companys = new ArrayList<companyInformation>();

        final var sql = "SELECT * FROM companies";//命令文を定数として持つ
        try(PreparedStatement stmt = this.connection.prepareStatement(sql)){
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                var user  = new companyInformation(result.getInt("id"),result.getString("name"));
                companys.add(user);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return companys;
    }
}
