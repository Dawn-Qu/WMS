package model;

public class Administrator {
    private char[]ANo=new char[6];
    private char[]Password=new char[8];

    public char[] getANo() {
        return ANo;
    }

    public char[] getPassword() {
        return Password;
    }

    public void setANo(char[] ANo) {
        this.ANo = ANo;
    }

    public void setPassword(char[] password) {
        Password = password;
    }
}
