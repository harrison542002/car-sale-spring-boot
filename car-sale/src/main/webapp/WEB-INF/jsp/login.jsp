<%@ include file="init.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://cdn.tailwindcss.com"></script>
<script src="https://kit.fontawesome.com/dfffc10f23.js"
	crossorigin="anonymous"></script>
<title>Login | ABC Cars</title>
</head>
<body class="bg-stone-200">
	<div class="flex justify-center mt-20">
		<div class="w-full max-w-xs">
			<c:if test="${not empty param.error}">
				<p class="text-red-500 text-xl m-3 text-center">Incorrect
					Credentials, please re correct and enter
				</p>
			</c:if>
			<form class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4"
				action="/login" method="post">
				<div class="mb-4">
					<label class="block text-gray-700 text-sm font-bold mb-2"
						for="username"> Username </label> <input
						class="shadow appearance-none border rounded w-full 
                py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline
                <c:if test="${not empty param.error}">
                	border-red-500
                </c:if>"
						id="username" type="text" name="username" placeholder="Username">
				</div>
				<div class="mb-6">
					<label class="block text-gray-700 text-sm font-bold mb-2"
						for="password"> Password </label> <input
						class="shadow appearance-none border rounded w-full py-2 
                px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline
                <c:if test="${not empty param.error}">
                	border-red-500
                </c:if>"
						id="password" type="password" placeholder="******************"
						name="password">
				</div>
				<input type="hidden" style="display: none;"
					name="${_csrf.parameterName}" value="${_csrf.token}" />
				<div class="flex items-center justify-between">
					<button
						class="bg-indigo-500 hover:bg-indigo-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
						type="submit">Sign In</button>
				</div>
			</form>
			<p class="text-center text-gray-500 text-xs">&copy;ABC Cars. All
				rights reserved.</p>
		</div>
	</div>
	<%@include file="footer.jsp" %>
</body>
</html>