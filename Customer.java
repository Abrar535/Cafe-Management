/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_cafe.management;

import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author USER
 */
public class Customer extends javax.swing.JFrame {

    /**
     * Creates new form Customer
     */
    
    MyDatabaseHandler ob;
    int o_id;
    int cur = 0;
    
    ArrayList<Item> items;
    ArrayList<Integer>orders;
    HashMap<Integer,Integer> mp;
    HashMap<Integer,Double> pr;
    
    public void setItem(int id)
    {
       if(id<items.size()) {
          show1.setVisible(true);
          item1.setText(items.get(id).Item_Name);
          price1.setText("Tk "+Integer.toString((int)items.get(id).Item_Price));
          ingredients1.setText(items.get(id).Item_Ingredients);
          quantity_stock1.setText("/ "+Integer.toString(items.get(id).Item_Stock));
          setImage(image1,item1.getText());
          quantity_text1.setText(Integer.toString(items.get(id).Current_Quantity));
          //item1.setText(items.get(id).Item_Name);
       }
       else show1.setVisible(false);
       id++;
       if(id<items.size()) {
          show2.setVisible(true);
         item2.setText(items.get(id).Item_Name);
          price2.setText("Tk "+Integer.toString((int)items.get(id).Item_Price));
          ingredients2.setText(items.get(id).Item_Ingredients);
          quantity_stock2.setText("/ "+Integer.toString(items.get(id).Item_Stock));
          setImage(image2,item2.getText());
          quantity_text2.setText(Integer.toString(items.get(id).Current_Quantity));
       }
       else show2.setVisible(false);
       id++;
       if(id<items.size()) {
          show3.setVisible(true);
          item3.setText(items.get(id).Item_Name);
          price3.setText("Tk "+Integer.toString((int)items.get(id).Item_Price));
          ingredients3.setText(items.get(id).Item_Ingredients);
          quantity_stock3.setText("/ "+Integer.toString(items.get(id).Item_Stock));
          setImage(image3,item3.getText());
          quantity_text3.setText(Integer.toString(items.get(id).Current_Quantity));
       }
       else show3.setVisible(false);
    }
    
    public void setArray(ResultSet res) throws SQLException
    {
       cur = 0;
       items.clear();
       while(res.next())
       {
          String name = res.getString("Item_Name");
          double price = res.getDouble("Item_Price");
          String category = res.getString("Item_Category");
          int stock = res.getInt("Item_Stock");
          String ingredients = res.getString("Item_Ingredients");
          int ItemNo = res.getInt("ItemNo");
          
          pr.put(ItemNo,price);
          Item tmp = new Item(name,price,category,stock,ingredients,0,ItemNo);
          items.add(tmp);
       }
       
       setItem(cur);
       
    }
   
    public void addTable(int i)
    {
        if(items.get(i).Current_Quantity==0) return;
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        Object row[] = new Object[4];
           
           row[0] = items.get(i).Item_Name;
           row[1] = items.get(i).Current_Quantity;
           row[2] = items.get(i).Item_Price;
           row[3] = items.get(i).Current_Quantity*items.get(i).Item_Price;
           model.addRow(row);
    }
    
    public void addTable1(ResultSet res) throws SQLException
    {
     
        DefaultTableModel model = (DefaultTableModel)order_table.getModel();
        Object row[] = new Object[4];
        
        while(res.next()){
           row[0] = res.getInt("order.OrderId");
           row[1] = res.getString("Date");
           row[2] = res.getString("Item_Name");
           row[3] = res.getInt("Quantity");
           model.addRow(row);
        }
    }
    
     public void addTable2(ResultSet res) throws SQLException
    {
     
        DefaultTableModel model = (DefaultTableModel)transaction_table.getModel();
        Object row[] = new Object[4];
        
        while(res.next()){
           row[0] = res.getInt("BillId");
           row[1] = res.getDouble("TotalBill");
           row[2] = res.getInt("Vat");
           row[3] = res.getDouble("TotalBillVat");
           model.addRow(row);
        }
    }
    
    
    public void setTrue(javax.swing.JPanel p){
        
       order_panel.setVisible(false );
       home_panel.setVisible(false);
       cart_panel.setVisible(false);
       category_panel.setVisible(false);
       admin_panel.setVisible(false);
       admin_pass.setVisible(false);
       p.setVisible(true); 
    }
   
    
    public void setImage(javax.swing.JLabel lab,String st)
    {
        String s = "image/"+st+".jpg";
        ImageIcon icon = new ImageIcon(s);
        lab.setIcon(icon);
    }
    
    public Customer() {
        initComponents();
        //setImage(image1,"burger");
        order_panel.setVisible(false );
        cart_panel.setVisible(false);
        category_panel.setVisible(false);
        admin_panel.setVisible(false);
        admin_pass.setVisible(false);
        home_panel.setVisible(true);
        //admin_order.setVisible(true);
        
        ob = new MyDatabaseHandler();
        ob.connectDatabase();
        items = new ArrayList<Item>();
        orders = new ArrayList<Integer>();
        
        mp = new HashMap<Integer, Integer>();
        pr = new HashMap<Integer, Double>();
        
        table.getTableHeader().setDefaultRenderer(new HeaderColor());
        table.setFillsViewportHeight(true);
        
        order_table.getTableHeader().setDefaultRenderer(new HeaderColor());
        order_table.setFillsViewportHeight(true);
        
        transaction_table.getTableHeader().setDefaultRenderer(new HeaderColor());
        transaction_table.setFillsViewportHeight(true);
       //table.setPreferredScrollableViewportSize(table.getPreferredSize());
       //table_panel.getViewport().setBackground(table.getBackground());
       o_id = 1;
      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        main_panel = new javax.swing.JPanel();
        side_panel = new javax.swing.JPanel();
        Offers = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        home1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Order = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Cart = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Admin = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        order_panel = new javax.swing.JPanel();
        show1 = new javax.swing.JPanel();
        image1 = new javax.swing.JLabel();
        quantity1 = new javax.swing.JPanel();
        quantity_text1 = new javax.swing.JLabel();
        plus1 = new javax.swing.JLabel();
        minus1 = new javax.swing.JLabel();
        quantity_stock1 = new javax.swing.JLabel();
        cart1 = new javax.swing.JPanel();
        add_to_cart1 = new javax.swing.JLabel();
        item1 = new javax.swing.JLabel();
        ingredients1 = new javax.swing.JLabel();
        ingredients_name4 = new javax.swing.JLabel();
        price1 = new javax.swing.JLabel();
        show2 = new javax.swing.JPanel();
        image2 = new javax.swing.JLabel();
        ingredients_name2 = new javax.swing.JLabel();
        cart2 = new javax.swing.JPanel();
        add_to_cart2 = new javax.swing.JLabel();
        item2 = new javax.swing.JLabel();
        ingredients2 = new javax.swing.JLabel();
        price2 = new javax.swing.JLabel();
        quantity2 = new javax.swing.JPanel();
        quantity_text2 = new javax.swing.JLabel();
        plus2 = new javax.swing.JLabel();
        minus2 = new javax.swing.JLabel();
        quantity_stock2 = new javax.swing.JLabel();
        show3 = new javax.swing.JPanel();
        image3 = new javax.swing.JLabel();
        ingredients_name3 = new javax.swing.JLabel();
        cart3 = new javax.swing.JPanel();
        add_to_cart3 = new javax.swing.JLabel();
        item3 = new javax.swing.JLabel();
        ingredients3 = new javax.swing.JLabel();
        price3 = new javax.swing.JLabel();
        quantity3 = new javax.swing.JPanel();
        quantity_text3 = new javax.swing.JLabel();
        plus3 = new javax.swing.JLabel();
        minus3 = new javax.swing.JLabel();
        quantity_stock3 = new javax.swing.JLabel();
        back_button = new javax.swing.JLabel();
        next_button = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        exit = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        settings = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        home_panel = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        Category_Header5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cart_panel = new javax.swing.JPanel();
        table_panel = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        item10 = new javax.swing.JLabel();
        confirm_button = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        total_bill_vat = new javax.swing.JLabel();
        total_bill = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        category_panel = new javax.swing.JPanel();
        show4c = new javax.swing.JPanel();
        image7 = new javax.swing.JLabel();
        item6 = new javax.swing.JLabel();
        show1c = new javax.swing.JPanel();
        image8 = new javax.swing.JLabel();
        item7 = new javax.swing.JLabel();
        show2c = new javax.swing.JPanel();
        image9 = new javax.swing.JLabel();
        item8 = new javax.swing.JLabel();
        show3c = new javax.swing.JPanel();
        image10 = new javax.swing.JLabel();
        item9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Category_Header = new javax.swing.JLabel();
        admin_panel = new javax.swing.JPanel();
        admin_add_item = new javax.swing.JPanel();
        ingredients_name5 = new javax.swing.JLabel();
        ingredients_field = new javax.swing.JTextField();
        ingredients_name7 = new javax.swing.JLabel();
        ingredients_name8 = new javax.swing.JLabel();
        ingredients_name9 = new javax.swing.JLabel();
        name_field = new javax.swing.JTextField();
        price_field = new javax.swing.JTextField();
        category_field = new javax.swing.JTextField();
        stock_field = new javax.swing.JTextField();
        ingredients_name10 = new javax.swing.JLabel();
        submit_button = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        admin_delete_item = new javax.swing.JPanel();
        delete_field = new javax.swing.JTextField();
        ingredients_name14 = new javax.swing.JLabel();
        delete_button = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        admin_order = new javax.swing.JPanel();
        table_panel1 = new javax.swing.JScrollPane();
        order_table = new javax.swing.JTable();
        quantity_radio = new javax.swing.JRadioButton();
        jLabel30 = new javax.swing.JLabel();
        item_radio = new javax.swing.JRadioButton();
        date_radio = new javax.swing.JRadioButton();
        jLabel33 = new javax.swing.JLabel();
        admin_transaction = new javax.swing.JPanel();
        table_panel3 = new javax.swing.JScrollPane();
        transaction_table = new javax.swing.JTable();
        jLabel32 = new javax.swing.JLabel();
        admin_pass = new javax.swing.JPanel();
        submit_button1 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        ingredients_name16 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        Category_Header1 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(180, 100));
        setUndecorated(true);

