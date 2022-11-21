package com.deepconcept.webservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deepconcept.webservices.user.User;
public interface UserRepository extends JpaRepository<User,Integer>{

}
