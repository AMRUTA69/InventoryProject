import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InventoryManager {
    // Change these credentials as per your MySQL setup
    private static final String URL = "jdbc:mysql://localhost:3306/InventoryDB";
    private static final String USER = "root"; // Your MySQL username
    private static final String PASSWORD = "root"; // Your MySQL password

    // Helper method to get connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // 1. Add Item
    public void addItem(String name, int quantity, double price) {
        String sql = "INSERT INTO Item (name, quantity, price) VALUES (?, ?, ?)";
        // Try-with-resources for PreparedStatement
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, name);
            pstmt.setInt(2, quantity);
            pstmt.setDouble(3, price);
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✔ Item added successfully!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error adding item: " + e.getMessage());
        }
    }

    // 2. View All Items
    public void viewItems() {
        String sql = "SELECT * FROM Item";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\n--- Inventory List ---");
            System.out.printf("%-5s %-20s %-10s %-10s\n", "ID", "Name", "Quantity", "Price");
            System.out.println("--------------------------------------------------");
            
            while (rs.next()) {
                System.out.printf("%-5d %-20s %-10d %-10.2f\n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error viewing items: " + e.getMessage());
        }
    }

    // 3. Update Item Quantity
    public void updateItem(int id, int newQuantity) {
        String sql = "UPDATE Item SET quantity = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, newQuantity);
            pstmt.setInt(2, id);
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✔ Item updated successfully!");
            } else {
                System.out.println("⚠ Item with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error updating item: " + e.getMessage());
        }
    }

    // 4. Delete Item
    public void deleteItem(int id) {
        String sql = "DELETE FROM Item WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✔ Item deleted successfully!");
            } else {
                System.out.println("⚠ Item with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error deleting item: " + e.getMessage());
        }
    }
}
