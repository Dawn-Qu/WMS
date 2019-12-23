package model;

public class Record {

	public final String goodsName;
	public final int amount;
	public final String srcWarehouseNumber;
	public final String destWarehouseNumber;

	public Record(String goodsName, int amount, String srcWarehouseNumber, String destWarehouseNumber) {
		super();
		this.goodsName = goodsName;
		this.amount = amount;
		this.srcWarehouseNumber = srcWarehouseNumber;
		this.destWarehouseNumber = destWarehouseNumber;
	}

	public Object[] flat() {
		return new Object[]{goodsName,amount,srcWarehouseNumber,destWarehouseNumber};
	}
}
