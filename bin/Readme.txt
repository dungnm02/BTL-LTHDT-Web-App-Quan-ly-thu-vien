Kiến trúc MVC:
------------ Model -> ??? -> 1 người. (Minh) 	
	Nhiệm vụ:
		=> Xây dựng các Entity(Giống 1 Class trong Java - Được kết nối với 1 CSDL). Làm sao để ae làm Controller thao tác, xây dựng các method cho nó
		=> Tôi nghĩ hiện đang có 2 Entity: Sách và Đơn mượn
			Sách -> Id, Tên sách, Tên tác giả, Thể loại, Năm phát hành và Ảnh (nếu có thể)
			Đơn mượn -> Id, Sách(???), Tên người mượn, Sđt, Email, Địa chỉ, Trạng thái.
 		=> Tạo dữ liệu mẫu.

------------ View -> Thymeleaf + HTML/CSS -> 1 Người. (Ngọc) 

	Nhiệm vụ:
		=> Hiển thị các dữ liệu
		=> Tạo form để người dùng gửi thông tin,.....

------------ Controller -> ??? -> 2 người (Dũng, Cường)
	
	Nhiệm vụ:
		=> Xử lý các tác vụ:
			- Đăng nhập
			- Tìm kiếm, Sắp xếp thông tin (???)
			- Thêm/ Xóa/ Cập nhật các thông tin về Sách/ Đơn mượn trên CSDL
			
