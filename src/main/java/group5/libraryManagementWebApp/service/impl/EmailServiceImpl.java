package group5.libraryManagementWebApp.service.impl;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.stereotype.Service;

import group5.libraryManagementWebApp.email.EmailDetails;
import group5.libraryManagementWebApp.model.Book;
import group5.libraryManagementWebApp.model.BookOnLoan;
import group5.libraryManagementWebApp.model.Loan;
import group5.libraryManagementWebApp.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendMail(EmailDetails emailDetails) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom("demobtl.lthdt@gmail.com");
		email.setSentDate(Date.valueOf(LocalDate.now()));
		email.setTo(emailDetails.getRecipient());
		email.setSubject(emailDetails.getSubject());
		email.setText(emailDetails.getBody());

		javaMailSender.send(email);
	}

	@Override
	public EmailDetails createNewEmail(Loan loan) {
		EmailDetails emailDetails = new EmailDetails();
		String body = "";
		body += "Xin chào, " + loan.getLoanerName() + "\n";
		body += "Thư viện đã nhận được đơn mượn của bạn. Xin vui lòng đợi phản hồi từ chúng tôi" + "\n";
		body += getLoanDetails(loan);
		emailDetails.setSubject("Thư viện X: Tạo đơn mượn thành công");
		emailDetails.setRecipient(loan.getLoanerEmail());
		emailDetails.setBody(body);
		return emailDetails;
	}

	@Override
	public EmailDetails createAcceptEmail(Loan loan) {
		EmailDetails emailDetails = new EmailDetails();
		String body = "";
		body += "Xin chào, " + loan.getLoanerName() + "\n";
		body += "Thư viện đã xác nhận đơn mượn của bạn và sách bạn mượn đã sẵn sàng để được lấy." + "\n";
		body += getLoanDetails(loan);
		emailDetails.setSubject("Thư viện X: Xác nhận đơn mượn");
		emailDetails.setRecipient(loan.getLoanerEmail());
		emailDetails.setBody(body);
		return emailDetails;
	}

	@Override
	public EmailDetails createCancelEmail(Loan loan) {
		EmailDetails emailDetails = new EmailDetails();
		String body = "";
		body += "Xin chào, " + loan.getLoanerName() + "\n";
		body += "Thư viện đã hủy đơn mượn của bạn, xin liên hệ với chúng tôi để biết thêm thông tin chi tiết" + "\n";
		body += getLoanDetails(loan);
		emailDetails.setSubject("Thư viện X: Hủy đơn mượn");
		emailDetails.setRecipient(loan.getLoanerEmail());
		emailDetails.setBody(body);
		return emailDetails;
	}

	@Override
	public EmailDetails createInProgressEmail(Loan loan) {
		EmailDetails emailDetails = new EmailDetails();
		String body = "";
		body += "Xin chào, " + loan.getLoanerName() + "\n";
		body += "Bạn đã mượn sách thành công" + "\n";
		body += getLoanDetails(loan);
		emailDetails.setSubject("Thư viện X: Mượn sách thành công");
		emailDetails.setRecipient(loan.getLoanerEmail());
		emailDetails.setBody(body);
		return emailDetails;
	}

	@Override
	public EmailDetails createUpdateExpireDateEmail(Loan loan) {
		EmailDetails emailDetails = new EmailDetails();
		String body = "";
		body += "Xin chào, " + loan.getLoanerName() + "\n";
		body += "Bạn đã gia hạn đơn sách thành công" + "\n";
		body += getLoanDetails(loan);
		emailDetails.setSubject("Thư viện X: Gia hạn thành công");
		emailDetails.setRecipient(loan.getLoanerEmail());
		emailDetails.setBody(body);
		return emailDetails;
	}

	@Override
	public EmailDetails createCompleteEmail(Loan loan) {
		EmailDetails emailDetails = new EmailDetails();
		String body = "";
		body += "Xin chào, " + loan.getLoanerName() + "\n";
		body += "Bạn đã trả sách thành công" + "\n";
		body += getLoanDetails(loan);
		emailDetails.setSubject("Thư viện X: Trả sách thành công");
		emailDetails.setRecipient(loan.getLoanerEmail());
		emailDetails.setBody(body);
		return emailDetails;
	}

	@Override
	public EmailDetails createExpireEmail(Loan loan) {
		EmailDetails emailDetails = new EmailDetails();
		String body = "";
		body += "Xin chào, " + loan.getLoanerName() + "\n";
		body += "Đơn mượn của bạn đã quá hạn, hãy chuẩn bị nộp phạt" + "\n";
		body += getLoanDetails(loan);
		emailDetails.setSubject("Thư viện X: Đơn mượn quá hạn");
		emailDetails.setRecipient(loan.getLoanerEmail());
		emailDetails.setBody(body);
		return emailDetails;
	}

	@Override
	public String getLoanDetails(Loan loan) {
		String loanDetails = "";
		loanDetails += "-------------------------------------------------------------------------------" + "\n";
		loanDetails += "Thông tin đơn mượn" + "\n";
		loanDetails += "Mã đơn mượn: " + loan.getId() + "\n";
		loanDetails += "Họ và tên: " + loan.getLoanerName() + "\n";
		loanDetails += "Email: " + loan.getLoanerEmail() + "\n";
		loanDetails += "Số điện thoại: " + loan.getLoanerTel() + "\n";
		for (BookOnLoan bookOnLoan : loan.getBooksOnLoan()) {
			Book book = bookOnLoan.getBook();
			loanDetails += "Tên sách: " + book.getTitle() + " Tên tác giả: " + book.getAuthorName() + " Năm xuất bản: "
					+ book.getPublishedYear() + " Số lượng: " + bookOnLoan.getQuantity() + "\n";
		}
		if (loan.getBorrowDate() != null) {
			loanDetails += "Ngày mượn: " + loan.getBorrowDate() + "\n";
		}
		if (loan.getExpireDate() != null) {
			loanDetails += "Ngày hết hạn: " + loan.getExpireDate() + "\n";
		}
		if (loan.getReturnDate() != null) {
			loanDetails += "Ngày trả: " + loan.getReturnDate() + "\n";
		}
		loanDetails += "-------------------------------------------------------------------------------" + "\n";
		return loanDetails;
	}

}
