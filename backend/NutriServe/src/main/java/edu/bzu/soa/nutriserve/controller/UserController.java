package edu.bzu.soa.nutriserve.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.bzu.soa.nutriserve.datastore.UserDao;
import edu.bzu.soa.nutriserve.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@Api(value="NutriServe User", description="User of NutriServe online service")
public class UserController {

	List<User> users = new ArrayList<User>();
	 @ApiOperation(value = "Get All Users in the system",response = List.class)
	@RequestMapping(method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return  users;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ApiOperation(value = "Get user with user id = id from the system",response = User.class)
	public User getUserWithId(@PathVariable Long id) {
		System.out.println("I am here ........");
		User user = new User();
		user.setId(1234567);
		user.setActivityStyle("Active");
		user.setBirthDate(new Date());
		user.setEmail("Eliasdkh@gmail.com");
		user.setGender("M");
		user.setHeight(180);
		user.setWeight(95);
		user.setName("Elias Khalil");
		return user;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Register/add new user to the system",response = User.class)
	User addUser(@RequestBody User user) {
		Random r = new Random(); 
		int id = r.nextInt(100);
	
		user.setId(id);
		users.add(user);
		UserDao dao = new UserDao();
		dao.addUser(user);
		return user;
	}
	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update an existing user info on the system",response = User.class)
	User modifyUser(@RequestBody User user) {
		Random r = new Random(); 
		int id = r.nextInt(100);
	
		user.setId(id);
		users.add(user);
		
		return user;
	}

}
