package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "description")
    private String description;
    @Column(name = "date_of_man")
    private Date dateOfMan;
    @Column(name = "price")
    private Double price;

    public Product(int id, String productName, String description, Date dateOfMan, Double price) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.dateOfMan = dateOfMan;
        this.price = price;
    }

    @OneToMany
    @JoinColumn(name = "sale")
    private List<Sale> sales;

    public Product() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateOfMan() {
        return dateOfMan;
    }

    public void setDateOfMan(Date dateOfMan) {
        this.dateOfMan = dateOfMan;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", dateOfMan=" + dateOfMan +
                ", price=" + price +
                '}';
    }
}
