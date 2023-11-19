# DuAnThucTap
- App được thiết kế và tạo dựng bởi 1 thành viên, có tham khảo thiết kế giao giện theo nhiều nguồn khác nhau.
- App được làm gấp rút do nhóm giải tán.
- Gmail: phucleaaee@gmail.com
  
# I. BÀI GIỚI THIỆU VỀ APP PL SHOPS
- PL SHOPS là 1 ứng dụng mua sắm điện thoại và laptop được phân quyền sử dụng user và admin.

- PL SHOPS được xây dựng trên Android Studio băng ngôn ngữ java, dữ liệu của app được lưu trên 000Webhost.com bằng phpMyAdmin.

- Các chức năng của người dùng :
    + Thực hiện đầy đủ các thao tác đăng nhập, đăng ký, quản lý thông tin cá nhân.
    + Mua, đặt hàng, thanh toán, chat bot để biết thêm chi tiết sản phẩm(chưa hoàn thiện).
    + Thông tin chi tiết sản phẩm.
    + Chương trình ưu đãi, blog giới thiệu các sản phẩm(chưa hoàn thiện).
      
- Các chức năng của quản trị viên :
    + Thực hiện đầy đủ các thao tác đăng nhập, 
    +  Quản lý tài khoản.
    +  Quản lý sản phẩm và các danh mục sản phẩm.
    +  Quản lý đơn hàng(chưa hoàn thiện)
    + Báo cáo thống kê doanh thu, doanh số theo thời gian(chưa hoàn thiện).
      
# II. PHÂN TÍCH CHI TIẾT CÁC CHỨC NĂNG CHÍNH
1. Đăng nhập: Người dùng và người quản trị đều cần có chức năng đăng nhập để truy cập vào ứng dụng.   

2. Đăng ký: Người dùng có thể đăng ký tài khoản mới để sử dụng ứng dụng.

3. Quản lý thông tin cá nhân: Người dùng có thể thay đổi thông tin cá nhân của mình
   (tên, địa chỉ, số điện thoại, email, mật khẩu, v.v.).

4. Mua hàng: Người dùng có thể xem danh sách sản phẩm, chọn mua và thêm vào giỏ hàng.

5. Đặt hàng: Người dùng có thể xem giỏ hàng, chỉnh sửa số lượng và đặt hàng.

6. Thanh toán: Người dùng có thể chọn phương thức thanh toán và thực hiện thanh toán cho đơn hàng.

7. Chat bot: Người dùng có thể trò chuyện với chat bot để biết thêm chi tiết về sản phẩm.

8. Xem thông tin chi tiết sản phẩm: Người dùng có thể xem thông tin chi tiết về từng sản phẩm
   (mô tả, giá, ảnh, v.v.).

9. Xem chương trình ưu đãi: Người dùng có thể xem các chương trình ưu đãi đang diễn ra
   và áp dụng cho đơn hàng của mình.

10. Xem blog giới thiệu sản phẩm: Người dùng có thể xem các bài viết blog giới thiệu về các sản phẩm.

11. Quản lý tài khoản: Người quản trị có thể quản lý tài khoản người dùng (thêm, sửa, xóa).

12. Phân quyền sử dụng: Người quản trị có thể phân quyền sử dụng cho các tài khoản người dùng.

13. Quản lý sản phẩm: Người quản trị có thể quản lý danh sách sản phẩm (thêm, sửa, xóa).

14. Quản lý danh mục sản phẩm: Người quản trị có thể quản lý danh mục sản phẩm (thêm, sửa, xóa).

15. Quản lý đơn hàng: Người quản trị có thể xem danh sách đơn hàng, xem chi tiết từng đơn hàng,
    cập nhật trạng thái đơn hàng.

16. Báo cáo thống kê doanh thu: Người quản trị có thể xem báo cáo thống kê doanh thu theo thời gian
    (ngày, tuần, tháng, quý, năm).
