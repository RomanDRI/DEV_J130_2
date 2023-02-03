package Database;


public class OrderItem {

    private Integer orderCode;
    private String[] vendorCodeOfProduct;
    private Integer[] price;
    private Integer[] numberOfProducts;


    public OrderItem(String[] vendorCodeOfProduct, Integer[] numberOfProducts) {
        if(vendorCodeOfProduct.length == numberOfProducts.length) {
            setVendorCodeOfProduct(vendorCodeOfProduct);
            setNumberOfProducts(numberOfProducts);
        } else throw new IllegalArgumentException("Укажите нужное количество товара для каждого введенного артикула");
        this.price = new Integer[numberOfProducts.length];
    }


    public String[] getVendorCodeOfProduct() {
        return vendorCodeOfProduct;
    }
    public void setVendorCodeOfProduct(String[] vendorCodeOfProduct) {
        if (vendorCodeOfProduct.length == 0) throw new IllegalArgumentException("Введите артикул товара. Артикул должен состоять из 7 знаков");
        for (int i = 0; i < vendorCodeOfProduct.length; i++) {
            String checkCode = vendorCodeOfProduct[i];
            if (checkCode != "3251615" & checkCode != "3251616" & checkCode != "3251617" & checkCode != "3251619" & checkCode != "3251620") {
                throw new IllegalArgumentException("Указан несуществующий артикул");
            } else {
                this.vendorCodeOfProduct = vendorCodeOfProduct;
            }
        }
    }

    public Integer[] getPrice() {
        return price;
    }

    public void setPrice(Integer price, int i) {
        this.price[i] = price;
    }

    public Integer[] getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(Integer[] numberOfProducts) {
        if(numberOfProducts.length != 0) this.numberOfProducts = numberOfProducts;
        else throw new IllegalArgumentException("Введите необходимое количество товара");
    }

    @Override
    public String toString() {
        return "orderCode " + orderCode +
                ", vendorCodeOfProduct " + vendorCodeOfProduct +
                ", price " + price +
                ", numberOfProducts " + numberOfProducts;
    }


}
