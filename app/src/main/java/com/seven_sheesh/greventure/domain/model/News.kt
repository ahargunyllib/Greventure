package com.seven_sheesh.greventure.domain.model

data class News(
    val id: String,
    val title: String,
    val description: String,
    val photoUrl: String,
    val createdAt: String,
    val author: String,
    val minutesToRead: Int
)

val dummyNews = listOf(
    News(
        id = "1",
        title = "FILKOM UB Terapkan PELATO Berbasis IOT di Kota Malang",
        description = "Dosen Fakultas Ilmu Komputer Universitas Brawijaya (FILKOM UB)—Tibyani, S.T., M.T., Dr. Candra Dewi, S.Kom., M.Sc., Prof. Ir. Wayan Firdaus Mahmudy, S.Si., M.T., Ph.D., dan Intan Sartika Eris Maghfiroh, S.E., M.B.A.—melaksanakan Pengabdian Masyarakat di Gazebo Karomah, Kelurahan Lowokwaru, Kota Malang pada 25 Agustus 2024. \n" +
                "\n" +
                "Kegiatan ini bertujuan memberdayakan masyarakat melalui sistem smart urban farming berbasis Internet of Things (IoT), dengan program bernama Branding Produk Dan Sistem Pengairan Lahan Otomatis (PELATO) Berbasis IoT Menuju Mandiri Pangan. Pelatihan dan pendampingan ini dihadiri 20 anggota Kelompok Tani Karomah. Sistem PELATO menggunakan sensor kelembapan dan sensor ultrasonik yang terintegrasi dengan chatbot Telegram untuk memantau kondisi tanah dan sisa volume air. Pengujian menunjukkan sistem smart farming ini berjalan dengan baik.",
        photoUrl = "https://csjzppimbihiqokacjco.supabase.co/storage/v1/object/public/news/news_1.png",
        createdAt = "27 Agustus 2024",
        author = "Humas Filkom",
        minutesToRead = 2
    ),
    News(
        id = "2",
        title = "Semarakkan HUT Kemerdekaan, Pemkot Malang Gelar Pedalling for Freedom",
        description = "Dalam rangka memeriahkan perayaan Hari Ulang Tahun (HUT) Kemerdekaan Republik Indonesia ke-79, Pemerintah Kota Malang mengadakan acara bertajuk “Pedalling for Freedom.” Acara ini diikuti ratusan peserta dari berbagai komunitas sepeda dan warga setempat yang antusias menyemarakkan suasana kemerdekaan. Rute bersepeda yang disiapkan melintasi beberapa ikon kota seperti Balai Kota, Alun-Alun Merdeka, hingga ke Kampung Warna-Warni, menawarkan sensasi unik bersepeda sambil menikmati panorama kota Malang yang bersejarah. Kegiatan ini tidak hanya menjadi ajang untuk memperingati kemerdekaan, tetapi juga sebagai sarana promosi gaya hidup sehat serta pengenalan destinasi wisata lokal kepada peserta.\n" +
                "\n" +
                "Walikota Malang, bersama dengan jajaran pemerintah setempat, turut ambil bagian dalam acara ini sebagai bentuk apresiasi dan dukungan terhadap komunitas sepeda yang terus berkembang di kota tersebut. Pedalling for Freedom juga dilengkapi dengan berbagai kegiatan hiburan, seperti penampilan musik tradisional, pameran UMKM, dan lomba hias sepeda bertema kemerdekaan, yang menambah semarak suasana. Acara ini diharapkan dapat menjadi tradisi tahunan yang menginspirasi masyarakat untuk terus menjaga semangat nasionalisme dan kebersamaan, sekaligus meningkatkan kesadaran akan pentingnya kebugaran dan kelestarian lingkungan melalui bersepeda.",
        photoUrl = "https://csjzppimbihiqokacjco.supabase.co/storage/v1/object/public/news/news_2.png",
        createdAt = "25 Agustus 2024",
        author = "Afriza Herdian",
        minutesToRead = 5
    ),
    News(
        id = "3",
        title = "Daftar Kuliner Malang yang Cocok Untuk Mahasiswa",
        description = "Malang dikenal sebagai kota pelajar yang memiliki beragam kuliner lezat dengan harga terjangkau, menjadikannya surga bagi para mahasiswa. Di tengah kesibukan kuliah dan aktivitas kampus, banyak mahasiswa yang mencari tempat makan enak tanpa perlu merogoh kocek dalam-dalam. Salah satu destinasi favorit adalah Nasi Goreng Resek, yang menawarkan cita rasa khas dengan bumbu rempah yang kuat. Selain itu, ada Bakso Bakar Pak Man yang legendaris, terkenal dengan bakso yang dibakar dengan bumbu khas dan harga ramah kantong. Sate Gebug, meski sederhana, menjadi pilihan tepat bagi mahasiswa yang ingin menikmati kuliner dengan sensasi unik dan berkesan.\n" +
                "\n" +
                "Selain itu, ada juga Warung Sambal Lalapan Cak Tomo yang populer di kalangan mahasiswa karena pilihan lauk beragam dan sambalnya yang pedas menggugah selera. Tidak ketinggalan, Toko Oen yang legendaris bisa menjadi alternatif tempat nongkrong dengan suasana klasik dan berbagai pilihan es krim yang pas di kantong. Tempat-tempat ini bukan hanya menawarkan makanan yang enak dan murah, tetapi juga menjadi spot berkumpul dan melepas penat setelah aktivitas kampus. Dengan pilihan kuliner yang beragam, Malang memberikan pengalaman kuliner yang tak hanya memuaskan perut, tetapi juga menghangatkan hati para mahasiswa.",
        photoUrl = "https://csjzppimbihiqokacjco.supabase.co/storage/v1/object/public/news/news_3.png",
        createdAt = "24 Agustus 2024",
        author = "Yusrizal Harits",
        minutesToRead = 7
    ),
    News(
        id = "4",
        title = "Demo Hari kedua Kawal Putusan MK di Malang Berakhir Ricuh",
        description = "Aksi demonstrasi di Malang yang memasuki hari kedua untuk mengawal putusan Mahkamah Konstitusi (MK) berakhir ricuh. Ratusan mahasiswa dan elemen masyarakat yang tergabung dalam aliansi aksi berkumpul di depan Balai Kota Malang untuk menyuarakan aspirasi terkait putusan MK yang dinilai kontroversial. Aksi yang semula berlangsung damai berubah menjadi ricuh ketika terjadi saling dorong antara massa dan aparat kepolisian yang berjaga. Beberapa demonstran mencoba menerobos barikade polisi, memicu situasi menjadi semakin tegang. Gas air mata pun terpaksa ditembakkan untuk membubarkan massa yang terus merangsek ke arah gedung pemerintahan.\n" +
                "\n" +
                "Kericuhan ini mengakibatkan sejumlah demonstran dan aparat terluka ringan, sementara beberapa orang lainnya diamankan oleh pihak kepolisian untuk dimintai keterangan lebih lanjut. Meski demikian, para demonstran berjanji akan terus mengawal putusan MK hingga tuntutan mereka didengar. Mereka menuntut transparansi dan keadilan dalam proses hukum yang dianggap mempengaruhi masa depan demokrasi di Indonesia. Aksi ini mendapat sorotan luas, baik dari masyarakat lokal maupun nasional, yang berharap agar aspirasi publik dapat disampaikan dengan damai tanpa kekerasan.",
        photoUrl = "https://csjzppimbihiqokacjco.supabase.co/storage/v1/object/public/news/news_4.png",
        createdAt = "23 Agustus 2024",
        author = "Aufii Fathin",
        minutesToRead = 10
    ),
    News(
        id = "5",
        title = "Bupati Malang Hadiri Karnaval Di Desa Mulyoagung Dau Malang",
        description = "Bupati Malang hadir dalam acara karnaval di Desa Mulyoagung, Kecamatan Dau, sebagai bagian dari rangkaian perayaan Hari Kemerdekaan Republik Indonesia. Karnaval ini menjadi momen yang dinanti warga setempat, menampilkan berbagai atraksi seni dan budaya yang melibatkan partisipasi aktif masyarakat, mulai dari anak-anak hingga orang dewasa. Beragam kostum unik dengan tema-tema yang kreatif, mulai dari pakaian adat hingga replika ikon-ikon nasional, memeriahkan jalannya karnaval. Kehadiran Bupati Malang menjadi sorotan tersendiri, yang menyempatkan diri berbaur dengan warga, menyaksikan setiap penampilan, dan memberikan apresiasi atas semangat kebersamaan yang terpancar dalam acara tersebut.\n" +
                "\n" +
                "Dalam sambutannya, Bupati Malang menyampaikan rasa bangganya terhadap partisipasi warga Desa Mulyoagung yang turut menjaga tradisi dan memupuk rasa cinta tanah air melalui kegiatan positif seperti karnaval ini. Ia juga berharap acara seperti ini dapat terus dilestarikan sebagai ajang untuk memperkuat tali silaturahmi antarwarga. Acara karnaval ditutup dengan pertunjukan musik dan pameran UMKM lokal, yang semakin memperkaya suasana perayaan. Momen ini tidak hanya menjadi hiburan bagi warga tetapi juga menjadi ajang promosi potensi desa yang kaya akan budaya dan kreativitas.",
        photoUrl = "https://csjzppimbihiqokacjco.supabase.co/storage/v1/object/public/news/news_5.png",
        createdAt = "22 Agustus 2024",
        author = "Ghufron Bagaskara",
        minutesToRead = 11
    )
)