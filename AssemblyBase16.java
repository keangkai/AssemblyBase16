/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assemblybase16;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class AssemblyBase16 {

    public static String operatorCode;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
        System.out.println("Please enter operation!");
        Scanner sc = new Scanner(System.in);
        operatorCode = sc.nextLine();
//        System.out.println(operatorCode);
        String[] ts = {"$t0", "$t1", "$t2", "$t3", "$t4", "$t5", "$t6", "$t7", "$t8", "$t9", "$s0", "$s1", "$s2", "$s3", "$s4", "$s5", "$s6", "$s7"};
        
        String funct = "";
        int rdValue = 0, rsValue = 0, rtValue = 0;
        String rd = "", rs = "", rt = "";
        String binaryOperation = "";
        String shamt  = "00000", op = "000000";
        int count = 0;
        
        String[] x = operatorCode.split("\\s+");
        
        if(x.length == 4) {
            x[0] = x[0].replace(",", "");
            x[1] = x[1].replace(",", "");
            x[2] = x[2].replace(",", "");
            x[3] = x[3].replace(",", "");

            // R-Type
            if (x[0].toLowerCase().equals("add") || x[0].toLowerCase().equals("sub")) {
                funct = x[0].equals("add") ? "100000" : "011101";
                if (x[1].equals(x[2]) || x[1].equals(x[3]) || x[2].equals(x[3])) {
                    System.out.println("Error");
                    System.exit(0);
                }
                
                // check if rs rt and rd are valid
                for(int i = 0; i < ts.length; i++) {
                    if (x[1].equals(ts[i])) {
                        count++;
                    }
                    if (x[2].equals(ts[i])) {
                        count++;
                    }
                    if (x[3].equals(ts[i])) {
                        count++;
                    }
                }
                if (count != 3) {
                    System.out.println("Error");
                    System.exit(0);
                }
                for(int i = 0; i < ts.length; i++) {
                    if (ts[i].equals(x[1])) {
                        if (i >= 0 && i <= 7) {
                            rdValue = 8 + i;
                        }
                        else if (i >= 8 && i <= 9) {
                            rdValue = 24 + (i - 8);
                        }
                        else if (i >= 10 && i <= 17) {
                            rdValue = 16 + (i - 10);
                        }
                    }
                    if (ts[i].equals(x[2])) {
                        if (i >= 0 && i <= 7) {
                            rsValue = 8 + i;
                        }
                        else if (i >= 8 && i <= 9) {
                            rsValue = 24 + (i - 8);
                        }
                        else if (i >= 10 && i <= 17) {
                            rsValue = 16 + (i - 10);
                        }
                    }
                    if (ts[i].equals(x[3])) {
                        if (i >= 0 && i <= 7) {
                            rtValue = 8 + i;
                        }
                        else if (i >= 8 && i <= 9) {
                            rtValue = 24 + (i - 8);
                        }
                        else if (i >= 10 && i <= 17) {
                            rtValue = 16 + (i - 10);
                        }
                    }
                }
                rd = Integer.toString(rdValue,2);
                rs = Integer.toString(rsValue,2);
                rt = Integer.toString(rtValue,2);
                
                rd = rd.length() == 4 ? "0" + rd : rd;
                rs = rs.length() == 4 ? "0" + rs : rs;
                rt = rt.length() == 4 ? "0" + rt : rt;

                binaryOperation = op + rs + rt + rd + shamt + funct;

                String hexadecimalOperation = new BigInteger(binaryOperation, 2).toString(16);

//                System.out.println(binaryOperation);
                System.out.println("Hexadecimal is " + hexadecimalOperation);
            }
            
            // I-Type
            else if (x[0].toLowerCase().equals("addi")) {
                op = "011110";
                for(int i = 0; i < ts.length; i++) {
                    if (x[1].equals(ts[i])) {
                        count++;
                    }
                    if (x[2].equals(ts[i])) {
                        count++;
                    }
                    if (Integer.parseInt(x[3]) >= 0 && Integer.parseInt(x[3]) <= 65536) {
                        count++;
                    }
                }
                if (count != 3) {
                    System.out.println("Error");
                    System.exit(0);
                }
                for(int i = 0; i < ts.length; i++) {
                    if (ts[i].equals(x[1])) {
                        if (i >= 0 && i <= 7) {
                            rsValue = 8 + i;
                        }
                        else if (i >= 8 && i <= 9) {
                            rsValue = 24 + (i - 8);
                        }
                        else if (i >= 10 && i <= 17) {
                            rsValue = 16 + (i - 10);
                        }
                    }
                    if (ts[i].equals(x[2])) {
                        if (i >= 0 && i <= 7) {
                            rtValue = 8 + i;
                        }
                        else if (i >= 8 && i <= 9) {
                            rtValue = 24 + (i - 8);
                        }
                        else if (i >= 10 && i <= 17) {
                            rtValue = 16 + (i - 10);
                        }
                    }
                }
                rs = Integer.toString(rsValue,2);
                rt = Integer.toString(rtValue,2);
                String number = Integer.toString(Integer.parseInt(x[3]),2);
                rs = rs.length() == 4 ? "0" + rs : rs;
                rt = rt.length() == 4 ? "0" + rt : rt;
                
                while(number.length() < 16) {
                    number = "0" + number;
                }
                
                binaryOperation = op + rs + rt + number;
                String hexadecimalOperation = new BigInteger(binaryOperation, 2).toString(16);
                
                System.out.println("Hexadecimal is " + hexadecimalOperation);
            }
            else {
                System.out.println("Error");
                System.exit(0);
            }
        }
        else {
            System.out.println("Error");
            System.exit(0);
        }
       
        
        
       
//        A.add("a");
//        A.add(x);
//        System.out.println("Please enter your instruction");
//        //System.out.println(x);
//        System.out.println(A);
        
    }
//    public static int Add(){
//        int DesRegister;
//        int FRegister;
//        int Sregister;
//        
//        System.out.println("Please choose Destination register");
//        System.out.println("&t0 enter 0, &t1 enter 1, &t2 enter 2, &t3 enter 3, &t4 enter 4\n&t5 enter 5, &t6 enter 6, &t7 enter 7, &t8 enter 8, &t9 enter 9");
//        Scanner sc = new Scanner(System.in);
//        DesRegister = sc.nextInt();
//        System.out.println("Please choose First source register");
//        
//    return 0;    
//    }
//    public static int Sub(){
//    
//    return 0;
//    }
//    public static int Addi(){
//    
//    return 0;
//    }
}
