package com.example.poshell.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {
    private Product product;
    private int amount;

    public boolean isProduct(Product product){
        return this.product.equals(product);
    }

    public boolean modify(Product product,int amount){
        if(isProduct(product)){
            this.amount = amount;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
        return product.toString() +"\t" + amount;
    }
}
