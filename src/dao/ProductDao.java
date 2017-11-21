package dao;

import java.net.ConnectException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import entities.Product;

public class ProductDao extends DbManager {

	// get map product
	public HashMap<Integer, Product> getAllProductMap() {
		HashMap<Integer, Product> map = new HashMap<>();
		String sql = "select * from product";
		PreparedStatement pstm;
		ResultSet rs;
		Product product;
		
		openConnection();
		if(connection != null){
			try {
				pstm = connection.prepareStatement(sql);
				rs = pstm.executeQuery();
				while(rs.next()) {
					product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setDescription(rs.getString("description"));
					product.setImage(rs.getString("image"));
					product.setPrice(rs.getInt("price"));
					product.setQuantity(rs.getInt("quantity"));
					product.setSubCategoryId(rs.getInt("sub_category_id"));
					map.put(product.getId(), product);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return map;
	}
	
	// get map product id and subCategoryId
	public HashMap<Integer, Integer> getProductMap() {
		String sql = "Select * from product";
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		openConnection();
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				map.put(rs.getInt("id"), rs.getInt("sub_category_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}

	public ArrayList<Product> getListProductBySubCategoryId(int subCategoryId) {
		ArrayList<Product> listProduct = null;
		String sql = "select * from product where sub_category_id = ?";
		PreparedStatement pstm;
		ResultSet rs;
		Product product;
		openConnection();
		if (connection != null) {
			try {
				listProduct = new ArrayList<>();
				pstm = connection.prepareStatement(sql);
				pstm.setInt(1, subCategoryId);
				rs = pstm.executeQuery();
				while (rs.next()) {
					product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setDescription(rs.getString("description"));
					product.setImage(rs.getString("image"));
					product.setPrice(rs.getInt("price"));
					product.setQuantity(rs.getInt("quantity"));
					product.setSubCategoryId(rs.getInt("sub_category_id"));
					listProduct.add(product);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listProduct;
	}

	public Product getProduct(int productId) {
		Product product = null;
		String sql = "select * from product where id = ?";
		PreparedStatement pstm;
		ResultSet rs;
		openConnection();
		if(connection != null) {
			try {
				pstm = connection.prepareStatement(sql);
				pstm.setInt(1, productId);
				rs = pstm.executeQuery();
				while (rs.next()) {
					product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setDescription(rs.getString("description"));
					product.setImage(rs.getString("image"));
					product.setPrice(rs.getInt("price"));
					product.setQuantity(rs.getInt("quantity"));
					product.setSubCategoryId(rs.getInt("sub_category_id"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return product;
	}
	public void initProduct(int numberProduct) {
		String sql = "insert into product(name,description,price,image,quantity,sub_category_id) values( ?, ?, ?, ?, ?, ?) ";
		openConnection();
		try {
			PreparedStatement pstm;
			String sqlTruncate = "truncate table product";
			pstm = connection.prepareStatement(sqlTruncate);
			pstm.executeUpdate();
			pstm.clearBatch();
			for (int i = 0; i < numberProduct; i++) {
				pstm = connection.prepareStatement(sql);
				int j = 0;
				pstm.setString(++j, "Sản Phẩm " + i);
				pstm.setString(++j, "Mô tả sản phẩm");
				pstm.setInt(++j, 100000);
				pstm.setString(++j, "img.jpg");
				pstm.setInt(++j, 10);
				pstm.setInt(++j, i % 5 + 1);
				pstm.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ProductDao dao = new ProductDao();
		dao.getAllProductMap();
		// HashMap map = dao.getProductMap();
		// System.out.print(map.get(new Integer(3)));
	}
}
