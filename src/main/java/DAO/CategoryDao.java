package DAO;

import DatabaseConnection.ConnectionFactory;
import Model.Category;
import Model.Product;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

public class CategoryDao implements DaoInterface <Category> {
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;
    private ResultSet rs1=null;
    private Statement stmt1=null;
    private ResultSet rs = null;
    Stocks stocks = null;
    public CategoryDao() {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.createStatement();
            stmt1=con.createStatement();
            stocks = new Stocks();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Thêm mới một danh mục sản phẩm vào cơ sở dữ liệu
    @Override
    public int insert(Category category) {
        try{
            String query = "SELECT * FROM products WHERE id=? AND name=?";
            pstmt.setInt(1,category.getId());
            pstmt.setString(2,category.getName());
            rs=pstmt.executeQuery(query);
            if(rs.next()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("The same category has been added!");
                alert.show();
            }else{
                addFunction(category);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    // Cập nhật thông tin của một danh mục sản phẩm trong cơ sở dữ liệu
    public int update(Category category) {
        String sql = "UPDATE productCategories SET name=? WHERE id=?";
        int affectedRows = 0;
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, category.getName());
            stmt.setInt(2, category.getId());
            affectedRows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    @Override
    // Xóa một danh mục sản phẩm khỏi cơ sở dữ liệu
    public int delete(int categoryCode) {
        String sql = "DELETE FROM productCategories WHERE CategoryCode=?";
        int affectedRows = 0;
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, categoryCode);
            affectedRows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    @Override
    // Lấy danh mục sản phẩm từ cơ sở dữ liệu theo id
    public ResultSet selectByID(int ID) {
        String sql = "SELECT * FROM productCategories WHERE ID=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, ID);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public int addFunction(Category category) {
        int result;
        String url="insert into productCategories (ID, NAME, description, productList)"
                + "values (?,?,?,?)";
        try {
            pstmt = con.prepareStatement(url);
            pstmt.setInt(1,category.getId());
            pstmt.setString(2, category.getName());
            pstmt.setString(3, category.getDescription());
            pstmt.setObject(4, category.getProductList());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    // Lấy danh sách tất cả các danh mục sản phẩm từ cơ sở dữ liệu
    public ResultSet selectALL(int Limit, int offSet) {
        String sql = "SELECT * FROM productCategories";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    public ArrayList<Category> selectALL1() {
        String sql = "SELECT * FROM productCategories";
        ArrayList<Category> categories = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                ArrayList<Product> productList = new ArrayList<>();
                String query ="SELECT * FROM PRODUCTS WHERE categoryid=?";
                PreparedStatement pstmt2= con.prepareStatement(query);
                pstmt2.setInt(1,rs.getInt("categoryID"));
                ResultSet rs2 = pstmt2.executeQuery();
                while (rs2.next()){
                    productList.add(new Product(rs2.getString("PRODUCTNAME"), null  , rs2.getInt("PID"),rs2.getDouble("costPrice"),rs2.getString("THUMBNAIL")));
                }
                categories.add(new Category(rs.getInt("categoryid"),rs.getString("Name"), productList, rs.getInt("parent_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
    // Lấy danh sách các sản phẩm trong một danh mục sản phẩm từ cơ sở dữ liệu theo id
    private List<Product> getProductsByCategoryId(String categoryName) {
        String sql = "SELECT * FROM products p join productcategories pc on p.categoryid=pc.categoryid  WHERE MATCH(pc.name) AGAINST(?)";
        categoryName = "\"" + categoryName.trim() + "\"";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, categoryName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Category category = new Category();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
    public int getCategoryIDByName(String name){
        String query = "SELECT categoryid FROM productCategories WHERE name = ?";
        int categoryId=0;
        try {
            pstmt =con.prepareStatement(query);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                categoryId = rs.getInt("categoryid");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryId;
    }
    public String getNameByCategoryID(int ID){
        String query = "SELECT name FROM productCategories WHERE categoryid = ?";
        String name=null;
        try {
            pstmt =con.prepareStatement(query);
            pstmt.setInt(1, ID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                name = rs.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return name;
    }
    public int getNumCategory(){
        String query="SELECT COUNT(categoryid) as numberCategory FROM productCategories";
        int numberProduct=0;
        try {
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if(rs.next())
                numberProduct=rs.getInt("numberCategory");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return numberProduct;
    }
}