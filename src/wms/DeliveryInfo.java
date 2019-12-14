package wms;

public class DeliveryInfo {

    private char[]RNo=new char[10];
    private char[]ClientNo=new char[5];

    public void setClientNo(char[] clientNo) {
        ClientNo = clientNo;
    }

    public void setRNo(char[] RNo) {
        this.RNo = RNo;
    }

    public char[] getClientNo() {
        return ClientNo;
    }

    public char[] getRNo() {
        return RNo;
    }
}
