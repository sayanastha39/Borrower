package com.lms.LMSBorrower.BorrowerDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lms.LMSBorrower.POJO.BookLoans;
import com.lms.LMSBorrower.POJO.BookLoansCompositeKey;

@Repository

public interface BookLoansDAO extends JpaRepository<BookLoans, BookLoansCompositeKey> {

}