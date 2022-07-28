package fr.m2i.webdistributeur;

public class Product {

    private int id;
    private String name;
    private int price;
    private int quantity;

    public Product() {

    }

    public Product(int id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String nom) {
        this.name = nom;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int prix) {
        this.price = prix;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantite) {
        this.quantity = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + name + ", prix=" + price + ", quantite=" + quantity + '}';
    }
}
