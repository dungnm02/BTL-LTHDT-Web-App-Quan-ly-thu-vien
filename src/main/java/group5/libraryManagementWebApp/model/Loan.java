package group5.libraryManagementWebApp.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "loans")
@Data // toString, Getter, Setter...
@NoArgsConstructor // Constructor with no parameters
public class Loan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Nationalized
	@Column(nullable = false)
	private String loanerName;
	@Column(nullable = false)
	private String loanerTel;
	@Column(nullable = false)
	private String loanerEmail;
	@Column(nullable = false)
	private String status;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date borrowDate; 
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date returnDate;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "loan")
	List<BookOnLoan> booksOnLoan;

}
