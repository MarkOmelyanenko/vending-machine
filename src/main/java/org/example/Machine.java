package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class Machine {
    private HashSet<Product> products = new HashSet<>();

    public void addCategory(String title, double price, int quantity) {

        if (!products.add(new Product(title, price, quantity))) System.out.println("This product already exists!");
        else System.out.printf("""
                Product added!
                Title: %s
                Price: %.2f
                Quantity: %d
                """, title, price, quantity);
    }

    public void addItem(String title, int quantity) {

        for (Product product : products) {
            if (product.getTitle().equals(title)) {
                product.setQuantity(product.getQuantity() + quantity);
                System.out.println("Quantity is updated: " + product.getQuantity());
            } else System.out.println("Error :(");
        }
    }

    public void purchase(String title, String purchaseDate) {

        for (Product product : products) {
            if (product.getTitle().equals(title) && product.getQuantity() > 0) {
                product.setQuantity(product.getQuantity() - 1);
                product.setPurchaseDate(purchaseDate);
            }
        }
    }

    public void list() {

        List<Product> sortderProducts = new ArrayList<>(products);
        sortderProducts.sort(Comparator.comparingInt(Product::getQuantity));

        System.out.println("""
        Title | Price | Quantity
        ------------------------""");

        for (Product product : sortderProducts) {
            System.out.printf("%s | %.2f | %d",
                    product.getTitle(), product.getPrice(), product.getQuantity());
        }
    }

    public void clear() {
        for (Product product : products) {
            if (product.getQuantity() == 0) {
                System.out.println("Removing " + product.getTitle());
                products.remove(product);
            } else System.out.println("Nothing to clear");
        }
    }

    public void report(String purchaseDate) {
        // todo:
        //  2021-04 && 2021-04-21
    }
}
