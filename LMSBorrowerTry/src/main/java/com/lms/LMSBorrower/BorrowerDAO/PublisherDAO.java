
package com.lms.LMSBorrower.BorrowerDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lms.LMSBorrower.POJO.Publisher;

@Repository

public interface PublisherDAO extends JpaRepository<Publisher, Integer> {

}