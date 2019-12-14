package model;

import java.util.Arrays;

public class WareHouse {
    private char []WNO=new char[3];
    private char []WName=new char[20];
    private int Capacity=0;
    public char[] getWNO(){
        return WNO;
    }

    public char[] getWName() {
        return WName;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setWNO(char[] WNO) {
        this.WNO = WNO;
    }

    public void setWName(char[] WName) {
        this.WName = WName;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    @Override
    public String toString() {
        return "WareHouse{" +
                "WNO=" + Arrays.toString(WNO) +
                ", WName=" + Arrays.toString(WName) +
                ", Capacity=" + Capacity +
                '}';
    }
}
