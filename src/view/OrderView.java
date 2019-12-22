package view;

import java.sql.Timestamp;

public class OrderView {
    private char[]RNo=new char[10];
    private char[]GNo0=new char[8];
    private char[]GName=new char[15];
    private char[]ClientNo=new char[5];
    private char[]ClientName=new char[20];
    private char[]SourceWNo=new char[3];
    private int Amount=0;
    private Timestamp Rtime;

    public char[] getSourceWNo() {
        return SourceWNo;
    }

    public void setSourceWNo(char[] sourceWNo) {
        SourceWNo = sourceWNo;
    }

    public char[] getRNo() {
        return RNo;
    }

    public char[] getClientNo() {
        return ClientNo;
    }

    public int getAmount() {
        return Amount;
    }

    public char[] getClientName() {
        return ClientName;
    }

    public char[] getGName() {
        return GName;
    }

    public char[] getGNo0() {
        return GNo0;
    }


    public Timestamp getRtime() {
        return Rtime;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public void setRNo(char[] RNo) {
        this.RNo = RNo;
    }

    public void setGName(char[] GName) {
        this.GName = GName;
    }

    public void setRtime(Timestamp rtime) {
        Rtime = rtime;
    }

    public void setClientNo(char[] clientNo) {
        ClientNo = clientNo;
    }

    public void setClientName(char[] clientName) {
        ClientName = clientName;
    }

    public void setGNo0(char[] GNo0) {
        this.GNo0 = GNo0;
    }
}
