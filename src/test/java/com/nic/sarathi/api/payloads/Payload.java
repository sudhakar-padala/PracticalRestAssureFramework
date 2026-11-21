package com.nic.sarathi.api.payloads;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;



import com.github.javafaker.Faker;
import com.nic.sarathi.api.pojo.Address;
import com.nic.sarathi.api.pojo.Cart;
import com.nic.sarathi.api.pojo.CartProduct;
import com.nic.sarathi.api.pojo.Geolocation;
import com.nic.sarathi.api.pojo.Login;
import com.nic.sarathi.api.pojo.Name;
import com.nic.sarathi.api.pojo.Product;
import com.nic.sarathi.api.pojo.User;

public class Payload {
	
	private static final Faker faker=new Faker();
	private static final String categories[]= {"electronics","furniture","books","beauty","clothing"};
	private static final Random random= new Random();
	
	
	//Products
	public static Product productPayload() {
		String name=faker.commerce().productName();
		double price=Double.parseDouble(faker.commerce().price());
		String description=faker.lorem().sentence();
		String imageUrl="https://picsum.photos/200/300";
		String category=categories[random.nextInt(categories.length)];
		
		return new Product(name, price, description, imageUrl, category);
		
	}
	
	
//User
	public static User userpayload() {
		
		//Name
		
		String firstname=faker.name().firstName();
		String lastname=faker.name().lastName();
		
		Name name=new Name(firstname,lastname);
		
		//Geolocation
		
				String lat=faker.address().latitude();
				String lng=faker.address().longitude();
				
				Geolocation location= new Geolocation(lat, lng);
		
		
		//Address
		
		String city=faker.address().city();
		String street=faker.address().streetName();
		int number=random.nextInt(100);		
		String zipcode=faker.address().zipCode();
		Address address=new Address(city,street,number,zipcode,location);
		
		
		
		
		//User
		
		String email=faker.internet().emailAddress();
		String username=faker.name().username();
		String password=faker.internet().password();
		String phoneno=faker.phoneNumber().cellPhone();
		
		User user=new User(email,username,password,name,address,phoneno);
		
		return user;
		
	}
	
	//Carts
	public static Cart cartPayload(int userId) {
        List<CartProduct> products = new ArrayList<>();
        
        
        // Adding one random product to the cart
        int productId = random.nextInt(100);
        int quantity = random.nextInt(10) + 1;
               
        CartProduct cartProduct= new CartProduct(productId, quantity);
        products.add(cartProduct);

        
        //new Date()  ----> Returns date like  Wed Feb 19 13:17:45 IST 202
        // We need to convert this to "yyyy-MM-dd" format in String 
        
         SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);// Define output date format
         String date = outputFormat.format(new Date());//Converting to String
	    
        return new Cart(userId, date, products);
    }
	
	
	//Login
		public static Login loginPayload()
		{
			String username=faker.name().username();
			String password=faker.internet().password();
			
			Login login=new Login(username,password);
			return login;
			
		}
}
