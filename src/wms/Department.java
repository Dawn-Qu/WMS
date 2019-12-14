package wms;

public class Department {
    private char[] Dno=new char[3];
    private  char[]Dname=new char[20];

    public char[] getDname() {
        return Dname;
    }

    public char[] getDno() {
        return Dno;
    }

    public void setDname(char[] dname) {
        Dname = dname;
    }

    public void setDno(char[] dno) {
        Dno = dno;
    }
}
