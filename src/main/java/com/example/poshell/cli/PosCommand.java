package com.example.poshell.cli;

import com.example.poshell.biz.PosService;
import com.example.poshell.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class PosCommand {

    private PosService posService;

    @Autowired
    public void setPosService(PosService posService) {
        this.posService = posService;
    }

    @ShellMethod(value = "List Products", key = "p")
    public String products() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (Product product : posService.products()) {
            stringBuilder.append("\t").append(++i).append("\t").append(product).append("\n");
        }
        return stringBuilder.toString();
    }

    @ShellMethod(value = "New Cart", key = "n")
    public String newCart() {
        return posService.newCart() + " OK";
    }

    @ShellMethod(value = "Add a Product to Cart", key = "a")
    public String addToCart(String productId, int amount) {
        if (posService.add(productId, amount)) {
            return posService.getCart().toString();
        }
        return "ERROR";
    }

    @ShellMethod(value = "Empty all the products in Cart",key = "e")
    public String emptyCart(){
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("Try to empty the cart.\n");
        if(posService.empty(posService.getCart())){
            strBuilder.append(posService.getCart().toString());
        }else{
            strBuilder.append("ERROR");
        }
        return strBuilder.toString();
    }

    @ShellMethod(value = "Show all items in cart",key = "s")
    public String showCart(){
        return posService.total(posService.getCart());
    }

    @ShellMethod(value = "Checkout all items in cart and empty the cart",key = "c")
    public String checkoutCart(){
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("Try to checkout the cart.\n");
        strBuilder.append(posService.total(posService.getCart()));
        if(posService.empty(posService.getCart())){
            strBuilder.append("\nSucceed checkout!");
        }else{
            strBuilder.append("\nFail to checkout!");
        }
        return strBuilder.toString();
    }

    @ShellMethod(value = "Modify item in cart",key = "m")
    public String modifyItemInCart(String productId,int amount){
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("Try to modify specific item in cart.\n");
        if(posService.modify(productId, amount)){
            strBuilder.append("Succeed modifying ").append(productId).append("\n");
            strBuilder.append(posService.total(posService.getCart()));
        }else{
            strBuilder.append("Fail to modify ").append(productId).append("\n");
        }
        return strBuilder.toString();
    }

    @ShellMethod(value = "Add a new type of product", key = "ap")
    public String addNewProduct(String productName,int productPrice){
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("Try to add a new product to products.\n");
        if(posService.addProduct(productName, productPrice)){
            strBuilder.append("Succeed to adding ");
        }else{
            strBuilder.append("Fail to add .");
        }
        strBuilder.append(productName+".");
        return strBuilder.toString();
    }
}
