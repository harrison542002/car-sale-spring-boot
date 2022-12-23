<%@ include file="init.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://kit.fontawesome.com/dfffc10f23.js"
	crossorigin="anonymous"></script>
    <title>Registration | ABC Cars</title>
</head>
<body class="bg-stone-200">
    <div class="flex justify-center mt-20">
        <div class="w-full max-w-xs">
            <form:form class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4" action="/registration" 
            method="POST" modelAttribute="user" >
            <p class="text-red-500 text-xs font-bold my-4">${notFound}
            <c:if test="${not empty error}">
       			Please input all required data.
       		</c:if>
            </p>
            
              <div class="mb-4">
                <form:label class="block text-gray-700 text-sm font-bold mb-2" for="username" path="username">
                  Username
                </form:label>
                <c:if test="${not empty error}">
                	<p class="text-red-500">*</p>
                </c:if>
                <form:input path="username" class="
                shadow appearance-none border
                rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none 
                focus:shadow-outline"
                id="username" type="text"  placeholder="Username" />
              </div>
              <div class="mb-4">
                <form:label class="block text-gray-700 text-sm font-bold mb-2" for="emailAddress" path="emailAddress">
                  Email Address
                </form:label>
                <c:if test="${not empty error}">
                	<p class="text-red-500">*</p>
                </c:if>
                <form:input class="shadow appearance-none border 
                rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" 
                id="emailAddress" type="email"  placeholder="Email Address" path="emailAddress"/>
              </div>
              <div class="mb-6">
                <form:label class="block text-gray-700 text-sm font-bold mb-2" for="password" path="password">
                  Password
                </form:label>
                <c:if test="${not empty error}">
                	<p class="text-red-500">*</p>
                </c:if>
                <form:input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline" id="password" 
                type="password" placeholder="******************" path="password"/>
              </div>
              <input type="hidden" style="display: none;" name="${_csrf.parameterName}" value="${_csrf.token}" />
              <div class="flex items-center justify-between">
                <button class="bg-indigo-500 hover:bg-indigo-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" 
                type="submit">
                  Sign In
                </button>
              </div>
            </form:form>
            <p class="text-center text-gray-500 text-xs">
              &copy;ABC Cars. All rights reserved.
            </p>
          </div>
    </div>
    <%@include file="footer.jsp" %>
</body>
</html>