# III PHÂN TÍCH VÀ THIẾT KẾ CẤU TRÚC DỮ LIỆU
 1. Ngôn ngữ lập trình và công nghệ sử dụng:
    - Ứng dụng di động: Android Java.
    - Cơ sở dữ liệu: phpMyAdmin.
 
 2. Thiết kế cơ sở dữ liệu:
     A. Bảng "Users" (Người dùng):
        - userId (khóa chính)
        - name
        - address
        - phone
        - email
        - username
        - password (Nên lưu mật khẩu đã được băm thay vì lưu trực tiếp mật khẩu)
        - roleId (khóa ngoại đến bảng "Roles")
    
     B. Bảng "Products" (Sản phẩm):
        - productId (khóa chính)
        - name
        - description
        - imageUrl
        - price
        - categoryId (khóa ngoại đến bảng "Categories")
        - quantity
    
     C. Bảng "Categories" (Danh mục sản phẩm):
        - categoryId (khóa chính)
        - name
        - imageUrl
    
     D. Bảng "Orders" (Đơn hàng):
        - orderId (khóa chính)
        - userId (khóa ngoại đến bảng "Users")
        - orderDate
        - totalAmount
        - statusId (khóa ngoại đến bảng "OrderStatus")
    
     E. Bảng "Roles" (Vai trò):
        - roleId (khóa chính)
        - role (ví dụ: admin, user, guest)
    
     F. Bảng "OrderStatus" (Trạng thái đơn hàng):
        - statusId (khóa chính)
        - status (ví dụ: pending, processing, shipped, completed, cancelled)
    
# IV. HINH ẢNH SẢN PHẨM
 - màn hình chào ![image](https://github.com/phuclhdrake/DuAnThucTap/assets/105592184/8d398e93-c47c-40ac-8d1f-37ed19fc04dc)
 - màn Login ![image](https://github.com/phuclhdrake/DuAnThucTap/assets/105592184/48a05ebd-e74b-46fc-8a11-755928432be8)
 - màn SignUp ![image](https://github.com/phuclhdrake/DuAnThucTap/assets/105592184/929437cb-1a1c-4729-8c64-2abc83416425)
 - màn Home ![image](https://github.com/phuclhdrake/DuAnThucTap/assets/105592184/572dce94-658a-4cee-99d3-a0c9ad34612a) và ![image](https://github.com/phuclhdrake/DuAnThucTap/assets/105592184/636334b0-8fb9-4a00-bdce-c2d065cfa8bf)
 - màn chi tiết sản phẩm ![image](https://github.com/phuclhdrake/DuAnThucTap/assets/105592184/7e7a61fa-f769-45f7-b99d-b894e8a7ea2a)
 - màn oder(đặt hàng) ![image](https://github.com/phuclhdrake/DuAnThucTap/assets/105592184/2f6ab8f7-c3a8-4295-89f3-981eb4bd8047)
 - màn Cart ![image](https://github.com/phuclhdrake/DuAnThucTap/assets/105592184/09510350-1395-4c6f-881c-b845b3712c77)
 - màn Account ![image](https://github.com/phuclhdrake/DuAnThucTap/assets/105592184/a862089a-fdda-4236-98fa-898cd4008df3)
 - màn Account của admin ![image](https://github.com/phuclhdrake/DuAnThucTap/assets/105592184/9d238dd4-47dc-4247-8c5d-c68862d7abec)
 - màn Quản lý sản phẩm ![image](https://github.com/phuclhdrake/DuAnThucTap/assets/105592184/d3375420-adeb-4ff4-949d-27da25523e87)
 - màn Sử sản phẩm ![image](https://github.com/phuclhdrake/DuAnThucTap/assets/105592184/4491bed1-7bef-46dc-9f8f-c348446aa2df)
 - màn Thêm sản phẩm ![image](https://github.com/phuclhdrake/DuAnThucTap/assets/105592184/394cc9b8-7f04-41b3-b09f-7956163e2d38)

# V. TỔNG KẾT
  - Tiếp tục xây dựng và phát triển ứng dụng hoàn thiện hơn, cải thiện hiệu xuất của ứng dụng.
  - Hoàn thiện tất cả các chức năng.
  - Thêm các tính năng mới hữu ích với người dùng và quản trị.
       
