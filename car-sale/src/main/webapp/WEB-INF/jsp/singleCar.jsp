<%@include file="init.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<script src="https://cdn.tailwindcss.com"></script>
<script src="https://kit.fontawesome.com/dfffc10f23.js"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.2/jquery.min.js"
	integrity="sha512-tWHlutFnuG0C6nQRlpvrEhE4QpkG1nn2MOUMWmUeRePl4e3Aki0VB6W1v3oLjFtd0hVOtRQ9PHpSfN6u6/QXkQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<title>Car Details | ABC Cars</title>
</head>

<body class="bg-stone-200">
	<%@include file="nav.jsp" %>
	<div class="m-2 mt-2 text-xl font-semibold hover:text-indigo-500" id="backToPrevious">
		<i class="fa-solid fa-arrow-left-long hover:scale-120 ease-in-out duration-300"></i> Back To Car Lists
	</div>
	<div class="lg:grid grid-cols-3">
		<div class="m-3 col-span-2">
			<div
				class="mb-3 border-2 border-indigo-500 
            brightness-100 drop-shadow-lg rounded-md hover:scale-90 ease-in-out duration-300">
				<img src="data:image/jpg;base64,${carImage}" class="">
			</div>
			<div class="mb-3">
				<h1 class="text-4xl font-bold text-pink-600">$
					${car.price_range}</h1>
			</div>
			<div>
				<h1 class="text-3xl font-bold mb-3">Basic Information</h1>
				<div class="font-semibold">
					<div class="flex border-b py-2
                 justify-between">
						<p>Make</p>
						<p>${car.make }</p>
					</div>
					<div class="flex border-b py-2
                 justify-between">
						<p>Model</p>
						<p>${car.model}</p>
					</div>
					<div class="flex border-b py-2 justify-between">
						<p>Registration Year</p>
						<p>${car.registration_year }</p>
					</div>
					<div class="flex border-b py-2 justify-between">
						<p>Color</p>
						<p>${car.color }</p>
					</div>
					<div class="flex border-b py-2  justify-between">
						<p>Mileage</p>
						<p>${car.mileage}km</p>
					</div>
					<div class="flex border-b py-2  justify-between">
						<p>Engine Power</p>
						<p>${car.enginePower}CC</p>
					</div>
					<div class="flex border-b py-2  justify-between">
						<p>Transmission</p>
						<p>${car.transmission}</p>
					</div>
					<div class="flex border-b py-2  justify-between">
						<p>License</p>
						<p>
							<c:if test="${car.license}">
                    		Yes
                    	</c:if>
							<c:if test="${!car.license}">
                    		No
                    	</c:if>
						</p>
					</div>
					<div class="flex border-b py-2 justify-between">
						<p>Fuel Type</p>
						<p>${car.fuelType}</p>
					</div>
				</div>
			</div>
		</div>
		<div class="m-4 min-h-50">
			<div class="bg-slate-100 py-2 px-2 text-center" id="bookingForm">
			<c:if test="${empty appointment}">
				<h1 class="font-bold text-3xl text-indigo-700">Book Now</h1>
				<form:form class="w-full max-w-sm mt-10 mb-10"
					modelAttribute="booking" id="apForm"
					action="/transcation/booking/${car.car_id}" method="post">
					<div class="md:flex md:items-center mb-6">
						<div class="md:w-1/3">
							<form:label
								class="block text-gray-500 font-bold md:text-right mb-1 md:mb-0 pr-4"
								for="purpose" path="purpose">
                          Purpose
                        </form:label>
						</div>
						<div class="md:w-2/3">
							<form:input
								class="bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-4 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-indigo-700"
								name="purpose" id="purpose" type="text"
								placeholder="Purpose for Appointment" path="purpose" />
						</div>
					</div>
					<div class="md:flex md:items-center mb-6">
						<div class="md:w-1/3">
							<form:label
								class="block text-gray-500 font-bold md:text-right mb-1 md:mb-0 pr-4"
								for="biddingPrice" path="biddingPrice">
                          Bidding Price
                        </form:label>
						</div>
						<div class="md:w-2/3">
							<form:input
								class="bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-4 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-indigo-700"
								id="biddingPrice" type="number" name="biddingPrice"
								placeholder="Bidding Price" path="biddingPrice" />
						</div>
					</div>
					<input type="hidden" style="display: none;"
						name="${_csrf.parameterName}" value="${_csrf.token}" />
					<div class="md:flex md:items-center">
						<div class="md:w-1/3"></div>
						<div class="md:w-2/3">
							<button
								class="shadow bg-indigo-700 hover:bg-indigo-900 focus:shadow-outline 
                        focus:outline-none text-white font-bold py-2 px-4 rounded w-full"
								type="submit">Submit Booking</button>
						</div>
					</div>
				</form:form>
			</c:if>
			<c:if test="${not empty appointment}">
				<h1 class="font-bold text-3xl text-indigo-700">Booking has been placed</h1>
                        <div class="p-3">
                            <p class="text-xl font-semibold">
                                Booking with purpose "${appointment.purpose}" and bidding price - ${appointment.bidding_price}, has
                                been placed.
                            </p>
                        </div>
			</c:if>
			</div>
			<hr>
			<c:if test="${not empty rcar}">
				<div class="mt-10">
				<h1 class="font-bold text-3xl text-indigo-700">Related Cars</h1>
				<div class="mt-4">
					<div class="border rounded-md drop-shadow-xl">
						<div class="m-5">
							<div>
								<img class="container max-h-52" src="data:image/jpg;base64,${rcarimage}" alt="car">
							</div>
							<div>
								<h1 class="text-blue-700 text-xl font-semibold mb-3 mt-3">
									${rcar.model }</h1>
								<div class="grid grid-cols-2 gap-2">
									<div>
										<p>Registration Year</p>
										<p>Color</p>
										<p>Make</p>
										<p>Mileage</p>
									</div>
									<div>
										<p>: ${rcar.registration_year }</p>
										<p>: ${rcar.color }</p>
										<p>: ${rcar.make }</p>
										<p>: ${rcar.mileage} km</p>
									</div>
								</div>
							</div>
						</div>
						<button
							class="bg-indigo-500 p-3 w-full 
                            text-slate-50 font-semibold hover:bg-indigo-700 rounded-br-md rounded-bl-md">
							<a href="<c:url value='/cars/getCar/${rcar.car_id}'/>">View Detail</a>
						</button>
					</div>
				</div>
			</div>
			</c:if>
		</div>
	</div>
	<%@include file="footer.jsp" %>
	<script type="text/javascript" src="<c:url value='/js/postAppointment.js'/>"></script>
</body>
</html>