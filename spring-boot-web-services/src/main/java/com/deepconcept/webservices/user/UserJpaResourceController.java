package com.deepconcept.webservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.deepconcept.webservices.jpa.PostRepository;
import com.deepconcept.webservices.jpa.UserRepository;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
@RestController
public class UserJpaResourceController {
	
	@Resource
	UserRepository repository;
	
	@Resource 
	PostRepository postRepository;
	
	@GetMapping("/jap/users")
	public List<User> retriveAllUser(){
		return repository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public User retriveAllUser(@PathVariable int id){
		Optional<User> user =repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("User is not available of id "+id);
		}
		
		return user.get();
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		 User savedUser=repository.save(user);
		 
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	//
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id){
		
		repository.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrivePostsForUser(@PathVariable int id){
		Optional<User> user =repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("User is not available of id "+id);
		}
		return user.get().getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post){
		Optional<User> user =repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("User is not available of id "+id);
		}
		
		post.setUser(user.get());
		Post savedPost=postRepository.save(post);
		//Hateoas
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
		return ResponseEntity.created(location).build();
		
	}
}
