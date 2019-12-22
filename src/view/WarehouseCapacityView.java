package view;

import java.util.Arrays;

public class WarehouseCapacityView {
    private char[] WNo=new char[3];
    private char[] WName=new char[20];
    private int Capacity;
    private int ExcessCapacity;

    public void setWName(char[] WName) {
        this.WName = WName;
    }

    public void setWNo(char[] WNo) {
        this.WNo = WNo;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public void setExcessCapacity(int excessCapacity) {
        ExcessCapacity = excessCapacity;
    }

    public char[] getWName() {
        return WName;
    }

    public char[] getWNo() {
        return WNo;
    }

    public int getCapacity() {
        return Capacity;
    }

    public int getExcessCapacity() {
        return ExcessCapacity;
    }

    @Override
    public String toString() {
        return "WarehouseCapacityView{" +
                "WNo=" + Arrays.toString(WNo) +
                ", WName=" + Arrays.toString(WName) +
                ", Capacity=" + Capacity +
                ", ExcessCapacity=" + ExcessCapacity +
                '}';
    }
}
