# jdbc-project
# Java-Ve-Veritaban-Temelleri

JDBC Nedir? Neden Kullanılır?
1. GİRİŞ: VERİTABANI İLE JAVA’NIN İLETİŞİMİ NASIL SAĞLANIR?
Modern uygulamalarda veriler çoğunlukla veritabanlarında (databases) saklanır. Kullanıcı verileri, ürün bilgileri, sipariş kayıtları gibi birçok veri SQL tabanlı ilişkisel veritabanlarında (MySQL, PostgreSQL, Oracle, MSSQL gibi) tutulur.
Ancak Java uygulamaları doğrudan veritabanı ile iletişim kuramaz. Çünkü:
•	Java ve veritabanları farklı teknolojilerdir.
•	Aralarındaki iletişim için ortak bir köprü gerekir.
İşte bu köprü JDBC (Java Database Connectivity)’dir.
2. JDBC NEDİR?
JDBC (Java Database Connectivity):
Java uygulamaları ile ilişkisel veritabanları arasında bağlantı kurmaya, veri göndermeye ve veri almaya yarayan Java tabanlı bir API’dir.
➡ Kısaca: Java → Veritabanı bağlantısını kuran standart bir yapıdır.
________________________________________
3. JDBC’NİN AMACI NEDİR?
 Temel Amaçları:
•	Java uygulamasıyla SQL sorguları çalıştırmak
•	Veritabanından veri çekmek veya veri kaydetmek
•	Bağlantı oluşturmak ve yönetmek
•	Bağlantıyı kapatmak ve kaynakları serbest bırakmak
________________________________________
4. JDBC NEDEN GEREKLİDİR?
4.1. Java ile Veritabanı Direkt Bağlanamaz
•	Java, veritabanının dilini (SQL) doğrudan anlayamaz.
•	Her veritabanının kendine özgü bağlantı kurma biçimi, sürücüsü (driver) ve iletişim protokolü vardır.
•	JDBC, Java tarafında bu karmaşıklığı soyutlayarak kolay kullanım sağlar.
4.2. Veritabanı Bağımsızlığı Sağlar
Java + JDBC kullanarak MySQL ile çalışırken aynı kodu çok az değişiklikle PostgreSQL, Oracle gibi farklı veritabanlarında da kullanabilirsin.
________________________________________
5. JDBC’NİN TEMEL BİLEŞENLERİ
5.1. Driver (Sürücü)
Veritabanı ile iletişim kurmayı sağlayan araç.
Her veritabanının kendine ait JDBC sürücüsü vardır.
Örneğin:
•	MySQL → com.mysql.cj.jdbc.Driver
•	PostgreSQL → org.postgresql.Driver
•	Oracle → oracle.jdbc.driver.OracleDriver
5.2. Connection (Bağlantı)
Java’dan veritabanına açılan kapı.
Veritabanına bağlanmak için Connection nesnesi oluşturulur.
5.3. Statement / PreparedStatement
SQL sorgularının Java’da çalıştırılmasını sağlar.
•	Statement: Basit sorgular
•	PreparedStatement: Daha güvenli ve optimize edilmiş sorgular. (SQL Injection’a karşı korur)
5.4. ResultSet
Sorgudan dönen verilerin tutulduğu nesne.
Veritabanından gelen verileri Java’ya getirir.
________________________________________
6. JDBC NASIL ÇALIŞIR? (ADIM ADIM)
➤ Adım 1: Sürücüyü Yükleme
Class.forName("com.mysql.cj.jdbc.Driver");
➤ Adım 2: Bağlantı Kurma
Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/mydatabase",
    "username",
    "password"
);
➤ Adım 3: SQL Sorgusu Çalıştırma
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM users");
➤ Adım 4: Sonuçları Okuma
while (rs.next()) {
    System.out.println(rs.getString("name"));
}
➤ Adım 5: Bağlantıyı Kapatma
rs.close();
stmt.close();
con.close();
________________________________________
7. JDBC MİMARİSİ NASILDIR?
JDBC katmanlı bir yapıya sahiptir:
JAVA KODU
   │
   ▼
JDBC API (Connection, Statement, ResultSet)
   │
   ▼
JDBC DRIVER (MySQL, PostgreSQL, Oracle Driver vb.)
   │
   ▼
VERİTABANI (MySQL, PostgreSQL, Oracle vb.)
________________________________________
8. JDBC ÇEŞİTLERİ (DRIVER TİPLERİ)
Driver Tipi	Açıklama	Kullanım Durumu
Type 1	JDBC-ODBC Köprüsü	Eski / Kullanılmaz
Type 2	Yerel (Native) API	Nadiren
Type 3	Ağ Protokolü Sürücü	Az kullanılır
Type 4	Tamamen Java ile yazılmış, en yaygın tip	Modern sürücüler
Modern uygulamalarda Type 4 JDBC Driver kullanılır.
________________________________________
9. JDBC NEDEN ÖNEMLİDİR?
✔️ Veritabanı bağımsızlığı sağlar.
✔️ SQL sorgularını Java’dan çalıştırma imkânı sunar.
✔️ Performanslı ve optimize edilmiş veri erişimi sağlar.
✔️ Büyük ölçekli projelerde standart veri erişim aracıdır.
✔️ ORM (Hibernate gibi) araçların temelinde de JDBC yatar.
________________________________________
10. JDBC KULLANIM ALANLARI
•	Web uygulamaları (Spring Boot, Java EE)
•	Masaüstü Java programları
•	Veri analizi / raporlama uygulamaları
•	Batch işlemler
•	API tabanlı veri servisleri
________________________________________
11. JDBC İLE GELEN PROBLEMLER ve ÇÖZÜMLERİ
Problem	Çözüm
Çok fazla kod yazılması	Hibernate, JPA gibi ORM kullanımı
Hata yönetiminin zor olması	Exception handling ile yönetilebilir hale getirme
Bağlantı havuzu gereksinimi	HikariCP, Apache DBCP gibi çözümler
SQL bağımlılığı	ORM araçları SQL bağımlılığını azaltır
________________________________________
12. JDBC İLE İLGİLİ SIK KULLANILAN SINIFLAR
•	DriverManager
•	Connection
•	Statement, PreparedStatement, CallableStatement
•	ResultSet
•	SQLException
________________________________________
13. KÜÇÜK BİR UYGULAMA ÖRNEĞİ
try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/testdb", "root", "password"
    );
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM employees");

    while (rs.next()) {
        System.out.println(rs.getInt("id") + " - " + rs.getString("name"));
    }

    rs.close();
    stmt.close();
    con.close();
} catch (Exception e) {
    e.printStackTrace();
}
________________________________________
14. SONUÇ OLARAK JDBC
✅ Java’da veritabanı işlemleri için temel ve vazgeçilmez bir araçtır.
✅ Basit projelerde doğrudan kullanılabilir.
✅ Karmaşık projelerde Hibernate, JPA gibi araçların altyapısı olarak çalışır.
________________________________________
15. ÖZETLE
•	JDBC = Java ile veritabanı arasında köprü
•	SQL sorgularını Java’dan çalıştırma aracı
•	Bağlantı yönetimi, veri alma ve gönderme işlemlerinin temelini oluşturur
•	Modern projelerde genellikle ORM katmanı ile birlikte kullanılır.
