package com.deepconcept.webservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deepconcept.webservices.user.Post;

public interface PostRepository extends JpaRepository<Post,Integer>{

}
