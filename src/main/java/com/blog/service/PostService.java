package com.blog.service;

import java.util.List;

import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;

public interface PostService {
	public PostDto createPost(PostDto postDto);
	public PostResponse getAllPost(int pageNo, int pageSize,String sortBy,String sortDir);
	public PostDto getPostById(long id);
	public PostDto updatePost(PostDto postDto,long id);
	public PostDto deletePost(long id);
	
}
