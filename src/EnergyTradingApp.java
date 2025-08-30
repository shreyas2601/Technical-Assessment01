import java.sql.*;
import java.util.Scanner;

public class EnergyTradingApp {

    // Update with your DB details
    static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=EnergyTradingDB;encrypt=false;integratedSecurity=true";
    static final String USER = "sa";
    static final String PASS = "password";


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            System.out.println("✅ Connected to SQL Server successfully!");

            while (true) {
                System.out.println("\n===== Energy Trading Menu =====");
                System.out.println("1. Add a Trade");
                System.out.println("2. View All Trades");
                System.out.println("3. Update Trade");
                System.out.println("4. Delete Trade");
                System.out.println("5. Search Trades by Counterparty");
                System.out.println("6. Search Trades by Commodity");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        addTrade(conn, sc);
                        break;
                    case 2:
                        viewAllTrades(conn);
                        break;
                    case 3:
                        updateTrade(conn, sc);
                        break;
                    case 4:
                        deleteTrade(conn, sc);
                        break;
                    case 5:
                        searchByCounterparty(conn, sc);
                        break;
                    case 6:
                        searchByCommodity(conn, sc);
                        break;
                    case 7:
                        System.out.println("Exiting... Goodbye!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add Trade
    private static void addTrade(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Trade Date (YYYY-MM-DD): ");
        String tradeDate = sc.nextLine();
        System.out.print("Enter Counterparty: ");
        String counterparty = sc.nextLine();
        System.out.print("Enter Commodity: ");
        String commodity = sc.nextLine();
        System.out.print("Enter Volume: ");
        double volume = sc.nextDouble();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();
        sc.nextLine(); 
        System.out.print("Enter Trade Type (BUY/SELL): ");
        String tradeType = sc.nextLine();

        String sql = "INSERT INTO Trades (TradeDate, Counterparty, Commodity, Volume, Price, TradeType) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(tradeDate));
            stmt.setString(2, counterparty);
            stmt.setString(3, commodity);
            stmt.setDouble(4, volume);
            stmt.setDouble(5, price);
            stmt.setString(6, tradeType);
            stmt.executeUpdate();
            System.out.println("✅ Trade added successfully!");
        }
    }

    // View All Trades
    private static void viewAllTrades(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Trades";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("ID: %d | Date: %s | Counterparty: %s | Commodity: %s | Volume: %.2f | Price: %.2f | Type: %s%n",
                        rs.getInt("TradeID"), rs.getDate("TradeDate"), rs.getString("Counterparty"),
                        rs.getString("Commodity"), rs.getDouble("Volume"), rs.getDouble("Price"),
                        rs.getString("TradeType"));
            }
        }
    }

    // Update Trade
    private static void updateTrade(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Trade ID to update: ");
        int id = sc.nextInt();
        System.out.print("Enter new Volume: ");
        double volume = sc.nextDouble();
        System.out.print("Enter new Price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        String sql = "UPDATE Trades SET Volume=?, Price=? WHERE TradeID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, volume);
            stmt.setDouble(2, price);
            stmt.setInt(3, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Trade updated successfully!");
            } else {
                System.out.println(" Trade ID not found.");
            }
        }
    }

    // Delete Trade
    private static void deleteTrade(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Trade ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        String sql = "DELETE FROM Trades WHERE TradeID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Trade deleted successfully!");
            } else {
                System.out.println(" Trade ID not found.");
            }
        }
    }

    // Search by Counterparty
    private static void searchByCounterparty(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Counterparty to search: ");
        String counterparty = sc.nextLine();

        String sql = "SELECT * FROM Trades WHERE Counterparty LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + counterparty + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.printf("ID: %d | Date: %s | Counterparty: %s | Commodity: %s | Volume: %.2f | Price: %.2f | Type: %s%n",
                        rs.getInt("TradeID"), rs.getDate("TradeDate"), rs.getString("Counterparty"),
                        rs.getString("Commodity"), rs.getDouble("Volume"), rs.getDouble("Price"),
                        rs.getString("TradeType"));
            }
        }
    }

    // Search by Commodity
    private static void searchByCommodity(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Commodity to search: ");
        String commodity = sc.nextLine();

        String sql = "SELECT * FROM Trades WHERE Commodity LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + commodity + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.printf("ID: %d | Date: %s | Counterparty: %s | Commodity: %s | Volume: %.2f | Price: %.2f | Type: %s%n",
                        rs.getInt("TradeID"), rs.getDate("TradeDate"), rs.getString("Counterparty"),
                        rs.getString("Commodity"), rs.getDouble("Volume"), rs.getDouble("Price"),
                        rs.getString("TradeType"));
            }
        }
    }
}
