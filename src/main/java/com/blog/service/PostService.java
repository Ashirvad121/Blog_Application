package com.blog.service;

import java.util.List;

import com.blog.payload.PostDto;

public interface PostService {
	public PostDto createPost(PostDto postDto);
	public List<PostDto> getAllPost();
	public PostDto getPostById(long id);
	public PostDto updatePost(PostDto postDto,long id);
	public PostDto deletePost(long id);
	
}
