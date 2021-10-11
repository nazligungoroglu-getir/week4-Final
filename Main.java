package com.academy;

import com.academy.model.*;

import java.util.*;
import java.util.Scanner;

// Güniz Gizay Erhamamcı ile pair olarak bu ödevi yaptık. Bilgilerinize.

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    public static List<MainCategory> mainCategories = initializeData();
    public static ShoppingCart shoppingCart = new ShoppingCart();
    public static boolean isContinue = true;

    public static void main(String[] args) {
        System.out.println("Welcome!");
        landingPage();
    }

    private static void landingPage(){
        boolean isViewTheCartOptionIsShown = false;

        while(isContinue){
            System.out.println("Please choose an option: ");
            System.out.println("1 Main Menu");
            System.out.println("2 Search");
            System.out.println("3 Profile");

            if (isViewTheCartOptionIsShown){
                System.out.println("4 View the cart");
            }

            int option = Integer.parseInt(scanner.nextLine());

            switch (option){
                case 1:
                    mainMenu();
                    break;
                case 2:
                    search();
                    break;
                case 3:
                    profile();
                    break;
                case 4:
                    viewTheCart(shoppingCart);
                    break;
                default:
                    System.out.println("You chose invalid option!");
                    break;
            }
            isViewTheCartOptionIsShown = true;
        }

    }

    private static void mainMenu(){

        mainCategories(mainCategories, shoppingCart);

        System.out.println("Please choose an option:");
        System.out.println("1 View the cart");
        System.out.println("2 Back to LandingPage");

        int viewCartOrReturn = Integer.parseInt(scanner.nextLine());

        if (viewCartOrReturn == 1){
            viewTheCart(shoppingCart);
        }
        else if (viewCartOrReturn != 2){
            System.out.println("You chose invalid option!");
        }
    }

    private static void mainCategories(List<MainCategory> mainCategories, ShoppingCart shoppingCart){
        System.out.println("Main categories are: ");
        for (int i = 0; i < mainCategories.size(); i++) {
            System.out.println(i+1 + " " + mainCategories.get(i).getName());
        }

        int mainCategoryIndex = Integer.parseInt(scanner.nextLine());
        List<SubCategory> subCategories = mainCategories.get(mainCategoryIndex - 1).getSubCategories();

        for (int i = 0; i < subCategories.size(); i++) {
            System.out.println(i+1 + " " + subCategories.get(i).getName());
        }

        int subCategoryIndex = Integer.parseInt(scanner.nextLine());
        List<Product> products = subCategories.get(subCategoryIndex - 1).getProducts();

        for (int i = 0; i < products.size(); i++) {
            System.out.println(i+1 + " " + products.get(i).getName());
        }

        int productIndex = Integer.parseInt(scanner.nextLine());
        Product selectedProduct = products.get(productIndex - 1);
        printProductAndAddToShoppingCart(selectedProduct);
    }

    private static void printProductAndAddToShoppingCart(Product selectedProduct){
        System.out.println("Product detail:\nName: " + selectedProduct.getName() + " - Description: " +
                selectedProduct.getDescription() + " - Price: " + selectedProduct.getPrice() + " - Reduced Price: "
                + selectedProduct.getReducedPrice());

        System.out.println("Please enter quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(selectedProduct, quantity);
        shoppingCart.addCartItem(shoppingCartItem);
    }

    private static void search(){
        boolean isSearchSuccess = false;

        System.out.print("Please enter at least 3 characters to search: ");

        while(!isSearchSuccess){
            String searchText = scanner.nextLine();

            if (searchText.length() < 3){
                System.out.print("No result - Please enter at least 3 characters to search: ");
            }
            else {
                isSearchSuccess = true;
                List<Product> products = searchProducts(searchText);
                writeSearchedProducts(products);
            }
        }
    }

    private static void writeSearchedProducts(List<Product> products){
        if (products.size() == 0){
            System.out.println("No products found");
            return;
        }

        System.out.println("Found products: ");

        for (int i = 0; i < products.size(); i++) {
            System.out.println(i+1 + " " + products.get(i).getName());
        }

        int chosenProductIndex = Integer.parseInt(scanner.nextLine());
        Product chosenProduct = products.get(chosenProductIndex - 1);
        printProductAndAddToShoppingCart(chosenProduct);
    }

    private static List<Product> searchProducts(String searchText){
        List<Product> foundProducts = new ArrayList<>();
        List<Product> products = getAllProducts();

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().toLowerCase().contains(searchText.toLowerCase())){
                foundProducts.add(products.get(i));
            }
        }

        return foundProducts;
    }

    private static List<Product> getAllProducts(){
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < mainCategories.size(); i++) {
            for (int j = 0; j < mainCategories.get(i).getSubCategories().size(); j++) {
                products.addAll(mainCategories.get(i).getSubCategories().get(j).getProducts());
            }
        }

        return products;
    }

    private static void profile(){
        User ahmet = new User("Ahmet", "ahmet", "1234", "5552909999",
                "İstanbul / Avcılar");
        User mehmet = new User("Mehmet", "mehmet", "1234+", "05552909998",
                "Kocaeli / Gebze");
        User ayse = new User("Ayse", "ayse", "12345", "02121682898",
                "Konya / Meram");
        User fatma = new User("Fatma", "fatma", "A123456+", "01111111111",
                "Ordu / Ünye");
        User suspicious = new User("Suspicious", "hayride", "1234", "09999999999",
                "Samsun / Çarşamba");

        List<User> users = Arrays.asList(ahmet, mehmet, ayse, fatma, suspicious);

        System.out.println("Please choose an user: ");
        for (int i = 0; i < users.size(); i++) {
            User currentUser = users.get(i);
            System.out.println(i+1 + " User: " + currentUser.getName());
        }

        int userIndex = Integer.parseInt(scanner.nextLine());
        User user = users.get(userIndex - 1);

        System.out.println("User Detail:\nUser: " + user.getName() + " Username: " + user.getUserName() +
                " Password: " + user.getPassword() + " Phone: " + user.getPhone() + " Address: " +
                user.getAddress());

        System.out.println("Returning to LandingPage");
    }

    private static void viewTheCart(ShoppingCart shoppingCart){
        isContinue = false;
        List<ShoppingCartItem> shoppingCartItems = shoppingCart.getCartItems();

        for (int i = 0; i < shoppingCartItems.size(); i++) {
            ShoppingCartItem shoppingCartItem = shoppingCartItems.get(i);
            Product product = shoppingCartItem.getProduct();

            System.out.println("Cart Item Detail:\nProduct Name: " + product.getName() + " - Quantity: "
                    + shoppingCartItem.getQuantity() + " Item Price: "
                    + product.getReducedPrice() * shoppingCartItem.getQuantity());
        }

        double payableTotalPrice = shoppingCart.getTotalPrice() - shoppingCart.getTotalDiscount();

        double fee = 0;
        if (payableTotalPrice <= 100){
            fee = 5;
        }

        double payableTotalPriceWithFee = payableTotalPrice + fee;

        System.out.println("Total Price: " + shoppingCart.getTotalPrice() + " Total Earned Discount: "
                + shoppingCart.getTotalDiscount() + " Payable Total Price: " + payableTotalPrice
                + " Total Amount (Included Fee): " + payableTotalPriceWithFee);
    }

    private static List<MainCategory> initializeData() {
        MainCategory fruitsAndVegetables = new MainCategory("Fruit & Vegetables");
        MainCategory snack = new MainCategory("Snack");
        MainCategory breakfast = new MainCategory("Breakfast");

        SubCategory fruit = new SubCategory("Fruit");
        SubCategory vegetable = new SubCategory("Vegetable");
        SubCategory chips = new SubCategory("Chips");
        SubCategory chocolate = new SubCategory("Chocolate");
        SubCategory milk = new SubCategory("Milk");
        SubCategory delicatessen = new SubCategory("Delicatessen");

        Product apple = new Product("Apple", "Fresh apple", 6, 4);
        Product pear = new Product("Pear", "Fresh pear", 8, 5);
        Product grape = new Product("Grape", "Fresh grape", 9, 7);

        Product tomato = new Product("Tomato", "From garden tomato", 5, 3);
        Product pepper = new Product("Pepper", "From garden pepper", 3, 2);
        Product eggplant = new Product("Eggplant", "From garden eggplant", 7, 5);

        Product lays = new Product("Lays", "Spicy", 5, 3);
        Product doritos = new Product("Doritos", "Cheese included", 3, 2);
        Product ruffles = new Product("Ruffles", "Crispy", 7, 5);

        Product tadelle = new Product("Tadelle", "Fine", 3, 2);
        Product albeni = new Product("Albeni", "Nice", 3, 2);
        Product metro = new Product("Metro", "Great", 5, 4);
        Product hobby = new Product("Hobby", "Old", 7, 5);

        Product fullFat = new Product("Full-fat", "8% fat included", 8, 6);
        Product lowFat = new Product("Low-fat", "2% fat included", 10, 9);
        Product lactoFree = new Product("Lacto-free", "Lactose free", 12, 10);

        Product cheddar = new Product("Cheddar", "Type of cheese", 8, 6);
        Product cheese = new Product("Cheese", "Ezine", 10, 9);
        Product salami = new Product("Salami", "Salam", 12, 10);
        Product sausage = new Product("Sausage", "German sausage", 12, 10);
        Product olive = new Product("Olive", "Marmara Birlik", 12, 10);

        fruitsAndVegetables.addSubCategories(Arrays.asList(fruit, vegetable));
        snack.addSubCategories(Arrays.asList(chips, chocolate));
        breakfast.addSubCategories(Arrays.asList(milk, delicatessen));

        fruit.addProducts(Arrays.asList(apple, pear, grape));
        vegetable.addProducts(Arrays.asList(tomato, pepper, eggplant));
        chips.addProducts(Arrays.asList(lays, doritos, ruffles));
        chocolate.addProducts(Arrays.asList(tadelle, albeni, metro, hobby));
        milk.addProducts(Arrays.asList(fullFat, lowFat, lactoFree));
        delicatessen.addProducts(Arrays.asList(cheddar, cheese, salami, sausage, olive));

        return Arrays.asList(fruitsAndVegetables, snack, breakfast);
    }
}
