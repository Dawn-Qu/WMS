package model;

import java.util.Arrays;

public class Record {

	public final String goodsName;
	public final int amount;
	public final int srcWarehouseNumber;
	public final int destWarehouseNumber;
	public Record(String goodsName, int amount, int srcWarehouseNumber, int destWarehouseNumber) {
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
