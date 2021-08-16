package com.example.auth;

import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository <Post,Integer> {
    Iterable<Post> findAllByApplicationUserId(Integer id);

}
