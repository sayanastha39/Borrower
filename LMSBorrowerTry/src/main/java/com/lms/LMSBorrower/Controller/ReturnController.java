package com.lms.LMSBorrower.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lms.LMSBorrower.POJO.BookLoans;
import com.lms.LMSBorrower.Service.BorrowerService;

@RestController //whole control is done through this 
@Component //initializing bean

public class ReturnController {
	@ExceptionHandler({MethodArgumentTypeMismatchException.class, JsonProcessingException.class, NullPointerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handle(Exception e) {
        return "Invalid Request";
    }

	@Autowired
	BorrowerService borrowerService;

	@PutMapping(value = "/borrower/return", 
			consumes={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
			produces={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	
	public ResponseEntity<BookLoans> writeReturn(@RequestBody BookLoans bookLoans){
		
		boolean cardExists = borrowerService.ifCardExistsBorrower(bookLoans.getBlCompKey().getBorrower().getCardNo());
		boolean branchExists = borrowerService.existsBranchReturn(bookLoans.getBlCompKey().getBranch().getBranchId());
		boolean bookExists = borrowerService.existsBookReturn
				(bookLoans.getBlCompKey().getBorrower().getCardNo(), bookLoans.getBlCompKey().getBranch().getBranchId(), bookLoans.getBlCompKey().getBook().getBookId());
		
		if(cardExists && branchExists && bookExists == true && bookLoans!= null){
			borrowerService.writeReturn(bookLoans);
			borrowerService.updateCopiesReturn(bookLoans);
			borrowerService.readEmbedded(bookLoans);
			return new ResponseEntity<BookLoans>(bookLoans, HttpStatus.ACCEPTED);
			}
		else {
			return new ResponseEntity<BookLoans>(bookLoans, HttpStatus.NOT_FOUND);
		}
	}
}
