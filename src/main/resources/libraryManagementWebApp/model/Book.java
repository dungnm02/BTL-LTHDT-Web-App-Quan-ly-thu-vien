package group5.libraryManagementWebApp.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="books")
@Data //toString, Getter, Setter...
@NoArgsConstructor //Constructor with no parameters
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	private String title;
	private String authorName;
	private String category;
	private Integer publishedYear;
	private Integer available;
		
//    @ManyToMany(mappedBy = "books")
//    private List<Loan> loans;
    
	public Book(String title, String authorName, String category, Integer publishedYear, Integer available) {
		super();
		this.title = title;
		this.authorName = authorName;
		this.category = category;
		this.publishedYear = publishedYear;
		this.available = available;
	}	
	
	
}
