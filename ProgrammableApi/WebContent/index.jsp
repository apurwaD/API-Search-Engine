<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="validation.js"></script>
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
	<div class="center" height="30%" id="header">
		<b align="center"><h1>
				<u>Programmable Apis/Mashup Search</u>
			</h1></b>
	</div>
	<div class="centerForm" height="70%">

		<form name="myForm" action="SearchApiMashup"
			onsubmit="return validate()" method="post">
			<b align="center"><h3>
					<u>Search By Criteria</u>
				</h3></b>
			<div align="center" height="70%">
				<table class="center" height="250px" width="500">

					<tr border="1px">
						<td width="250"><b style="font-size: 15px;">Select Search</b></td>
						<td width="250"><select name="typeOfSearch" id="searchType"
							onchange="onSelect();" onfocus="this.selectedIndex = -1;">
								<option value="select">Select</option>
								<option value="apis">Api</option>
								<option value="mashup">Mashup</option>
						</select></td>
					</tr>

					<tr>
						<td><b style="font-size: 15px;">Select Search Criteria</b></td>
					<tr>
						<td><input type="radio" name="criteria" value="updatedyear"
							checked="checked"> Updated Year<br></td>
					</tr>
					<tr>
						<td><input type="radio" name="criteria" value="tags" id="typecriteria">Tags<br></td>
					</tr>
					<tbody id="api" style="display: none;">
						<tr>
							<td colspan="2"><input type="radio" name="criteria"
								value="protocols" > Protocols</td>
						</tr>
						<tr>
							<td colspan="2"><input type="radio" name="criteria"
								value="category"> Category</td>
						</tr>
						<tr>
							<td><input type="radio" name="criteria" value="rating">Rating:
							</td>
							<td><select name="ratingvalue">
									<option value="less">Less Than</option>
									<option value="equal">Equal to</option>
									<option value="great">Greater Than</option>
							</select></td>
						</tr>
					</tbody>
					<tbody id="mashup" style="display: none;">
						<tr>
							<td colspan="2"><input type="radio" name="criteria"
								value="usedapis"> Used Apis</td>
						</tr>
					</tbody>


					</tr>
					<tr>
						<td><b style="font-size: 15px;">Enter Value to Search</b></td>
						<td><input type="text" name="searchvalue"></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Submit"></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<div class="centerForm" height="70%">

		<form name="myForm2" action="SearchApiMashup" onsubmit="return validate()"
			method="post">
			<b align="center"><h3>
					<u>Search By Keyword</u>
				</h3></b>
			<div align="center" height="70%">
				<table class="center" height="150px" width="500px">

					<tr>
						<td><b style="font-size: 15px;">Select Search</b></td>
						<td><select name="typeOfSearch">
								<option value="apis">API</option>
								<option value="mashup">Mashup</option>
						</select></td>
					</tr>
					<td><b style="font-size: 15px;">Enter Keyword to Search</b></td>
					<td><input type="text" name="searchvalue"></td>
					</tr>

					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Search"></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<div id="two" style="width: 50%; float: left"></div>
</body>
</html>