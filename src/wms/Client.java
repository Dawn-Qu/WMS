package wms;

public class Client {
    private char []ClinetNo=new char[6];
    private char []ClientName=new char[20];

    public char[] getClientName() {
        return ClientName;
    }

    public char[] getClinetNo() {
        return ClinetNo;
    }

    public void setClientName(char[] clientName) {
        ClientName = clientName;
    }

    public void setClinetNo(char[] clinetNo) {
        ClinetNo = clinetNo;
    }
}
