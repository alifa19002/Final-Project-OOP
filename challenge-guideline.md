# Challenge Guidelines

## Project UAS OOP – Sudoku

**A. Deskripsi masalah**
- Nama main file adalah ‘Sudoku’ (Contoh command: java Sudoku).
- Ketika dijalankan, program akan membentuk (9 x 9) grid ubin dengan (3 x 3) sub-grids.
- Setiap ubin berisi angka 1 - 9, kemudian ada beberapa ubin yang ditampilkan berisi string kosong (mask).
- Secara default akan menampilkan 4x9 (36) ubin yang telah berisi angka (tidak dimasking). 
- Posisi ubin yang dimasking bersifat random (posisi berbeda setiap program dijalankan).
- Dengan menggunakan args (manfaatkan argument String[] args pada method main), tambahkan argumen 'difficulty' sebagai tingkat kesulitan (banyaknya tile yang sudah berisi angka). Dengan rincian:
    - ‘easy’ : banyak tile yang berisi angka ada 4x9 buah, (command: java Sudoku easy).
    - ‘medium’ : banyak tile yang berisi angka ada 2x9 buah, ( command: java Sudoku medium).
    - ‘hard’ : banyak tile yang berisi angka ada 1x9 buah, (command: java Sudoku hard).
- Setiap kali program berjalan akan menampilkan ubin-ubin dengan isi nomor unik (tidak ada angka yang sama pada 1 baris, 1 kolom, dan 1 sub-grids) yang teracak dari angka 1 sampai 9, kemudian terdapat beberapa ubin yang dimasking tergantung tingkat kesulitan yang dipilih.

**B. Setelah app berjalan**
- Tampilkan grid ubin dengan besar 9 x 9 yang terdiri dari 3 x 3 sub-grids dimana ubin adalah komponen drawing board.
- Acak angka di dalam ubin dengan syarat unik (tidak ada angka yang sama pada 1 baris, 1 kolom, dan 1 sub-grids).
- Lakukan masking terhadap ubin tertentu (angka disembunyikan dan dijadikan text field).
- Ubin yang tidak dimasking bersifat read-only (angka didalamnya tidak bisa diubah).
- Cek apakah urutan tersebut dapat dipecahkan dengan aturan: Setiap angka yang didahului oleh angka yang lebih besar dianggap sebagai inversi, jumlah inversi dari puzzle harus genap.
- Buat listener untuk memproses input. Setiap inputan user akan dicek:
    - Koordinat x dan y dimana inputan tersebut berada.
    - Apakah nilai yang dimasukan pada ubin sesuai dengan jawaban, jika sesuai maka ubah warna tulisan menjadi hijau, jika tidak sesuai maka ubah menjadi warna merah.
    - Highlight ubin yang sudah berisi angka, jika dalam 1 baris, 1 kolom, atau 1 sub-grids berisi angka yang sama dengan inputan user.
- Buat button “Reset/Restart” untuk mengacak ulang grid dan memulai game baru.

**C. Batasan lainnya**
- Gunakan prinsip modularitas pada code, misalnya initGame(), getPuzzle(), dll.
- Tampilan dibuat semenarik mungkin, misalnya warna, font, layout, dll.
- Method yang digunakan dibebaskan asalkan mencakupi fungsi yang diminta dari poin B.
- Jika ada batasan atau fitur tambahan lain, tolong deskripsikan kepada asprak (akan menjadi nilai lebih).

**D. Challenge ideas (optional)**
- Buat menu bar seperti :  "File" ("Restart", "Clear", "Exit"), "About", and "Help".
- Buat Timer dan action pause dan resume.
- Buat menu Highscore dengan nama pemain.
