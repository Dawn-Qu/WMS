package wms;

public class Stock {
    private char[] WNo=new char[3];
    private char[] GNo=new char[8];
    private int Amount;

    public char[] getGNo() {
        return GNo;
    }

    public char[] getWNo() {
        return WNo;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public void setGNo(char[] GNo) {
        this.GNo = GNo;
    }

    public void setWNo(char[] WNo) {
        this.WNo = WNo;
    }
}
