package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductRuleDao extends DbManager{
	public ArrayList<Integer> getRule(int product_id, int numberRule) {
		ArrayList<Integer> list = null;
		String sql = "select product_id2 from product_rule WHERE product_id1 = ? ORDER BY sup_count DESC LIMIT ?";
		PreparedStatement pstm;
		ResultSet rs;
		
		openConnection();
		if(connection != null) {
			try {
				list = new ArrayList<>();
				pstm = connection.prepareStatement(sql);
				pstm.setInt(1, product_id);
				pstm.setInt(2, numberRule);
				rs = pstm.executeQuery();
				while(rs.next()){
					list.add(rs.getInt("product_id2"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return list;
	} 
	
	public static void main(String []agrs){
		ProductRuleDao dao = new ProductRuleDao();
		for(int i : dao.getRule(1, 4)){
			System.out.println(i);
		}
	}
}
