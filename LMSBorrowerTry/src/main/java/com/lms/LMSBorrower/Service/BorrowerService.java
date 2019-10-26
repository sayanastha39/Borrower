package com.lms.LMSBorrower.Service;

import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.lms.LMSBorrower.BorrowerDAO.*;
import com.lms.LMSBorrower.POJO.Author;
import com.lms.LMSBorrower.POJO.Book;
import com.lms.LMSBorrower.POJO.BookCopies;
import com.lms.LMSBorrower.POJO.BookCopiesComposite;
import com.lms.LMSBorrower.POJO.BookLoans;
import com.lms.LMSBorrower.POJO.BookLoansCompositeKey;
import com.lms.LMSBorrower.POJO.Borrower;
import com.lms.LMSBorrower.POJO.LibraryBranch;
import com.lms.LMSBorrower.POJO.Publisher;

@Component
public class BorrowerService {
	
	
	 @Autowired 
	 BookLoansDAO bookLoansDao;
	 
	 @Autowired //it lets you access DAO without creating instance
	 LibraryBranchDAO libraryBranchDao;
	 
	 @Autowired //it lets you access DAO without creating instance
	 BookDAO bookDao;
	 
	 @Autowired //it lets you access DAO without creating instance
	 BorrowerDAO borrowerDao;
	 
	 @Autowired
	 PublisherDAO publisherDao;
	 
	 @Autowired
	 AuthorDAO authorDao;
	 
	 @Autowired 
	 BookCopiesDAO bookCopiesDao;
	 
	 
	 
	 public boolean ifCardExistsBorrower(int cardNo){
		 List<Borrower> list = borrowerDao.findAll();
		 boolean cardExists = list.stream()
	                .anyMatch(id -> id.getCardNo().equals(cardNo));
		 return cardExists;
		 }
	 
	 public boolean ifCardExistsBranch(int branchId) {
		 List<LibraryBranch> bch = libraryBranchDao.findAll();
		 boolean branchExists = bch.stream()
		                .anyMatch(id -> id.getBranchId().equals(branchId));
		 return branchExists;
		 } 
	 
		//  book checkout    
	 public boolean ifCardExistsBook( int branchId, int bookId){
		 List<BookCopies> bk = bookCopiesDao.findAll();
		 boolean bookExists  = bk.stream()
			                .anyMatch(id -> id.getBookCopiesComposite().getBook().getBookId().equals(bookId) && id.getBookCopiesComposite().getBook().getBookId().equals(branchId));
		 return bookExists;
		 } 
	 
	 public boolean existsBranchReturn(int branchId) {
		 List<LibraryBranch> bch = libraryBranchDao.findAll();
		 boolean branchExists = bch.stream()
		                .anyMatch(id -> id.getBranchId().equals(branchId));
		 return branchExists;
		 } 
	 
	 //book return
	 public boolean existsBookReturn( int cardNo, int branchId,  int bookId){
		 List<BookLoans> bk = bookLoansDao.findAll();
		 boolean bookExists = bk.stream()
			                .anyMatch(id -> id.getBlCompKey().getBook().getBookId().equals(bookId)&&
			                		id.getBlCompKey().getBranch().getBranchId().equals(branchId)&&
			                		id.getBlCompKey().getBorrower().getCardNo().equals(cardNo));
		 return bookExists;
		 } 
	 
	 //CHECKOUT VALIDATE IF Exists
	 public boolean existsCheckout( int cardNo, int branchId,  int bookId){
		 List<BookLoans> bk = bookLoansDao.findAll();
		 boolean checkedOut = bk.stream()
			                .anyMatch(id -> id.getBlCompKey().getBook().getBookId().equals(bookId)&&
			                		id.getBlCompKey().getBranch().getBranchId().equals(branchId)&&
			                		id.getBlCompKey().getBorrower().getCardNo().equals(cardNo));
		 return checkedOut;
		 }
	 	 
	 public BookLoans writeLoans(BookLoans bookLoans){
		 Timestamp timestamp = new Timestamp(new Date().getTime());
			Calendar duetime = Calendar.getInstance();
			
		 bookLoans.setDateOut(timestamp);
		 
			duetime.add(Calendar.DAY_OF_MONTH, 7);
			timestamp = new Timestamp(duetime.getTime().getTime());
			bookLoans.setDueDate(timestamp);
		 return bookLoansDao.save(bookLoans);
		 
	    }
	 
