package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "sales_name")
    private String salesName;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "dos")
    private Date dos;

    public Sale(int id, String salesName, int productId, Date dos) {
        this.id = id;
        this.salesName = salesName;
        this.productId = productId;
        this.dos = dos;
    }

    public Sale() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Date getDos() {
        return dos;
    }

    public void setDos(Date dos) {
        this.dos = dos;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", salesName='" + salesName + '\'' +
                ", productId=" + productId +
                ", dos='" + dos + '\'' +
                '}';
    }
}
