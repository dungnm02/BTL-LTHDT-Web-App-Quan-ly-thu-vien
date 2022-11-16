package group5.libraryManagementWebApp.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="loans")
@Data //toString, Getter, Setter...
@NoArgsConstructor //Constructor with no parameters
public class Loan {
	private enum Status {
		NEW,
		ACCEPTED,
		CANCELED,
		INPROGRESS,
		COMPLETED,
		EXPIRED
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String loanerName;
	private String loanerTel;
	private String loanerEmail;
	
    @Enumerated(EnumType.STRING)
	private Status status;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date borrowDate; //Khởi tạo khi status -> INPROGRESS
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date returnDate; //Khởi tạo khi status -> COMPLETED
    
    //Quan hệ n-n giữa Loan và Book, một đầu sách xuất hiện trong nhiều đơn mượn, một đơn mượn có nhiều quyển sách
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name= "loans_books", 
				joinColumns = @JoinColumn(name = "loan_id"),
				inverseJoinColumns = @JoinColumn(name = "book_id"))
	private List<Book> books; 	
	
	public Loan(String loanerName, String loanerTel, String loanerEmail, List<Book> books) {
		super();
		this.loanerName = loanerName;
		this.loanerTel = loanerTel;
		this.loanerEmail = loanerEmail;
		this.status = Status.NEW;
		this.books = books;
	}
	
}
