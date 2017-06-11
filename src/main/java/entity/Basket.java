package entity;

import java.util.HashMap;
import java.util.Map;

public class Basket {

    public void add(Integer productToPriceId){
        productToCountList.put(productToPriceId, new ProductToCount(productToPriceId, 1));
    }

    public void add(Integer productToPriceId, Integer count){

        productToCountList.put(productToPriceId, new ProductToCount(productToPriceId, count));
    }

    public Integer getCountProducts(){
        return productToCountList.size();
    }

    public Double getCost(){
        return 0.0;
    }


    public Basket(){
        setProducts(new HashMap<Integer, ProductToCount>(20));
    }

    public Map<Integer, ProductToCount> productToCountList;

    public Map<Integer, ProductToCount> getProducts() {
        return productToCountList;
    }

    public void setProducts(Map<Integer, ProductToCount> productToCountList) {
        this.productToCountList = productToCountList;
    }


}
