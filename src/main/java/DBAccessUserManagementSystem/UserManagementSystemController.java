package DBAccessUserManagementSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UserManagementSystemController {
//    private UserDao userDao;
    //private CompanyDao companyDao;
    CompanyService companyService;
    private  UserService userService;
    @FXML
    TextField userName;

    @FXML
    TextField score;

    @FXML
    ComboBox affiliatedCompany;

    //編集時
    @FXML
    TextField userNameEdit;

    @FXML
    TextField scoreEdit;
    @FXML
    ComboBox affiliatedCompanyEdit;

    //テーブル
    @FXML
    TableView<UserInformation> userManagementSystemTable;

    @FXML
    TableColumn<UserInformation,Integer> idColumn;

    @FXML
    TableColumn<UserInformation,Integer> affiliatedCompanyIdColumn;

    @FXML
    TableColumn<UserInformation,String> affiliatedCompanyColumn;

    @FXML
    TableColumn<UserInformation,String> nameColumn;

    @FXML
    TableColumn<UserInformation,Integer> scoreColumn;

    @FXML
    TableView<companyInformation> companyTable;

    @FXML
    TableColumn companyIdColumn;

    @FXML
    TableColumn companyNameColumn;


    @FXML
    Label inputError;

    @FXML
    Label rangeError;

    @FXML
    Label inputError1;

    @FXML
    Label editInputError;

    @FXML
    Label editRangeError;

    @FXML
    Label editInputError1;

    //社員を管理する リストを作成
    ObservableList<UserInformation> userInformationObservableList = FXCollections.observableArrayList();
    ObservableList<companyInformation> companyObservableList = FXCollections.observableArrayList();
    int id = 0;

    public void initialize(){
        System.out.println(affiliatedCompanyEdit);
        //接続確保
        Connection connection = DbUtil.getConnection();
        //this.userDao = new UserDao(connection);
        this.userService = new UserService();
        this.companyService = new CompanyService();
        errorShow();

        //コンボボックス
        affiliatedCompany.getItems().addAll("1","2","3","4");//追加と
        affiliatedCompanyEdit.getItems().addAll("1","2","3","4");//更新

        //初期コンボボックス設定 会社名設定
        ObservableList<String> items = affiliatedCompany.getItems();
        affiliatedCompany.getSelectionModel().select(items.get(0));


        //DB更新
        dbUppdate();

        //UserTableバインド設定
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        affiliatedCompanyIdColumn.setCellValueFactory(new PropertyValueFactory<>("affiliatedCompanyId"));
        affiliatedCompanyColumn.setCellValueFactory(new PropertyValueFactory<>("affiliatedCompany"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        //企業
        companyIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //テーブルから取得して
        List<companyInformation> companys = companyService.getCompany();
        if(companys != null) {
            for(var company : companys) {
                //GUIテーブルに格納
                companyObservableList.add(new companyInformation(company.getId(), company.getName()));
                companyTable.setItems(companyObservableList);
            }
        }

        //テーブルを選択した時の処理
        userManagementSystemTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                String name = newSelection.getName();
                int affiliateCompanyId = newSelection.getAffiliatedCompanyId();
                int score = newSelection.getScore();
                id = newSelection.getId();
                //選択された行を編集可能エリアにセット
                userNameEdit.setText(name);
                scoreEdit.setText(String.valueOf(score));

                //コンボボックスの選択状態
                affiliatedCompanyEdit.getSelectionModel().select(affiliateCompanyId - 1);
            }
        });

    }

    void errorShow(){
        inputError.setOpacity(0);
        rangeError.setOpacity(0);
        inputError1.setOpacity(0);
        editInputError.setOpacity(0);
        editRangeError.setOpacity(0);
        editInputError1.setOpacity(0);
    }

    @FXML
    void updateButton(){
        errorShow();
        if(!userManagementSystemTable.getSelectionModel().isEmpty()) {
            //空白が入力されたら
            boolean updateFlg = true;
            String name = userNameEdit.getText();
            String aff = affiliatedCompanyEdit.getValue().toString();
            String scoreText = scoreEdit.getText();
            int score = 0;
            if(!isCheck(scoreText)){
                System.out.println("数値ではないエラー出力");
                editInputError1.setOpacity(1);
                updateFlg = false;
            }else{
                if(scoreText == "" || scoreText==" "){
                    System.out.println("数値入力空白エラー");
                    editInputError1.setOpacity(1);
                    updateFlg = false;
                }else{
                    score = Integer.parseInt(scoreText);
                    if(!rangCheck(score)){
                        System.out.println("入力が範囲外です");
                        editRangeError.setOpacity(1);
                        updateFlg = false;
                    }
                }

            }

            if(name == "" || name == " "){
                //エラー処理
                System.out.println("名前欄が空白なのでエラー");
                editInputError.setOpacity(1);
                updateFlg = false;
            }

            if(updateFlg) {
                System.out.println("選択されている");

                userService.userUpdate(new UserInformation(this.id,name,Integer.parseInt(aff),score));//DBを更新する
                dbUppdate();
            }

        }else{
            System.out.println("選択されていない");
        }

    }

    @FXML
    void deleteButton(){
        if(!userManagementSystemTable.getSelectionModel().isEmpty()) {
            //GUIテーブルのデータが一つ以上なら削除可能
            if(userInformationObservableList.size() > 1) {
                //選択されたIDをDBから削除する
                userService.userRemove(this.id);
                //選択されたIDをGUIから削除する
                userInformationObservableList.remove(userManagementSystemTable.getSelectionModel().getSelectedIndex());
            }
        }
    }

    @FXML
    void userAddButton(){

        errorShow();
        //空白が入力されたら
        boolean addFlg = true;
        //データーベースを取得する。
        String name = userName.getText();//ユーザーねむ
        String aff = affiliatedCompany.getValue().toString();//企業ID
        String scoreText = score.getText();//スコア入力させる。


        int score = 0;
        if(!isCheck(scoreText)){
            System.out.println("数値ではないエラー出力");
            inputError1.setOpacity(1);
            addFlg = false;
        }else{
            if(scoreText == "" || scoreText==" "){
                System.out.println("数値入力空白エラー");
                inputError1.setOpacity(1);
                addFlg = false;
            }else{
                score = Integer.parseInt(scoreText);
                if(!rangCheck(score)){
                    System.out.println("入力が範囲外です");
                    rangeError.setOpacity(1);
                    addFlg = false;
                }
            }

        }

        if(name == "" || name == " "){
            //エラー処理
            System.out.println("名前欄が空白なのでエラー");
            inputError.setOpacity(1);
            addFlg = false;
        }

        if(addFlg){
            //DBに追加
            userService.userAdd(new UserInformation(name,Integer.parseInt(aff),score));
            System.out.println("企業 ID"+Integer.parseInt(aff));
            //テーブルをクリアして
            dbUppdate();
        }
    }

    void dbUppdate(){
        userInformationObservableList.clear();

        List<UserInformation> getUserDate = new ArrayList<>();
        getUserDate = userService.getUserCompanyJoin();

        //DBから取得したデータを
        if(getUserDate != null) {
            for (var user : getUserDate) {
                System.out.println("ID"+user.getId());
                userInformationObservableList.add(new UserInformation(user.getId(),user.getName(), user.getAffiliatedCompanyId(),user.getAffiliatedCompany(), user.getScore()));
                System.out.println("ID "+user.getId()+"名前 "+user.getName()+"企業iD "+user.getAffiliatedCompanyId()+"企業 "+user.getAffiliatedCompany()+"スコア "+user.getScore());
                userManagementSystemTable.setItems(userInformationObservableList);
            }
        }
    }

    //リストの中を削除して
    //もう一度

    boolean isCheck(String str){
        boolean isNumber = true;

        for(var i = 0; i < str.length(); i++){
            if(!Character.isDigit(str.charAt(i))){
                isNumber = false;
                break;
            }
        }

        return isNumber;
    }

    boolean rangCheck(int num){
        boolean isRange= true;

        if(num > 100 || num < 0){
            isRange = false;
        }

        return isRange;
    }
}
