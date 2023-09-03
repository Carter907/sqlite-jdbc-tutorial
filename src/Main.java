import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:file:database")) {
            try (var statement = conn.prepareStatement("CREATE TABLE IF NOT EXISTS test(id INTEGER, name VARCHAR(255), primary key(id))")) {
                statement.execute();
            }

            var select = conn.prepareStatement("SELECT * FROM test");
            try (var statement = conn.prepareStatement("INSERT INTO test VALUES(?, ?)")) {
                statement.setInt(1, 1);
                statement.setString(2, "Person");
                statement.execute();

            }
            try (var results = select.executeQuery()) {
                while (results.next()) {
                    System.out.printf("""
                            %d,
                            %s
                            """, results.getInt(1), results.getString(2));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}