package Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ExceptionTest {

    public static void main(String[] args){
        try{
            read();
        }catch (TestException e){
            System.out.println(e.getMessage());
        }
    }

    public static void read() throws TestException {
        String name = null;
        if(name == null){
            throw new TestException("値がnullです。");//例外を発生させる。
        }
    }
}
