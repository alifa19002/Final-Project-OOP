# Sudoku

This repository is a final project (Java GUI) from Object-Oriented Programming Class, Teknik Informatika Universitas Padjadjaran. 

[Challenge Guidelines](challenge-guideline.md)

**Project Sudoku (Java GUI) dengan implementasi konsep OOP (Object Oriented Programming) dan Java Swing berbasis GUI. Sudoku adalah sejenis teka-teki logika. Tujuannya adalah untuk mengisikan angka-angka dari 1 sampai 9 ke dalam jaring-jaring 9×9 yang terdiri dari 9 kotak 3×3 tanpa ada angka yang berulang di satu baris, kolom atau kotak**

## Credits
| NPM           | Name                 |
| ------------- |----------------------|
| 140810190010  | Cut Fazira Zuhra     |
| 140810190018  | Alifa Hafida Anwar   |
| 140810190052  | Putri Ainur Fitri    |

## Change log
- **[Sprint Planning](changelog/sprint-planning.md) - (16 Nov)** 
   -  Mencari referensi tentang permainan sudoku
   -  Scrum Meeting untuk berdiskusi dan membahas sprint backlog secara keseluruhan

- **[Sprint 1](changelog/sprint-1.md) - (date from 16 Nov until 22 Nov)** 
   - Membuat tampilan grid 9x9 (dengan subgrid 3x3)
   - Pengimplementasian OOP Dasar
   - Membuat logika untuk mengacak angka
   - Membuat tampilan difficulty
   - Membuat tampilan string kosong dan angka clue

- **[Sprint 2](changelog/sprint-2.md) - (date from 23 Nov until 29 Nov)** 
   - Set angka yang tidak dimasking tidak bisa diganti
   - Membuat logika untuk mengecek inputan user benar atau salah
   - Membuat logika untuk menghighlight ubin yang bernilai sama di 1 baris, 1 kolom, atau 1 subgrid
   - Membuat tampilan button reset/restart
   
- **[Sprint 3](changelog/sprint-3.md) - (date from 30 Nov until 6 Dec)** 
   - Penyempurnaan Tampilan
   - Challenge ideas (optional)

## Running The App

- Compile File Sudoku.java
- Run sesuai dengan tingkat kesulitan (Difficulty Level: EASY, MEDIUM, HARD) yang diinginkan, dengan rincian command :
   - java Sudoku easy untuk level ‘easy’
   - java Sudoku easy untuk level ‘medium’
   - java Sudoku easy untuk level ‘hard’

## Classes Used

![Mockup](/images/UML.png)

## Notable Assumption and Design App Details

- Tampilan interface Sudoku akan muncul (grid 9 x 9 dengan subgrid 3 x 3 beserta menu dan tampilan lainnya)
- User dapat melakukan input angka terhadap grid yang editable sesuai dengan level, dimana semakin sulit levelnya maka semakin banyak pula grid/tile yang harus diisi
- Klik enter untuk memasukkan angka sekaligus memeriksa apakah inputan user sudah benar dan sesuai
- Jika sesuai, maka inputan user akan berwarna hijau. Namun jika sebaliknya, akan berwarna merah dan tile yang berisi angka yang sama dengan inputan user dalam 1 subgrid akan dihighlight dengan warna merah
- Jumlah 'Cells remaining' akan terus berkurang beriringan dengan jumlah inputan user yang sesuai dengan jawaban
- Dalam permainan, user bisa mengakses pilihan menu (File dan Options). Pada menu File, user dapat restart game, reset jawaban, dan exit (keluar). Sedangkan pada menu Options, user dapat mengganti level permainan (Easy, Medium, Hard)
- Setelah semua tile/grid terisi dengan benar dan tidak ada lagi 'remaining cells', maka permainan selesai dan akan muncul window dialog 'Congratulations'