<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.List,cc.openhome.model.Accounting" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Record</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/record.css" />
</head>

<body>

	<section>
		<!--for demo wrap-->
		<h1>Record</h1>
		<div class="tbl-header">
			<table cellpadding="0" cellspacing="0" border="0">
				<thead>
					<tr>
						<th>日期</th>
						<th>收/支</th>
						<th>金額</th>
						<th>現金/銀行帳戶</th>
						<th>類別</th>
						<th>備註</th>
						<th>刪除</th>
					</tr>
				</thead>
			</table>
		</div>
		<div class="tbl-content">

			<table cellpadding="0" cellspacing="0" border="0">
				<tbody>

					<% List<Accounting> allRecord = (List<Accounting>) request.getAttribute("allRecord");
							for(Accounting record : allRecord) {
							%>

							<tr>


								<td>
									<%= record.getDate() %>
								</td>

								<% if(record.getType()==true) {%>
									<td>收</td>
									<% }else { %>
										<td>支</td>
										<% } %>

											<td>
												<%= record.getAmount() %>
											</td>
											<td>
												<%= record.getAccounttype() %>
											</td>
											<td>
												<%= record.getCategory() %>
											</td>
											<td>
												<%= record.getNotes() %>
											</td>
											<td>
												<form method='post' action='del_record'>
													<input type='hidden' name='id'
														value='<%= record.getId()  %>'>
													<button type='submit'>刪除</button>
												</form>
											</td>
							</tr>
							<% } %>
				</tbody>
			</table>
		</div>
	</section>
	<div style="text-align: center">
		<a class='hre' href='/Accounting/main' style="text-decoration: none; color: #FFFFFF;">回首頁</a>
	</div>
</body>

</html>