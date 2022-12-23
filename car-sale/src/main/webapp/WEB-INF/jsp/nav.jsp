<%@include file="init.jsp"%>
<head>
<link rel="icon" type="image/x-icon" href="<c:url value='/img/logometa.png'/>">
</head>
<sec:authorize access="hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')"
	var="isAuthenticated">
</sec:authorize>
<nav class="sticky top-0 z-10 backdrop-blur bg-stone-300">
	<div class="max-w-5xl mx-auto px-4">
		<div class="flex items-center justify-between h-20">
			<div class="">
                <img src="<c:url value='/img/logo.png'/>" class="block w-20" alt="logo">
            </div>
			<div class="flex space-x-4 text-neutral-900">
				<div class="m-3">
					<a href="<c:url value="/home"/>" class="hover:text-stone-500 m-1">Home</a>
					<a href="<c:url value="/cars/getAllCars/0"/>"
						class="hover:text-stone-500 m-1">View Cars</a> <a
						href="<c:url value="/home"/>" class="hover:text-stone-500 m-1">
						Search
						</a>
				</div>
				<c:if test="${!isAuthenticated}">
					<button
						class="bg-gradient-to-r from-violet-500 
              to-fuchsia-500 w-20 rounded-lg hover:scale-125 ease-in-out duration-300">
						<a href="<c:url value="/login"/>" class="">
							<p class="text-center p-3 font-semibold text-neutral-50">Log
								In</p>
						</a>
					</button>
				</c:if>
				<c:if test="${isAuthenticated}">
					<button
						class="bg-gray-600 w-20 rounded-lg hover:scale-125 
							text-stone-100 ease-in-out duration-300">
						<a href="<c:url value="/login?logout=true"/>" class="">
							<p class="text-center p-3 font-semibold text-neutral-50">Logout</p>
						</a>
					</button>
				</c:if>
				<c:if test="${isAuthenticated}">
				<div
					class="text-xl border border-gray-300 rounded-md p-3 hover:bg-gradient-to-r from-violet-500 
            to-fuchsia-500 hover:text-gray-100">
                    <a href="<c:url value="/profile"/>"> 
					<i class="fa-solid fa-user"></i>
					</a>
                </div>
			</c:if>
			</div>
		</div>
	</div>
	</div>
</nav>