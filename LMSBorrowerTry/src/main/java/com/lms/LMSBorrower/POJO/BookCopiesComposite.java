package com.lms.LMSBorrower.POJO;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Embeddable
public class BookCopiesComposite implements Serializable {
	
	private static final long serialVersionUID = -4686491596600192203L;

	@ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "bookId")
    private Book book;
    
    @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "branchId")
    private LibraryBranch branch;
    
    public BookCopiesComposite() {
    }

	public BookCopiesComposite(Book book, LibraryBranch branch) {
		super();
		this.book = book;
		this.branch = branch;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LibraryBranch getBranch() {
		return branch;
	}

	public void setBranch(LibraryBranch branch) {
		this.branch = branch;
	}

	@Override
	public String toString() {
		return "BookCopiesComposite [book=" + book + ", branch=" + branch + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((branch == null) ? 0 : branch.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookCopiesComposite other = (BookCopiesComposite) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (branch == null) {
			if (other.branch != null)
				return false;
		} else if (!branch.equals(other.branch))
			return false;
		return true;
	}


}