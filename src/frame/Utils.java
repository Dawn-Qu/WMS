package frame;

public class Utils {

	private Utils() {
		// TODO Auto-generated constructor stub
	}

	public final static String workplace = System.getProperty("user.dir");
	public final static String mainFrameImgPath = workplace+"\\img\\main.jpg";
	public final static String loginFrameImgPath = workplace+"\\img\\login.jpg";
	
	
	public static void main(String[] args) {
		System.out.println(workplace);
		System.out.println(mainFrameImgPath);
	}
}
