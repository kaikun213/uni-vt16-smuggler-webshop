/**
 * 
 */
package pages;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import baseClasses.Page;
import baseClasses.Product;

/**
 * @author kaikun
 *
 */

@Named
@SessionScoped
public class Mainpage extends Page implements Serializable {
	
	private List<Product> products = new ArrayList<Product>();
	private String category = "";
	private String search = "";
	
	/**
	 * Default serialVersionID generated from eclipse
	 */
	private static final long serialVersionUID = 1L;
	
	
	/** b is true if there is no search or category selected -> show all items
	 * sets the content to all available products or the searched/category ones from the web-shop
	 * creates objects for all/selected products in content and puts them into the <products> list.
	 */
	public void setProducts(Boolean b) {
		try {
			// default : display all products
			if (b) setContent("select * from webshopDB.product");
			// category : display products from selected category
			else if (!category.isEmpty()) setContent("select * from webshopDB.product, webshopDB.category WHERE product.category=category.id AND category.name=\""+category+"\";");
			// search : display searched products - not case-sensitive and searches substrings
			else setContent("SELECT * FROM webshopDB.product WHERE UPPER(name) LIKE '%"+search.toUpperCase()+"%' OR UPPER(description) LIKE '%"+search.toUpperCase()+"%';");
			products = toProducts(content);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the content and the product list.
	 * @return the list of products in the databease. 
	 */
	public List<Product> getProducts(){
		// default : display all products
		if (category.isEmpty() && search.isEmpty()) setProducts(true);
		// search or category : display specific
		else setProducts(false);
		// Reset category to show all products by default again!
		category = "";
		search = "";
		return products;
	}	

	/**
	 * returns a List with all the category names
	 */
	@Override
	public List<String> getCategories(){
		List<String> categories = new ArrayList<String>();
		try {
			categories = super.getCategories();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}
	
	
	public void setCategory(String s){
		category = s;
	}
	
	public String getCategory(){
		return category;
	}
	
	public void setSearch(String s){
		search = s;
	}
	
	public String getSearch(){
		return search;
	}
	
}