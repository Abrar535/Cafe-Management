/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_cafe.management;

/**
 *
 * @author USER
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class MyDatabaseHandler {
    Connection connect = null;
    
   public void connectDatabase(){
        try{
           //Class.forName("com.mysql.jdbc.Driver");
         
            //Class.forName("something.jdbc.driver.YourFubarDriver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/cafe","root","");
            System.out.println("Successfully Connected to Mysql");

        }catch(Exception e){
            System.out.println("Not Connected..");
            e.printStackTrace();
        }
    }
    public ResultSet testQuery(String category){
            ResultSet resultSet = null;
            //System.out.println("YES "+category);
        try{
            String query = "SELECT * FROM item where Item_Category = '"+category+"' ";
            Statement statement ;
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);
            
//            String query = "SELECT * FROM student WHERE student_name =? and id_student =?";
//            PreparedStatement pStatement = connect.prepareStatement(query);
//            pStatement.setString(1, "ABC");
//            pStatement.setString(2, "1");
//            resultSet = pStatement.executeQuery();
            
            System.out.println("Successfully Done Query..");
           
        }catch(Exception e){
            System.out.println("Error in Query..");
            e.printStackTrace();
        }           
        return resultSet;
    }
    
    public ResultSet testOrder(String filter){
            ResultSet resultSet = null;
            //System.out.println("YES "+category);
        try{
            String query = "select order.OrderId,Date,item.ItemNo,Item_Name,Quantity from `order`,order_item,item where `order`.OrderId = order_item.OrderId and order_item.ItemNo = item.ItemNo order by "+filter+" desc";
            Statement statement ;
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);
            
//            String query = "SELECT * FROM student WHERE student_name =? and id_student =?";
//            PreparedStatement pStatement = connect.prepareStatement(query);
//            pStatement.setString(1, "ABC");
//            pStatement.setString(2, "1");
//            resultSet = pStatement.executeQuery();
            
            System.out.println("Successfully Done Query..");
           
        }catch(Exception e){
            System.out.println("Error in Query..");
            e.printStackTrace();
        }           
        return resultSet;
    }
    
    public ResultSet testBill(String filter){
            ResultSet resultSet = null;
            //System.out.println("YES "+category);
        try{
            String query = "select * from bill order by "+filter+"";
            Statement statement ;
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);
            
//            String query = "SELECT * FROM student WHERE student_name =? and id_student =?";
//            PreparedStatement pStatement = connect.prepareStatement(query);
//            pStatement.setString(1, "ABC");
//            pStatement.setString(2, "1");
//            resultSet = pStatement.executeQuery();
            
            System.out.println("Successfully Done Query..");
           
        }catch(Exception e){
            System.out.println("Error in Query..");
            e.printStackTrace();
        }           
        return resultSet;
    }
    public int getCount_order() throws SQLException{
            ResultSet resultSet = null;
          
        try{
            String query = "select OrderId from `order`";
            Statement statement ;
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);
            
            System.out.println("Successfully Done Query..");
           
        }catch(Exception e){
            System.out.println("Error in Query..");
            e.printStackTrace();
        }           
        
        int ret = 0;
        
        while(resultSet.next()){
            ret = resultSet.getInt("OrderId");
        }
        
        return ret;
    }
    
    public void showResult(ResultSet resultSet) {
        try{
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                //String name = resultSet.getString("student_name");
                //String dept_name = resultSet.getString("student_dept");
                //double total_cred = resultSet.getDouble("student_credit");         
                System.out.println("Id: "+id );
            }
        }catch(Exception e){
            e.printStackTrace();
        }      
    }
     public void insertData_bill(double bill,int vat,double billvat,int id){
        try{
            //String query = "INSERT INTO student (student_name,student_dept,student_credit) values(?,?,?)";
            String query = "INSERT INTO bill (TotalBill,Vat,TotalBillVat,OrderId) values(?,?,?,?)";
            PreparedStatement pStatement = connect.prepareStatement(query);
            /* pStatement.setString(1, "ABCD");
            pStatement.setString(2, "Comp.Sci.");
            pStatement.setString(3, "125.2"); */
            
            pStatement.setDouble(1, bill);
            pStatement.setInt(2, vat);
            pStatement.setDouble(3, billvat);
            pStatement.setInt(4, id);
           
            pStatement.executeUpdate();
            
            System.out.println("Successfully Insert..");
            
            
        }catch(Exception e){
            System.out.println("Error in inserting");
            e.printStackTrace();
        }
    }
     public void insertData_order(int id){
        try{
            //String query = "INSERT INTO student (student_name,student_dept,student_credit) values(?,?,?)";
            String query = "INSERT INTO `order` (CustomerId) values(?)";
            PreparedStatement pStatement = connect.prepareStatement(query);
            /* pStatement.setString(1, "ABCD");
            pStatement.setString(2, "Comp.Sci.");
            pStatement.setString(3, "125.2"); */
            
            pStatement.setInt(1, id);
           
            pStatement.executeUpdate();
            
            System.out.println("Successfully Insert..");
            
            
        }catch(Exception e){
            System.out.println("Error in inserting");
            e.printStackTrace();
        }
    }
    
    
      public void insertData_order_item(int id,int no,int quantity){
        try{
            //String query = "INSERT INTO student (student_name,student_dept,student_credit) values(?,?,?)";
            String query = "INSERT INTO order_item (OrderId,ItemNo,Quantity) values(?,?,?)";
            PreparedStatement pStatement = connect.prepareStatement(query);
            /* pStatement.setString(1, "ABCD");
            pStatement.setString(2, "Comp.Sci.");
            pStatement.setString(3, "125.2"); */
            
            pStatement.setInt(1, id);
            pStatement.setInt(2, no);
            pStatement.setInt(3, quantity);
           
            pStatement.executeUpdate();
            
            System.out.println("Successfully Insert..");
            
            
        }catch(Exception e){
            System.out.println("Error in inserting");
            e.printStackTrace();
        }
    }
      
    public void insertData_item(String name,double price,String cat,int stock,String ing){
        try{
            //String query = "INSERT INTO student (student_name,student_dept,student_credit) values(?,?,?)";
            String query = "INSERT INTO item (Item_Name,Item_Price,Item_Category,Item_Stock,Item_Ingredients) values(?,?,?,?,?)";
            PreparedStatement pStatement = connect.prepareStatement(query);
            /* pStatement.setString(1, "ABCD");
            pStatement.setString(2, "Comp.Sci.");
            pStatement.setString(3, "125.2"); */
            
            pStatement.setString(1, name);
            pStatement.setDouble(2,price);
            pStatement.setString(3, cat);
            pStatement.setInt(4, stock);
            pStatement.setString(5, ing);
           
            pStatement.executeUpdate();
            
            System.out.println("Successfully Insert..");
            
            
        }catch(Exception e){
            System.out.println("Error in inserting");
            e.printStackTrace();
        }
    }
    
   public void deleteData(String st){
            ResultSet resultSet = null;
            //System.out.println("YES "+category);
        try{
            String query = "delete from item where Item_Name = '"+st+"'";
            //Statement statement ;
            //statement = connect.createStatement();
            PreparedStatement pStatement = connect.prepareStatement(query);
            pStatement.executeUpdate();
            
//            String query = "SELECT * FROM student WHERE student_name =? and id_student =?";
//            PreparedStatement pStatement = connect.prepareStatement(query);
//            pStatement.setString(1, "ABC");
//            pStatement.setString(2, "1");
//            resultSet = pStatement.executeQuery();
            
            System.out.println("Successfully Done Query..");
           
        }catch(Exception e){
            System.out.println("Error in Query..");
            e.printStackTrace();
        }           
        
    }
    public void insertData(){
        try{
            //String query = "INSERT INTO student (student_name,student_dept,student_credit) values(?,?,?)";
            String query = "INSERT INTO student (id,age) values(?,?)";
            PreparedStatement pStatement = connect.prepareStatement(query);
            /* pStatement.setString(1, "ABCD");
            pStatement.setString(2, "Comp.Sci.");
            pStatement.setString(3, "125.2"); */
            
            pStatement.setInt(1, 99);
            pStatement.setInt(2, 88);
           
            pStatement.executeUpdate();
            
            System.out.println("Successfully Insert..");
            
            
        }catch(Exception e){
            System.out.println("Error in inserting");
            e.printStackTrace();
        }
    }
}
