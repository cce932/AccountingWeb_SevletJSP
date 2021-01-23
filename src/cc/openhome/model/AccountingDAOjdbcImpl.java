package cc.openhome.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;

public class AccountingDAOjdbcImpl {
    private DataSource dataSource;

    public AccountingDAOjdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Accounting> findAcctingByName(String name) {

        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Accounting WHERE NAME = ?")) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            List<Accounting> accting = new ArrayList<>();
            while (rs.next()) {
                Accounting a = new Accounting(rs.getInt(1), rs.getBoolean(2), rs.getString(3), rs.getString(4),
                        rs.getBoolean(5), rs.getInt(6), rs.getString(7), rs.getString(8));

                accting.add(a);
            }
            return accting;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delAccting(int id) {

        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM Accounting WHERE ID = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("acctingImpl delAccting: " + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int getBudget(String name) { // 取得預算
        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT BUDGET FROM ACCOUNT WHERE NAME = ?")) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            int budget = 0;
            while (rs.next()) {
                budget = rs.getInt(1);
            }
            return budget;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getMonthCost(String name) { // 查詢本月花費
        Date month = new Date(); // 當前時間
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMM"); // 設定日期格式

        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT AMOUNT, ID FROM accounting WHERE name = ? and date LIKE ? AND TYPE = ?")) {
            stmt.setString(1, name);
            stmt.setString(2, dateFormat.format(month) + '%'); // date是本月 例如month=202006 則20200602的列就會被搜到
            stmt.setBoolean(3, false); // type: false 支出

            ResultSet rs = stmt.executeQuery();
            int amount = 0;
            while (rs.next()) {

                amount += rs.getInt(1);
            }
            return amount;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 加日期了
    public int findTodayAmount(String name) { // 本日餘額
        Date date = new Date(); // 當前時間
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMdd"); // 設定日期格式

        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn
                        .prepareStatement("SELECT TYPE, AMOUNT, ID FROM accounting WHERE name = ? and date = ?")) {
            stmt.setString(1, name);
            stmt.setString(2, dateFormat.format(date));
            ResultSet rs = stmt.executeQuery();

            int amount = 0;
            while (rs.next()) {
                if (rs.getBoolean(1) == true) { // 收入
                    amount += rs.getInt(2);
                } else { // 支出
                    amount -= rs.getInt(2);
                }
            }

            return amount;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int findAllCash(String name) {
        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn
                        .prepareStatement("SELECT TYPE, AMOUNT FROM accounting WHERE name = ? and accounttype = ?")) {
            stmt.setString(1, name);
            stmt.setBoolean(2, true);
            ResultSet rs = stmt.executeQuery();

            int amount = 0;
            while (rs.next()) {
                if (rs.getBoolean(1) == true) { // 收入
                    amount += rs.getInt(2);
                } else { // 支出
                    amount -= rs.getInt(2);
                }

            }

            return amount;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int findAllDeposit(String name) { // 銀行餘額
        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn
                        .prepareStatement("SELECT TYPE, AMOUNT FROM accounting WHERE name = ? and ACCOUNTTYPE = ?")) {
            stmt.setString(1, name);
            stmt.setBoolean(2, false);
            ResultSet rs = stmt.executeQuery();

            int amount = 0;
            while (rs.next()) {
                if (rs.getBoolean(1) == true) { // 收入
                    amount += rs.getInt(2);
                } else { // 支出
                    amount -= rs.getInt(2);
                }
            }

            return amount;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int findAllAmount(String name) {
        return findAllCash(name) + findAllDeposit(name);
    }

    public void createAccounting(Accounting accting) {
        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO ACCOUNTING (TYPE, DATE, CATEGORY, ACCOUNTTYPE, AMOUNT, NOTES, NAME) "
                                + "VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            System.out.println("category:  " + accting.getCategory());

            stmt.setBoolean(1, accting.getType());
            stmt.setString(2, accting.getDate());
            stmt.setString(3, accting.getCategory());
            stmt.setBoolean(4, accting.getAccounttype());
            stmt.setInt(5, accting.getAmount());
            stmt.setString(6, accting.getNotes());
            stmt.setString(7, accting.getName());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
