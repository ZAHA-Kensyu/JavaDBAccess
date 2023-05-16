package Test;

//非チェック例外を発生させる。
public class TestException extends RuntimeException{
    public TestException(String message){
        super(message);
    }
}
