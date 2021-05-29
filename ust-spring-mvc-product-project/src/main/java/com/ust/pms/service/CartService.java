package com.ust.pms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ust.pms.model.Cart;
import com.ust.pms.model.CustomerData;
import com.ust.pms.model.Product;
import com.ust.pms.repository.CartRepository;


@Service

public class CartService {
	
	@Autowired
	CartRepository cartRepository; 
	@Autowired
	ProductService productService;
	@Autowired
	CustomerDataService customerDataService;
	 public void saveCart(Cart cart)
	  {
		  //This will save the product in database
		  cartRepository.save(cart);
		
	  }
	 public String addToCart(String productId,String productName,String totalQuantity,String quantity,String price) {
		 String username=null;
		//username=customerDataService.getUserFromPrincipal();
		
		 Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 if(principal instanceof UserDetails) {
			 username=((UserDetails)principal).getUsername();			 
		 }
		List<CustomerData> userList=customerDataService.findByUsername(username);
		CustomerData userData=userList.get(0);
		 System.out.println("#######Username"+userData.getUsername());
		 Product product=productService.getProduct(Integer.parseInt(productId));
		 Cart cart=new Cart();
        List<Cart> cartProducts=cartRepository.isProductExistInCart(Integer.parseInt(productId),userData.getUserId());
		 //List<Integer> pid=new ArrayList<>();
		// pid.add(Integer.parseInt(productId));
       
       
		 if(cartProducts !=null && !cartProducts.isEmpty()) {
			 Cart existingCart= cartProducts.get(0);
			 cart.setCartId(existingCart.getCartId());
			 cart.setQuantity(Integer.sum(existingCart.getQuantity(),Integer.parseInt(quantity)));
			 cart.setPrice(cart.getQuantity() * product.getPrice());
		 }
		 else {
			 cart.setQuantity(Integer.parseInt(quantity));
		 }
		 cart.setProductId(Integer.parseInt(productId));
		 cart.setUserId(userData.getUserId());
		 cart.setUsername(username);
		 cart.setPrice(Integer.parseInt(price));
		 cart.setProductName(product.getProductName());
		 //cart.setQuantity(Integer.parseInt(quantity));
		 cartRepository.save(cart);		 
		
		
       return "";
	 	
	 }
		
		
	public List<Cart> getCartProducts(){
		String username=customerDataService.getUserFromPrincipal();
		List<CustomerData> userList=customerDataService.findByUsername(username);
		CustomerData userdata=userList.get(0);
		List<Cart> cartProducts= cartRepository.getCartItemsByUserId(userdata.getUserId());
		return cartProducts;
		
	}
	public void deleteCartItem(int cartId) {
		cartRepository.deleteById(cartId);
	}
	public Cart getCartProduct(Integer cartId)
	  {
		  Optional<Cart> cart = cartRepository.findById(cartId);
		  return cart.get();
	  }
	 
	public Cart isProductExist(int pid,String userName) {
		
		return cartRepository.isProductExist(pid,userName);
	}
	 public void updateProduct(Cart cart)
	  {
		  //saveOr Update
		  cartRepository.save(cart);
		  
	  }
	  public Cart getProduct(Integer productId)
	  {
		  Optional<Cart> cart = cartRepository.findById(productId);
		  return cart.get();
	  }
	public void updateCartItem(String cartItem,String quantity,String product) {
		int cartId= Integer.parseInt(cartItem);
		int cartQuantity=Integer.parseInt(quantity);
		Optional<Cart> cart=cartRepository.findById(cartId);
		Cart shoppingCart=cart.get();
		
		if(cartQuantity>shoppingCart.getQuantity()) {
			int productQuantity= cartQuantity-shoppingCart.getQuantity();
			productService.updateProductQuantity(product, productQuantity, "Sub");
		}else if(cartQuantity<shoppingCart.getQuantity()) {
			int productQuantity=shoppingCart.getQuantity()-cartQuantity;
			productService.updateProductQuantity(product, productQuantity, "Add");
		}
		shoppingCart.setQuantity(cartQuantity);
		cartRepository.save(shoppingCart);
		
	}
	public int subTotal(List<Cart> cartProducts) {
		int total=cartProducts.stream().map(pm ->pm.getPrice()).mapToInt(i->i.intValue()).sum();
		System.out.println("################Total"+total);
		return total;
	}
	

}
