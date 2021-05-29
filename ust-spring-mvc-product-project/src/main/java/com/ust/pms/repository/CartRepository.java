package com.ust.pms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ust.pms.model.Cart;

public interface CartRepository extends CrudRepository<Cart, Integer> {
	@Query("SELECT u FROM Cart u WHERE u.userId= :userId ")
	List<Cart> getCartItemsByUserId(@Param("userId")int userId);
	
	@Query("SELECT u FROM Cart u WHERE u.cartId= :cartId ")
	List<Cart> getCartItemsByCartId(@Param("cartId")int cartId);
	
	@Query("SELECT u FROM Cart u WHERE u.productId = :productId and u.userId= :userId")
	
	List<Cart> isProductExistInCart(@Param("productId") int pid,@Param("userId")int userId);
   
	@Query("SELECT u FROM Cart u WHERE u.productId = :productId and u.username= :username")
	
	Cart isProductExist(@Param("productId") int pid,@Param("username")String username);

}
