
package com.lms.LMSBorrower.BorrowerDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lms.LMSBorrower.POJO.BookCopies;
import com.lms.LMSBorrower.POJO.BookCopiesComposite;

@Repository

public interface BookCopiesDAO extends JpaRepository<BookCopies, BookCopiesComposite> {

}