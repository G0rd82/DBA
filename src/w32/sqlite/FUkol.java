package w32.sqlite;
// Převeďte data z https://onemocneni-aktualne.mzcr.cz/api/v2/covid-19
// (COVID-19: Přehled osob s prokázanou nákazou dle hlášení krajských
// hygienických stanic (v2)).
// K nalezení zde: X:\stemberk\verejne_zaci\osoby.csv
// do databáze (můžete si vybrat mezi SQLite, či MySQL).
// Totéž proveďte pro soubor X:\stemberk\verejne_zaci\staty.csv a zobrazte
// v TableView, případně v nějakém grafu JavaFX.

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Struktura tabulky:
 * id,datum,vek,pohlavi,kraj_nuts_kod,okres_lau_kod,nakaza_v_zahranici,nakaza_zeme_csu_kod,reportovano_khs
 * 1ea976a2-896a-40b2-b617-b780a713323d,2020-03-01,43,M,CZ042,CZ0421,1,IT,1
 */
public class FUkol {
    public static void createTable() {
        Connection c = null;
        Statement stmt = null;
        try {
            //Class.forName("org.w32.sqlite.JDBC");
            //c = DriverManager.getConnection("jdbc:w32.sqlite:SqliteJavaDB.db");
            c = AMainDBConn.connect();
            System.out.println("Database Opened...\n");
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS covid " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "id2 TEXT NOT NULL," +
                    "datum TEXT NOT NULL, " +
                    "vek INT NOT NULL, " +
                    "pohlavi TEXT NOT NULL," +
                    "kraj TEXT NOT NULL," +
                    "okres TEXT NOT NULL," +
                    "nakaza_v_zh BIT ," +
                    "nakaza_zeme_csu TEXT ," +
                    "reportovano BIT );" ;
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table Product Created Successfully!!!");
    }
    /**
     * Insert a new row into the warehouse table
     *
     * @param name
     */
    public static void insert(String id2, String datum, int vek, String pohlavi, String kraj, String okres, boolean nakaza_v_zh,String nakaza_zeme_csu, boolean reportovano) {
        String sql = "INSERT INTO covid(id2, datum, vek, pohlavi, kraj, okres, nakaza_v_zh, nakaza_zeme_csu, reportovano)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = AMainDBConn.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id2);
            pstmt.setString(2, datum);
            pstmt.setInt(3, vek);
            pstmt.setString(4, pohlavi);
            pstmt.setString(5, kraj);
            pstmt.setString(6, okres);
            pstmt.setBoolean(7, nakaza_v_zh);
            pstmt.setString(8, nakaza_zeme_csu);
            pstmt.setBoolean(9, reportovano);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) throws IOException {
        FileReader fr = null;
        String radka;
        int cnt = 0;
        fr = new FileReader("X:\\stemberk\\verejne_zaci\\osoby.csv");
        BufferedReader br = new BufferedReader(fr);
        br.readLine(); // prvni radku zahodime;
        createTable();

        while((radka = br.readLine()) != null) {
            String id = "";
            String datum = "";
            int vek = 0;
            String mf = "";
            String kraj = "";
            String okres = "";
            Boolean vZahranici = false;
            String stat = "";
            Boolean reportovanoKhs = false;

            String[] hodnoty = radka.split(",");
            if(hodnoty.length > 0 ) id = hodnoty[0];
            if(hodnoty.length > 1 ) datum = hodnoty[1];
            if(hodnoty.length > 2 ) vek = Integer.parseInt(hodnoty[2]);
            if(hodnoty.length > 3 ) mf = hodnoty[3];
            if(hodnoty.length > 4 ) kraj = hodnoty[4];
            if(hodnoty.length > 5 ) okres = hodnoty[5];
            if(hodnoty.length > 6 ) vZahranici = Boolean.parseBoolean(hodnoty[6]);
            if(hodnoty.length > 7 ) stat = hodnoty[5];
            if(hodnoty.length > 8 ) reportovanoKhs = Boolean.parseBoolean(hodnoty[6]);
            System.out.format("%s, %s, %d, %s, %s, %s, %b, %s, %b%n",
                    id, datum, vek, mf, kraj, okres, vZahranici, stat, reportovanoKhs);
            insert(id, datum, vek, mf, kraj, okres, vZahranici, stat, reportovanoKhs);
            if (cnt++ > 10) break;

        }
    }
}
