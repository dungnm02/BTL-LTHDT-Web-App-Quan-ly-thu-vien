package group5.libraryManagementWebApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books_loans")
@Data // toString, Getter, Setter...
@NoArgsConstructor // Constructor with no parameters
public class BookOnLoan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "loanId", referencedColumnName = "id")
	private Loan loan;

	@ManyToOne
	@JoinColumn(name = "bookId", referencedColumnName = "id")
	private Book book;

	private Integer quantity;

}
