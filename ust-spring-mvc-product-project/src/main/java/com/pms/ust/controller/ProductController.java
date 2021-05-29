package com.pms.ust.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ust.pms.model.Cart;
import com.ust.pms.model.Product;
import com.ust.pms.service.CartService;
import com.ust.pms.service.CustomerDataService;
import com.ust.pms.service.ProductService;

@Controller

public class ProductController {
	@Autowired
     ProductService productService;
	@Autowired
	CartService cartService;
	@Autowired
	CustomerDataService customerDataService;
	
	@RequestMapping("/addProduct")
	public ModelAndView addProduct() {
		
		//Command
		return new ModelAndView("addProduct","command",new Product());
			
			
	}
	
	@RequestMapping("/saveProduct")
	public ModelAndView saveProduct(Product product) {
		int pid=product.getProductId();
		ModelAndView view=new ModelAndView();
		productService.saveProduct(product);
		view.addObject("message"," Product "+pid+" Saved!");
		List<Product> products=productService.getProducts();
		view.addObject("products",products);
		view.setViewName("viewAllProducts");
		return view;
		
	}
	@RequestMapping("/addcart/{productId}/{quantity}")
	public ModelAndView addcart(@PathVariable ("productId") String productId,@PathVariable ("quantity") String quantity ) {
	ModelAndView view=new ModelAndView();
	view.addObject("productId",productId);
	view.addObject("quantity",quantity);
	view.setViewName("cart");
		return view;
	}
	
	@RequestMapping("/delete/{productId}")
	public ModelAndView deleteProduct(@PathVariable ("productId") String productId) {
		Integer pId= Integer.parseInt(productId);
		Product product = productService.getProduct(pId);
		String pName = product.getProductName();
		productService.deleteProduct(pId);
		ModelAndView view = new ModelAndView();
		view.addObject("message",pName+" Deleted");
		List<Product> products=productService.getProducts();
		view.addObject("products",products);
		view.setViewName("viewAllProducts");		
		return view;
	}
	//working delete cart product
	@RequestMapping("/deleteCart/{cartId}")
	public ModelAndView deletecartProduct(@PathVariable ("cartId") String cartId) {
		Integer pId= Integer.parseInt(cartId);
		Cart cart = cartService.getCartProduct(pId);
		String pName = cart.getProductName();
		cartService.deleteCartItem(pId);
		ModelAndView view = new ModelAndView();
		view.addObject("message",pName+" Deleted");
		List<Cart> cartProducts=cartService.getCartProducts();
		view.addObject("cart",cartProducts);
		view.setViewName("viewCart");		
		return view;
	}
	
	
	
	
	@RequestMapping("/updateProduct")
	public String updateProduct() {
		return "updateProduct";
	}
@RequestMapping("/searchProductByIdForm")
public ModelAndView searchProductByIdForm() {
	return new ModelAndView("searchProductByIdForm","command",new Product());
}
	@RequestMapping("/searchProductById")
	public ModelAndView searchProductById(Product product) {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("searchProductByIdForm");
		int pid=product.getProductId();
		if(productService.isProductExist(pid))
		{
			Product productDetails=productService.getProduct(pid);
			modelAndView.addObject("command",productDetails);
		}
		else
		{
			modelAndView.addObject("command",new Product());
			modelAndView.addObject("msg","Product with product Id:"+ pid+"does not exist..");
		}
		return modelAndView;
	}
	@RequestMapping("/deleteProductById")
	public ModelAndView deleteProductById(Product product) {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("searchProductByIdForm");
		modelAndView.addObject("command",new Product());
		int pid=product.getProductId();
		if(productService.isProductExist(pid))
		{
			productService.deleteProduct(pid);
			modelAndView.addObject("msg","Product with product Id:"+ pid+" exist..");
			
		}
		else
		{
			
			modelAndView.addObject("msg","Product with product Id:"+ pid+"does not exist..");
		}
		return modelAndView;
		
	}
	@RequestMapping("/viewAllProducts")
	public ModelAndView viewAllProducts() {
		List<Product> products=productService.getProducts();
		return new ModelAndView("viewAllProducts","products",products);
	}
	@RequestMapping("/customerViewProducts")
	public ModelAndView customerViewProducts() {
		List<Product> products=productService.getProducts();
		return new ModelAndView("customerViewProducts","products",products);
	}
	@RequestMapping("/viewCart")
	public ModelAndView customerViewCart() {
		List<Cart> cart =cartService.getCartProducts();
		List<Product> products=productService.getProducts();
		if(cart.size() ==0) {
			ModelAndView view=new ModelAndView();
			view.addObject("products",products);
			view.addObject("msg","Cart is empty");
			view.setViewName("view");
			return view;
			}
		 
		 ModelAndView view=new ModelAndView();
			view.addObject("cart",cart);
			int s=cartService.subTotal(cart);
			view.addObject("message","See your response..plz go through the cart")  ;
			view.addObject("sum",cartService.subTotal(cart));
			System.out.println("##########"+ s);
			//customerDataService.sendCartMail(cart);
			view.setViewName("viewCart");
			return view;
		//return new ModelAndView("viewCart","cart",cart);
	}
	
}
