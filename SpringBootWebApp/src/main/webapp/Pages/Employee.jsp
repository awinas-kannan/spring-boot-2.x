<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Form</title>

<!-- http://localhost:9090/Pages/Employee.jsp -->

<!-- Access to XMLHttpRequest at
'http://localhost:9090/saveEmployee' from origin 'http://localhost:8080'
has been blocked by CORS policy: Response to preflight request doesn't
pass access control check: It does not have HTTP ok status. -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("#button").click(function() {
			var productmodel = {
				name : $('#name1').val(),
				age : null,
				tech : $('#tech1').val()
			};
			var requestJSON = JSON.stringify(productmodel);
			$.ajax({
				type : "POST",
				url : "http://localhost:9090/saveEmployee", //https://localhost:443/saveEmployee
				headers : {
					"Content-Type" : "application/json"
				},
				data : requestJSON,
				success : function(data) {
					console.log(data);
					alert(data.name + " is created");
				},
				error : function(data) {
				}
			});
		});
	});
</script>
</head>
<body>

	<form action="addEmployee">
		Name <input type="text" name="name"><br> Tech <input
			type="text" name="tech"><br> <input type="submit">
	</form>
	<form>
		Name <input type="text" id="name1"><br> Tech <input
			type="text" id="tech1"><br> <input type="button"
			id="button" value="save employee">
	</form>


</body>
</html>