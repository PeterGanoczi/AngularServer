package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="user_id")
    private int userId;



    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

//    @Column(name="category")
//    private String category;

    @Column(name="price")
    private double price;

    @Column(name="image")
    private String imgUrl;

    @Column(name="time")
    private Date createTime;

    @Transient
    private String userName;

    public Product(int userId, String name, String description, double price, String imgUrl) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public Product( String name, String description, double price, String imgUrl) {

        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;

    }

    public Product(Long id, int userId, String name, String description, double price, String imgUrl, Date createTime, String userName) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.createTime = createTime;
        this.userName=userName;
    }

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", createTime=" + createTime +'\'' +
                ", userName=" + userName +
                '}';
    }
}
