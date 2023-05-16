package com.example.dbaccess;
import org.controlsfx.property.BeanPropertyUtils;
import java.sql.Connection;
import java.util.List;

public class ProductService {
    private ProductDao productDao;

    public ProductService(){
        Connection connection = DBUtil.getConnection();//DB接続データを取得
        this.productDao = new ProductDao(connection);//DbAccessObjectインスタンス生成
    }

    public ProductRecord findById(int id) throws ProductNotFoundException{
        ProductRecord productRecord = null;
        productRecord = productDao.findByld(id);
        if(productRecord == null) throw new ProductNotFoundException("値がNULLです。");
        return productRecord;
    }

    public List<ProductRecord> findByName(String name){
        return productDao.findByName(name);
    }

    public int insert(ProductRecord productRecord){
        int result = 0;
        try {
            result = productDao.insert(productRecord);
            //エラーが発生した場合、適当なerrorハンドリングを行う。
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public int update(ProductRecord productRecord){
        return productDao.update(productRecord);
    }

    public int delete(int id){
        return productDao.delete(id);
    }
}
