package frame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * use this class to create a local table model
 * it defines several table model used by frame package
 * this table model is used to show
 * @author 90946
 *
 */
public class ShowTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8762197853686994754L;
	
	
	
	public static final ShowTableModel recordTableModel = 
	new ShowTableModel(Arrays.asList("物资号","数量","来源仓库号","目的仓库号"));
	
	public static final ShowTableModel warehouseSearchTableModel = 
			new ShowTableModel(Arrays.asList("仓库号","仓库名","总容量","可用容量"));
	
	public static final ShowTableModel goodsSearchTableModel = 
	new ShowTableModel(Arrays.asList("物资号","物资名","数量","仓库号","仓库名"));
	
	public static final ShowTableModel purchaseRecordTableModel = 
			new ShowTableModel(Arrays.asList("记录号","部门号","物资号","物资名","物资数量","目的仓库号","时间"));

	public static final ShowTableModel transferRecordTableModel = 
			new ShowTableModel(Arrays.asList("记录号","物资号","物资名","物资数量","来源仓库号","目的仓库号","时间"));

	public static final ShowTableModel deliveryRecordTableModel = 
			new ShowTableModel(Arrays.asList("记录号","客户号","物资号","物资名","物资数量","来源仓库号","时间"));

	public static final ShowTableModel warehouseTableModel = 
			new ShowTableModel(Arrays.asList("仓库号","仓库名","容量","已用容量"));
	/**
	 * make a empty table model by column name
	 * @param columnNameList
	 */
	public ShowTableModel(List<String> columnNameList) {
		// TODO Auto-generated constructor stub
		this.columnNames = columnNameList.toArray(new String[] {});
		this.data = new ArrayList<>();
	}

	private String[] columnNames;
	private List<List<Object>> data;

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	/**
	 * get a value in (row,col)
	 */
	public Object getValueAt(int row, int col) {
		return data.get(row).get(col);
	}

	/*
	 * JTable uses this method to determine the default renderer/ editor for each
	 * cell. If we didn't implement this method, then the last column would contain
	 * text ("true"/"false"), rather than a check box.
	 */
	public Class<?> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	/*
	 * Don't need to implement this method unless your table's editable.
	 */
	public boolean isCellEditable(int row, int col) {
//Note that the data/cell address is constant,
//no matter where the cell appears onscreen.
		return false;
	}

	/*
	 * Don't need to implement this method unless your table's data can change.
	 */
	/**
	 * set a value in (row,col)
	 */
	public void setValueAt(Object value, int row, int col) {
		List<Object> list = data.get(row);
		list.set(col, value);
		fireTableCellUpdated(row, col);
	}
	
	/**
	 * insert a row to this table
	 * @param params
	 */
	public void addRow(Object ... params) {
		List<Object> list = new ArrayList<>(Arrays.asList(params));
		data.add(list);
		fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
	}
	
	/**
	 * delete a row in the specified index
	 * @param index
	 */
	public void delRow(int index) {
		if(0>index||index>=getRowCount())throw new IllegalArgumentException();
		data.remove(index);
		fireTableRowsDeleted(index, index);
	}
	
	/**
	 * clear all data in table
	 */
	public void clear() {
		int r = getRowCount();
		data.clear();
		fireTableRowsDeleted(0, r);
	}
}