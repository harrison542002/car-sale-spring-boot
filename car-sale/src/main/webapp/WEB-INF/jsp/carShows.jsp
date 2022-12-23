<%@include file="init.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://cdn.tailwindcss.com"></script>
<script src="https://kit.fontawesome.com/dfffc10f23.js"
	crossorigin="anonymous"></script>
<title>Car Show | ABC Car</title>
</head>
<body class="bg-stone-200">
	<%@include file="nav.jsp" %>
	<div class="mb-4 mt-4">
		<h1 class="mt-3 mb-3 text-center text-4xl font-semibold text-indigo-500">Car Show</h1>
		<div class="m-4">
			<form action="<c:url value='/cars/searchCars'/>" class="flex shadow-md" method="get">
				<input name="searchQuery" type="text"
					class="w-full border-b-4 border-indigo-500
					rounded-l-md p-3 lg:text-xl"
					placeholder="Enter make, model, year for filtering"> <input
					type="hidden" style="display: none;" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<button class="p-4 bg-indigo-500 text-gray-50 inline-block rounded-r-md" type="submit">
					<i class="fa-solid fa-filter"></i>
				</button>
			</form>
		</div>
	</div>
	<c:if test="${not empty error}">
		<p class="m-5 text-neutral-500 text-xl">${error}</p>
		<div class="flex justify-center">
			<img class="block" src="<c:url value='/img/notFound.jpg'/>" alt="car">
		</div>
	</c:if>
	<c:if test="${not empty searchQuery}">
		<p class="m-5 text-neutral-500 text-xl">
			Showing Results For : "${searchQuery}"
		</p>
	</c:if>
	<c:if test="${not empty cars}">
		<div class="lg:grid grid-cols-3 gap-4 col-span-2 m-4">
			<c:forEach items="${cars}" var="car" varStatus="status">
				<div class="border border-gray-400 rounded-md shadow-md">
					<div class="m-5">
						<div>
							<img class="container max-h-52 rounded-md hover:scale-110 ease-in-out duration-300"
								src="data:image/jpg;base64,${imageSource.get(status.index)}"
								alt="car">
						</div>
						<div>
							<h1 class="text-blue-700 text-xl font-semibold mb-3 mt-3">
								${car.model}</h1>
							<div class="grid grid-cols-2 gap-2">
								<div>
									<p>Registration Year</p>
									<p>Color</p>
									<p>Make</p>
									<p>Mileage</p>
									<p>Engine Power</p>
									<p>License</p>
									<p>Fuel Type</p>
								</div>
								<div>
									<p>: ${car.registration_year }</p>
									<p>: ${car.color}</p>
									<p>: ${car.make }</p>
									<p>: ${car.mileage} km</p>
									<p>: ${car.enginePower} CC</p>
									<p>: ${car.license ? 'has' : 'none'}</p>
									<p>: ${car.fuelType}
								</div>
							</div>
						</div>
					</div>
					<button
						class="bg-indigo-500 p-3 w-full 
                    text-slate-50 font-semibold hover:bg-indigo-700 rounded-br-md rounded-bl-md">
						<a href="<c:url value='/cars/getCar/${car.car_id}'/>"> View
							Detail </a>
					</button>
				</div>
			</c:forEach>
		</div>
		<div class="flex justify-center m-10">
			<button
				class="w-30 bg-indigo-500 font-semibold
        drop-shadow-xl p-4 rounded-lg text-gray-50 hover:bg-indigo-700">
				See More <i
					class="fa-solid fa-arrow-down hover:scale-125 ease-in-out duration-300"></i>
			</button>
		</div>
	</c:if>
	<%@include file="footer.jsp" %>
</body>
</html>