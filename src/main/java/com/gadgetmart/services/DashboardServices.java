package com.gadgetmart.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gadgetmart.config.DatabaseConfig;

public class DashboardServices {
	private final DatabaseConfig dbconfig = new DatabaseConfig();
	

	
	//dashboard
	//get order count
	public int getOrderCount() {
		int count = 0;
		try (Connection con = dbconfig.getConnection()) {
			PreparedStatement st = con.prepareStatement("SELECT COUNT(*) AS Order_Count FROM orders");

			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				count = rs.getInt("Order_Count");
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		return count;

	}
	
	//get pending count of order
	public int getPendingCount() {
		int count = 0;
		try (Connection con = dbconfig.getConnection()) {
			PreparedStatement st = con.prepareStatement("SELECT COUNT(*) AS Order_Count FROM orders WHERE Status = 'Pending'");

			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				count = rs.getInt("Order_Count");
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		return count;

	}
	
	//get delivery count of order
	public int getdeliveredCount() {
		int dcount = 0;
		try (Connection con = dbconfig.getConnection()) {
			PreparedStatement st = con.prepareStatement("SELECT COUNT(*) AS Order_Count FROM orders WHERE Status = 'Delivered'");

			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				dcount = rs.getInt("Order_Count");
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		return dcount;

	}
//get canceled order count
	public int getcanceledCount() {
		int count = 0;
		try (Connection con = dbconfig.getConnection()) {
			PreparedStatement st = con.prepareStatement("SELECT COUNT(*) AS Cancel_Count FROM orders WHERE Status = 'Canceled'");

			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				count = rs.getInt("Cancel_Count");
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		return count;

	}
	
	//get total order product count
	public int getTotalproductorderCount() {
		int Tcount = 0;
		try (Connection con = dbconfig.getConnection()) {
			PreparedStatement st = con.prepareStatement("SELECT COUNT(*) AS Total FROM order_producrs");

			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				Tcount = rs.getInt("Total");
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		return Tcount;

	}
	
	//get number of each product count
	public List<Integer> getEachProductCount() {
        List<Integer> counts = new ArrayList<>();

        try (Connection con = dbconfig.getConnection()) {
            PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM products GROUP BY LEFT(Product_Id, 2) ORDER BY LEFT(Product_Id, 2) ASC");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                counts.add(rs.getInt(1));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return counts;
    }
	
	//get number of order of each product
	public List<Integer> getorderEachProductCount() {
        List<Integer> counts = new ArrayList<>();

        try (Connection con = dbconfig.getConnection()) {
            PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM order_producrs GROUP BY LEFT(Product_Id, 2) ORDER BY LEFT(Product_Id, 2) ASC");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                counts.add(rs.getInt(1));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return counts;
    }
	
}
