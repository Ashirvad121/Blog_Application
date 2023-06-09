package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.PostDto;
import com.blog.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	private PostService postService;

	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	//create blog post
	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
		return new ResponseEntity(postService.createPost(postDto),HttpStatus.CREATED);
	}
	@GetMapping
	public ResponseEntity<List<PostDto>> getAllPosts(){
		List<PostDto> postDto= postService.getAllPost();
		return new ResponseEntity(postDto,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
		return new ResponseEntity(postService.getPostById(id),HttpStatus.OK);
	}
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@PathVariable("id") long id, @RequestBody PostDto postDto){
		return new ResponseEntity<>(postService.updatePost(postDto, id),HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<PostDto> deletePost(@PathVariable("id") long id){
		return new ResponseEntity<>(postService.deletePost(id),HttpStatus.OK);
	}
}
