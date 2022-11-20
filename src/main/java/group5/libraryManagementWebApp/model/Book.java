package group5.libraryManagementWebApp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data // toString, Getter, Setter...
@NoArgsConstructor // Constructor with no parameters
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String authorName;
	private String category;
	private Integer publishedYear;
	private Integer available;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "book")
	List<BookOnLoan> loansHaveThisBook;

	public Book(String title, String authorName, String category, Integer publishedYear, Integer available) {
		super();
		this.title = title;
		this.authorName = authorName;
		this.category = category;
		this.publishedYear = publishedYear;
		this.available = available;
	}

}
