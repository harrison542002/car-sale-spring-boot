<%@include file="init.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://kit.fontawesome.com/dfffc10f23.js"
	crossorigin="anonymous"></script>
    <title>Create Profile | ABC Cars</title>
</head>
 <body class="bg-stone-200">
    <h1 class="text-center m-5  text-3xl font-semibold text-indigo-500">
        Create Profile
    </h1>
    <div class="flex justify-center mt-2">
        <div class="w-full max-w-xs">
            <form:form class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4" 
            action="profile" method="post" modelAttribute="profile">
                <div class="flex mb-4">
                    <div>
                        <form:label class="block text-gray-700 text-sm font-bold mb-2" for="firstName" path="firstName">
                          First Name
                        </form:label>
                        <form:input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" 
                        id="firstName" type="text"  placeholder="First Name" path="firstName"/>
                      </div>
                      <div>
                        <form:label class="block text-gray-700 text-sm font-bold mb-2" for="lastName"
                        path="">
                          Last Name
                        </form:label>
                        <form:input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 
                        leading-tight focus:outline-none focus:shadow-outline ml-1" 
                        id="lastName" type="text"  path="lastName" placeholder="Last Name"/>
                      </div>
                </div>
              
              <div class="mb-6">
                <form:label class="block text-gray-700 text-sm font-bold mb-2" for="gender" path="gender">
                  Gender
                </form:label>
                <form:select class="shadow border rounded w-full py-2 px-3"
                    name="gender" id="gender" path="gender">
                        <form:option value="Male">Male</form:option>
                        <form:option value="FeMale">Female</form:option>
                </form:select>
              </div>
              <div class="mb-6">
                <form:label class="block text-gray-700 text-sm font-bold mb-2" for="location" path="location">
                  Location
                </form:label>
                <form:input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 
                        leading-tight focus:outline-none focus:shadow-outline" 
                        id="location" type="text" path="location" placeholder="Location"/>
              </div>
              <input type="hidden" style="display: none;" name="${_csrf.parameterName}" value="${_csrf.token}" />
              <div class="flex items-center justify-between">
                <button class="bg-indigo-500 hover:bg-indigo-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" 
                type="submit">
                    Create Profile
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