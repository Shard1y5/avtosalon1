package Connection;
import java.sql.*;

public class TestDatabase {

  public static void main(String[] argv) throws SQLException {

   System.out.println("-------- MySQL JDBC Connection Testing ------------");

   try {
      JDBC.connect();
   } catch (SQLException e) {
      System.out.println("Connection Failed!");
      e.printStackTrace();
      return;
   }

   JDBC.close();
  }
}