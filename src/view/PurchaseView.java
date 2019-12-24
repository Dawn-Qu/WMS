package view;

import java.sql.Timestamp;

public class PurchaseView {
    private char[]RNo=new char[10];
    private char[]DNo=new char[3];
    private char[]GNo=new char[8];
    private char[]GName=new char[15];
    private char[]DestWNo=new char[3];
    private int Amount=0;
    private Timestamp RTime;

    public char[] getGName() {
        return GName;
    }

    public int getAmount() {
        return Amount;
    }

    public char[] getRNo() {
        return RNo;
    }

    public char[] getGNo() {
        return GNo;
    }

    public char[] getDNo() {
        return DNo;
    }

    public Timestamp getRTime() {
        return RTime;
    }

    public void setRNo(char[] RNo) {
        this.RNo = RNo;
    }

    public void setGNo(char[] GNo) {
        this.GNo = GNo;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public char[] getDestWNo() {
        return DestWNo;
    }

    public void setDestWNo(char[] DestWNo) {
        this.DestWNo = DestWNo;
    }

    public void setGName(char[] GName) {
        this.GName = GName;
    }

    public void setDNo(char[] DNo) {
        this.DNo = DNo;
    }

    public void setRTime(Timestamp RTime) {
        this.RTime = RTime;
    }
}
