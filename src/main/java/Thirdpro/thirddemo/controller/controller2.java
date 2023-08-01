package Thirdpro.thirddemo.controller;

import java.net.URI;
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


@RestController
@RequestMapping("/control2")
public class controller2 {
	
	    @Autowired
	    private UserRepository userRepository;
	    
	    @GetMapping("/jpa/users")
		public List<User> retrieveAllUsers() {
			return userRepository.findAll();
		}
        
	    @DeleteMapping("/jpa/users/{id}")
		public void deleteUser(@PathVariable int id) {
			userRepository.deleteById(id);
		}

		//
		// input - details of user
		// output - CREATED & Return the created URI

		// HATEOAS

		@PostMapping("/jpa/users")
		public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
			User savedUser = userRepository.save(user);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
					.toUri();

			return ResponseEntity.created(location).build();

		}
		

		@GetMapping("/method")
		public controlBean method() {
			return new controlBean("Pookutty","CEO",1);
		}
	}

