<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
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
<title>Profile | ABC Cars</title>
</head>
<body class="bg-stone-200">
	<%@include file="nav.jsp"%>
	<input type="hidden" id="secrete" style="display: none;"
		name="${_csrf.parameterName}" value="${_csrf.token}" />
	<div class="lg:grid grid-cols-5 mt-4 maindiv sm:block">
		<div class="p-4" id="profile" data-profileId="${profile.profile_id}">
			<div class="flex justify-center">
				<img class="rounded-full w-20 shadow-lg"
					src="<c:url value='/img/profile.jpeg'/>" 
					alt="profile image">
			</div>
			<div class="profile-info">
				<div class="mt-5 pl-2">
					<p class="text-xl font-bold">
						<span id="firstNamee">${profile.firstName}</span> <span
							id="lastNamee">${profile.lastName}</span>
					</p>
					<p id="location">${profile.location}</p>
					<p id="gender">${profile.gender}</p>
				</div>
				<div class="mt-3">
					<button
						class="bg-gray-900 text-gray-100 rounded-lg p-3 w-full shadow-lg 
                    hover:bg-black text-gray-300 font-bold"
						id="editProfile">Edit Profile</button>
				</div>
			</div>
			<div class="text-l text-center mt-2 lg:block sm:flex">
				<a
					class="lg:p-4 lg:block lg:border lnav sm:p-2 p-3
                <c:if test="${status == 'ACTIVATE'}" >
						border-gray-500 shadow-md rounded-full text-indigo-500
				</c:if>
				hover:text-indigo-500"
					href="/admin"> Review Approved Cars </a> <a
					class="lg:p-4 lg:border block lnav sm:p-2 p-3
                <c:if test="${status == 'PENDING'}" >
						border-gray-500 shadow-md rounded-full text-indigo-500
				</c:if>
                hover:text-indigo-500"
					href="/admin?status=PENDING"> Pending Cars </a> <a
					class="lg:p-4 lg:border block lnav sm:p-2 p-3
					hover:text-indigo-500" id="userInfo"
					data-url="<c:url value='/getUsers'/>"> User Info </a> <a
					class="lg:p-4 lg:border block lnav sm:p-2 p-3
                <c:if test="${status == 'BOOKING'}">
						border-gray-500 shadow-md rounded-full text-indigo-500
				</c:if>
                 hover:text-indigo-500"
                 href="/admin?status=BOOKING">
					Review Bookings </a>
			</div>
		</div>
		<div class="col-span-4 border-gray-900 border-l-4 rounded-md">
			<div
				class="flex justify-center text-stone-200 border-t rounded-tl-lg bg-gray-900">
				<p
					class="info-head bg-gray-900 text-2xl rounded-tl-lg p-4 hover:text-gray-700
					">
					<c:if test="${status == 'ACTIVATE'}">
						Approved Cars
					</c:if>
					<c:if test="${status == 'PENDING'}">
						Pending Cars
				</c:if>
					<c:if test="${status == 'BOOKING'}">
						Booking Status
				</c:if>
				</p>
			</div>
			<div class="carLists overflow-y-auto h-[30rem] rounded-bl-lg">
				<c:forEach items="${cars}" var="car" varStatus="state">
					<div class="p-3 hover:text-gray-500 hover:bg-gray-100 border-b-2 border-gray-300"
						id="car${car.car_id}">
						<div class="lg:grid grid-cols-2 carSale sm:block">
							<div class="mr-4">
								<img
									src="data:image/jpg;base64,${imageSources.get(state.index)}"
									class="h-40 rounded-lg">
							</div>
							<div>
								<div class="heading">
									<h1
										class="text-xl font-bold hover:underline hover:decoration-1">
										<a href="<c:url value='/cars/getCar/${car.car_id}'/>">
											${car.model} </a>
									</h1>
								</div>
								<div class="grid grid-cols-2 mt-4">
									<div class="subTitle font-bold">
										<p class="font-semibold">Price Range</p>
										<p class="font-semibold">Make</p>
										<p class="font-semibold">Registration Year</p>
										<p class="font-semibold">Mileage</p>
										<p class="font-semibold">Color</p>
									</div>
									<div>
										<p>${car.price_range}</p>
										<p>${car.make}</p>
										<p>${car.registration_year}</p>
										<p>${car.mileage}km</p>
										<p>${car.color}</p>
									</div>
								</div>
								<c:if test="${status == 'PENDING'}">
									<div class="text-gray-400">
										<i class="fa-solid fa-clock"></i> Pending
									</div>
									<div class="m-1 container">
										Activation : <i
											class="fa-regular fa-square-check text-2xl
            text-green-400 hover:text-green-700 approve"
											data-car="car${car.car_id}"
											data-url="/cars/modifyingCar?action=ACTIVATE&carId=${car.car_id}"></i>
										<i
											class="fa-solid fa-square-xmark text-2xl
            text-red-600 hover:text-red-800 deny"
											data-car="car${car.car_id}"
											data-url="/cars/modifyingCar?action=DEACTIVATE&carId=${car.car_id}"></i>
									</div>
								</c:if>
								<c:if test="${status == 'ACTIVATE'}">
									<div class="text-green-400">
										<i class="fa-solid fa-circle-check"></i> Approved
									</div>
									<div>
										<button
											class="mt-4 text-l rounded-lg p-3 
											text-stone-200 bg-red-500 hover:text-red-800 deny"
											data-car="car${car.car_id}"
											data-url="/cars/modifyingCar?action=DEACTIVATE&carId=${car.car_id}">
											Takedown</button>
									</div>
								</c:if>
								<c:if test="${status == 'BOOKING' }">
									<div class="mb-2 mt-2">
										<p>Booked User :
											${appointments.get(state.index).user.username}</p>
										<p>Email Address :
											${appointments.get(state.index).user.emailaddress}</p>
										<p>Purpose : ${appointments.get(state.index).purpose}</p>
										<p>Status : ${appointments.get(state.index).status}</p>
									</div>
									<div class="container">
										Approve Booking : <i
											class="fa-regular fa-square-check text-2xl
        text-green-400 hover:text-green-700 approve"
											data-car="car${car.car_id}"
											data-url="/transcation/processBooking/${appointments.get(state.index).appointment_id}?action=APPROVE"></i>
										<i
											class="fa-solid fa-square-xmark text-2xl
        text-red-600 hover:text-red-800 deny"
											data-car="car${car.car_id}"
											data-url="/transcation/processBooking/${appointments.get(state.index).appointment_id}?action=DENY"></i>
									</div>
								</c:if>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	<script src="<c:url value='/js/admin.js'/>"></script>
	<script src="<c:url value='/js/profile.js'/>"></script>
</body>

</html>