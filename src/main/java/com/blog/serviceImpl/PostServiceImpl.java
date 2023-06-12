package com.blog.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;
import com.blog.repository.PostRepository;
import com.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService{
	private PostRepository PostRepository;
	
	@Autowired
	public PostServiceImpl(com.blog.repository.PostRepository postRepository) {
		super();
		PostRepository = postRepository;
	}


	@Override
	public PostDto createPost(PostDto postDto) {
		// TODO Auto-generated method stub
		//convert DTO to entity
		Post post=mapToEntity(postDto);
	    Post newPost=PostRepository.save(post);
	    System.out.println(newPost);
	    //convert entity to DTO
	    PostDto postResponse=mapToDto(newPost);
		return postResponse;
	}
	
	private PostDto mapToDto(Post post) {
		PostDto postDto=new PostDto();
		postDto.setContent(post.getContent());
		postDto.setDescription(post.getDescription());
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		return postDto;
	}
	private Post mapToEntity(PostDto postDto) {
		Post post=new Post();
		post.setContent(postDto.getContent());
		post.setDescription(postDto.getDescription());
		post.setTitle(postDto.getTitle());
		return post;
	}


	@Override
	public PostResponse getAllPost(int pageNo, int pageSize,String sortBy,String sortDir) {
		//create Pageable instance
		//Pageable is an interface
		//AbstractPageRequest is an class which implement pageable
		//PageRequest is a class which extend AbstractPageRequest
		//of() is a static method belong from PageRequest
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		
		Pageable pageable=PageRequest.of(pageNo, pageSize,sort);
		Page<Post> posts= PostRepository.findAll(pageable);
		//get content from page object
		List<Post> listOfPosts= posts.getContent();
		List<PostDto> contents= listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(contents);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLast(posts.isLast());
		return postResponse;
	}


	@Override
	public PostDto getPostById(long id) {
		// TODO Auto-generated method stub
		Post post= PostRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("posts", "id", id));
		PostDto postDto =mapToDto(post);
		return postDto;
	}


	@Override
	public PostDto updatePost(PostDto postDto,long id) {
		// TODO Auto-generated method stub
		Post post=PostRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post", "id", id));
		post.setContent(postDto.getContent());
		post.setDescription(postDto.getDescription());
		post.setTitle(postDto.getTitle());
		Post updatedPost= PostRepository.save(post);
		return mapToDto(updatedPost);
	}


	@Override
	public PostDto deletePost(long id) {
		// TODO Auto-generated method stub
		Post post=PostRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		PostRepository.delete(post);
		return mapToDto(post);
	}
	
	
}
