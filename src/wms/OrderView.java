package wms;

import java.sql.Timestamp;

public class OrderView {
    private char[]RNo=new char[10];
    private char[]GNo0=new char[8];
    private char[]GName=new char[15];
    private char[]ClientNo=new char[5];
    private char[]ClientName=new char[20];
    private int Amount=0;
    private Timestamp Rtime;

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

}
