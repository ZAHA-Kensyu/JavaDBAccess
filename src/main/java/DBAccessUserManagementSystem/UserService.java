package DBAccessUserManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private UserDao userDao;
    UserService(){
        Connection connection = DbUtil.getConnection();
        this.userDao = new UserDao(connection);
    }

    public List<UserInformation> getUserDate(){
        try{
            return userDao.getUserDate();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public int userAdd(UserInformation userInformation){
        try{
            return userDao.userAdd(userInformation);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int userUpdate(UserInformation userInformation){
        try{
            return userDao.userUpdate(userInformation);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int userRemove(int id){
        try{
            return userDao.userRemove(id);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    //結合して取得
    public List<UserInformation> getUserCompanyJoin(){
        try {
            return userDao.getUserCompanyJoin();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
