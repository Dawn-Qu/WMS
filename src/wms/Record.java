package wms;

import java.sql.Timestamp;

public class Record {
    private char[]RNo=new char[10];
    private char[]RUsage=new char[20];
    private Timestamp Rtime;

    public char[] getRNo() {
        return RNo;
    }

    public Timestamp getRtime() {
        return Rtime;
    }

    public char[] getRUsage() {
        return RUsage;
    }

    public void setRNo(char[] RNo) {
        this.RNo = RNo;
    }

    public void setRtime(Timestamp rtime) {
        Rtime = rtime;
    }

    public void setRUsage(char[] RUsage) {
        this.RUsage = RUsage;
    }
}
