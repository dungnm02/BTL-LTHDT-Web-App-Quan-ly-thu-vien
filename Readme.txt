Setup CSDL: Microsoft SQL Server Management Studio 18:
  
  - Tạo thông tin đăng nhập:
    - Security/Logins -> New Login:
      -> Login name = demo -> SQL Authentication -> password = demotest -> OK
  
  - Tạo Database tên Library
    - Security/Users -> New User:
      -> SQL user with login -> User name = demo, Login name = demo -> OK
    - Security/Users/demo -> Properties
      -> Owned Schemas -> Chọn tất cả trừ db_denydatareader, db_denydatawriter, guest
      -> Memberships   -> Chọn tất cả trừ db_denydatareader, db_denydatawriter
  
  ->Có thể bị bắt đổi mật khẩu: -> Đổi ở Security/Logins -> Cập nhật:
    Vào /BTL-LTHDT-Web-App-Quan-ly-thu-vien-main/src/main/resources/application.properties:
    spring.datasource.username=demo
    spring.datasource.password=password mới
  
Setup Mail: Gmail
  https://www.geeksforgeeks.org/spring-boot-sending-email-via-smtp/
  - Tài khoản Google: https://myaccount.google.com/
    -> 2-Step Verification -> On
    -> App Password -> Select App = (Custom Name)LMS, Device = Window Computer -> Lấy được password
    -> Vào /BTL-LTHDT-Web-App-Quan-ly-thu-vien-main/src/main/resources/application.properties:
    spring.mail.username= tên email không có hậu tố.
    spring.mail.password= password ở trên.
    
  ->Có thể bị Google chặn ngẫu nhiên do nghi ngờ là bot?
