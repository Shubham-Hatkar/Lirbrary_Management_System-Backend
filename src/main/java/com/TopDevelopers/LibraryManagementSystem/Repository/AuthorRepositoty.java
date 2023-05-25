package com.TopDevelopers.LibraryManagementSystem.Repository;

import com.TopDevelopers.LibraryManagementSystem.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepositoty extends JpaRepository<Author,Integer> {
}
