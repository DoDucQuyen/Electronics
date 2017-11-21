package dao;


import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.Bill;
import entities.Cart;

public class BillDao extends DbManager{
	public void insertBill(Bill bill) {
		String sql = "insert into bill (cart_detail, address, phone, account_id) values(?, N?, ?, ?)";
		PreparedStatement pstm;
		
		openConnection();
		if(connection != null) {
			try {
				pstm = connection.prepareStatement(sql);
				int index = 0;
				pstm.setString(++index, bill.getCart().toString());
				pstm.setString(++index, bill.getAddress());
				pstm.setString(++index, bill.getPhone());
				pstm.setInt(++index, bill.getAccountId());
				pstm.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public static void main(String []args) {
		BillDao dao = new BillDao();
		Bill bill = new Bill();
		bill.setCart(new Cart());
		bill.setAddress("nam cao");
		bill.setPhone("092812391");
		bill.setAccountId(1);
		dao.insertBill(bill);
	}
}
