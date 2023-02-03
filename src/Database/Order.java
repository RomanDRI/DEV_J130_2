package Database;

import java.sql.Date;


public class Order {
    private Integer id;
    private Date creationDate;
    private String fullName;
    private String telephone;
    private String email;
    private String deliveryAddress;
    private String orderStatus;
    private Date shippingDate;
    private OrderItem orderItem;

    public Order(String fullName, String telephone, String email, String deliveryAddress, String[] vendorCodeOfProduct, Integer[] numberOfProducts) {
        setFullName(fullName);
        setTelephone(telephone);
        setEmail(email);
        setDeliveryAddress(deliveryAddress);
        this.orderItem = new OrderItem(vendorCodeOfProduct, numberOfProducts);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreationDate() {
        Long now = System.currentTimeMillis();
        creationDate = new Date(now);
        return creationDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if(fullName != null & fullName != "") this.fullName = fullName;
        else throw new IllegalArgumentException("Введите ФИО");
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        if(telephone != null & telephone != "") this.telephone = telephone;
        else throw new IllegalArgumentException("Введите телефон");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email != null & email != "") this.email = email;
        else throw new IllegalArgumentException("Введите email");
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        if(deliveryAddress != null & deliveryAddress != "") this.deliveryAddress = deliveryAddress;
        else throw new IllegalArgumentException("Введите адрес доставки");
    }

    public String getOrderStatus() {
        orderStatus = "P";
        return orderStatus;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", fullName='" + fullName + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", shippingDate=" + shippingDate +
                '}';
    }
}
