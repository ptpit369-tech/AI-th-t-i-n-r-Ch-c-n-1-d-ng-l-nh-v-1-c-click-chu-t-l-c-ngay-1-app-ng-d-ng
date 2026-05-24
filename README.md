# 🇻🇳 Khám Phá Việt Nam

> Ứng dụng Android giới thiệu các địa điểm du lịch nổi tiếng và danh lam thắng cảnh tại Việt Nam — được tạo ra với sức mạnh của **Google Gemini AI** và **Jetpack Compose**.

---

## ✨ Tính năng nổi bật

- 🗺️ **Khám phá 10+ điểm đến** trải dài 3 miền Bắc – Trung – Nam
- 🔍 **Lọc theo vùng miền và loại hình** du lịch (Di sản, Phố cổ, Núi, Biển, Thiên nhiên, Ẩm thực, Lịch sử)
- 📋 **Chi tiết từng địa điểm**: mô tả, địa chỉ, thời điểm lý tưởng, giá vé, điểm nổi bật, gợi ý ẩm thực
- ⭐ **Hệ thống đánh giá & bình luận** — lưu trực tiếp trên thiết bị
- 🤖 **Tích hợp Gemini AI** để hỗ trợ thông tin du lịch thông minh
- 🎨 **Giao diện Jetpack Compose** hiện đại, mượt mà, hỗ trợ Dark Mode

---

## 📍 Các địa điểm trong ứng dụng

| Địa điểm | Tỉnh / Thành | Vùng | Loại hình |
|---|---|---|---|
| Vịnh Hạ Long | Quảng Ninh | Miền Bắc | Di sản |
| Phố Cổ Hội An | Quảng Nam | Miền Trung | Phố cổ |
| Sa Pa | Lào Cai | Miền Bắc | Núi |
| Mũi Né | Bình Thuận | Miền Nam | Biển |
| Phú Quốc | Kiên Giang | Miền Nam | Biển |
| Đà Lạt | Lâm Đồng | Miền Nam | Thiên nhiên |
| Cố Đô Huế | Thừa Thiên Huế | Miền Trung | Lịch sử |
| Ninh Bình | Ninh Bình | Miền Bắc | Thiên nhiên |
| Cần Thơ | Cần Thơ | Miền Nam | Ẩm thực |
| Côn Đảo | Bà Rịa – Vũng Tàu | Miền Nam | Biển |

---

## 🛠️ Công nghệ sử dụng

- **Ngôn ngữ**: Kotlin
- **UI Framework**: Jetpack Compose + Material 3
- **AI**: Google Gemini API (`MAJOR_CAPABILITY_SERVER_SIDE_GEMINI_API`)
- **Build System**: Gradle (Kotlin DSL)
- **Min SDK**: Android (xem `app/build.gradle.kts`)
- **Lưu trữ**: SharedPreferences (lưu đánh giá người dùng cục bộ)

---

## 🚀 Cài đặt & Chạy thử

### Yêu cầu

- [Android Studio](https://developer.android.com/studio) (phiên bản mới nhất)
- Tài khoản Google AI Studio để lấy Gemini API Key

### Các bước thực hiện

1. **Clone repo về máy**
   ```bash
   git clone https://github.com/<your-username>/kham-pha-viet-nam.git
   cd kham-pha-viet-nam
   ```

2. **Mở bằng Android Studio**
   - Chọn **Open** → trỏ vào thư mục vừa clone
   - Cho phép Android Studio tự xử lý các dependency

3. **Cấu hình API Key**
   - Tạo file `.env` ở thư mục gốc của project (xem `.env.example` để tham khảo)
   - Thêm dòng sau vào file `.env`:
     ```
     GEMINI_API_KEY=your_api_key_here
     ```
   - Lấy API Key miễn phí tại [Google AI Studio](https://aistudio.google.com)

4. **Sửa cấu hình build**
   - Mở file `app/build.gradle.kts`
   - Xóa dòng sau (chỉ cần thiết cho bản build từ AI Studio):
     ```
     signingConfig = signingConfigs.getByName("debugConfig")
     ```

5. **Chạy ứng dụng**
   - Kết nối thiết bị Android hoặc khởi động máy ảo (emulator)
   - Nhấn **Run ▶** trong Android Studio

---

## 📁 Cấu trúc thư mục

```
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/
│   │   │   │   ├── MainActivity.kt       # Màn hình chính & toàn bộ UI
│   │   │   │   ├── Destination.kt        # Data model & danh sách địa điểm
│   │   │   │   └── ui/theme/             # Theme, màu sắc, typography
│   │   │   └── res/                      # Resources (icon, layout values...)
│   │   ├── test/                         # Unit tests
│   │   └── androidTest/                  # Instrumented tests
│   └── build.gradle.kts
├── .env.example                          # Mẫu cấu hình API Key
├── .gitignore
└── README.md
```

---

## 🤝 Đóng góp

Mọi đóng góp đều được chào đón! Vui lòng mở **Issue** hoặc gửi **Pull Request** nếu bạn muốn cải thiện ứng dụng.

---

## 📄 Giấy phép

Dự án này được phát triển qua [Google AI Studio](https://aistudio.google.com/apps/992f7d19-071a-46a7-85fb-09e689da8c91) và được chia sẻ cho mục đích học tập, tham khảo.
