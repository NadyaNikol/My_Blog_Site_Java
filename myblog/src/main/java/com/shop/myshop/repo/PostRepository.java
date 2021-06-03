package com.shop.myshop.repo;

import com.shop.myshop.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {


}
