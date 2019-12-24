package service;

import exception.*;
import model.Goods;
import view.*;

import exception.CapacityException;
import exception.GoodsNotFoundException;
import exception.NameOrPasswordException;
import exception.StorageException;
import model.RecordDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataProcessing {
    public static final String URL = "jdbc:mysql://111.229.228.57:3306/warehouse_db?serverTimezone=GMT%2B8";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    public static Connection connection;
    public static final String USAGE_PURCHASE="purchase";
    public static final String USAGE_TRANSFER="rearrange";
    public static final String USAGE_SELL="sell";

    static {
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            //2. 获得数据库连接
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void purchase(String[] GNo, int[] amount, String destinyWNo, String DNo) throws CapacityException, GoodsNotFoundException, SQLException, Exception {

        int sum = getSumCost(GNo, amount);//总占量

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
                PreparedStatement insertPurchase = connection.prepareStatement("INSERT INTO purchaseinfo(RNo,DNo,DestWNo) VALUES(?,?,?);");
                insertPurchase.setString(1, newRNo);
                insertPurchase.setString(2, DNo);
                insertPurchase.setString(3,destinyWNo);
                insertPurchase.execute();

                PreparedStatement insertRecord = connection.prepareStatement("INSERT INTO record(RNo,RUsage,Rtime) VALUES(?,?,?);");
                insertRecord.setString(1, newRNo);
                insertRecord.setString(2,USAGE_PURCHASE);
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
            PreparedStatement insertPurchase = connection.prepareStatement("INSERT INTO purchaseinfo(RNo,DNo,DestWNo) VALUES(?,?);");
            insertPurchase.setString(1, newRNo);
            insertPurchase.setString(2, DNo);
            insertPurchase.setString(3,destinyWNo);
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

    public static void transfer(String[] GNo, int[] amount, String sourceWNo,String destinyWNo,String ANo) throws Exception,CapacityException, GoodsNotFoundException,StorageException {
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
                PreparedStatement wareQuery = connection.prepareStatement("SELECT WNo FROM warehouse WHERE WNo=?;");
                wareQuery.setString(1,sourceWNo);
                ResultSet wareSet=storagePstmt.executeQuery();
                if(!wareSet.next()){
                    throw new Exception(destinyWNo + "仓库不存在");
                }
                else{
                    throw new StorageException(GNo[i]+"物资数量不足");
                }
            }
        }
        //将物资从来源仓库中调转至目的仓库中
        String newRNo=getNewRNo();
        for(int i=0;i<GNo.length;++i){
            //若数量正好相同，则直接改仓库号；
            //若不同，则减去相应的数量，然后插入（原本没有）或更新加上相应数量
            PreparedStatement amountQuery=connection.prepareStatement
                    ("SELECT Amount FROM stock_view WHERE GNo=? AND WNo=?");
            amountQuery.setString(1,GNo[i]);
            amountQuery.setString(2,sourceWNo);
            ResultSet amountSet=amountQuery.executeQuery();
            if(amountSet.next()){
                int amountTmp=amountSet.getInt("Amount");
                //改仓库号
                if(amountTmp==amount[i]){
                    PreparedStatement pst=connection.
                            prepareStatement("UPDATE stock SET WNo=? WHERE WNo=? AND GNo=?");
                    pst.setString(1,destinyWNo);
                    pst.setString(2,sourceWNo);
                    pst.setString(3,GNo[i]);
                    pst.executeUpdate();
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
                    if(!insOrUpdSet.next()){
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
                                "UPDATE stock SET Amount=Amount+? WHERE WNo=? AND GNo=?;"
                        );
                        update.setInt(1,amount[i]);
                        update.setString(2,destinyWNo);
                        update.setString(3,GNo[i]);
                        update.executeUpdate();
                    }
                }
                //库存信息更新完毕，下面开始更新记录
                //record只需更新一次，recorddetail需要更新多次，transferinfo需要更新多次
                if(i==0) {
                    PreparedStatement record = connection.prepareStatement
                            ("INSERT INTO record (RNo,RUsage,Rtime) VALUES(?,?,?);");
                    record.setString(1, newRNo);
                    record.setString(2, USAGE_TRANSFER);
                    record.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                    record.execute();
                }
                //recorddetail更新
                PreparedStatement recorddetail=connection.prepareStatement
                        ("INSERT INTO recorddetail (RNo,GNo,SourceWNo,Amount) " +
                                "VALUES(?,?,?,?);");
                recorddetail.setString(1,newRNo);
                recorddetail.setString(2,GNo[i]);
                recorddetail.setString(3,sourceWNo);
                recorddetail.setInt(4,amount[i]);
                recorddetail.execute();
                //transferinfo更新
                PreparedStatement transferinfo=connection.prepareStatement
                        ("INSERT INTO transferinfo (RNo,GNo,ANo,DestWNo)" +
                                "VALUES (?,?,?,?);");
                transferinfo.setString(1,newRNo);
                transferinfo.setString(2,GNo[i]);
                transferinfo.setString(3,ANo);
                transferinfo.setString(4,destinyWNo);
                transferinfo.execute();
            }
        }

    }

    public static boolean logIn(String name,String password)throws SQLException {
        PreparedStatement pstmt=connection.prepareStatement
                ("SELECT * FROM administrator WHERE ANo=? AND Password=?;");
        pstmt.setString(1,name);
        pstmt.setString(2,password);
        ResultSet resultSet=pstmt.executeQuery();
        if(resultSet.next()){
            return true;
        }
        else{
            return false;
        }
    }

    public static void sell(String sourceWNo,String clientNo,
                            String[] GNo,int[] amount) throws Exception{
        if(GNo.length!=amount.length){
            throw new Exception("物资号与物资数量数组长度不同");
        }
        //检查物资是否足够,
        //比较所需数量和仓库中已有数量
        //代表相等，1代表有多余
        int[] excessStatus=new int[GNo.length];
        for(int i=0;i<GNo.length;++i){
            PreparedStatement fullQuery=connection.prepareStatement
                    ("SELECT Amount FROM stock WHERE WNo=? AND GNo=?;");
            fullQuery.setString(1,sourceWNo);
            fullQuery.setString(2,GNo[i]);
            ResultSet fullSet=fullQuery.executeQuery();
            if(fullSet.next()){
                int tmpAmount=fullSet.getInt("Amount");
                if(amount[i]>tmpAmount){
                    throw new StorageException("物资数量不足");
                }
                else if(amount[i]==tmpAmount){
                    excessStatus[i]=0;
                }
                else if(amount[i]<tmpAmount){
                    excessStatus[i]=1;
                }
            }
            else {
                throw new StorageException(sourceWNo+"仓库中不存在物资"+GNo);
            }
        }
        //物资足够，进行调动
        for(int i=0;i<GNo.length;++i){
            //若物资数量相等，删去此行
            if(excessStatus[i]==0){
                PreparedStatement deleteStock=connection.prepareStatement
                        ("DELETE FROM stock WHERE WNo=? AND GNo=?;");
                deleteStock.setString(1,sourceWNo);
                deleteStock.setString(2,GNo[i]);
                deleteStock.execute();
            }
            //若物资数量不同，减去相应的数量
            else if(excessStatus[i]==1){
                PreparedStatement updateStock=connection.prepareStatement
                        ("UPDATE stock SET Amount=Amount-? WHERE WNo=? AND GNo=?;");
                updateStock.setInt(1,amount[i]);
                updateStock.setString(2,sourceWNo);
                updateStock.setString(3,GNo[i]);
                updateStock.executeUpdate();
            }
        }
        //产生记录
        String newRNo=getNewRNo();
        //record
        PreparedStatement record = connection.prepareStatement
                ("INSERT INTO record (RNo,RUsage,Rtime) VALUES(?,?,?);");
        record.setString(1, newRNo);
        record.setString(2, USAGE_SELL);
        record.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
        record.execute();
        //recorddetail
        for(int i=0;i< GNo.length;++i) {
            PreparedStatement recorddetail = connection.prepareStatement
                    ("INSERT INTO recorddetail (RNo,GNo,SourceWNo,Amount) VALUES(?,?,?,?);");
            recorddetail.setString(1, newRNo);
            recorddetail.setString(2, GNo[i]);
            recorddetail.setString(3,sourceWNo);
            recorddetail.setInt(4,amount[i]);
            recorddetail.execute();
        }
        //deliveryinfo
        PreparedStatement deliveryinfo=connection.prepareStatement
                ("INSERT INTO deliveryinfo (RNo,ClientNo) VALUES (?,?);");
        deliveryinfo.setString(1,newRNo);
        deliveryinfo.setString(2,clientNo);
        deliveryinfo.execute();
    }

    public static void addClient(String clientNo,String clientName)throws SQLException{
        PreparedStatement insert=connection.prepareStatement
                ("INSERT INTO client (ClientNo,ClientName) VALUES(?,?);");
        insert.setString(1,clientNo);
        insert.setString(2,clientName);
        insert.execute();
    }

    public static void addAdministrator(String ANo,String password)throws SQLException{

        PreparedStatement insert=connection.prepareStatement
                ("INSERT INTO administrator (ANo,Password) VALUES(?,?);");
        insert.setString(1,ANo);
        insert.setString(2, password);
        insert.execute();
    }

    public static void addDepartment(String DNo,String Dname)throws SQLException{

        PreparedStatement insert=connection.prepareStatement
                ("INSERT INTO department (DNo,Dname) VALUES(?,?);");
        insert.setString(1,DNo);
        insert.setString(2,Dname);
        insert.execute();
    }

    public static void addWarehouse(String WNo,String WName,int capacity)throws SQLException{

        PreparedStatement insert=connection.prepareStatement
                ("INSERT INTO warehouse (WNo,WName,Capacity) VALUES(?,?,?);");
        insert.setString(1,WNo);
        insert.setString(2,WName);
        insert.setInt(3,capacity);
        insert.execute();
    }

    private static String getNewRNo() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("SELECT MAX(RNo) FROM record;");
        if (set.next()) {
            String tmpRNo = set.getString(1);
            if(tmpRNo==null){
                return "0000000000";
            }
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
    private static String getNewGNo() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("SELECT MAX(GNo) FROM goods;");
        if (set.next()) {
            String tmpGNo = set.getString(1);
            if(tmpGNo==null) return "00000000";
            long number = Long.valueOf(tmpGNo);
            ++number;
            String newGNo = String.valueOf(number);
            char[] chars = new char[]{'0', '0', '0', '0', '0', '0', '0', '0'};
            for (int i = 8 - newGNo.length(), j = 0; i < 8; ++i, ++j) {
                chars[i] = newGNo.charAt(j);
            }
            return String.valueOf(chars);
        } else {
            return "00000000";
        }
    }
    public static void addGoods(String name,int cost,int price) throws Exception, NameRepeatException {
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("SELECT Gname FROM goods;");
        while(set.next()){
            String tmpGName = set.getString(1);
            if(tmpGName.equals(name))
                throw new NameRepeatException("物资名与仓库中已有物资重复");
        }
        PreparedStatement insertRecordDetail =
                connection.prepareStatement("INSERT INTO goods(GNo,GName,Cost,Price) VALUES(?,?,?,?)");
        insertRecordDetail.setString(1, getNewGNo());
        insertRecordDetail.setString(2, name);
        insertRecordDetail.setInt(3,cost);
        insertRecordDetail.setInt(4, price);
        insertRecordDetail.execute();

    }
    public static List getRecordDetail(Timestamp Time1,Timestamp Time2,String Usage,String gNo,String cNo ,String sourceWNo ,String destWNo) throws SQLException
    {

        //根据时间、gno\destwno\sourcewno\destwno查询物资调转记录
        if(Usage.equals("rearrange"))
        { String str="select * from transfer_view where time (between ? and ?) and Rusage=?" +
                " and GNo=?  and SourceWNo=? and   DestWNo=? oder by RTime";
           PreparedStatement ptmt = connection.prepareStatement(str);
           ptmt.setTimestamp(1,Time1);
           ptmt.setTimestamp(2,Time2);
           ptmt.setString(3,Usage);
           ptmt.setString(4,gNo);
           ptmt.setString(5,sourceWNo);
           ptmt.setString(6,destWNo);
            ResultSet set = ptmt.executeQuery();
            List<TransferView> tfview=new ArrayList<>();
           TransferView t=null;
            while(set.next()){
                t=new TransferView();
                t.setDestWNo(set.getString("DestWNo").toCharArray());
                t.setGNo(set.getString("GNo").toCharArray());
                t.setRNo(set.getString("RNo").toCharArray());
                t.setRTime(set.getTimestamp("RTime"));
                t.setSourceWNo(set.getString("SourceWNo").toCharArray());
                t.setAmount(set.getInt("Amount"));
                tfview.add(t);
            }
            return  tfview;
        }
        //根据时间、gno\clientno\sourcewno查找出售记录单
        else if(Usage.equals("sell"))
        { String str="select * from order_view where time  (between ? and ?) and Rusage=?" +
                " and GNo=? and ClientNo=?  oder by RTime";
            PreparedStatement ptmt = connection.prepareStatement(str);
            ptmt.setTimestamp(1,Time1);
            ptmt.setTimestamp(2,Time2);
            ptmt.setString(3,Usage);
            ptmt.setString(4,gNo);
            ptmt.setString(5,cNo);
            ResultSet set = ptmt.executeQuery();
            List<OrderView> orderview=new ArrayList<>();
            OrderView t=null;
            while(set.next()){
                t=new OrderView();
                t.setClientNo(set.getString("ClientNo").toCharArray());
                t.setClientName(set.getString("ClientName").toCharArray());
                t.setRNo(set.getString("RNo").toCharArray());
                t.setRtime(set.getTimestamp("RTime"));
                t.setSourceWNo(set.getString("SourceWNo").toCharArray());
                t.setAmount(set.getInt("Amount"));
                orderview.add(t);
            }
            return  orderview;
        }
        //根据gno\time查采购记录
        else if(Usage.equals("purchase"))
        {
            String str="select * from purchase_view where time (between ? and ?) and Rusage=?" +
                    " and GNo=? oder by RTime";
            PreparedStatement ptmt = connection.prepareStatement(str);
            ptmt.setTimestamp(1,Time1);
            ptmt.setTimestamp(2,Time2);
            ptmt.setString(3,Usage.toString());
            ptmt.setString(4,gNo.toString());
            ResultSet set = ptmt.executeQuery();
            List<PurchaseView> pview=new ArrayList<>();
            PurchaseView t=null;
            while(set.next()){
                t=new PurchaseView();
                t.setGName(set.getString("GName").toCharArray());
                t.setGNo(set.getString("GNo").toCharArray());
                t.setRNo(set.getString("RNo").toCharArray());
                t.setRTime(set.getTimestamp("RTime"));
                t.setSourceWNo(set.getString("SourceWNo").toCharArray());
                t.setAmount(set.getInt("Amount"));
                pview.add(t);
            }
            return  pview;
        }
        else {
            new RecordNotFoundException("没有找到对应的记录");
            return null;
        }
    }
    public static List<Goods> getAllGoods()throws SQLException{
        Statement statement=connection.createStatement();
        ResultSet set=statement.executeQuery
                ("SELECT * FROM goods;");
        List<Goods> goodsList=new ArrayList<>();
        while(set.next()){
            Goods goods=new Goods();
            goods.setGNO(set.getString("GNo").toCharArray());
            goods.setGName(set.getString("GName").toCharArray());
            goods.setCost(set.getInt("Cost"));
            goods.setPrice(set.getInt("Price"));
            goodsList.add(goods);
        }
        return goodsList;
    }
    public static List<WarehouseCapacityView> getWarehouseCapacityView(String name,String num) throws SQLException {
        String str="select * from warehouse_capacity_view where WNo=? and WName=?";
        PreparedStatement ptmt = connection.prepareStatement(str);
        if(!num.isEmpty())
        ptmt.setString(1,num);
        if(!name.isEmpty())
        ptmt.setString(2,name);
        ResultSet set = ptmt.executeQuery();
        List<WarehouseCapacityView> warecapview=new ArrayList<>();
        WarehouseCapacityView wcp=null;
        while(set.next()){
            wcp=new WarehouseCapacityView();
            wcp.setWNo(set.getString("WNo").toCharArray());
            wcp.setWName(set.getString("WName").toCharArray());
            wcp.setCapacity(set.getInt("Capacity"));
            wcp.setExcessCapacity(set.getInt("ExcessCapacity"));
            warecapview.add(wcp);
        }
        return warecapview;

    }
    public static List<WarehouseCapacityView> getAllWarehouseCapacityView() throws SQLException {
        Statement statement = connection.createStatement();
        String str="select * from warehouse_capacity_view order by WNo";
        ResultSet set = statement.executeQuery(str);
        List<WarehouseCapacityView> warecapview=new ArrayList<>();
        WarehouseCapacityView wcp=null;
        while(set.next()){
            wcp=new WarehouseCapacityView();
            wcp.setWNo(set.getString("WNo").toCharArray());
            wcp.setWName(set.getString("WName").toCharArray());
            wcp.setCapacity(set.getInt("Capacity"));
            wcp.setExcessCapacity(set.getInt("ExcessCapacity"));
            warecapview.add(wcp);
        }
        return warecapview;

    }
    public static List<StockView> getStockView(String GoodsNo,String WareNo) throws SQLException {

        String str="select * from stock_view where GNo=? and WNo=? order by WNo";
        PreparedStatement ptmt = connection.prepareStatement(str);
        if(!GoodsNo.isEmpty())
        ptmt.setString(1,GoodsNo);
        if(!WareNo.isEmpty())
        ptmt.setString(2,WareNo);
        ResultSet set = ptmt.executeQuery();
        List<StockView> warecapview=new ArrayList<>();
        StockView sw=null;
        while(set.next()){
            sw=new StockView();
            sw.setWNo(set.getString("WNo").toCharArray());
            sw.setWName(set.getString("WName").toCharArray());
            sw.setGName(set.getString("GName").toCharArray());
            sw.setGNo(set.getString("GNo").toCharArray());
            sw.setAmount(set.getInt("Amount"));
            warecapview.add(sw);
        }
        return warecapview;

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


