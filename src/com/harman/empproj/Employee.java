package com.harman.empproj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Employee {
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            int option;
            while(true){
                System.out.println("Select an option :");
                System.out.println("1 . Add an employee ");
                System.out.println("2 . view all employee ");
                System.out.println("3 . delete the employee ");
                System.out.println("4 . Exit");
                option = in.nextInt();
                switch (option){
                    case 1 :
                        try {
                            Connection c = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/company?autoReconnect=true&useSSL=false", "root", "");
                            String employee_code, employee_name, employee_designation, employee_salary, employee_mobileno;
                            System.out.println("Enter the code :");
                            employee_code = in.next();
                            System.out.println("Enter the name :");
                            employee_name = in.next();
                            System.out.println("Enter the designation :");
                            employee_designation= in.next();
                            System.out.println("Enter the salary :");
                            employee_salary = in.next();
                            System.out.println("Enter the phone :");
                            employee_mobileno = in.next();
                            Statement stmt = c.createStatement();
                            stmt.executeUpdate("INSERT INTO `employee`(`employee_code`, `employee_name`, `employee_designation`, `employee_salary`, `employee_mobileno`)" +
                                    " VALUES('"+employee_code+"','"+employee_name+"','"+employee_designation+"',"+employee_salary+","+employee_mobileno+")");
                            System.out.println("Inserted sucessfully");
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;
                    case 2 :
                        System.out.println("View all employees selected");
                        try {
                            Connection c = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/company?autoReconnect=true&useSSL=false", "root", "");
                            Statement stmt = c.createStatement();
                            ResultSet rs = stmt.executeQuery("SELECT `id`, `employee_code`, `employee_name`, `employee_designation`, `employee_salary`, `employee_mobileno` FROM `employee` WHERE 1");
                            while (rs.next()){
                                System.out.println("employee_code = " + rs.getInt("employee_code"));
                                System.out.println("employee_name = "+ rs.getString("employee_name"));
                                System.out.println("employee_designation = "+ rs.getString("employee_designation"));
                                System.out.println("employee_salary = " + rs.getInt("employee_salary"));
                                System.out.println("employee_mobileno = "+ rs.getBigDecimal("employee_mobileno"));
                            }
                        }
                        catch (Exception e)
                        {
                            System.out.println(e);
                        }
                        break;
                    case 3 :try {
                        int employee_code;
                        System.out.println("Enter the code to be deleted:");
                        employee_code = in.nextInt();
                        Connection c = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/company?autoReconnect=true&useSSL=false", "root", "");
                        Statement stmt = c.createStatement();
                        stmt.executeUpdate("DELETE FROM `employee` WHERE `employee_code`=" +employee_code);
                        System.out.println("Deleted sucessfully");
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                        break;
                    case 4 :
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }
    }