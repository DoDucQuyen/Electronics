<%@page import="entities.Product"%>
<%@page import="dao.ProductDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product</title>
</head>
<body>
	<%
		ProductDao productDao = new ProductDao();
		String sub_category_id = "";
		String sub_category_name = "";
		if (request.getParameter("sub_category") != null) {
			sub_category_id = request.getParameter("sub_category");

		}
		if (request.getParameter("sub_category_name") != null) {
			sub_category_name = request.getParameter("sub_category_name");

		}
	%>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<div class="products">
			<h2 class=" products-in"><%=sub_category_name %></h2>
			<div class=" top-products">
				<%
					for(Product product : productDao.getListProductBySubCategoryId(Integer.parseInt(sub_category_id))){			
				%>
				<div class="col-md-3 md-col">
					<div class="col-md">
						<a href="single.jsp?product_id=<%=product.getId() %>" class="compare-in"><img
							src="images/pic3.jpg" alt="" />
							</a>
						<div class="top-content">
							<h5>
								<a href="single.jsp?product_id=<%=product.getId() %>"><%=product.getName() %></a>
							</h5>
							<div class="white">
								<a href="CartController?command=addToCart&product_id=<%=product.getId() %>"
									class="hvr-shutter-in-vertical hvr-shutter-in-vertical2">Mua</a>
								<p class="dollar">
									<span class="in-dollar">VND</span><span><%=product.getPrice() %></span>
								</p>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
				</div>
				
				<%
					}
				%>
				<div class="clearfix"></div>
			</div>



		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>