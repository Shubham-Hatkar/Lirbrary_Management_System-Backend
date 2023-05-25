package com.TopDevelopers.LibraryManagementSystem.Repository;

import com.TopDevelopers.LibraryManagementSystem.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>
{
}
