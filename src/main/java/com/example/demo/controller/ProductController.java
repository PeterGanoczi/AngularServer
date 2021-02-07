package com.example.demo.controller;


import com.example.demo.DemoApplication;
import com.example.demo.model.Product;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/products")
public class ProductController {


    @GetMapping(path = "/list")
    public List<Product> getAllProducts(){
        Product product;
        List<Product> list=new ArrayList<>();

        try{
            Connection connection= DriverManager.getConnection(
                    DemoApplication.URL, DemoApplication.ROOT, DemoApplication.ROOT);

            PreparedStatement statement=connection.prepareStatement("select * from products join users on products.user_id=users.id order by time desc ");
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                product=new Product();
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setUserId(resultSet.getInt("user_id"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getDouble("price"));
                product.setImgUrl(resultSet.getString("image"));
                product.setUserName(resultSet.getString("username"));
                product.setCreateTime(resultSet.getDate("time"));
                //doplnit cas registracie

                list.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<?> createProduct(@RequestBody String body) throws JSONException {

        JSONObject response=new JSONObject();

        try{

            System.out.println("Request:\n"+body);
            JSONObject request=new JSONObject(body);


            if (request.has("userId") && request.has("name") && request.has("description") && request.has("price") && request.has("imgUrl")){
                Product product=new Product(
                        request.getInt("userId"),
                        request.getString("name"),
                        request.getString("description"),
                        request.getDouble("price"),
                        request.getString("imgUrl")
                );

                PreparedStatement statement=DemoApplication.getConnection().prepareStatement(
                        "INSERT INTO products (user_id, name,description,price,image)"+
                                "VALUES (?, ?, ?, ?,?)");

                statement.setInt(1, product.getUserId());
                statement.setString(2, product.getName());
                statement.setString(3, product.getDescription());
                statement.setDouble(4, product.getPrice());
                statement.setString(5, product.getImgUrl());

                statement.executeUpdate();

                response.put("success", "Product with name:" + product.getName()+" created");
                return ResponseEntity.status(200).body(response.toString());

            } else {
                response.put("error", "Some product information is missing");
                return ResponseEntity.status(400).body(response.toString());
            }

        } catch(Exception e){
            e.printStackTrace();
            response.put("error", "Product was not created");
            return ResponseEntity.status(400).body(response.toString());
        }

    }


    @PostMapping(path = "/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody String body) throws JSONException {

        JSONObject response=new JSONObject();

        try{

            System.out.println("Request:\n"+body);
            JSONObject request=new JSONObject(body);


//            if (request.has("name") && request.has("description") && request.has("price") && request.has("imgUrl")){
                Product product=new Product(
                        request.getString("name"),
                        request.getString("description"),
                        request.getDouble("price"),
                        request.getString("imgUrl")
                );

                PreparedStatement statement=DemoApplication.getConnection().prepareStatement(
                        "UPDATE products SET name=?, description=?, price=?, image=? WHERE id=?");

                statement.setString(1, product.getName());
                statement.setString(2, product.getDescription());
                statement.setDouble(3, product.getPrice());
                statement.setString(4, product.getImgUrl());
                statement.setInt(5, id);

                statement.executeUpdate();

                response.put("success", "Product with name:" + product.getName()+"updateded");
                return ResponseEntity.status(200).body(response.toString());

//            } else {
//                response.put("error", "Some product information is missing");
//                return ResponseEntity.status(400).body(response.toString());
//            }

        } catch(Exception e){
            e.printStackTrace();
            response.put("error", "Product was not updated");
            return ResponseEntity.status(400).body(response.toString());
        }

    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) throws JSONException {

        JSONObject response=new JSONObject();

        try{
            PreparedStatement statement=DemoApplication.getConnection().prepareStatement(
                    "DELETE FROM products WHERE id=?");

            statement.setInt(1,id);
            statement.executeUpdate();

            response.put("success", "Product with id: "+id+" was deleted");
            return ResponseEntity.status(200).body(response.toString());


        }catch (Exception e){
            e.printStackTrace();
            response.put("error", "Product with id: "+id+" does not exists");
            return ResponseEntity.status(400).body(response.toString());
        }

    }

    @GetMapping(path = "/{id}")
    public Product getProduct(@PathVariable("id") String productId){
        Product product=new Product();


        try{
            Connection connection= DriverManager.getConnection(
                    DemoApplication.URL, DemoApplication.ROOT, DemoApplication.ROOT);

            PreparedStatement statement=connection.prepareStatement("select * from products join users on products.user_id=users.id where products.id="+productId);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                product=new Product();
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getDouble("price"));
                product.setImgUrl(resultSet.getString("image"));
                product.setUserName(resultSet.getString("username"));
                product.setCreateTime(resultSet.getDate("time"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @GetMapping(path = "/list/{id}")
    public List <Product> getProductsByUser(@PathVariable("id") String userId){
        Product product=new Product();
        List<Product> list=new ArrayList<>();


        try{
            Connection connection= DriverManager.getConnection(
                    DemoApplication.URL, DemoApplication.ROOT, DemoApplication.ROOT);

            PreparedStatement statement=connection.prepareStatement("select * from products join users on products.user_id=users.id WHERE user_id=" +userId +
                    " order by time desc");
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                product=new Product();
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setUserId(resultSet.getInt("user_id"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getDouble("price"));
                product.setImgUrl(resultSet.getString("image"));
                product.setUserName(resultSet.getString("username"));
                product.setCreateTime(resultSet.getDate("time"));
                //doplnit cas registracie

                list.add(product);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
