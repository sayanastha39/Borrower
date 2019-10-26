package com.lms.LMSBorrower.BorrowerDAO;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lms.LMSBorrower.POJO.Author;

@Repository

public interface AuthorDAO extends JpaRepository<Author, Integer> {

}