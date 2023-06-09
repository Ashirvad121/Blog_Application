package com.blog.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
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
	public List<PostDto> getAllPost() {
		// TODO Auto-generated method stub
		List<Post> listOfPost= PostRepository.findAll();
		//convert List<Post> to List<PostDto>
		List<PostDto> listOfPostDto=new ArrayList<>();
		for(Post post : listOfPost) {
			PostDto postDto= mapToDto(post);
			listOfPostDto.add(postDto);
		}
		return listOfPostDto;
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
