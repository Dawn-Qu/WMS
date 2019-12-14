package wms;

import java.sql.Timestamp;

public class RecordView {
    private char[]RNo=new char[10];
    private char[]GNo=new char[8];
    private char[]SourceWNo=new char[3];
    private char[]RUsage=new char[20];
    private Timestamp RTime;
    private int Amount=0;

    public char[] getRUsage() {
        return RUsage;
    }

    public char[] getRNo() {
        return RNo;
    }

    public Timestamp getRTime() {
        return RTime;
    }

    public char[] getGNo() {
        return GNo;
    }

    public int getAmount() {
        return Amount;
    }

    public char[] getSourceWNo() {
        return SourceWNo;
    }

    public void setRUsage(char[] RUsage) {
        this.RUsage = RUsage;
    }

    public void setRNo(char[] RNo) {
        this.RNo = RNo;
    }

    public void setRTime(Timestamp RTime) {
        this.RTime = RTime;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public void setGNo(char[] GNo) {
        this.GNo = GNo;
    }

    public void setSourceWNo(char[] sourceWNo) {
        SourceWNo = sourceWNo;
    }
}
