package com.pms.ust.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pms.ust.email.EmailService;
import com.ust.pms.model.Cart;
import com.ust.pms.model.CustomerData;
import com.ust.pms.model.MyNumbers;
import com.ust.pms.model.Numbers;
import com.ust.pms.model.Product;
import com.ust.pms.model.UserRegistration;
import com.ust.pms.service.CartService;
import com.ust.pms.service.CustomerDataService;
//import com.ust.pms.service.EmailService;
import com.ust.pms.service.MyNumbersService;
import com.ust.pms.service.ProductService;

@Controller

public class IndexController {
	@Autowired
	MyNumbersService myNumbersService;
	@Autowired
	CustomerDataService customerDataService;
	@Autowired
	CartService cartService;
	@Autowired
	ProductService productService;

	
	@RequestMapping("/addtocart")
	public ModelAndView addtocart(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		String username=null;
		Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			username=((UserDetails)principal).getUsername();
			System.out.println("username is:"+username);
		}
		System.out.println("username is:"+username);
		ModelAndView view=new ModelAndView();
		view.addObject("username",username);
		//ModelAndView view1=new ModelAndView();
		List<Cart> cartItems=cartService.getCartProducts();
	     int size=cartItems.size();
	     if(size>=10) {
	    	 view.addObject("fullCart","Your cart is full");
	     }
	     else {
				
		if(request.getParameter("productId")!=null && ! request.getParameter("productId").isEmpty())
		{
		cartService.addToCart(request.getParameter("productId"),
				request.getParameter("productName"),
				request.getParameter("quantityOnHand"),
				request.getParameter("quantity"),
				request.getParameter("price"));
		int productQuantity=Integer.parseInt(request.getParameter("quantity"));
		productService.updateProductQuantity(request.getParameter("productId"),productQuantity,"Sub");
	     
		}
	     }
	     
	     if(cartItems.isEmpty()) {
	    	 view.addObject("emptyCart","Your Cart is empty");
	    	 //customerDataService.sendCartMail(cartItems);
	    	 view.setViewName("view");
	    	 return view;
	       }
	     List<Cart> cartProducts=cartService.getCartProducts();
			view.addObject("cart",cartProducts);
			view.addObject("message","See your response..plz go through the cart")  ;
			view.addObject("sumTotal",cartService.subTotal(cartProducts));
			//customerDataService.sendCartMail(cartItems);
			view.setViewName("viewCart");		
			return view;
	    }
	@RequestMapping("/saveCart")
	public ModelAndView saveCart(Cart cart) {
		cartService.saveCart(cart);
		List<Cart> cartProducts=cartService.getCartProducts();
		ModelAndView view = new ModelAndView();
		view.addObject("cart",cartProducts);
		view.setViewName("viewCart");		
		return view;
	}
	@RequestMapping("/deleteCartProductById")
	public ModelAndView deleteCartProductById(Cart cart) {
		String username=null;
		Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			 username = ((UserDetails)principal).getUsername();
			System.out.println("username is:"+username);
		}
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("viewCart");
		modelAndView.addObject("command",new Product());
		int pid=cart.getCartId();
		Cart del = cartService.isProductExist(pid,username);
		if(del!=null)
		{
			cartService.deleteCartItem(pid);
			modelAndView.addObject("msg","Product with product Id:"+ pid+" exist..");
			
		}
		else
		{
			
			modelAndView.addObject("msg","Product with product Id:"+ pid+"does not exist..");
		}
		return modelAndView;
		
	}
	@RequestMapping("/index")
	public ModelAndView Index() {
		
		String username=null;
		Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			username=((UserDetails)principal).getUsername();
			System.out.println("username is:"+username);
		}
		System.out.println("username is:"+username);
		ModelAndView view=new ModelAndView();
		view.addObject("username",username);
		//view.addObject("password","ath@12");
		view.setViewName("index");
		return view;
				
	}
	@RequestMapping("/")
	public String Home() {
		return "Home";
	}
	@RequestMapping("/view")
	public ModelAndView customerViewProducts() {
		List<Product> products=productService.getProducts();
		return new ModelAndView("view","products",products);
	}
	@RequestMapping("/clientHome")
	public ModelAndView clientHome() {
		String username=null;
		Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			username=((UserDetails)principal).getUsername();
			System.out.println("username is:"+username);
		}
		System.out.println("username is:"+username);
		ModelAndView view=new ModelAndView();
		view.addObject("username",username);
		//view.addObject("password","ath@12");
		view.setViewName("clientHome");
		return view;
				
	}
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(Model model,String error,String logout) {
		if(error!=null) {
			model.addAttribute("errorMsg","Your username and password is incorrect");
		}
		if(logout!=null) {
			model.addAttribute("msg","Successfully logged out..");
		}
		return "login";
	}
	
	//register code
	@RequestMapping(value="/register" , method=RequestMethod.GET)
	public ModelAndView register() {
		return new ModelAndView("registration","user",new UserRegistration());
		
	}
	//register code-POST
	@Autowired
	JdbcUserDetailsManager jdbcUserDetailsManager;
	@Autowired
	EmailService emailService;
	@RequestMapping(value="/register" , method=RequestMethod.POST)
	public ModelAndView registeruser(@ModelAttribute("user") UserRegistration userRegistration)
	{
		//authorities to be granted
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		User user=new User(userRegistration.getUsername(),userRegistration.getPassword(),authorities);
		jdbcUserDetailsManager.createUser(user);
		customerDataService.saveUserData(userRegistration);
		emailService.sendMail(userRegistration.getMail(), "Registration Successful", "Hello,"+userRegistration.getUsername()+"Welcome to product App");
		return new ModelAndView("login","message","You have been successfully registered..please login to proceed");
		
		
		
	}
	@RequestMapping("/searchCartProduct")
	public ModelAndView searchCartProduct() {
		return new ModelAndView("searchCartProduct","command",new Cart());
	}
	@RequestMapping("/searchCartProductById")
	public ModelAndView searchCartProductById(Cart cart) {
		String username=null;
		Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			 username = ((UserDetails)principal).getUsername();
			System.out.println("username is:"+username);
		}
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("searchCartProduct");
		int pid=cart.getProductId();
		Cart cartPro = cartService.isProductExist(pid, username);
		if(cartPro!=null)
		{
			modelAndView.addObject("command",cartPro);
		}
		else
		{
			modelAndView.addObject("command",new Cart());
			modelAndView.addObject("msg","Product with product Id:"+ pid+"does not exist..");
		}
		return modelAndView;
	}
	
}
