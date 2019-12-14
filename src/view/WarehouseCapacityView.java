package view;

import java.util.Arrays;

public class WarehouseCapacityView {
    private char[]RNo=new char[10];
    private char[]GNo=new char[8];
    private char[]ANo=new char[6];
    private char[]DestWNo=new char[3];

    public char[] getANo() {
        return ANo;
    }

    public char[] getGNo() {
        return GNo;
    }

    public char[] getRNo() {
        return RNo;
    }

    public char[] getDestWNo() {
        return DestWNo;
    }

    public void setANo(char[] ANo) {
        this.ANo = ANo;
    }

    public void setGNo(char[] GNo) {
        this.GNo = GNo;
    }

    public void setRNo(char[] RNo) {
        this.RNo = RNo;
    }

    public void setDestWNo(char[] destWNo) {
        DestWNo = destWNo;
    }

    @Override
    public String toString() {
        return "WarehouseCapacityView{" +
                "RNo=" + Arrays.toString(RNo) +
                ", GNo=" + Arrays.toString(GNo) +
                ", ANo=" + Arrays.toString(ANo) +
                ", DestWNo=" + Arrays.toString(DestWNo) +
                '}';
    }
}
