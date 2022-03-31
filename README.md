# UTS_JavaProgramming_535180008
Nama / NIM : Devid Sumarlie / 535180008
 Repository Tugas UTS Java
 
Berikut adalah Aplikasi untuk UTS Java Programming. Topik aplikasi yang saya ambil adalah Library Management System(LMS) menggunakan JavaFX. LMS ini diepruntukkan untuk administrator perpustakaan, sehingga user role yang ada di dalam aplikasi hanya terbatas pada administrator yang menggunakan/memakainya. 

##Technology stack yang diapakai dalam pembuatan LMS :
- IDE Netbeans 12.6 & Intellij IDEA 2019.2
- Java JDK versi 11
- SqLite JDBC 3.27.2.1 (Jar SqLite dapat diunduh di [Maven Repo](https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.27.2.1/) dan di-include kan ke dalam pengaturan library pada masing-masing IDE yang dipakai. Pada project ini tidak dipakai pom.xml dari maven.
- Openjfx dengan versi openjfx-11.0.2_windows-x64_bin-sdk( harus ter-include dalam pengaturan lib pada IDE, atau bisa juga dijalankan dengan java jdk versi 8).
- DBeaver untuk pengolahan file .sqlite

Aplikasi dibuat dengan coding manual dan tidak memakai fxml untuk desain letak menu.

Semua komponen UI rata-rata memakai pengaturan peletakkan koordinat x,y menggunakan integer, yang dimana kelemahannya adalah bisa mengorbankan modularitas dan fleksibilitas untuk mengatur letak komponen UI secara leluasa, terlebih lagi jika dibandingkan dengan pengaturan memalui FXML yang lebih mudah. Styling komponen UI menggunakan file stylesheet CSS.

Database digunakan karena terdapat banyak kendala menggunakan array untuk metode store data , akses index array yang masih gagal jika data ingin di-add secara terurut pada observeablearraylist.

Di dalam aplikasi terdapat 7 menu yaitu beranda, add member, Detail member, Tambah Buku, List Buku, Pengembalian(masih terdapat bug) dan Peminjaman.

Untuk pop-up menu Peminjaman masih ter-embed ke dalam panel aplikasi dan tidak menggunakan stage dan scene terpisah.

Untuk mengkases aplikasi, kredensial default yang dipakai dan yang ter hardcode adalah admin:admin dan tidak case sensitive, untuk form password masih belum terdapat input sanitation / belum diimplementasikan, tetapi untuk input validation, sudah terintegrasi dan dapat mengecek apakah kredensial salah / tidak.

Aplikasi hanya bisa dijalankan di dalam IDE dan tidak ada file .exe standalone.

Aplikasi akan berjalan jika koneksi ke sql sukses, jika tidak maka akan di keluarkan output gagal pada console IDE. Setiap koneksi yang dibuat terdapat fungsi untuk menutup koneksi yang lama tidak terpakai  agar menghindari deadlock pada Database dan operasi database selanjutnya dapat dilakukan(di-eksekusi tidak secara simultan/batch melainkan satu-satu). Disamping itu, agar tidak menyulitkan pembuatan tabel dalam DB( kecuali untuk tabel User yang sudha dibuat & di hardcode terlebih dahulu oleh pembuat aplikasi), maka terdapat operasi CREATE TABLE dengan nama fungsi sql.setuptabelbuku(), sql.setuptabelmember(), sql.setuptabelpinjam() yang dimana dapat di-instantiasi dari fungsi SqlConnection().

Penginputan data secara redundant / berkali - kali ,e.g. baik dengan konten data yang sama pada suatu form secara berulang  akan terjadi duplikat data pada DB dan koneksi akan berhenti.

## Detail setiap menu :
- beranda -> untuk kembali ke home
- add member -> menambahkan member
- Detail member -> melihat detail mamber dalam bentuk tabel
- Tambah buku -> untuk menambahkan buku
- Detail buku -> untuk melihat buku apa saja yang ada, status ketersediaan menunjukkan sedang tersedia atau tidaknya suatu buku
- pengembalian buku(masih terdapat bug) untuk pengembalian buku pengunjung perpustakaan
- Peminjaman Buku -> untuk meminjamkan buku ke user


