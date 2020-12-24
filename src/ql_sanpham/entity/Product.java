package ql_sanpham.entity;

/*
 *@Author: AnthonyLe
 * *Vjp pRo
 */
public class Product {

    private int id;
    private int categoryid;
    private String productid;
    private String name;
    private float unitPrice;
    private int quantity;
    private String description;

    public Product(int id, int categoryid, String productid, String name, float unitPrice, int quantity, String description) {
        this.id = id;
        this.categoryid = categoryid;
        this.productid = productid;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.description = description;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }

}
