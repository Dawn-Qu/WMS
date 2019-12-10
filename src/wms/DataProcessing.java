package wms;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
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
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void purchase(String [] GNo,int[] amount,String destinyWNo,String DNo) throws CapacityException,GoodsNotFoundException,SQLException,Exception{

        int sum=0;
        if(GNo.length!=amount.length){
            throw new Exception("物资数组与数量数组长度不同");
        }

        for(int i=0;i<GNo.length;++i){
            PreparedStatement pstmt=connection.prepareStatement("SELECT Cost FROM goods WHERE GNo=?;");
            pstmt.setString(1,GNo[i]);
            ResultSet resultSet= pstmt.executeQuery();
            int cost;
            if(resultSet.next()){
                cost=resultSet.getInt("Cost");
            }
            else throw new GoodsNotFoundException("物资号为"+GNo[i]+"的物资不存在");
            sum+=amount[i]*cost;
        }
        PreparedStatement pstmt=connection.prepareStatement("SELECT ExcessCapacity FROM warehouse_capacity_view WHERE WNo=?");
        pstmt.setString(1,destinyWNo);
        ResultSet resultSet=pstmt.executeQuery();
        int excess;
        if(resultSet.next()) {
            excess = resultSet.getInt("ExcessCapacity");
            if (sum > excess) {
                throw new CapacityException("仓库容量不足");
            }
            else{//更新库存和记录
                for(int i=0;i<GNo.length;++i) {//更新库存
                    PreparedStatement select=connection.prepareStatement("SELECT * FROM stock WHERE WNo=? AND GNo=?;");
                    select.setString(1,destinyWNo);
                    select.setString(2,GNo[i]);

                    ResultSet selectSet=select.executeQuery();
                    if(selectSet.next()){
                        PreparedStatement update = connection.prepareStatement("UPDATE stock SET Amount=Amount+? WHERE WNo=? AND GNo=?;");
                        update.setInt(1,amount[i]);
                        update.setString(2,destinyWNo);
                        update.setString(3,GNo[i]);
                        update.executeUpdate();
                    }
                    else {
                        PreparedStatement insert = connection.prepareStatement("INSERT INTO stock(WNo,GNo,Amount) VALUES(?,?,?)");
                        insert.setString(1, destinyWNo);
                        insert.setString(2, GNo[i]);
                        insert.setInt(3, amount[i]);
                        insert.execute();
                    }

                }
                //更新记录
                String newRNo=getNewRNo();
                PreparedStatement insertPurchase=connection.prepareStatement("INSERT INTO purchaseinfo(RNo,DNo) VALUES(?,?);");
                insertPurchase.setString(1,newRNo);
                insertPurchase.setString(2,DNo);
                insertPurchase.execute();

                PreparedStatement insertRecord=connection.prepareStatement("INSERT INTO record(RNo,RUsage,Rtime) VALUES(?,?,?);");
                insertRecord.setString(1,newRNo);
                insertRecord.setString(2,"采购");
                insertRecord.setTimestamp(3,new Timestamp(System.currentTimeMillis()));
                insertRecord.execute();

                for(int i=0;i<GNo.length;++i) {
                    PreparedStatement insertRecordDetail =
                            connection.prepareStatement("INSERT INTO recorddetail(RNo,GNo,SourceWNo,Amount) VALUES(?,?,'',?)");
                    insertRecordDetail.setString(1,newRNo);
                    insertRecordDetail.setString(2,GNo[i]);
                    insertRecordDetail.setInt(3,amount[i]);
                    insertRecordDetail.execute();
                }


            }
        }
        else{//更新库存和记录
            for(int i=0;i<GNo.length;++i) {//更新库存
                PreparedStatement select=connection.prepareStatement("SELECT * FROM stock WHERE WNo=? AND GNo=?;");
                select.setString(1,destinyWNo);
                select.setString(2,GNo[i]);

                ResultSet selectSet=select.executeQuery();
                if(selectSet.next()){
                    PreparedStatement update = connection.prepareStatement("UPDATE stock SET Amount=Amount+? WHERE WNo=? AND GNo=?;");
                    update.setInt(1,amount[i]);
                    update.setString(2,destinyWNo);
                    update.setString(3,GNo[i]);
                    update.executeUpdate();
                }
                else {
                    PreparedStatement insert = connection.prepareStatement("INSERT INTO stock(WNo,GNo,Amount) VALUES(?,?,?)");
                    insert.setString(1, destinyWNo);
                    insert.setString(2, GNo[i]);
                    insert.setInt(3, amount[i]);
                    insert.execute();
                }

            }
            //更新记录
            String newRNo=getNewRNo();
            PreparedStatement insertPurchase=connection.prepareStatement("INSERT INTO purchaseinfo(RNo,DNo) VALUES(?,?);");
            insertPurchase.setString(1,newRNo);
            insertPurchase.setString(2,DNo);
            insertPurchase.execute();

            PreparedStatement insertRecord=connection.prepareStatement("INSERT INTO record(RNo,RUsage,Rtime) VALUES(?,?,?);");
            insertRecord.setString(1,newRNo);
            insertRecord.setString(2,"采购");
            insertRecord.setTimestamp(3,new Timestamp(System.currentTimeMillis()));
            insertRecord.execute();

            for(int i=0;i<GNo.length;++i) {
                PreparedStatement insertRecordDetail =
                        connection.prepareStatement("INSERT INTO recorddetail(RNo,GNo,SourceWNo,Amount) VALUES(?,?,?,?)");
                insertRecordDetail.setString(1,newRNo);
                insertRecordDetail.setString(2,GNo[i]);
                insertRecordDetail.setString(3,"");
                insertRecordDetail.setInt(4,amount[i]);
                insertRecordDetail.execute();
            }

        }

    }

    private static String getNewRNo()throws SQLException{
        Statement statement=connection.createStatement();
        ResultSet set= statement.executeQuery("SELECT MAX(RNo) FROM record;");
        if(set.next()){
            String tmpRNo=set.getString(1);
            long number=Long.valueOf(tmpRNo);
            ++number;
            String newRNo=String.valueOf(number);
            char[] chars=new char[]{'0','0','0','0','0','0','0','0','0','0'};
            for(int i=10-newRNo.length(),j=0;i<10;++i,++j){
                chars[i]=newRNo.charAt(j);
            }
            return String.valueOf(chars);
        }
        else{
            return "0000000000";
        }
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