package group5.libraryManagementWebApp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

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
	@Nationalized 
	@Column(nullable = false)
	private String title;
	@Nationalized 
	@Column(nullable = false)
	private String authorName;
	@Nationalized 
	@Column(nullable = false)
	private String category;
	@Column(nullable = false)
	private Integer publishedYear;
	@Column(nullable = false)
	private Integer available;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "book")
	List<BookOnLoan> loansHaveThisBook;
}
