package wms;

public class PurchaseInfo {
    private char[]RNo=new char[10];
    private char[] DNo=new char[3];

    public char[] getDNo() {
        return DNo;
    }

    public char[] getRNo() {
        return RNo;
    }

    public void setDNo(char[] DNo) {
        this.DNo = DNo;
    }

    public void setRNo(char[] RNo) {
        this.RNo = RNo;
    }
}
