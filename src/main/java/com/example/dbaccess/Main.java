package com.example.dbaccess;

import java.sql.Connection;

public class Main {
    public static void main(String[] args){
        ProductService productService = new ProductService();


        System.out.println("ID検索 :"+ productService.findById(-1));//IDが101のものを探す。

        //ID検索 //投げられた例外を処理する。
//        try {
//            System.out.println("ID検索 :"+ productService.findById(10));//IDが101のものを探す。
//        }catch(ProductNotFoundException e){
//            System.out.println(e.getMessage());
//        }

        //名前処理確認
        System.out.println("名前検索 :"+productService.findByName("地球"));

        //インサート
        ProductRecord productRecord = new ProductRecord(10,"aaa",100);
        System.out.println("インサート結果 :"+productService.insert(productRecord)+"件");

        //更新
        ProductRecord updateProductRecord = new ProductRecord(10,"UPDATE",10);
        System.out.println("更新結果 :"+productService.update(updateProductRecord)+"件");

        //削除
        System.out.println("削除結果 :"+productService.delete(1)+"件");
    }
}
