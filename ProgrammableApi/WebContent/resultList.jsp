<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="validatepasswords.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Programmable Api</title>
<style>
.center {
	margin: auto;
	width: 80%;
	border: 3px solid #00b3b3;
	padding: 10px;
	background-color: #ccffff;
}

.centerForm {
	margin: auto;
	width: 60%;
	padding: 10px;
}

.centerBody {
	margin: auto;
	width: 60%;
	padding: 10px;
	background-color: #ccffff;
}
</style>
</head>
<body id="body1" class="centerBody">
	<%
		HttpSession sess = request.getSession();
		HashMap<String, String> listOfApi = new HashMap<String, String>();
		listOfApi = (HashMap) sess.getAttribute("apis");
		String type = (String) sess.getAttribute("typeApiMashup");
	%>
	<div class="center" height="30%" id="header">
		<b align="center"><h1>
				<u>Programmable Apis/Mashup Search</u>
			</h1></b>
	</div>

	<b align="center"><h1>
			<u>Results for <%=type%>
			</u>
		</h1></b>
	<table class="center" height="150px" width="300px">

		<tr style="outline: thin solid red">
			<th><b style="font-size: 15px;">Api No</b></th>
			<th><b style="font-size: 15px;">Name</b></th>
			<th><b style="font-size: 15px;">URL</b></th>
		</tr>
		<%
			Iterator it = listOfApi.entrySet().iterator();
			int i = 0;
			while (it.hasNext()) {
				i++;
				Map.Entry pair = (Map.Entry) it.next();
		%>
		<tr style="outline: thin solid">
			<td><%=i%></td>
			<td><%=pair.getValue()%>
			<td><a href="<%=pair.getKey()%>"><%=pair.getKey()%></a></a></td>
		</tr>
		<%
			}
		%>
	</table>
	</div>

</body>
</html>