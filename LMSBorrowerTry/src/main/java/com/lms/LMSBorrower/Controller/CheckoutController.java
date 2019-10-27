package com.lms.LMSBorrower.Controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lms.LMSBorrower.POJO.Author;
import com.lms.LMSBorrower.POJO.BookLoans;
import com.lms.LMSBorrower.POJO.Borrower;
import com.lms.LMSBorrower.Service.BorrowerService;

@RestController 
@Component 

public class CheckoutController{
	@ExceptionHandler({MethodArgumentTypeMismatchException.class, JsonProcessingException.class, NullPointerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handle(Exception e) {
        return "Invalid Request";
    }

	@Autowired
	BorrowerService borrowerService;
	
	@GetMapping(path="/health/")
	public HttpStatus isHealthy() {
		return HttpStatus.OK;
	}
	
	@PutMapping(value="/borrower/checkout", consumes = {"application/xml", "application/json"}) 
	
	public  ResponseEntity<?> writeLoans(@RequestHeader("Accept") String accept, @RequestBody BookLoans bookLoans){
		
		boolean cardExists = borrowerService.ifCardExistsBorrower(bookLoans.getBlCompKey().getBorrower().getCardNo());
		boolean branchExists = borrowerService.ifCardExistsBranch(bookLoans.getBlCompKey().getBranch().getBranchId());
		boolean bookExists = borrowerService.ifCardExistsBook(bookLoans.getBlCompKey().getBranch().getBranchId(), bookLoans.getBlCompKey().getBook().getBookId());
		boolean checkedOut = borrowerService.existsCheckout
				(bookLoans.getBlCompKey().getBorrower().getCardNo(), bookLoans.getBlCompKey().getBranch().getBranchId(), bookLoans.getBlCompKey().getBook().getBookId());
		
		if((cardExists && branchExists  && bookExists) == true && checkedOut == false && bookLoans!= null)
		{
			boolean check = borrowerService.updateCopiesCheckout(bookLoans);
			
			if (check== true) {
			borrowerService.writeLoans(bookLoans);
			borrowerService.readEmbedded(bookLoans);
			return new ResponseEntity<BookLoans>(bookLoans, HttpStatus.CREATED);
			
			}
			else {
				return new ResponseEntity<String>("Copies less than 0", HttpStatus.UNPROCESSABLE_ENTITY);
			}
		}
		else {
			return new ResponseEntity<BookLoans>(bookLoans, HttpStatus.NOT_FOUND);
			}
	}
	
	
	@GetMapping (value="/borrower/{cardNo}", produces = {"application/xml", "application/json"}, consumes = {"application/xml", "application/json"}) 
	public Optional<Borrower> readtest(@PathVariable Integer cardNo) {
		 return borrowerService.readBorrowerbyId(cardNo);
	 }
}


