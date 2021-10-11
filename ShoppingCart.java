package com.academy.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<ShoppingCartItem> cartItems;

    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
    }

    public List<ShoppingCartItem> getCartItems() {
        return cartItems;
    }

    public void addCartItem(ShoppingCartItem cartItem) {
        if (!isExist(cartItem)){
            this.cartItems.add(cartItem);
        }
    }

    public double getTotalPrice() {
        double total = 0;
        for (int i = 0; i < this.cartItems.size(); i++) {
            total += cartItems.get(i).getProduct().getPrice() * cartItems.get(i).getQuantity();
        }

        return total;
    }

    public double getTotalDiscount(){
        double discount = 0;
        for (int i = 0; i < this.cartItems.size(); i++) {
            double unitDiscount = cartItems.get(i).getProduct().getPrice() - cartItems.get(i).getProduct().getReducedPrice();
            discount += unitDiscount * cartItems.get(i).getQuantity();
        }

        return discount;
    }

    private boolean isExist(ShoppingCartItem cartItem) {
        for (int i = 0; i < this.cartItems.size(); i++) {

            ShoppingCartItem shoppingCartItem = cartItems.get(i);

            if (shoppingCartItem.getProduct().equals(cartItem.getProduct())) {
                shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() + cartItem.getQuantity());
                return true;
            }
        }

        return false;
    }
}
