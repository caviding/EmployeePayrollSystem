package entity;
import java.sql.*;
import java.sql.Connection;
import java.util.Scanner;
import java.util.UUID;

import service.EmployeeService;

public class Main {

    public static void main(String[] args) throws SQLException, InterruptedException {

        try {

            EmployeeService service = new EmployeeService();
            Scanner sc = new Scanner(System.in);

            String url = "jdbc:mysql://localhost:3306/mysql";
            String user = "root";
            String password = "admin";

            Connection con = null;
            while (true) {

                try {
                    con = DriverManager.getConnection(url, user, password);
                } catch (SQLException e) {
                    System.out.println("Connection to DB is failed .");
                    e.printStackTrace();
                }

                System.out.println("\n===== Employee Management System =====");
                System.out.println("1. Add Full-Time Employee");
                System.out.println("2. Add Part-Time Employee");
                System.out.println("3. List Employees");
                System.out.println("4. Calculate Salary");
                System.out.println("5. Exit");
                System.out.print("Choose: ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1:

                        System.out.print("Name: ");
                        String name1 = sc.nextLine();
                        sc.nextLine();

                        System.out.print("Surname: ");
                        String surname1 = sc.nextLine();

                        System.out.print("Position: ");
                        String pos1 = sc.nextLine();

                        System.out.print("Monthly Salary: ");
                        double monthly = sc.nextDouble();

                        System.out.print("Bonus: ");
                        double bonus = sc.nextDouble();

                        try {
                            Statement sta = con.createStatement();

                            UUID uniqueID = UUID.randomUUID();
                            int id = (int) (uniqueID.getMostSignificantBits() >> 32);
                            id = Math.abs(id);

                            String sql = "SELECT COUNT(*) FROM fulltimeemployee WHERE id = " + id +
                                    " UNION SELECT COUNT(*) FROM parttimeemployee WHERE id = " + id;
                            ResultSet rs = sta.executeQuery(sql);
                            rs.next();
                            if (rs.getInt(1) == 1) {
                                System.out.println("---- Employee with this id already exist !!! ----");
                                break;
                            }


                            String query = "INSERT INTO fulltimeemployee VALUES (?,?,?,?,?,?);";
                            PreparedStatement st = con.prepareStatement(query);
                            st.setInt(1, id);
                            st.setString(2, name1);
                            st.setString(3, surname1);
                            st.setString(4, pos1);
                            st.setDouble(5, monthly);
                            st.setDouble(6, bonus);

                            st.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        System.out.println("----Adding Employee....");
                        Thread.sleep(3000);
                        break;

                    case 2:
                        System.out.print("Name: ");
                        String name2 = sc.nextLine();
                        sc.nextLine();


                        System.out.print("Surname: ");
                        String surname2 = sc.nextLine();

                        System.out.print("Position: ");
                        String pos2 = sc.nextLine();

                        System.out.print("Hourly Rate: ");
                        double rate = sc.nextDouble();

                        System.out.print("Hours Worked: ");
                        int hours = sc.nextInt();

                        try {
                            Statement sta = con.createStatement();

                            UUID uniqueID = UUID.randomUUID();
                            int id = (int) (uniqueID.getMostSignificantBits() >> 32);
                            id = Math.abs(id);

                            String sql = "SELECT COUNT(*) FROM fulltimeemployee WHERE id = " + id +
                                    " UNION SELECT COUNT(*) FROM parttimeemployee WHERE id = " + id;
                            ResultSet rs = sta.executeQuery(sql);
                            rs.next();
                            if (rs.getInt(1) == 1) {
                                System.out.println("---- Employee with this id already exist !!! ----");
                                break;
                            }

                            String query = "INSERT INTO parttimeemployee VALUES (?,?,?,?,?,?);";
                            PreparedStatement st = con.prepareStatement(query);
                            st.setInt(1, id);
                            st.setString(2, name2);
                            st.setString(3, surname2);
                            st.setString(4, pos2);
                            st.setDouble(5, rate);
                            st.setInt(6, hours);

                            st.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        System.out.println("----Adding Employee....");
                        Thread.sleep(3000);
                        break;

                    case 3:
                        service.listEmployees();
                        Thread.sleep(3000);
                        break;

                    case 4:
                        System.out.print("Enter ID: ");
                        int empId = sc.nextInt();
                        service.calculateSalary(empId);
                        Thread.sleep(3000);
                        break;

                    case 5:
                        con.close();
                        System.out.println("-----The program is closing...");
                        Thread.sleep(3000);
                        return;

                    default:
                            System.out.println("----Invalid operation-----");
                        Thread.sleep(3000);
                }
            }
        }
        catch(Exception e) {
            System.out.println("-----Input Miss Match---");
            Thread.sleep(2000);
        }
    }
}