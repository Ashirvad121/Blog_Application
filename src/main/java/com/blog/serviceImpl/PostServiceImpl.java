package com.blog.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Post;
import com.blog.payload.PostDto;
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
		System.out.println(postDto);
		Post post=new Post();
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		System.out.println(post);
		
	    Post newPost=PostRepository.save(post);
	    System.out.println(newPost);
	    //convert entity to DTO
	    PostDto postResponse=new PostDto();
	    postResponse.setContent(newPost.getContent());
	    postResponse.setDescription(newPost.getDescription());
	    postResponse.setTitle(newPost.getTitle());
	    postResponse.setId(newPost.getId());
		return postResponse;
	}

}
