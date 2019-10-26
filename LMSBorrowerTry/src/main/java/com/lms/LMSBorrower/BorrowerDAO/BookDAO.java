package com.lms.LMSBorrower.BorrowerDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lms.LMSBorrower.POJO.Book;

@Repository

public interface BookDAO extends JpaRepository<Book, Integer> {

}