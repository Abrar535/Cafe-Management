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
public class Item {
    public String Item_Name,Item_Category;
    public double Item_Price;
    public int Item_Stock;
    public String Item_Ingredients;
    public int Current_Quantity;
    public int ItemNo;
    
    public Item()
    {
        
    }
    
    public Item(String Item_Name,double Item_Price,String Item_Category,int Item_Stock, String Item_Ingredients,int Current_Quantity,int ItemNo)
    {
       this.Item_Category = Item_Category;
       this.Item_Name = Item_Name;
       this.Item_Price = Item_Price;
       this.Item_Stock = Item_Stock;
       this.Item_Ingredients = Item_Ingredients;
       this.Current_Quantity = Current_Quantity;
       this.ItemNo = ItemNo;
    }
    
    
}
