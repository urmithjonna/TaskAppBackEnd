package com.example.project.repository;

import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.dto.Userdto;
import com.example.project.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	 public void save(Userdto userDto);

	 public Optional<Users>  findByEmail(String email);

}
