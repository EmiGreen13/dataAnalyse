package entity;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private int x = 5;

    public void add(Product product){
        productToCountList.add(new ProductToCount(product, 1));
    }

    public void add(Product product, Integer count){
        productToCountList.add(new ProductToCount(product, count));
    }

    public Integer getCountProducts(){
        return productToCountList.size();
    }

    public Double getCost(){
        return 0.0;
    }


    public Basket(){
        setProducts(new ArrayList<ProductToCount>(10));
    }

    private List<ProductToCount> productToCountList;

    public List<ProductToCount> getProducts() {
        return productToCountList;
    }

    public void setProducts(List<ProductToCount> productToCountList) {
        this.productToCountList = productToCountList;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
