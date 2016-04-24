package pages;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import baseClasses.Page;
import baseClasses.Product;

import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class Basket extends Page implements Serializable{

    static List<Product> products = new ArrayList<Product>();

    /**
     *
     * @return List<Products>
     */
    public List<Product> getBasket(){
        return products;
    }

    /**
     *
     ** @param  Product updated
     */

    public void edit(Product old, Product updated){
        int place = products.indexOf(old);
        //products.addAt(place, updated);
        products.remove(place);
        products.add(updated);
    }

    /**
     * @param selected
     *
     */

    public void remove(Product selected, Product old){
        int place = products.indexOf(old);
        products.remove(place);
    }

    /**
     *
     * @param Product item
     */

    public void add(Product item){
        products.add(item);
    }
}