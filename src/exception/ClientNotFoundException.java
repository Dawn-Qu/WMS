package exception;

public class ClientNotFoundException extends Exception{
    ClientNotFoundException(){
        super("客户不存在");
    }
    ClientNotFoundException(String message){
        super(message);
    }
}
