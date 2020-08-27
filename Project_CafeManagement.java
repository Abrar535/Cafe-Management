/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_cafe.management;

import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class Project_CafeManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      Scanner sc = new Scanner(System.in);
      BigInteger n = sc.nextBigInteger();
      System.out.println(n.multiply(new BigInteger("2")));
    }
    
    
    
}
