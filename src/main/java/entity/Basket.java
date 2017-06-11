package entity;

import java.util.HashSet;
import java.util.Set;

public class Basket {

    public void add(Integer productToPriceId){
        productToCountList.add(new ProductToCount(productToPriceId, 1));
    }

    public void add(Integer productToPriceId, Integer count){

        productToCountList.add(new ProductToCount(productToPriceId, count));
    }

    public Integer getCountProducts(){
        return productToCountList.size();
    }

    public Double getCost(){
        return 0.0;
    }


    public Basket(){
        setProducts(new HashSet<ProductToCount>(10));
    }

    public Set<ProductToCount> productToCountList;

    public Set<ProductToCount> getProducts() {
        return productToCountList;
    }

    public void setProducts(Set<ProductToCount> productToCountList) {
        this.productToCountList = productToCountList;
    }


}
