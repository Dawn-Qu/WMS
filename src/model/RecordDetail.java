package model;

public class RecordDetail {
    private char[]RNo=new char[10];
    private char[]Gno=new char[8];
    private char[]SourceWNo=new char[3];
    private int Amount=0;

    public char[] getSourceWNo() {
        return SourceWNo;
    }

    public int getAmount() {
        return Amount;
    }

    public char[] getRNo() {
        return RNo;
    }

    public char[] getGno() {
        return Gno;
    }

    public void setSourceWNo(char[] sourceWNo) {
        SourceWNo = sourceWNo;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public void setRNo(char[] RNo) {
        this.RNo = RNo;
    }

    public void setGno(char[] gno) {
        Gno = gno;
    }

}
