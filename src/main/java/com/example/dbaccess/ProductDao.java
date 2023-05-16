package com.example.dbaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    //フィールド
    Connection connection;

    //コンストラクタの定義
    public ProductDao(Connection connection){
        this.connection = connection;
    }

    ProductRecord findByld(int productRecordId){
        //idでproductsテーブルを検索する
        ProductRecord productRecord = null;
        final var sql = "SELECT * FROM products WHERE id = " + productRecordId;//命令文を定数として持つ
        //SQLに命令を渡す準備させる。
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            ResultSet result = stmt.executeQuery();//実際に実行

            while(result.next()){
                productRecord = new ProductRecord(result.getInt("id"),result.getString("name"),result.getInt("price"));
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return productRecord;
    }

    //リストのレコード返す 引数名前
    List<ProductRecord> findByName(String name){
        //リストを作成
        var productRecordList = new ArrayList<ProductRecord>();
        final var sql = "SELECT * FROM products WHERE name LIKE '%"+name+"%'";//命令文を定数として持つ
        try(PreparedStatement stmt = this.connection.prepareStatement(sql)){
            ResultSet result = stmt.executeQuery();

            while(result.next()){
               var productRecord  = new ProductRecord(result.getInt("id"),result.getString("name"),result.getInt("price"));
                productRecordList.add(productRecord);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return productRecordList;
    }

    //Insert型
    int insert(ProductRecord productRecord){
        int result = 0;
        final var sql = "INSERT INTO products VALUES("+productRecord.id()+","+"'"+productRecord.name()+"'"+","+productRecord.price()+");";
        try(PreparedStatement stmt = this.connection.prepareStatement(sql)){
            result = stmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        //処理件数を戻り値で返す
        return result;
    }

    int update(ProductRecord productRecord){
        int result = 0;
        final var sql = "UPDATE products SET id = "+productRecord.id()+", name = "+"'"+productRecord.name()+"'"+", price = "+productRecord.price()+" WHERE id = "+productRecord.id()+";";
        System.out.println(sql);

        try(PreparedStatement stmt = this.connection.prepareStatement(sql)){
            result = stmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        //処理件数を戻り値で返す
        return result;
    }

    int delete(int id){
        int result = 0;
        final var sql = "DELETE FROM products WHERE id = "+ id +";";
        try(PreparedStatement stmt = this.connection.prepareStatement(sql)){
            result = stmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        //処理件数を戻り値で返す
        return result;
    }
}
