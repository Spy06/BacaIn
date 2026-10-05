# BacaIn

## Nama & NIM
Farhan Hakim (H1D024064)

## Stack Teknologi & Library (Technical Stack)

Aplikasi ini menggunakan teknologi terbaru yang direkomendasikan oleh Google untuk pengembangan Android:

*   Bahasa Pemrograman: [Kotlin](https://kotlinlang.org/) - Bahasa utama yang digunakan secara keseluruhan.
*   UI Framework: [Jetpack Compose](https://developer.android.com/jetpack/compose) - Toolkit modern deklaratif untuk membangun UI Android (termasuk Material 3).
*   Arsitektur: [MVVM (Model-View-ViewModel)](https://developer.android.com/topic/architecture) - Memisahkan logika bisnis dari UI.
*   Networking: 
    *   [Retrofit](https://square.github.io/retrofit/) - Type-safe HTTP client untuk memanggil REST API.
    *   [OkHttp Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor) - Untuk memantau dan melakukan *debug* pada HTTP request/response.
    *   [Gson](https://github.com/google/gson) - Konversi format JSON ke Kotlin data class (Serialization/Deserialization).
*   Asynchrony & Reactive: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html) - Menangani operasi asinkron dan aliran data reaktif secara aman dan efisien.
*   Navigasi: [Navigation Compose](https://developer.android.com/jetpack/compose/navigation) - Menangani perpindahan antar *screen* (layar) dalam satu activity (Single Activity Architecture).

## Arsitektur Aplikasi (Architecture)

Aplikasi ini menerapkan pola MVVM dipadukan dengan UDF:
1.  View (UI Layer): Diimplementasikan menggunakan Jetpack Compose (contoh: `HomeScreen.kt`, `DetailScreen.kt`). View hanya bertugas untuk *render* tampilan berdasarkan `UiState` dan mengirimkan aksi pengguna (*user intent*) ke ViewModel.
2.  ViewModel: Bertindak sebagai jembatan yang menyimpan dan memanipulasi UI State (contoh: `HomeViewModel.kt`). State direpresentasikan menggunakan `StateFlow` (seperti `AnimeListUiState.Loading`, `Success`, atau `Error`).
3.  Model (Data Layer): Terdiri dari integrasi API via Retrofit yang mengambil data anime (misal menggunakan ID seperti `malId`).

## Fitur Teknis Utama (Key Technical Features)

1.  Infinite Scrolling Pagination:
    Diimplementasikan pada `HomeScreen` dengan memanfaatkan `snapshotFlow` untuk mendengarkan perubahan state dari `LazyListState`. Ketika pengguna melakukan scroll hingga 5 item terakhir dari daftar yang tampil, aplikasi akan secara otomatis meminta data halaman berikutnya ke ViewModel (`loadNextPage()`).
    
2.  State Management berbasis Sealed Class:
    UI State dikelola menggunakan *sealed class* (contoh: `AnimeListUiState`), yang menjamin UI menangani semua kemungkinan kondisi: *Loading*, *Success*, dan *Error*.

3.  Animasi Transisi Halus (Compose Animation):
    Menggunakan `AnimatedVisibility` dengan kombinasi `fadeIn` dan `slideInVertically` pada setiap item daftar anime untuk memberikan pengalaman pengguna yang lebih hidup (fluid UX).

4.  Custom Material 3 Theming:
    Warna, tipografi, dan *shape* didefinisikan secara kustom (contoh menggunakan `AnimeNavy`, `AnimeCyan`, `AnimeViolet`) memanfaatkan material3 untuk identitas visual yang khas.

## Struktur Proyek

```
app/src/main/java/com/responsi/bacain/
│
├── ui/
│   ├── components/    # Reusable UI components (AnimeCard, dll)
│   ├── screen/        # Layar utama aplikasi (HomeScreen, DetailScreen)
│   ├── theme/         # Konfigurasi Compose Theme (Color, Typography, Theme)
│   └── viewmodel/     # ViewModel pengelola state dan logika bisnis
│
└── ...
```

## Cara Menjalankan Proyek (Setup & Installation)

1.  Clone repositori ini:
    ```bash
    git clone https://github.com/username/BacaIn.git
    ```
2.  Buka proyek menggunakan Android Studio (Disarankan versi terbaru seperti *Android Studio Iguana/Jellyfish* atau yang mendukung AGP 8+ dan Compose).
3.  Biarkan Gradle melakukan sinkronisasi (Sync Project with Gradle Files).
4.  Jalankan aplikasi (Run `app`) pada Emulator atau Perangkat Fisik (Minimum SDK 28).

Screenshoot:
<img width="1080" height="2424" alt="Screenshot_20261006_053157" src="https://github.com/user-attachments/assets/eb230418-1883-4d55-9494-61b4d779522c" />
<img width="1080" height="2424" alt="Screenshot_20261006_053210" src="https://github.com/user-attachments/assets/6adaa155-2c5d-4417-97ed-6dc6663e49c3" />
<img width="1080" height="2424" alt="Screenshot_20261006_053215" src="https://github.com/user-attachments/assets/8bb1df16-6c52-4518-b586-766d936dc6e9" />
<img width="1080" height="2424" alt="Screenshot_20261006_053226" src="https://github.com/user-attachments/assets/554d9846-ba1f-4637-9711-5682d66cbfb5" />


