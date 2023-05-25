package com.TopDevelopers.LibraryManagementSystem.Repository;

import com.TopDevelopers.LibraryManagementSystem.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}
