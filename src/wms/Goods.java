package wms;

public class Goods {
    private char []GNO=new char[8];
    private char []GName=new char[15];
    private int Cost=0;
    private int Price=0;

    public char[] getGNO() {
        return GNO;
    }

    public int getCost() {
        return Cost;
    }

    public int getPrice() {
        return Price;
    }

    public char[] getGName() {
        return GName;
    }

    public void setCost(int cost) {
        Cost = cost;
    }

    public void setGName(char[] GName) {
        this.GName = GName;
    }

    public void setGNO(char[] GNO) {
        this.GNO = GNO;
    }

    public void setPrice(int price) {
        Price = price;
    }

}
