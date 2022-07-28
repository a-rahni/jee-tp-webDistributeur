package fr.m2i.webdistributeur;

import java.util.ArrayList;
import java.util.List;

public class Distributor {

    private static Distributor instance;

    private int credit;
    private List<Product> stock;

    public static Distributor getInstance() {

        if (instance == null) {
            instance = new Distributor();
        }

        return instance;
    }

    private Distributor() {
        credit = 0;
        stock = new ArrayList();
        remplirLeStock();
    }
    
    public void remplirLeStock() {
        stock.add(new Product(1, "café", 1, 5));
        stock.add(new Product(2, "soda", 2, 5));
        stock.add(new Product(3, "barre chocolas", 3, 5));
    }

    public void insererArgent(int montant) {
        credit += montant;
    }

    public boolean stockSuffisant(int idProduit) {
        Product produit = getProduit(idProduit);
        return produit != null && produit.getQuantity() >= 1;
    }

    public boolean creditSuffisant(int idProduit) {
        Product produit = getProduit(idProduit);
        return produit != null && credit >= produit.getPrice();
    }

    public void commanderProduit(int idProduit){

        if(stockSuffisant(idProduit) && creditSuffisant(idProduit)) {
            Product produit = getProduit(idProduit);

            produit.setQuantity(produit.getQuantity() - 1);
            credit -= produit.getPrice(); 
        }
    }
    
    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public List<Product> getStock() {
        return stock;
    }

    public void setStock(List<Product> stock) {
        this.stock = stock;
    }
    
    public Product getProduit(int id) {
        for (Product produit: stock) {
           if (produit.getId() == id){
               return produit;
           }
        }
        return null;
    }
    
    public void addProduct(Product product) {
        stock.add(product);
    }
    
    public void removeProduct(int id) {
        for (int i=0;i<stock.size();i++) {
           if (stock.get(i).getId()== id){
               stock.remove(i);
           }
        }
    }
    
    public void updateProduct(Product product) {
        for (int i=0;i<stock.size();i++) {
           if (stock.get(i).getId()== product.getId()){
               stock.get(i).setName(product.getName());
               stock.get(i).setPrice(product.getPrice());
               stock.get(i).setQuantity(product.getQuantity());
           }
        }
    }
    
}
