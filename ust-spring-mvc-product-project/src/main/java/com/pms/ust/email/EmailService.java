package com.pms.ust.email;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ust.pms.model.Cart;


@Service("emailService")
public class EmailService  {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SimpleMailMessage preConfiguredMessage;

    public void sendMail(
      String to, String subject, String body) {
        
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("capestoneservice@gmail.com");
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(body);
        mailSender.send(message);
        
    }
    public void sendPreConfiguredMail(String message) {
    	SimpleMailMessage mailMessage=new SimpleMailMessage(preConfiguredMessage);
    	mailMessage.setText(message);
    	mailSender.send(mailMessage);
    }
    public String sendCartDetails(List<Cart> cartItems,String emailId,String subject,String name) {
		String result=null;
		MimeMessage message=mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper= new MimeMessageHelper(message,false,"utf-8");
			StringBuilder sb1=new StringBuilder(200);
			sb1.append("<html>"+"<body>Hi"+name+",<br/>"+"<div class='container'><table class='table' border='5px' style='border:2px blue'></br>");
		    if(!cartItems.isEmpty()) {
		    	sb1.append("<tr class='success'>Your cart is full ..please visit the site and purchase.<br/><td>");
		    	sb1.append("CART ID")
		    	.append("</td><br><td>")
		    	.append("PRODUCT NAME")
		    	.append("</td><br><td>")
		    	.append("PRODUCT ID")
		    	.append("</td><br><td>")
		    	.append("QUANTITY IN CART")
		    	.append("</td><br><td>")
		    	.append("PRICE")
		    	.append("</td><br><td>")
		    	.append("TOTAL AMT");
		    	sb1.append("</td><br><td>");
		    	for(Cart cat:cartItems) {
		    		sb1.append("<tr class='info'><br/><td>");
		    		sb1.append(cat.getCartId())
		    		.append("</td><br><td>")
		    		.append(cat.getProductName())
		    		.append("</td><br><td>")
		    		.append(cat.getProductId())
		    		.append("</td><br><td>")
		    		.append(cat.getQuantity())
		    		.append("</td><br><td>")
		    		.append(cat.getPrice())
		    		.append("</td><br><td>")
		    		.append(cat.getPrice());
		    		sb1.append("</td><br><td>");
		    		String in=sb1.toString();
		    	}
		    	
		    	
		    }else {
		    	sb1.append("Your cart is empty");
		    }
		    sb1.append("</table></br></body></html>");
		    message.setContent(sb1.toString() , "text/html");
		    helper.setTo(emailId);
		    helper.setSubject(subject);
		    result="success";
		    mailSender.send(message);
		    
		}catch(MessagingException e) {
			throw new MailParseException(e);
		}finally {
			if(result!="success") {
				result="fail";
			}
		}return result;
}
}