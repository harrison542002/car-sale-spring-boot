<%@ include file="init.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://cdn.tailwindcss.com"></script>
<link rel="stylesheet" href="css/home.css">
<script src="https://kit.fontawesome.com/dfffc10f23.js"
	crossorigin="anonymous"></script>
<title>Home | ABC Cars</title>
</head>
<body class="bg-stone-200">
	<%@include file="nav.jsp" %>
	<main>
		<form action="<c:url value='/cars/searchCars'/>" method="get">
			<div
				class="car-sale bg-cover bg-center h-96 min-h-full flex justify-center">
				<div
					class=" rounded-lg bg-white w-[60rem] h-20 mt-40 backdrop-brightness-50 
            bg-white/30 flex  text-2xl">
					<input type="text" class="w-full h-full rounded-l-lg p-8"
						name="searchQuery"
						placeholder="Search By Car Model, Make, Price ...">
					<input type="hidden" style="display: none;" name="${_csrf.parameterName}" 
					value="${_csrf.token}" />
					<button
						class="w-40 bg-gradient-to-r from-violet-500 to-fuchsia-500 rounded-r-lg"
						type="submit">
						<h1 class="text-gray-50">Search</h1>
					</button>
				</div>
			</div>
		</form>
		<div class="grid grid-cols-2 mt-5">
			<div>
				<img class="p-2" src="img/show-car.png" alt="car">
			</div>
			<div class="container mx-auto">
				<div class="m-8">
					<h1 class=" text-2xl font-bold">Earn Your Dream Car Now</h1>
					<p class="mt-2 mb-4 w-30">ABC Cars provide various type of cars
						for you to purchase. Moreover, portal allows you to sell your car
						with productive advertising!</p>
					<c:if test="${!isAuthenticated }">
						<button
							class="bg-gradient-to-r from-violet-500 to-fuchsia-500 w-40 rounded-lg
                    hover:scale-125 ease-in-out duration-300">
							<a href="<c:url value="/registration" />">
								<p class="text-center p-2 font-semibold text-neutral-50">Register
									Now</p>
							</a>
						</button>
					</c:if>
				</div>
			</div>
		</div>
	</main>
	<%@include file="footer.jsp" %>
</body>
</html>