	 public void writeReturn(BookLoans bookLoans) {
		 bookLoansDao.delete(bookLoans);
	    }
	 
	 public boolean updateCopiesCheckout(BookLoans bookLoans) {
		 
		 boolean check = false;
		 BookCopiesComposite bookComposite = new BookCopiesComposite();
		 bookComposite.setBook(bookLoans.getBlCompKey().getBook());
		 bookComposite.setBranch(bookLoans.getBlCompKey().getBranch());
		 
		 Optional<BookCopies> copy = bookCopiesDao.findById(bookComposite);
		 
		 Integer noOfCopies = copy.get().getNoOfCopies();
		 
		 if (noOfCopies> 0) {
			 BookCopies bookCopy = new BookCopies(bookComposite,noOfCopies - 1);
			 bookCopiesDao.save(bookCopy);
			 return check = true; 
		 }
		 else {
			 return check;
		 }
	 }
	 
	 public void updateCopiesReturn(BookLoans bookLoans) {
		 
		 BookCopiesComposite bookComposite = new BookCopiesComposite();
		 bookComposite.setBook(bookLoans.getBlCompKey().getBook());
		 bookComposite.setBranch(bookLoans.getBlCompKey().getBranch());
		 
		 Optional<BookCopies> copy = bookCopiesDao.findById(bookComposite);
		 Integer noOfCopies = copy.get().getNoOfCopies();
		 
			 BookCopies bookCopy = new BookCopies(bookComposite,noOfCopies +1 );
			 bookCopiesDao.save(bookCopy);
	 }

	 
	 public Optional<Borrower> readBorrowerbyId(Integer cardNo) {
		 return borrowerDao.findById(cardNo);
	 }
	 
	 public Optional<LibraryBranch> readBranchbyId(Integer branchId) {
		 return libraryBranchDao.findById(branchId);
	 }
	 
	 public Optional<Book> readBookbyId(Integer bookId) {
		 return bookDao.findById(bookId);
	 }
	 
	 public Optional<Publisher> readPublisherbyId(Integer publisherId) {
		 return publisherDao.findById(publisherId);
	 }
	 
	 public Optional<Author> readAuthorbyId(Integer authorId) {
		 return authorDao.findById(authorId);
	 }
	 
	 
	 public BookLoans readEmbedded(BookLoans bookLoans) {
		 
		 Optional<Borrower> borr = readBorrowerbyId(bookLoans.getBlCompKey().getBorrower().getCardNo());
		 Borrower borrower = new Borrower(bookLoans.getBlCompKey().getBorrower().getCardNo(), borr.get().getName(), borr.get().getAddress(), borr.get().getPhone());
		 
		 
		 Optional<LibraryBranch> bh = readBranchbyId(bookLoans.getBlCompKey().getBranch().getBranchId());
		 LibraryBranch lb = new LibraryBranch(bookLoans.getBlCompKey().getBranch().getBranchId(), bh.get().getBranchName(), bh.get().getBranchAddress());
		 
		 
		 Optional<Book> bk = readBookbyId(bookLoans.getBlCompKey().getBook().getBookId());
		 Book book = new Book(bookLoans.getBlCompKey().getBook().getBookId(), bk.get().getTitle(), bk.get().getAuthor(), bk.get().getPublisher());
				 
		 Optional <Author> au = readAuthorbyId(book.getAuthor().getAuthorId());
		 Author aut = new Author(book.getAuthor().getAuthorId(), au.get().getAuthorName());
		 
		 Optional<Publisher> pub = readPublisherbyId(book.getPublisher().getPublisherId());
		 Publisher pubs = new Publisher (book.getPublisher().getPublisherId(), pub.get().getPublisherName(), pub.get().getPublisherAddress(), pub.get().getPublisherPhone());
		 
		 book.setAuthor(aut);
		 book.setPublisher(pubs);
		 
		 
		 BookLoansCompositeKey blCompKey =  new BookLoansCompositeKey(borrower, lb, book);
		 bookLoans.setBlCompKey(blCompKey);
		 
		return bookLoans;
	 }
	 
}
