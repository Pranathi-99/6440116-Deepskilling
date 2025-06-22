/*
Data Structures & Algorithms
Exercise 2: E-commerce Platform Search Function

EcommerceSearchApp: Demonstrates a core E-commerce product search.
It's structured with nested static classes for conciseness in a single file, showcasing efficient search algorithms (O(1) by ID, indexed search by attributes).
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class EcommerceSearchApp {

    public static class Product {
        private int id;
        private String name;
        private String category;

        public Product(int id, String name, String category) {
            this.id = id;
            this.name = name;
            this.category = category;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public String getCategory() { return category; }

        @Override
        public String toString() {
            return String.format("ID:%d | Name:'%s' | Cat:'%s'", id, name, category);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Product product = (Product) o;
            return id == product.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static class ProductCatalog {
        private Map<Integer, Product> productsById = new HashMap<>();
        private Map<String, List<Integer>> categoryIndex = new HashMap<>();
        private Map<String, List<Integer>> nameIndex = new HashMap<>();

        public void addProduct(Product product) {
            if (product == null || productsById.containsKey(product.getId())) {
                System.out.println("WARN: Product ID " + (product != null ? product.getId() : "null") + " exists or invalid. Skipped.");
                return;
            }
            productsById.put(product.getId(), product);

            categoryIndex.computeIfAbsent(product.getCategory().toLowerCase(), k -> new ArrayList<>()).add(product.getId());
            nameIndex.computeIfAbsent(product.getName().toLowerCase(), k -> new ArrayList<>()).add(product.getId());

            System.out.println("ADDED: " + product.getName());
        }

        public Product searchById(int id) {
            return productsById.get(id); // O(1)
        }

        public List<Product> searchByName(String query) {
            List<Product> results = new ArrayList<>();
            String lowerQuery = query.toLowerCase();

            for (Map.Entry<String, List<Integer>> entry : nameIndex.entrySet()) {
                if (entry.getKey().contains(lowerQuery)) {
                    for (Integer productId : entry.getValue()) {
                        results.add(productsById.get(productId));
                    }
                }
            }
            return results;
        }

        public List<Product> searchByCategory(String category) {
            List<Product> results = new ArrayList<>();
            List<Integer> ids = categoryIndex.get(category.toLowerCase()); // O(1) average
            if (ids != null) {
                for (Integer id : ids) {
                    results.add(productsById.get(id));
                }
            }
            return results;
        }
    }

    public static void main(String[] args) {
        System.out.println("-- E-commerce Platform Serach Function --");

        ProductCatalog catalog = new ProductCatalog();

        System.out.println("\n- Initializing Catalog -");
        catalog.addProduct(new Product(101, "Laptop Pro X", "Electronics"));
        catalog.addProduct(new Product(102, "Wireless Mouse", "Electronics"));
        catalog.addProduct(new Product(201, "Running Shoes V2", "Footwear"));
        catalog.addProduct(new Product(202, "Hiking Boots", "Footwear"));
        catalog.addProduct(new Product(301, "Denim Jeans", "Apparel"));
        catalog.addProduct(new Product(101, "Duplicate Test", "Misc"));

        Runnable printResults = () -> {

            // Search by ID
            System.out.println("\nSEARCH BY ID (102): " + (catalog.searchById(102) != null ? catalog.searchById(102) : "Not found"));
            System.out.println("SEARCH BY ID (999): " + (catalog.searchById(999) != null ? catalog.searchById(999) : "Not found"));

            // Search by Name
            List<Product> nameResults = catalog.searchByName("lap");
            System.out.println("\nSEARCH BY NAME ('lap'): " + (nameResults.isEmpty() ? "None" : ""));
            nameResults.forEach(System.out::println);

            // Search by Category
            List<Product> catResults = catalog.searchByCategory("electronics");
            System.out.println("\nSEARCH BY CATEGORY ('electronics'): " + (catResults.isEmpty() ? "None" : ""));
            catResults.forEach(System.out::println);
        };

        printResults.run();
    }
}

/*
OUTPUT:
-- E-commerce Platform Serach Function --

- Initializing Catalog -
ADDED: Laptop Pro X
ADDED: Wireless Mouse
ADDED: Running Shoes V2
ADDED: Hiking Boots
ADDED: Denim Jeans
WARN: Product ID 101 exists or invalid. Skipped.

SEARCH BY ID (102): ID:102 | Name:'Wireless Mouse' | Cat:'Electronics'
SEARCH BY ID (999): Not found

SEARCH BY NAME ('lap'):
ID:101 | Name:'Laptop Pro X' | Cat:'Electronics'

SEARCH BY CATEGORY ('electronics'):
ID:101 | Name:'Laptop Pro X' | Cat:'Electronics'
ID:102 | Name:'Wireless Mouse' | Cat:'Electronics'
*/