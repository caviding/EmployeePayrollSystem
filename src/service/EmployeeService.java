package service;

import java.sql.*;
import java.util.List;
import entity.Employee;

import java.util.ArrayList;

public class EmployeeService {
    private List<Employee> employees = new ArrayList<>();

    String url = "jdbc:mysql://localhost:3306/mysql";
    String user = "root";
    String password = "admin";
    Connection con;

    {
        try {
            con = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addEmployee(Employee e) {
        employees.add(e);
        System.out.println("Employee added: " + e.getFullName());
    }

    public void listEmployees() throws SQLException {
        Statement sta = con.createStatement();
        String sql = "SELECT COUNT(*) FROM fulltimeemployee UNION SELECT COUNT(*) FROM parttimeemployee ";
        ResultSet rs = sta.executeQuery(sql);
        rs.next();
        if (rs.getInt(1)==0){
            System.out.println("-----No employees found !!!-----");
        }else {
            String sql1 = "SELECT * FROM fulltimeemployee";
            Statement st = con.createStatement();
            ResultSet rs1 = st.executeQuery(sql1);
            System.out.println("Full Time Employees are : ");
            while (rs1.next()) {
                int id = rs1.getInt("id");
                String name = rs1.getString("name");
                String surname = rs1.getString("surname");
                String position = rs1.getString("position");
                double salary = rs1.getDouble("monthlySalary");
                double bonus = rs1.getDouble("bonus");

                System.out.println(id + " | " + name + " | " + surname +
                        " | " + position + " | " + salary + " | " + bonus + "\n");
            }

            String sql2 = "SELECT * FROM parttimeemployee";
            Statement st1 = con.createStatement();
            ResultSet rs2 = st1.executeQuery(sql2);
            System.out.println("Part Time Employees are : ");
            while (rs2.next()) {
                int id = rs2.getInt("id");
                String name = rs2.getString("name");
                String surname = rs2.getString("surname");
                String position = rs2.getString("position");
                double hourlyRate = rs2.getDouble("hourlyRate");
                double hoursWorked = rs2.getInt("hoursWorked");

                System.out.println(id + " | " + name + " | " + surname +
                        " | " + position + " | " + hourlyRate + " | " + hoursWorked + "\n");
            }
        }
    }

    public void calculateSalary(int empId) throws SQLException {
        String sqlFullTime = "SELECT * FROM fulltimeemployee WHERE id = ?";
        PreparedStatement pstFullTime = con.prepareStatement(sqlFullTime);
        pstFullTime.setInt(1, empId);
        ResultSet rsFullTime = pstFullTime.executeQuery();

        if (rsFullTime.next()) {
            String name = rsFullTime.getString("name");
            String surname = rsFullTime.getString("surname");
            double salary = rsFullTime.getDouble("monthlySalary");
            double bonus = rsFullTime.getDouble("bonus");

            double totalSalary = salary + bonus;

            System.out.println("Full-Time Employee: ");
            System.out.println("Total Salary Of "+ name + " " + surname + " : " + totalSalary);
        } else {
            String sqlPartTime = "SELECT * FROM parttimeemployee WHERE id = ?";
            PreparedStatement pstPartTime = con.prepareStatement(sqlPartTime);
            pstPartTime.setInt(1, empId);
            ResultSet rsPartTime = pstPartTime.executeQuery();

            if (rsPartTime.next()) {
                String name = rsPartTime.getString("name");
                String surname = rsPartTime.getString("surname");
                double hourlyRate = rsPartTime.getDouble("hourlyRate");
                int hoursWorked = rsPartTime.getInt("hoursWorked");

                double totalSalary = hourlyRate * hoursWorked;

                System.out.println("Part-Time Employee: ");
                System.out.println("Total Salary Of "+ name + " " + surname + " : " + totalSalary);
            } else {
                System.out.println("No employee found with ID: " + empId);
            }
        }
    }

}

