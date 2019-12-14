package wms;

public class TransferInfo {
    private char[]RNo=new char[10];
    private char[]GNo=new char[8];
    private char[]ANo=new char[6];
    private char[]DestWNo=new char[3];

    public char[] getDestWNo() {
        return DestWNo;
    }

    public char[] getRNo() {
        return RNo;
    }

    public char[] getGNo() {
        return GNo;
    }

    public char[] getANo() {
        return ANo;
    }

    public void setDestWNo(char[] destWNo) {
        DestWNo = destWNo;
    }

    public void setRNo(char[] RNo) {
        this.RNo = RNo;
    }

    public void setGNo(char[] GNo) {
        this.GNo = GNo;
    }

    public void setANo(char[] ANo) {
        this.ANo = ANo;
    }
}
