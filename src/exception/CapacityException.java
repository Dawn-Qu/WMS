package exception;
public class CapacityException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 305217112650773631L;
	/**
	 * 
	 */
	CapacityException(){
        super();
    }
    public CapacityException(String message){
        super(message);
    }
}