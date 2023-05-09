package DAO;

import Controller.AlertAndVerifyController;
import DatabaseConnection.ConnectionFactory;
import Model.CameraApp;
import Model.Staff;

import java.sql.*;

public class StaffDAO implements DaoInterface<Staff>{
    Connection con = null;
    PreparedStatement pstmt = null;
    Statement stmt = null;
    ResultSet rs1=null;
    Statement stmt1=null;
    ResultSet rs = null;
    public static StaffDAO getInstance(){
        return new StaffDAO();
    }
    public StaffDAO(){
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.createStatement();
            stmt1=con.createStatement();
            Stocks stocks = new Stocks();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public int insert(Staff staff) {
        try{
            String query = "SELECT id FROM staff WHERE staffIDCard=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, staff.getStaffIDCard());
            rs=pstmt.executeQuery();
            if(rs.next()){
                AlertAndVerifyController.errorAlert("ERROR","THIS PRODUCT HAS BEEN ADDED!");
                return 0;
            }else{
                addFunction(staff);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public int delete(int id) throws SQLException {
        String deleteProduct= "DELETE FROM staff WHERE id= ?";
        int result;
        try {
            pstmt = con.prepareStatement(deleteProduct);
            pstmt.setInt(1,id);
            result=pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int update(Staff staff) {
        String updateCustomerDetails= "UPDATE staff SET position=?, phone=?, EMAIL=?,avatarLink=?,location=?,basicSalary=? WHERE  id= ?";
        int result;
        try {
            pstmt = con.prepareStatement(updateCustomerDetails);
            pstmt.setString(1, staff.getPosition());
            pstmt.setString(2, staff.getPhone());
            pstmt.setString(3, staff.getEmail());
            pstmt.setString(4, staff.getAvatarLink());
            pstmt.setString(5, staff.getLocation());
            pstmt.setFloat(6,staff.getBasicSalary());
            pstmt.setFloat(7, staff.getId());
            result=pstmt.executeUpdate();
            if(result>0)
                AlertAndVerifyController.informationAlert("Sucessful","THIS CUSTOMER INFORMATION HAS BEEN UPDATED SUCCESSFULLY");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public ResultSet selectALL(int Limit, int offSet) {
        try {
            String selectAllProduct = "SELECT fullname, position, joinDate FROM staff ORDER BY id ASC LIMIT "+Limit+" OFFSET "+offSet;
            pstmt= con.prepareStatement(selectAllProduct);
            rs = pstmt.executeQuery();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return  rs;
    }

    @Override
    public ResultSet selectByID(int ID) {
        return null;
    }

    @Override
    public int addFunction(Staff staff) {
        int result = 0;
        try {
            String url = "insert into staff (fullname, gender, staffIDCard, position , phone, joinDate, EMAIL, avatarLink, Birthdate)"
                    + "values (?,?,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(url);
            pstmt.setString(1, staff.getFullName());
            if(staff.getGender().equals("Nam"))
                pstmt.setInt(2,0);
            else if (staff.getGender().equals("Nữ"))
                pstmt.setInt(2,1);
            else pstmt.setInt(2,2);
            pstmt.setString(3, staff.getStaffIDCard());
            pstmt.setString(4, staff.getPosition());
            pstmt.setString(5, staff.getPhone());
            pstmt.setDate(6, staff.getJoinDate());
            pstmt.setString(7, staff.getEmail());
            pstmt.setString(8, staff.getAvatarLink());
            pstmt.setDate(9,staff.getBirthDate());
            result = pstmt.executeUpdate();
            if(result>0)
                AlertAndVerifyController.informationAlert("Sucessful","THIS STAFF HAS BEEN ADDED SUCCESSFULLY");
            addMonthlySalary(staff);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    private void addMonthlySalary(Staff staff){
        String checkSalary ="Select id FROM monthly_salary WHERE staffid=? AND monthSalary=?";
        try {
            pstmt = con.prepareStatement(checkSalary);
            pstmt.setInt(1,staff.getId());
            pstmt.setDate(2,staff.getMonthSalary());
            rs=pstmt.executeQuery();
            if(rs.next()){
                String sql = "UPDATE monthly_salary SET monthSalary=?, workingHours=?, overtimeHours=?, allowance=?, deduction=? WHERE id=?";
                pstmt = con.prepareStatement(sql);

                // Thiết lập các tham số truy vấn
                pstmt.setDate(1, staff.getMonthSalary());
                pstmt.setInt(2, staff.getWorkingHours());
                pstmt.setInt(3, staff.getOvertimeHours());
                pstmt.setFloat(4, staff.getAllowance());
                pstmt.setFloat(5, staff.getDeduction());
                pstmt.setInt(6, staff.getId());
                // Thực thi truy vấn
                int rowsAffected = pstmt.executeUpdate();
                if(rowsAffected>0)
                    AlertAndVerifyController.informationAlert("Success","SUCCESSFULLY UPDATE MONTHLY SALARY"+staff.getMonthSalary());
            }else{
                String sql = "INSERT INTO monthly_salary (staffid, monthSalary,workingHours, overtimeHours, allowance, deduction) VALUES (?, ?, ?, ?, ?, ?)";
                try {
                    pstmt = con.prepareStatement(sql);
                    // Thiết lập các tham số truy vấn
                    pstmt.setInt(1, staff.getId());
                    pstmt.setDate(2, staff.getMonthSalary());
                    pstmt.setInt(3, staff.getWorkingHours());
                    pstmt.setInt(4, staff.getOvertimeHours());
                    pstmt.setFloat(5, staff.getAllowance());
                    pstmt.setFloat(6, staff.getDeduction());

                    // Thực thi truy vấn
                    int rowsAffected = pstmt.executeUpdate();
                    if(rowsAffected>0)
                        AlertAndVerifyController.informationAlert("Success","SUCCESSFULLY CREATE NEW MONTHLY SALARY"+staff.getMonthSalary());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public int getNumStaff(){
        String query="SELECT COUNT(id) as numberStaff FROM staff";
        int numberProduct=0;
        try {
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if(rs.next())
                numberProduct=rs.getInt("numberStaff");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return numberProduct;
    }
}