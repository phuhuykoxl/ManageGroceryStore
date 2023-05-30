package Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Product {
    private Integer productId;
    private String productBarCode;
    private int supplierID;
    private String productName;
    private Integer quantity;
    private double costPrice;
    private double sellingPrice;
    private int categoryID;
    private int userId;
    private String customerCode;
    private Double totalCost;
    private String totalRevenue;
    private String thumbnailLink;
    private ArrayList<productLot> productLots;

    public Product(productLot productLot) {
        productLots=new ArrayList<>();
        productLots.add(productLot);
    }

    public Product(String productName, int categoryId, String avatar, int sid, double costPrice, double sellingPrice, ArrayList<productLot> productLots) {
        productLots=new ArrayList<>();
        this.productName=productName;
        this.categoryID=categoryId;
        this.thumbnailLink=avatar;
        this.supplierID=sid;
        this.costPrice=costPrice;
        this.sellingPrice=sellingPrice;
        this.productLots=productLots;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ArrayList<productLot> getProductLots() {
        return productLots;
    }

    public void setProductLots(ArrayList<productLot> productLots) {
        this.productLots = productLots;
    }

    public Product(Integer productId, String productBarCode, int supplierID, String productName, Integer quantity, double costPrice, double sellingPrice, int categoryID, int userId, String customerCode, Double totalCost, String totalRevenue, String thumbnailLink, ArrayList<productLot> productLots) {
        this.productId = productId;
        this.productBarCode = productBarCode;
        this.supplierID = supplierID;
        this.productName = productName;
        this.quantity = quantity;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.categoryID = categoryID;
        this.userId = userId;
        this.customerCode = customerCode;
        this.totalCost = totalCost;
        this.totalRevenue = totalRevenue;
        this.thumbnailLink = thumbnailLink;
        this.productLots = productLots;
    }

    public Product(String productName, double sellingPrice, int quantity, String totalRevenue) {
        this.productName = productName;
        this.sellingPrice = sellingPrice;
        this.quantity=quantity;
        this.totalRevenue=totalRevenue;
    }

    public Product(String productName, int pid,Double costPrice,  String thumbnailLink) {
        this.costPrice = costPrice;
        this.productName = productName;
        this.productId=pid;
        this.thumbnailLink=thumbnailLink;
    }
    public Product( String productName,String productBarCode, int pid, double costPrice, String thumbnailLink) {
        this.productBarCode = productBarCode;
        this.productName = productName;
        this.productId=pid;
        this.costPrice=costPrice;
        this.thumbnailLink=thumbnailLink;
    }

    public Product(String productName, Integer quantity, String totalRevenue) {
        this.productName = productName;
        this.quantity = quantity;
        this.totalRevenue = totalRevenue;
    }


    public Product(int STT, String productName, int quantity, String totalRevenue) {
        this.productId=STT;
        this.productName = productName;
        this.quantity = quantity;
        this.productBarCode = totalRevenue;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", totalRevenue=" + totalRevenue +
                '}';
    }

    public Product(String productName, int productId, int categoryID, int quantity, String thumbnailLink,  double costPrice, double sellingPrice) {
        this.productName = productName;
        this.productId = productId;
        this.quantity = quantity;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.categoryID = categoryID;
        this.thumbnailLink = thumbnailLink;
    }


    public String getThumbnailLink() {
        return thumbnailLink;
    }

    public void setThumbnailLink(String thumbnailLink) {
        this.thumbnailLink = thumbnailLink;
    }


    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductBarCode() {
        return productBarCode;
    }

    public void setProductBarCode(String productBarCode) {
        this.productBarCode = productBarCode;
    }


    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public String getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(String totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

}
