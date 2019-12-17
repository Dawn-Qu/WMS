package view;

import java.sql.Timestamp;

public class TransferView {
    private char[]RNo=new char[10];
    private char[]GNo=new char[8];
    private char[]GName=new char[15];
    private char[]SourceWNo=new char[3];
    private char[]DestWNo=new char[3];
    private int Amount=0;
    private Timestamp RTime;

    public void setAmount(int amount) {
        Amount = amount;
    }

    public char[] getGName() {
        return GName;
    }

    public char[] getGNo() {
        return GNo;
    }

    public char[] getRNo() {
        return RNo;
    }

    public char[] getSourceWNo() {
        return SourceWNo;
    }

    public Timestamp getRTime() {
        return RTime;
    }

    public char[] getDestWNo() {
        return DestWNo;
    }

    public void setGName(char[] GName) {
        this.GName = GName;
    }

    public void setGNo(char[] GNo) {
        this.GNo = GNo;
    }

    public void setRNo(char[] RNo) {
        this.RNo = RNo;
    }

    public void setSourceWNo(char[] sourceWNo) {
        SourceWNo = sourceWNo;
    }

    public void setRTime(Timestamp RTime) {
        this.RTime = RTime;
    }

    public void setDestWNo(char[] destWNo) {
        DestWNo = destWNo;
    }

    public int getAmount() {
        return Amount;
    }
}
