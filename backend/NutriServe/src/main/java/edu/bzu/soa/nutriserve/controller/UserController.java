package edu.bzu.soa.nutriserve.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.datastore.Key;

import edu.bzu.soa.nutriserve.datastore.TaskList;
import edu.bzu.soa.nutriserve.datastore.UserDao;
import edu.bzu.soa.nutriserve.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@Api(value="NutriServe User", description="User of NutriServe online service")
public class UserController {

	//static List<User> users = new ArrayList<User>();
	 @ApiOperation(value = "Get All Users in the system",response = List.class)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() {
		 
		 UserDao userDao = new UserDao();
		 List<User>  users= userDao.listUsers();
		  return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ApiOperation(value = "Get user with user id = id from the system",response = User.class)
	public  ResponseEntity<User>  getUserWithId(@PathVariable Long id) {
		 UserDao userDao = new UserDao();
		 List<User>  users =  userDao.listUsers();
		 
		 
	  for (User  user : users){
		  if ( user.getId() == id) {
			  return new ResponseEntity<User>(user, HttpStatus.OK);
		  }
	  }
	  User user = null;
	  return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
	 
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Register/add new user to the system",response = User.class)
	User addUser(@RequestBody User user) {
		Random r = new Random(); 
		int id = r.nextInt(100);
	
		user.setId(id);
		//users.add(user);
		 UserDao userDao = new UserDao();
		 Key key = userDao.addUser(user);
		 user.setId(key.getId());
		return user;
	}
	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update an existing user info on the system",response = User.class)
	User modifyUser(@RequestBody User user) {
		Random r = new Random(); 
		int id = r.nextInt(100);
	
		user.setId(id);
		//users.add(user);
		
		return user;
	}

}
