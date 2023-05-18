package DBAccessUserManagementSystem;

import com.example.dbaccess.ProductRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection connection;

    public UserDao(Connection connection){
        this.connection = connection;
    }

    //データを取得する
    List<UserInformation> getUserDate(){
        //リストを作成
        var userData = new ArrayList<UserInformation>();
        final var sql = "SELECT * FROM users";//命令文を定数として持つ
        try(PreparedStatement stmt = this.connection.prepareStatement(sql)){
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                var user  = new UserInformation(result.getInt("id"),result.getString("name"),result.getInt("company_id"),result.getInt("score"));
                userData.add(user);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return userData;
    }


    //ユーザー追加を作成
    public int userAdd(UserInformation userInformation){
        int result = 0;
        final var sql = "INSERT INTO users(name,company_id,score) VALUES("+"'"+userInformation.getName()+"'"+","+userInformation.getAffiliatedCompanyId()+","+userInformation.getScore()+");";
        try(PreparedStatement stmt = this.connection.prepareStatement(sql)){
            result = stmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        //処理件数を戻り値で返す
        return result;
    }

    public int userUpdate(UserInformation userInformation){
        int result = 0;
        final var sql = "UPDATE users SET name = "+"'"+userInformation.getName()+"'"
                +", company_id = " +userInformation.getAffiliatedCompanyId()
                +", score = "+userInformation.getScore()
                +" WHERE id = "+userInformation.getId()+";";
        System.out.println(sql);

        try(PreparedStatement stmt = this.connection.prepareStatement(sql)){
            result = stmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        //処理件数を戻り値で返す
        return result;
    }

    public int userRemove(int id){
        int result = 0;
        final var sql = "DELETE FROM users WHERE id = "+ id +";";
        try(PreparedStatement stmt = this.connection.prepareStatement(sql)){
            result = stmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        //処理件数を戻り値で返す
        return result;
    }

    //結合して取得
    public List<UserInformation> getUserCompanyJoin(){
        var userData = new ArrayList<UserInformation>();
        final var sql = "SELECT u.id,u.name, u.company_id, c.name c_name,u.score" +
                " FROM users u" +
                " JOIN companies c" +
                " ON u.company_id = c.id;";//命令文を定数として持つ
        System.out.println(sql);
        try(PreparedStatement stmt = this.connection.prepareStatement(sql)){
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                var user  = new UserInformation(result.getInt("id"),result.getString("name"),result.getInt("company_id"),result.getString("c_name"),result.getInt("score"));
                userData.add(user);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return userData;
    }

    //実装テスト用
    public static void main(String[] args) {

        UserManagementSystemController userManagementSystemController = new UserManagementSystemController();

//        Connection connection = DbUtil.getConnection();
//        UserDao userDao = new UserDao(connection);
//////        UserInformation user1 = new UserInformation(1,"TandC","zaha",10);
////        //System.out.println("追加件数 :"+userDao.userAdd(user1)+"件");
////        System.out.println("追加件数 :"+userDao.userRemove(5)+"件");
////
//        List<UserInformation> users =  userDao.getUserCompanyJoin();
//        for(var user : users) {
//            System.out.println("ID "+user.getId()+"名前 "+user.getName()+"企業iD "+user.getAffiliatedCompanyId()+"企業 "+user.getAffiliatedCompany()+"スコア "+user.getScore());
//        }
//        List<UserInformation> userdata = userDao.getUserDate();
//        for(var user :userdata){
//            System.out.println("id :" + user.getId() + " name :" +user.getName() + " company_id :"+user.getAffiliatedCompanyId()+" score :" + user.getScore());
//        }
    }
}
