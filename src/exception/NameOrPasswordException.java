package exception;

public class NameOrPasswordException extends Exception{
    NameOrPasswordException(){
        super("用户名或密码错误");
    }
    NameOrPasswordException(String message){
        super(message);
    }
}
