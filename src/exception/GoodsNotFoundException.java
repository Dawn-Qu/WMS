package exception;
public class GoodsNotFoundException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2596236536821901169L;
	GoodsNotFoundException(){
        super();
    }
    public GoodsNotFoundException(String message){
        super(message);
    }
}