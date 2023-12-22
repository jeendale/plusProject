package com.sparta.plusproject.repository;

import com.sparta.plusproject.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long>{

}
