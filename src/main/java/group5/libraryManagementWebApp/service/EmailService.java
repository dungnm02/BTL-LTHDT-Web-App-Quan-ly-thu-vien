package group5.libraryManagementWebApp.service;

import group5.libraryManagementWebApp.email.EmailDetails;
import group5.libraryManagementWebApp.model.Loan;

public interface EmailService {
	void sendMail(EmailDetails email);

	public EmailDetails createNewEmail(Loan loan);

	public EmailDetails createAcceptEmail(Loan loan);

	public EmailDetails createCancelEmail(Loan loan);

	public EmailDetails createInProgressEmail(Loan loan);

	public EmailDetails createUpdateExpireDateEmail(Loan loan);

	public EmailDetails createCompleteEmail(Loan loan);

	public EmailDetails createExpireEmail(Loan loan);

	public String getLoanDetails(Loan loan);
}
