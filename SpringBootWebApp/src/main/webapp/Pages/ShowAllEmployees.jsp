<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1" />
<title>View Products</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script>
	$(document).ready(
			function() {
				$.getJSON("http://localhost:9090/getAllEmployee", function(
						result) {
					console.log(result);
					$.each(result, function(key, value) {
						console.log("Key " + key + " Value " + value);
						$("#employeesJson").append(
								value.id + " " + value.name + " " + value.tech
										+ " " + "<br/>");
					});
				});
			});
</script>
</head>

<body>
	<div id="employeesJson"></div>
</body>
</html>