        main_panel.setBackground(new java.awt.Color(255, 255, 255));
        main_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        side_panel.setBackground(new java.awt.Color(30, 30, 30));
        side_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Offers.setBackground(new java.awt.Color(30, 30, 30));
        Offers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                OffersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OffersMouseExited(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Historic", 0, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(196, 196, 196));
        jLabel1.setText("Offers");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_cafe/management/images/icons8_Low_Price_23px.png"))); // NOI18N

        javax.swing.GroupLayout OffersLayout = new javax.swing.GroupLayout(Offers);
        Offers.setLayout(OffersLayout);
        OffersLayout.setHorizontalGroup(
            OffersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OffersLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );
        OffersLayout.setVerticalGroup(
            OffersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(OffersLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(OffersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        side_panel.add(Offers, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 210, 50));

        home1.setBackground(new java.awt.Color(30, 30, 30));
        home1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                home1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                home1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                home1MouseExited(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Historic", 0, 17)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(196, 196, 196));
        jLabel3.setText("Home");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_cafe/management/images/icons8_System_Information_23px_1.png"))); // NOI18N

        javax.swing.GroupLayout home1Layout = new javax.swing.GroupLayout(home1);
        home1.setLayout(home1Layout);
        home1Layout.setHorizontalGroup(
            home1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, home1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );
        home1Layout.setVerticalGroup(
            home1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, home1Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(home1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        side_panel.add(home1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, 50));

        Order.setBackground(new java.awt.Color(30, 30, 30));
        Order.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OrderMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                OrderMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OrderMouseExited(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Historic", 0, 17)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(196, 196, 196));
        jLabel5.setText("Order");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_cafe/management/images/icons8_Food_23px_1.png"))); // NOI18N

        javax.swing.GroupLayout OrderLayout = new javax.swing.GroupLayout(Order);
        Order.setLayout(OrderLayout);
        OrderLayout.setHorizontalGroup(
            OrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OrderLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );
        OrderLayout.setVerticalGroup(
            OrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OrderLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(OrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        side_panel.add(Order, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 210, 50));

        Cart.setBackground(new java.awt.Color(30, 30, 30));
        Cart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CartMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CartMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CartMouseExited(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI Historic", 0, 17)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(196, 196, 196));
        jLabel7.setText("Cart");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_cafe/management/images/icons8_Shopping_Cart_23px_1.png"))); // NOI18N

        javax.swing.GroupLayout CartLayout = new javax.swing.GroupLayout(Cart);
        Cart.setLayout(CartLayout);
        CartLayout.setHorizontalGroup(
            CartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CartLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );
        CartLayout.setVerticalGroup(
            CartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CartLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(CartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        side_panel.add(Cart, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 210, 50));

        Admin.setBackground(new java.awt.Color(30, 30, 30));
        Admin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AdminMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AdminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AdminMouseExited(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI Historic", 0, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(196, 196, 196));
        jLabel11.setText("Admin");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_cafe/management/images/icons8_Manager_23px.png"))); // NOI18N

        javax.swing.GroupLayout AdminLayout = new javax.swing.GroupLayout(Admin);
        Admin.setLayout(AdminLayout);
        AdminLayout.setHorizontalGroup(
            AdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdminLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );
        AdminLayout.setVerticalGroup(
            AdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdminLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(AdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        side_panel.add(Admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 210, 50));

        main_panel.add(side_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 530));

        order_panel.setBackground(new java.awt.Color(247, 246, 246));
        order_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        show1.setBackground(new java.awt.Color(234, 234, 234));
        show1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        image1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_cafe/management/images/burger.jpg"))); // NOI18N
        show1.add(image1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 200, 180));

        quantity1.setBackground(new java.awt.Color(158, 158, 0));
        quantity1.setForeground(new java.awt.Color(255, 255, 255));

        quantity_text1.setBackground(new java.awt.Color(204, 204, 0));
        quantity_text1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        quantity_text1.setForeground(new java.awt.Color(237, 237, 237));
        quantity_text1.setText("10");

        plus1.setBackground(new java.awt.Color(158, 158, 0));
        plus1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        plus1.setForeground(new java.awt.Color(237, 237, 237));
        plus1.setText("+");
        plus1.setOpaque(true);
        plus1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                plus1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                plus1MouseEntered(evt);
            }
        });

        minus1.setBackground(new java.awt.Color(204, 204, 0));
        minus1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        minus1.setForeground(new java.awt.Color(237, 237, 237));
        minus1.setText("  -");
        minus1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minus1MouseClicked(evt);
            }
        });

        quantity_stock1.setBackground(new java.awt.Color(204, 204, 0));
        quantity_stock1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        quantity_stock1.setForeground(new java.awt.Color(237, 237, 237));
        quantity_stock1.setText("/  0");

        javax.swing.GroupLayout quantity1Layout = new javax.swing.GroupLayout(quantity1);
        quantity1.setLayout(quantity1Layout);
        quantity1Layout.setHorizontalGroup(
            quantity1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(quantity1Layout.createSequentialGroup()
                .addComponent(minus1, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quantity_text1)
                .addGap(5, 5, 5)
                .addComponent(quantity_stock1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(plus1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        quantity1Layout.setVerticalGroup(
            quantity1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, quantity1Layout.createSequentialGroup()
                .addGroup(quantity1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, quantity1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(quantity_text1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(quantity_stock1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(minus1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(plus1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        show1.add(quantity1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 90, 20));

        cart1.setBackground(new java.awt.Color(158, 158, 0));
        cart1.setForeground(new java.awt.Color(255, 255, 255));
        cart1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cart1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cart1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cart1MouseExited(evt);
            }
        });

        add_to_cart1.setBackground(new java.awt.Color(204, 204, 0));
        add_to_cart1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        add_to_cart1.setForeground(new java.awt.Color(237, 237, 237));
        add_to_cart1.setText("Add to Cart");

        javax.swing.GroupLayout cart1Layout = new javax.swing.GroupLayout(cart1);
        cart1.setLayout(cart1Layout);
        cart1Layout.setHorizontalGroup(
            cart1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cart1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(add_to_cart1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cart1Layout.setVerticalGroup(
            cart1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(add_to_cart1, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        show1.add(cart1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 90, 20));

        item1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        item1.setForeground(new java.awt.Color(51, 51, 51));
        item1.setText("Mushroom Burger");
        show1.add(item1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, -1));

        ingredients1.setFont(new java.awt.Font("Segoe UI Semilight", 2, 13)); // NOI18N
        ingredients1.setForeground(new java.awt.Color(51, 51, 51));
        ingredients1.setText("Chicken, Beef, Cheese, Garlic");
        show1.add(ingredients1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 210, 30));

        ingredients_name4.setFont(new java.awt.Font("Segoe UI Semilight", 2, 13)); // NOI18N
        ingredients_name4.setForeground(new java.awt.Color(51, 51, 51));
        ingredients_name4.setText("Ingredients ");
        show1.add(ingredients_name4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 70, 30));

        price1.setFont(new java.awt.Font("Segoe UI Semilight", 2, 13)); // NOI18N
        price1.setForeground(new java.awt.Color(51, 51, 51));
        price1.setText("TK 250");
        show1.add(price1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 40, 30));

        order_panel.add(show1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 220, 320));

        show2.setBackground(new java.awt.Color(234, 234, 234));
        show2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        image2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_cafe/management/images/burger.jpg"))); // NOI18N
        show2.add(image2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 200, 180));

        ingredients_name2.setFont(new java.awt.Font("Segoe UI Semilight", 2, 13)); // NOI18N
        ingredients_name2.setForeground(new java.awt.Color(51, 51, 51));
        ingredients_name2.setText("Ingredients ");
        show2.add(ingredients_name2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 210, 30));

        cart2.setBackground(new java.awt.Color(158, 158, 0));
        cart2.setForeground(new java.awt.Color(255, 255, 255));
        cart2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cart2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cart2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cart2MouseExited(evt);
            }
        });

        add_to_cart2.setBackground(new java.awt.Color(204, 204, 0));
        add_to_cart2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        add_to_cart2.setForeground(new java.awt.Color(237, 237, 237));
        add_to_cart2.setText("Add to Cart");

        javax.swing.GroupLayout cart2Layout = new javax.swing.GroupLayout(cart2);
        cart2.setLayout(cart2Layout);
        cart2Layout.setHorizontalGroup(
            cart2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cart2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(add_to_cart2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cart2Layout.setVerticalGroup(
            cart2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(add_to_cart2, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        show2.add(cart2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 90, 20));

        item2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        item2.setForeground(new java.awt.Color(51, 51, 51));
        item2.setText("Burger");
        show2.add(item2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, -1));

        ingredients2.setFont(new java.awt.Font("Segoe UI Semilight", 2, 13)); // NOI18N
        ingredients2.setForeground(new java.awt.Color(51, 51, 51));
        ingredients2.setText("Chicken, Beef, Cheese, Garlic");
        show2.add(ingredients2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 210, 30));

        price2.setFont(new java.awt.Font("Segoe UI Semilight", 2, 13)); // NOI18N
        price2.setForeground(new java.awt.Color(51, 51, 51));
        price2.setText("TK 250");
        show2.add(price2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 40, 30));

        quantity2.setBackground(new java.awt.Color(158, 158, 0));
        quantity2.setForeground(new java.awt.Color(255, 255, 255));

        quantity_text2.setBackground(new java.awt.Color(204, 204, 0));
        quantity_text2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        quantity_text2.setForeground(new java.awt.Color(237, 237, 237));
        quantity_text2.setText("10");

        plus2.setBackground(new java.awt.Color(204, 204, 0));
        plus2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        plus2.setForeground(new java.awt.Color(237, 237, 237));
        plus2.setText("+");
        plus2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                plus2MouseClicked(evt);
            }
        });

        minus2.setBackground(new java.awt.Color(204, 204, 0));
        minus2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        minus2.setForeground(new java.awt.Color(237, 237, 237));
        minus2.setText(" -");
        minus2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minus2MouseClicked(evt);
            }
        });

        quantity_stock2.setBackground(new java.awt.Color(204, 204, 0));
        quantity_stock2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        quantity_stock2.setForeground(new java.awt.Color(237, 237, 237));
        quantity_stock2.setText("/  0");

        javax.swing.GroupLayout quantity2Layout = new javax.swing.GroupLayout(quantity2);
        quantity2.setLayout(quantity2Layout);
        quantity2Layout.setHorizontalGroup(
            quantity2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(quantity2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(minus2, javax.swing.GroupLayout.DEFAULT_SIZE, 12, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(quantity_text2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quantity_stock2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(plus2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        quantity2Layout.setVerticalGroup(
            quantity2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, quantity2Layout.createSequentialGroup()
                .addGroup(quantity2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, quantity2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(plus2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(quantity_text2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(quantity_stock2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(minus2))
                .addContainerGap())
        );

        show2.add(quantity2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 90, 20));

        order_panel.add(show2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 220, 320));

        show3.setBackground(new java.awt.Color(234, 234, 234));
        show3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        image3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_cafe/management/images/burger.jpg"))); // NOI18N
        show3.add(image3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 200, 180));

        ingredients_name3.setFont(new java.awt.Font("Segoe UI Semilight", 2, 13)); // NOI18N
        ingredients_name3.setForeground(new java.awt.Color(51, 51, 51));
        ingredients_name3.setText("Ingredients ");
        show3.add(ingredients_name3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 210, 30));

        cart3.setBackground(new java.awt.Color(158, 158, 0));
        cart3.setForeground(new java.awt.Color(255, 255, 255));
        cart3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cart3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cart3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cart3MouseExited(evt);
            }
        });

        add_to_cart3.setBackground(new java.awt.Color(204, 204, 0));
        add_to_cart3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        add_to_cart3.setForeground(new java.awt.Color(237, 237, 237));
        add_to_cart3.setText("Add to Cart");

        javax.swing.GroupLayout cart3Layout = new javax.swing.GroupLayout(cart3);
        cart3.setLayout(cart3Layout);
        cart3Layout.setHorizontalGroup(
            cart3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cart3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(add_to_cart3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cart3Layout.setVerticalGroup(
            cart3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(add_to_cart3, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        show3.add(cart3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 90, 20));

        item3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        item3.setForeground(new java.awt.Color(51, 51, 51));
        item3.setText("Burger");
        show3.add(item3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, -1));

        ingredients3.setFont(new java.awt.Font("Segoe UI Semilight", 2, 13)); // NOI18N
        ingredients3.setForeground(new java.awt.Color(51, 51, 51));
        ingredients3.setText("Chicken, Beef, Cheese, Garlic");
        show3.add(ingredients3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 210, 30));

        price3.setFont(new java.awt.Font("Segoe UI Semilight", 2, 13)); // NOI18N
        price3.setForeground(new java.awt.Color(51, 51, 51));
        price3.setText("TK 250");
        show3.add(price3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 40, 30));

        quantity3.setBackground(new java.awt.Color(158, 158, 0));
        quantity3.setForeground(new java.awt.Color(255, 255, 255));

        quantity_text3.setBackground(new java.awt.Color(204, 204, 0));
        quantity_text3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        quantity_text3.setForeground(new java.awt.Color(237, 237, 237));
        quantity_text3.setText("10");

        plus3.setBackground(new java.awt.Color(204, 204, 0));
        plus3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        plus3.setForeground(new java.awt.Color(237, 237, 237));
        plus3.setText("+");
        plus3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                plus3MouseClicked(evt);
            }
        });

        minus3.setBackground(new java.awt.Color(204, 204, 0));
        minus3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        minus3.setForeground(new java.awt.Color(237, 237, 237));
        minus3.setText(" -");
        minus3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minus3MouseClicked(evt);
            }
        });

        quantity_stock3.setBackground(new java.awt.Color(204, 204, 0));
        quantity_stock3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        quantity_stock3.setForeground(new java.awt.Color(237, 237, 237));
        quantity_stock3.setText("/  0");

        javax.swing.GroupLayout quantity3Layout = new javax.swing.GroupLayout(quantity3);
        quantity3.setLayout(quantity3Layout);
        quantity3Layout.setHorizontalGroup(
            quantity3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(quantity3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(minus3, javax.swing.GroupLayout.DEFAULT_SIZE, 12, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quantity_text3)
                .addGap(5, 5, 5)
                .addComponent(quantity_stock3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(plus3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        quantity3Layout.setVerticalGroup(
            quantity3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, quantity3Layout.createSequentialGroup()
                .addGroup(quantity3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, quantity3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(plus3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(quantity_stock3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(quantity3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(minus3)
                        .addComponent(quantity_text3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        show3.add(quantity3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 90, 20));

        order_panel.add(show3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, 220, 320));

        back_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_cafe/management/images/icons8_Back_Arrow_60px.png"))); // NOI18N
        back_button.setText("jLabel14");
        back_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back_buttonMouseClicked(evt);
            }
        });
        order_panel.add(back_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 60, 50));

        next_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_cafe/management/images/icons8_Forward_Button_60px.png"))); // NOI18N
        next_button.setText("jLabel14");
        next_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                next_buttonMouseClicked(evt);
            }
        });
        order_panel.add(next_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 390, 60, 50));

        main_panel.add(order_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 760, 470));

        jPanel3.setBackground(new java.awt.Color(153, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        main_panel.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 760, 10));

        jPanel2.setBackground(new java.awt.Color(242, 242, 242));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(68, 68, 68));
        jLabel15.setText("Cafe La Rosa");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 100, 30));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_cafe/management/images/icons8_Facebook_20px_5.png"))); // NOI18N
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(343, 17, -1, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_cafe/management/images/icons8_Twitter_20px_3.png"))); // NOI18N
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 17, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_cafe/management/images/icons8_Instagram_20px_4.png"))); // NOI18N
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(395, 17, -1, -1));

        exit.setBackground(new java.awt.Color(242, 242, 242));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitMouseExited(evt);
            }
        });

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_cafe/management/images/icons8_Shutdown_22px.png"))); // NOI18N
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel20MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel20MouseExited(evt);
            }
        });

        javax.swing.GroupLayout exitLayout = new javax.swing.GroupLayout(exit);
        exit.setLayout(exitLayout);
        exitLayout.setHorizontalGroup(
            exitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exitLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        exitLayout.setVerticalGroup(
            exitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exitLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 60, 50));

        settings.setBackground(new java.awt.Color(242, 242, 242));
        settings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                settingsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                settingsMouseExited(evt);
            }
        });

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_cafe/management/images/icons8_Settings_22px_4.png"))); // NOI18N
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel21MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout settingsLayout = new javax.swing.GroupLayout(settings);
        settings.setLayout(settingsLayout);
        settingsLayout.setHorizontalGroup(
            settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel21)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        settingsLayout.setVerticalGroup(
            settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(settings, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 0, 60, 50));

        main_panel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 760, 50));

        home_panel.setBackground(new java.awt.Color(247, 247, 247));
        home_panel.setLayout(null);

        jPanel16.setBackground(new java.awt.Color(153, 0, 0));

        Category_Header5.setFont(new java.awt.Font("Segoe UI Semibold", 3, 42)); // NOI18N
        Category_Header5.setForeground(new java.awt.Color(239, 239, 239));
        Category_Header5.setText("Welcome to Cafe Emoji");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Category_Header5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(Category_Header5)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        home_panel.add(jPanel16);
        jPanel16.setBounds(110, 40, 480, 120);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_cafe/management/images/home.jpg"))); // NOI18N
        home_panel.add(jLabel9);
        jLabel9.setBounds(0, 0, 870, 470);

        main_panel.add(home_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 760, 470));

        cart_panel.setBackground(new java.awt.Color(247, 246, 246));
        cart_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table_panel.setBackground(new java.awt.Color(247, 246, 246));
        table_panel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        table.setFont(new java.awt.Font("Segoe UI Historic", 0, 15)); // NOI18N
        table.setForeground(new java.awt.Color(35, 35, 35));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Name", "Quantity", "Unit Price", "Total Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table.setRequestFocusEnabled(false);
        table.setRowHeight(25);
        table.setSelectionBackground(new java.awt.Color(51, 51, 51));
        table.setSelectionForeground(new java.awt.Color(239, 239, 239));
        table_panel.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
        }

        cart_panel.add(table_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 620, 220));

        item10.setFont(new java.awt.Font("Segoe UI Historic", 3, 42)); // NOI18N
        item10.setForeground(new java.awt.Color(51, 51, 51));
        item10.setText("Cart");
        cart_panel.add(item10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 90, 40));

        confirm_button.setBackground(new java.awt.Color(247, 246, 246));
        confirm_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirm_buttonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confirm_buttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                confirm_buttonMouseExited(evt);
            }
        });
        confirm_button.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_cafe/management/images/icons8_Sort_Right_40px_1.png"))); // NOI18N
        confirm_button.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        cart_panel.add(confirm_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 370, 60, 40));

        jLabel13.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(28, 28, 28));
        jLabel13.setText("CONFIRM");
        cart_panel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 420, -1, -1));

        total_bill_vat.setFont(new java.awt.Font("Segoe UI Historic", 0, 15)); // NOI18N
        cart_panel.add(total_bill_vat, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 370, 230, 20));

        total_bill.setFont(new java.awt.Font("Segoe UI Historic", 0, 15)); // NOI18N
        cart_panel.add(total_bill, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, 120, 20));

        jLabel34.setFont(new java.awt.Font("Segoe UI Historic", 0, 15)); // NOI18N
        jLabel34.setText("Vat: 15%");
        cart_panel.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 370, 70, 20));

        main_panel.add(cart_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 760, 470));

        category_panel.setBackground(new java.awt.Color(247, 246, 246));
        category_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        show4c.setBackground(new java.awt.Color(234, 234, 234));
        show4c.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                show4cMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                show4cMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                show4cMouseExited(evt);
            }
        });
        show4c.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        image7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_cafe/management/images/bev.jpg"))); // NOI18N
        show4c.add(image7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 150, 140));

        item6.setFont(new java.awt.Font("Segoe UI Black", 0, 19)); // NOI18N
        item6.setForeground(new java.awt.Color(51, 51, 51));
        item6.setText("Beverage");
        show4c.add(item6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 90, 20));

        category_panel.add(show4c, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 190, 170, 190));

        show1c.setBackground(new java.awt.Color(234, 234, 234));
        show1c.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                show1cMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                show1cMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                show1cMouseExited(evt);
            }
        });
        show1c.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        image8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_cafe/management/images/burger.jpg"))); // NOI18N
        show1c.add(image8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 150, 140));

        item7.setFont(new java.awt.Font("Segoe UI Black", 0, 19)); // NOI18N
        item7.setForeground(new java.awt.Color(51, 51, 51));
        item7.setText("Burger");
        show1c.add(item7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 70, 20));

        category_panel.add(show1c, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 170, 190));

        show2c.setBackground(new java.awt.Color(234, 234, 234));
        show2c.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                show2cMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                show2cMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                show2cMouseExited(evt);
            }
        });
        show2c.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        image9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_cafe/management/images/pasta.jpeg"))); // NOI18N
        show2c.add(image9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 150, 140));

        item8.setFont(new java.awt.Font("Segoe UI Black", 0, 19)); // NOI18N
        item8.setForeground(new java.awt.Color(51, 51, 51));
        item8.setText("Pasta");
        show2c.add(item8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 70, 20));

        category_panel.add(show2c, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 170, 190));

        show3c.setBackground(new java.awt.Color(234, 234, 234));
        show3c.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                show3cMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                show3cMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                show3cMouseExited(evt);
            }
        });
        show3c.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        image10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_cafe/management/images/pizza.jpg"))); // NOI18N
        show3c.add(image10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 150, 140));

        item9.setFont(new java.awt.Font("Segoe UI Black", 0, 19)); // NOI18N
        item9.setForeground(new java.awt.Color(51, 51, 51));
        item9.setText("Pizza");
        show3c.add(item9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 70, 20));

        category_panel.add(show3c, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, 170, 190));

        jPanel1.setBackground(new java.awt.Color(153, 0, 0));

        Category_Header.setFont(new java.awt.Font("Segoe UI Semibold", 3, 42)); // NOI18N
        Category_Header.setForeground(new java.awt.Color(239, 239, 239));
        Category_Header.setText("Select Category");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(Category_Header, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Category_Header)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        category_panel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 350, 90));

        main_panel.add(category_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 760, 470));

        admin_panel.setBackground(new java.awt.Color(247, 246, 246));
        admin_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        admin_add_item.setBackground(new java.awt.Color(247, 246, 246));
        admin_add_item.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ingredients_name5.setFont(new java.awt.Font("Segoe UI Semilight", 2, 17)); // NOI18N
        ingredients_name5.setForeground(new java.awt.Color(51, 51, 51));
        ingredients_name5.setText("Item Stock :");
        admin_add_item.add(ingredients_name5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 110, 30));

        ingredients_field.setBackground(new java.awt.Color(247, 246, 246));
        ingredients_field.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        ingredients_field.setForeground(new java.awt.Color(17, 17, 17));
        ingredients_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingredients_fieldActionPerformed(evt);
            }
        });
        admin_add_item.add(ingredients_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 230, 240, 30));

        ingredients_name7.setFont(new java.awt.Font("Segoe UI Semilight", 2, 17)); // NOI18N
        ingredients_name7.setForeground(new java.awt.Color(51, 51, 51));
        ingredients_name7.setText("Item Price :");
        admin_add_item.add(ingredients_name7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 100, 30));

        ingredients_name8.setFont(new java.awt.Font("Segoe UI Semilight", 2, 17)); // NOI18N
        ingredients_name8.setForeground(new java.awt.Color(51, 51, 51));
        ingredients_name8.setText("Item Ingredients :");
        admin_add_item.add(ingredients_name8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 140, 30));

        ingredients_name9.setFont(new java.awt.Font("Segoe UI Semilight", 2, 17)); // NOI18N
        ingredients_name9.setForeground(new java.awt.Color(51, 51, 51));
        ingredients_name9.setText("Item Category :");
        admin_add_item.add(ingredients_name9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 130, 30));

        name_field.setBackground(new java.awt.Color(247, 246, 246));
        name_field.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        name_field.setForeground(new java.awt.Color(17, 17, 17));
        name_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_fieldActionPerformed(evt);
            }
        });
        admin_add_item.add(name_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 240, 30));

        price_field.setBackground(new java.awt.Color(247, 246, 246));
        price_field.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        price_field.setForeground(new java.awt.Color(17, 17, 17));
        price_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                price_fieldActionPerformed(evt);
            }
        });
        admin_add_item.add(price_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 240, 30));

        category_field.setBackground(new java.awt.Color(247, 246, 246));
        category_field.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        category_field.setForeground(new java.awt.Color(17, 17, 17));
        category_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                category_fieldActionPerformed(evt);
            }
        });
        admin_add_item.add(category_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, 240, 30));

        stock_field.setBackground(new java.awt.Color(247, 246, 246));
        stock_field.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        stock_field.setForeground(new java.awt.Color(17, 17, 17));
        stock_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stock_fieldActionPerformed(evt);
            }
        });
        admin_add_item.add(stock_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, 240, 30));

        ingredients_name10.setFont(new java.awt.Font("Segoe UI Semilight", 2, 17)); // NOI18N
        ingredients_name10.setForeground(new java.awt.Color(51, 51, 51));
        ingredients_name10.setText("Item Name : ");
        admin_add_item.add(ingredients_name10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, -1, 30));

        submit_button.setBackground(new java.awt.Color(38, 38, 38));
        submit_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submit_buttonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submit_buttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                submit_buttonMouseExited(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(196, 196, 196));
        jLabel14.setText("SUBMIT");

        javax.swing.GroupLayout submit_buttonLayout = new javax.swing.GroupLayout(submit_button);
        submit_button.setLayout(submit_buttonLayout);
        submit_buttonLayout.setHorizontalGroup(
            submit_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, submit_buttonLayout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        submit_buttonLayout.setVerticalGroup(
            submit_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        admin_add_item.add(submit_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 280, 80, 30));

        admin_panel.add(admin_add_item, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 760, 370));

        jPanel4.setBackground(new java.awt.Color(163, 0, 0));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(81, 0, 0));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_cafe/management/images/icons8_Add_New_25px.png"))); // NOI18N
        jPanel6.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

        jLabel16.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(234, 234, 234));
        jLabel16.setText("ADD ITEM");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 70, -1));

        admin_panel.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 160, 50));

        jPanel5.setBackground(new java.awt.Color(163, 0, 0));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(81, 0, 0));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_cafe/management/images/icons8_Delete_25px.png"))); // NOI18N
        jPanel7.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

        jLabel24.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(234, 234, 234));
        jLabel24.setText("DELETE ITEM");
        jPanel5.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 80, -1));

        admin_panel.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 160, -1));

        jPanel8.setBackground(new java.awt.Color(163, 0, 0));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel8MouseEntered(evt);
            }
        });
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(81, 0, 0));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_cafe/management/images/icons8_Purchase_Order_25px.png"))); // NOI18N
        jPanel9.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        jPanel8.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

        jLabel26.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(234, 234, 234));
        jLabel26.setText("ORDERS");
        jPanel8.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 50, -1));

        admin_panel.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 160, -1));

        jPanel10.setBackground(new java.awt.Color(163, 0, 0));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
        });
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBackground(new java.awt.Color(81, 0, 0));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_cafe/management/images/icons8_US_Dollar_25px.png"))); // NOI18N
        jPanel11.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        jPanel10.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

        jLabel28.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(234, 234, 234));
        jLabel28.setText("TRANSACTIONS");
        jPanel10.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 100, -1));

        admin_panel.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 170, -1));

        admin_delete_item.setBackground(new java.awt.Color(247, 246, 246));
        admin_delete_item.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        delete_field.setBackground(new java.awt.Color(247, 246, 246));
        delete_field.setFont(new java.awt.Font("Segoe UI Historic", 0, 16)); // NOI18N
        delete_field.setForeground(new java.awt.Color(17, 17, 17));
        delete_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_fieldActionPerformed(evt);
            }
        });
        admin_delete_item.add(delete_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, 240, 30));

        ingredients_name14.setFont(new java.awt.Font("Segoe UI Semilight", 2, 18)); // NOI18N
        ingredients_name14.setForeground(new java.awt.Color(51, 51, 51));
        ingredients_name14.setText("Item Name : ");
        admin_delete_item.add(ingredients_name14, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, -1, 30));

        delete_button.setBackground(new java.awt.Color(38, 38, 38));
        delete_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                delete_buttonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                delete_buttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                delete_buttonMouseExited(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(196, 196, 196));
        jLabel29.setText("DELETE");

        javax.swing.GroupLayout delete_buttonLayout = new javax.swing.GroupLayout(delete_button);
        delete_button.setLayout(delete_buttonLayout);
        delete_buttonLayout.setHorizontalGroup(
            delete_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, delete_buttonLayout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        delete_buttonLayout.setVerticalGroup(
            delete_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        admin_delete_item.add(delete_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 180, 80, 30));

        admin_panel.add(admin_delete_item, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 760, 370));

        admin_order.setBackground(new java.awt.Color(247, 246, 246));
        admin_order.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table_panel1.setBackground(new java.awt.Color(247, 246, 246));
        table_panel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        order_table.setFont(new java.awt.Font("Segoe UI Historic", 0, 15)); // NOI18N
        order_table.setForeground(new java.awt.Color(35, 35, 35));
        order_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order Id", "Date", "Item Name", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        order_table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        order_table.setRequestFocusEnabled(false);
        order_table.setRowHeight(25);
        order_table.setSelectionBackground(new java.awt.Color(51, 51, 51));
        order_table.setSelectionForeground(new java.awt.Color(239, 239, 239));
        table_panel1.setViewportView(order_table);
        if (order_table.getColumnModel().getColumnCount() > 0) {
            order_table.getColumnModel().getColumn(0).setResizable(false);
            order_table.getColumnModel().getColumn(1).setResizable(false);
            order_table.getColumnModel().getColumn(2).setResizable(false);
            order_table.getColumnModel().getColumn(3).setResizable(false);
        }

        admin_order.add(table_panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 620, 220));

        quantity_radio.setBackground(new java.awt.Color(247, 246, 246));
        quantity_radio.setFont(new java.awt.Font("Segoe UI Symbol", 0, 11)); // NOI18N
        quantity_radio.setText("Quantity");
        quantity_radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantity_radioActionPerformed(evt);
            }
        });
        admin_order.add(quantity_radio, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, -1, 30));

        jLabel30.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel30.setText("Sort By : ");
        admin_order.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 30));

        item_radio.setBackground(new java.awt.Color(247, 246, 246));
        item_radio.setFont(new java.awt.Font("Segoe UI Symbol", 0, 11)); // NOI18N
        item_radio.setText(" Item Name");
        item_radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_radioActionPerformed(evt);
            }
        });
        admin_order.add(item_radio, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, -1, 30));

        date_radio.setBackground(new java.awt.Color(247, 246, 246));
        date_radio.setFont(new java.awt.Font("Segoe UI Symbol", 0, 11)); // NOI18N
        date_radio.setText("Date");
        date_radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                date_radioActionPerformed(evt);
            }
        });
        admin_order.add(date_radio, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, -1, 30));

        jLabel33.setFont(new java.awt.Font("Segoe UI Historic", 0, 20)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setText("Order History");
        admin_order.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 130, -1));

        admin_panel.add(admin_order, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 760, 370));

        admin_transaction.setBackground(new java.awt.Color(247, 246, 246));
        admin_transaction.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table_panel3.setBackground(new java.awt.Color(247, 246, 246));
        table_panel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        transaction_table.setFont(new java.awt.Font("Segoe UI Historic", 0, 15)); // NOI18N
        transaction_table.setForeground(new java.awt.Color(35, 35, 35));
        transaction_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bill Id", "Total Bill", "Vat", "Total Bill with Vat"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        transaction_table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        transaction_table.setRequestFocusEnabled(false);
        transaction_table.setRowHeight(25);
        transaction_table.setSelectionBackground(new java.awt.Color(51, 51, 51));
        transaction_table.setSelectionForeground(new java.awt.Color(239, 239, 239));
        table_panel3.setViewportView(transaction_table);
        if (transaction_table.getColumnModel().getColumnCount() > 0) {
            transaction_table.getColumnModel().getColumn(0).setResizable(false);
            transaction_table.getColumnModel().getColumn(1).setResizable(false);
            transaction_table.getColumnModel().getColumn(2).setResizable(false);
            transaction_table.getColumnModel().getColumn(3).setResizable(false);
        }

        admin_transaction.add(table_panel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 620, 220));

        jLabel32.setFont(new java.awt.Font("Segoe UI Historic", 0, 20)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(51, 51, 51));
        jLabel32.setText("Transactions");
        admin_transaction.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 120, -1));

        admin_panel.add(admin_transaction, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 760, 370));

        main_panel.add(admin_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 760, 470));

        admin_pass.setBackground(new java.awt.Color(247, 246, 246));
        admin_pass.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        submit_button1.setBackground(new java.awt.Color(38, 38, 38));
        submit_button1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submit_button1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submit_button1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                submit_button1MouseExited(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(196, 196, 196));
        jLabel31.setText("SUBMIT");

        javax.swing.GroupLayout submit_button1Layout = new javax.swing.GroupLayout(submit_button1);
        submit_button1.setLayout(submit_button1Layout);
        submit_button1Layout.setHorizontalGroup(
            submit_button1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, submit_button1Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        submit_button1Layout.setVerticalGroup(
            submit_button1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        admin_pass.add(submit_button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 240, -1, 30));

        ingredients_name16.setFont(new java.awt.Font("Segoe UI Semilight", 2, 22)); // NOI18N
        ingredients_name16.setForeground(new java.awt.Color(51, 51, 51));
        ingredients_name16.setText("PASSWORD:");
        admin_pass.add(ingredients_name16, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, -1, 30));

        jPanel12.setBackground(new java.awt.Color(153, 0, 0));

        Category_Header1.setFont(new java.awt.Font("Segoe UI Semibold", 3, 25)); // NOI18N
        Category_Header1.setForeground(new java.awt.Color(239, 239, 239));
        Category_Header1.setText("ADMIN");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(Category_Header1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Category_Header1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        admin_pass.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 190, 60));

        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });
        admin_pass.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, 200, 30));

        main_panel.add(admin_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 760, 470));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel20MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseEntered
        // TODO add your handling code here:
         exit.setBackground(new java.awt.Color(193,193,193));
        
    }//GEN-LAST:event_jLabel20MouseEntered

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel21MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel21MouseEntered

    private void exitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseEntered
        // TODO add your handling code here:
        exit.setBackground(new java.awt.Color(193,193,193));
    }//GEN-LAST:event_exitMouseEntered

    private void exitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseExited
        // TODO add your handling code here:
        exit.setBackground(new java.awt.Color(242,242,242));
    }//GEN-LAST:event_exitMouseExited

    private void jLabel20MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseExited
        // TODO add your handling code here:
        exit.setBackground(new java.awt.Color(242,242,242));
    }//GEN-LAST:event_jLabel20MouseExited

    private void settingsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsMouseEntered
        // TODO add your handling code here:
        if(settings.contains(evt.getPoint())) settings.setBackground(new java.awt.Color(193,193,193));
    }//GEN-LAST:event_settingsMouseEntered

    private void settingsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsMouseExited
        // TODO add your handling code here:
        if(!settings.contains(evt.getPoint())) settings.setBackground(new java.awt.Color(242,242,242));
    }//GEN-LAST:event_settingsMouseExited

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitMouseClicked

    private void show1cMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_show1cMouseEntered
        // TODO add your handling code here:
        System.out.println("YES\n");
       show1c.setBackground(new java.awt.Color(173,173,173));
    }//GEN-LAST:event_show1cMouseEntered

    private void show1cMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_show1cMouseExited
        // TODO add your handling code here:
        
        show1c.setBackground(new java.awt.Color(234,234,234));
    }//GEN-LAST:event_show1cMouseExited

    private void show2cMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_show2cMouseEntered
        // TODO add your handling code here:
        show2c.setBackground(new java.awt.Color(173,173,173));
    }//GEN-LAST:event_show2cMouseEntered

    private void show2cMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_show2cMouseExited
        // TODO add your handling code here:
         show2c.setBackground(new java.awt.Color(234,234,234));
    }//GEN-LAST:event_show2cMouseExited

    private void show3cMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_show3cMouseEntered
        // TODO add your handling code here:
        show3c.setBackground(new java.awt.Color(173,173,173));
    }//GEN-LAST:event_show3cMouseEntered

    private void show3cMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_show3cMouseExited
        // TODO add your handling code here:
         show3c.setBackground(new java.awt.Color(234,234,234));
    }//GEN-LAST:event_show3cMouseExited

    private void show4cMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_show4cMouseEntered
        // TODO add your handling code here:
        show4c.setBackground(new java.awt.Color(173,173,173));
       
    }//GEN-LAST:event_show4cMouseEntered

    private void show4cMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_show4cMouseExited
        // TODO add your handling code here:
        show4c.setBackground(new java.awt.Color(234,234,234));
        
    }//GEN-LAST:event_show4cMouseExited

    private void home1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home1MouseEntered
        // TODO add your handling code here:
        home1.setBackground(new java.awt.Color(42,42,42));
        
        
    }//GEN-LAST:event_home1MouseEntered

    private void home1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home1MouseExited
        // TODO add your handling code here:
         home1.setBackground(new java.awt.Color(30,30,30));
    }//GEN-LAST:event_home1MouseExited

    private void OrderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrderMouseEntered
        // TODO add your handling code here:
         Order.setBackground(new java.awt.Color(42,42,42));
    }//GEN-LAST:event_OrderMouseEntered

    private void OrderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrderMouseExited
        // TODO add your handling code here:
        Order.setBackground(new java.awt.Color(30,30,30));    
    }//GEN-LAST:event_OrderMouseExited

    private void CartMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CartMouseExited
        // TODO add your handling code here:
        Cart.setBackground(new java.awt.Color(30,30,30));
    }//GEN-LAST:event_CartMouseExited

    private void CartMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CartMouseEntered
        // TODO add your handling code here:
        Cart.setBackground(new java.awt.Color(42,42,42));
    }//GEN-LAST:event_CartMouseEntered

    private void OffersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OffersMouseEntered
        // TODO add your handling code here:
        Offers.setBackground(new java.awt.Color(42,42,42));
    }//GEN-LAST:event_OffersMouseEntered

    private void OffersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OffersMouseExited
        // TODO add your handling code here:
        Offers.setBackground(new java.awt.Color(30,30,30));
    }//GEN-LAST:event_OffersMouseExited

    private void AdminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AdminMouseEntered
        // TODO add your handling code here:
        
        Admin.setBackground(new java.awt.Color(42,42,42));
    }//GEN-LAST:event_AdminMouseEntered

    private void AdminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AdminMouseExited
        // TODO add your handling code here:
        Admin.setBackground(new java.awt.Color(30,30,30));
    }//GEN-LAST:event_AdminMouseExited

    private void OrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrderMouseClicked
        // TODO add your handling code here:
        setTrue(category_panel);
        
    }//GEN-LAST:event_OrderMouseClicked

    private void home1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home1MouseClicked
        // TODO add your handling code here:
        setTrue(home_panel);
    }//GEN-LAST:event_home1MouseClicked

    private void CartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CartMouseClicked
        // TODO add your handling code here:
      
        setTrue(cart_panel);
        
        double bill = 0,billvat = 0;
        int vat = 15;
        int q;
        
        for (Integer order : orders) {
            try{
                q = mp.get(order);
                if(q==0) continue;
                
                //ob.insertData_order_item(ob.getCount_order(),order,q);
                bill = bill+(pr.get(order)*q);
                
                //mp.put(order,0);
            }
            catch(Exception e)
            {
              
            }
        }
        
        billvat = bill + (bill*0.15);
        
        jLabel34.setVisible(true);
        total_bill_vat.setVisible(true);
        total_bill.setText("Total Bill : "+Double.toString(bill));
        total_bill_vat.setText("Total Bill with Vat : "+Double.toString(billvat));
    }//GEN-LAST:event_CartMouseClicked

    private void show1cMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_show1cMouseClicked
        // TODO add your handling code here:
        ResultSet res = ob.testQuery("Burger");
        try {
            setArray(res);
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        setTrue(order_panel);
        
    }//GEN-LAST:event_show1cMouseClicked

    private void show2cMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_show2cMouseClicked
        // TODO add your handling code here:
          
        ResultSet res = ob.testQuery("Pasta");
        try {
            setArray(res);
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        for(int i = 0;i<items.size();i++)
        {
            System.out.println(items.get(i).Item_Name+" "+items.get(i).Item_Price); 
        }
        */
        setTrue(order_panel);
        
    }//GEN-LAST:event_show2cMouseClicked

    private void show3cMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_show3cMouseClicked
        // TODO add your handling code here:
        ResultSet res = ob.testQuery("Pizza");
        try {
            setArray(res);
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        setTrue(order_panel);
     
    }//GEN-LAST:event_show3cMouseClicked

    private void show4cMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_show4cMouseClicked
        // TODO add your handling code here:
         ResultSet res = ob.testQuery("Beverage");
        try {
            setArray(res);
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        setTrue(order_panel);
    }//GEN-LAST:event_show4cMouseClicked

    private void next_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_next_buttonMouseClicked
        // TODO add your handling code here:
        if(cur+3<items.size()) {
            cur+=3;
            setItem(cur);
        }   
    }//GEN-LAST:event_next_buttonMouseClicked

    private void back_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back_buttonMouseClicked
        // TODO add your handling code here:
         if(cur-3>=0) {
            cur-=3;
            setItem(cur);
        }   
    }//GEN-LAST:event_back_buttonMouseClicked

    private void plus1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plus1MouseClicked
        // TODO add your handling code here:
        try{
            
        if(items.get(cur).Current_Quantity<items.get(cur).Item_Stock) {
            items.get(cur).Current_Quantity++;
            quantity_text1.setText(Integer.toString(items.get(cur).Current_Quantity));
        }
        
        }
        catch(Exception e)
        {
                    
        }
        
    }//GEN-LAST:event_plus1MouseClicked

    private void minus1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minus1MouseClicked
        // TODO add your handling code here:
        try{
          if(items.get(cur).Current_Quantity>0) {
            items.get(cur).Current_Quantity--;
            quantity_text1.setText(Integer.toString(items.get(cur).Current_Quantity));
        }
        }
        catch(Exception e)
        {
                    
        }
    }//GEN-LAST:event_minus1MouseClicked

    private void plus2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plus2MouseClicked
        // TODO add your handling code here:
         try{
          if(items.get(cur+1).Current_Quantity<items.get(cur+1).Item_Stock) {
            items.get(cur+1).Current_Quantity++;
            quantity_text2.setText(Integer.toString(items.get(cur+1).Current_Quantity));
        }
       }
       catch(Exception e)
       {
                    
       }
    }//GEN-LAST:event_plus2MouseClicked

    private void minus2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minus2MouseClicked
        // TODO add your handling code here:
        try{
          if(items.get(cur+1).Current_Quantity>0) {
            items.get(cur+1).Current_Quantity--;
            quantity_text2.setText(Integer.toString(items.get(cur+1).Current_Quantity));
        }
        }
        catch(Exception e)
        {
                    
        }
    }//GEN-LAST:event_minus2MouseClicked

    private void plus3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plus3MouseClicked
        // TODO add your handling code here:
         try{
          if(items.get(cur+2).Current_Quantity<items.get(cur+2).Item_Stock) {
            items.get(cur+2).Current_Quantity++;
            quantity_text3.setText(Integer.toString(items.get(cur+2).Current_Quantity));
        }
       }
       catch(Exception e)
       {
                    
       }
    }//GEN-LAST:event_plus3MouseClicked

    private void minus3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minus3MouseClicked
        // TODO add your handling code here:
         try{
          if(items.get(cur+2).Current_Quantity>0) {
            items.get(cur+2).Current_Quantity--;
            quantity_text3.setText(Integer.toString(items.get(cur+2).Current_Quantity));
        }
       }
       catch(Exception e)
       {
                    
       }
    }//GEN-LAST:event_minus3MouseClicked

    private void plus1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plus1MouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_plus1MouseEntered

    private void cart2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cart2MouseClicked
        // TODO add your handling code here:
        int idx = cur+1;
        addTable(idx);
        
        orders.add(items.get(idx).ItemNo);
        int q;

        try{
            q = mp.get(items.get(idx).ItemNo);
        }
        catch(Exception e)
        {
            q = 0;
        }
        mp.put(items.get(idx).ItemNo,q+items.get(idx).Current_Quantity);
        
        //Item tmp = new Item(items.get(idx).Item_Name,items.get(idx).Item_Price,items.get(idx).Item_Category,items.get(idx).Item_Stock,items.get(idx).Item_Ingredients,0,items.get(idx).ItemNo);
        //items.set(idx, tmp);
        items.get(idx).Current_Quantity = 0;
        quantity_text2.setText("0");
    }//GEN-LAST:event_cart2MouseClicked

    private void cart3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cart3MouseClicked
        // TODO add your handling code here:
        int idx = cur+2;
        addTable(idx);
        
        orders.add(items.get(idx).ItemNo);
        int q;

        try{
            q = mp.get(items.get(idx).ItemNo);
        }
        catch(Exception e)
        {
            q = 0;
        }
        mp.put(items.get(idx).ItemNo,q+items.get(idx).Current_Quantity);
        

        //Item tmp = new Item(items.get(idx).Item_Name,items.get(idx).Item_Price,items.get(idx).Item_Category,items.get(idx).Item_Stock,items.get(idx).Item_Ingredients,0,items.get(idx).ItemNo);
        //items.set(idx, tmp);
        items.get(idx).Current_Quantity = 0;
        quantity_text3.setText("0");
    }//GEN-LAST:event_cart3MouseClicked

    private void cart1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cart1MouseClicked
        // TODO add your handling code here:
        
        
        int idx = cur;
        addTable(idx);
        
        orders.add(items.get(idx).ItemNo);
        int q;

        try{
            q = mp.get(items.get(idx).ItemNo);
        }
        catch(Exception e)
        {
            q = 0;
        }
        mp.put(items.get(idx).ItemNo,q+items.get(idx).Current_Quantity);
        
        //Item tmp = new Item(items.get(idx).Item_Name,items.get(idx).Item_Price,items.get(idx).Item_Category,items.get(idx).Item_Stock,items.get(idx).Item_Ingredients,0,items.get(idx).ItemNo);
        //items.set(idx, tmp);
        items.get(idx).Current_Quantity = 0;
        quantity_text1.setText("0");
       
    }//GEN-LAST:event_cart1MouseClicked

    private void confirm_buttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirm_buttonMouseEntered
        // TODO add your handling code here:
        confirm_button.setBackground(new java.awt.Color(120,120,120));
    }//GEN-LAST:event_confirm_buttonMouseEntered

    private void confirm_buttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirm_buttonMouseExited
        // TODO add your handling code here:
        confirm_button.setBackground(new java.awt.Color(247,246,246));
    }//GEN-LAST:event_confirm_buttonMouseExited
   
    private void confirm_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirm_buttonMouseClicked
        // TODO add your handling code here:
        
        int q;
        
        if(orders.isEmpty()) return;
        
        
        try {
            ob.insertData_order(ob.getCount_order()+1);
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        double bill = 0,billvat = 0;
        int vat = 15;
        
        for (Integer order : orders) {
            try{
                q = mp.get(order);
                if(q==0) continue;
                
                ob.insertData_order_item(ob.getCount_order(),order,q);
                bill = bill+(pr.get(order)*q);
                
                mp.put(order,0);
            }
            catch(Exception e)
            {
              
            }
        }
        
        billvat = bill+ (bill*0.15);
        
        try {
            ob.insertData_bill(bill,vat,billvat,ob.getCount_order());
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        //o_id++;
        items.clear();
        orders.clear();
        mp.clear();
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        total_bill.setText("Order Confirmed");
        jLabel34.setVisible(false);
        total_bill_vat.setVisible(false);
    }//GEN-LAST:event_confirm_buttonMouseClicked

    private void AdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AdminMouseClicked
        // TODO add your handling code here:
        setTrue(admin_pass);
        
    }//GEN-LAST:event_AdminMouseClicked

    private void ingredients_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingredients_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ingredients_fieldActionPerformed

    private void name_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_name_fieldActionPerformed

    private void price_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_price_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_price_fieldActionPerformed

    private void category_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_category_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_category_fieldActionPerformed

    private void stock_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stock_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stock_fieldActionPerformed

    private void submit_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submit_buttonMouseClicked
        // TODO add your handling code here:
        
        ob.insertData_item(name_field.getText(),Double.parseDouble(price_field.getText()), category_field.getText(), Integer.parseInt(stock_field.getText()), ingredients_field.getText());
        price_field.setText("");
        category_field.setText("");
        stock_field.setText("");
        ingredients_field.setText("");
        name_field.setText("");
        
      
    }//GEN-LAST:event_submit_buttonMouseClicked

    private void submit_buttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submit_buttonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_submit_buttonMouseEntered

    private void submit_buttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submit_buttonMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_submit_buttonMouseExited

    private void delete_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_delete_fieldActionPerformed

    private void delete_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delete_buttonMouseClicked
        // TODO add your handling code here:
        String st = delete_field.getText();
        ob.deleteData(st);
        delete_field.setText("");
    }//GEN-LAST:event_delete_buttonMouseClicked

    private void delete_buttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delete_buttonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_delete_buttonMouseEntered

    private void delete_buttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delete_buttonMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_delete_buttonMouseExited

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        // TODO add your handling code here:
        admin_order.setVisible(false);
        admin_delete_item.setVisible(false);
        admin_transaction.setVisible(false);
        admin_add_item.setVisible(true);
        
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        // TODO add your handling code here:
        admin_order.setVisible(false);
        admin_transaction.setVisible(false);
        admin_add_item.setVisible(false);
        admin_delete_item.setVisible(true);
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        // TODO add your handling code here:
        
        admin_transaction.setVisible(false);
        admin_add_item.setVisible(false);
        admin_delete_item.setVisible(false);
        admin_order.setVisible(true);
        
        quantity_radio.setSelected(false);
        item_radio.setSelected(false);
        date_radio.setSelected(false);
        
        DefaultTableModel model = (DefaultTableModel) order_table.getModel();
        model.setRowCount(0);
        
        
        ResultSet res = ob.testOrder("Date");
        try {
            addTable1(res);
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jPanel8MouseClicked

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
        // TODO add your handling code here:
        
        admin_add_item.setVisible(false);
        admin_delete_item.setVisible(false);
        admin_order.setVisible(false);
        admin_transaction.setVisible(true);
        
        DefaultTableModel model = (DefaultTableModel) transaction_table.getModel();
        model.setRowCount(0);
        
        
        ResultSet res = ob.testBill("BillId");
        try {
            addTable2(res);
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jPanel10MouseClicked

    private void quantity_radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantity_radioActionPerformed
        // TODO add your handling code here:
        if(quantity_radio.isSelected()) {
        DefaultTableModel model = (DefaultTableModel) order_table.getModel();
        model.setRowCount(0);
        
        ResultSet res = ob.testOrder("Quantity");
        try {
            addTable1(res);
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        date_radio.setSelected(false);
        item_radio.setSelected(false);
        }
    }//GEN-LAST:event_quantity_radioActionPerformed

    private void item_radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_radioActionPerformed
        // TODO add your handling code here:
        if(item_radio.isSelected()) {
        DefaultTableModel model = (DefaultTableModel) order_table.getModel();
        model.setRowCount(0);
        
        ResultSet res = ob.testOrder("Item_Name");
        try {
            addTable1(res);
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        date_radio.setSelected(false);
        quantity_radio.setSelected(false);
        }
        
    }//GEN-LAST:event_item_radioActionPerformed

    private void date_radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_date_radioActionPerformed
        // TODO add your handling code here:
        if(date_radio.isSelected()) {
        DefaultTableModel model = (DefaultTableModel) order_table.getModel();
        model.setRowCount(0);
        
        ResultSet res = ob.testOrder("Date");
        try {
            addTable1(res);
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        quantity_radio.setSelected(false);
        item_radio.setSelected(false);
        }
    }//GEN-LAST:event_date_radioActionPerformed

    private void jPanel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel8MouseEntered

    private void submit_button1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submit_button1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_submit_button1MouseExited

    private void submit_button1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submit_button1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_submit_button1MouseEntered

    private void submit_button1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submit_button1MouseClicked
        // TODO add your handling code here:
        String st = "admin";
        //System.out.println(jPasswordField1.getText());
        String tmp = jPasswordField1.getText().toString().toLowerCase();
        if(tmp.equals(st)){
        setTrue(admin_panel);
        admin_order.setVisible(false);
        admin_delete_item.setVisible(false);
        admin_transaction.setVisible(false);
        admin_add_item.setVisible(false);
        }
    }//GEN-LAST:event_submit_button1MouseClicked

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void cart1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cart1MouseEntered
        // TODO add your handling code here:
        cart1.setBackground(new java.awt.Color(96,96,0));
        
        
    }//GEN-LAST:event_cart1MouseEntered

    private void cart1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cart1MouseExited
        // TODO add your handling code here:
        
        
        cart1.setBackground(new java.awt.Color(158,158,0));
    }//GEN-LAST:event_cart1MouseExited

    private void cart2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cart2MouseEntered
        // TODO add your handling code here:
        cart2.setBackground(new java.awt.Color(96,96,0));
        
       
    }//GEN-LAST:event_cart2MouseEntered

    private void cart2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cart2MouseExited
        // TODO add your handling code here:
        
        
        cart2.setBackground(new java.awt.Color(158,158,0));
    }//GEN-LAST:event_cart2MouseExited

    private void cart3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cart3MouseEntered
        // TODO add your handling code here:
        cart3.setBackground(new java.awt.Color(96,96,0));
        
        
    }//GEN-LAST:event_cart3MouseEntered

    private void cart3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cart3MouseExited
        // TODO add your handling code here:
       
        
        cart3.setBackground(new java.awt.Color(158,158,0));
    }//GEN-LAST:event_cart3MouseExited
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Customer().setVisible(true);
            }
        });
    }
    
    public class HeaderColor extends DefaultTableCellRenderer{
        public HeaderColor(){
            setOpaque(true);
        }
         
        public Component getTableCellRendererComponent(JTable mytable,Object value,boolean selected,boolean focused,int row,int column){
            super.getTableCellRendererComponent(mytable, value, selected, focused, row, column);
            setBackground(new java.awt.Color(34,34,34));
            setForeground(new java.awt.Color(255,255,255));
            return this;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Admin;
    private javax.swing.JPanel Cart;
    private javax.swing.JLabel Category_Header;
    private javax.swing.JLabel Category_Header1;
    private javax.swing.JLabel Category_Header5;
    private javax.swing.JPanel Offers;
    private javax.swing.JPanel Order;
    private javax.swing.JLabel add_to_cart1;
    private javax.swing.JLabel add_to_cart2;
    private javax.swing.JLabel add_to_cart3;
    private javax.swing.JPanel admin_add_item;
    private javax.swing.JPanel admin_delete_item;
    private javax.swing.JPanel admin_order;
    private javax.swing.JPanel admin_panel;
    private javax.swing.JPanel admin_pass;
    private javax.swing.JPanel admin_transaction;
    private javax.swing.JLabel back_button;
    private javax.swing.JPanel cart1;
    private javax.swing.JPanel cart2;
    private javax.swing.JPanel cart3;
    private javax.swing.JPanel cart_panel;
    private javax.swing.JTextField category_field;
    private javax.swing.JPanel category_panel;
    private javax.swing.JPanel confirm_button;
    private javax.swing.JRadioButton date_radio;
    private javax.swing.JPanel delete_button;
    private javax.swing.JTextField delete_field;
    private javax.swing.JPanel exit;
    private javax.swing.JPanel home1;
    private javax.swing.JPanel home_panel;
    private javax.swing.JLabel image1;
    private javax.swing.JLabel image10;
    private javax.swing.JLabel image2;
    private javax.swing.JLabel image3;
    private javax.swing.JLabel image7;
    private javax.swing.JLabel image8;
    private javax.swing.JLabel image9;
    private javax.swing.JLabel ingredients1;
    private javax.swing.JLabel ingredients2;
    private javax.swing.JLabel ingredients3;
    private javax.swing.JTextField ingredients_field;
    private javax.swing.JLabel ingredients_name10;
    private javax.swing.JLabel ingredients_name14;
    private javax.swing.JLabel ingredients_name16;
    private javax.swing.JLabel ingredients_name2;
    private javax.swing.JLabel ingredients_name3;
    private javax.swing.JLabel ingredients_name4;
    private javax.swing.JLabel ingredients_name5;
    private javax.swing.JLabel ingredients_name7;
    private javax.swing.JLabel ingredients_name8;
    private javax.swing.JLabel ingredients_name9;
    private javax.swing.JLabel item1;
    private javax.swing.JLabel item10;
    private javax.swing.JLabel item2;
    private javax.swing.JLabel item3;
    private javax.swing.JLabel item6;
    private javax.swing.JLabel item7;
    private javax.swing.JLabel item8;
    private javax.swing.JLabel item9;
    private javax.swing.JRadioButton item_radio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel main_panel;
    private javax.swing.JLabel minus1;
    private javax.swing.JLabel minus2;
    private javax.swing.JLabel minus3;
    private javax.swing.JTextField name_field;
    private javax.swing.JLabel next_button;
    private javax.swing.JPanel order_panel;
    private javax.swing.JTable order_table;
    private javax.swing.JLabel plus1;
    private javax.swing.JLabel plus2;
    private javax.swing.JLabel plus3;
    private javax.swing.JLabel price1;
    private javax.swing.JLabel price2;
    private javax.swing.JLabel price3;
    private javax.swing.JTextField price_field;
    private javax.swing.JPanel quantity1;
    private javax.swing.JPanel quantity2;
    private javax.swing.JPanel quantity3;
    private javax.swing.JRadioButton quantity_radio;
    private javax.swing.JLabel quantity_stock1;
    private javax.swing.JLabel quantity_stock2;
    private javax.swing.JLabel quantity_stock3;
    private javax.swing.JLabel quantity_text1;
    private javax.swing.JLabel quantity_text2;
    private javax.swing.JLabel quantity_text3;
    private javax.swing.JPanel settings;
    private javax.swing.JPanel show1;
    private javax.swing.JPanel show1c;
    private javax.swing.JPanel show2;
    private javax.swing.JPanel show2c;
    private javax.swing.JPanel show3;
    private javax.swing.JPanel show3c;
    private javax.swing.JPanel show4c;
    private javax.swing.JPanel side_panel;
    private javax.swing.JTextField stock_field;
    private javax.swing.JPanel submit_button;
    private javax.swing.JPanel submit_button1;
    private javax.swing.JTable table;
    private javax.swing.JScrollPane table_panel;
    private javax.swing.JScrollPane table_panel1;
    private javax.swing.JScrollPane table_panel3;
    private javax.swing.JLabel total_bill;
    private javax.swing.JLabel total_bill_vat;
    private javax.swing.JTable transaction_table;
    // End of variables declaration//GEN-END:variables
}
