package Thirdpro.thirddemo.controller;
import java.net.URI;
import java.util.Arrays;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import Thirdpro.thirddemo.service.Service1;


@RestController
@RequestMapping("/control1")
public class controller1 {
	
	    @Autowired
	    private Service1 service;
	    
	    @GetMapping("/users")
		public List<User> retrieveAllUsers() {
			return service.findAll();
		}

		// GET /users/{id}
		@GetMapping("/users/{id}")
		public User retrieveUser(@PathVariable int id) {

			User user = service.findOne(id);
			if (user == null)
				throw new UserNotFoundException("id-" + id);

			return user;
		}
		
		@PostMapping("/users")
		public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {

			User saveduser = service.save(user);

			// CREATED
			// /user/{id} savedUser.getId()

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveduser.getId())
					.toUri();

			return ResponseEntity.created(location).build();

		}

		@DeleteMapping("/users/{id}")
		public void deleteUser(@PathVariable int id) {
			User user = service.deleteById(id);

			if (user == null)
				throw new UserNotFoundException("id-" + id);
		}

	
		@GetMapping("/friend")
		public List<controlBean> friend(){
			return Arrays.asList(new controlBean("Yuvarani","CEO",1),
					new controlBean("Darunraj","GM",2));
		}
	}


