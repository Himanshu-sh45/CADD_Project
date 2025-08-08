import java.sql.*;
import java.util.*;

class Transaction {
    private int accountId;
    private java.util.Date transactionDate;
    private double amount;

    public Transaction() {}

    public Transaction(int accountId, java.util.Date transactionDate, double amount) {
        this.accountId = accountId;
        this.transactionDate = transactionDate;
        this.amount = amount;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public java.util.Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(java.util.Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "accountId=" + accountId +
                ", transactionDate=" + transactionDate +
                ", amount=" + amount +
                '}';
    }
}

public class FinancialManagementSystem {

    private Connection conn;

    public FinancialManagementSystem() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbUrl = "jdbc:mysql://localhost:3306/finantial_db";
            String username = "root";
            String password = "HB46717017@dd";
            conn = DriverManager.getConnection(dbUrl, username, password);
            conn.setAutoCommit(false);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void addTransaction(List<Transaction> transactions) {
        try {
            String sql = "INSERT INTO transactions (account_id, transaction_date, amount) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            for (Transaction transaction : transactions) {
                pstmt.setInt(1, transaction.getAccountId());
                pstmt.setDate(2, new java.sql.Date(transaction.getTransactionDate().getTime()));
                pstmt.setDouble(3, transaction.getAmount());
                pstmt.addBatch();
            }

            pstmt.executeBatch();
            conn.commit();
            System.out.println("Transactions added successfully.");
        } catch (SQLException e) {
            try {
                conn.rollback();
                System.out.println("Error adding transactions: " + e.getMessage());
            } catch (SQLException ex) {
                System.out.println("Error rolling back transaction: " + ex.getMessage());
            }
        }
    }

    public void updateAccountBalance(int accountId, double newBalance) {
        try {
            String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, newBalance);
            pstmt.setInt(2, accountId);
            pstmt.executeUpdate();
            conn.commit();
            System.out.println("Account balance updated successfully.");
        } catch (SQLException e) {
            try {
                conn.rollback();
                System.out.println("Error updating balance: " + e.getMessage());
            } catch (SQLException ex) {
                System.out.println("Error rolling back transaction: " + ex.getMessage());
            }
        }
    }

    public List<Transaction> getTransactions(int accountId) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            String sql = "SELECT * FROM transactions WHERE account_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, accountId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setAccountId(rs.getInt("account_id"));
                transaction.setTransactionDate(rs.getDate("transaction_date"));
                transaction.setAmount(rs.getDouble("amount"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching transactions: " + e.getMessage());
        }
        return transactions;
    }

    public static void main(String[] args) {
        FinancialManagementSystem system = new FinancialManagementSystem();

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1, new java.util.Date(), 100.0));
        transactions.add(new Transaction(1, new java.util.Date(), 200.0));

        system.addTransaction(transactions);
        system.updateAccountBalance(1, 500.0);

        List<Transaction> transactionList = system.getTransactions(1);
        for (Transaction transaction : transactionList) {
            System.out.println(transaction);
        }

    }
}
