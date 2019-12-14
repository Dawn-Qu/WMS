package wms;

public class StockView {
    private char[]GNo=new char[8];
    private char[]GName=new char[15];
    private int Amount=0;
    private char[]WNo=new char[3];
    private char[]WName=new char[20];

    public int getAmount() {
        return Amount;
    }

    public char[] getGNo() {
        return GNo;
    }

    public char[] getGName() {
        return GName;
    }

    public char[] getWNo() {
        return WNo;
    }

    public char[] getWName() {
        return WName;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public void setGNo(char[] GNo) {
        this.GNo = GNo;
    }

    public void setGName(char[] GName) {
        this.GName = GName;
    }

    public void setWNo(char[] WNo) {
        this.WNo = WNo;
    }

    public void setWName(char[] WName) {
        this.WName = WName;
    }
}
