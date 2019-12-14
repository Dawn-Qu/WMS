package wms;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.*;

public class DataProcessing {
    public static final String URL = "jdbc:mysql://localhost:3306/warehouse_db?serverTimezone=GMT%2B8";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    public static Connection connection;

    static {
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2. 获得数据库连接
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void purchase(String[] GNo, int[] amount, String destinyWNo, String DNo) throws CapacityException, GoodsNotFoundException, SQLException, Exception {

        int sum = getSumCost();//总占量

        //检测容量
        PreparedStatement pstmt = connection.prepareStatement("SELECT ExcessCapacity FROM warehouse_capacity_view WHERE WNo=?");
        pstmt.setString(1, destinyWNo);
        ResultSet resultSet = pstmt.executeQuery();
        int excess;
        if (resultSet.next()) {
            excess = resultSet.getInt("ExcessCapacity");
            if (sum > excess) {
                throw new CapacityException("仓库容量不足");
            }
            //更新库存和记录
            else {
                for (int i = 0; i < GNo.length; ++i) {//更新库存
                    PreparedStatement select = connection.prepareStatement("SELECT * FROM stock WHERE WNo=? AND GNo=?;");
                    select.setString(1, destinyWNo);
                    select.setString(2, GNo[i]);

                    ResultSet selectSet = select.executeQuery();
                    if (selectSet.next()) {
                        PreparedStatement update = connection.prepareStatement("UPDATE stock SET Amount=Amount+? WHERE WNo=? AND GNo=?;");
                        update.setInt(1, amount[i]);
                        update.setString(2, destinyWNo);
                        update.setString(3, GNo[i]);
                        update.executeUpdate();
                    } else {
                        PreparedStatement insert = connection.prepareStatement("INSERT INTO stock(WNo,GNo,Amount) VALUES(?,?,?)");
                        insert.setString(1, destinyWNo);
                        insert.setString(2, GNo[i]);
                        insert.setInt(3, amount[i]);
                        insert.execute();
                    }

                }
                //更新记录
                String newRNo = getNewRNo();
                PreparedStatement insertPurchase = connection.prepareStatement("INSERT INTO purchaseinfo(RNo,DNo) VALUES(?,?);");
                insertPurchase.setString(1, newRNo);
                insertPurchase.setString(2, DNo);
                insertPurchase.execute();

                PreparedStatement insertRecord = connection.prepareStatement("INSERT INTO record(RNo,RUsage,Rtime) VALUES(?,?,?);");
                insertRecord.setString(1, newRNo);
                insertRecord.setString(2, "采购");
                insertRecord.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                insertRecord.execute();

                for (int i = 0; i < GNo.length; ++i) {
                    PreparedStatement insertRecordDetail =
                            connection.prepareStatement("INSERT INTO recorddetail(RNo,GNo,SourceWNo,Amount) VALUES(?,?,'',?)");
                    insertRecordDetail.setString(1, newRNo);
                    insertRecordDetail.setString(2, GNo[i]);
                    insertRecordDetail.setInt(3, amount[i]);
                    insertRecordDetail.execute();
                }


            }
        } else {//更新库存和记录
            for (int i = 0; i < GNo.length; ++i) {//更新库存
                PreparedStatement select = connection.prepareStatement("SELECT * FROM stock WHERE WNo=? AND GNo=?;");
                select.setString(1, destinyWNo);
                select.setString(2, GNo[i]);

                ResultSet selectSet = select.executeQuery();
                if (selectSet.next()) {
                    PreparedStatement update = connection.prepareStatement("UPDATE stock SET Amount=Amount+? WHERE WNo=? AND GNo=?;");
                    update.setInt(1, amount[i]);
                    update.setString(2, destinyWNo);
                    update.setString(3, GNo[i]);
                    update.executeUpdate();
                } else {
                    PreparedStatement insert = connection.prepareStatement("INSERT INTO stock(WNo,GNo,Amount) VALUES(?,?,?)");
                    insert.setString(1, destinyWNo);
                    insert.setString(2, GNo[i]);
                    insert.setInt(3, amount[i]);
                    insert.execute();
                }

            }
            //更新记录
            String newRNo = getNewRNo();
            PreparedStatement insertPurchase = connection.prepareStatement("INSERT INTO purchaseinfo(RNo,DNo) VALUES(?,?);");
            insertPurchase.setString(1, newRNo);
            insertPurchase.setString(2, DNo);
            insertPurchase.execute();

            PreparedStatement insertRecord = connection.prepareStatement("INSERT INTO record(RNo,RUsage,Rtime) VALUES(?,?,?);");
            insertRecord.setString(1, newRNo);
            insertRecord.setString(2, "采购");
            insertRecord.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            insertRecord.execute();

            for (int i = 0; i < GNo.length; ++i) {
                PreparedStatement insertRecordDetail =
                        connection.prepareStatement("INSERT INTO recorddetail(RNo,GNo,SourceWNo,Amount) VALUES(?,?,?,?)");
                insertRecordDetail.setString(1, newRNo);
                insertRecordDetail.setString(2, GNo[i]);
                insertRecordDetail.setString(3, "");
                insertRecordDetail.setInt(4, amount[i]);
                insertRecordDetail.execute();
            }

        }

    }

    public static void transfer(String[] GNo, int[] amount, String sourceWNo,String destinyWNo) throws Exception,CapacityException, GoodsNotFoundException,StorageException {
        int sum = getSumCost(GNo, amount);
        PreparedStatement pstmt = connection.prepareStatement("SELECT ExcessCapacity FROM warehouse_capacity_view WHERE WNo=?;");
        pstmt.setString(1, destinyWNo);
        ResultSet capacityQuery = pstmt.executeQuery();
        if (capacityQuery.next()) {
            int excess = capacityQuery.getInt("ExcessCapacity");
            if (sum > excess) {
                throw new CapacityException("仓库容量不足");
            }
        } else {
            throw new Exception("仓库不存在");
        }
        //来源仓库中物资数量是否足够
        for (int i = 0; i < GNo.length; ++i) {
            PreparedStatement storagePstmt = connection.prepareStatement("SELECT GNo,Amount FROM stock_view WHERE GNo=? AND WNo=?;");
            storagePstmt.setString(1,GNo[i]);
            storagePstmt.setString(2,sourceWNo);
            ResultSet storageQuery=storagePstmt.executeQuery();
            if(storageQuery.next()){
                int tmpAmount=storageQuery.getInt("Amount");
                if(tmpAmount<amount[i]){
                    throw new StorageException(GNo[i]+"物资数量不足");
                }
            }
            else{
                throw new Exception(destinyWNo+"仓库不存在");
            }
        }
        //将物资从来源仓库中调转至目的仓库中
        for(int i=0;i<GNo.length;++i){
            //若数量正好相同，则直接改仓库号；
            //若不同，则减去相应的数量，然后插入（原本没有）或更新加上相应数量
            PreparedStatement amountQuery=connection.prepareStatement("SELECT GNo FROM stock_view WHERE GNo=? AND WNo=?");
            amountQuery.setString(1,GNo[i]);
            amountQuery.setString(2,sourceWNo);
            ResultSet amountSet=amountQuery.executeQuery();
            if(amountSet.next()){
                int amountTmp=amountSet.getInt("GNo");
                //改仓库号
                if(amountTmp==amount[i]){
                    PreparedStatement pst=connection.
                            prepareStatement("UPDATE stock SET WNo=? WHERE WNo=? AND GNo=?");
                    pst.setString(1,destinyWNo);
                    pst.setString(2,destinyWNo);
                    pst.setString(3,GNo[i]);
                }
                else {
                    //减去相应的数量
                    PreparedStatement sub=connection.prepareStatement(
                            "UPDATE stock SET Amount=Amount-? WHERE WNo=? AND GNo=?"
                    );
                    sub.setInt(1,amount[i]);
                    sub.setString(2,sourceWNo);
                    sub.setString(3,GNo[i]);
                    sub.executeUpdate();
                    PreparedStatement insOrUpdQuery=connection.prepareStatement(
                            "SELECT GNo FROM stock_view WHERE GNo=? AND WNo=?");
                    insOrUpdQuery.setString(1,GNo[i]);
                    insOrUpdQuery.setString(2,destinyWNo);
                    ResultSet insOrUpdSet=insOrUpdQuery.executeQuery();
                    //如果目标仓库原本没有这种物资，插入
                    if(insOrUpdSet.next()){
                        PreparedStatement insert=connection.prepareStatement(
                                "INSERT INTO stock(WNo,GNo,Amount) VALUES(?,?,?);"
                        );
                        insert.setString(1,destinyWNo);
                        insert.setString(2,GNo[i]);
                        insert.setInt(3,amount[i]);
                        insert.execute();
                    }
                    //如果有这种物资，更新
                    else{
                        PreparedStatement update=connection.prepareStatement(
                                "UPDATE stock SET Amount=? WHERE WNo=? AND GNo=?;"
                        );
                        update.setInt(1,amount[i]);
                        update.setString(2,destinyWNo);
                        update.setString(3,GNo[i]);
                        update.executeUpdate();
                    }
                }

            }
        }

    }

    private static String getNewRNo() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("SELECT MAX(RNo) FROM record;");
        if (set.next()) {
            String tmpRNo = set.getString(1);
            long number = Long.valueOf(tmpRNo);
            ++number;
            String newRNo = String.valueOf(number);
            char[] chars = new char[]{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'};
            for (int i = 10 - newRNo.length(), j = 0; i < 10; ++i, ++j) {
                chars[i] = newRNo.charAt(j);
            }
            return String.valueOf(chars);
        } else {
            return "0000000000";
        }
    }

    private static int getSumCost(String[] GNo,int[] amount)throws Exception{
        int sum = 0;//总占量
        if (GNo.length != amount.length) {
            throw new Exception("物资数组与数量数组长度不同");
        }

        for (int i = 0; i < GNo.length; ++i) {//检测物资是否存在
            PreparedStatement pstmt = connection.prepareStatement("SELECT Cost FROM goods WHERE GNo=?;");
            pstmt.setString(1, GNo[i]);
            ResultSet resultSet = pstmt.executeQuery();
            int cost;
            if (resultSet.next()) {
                cost = resultSet.getInt("Cost");
            } else throw new GoodsNotFoundException("物资号为" + GNo[i] + "的物资不存在");
            sum += amount[i] * cost;
        }
        return sum;
    }
    public static void main(String[] args) throws Exception {
    }
}

class CapacityException extends Exception{
    CapacityException(){
        super();
    }
    CapacityException(String message){
        super(message);
    }
}
class GoodsNotFoundException extends Exception{
    GoodsNotFoundException(){
        super();
    }
    GoodsNotFoundException(String message){
        super(message);
    }
}