package Database;


public class Product {
    private String vendorCode;
    private String productName;
    private String productColor;
    private Integer productPrice;
    private Integer productInStock;

    public Product(String vendorCode, String productName, String productColor, Integer productPrice, Integer productInStock) {
        this.vendorCode = vendorCode;
        this.productName = productName;
        this.productColor = productColor;
        this.productPrice = productPrice;
        this.productInStock = productInStock;

    }

    @Override
    public String toString() {
        if(productColor!=null) {
            return "vendorCode: " + vendorCode +
                    ", productName: " + productName +
                    ", productColor: " + productColor +
                    ", productPrice: " + productPrice +
                    ", productInStock: " + productInStock;
        }
        return "vendorCode: " + vendorCode +
                ", productName: " + productName +
                ", productPrice: " + productPrice +
                ", productInStock: " + productInStock;
    }
}
