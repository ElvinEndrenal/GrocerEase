package com.example.grocerease;

public class listModel {

    private int ID;
    private String groceryName;
    private int quantity;
    private float price;

    public listModel(int ID, String groceryName, int quantity, float price) {
        this.ID = ID;
        this.groceryName = groceryName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getGroceryName() {
        return groceryName;
    }

    public void setGroceryName(String groceryName) {
        this.groceryName = groceryName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "listModel{" +
                "groceryName='" + groceryName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
