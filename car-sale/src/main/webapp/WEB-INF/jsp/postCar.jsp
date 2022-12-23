<%@include file="init.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://kit.fontawesome.com/dfffc10f23.js" crossorigin="anonymous"></script>
    <title>Post Car | ABC Cars</title>
</head>

<body class="bg-stone-200">
    <h1 class="text-center m-5  text-3xl font-semibold text-indigo-500">
        Post New Car For Sell
    </h1>
    <div class="flex justify-center mt-2">
        <div class="w-96">
            <form:form class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4" 
            action="/cars/postingCar" method="post" enctype="multipart/form-data"
            modelAttribute="car">
                <div class="mb-4">
                    <form:label class="block text-gray-700 text-sm font-bold mb-2" for="make"
                    path="make">
                        Make
                    </form:label>
                    <form:input
                        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        id="make" type="text" path="make" name="make" placeholder="Make Of Car"/>
                </div>
                <div class="mb-4">
                    <form:label class="block text-gray-700 text-sm font-bold mb-2" for="model"
                    path="model">
                        Model
                    </form:label>
                    <form:input
                        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        id="model" name="model" path="model" type="text" placeholder="Model Of Car"/>
                </div>
                <div class="mb-4">
                    <form:label class="block text-gray-700 text-sm font-bold mb-2" for="regsitrationYear"
                    name="regsitrationYear" path="regsitrationYear">
                        Registration of Year (yr)
                    </form:label>
                    <form:input
                        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        id="regsitrationYear" name="regsitrationYear" path="regsitrationYear" type="number" 
                      	placeholder="Registration Year of Car"/>
                </div>
                <div class="mb-4">
                    <form:label class="block text-gray-700 text-sm font-bold mb-2" for="priceRange"
                    name="priceRange" path="priceRange">
                        Price Range
                    </form:label>
                    <form:input
                        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        id="priceRange" type="text" placeholder="Price Range of Car"
                        path="priceRange"/>
                </div>
                <div class="mb-4">
                    <form:label class="block text-gray-700 text-sm font-bold mb-2" for="color" path="color">
                        Color
                    </form:label>
                    <form:input
                        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        id="color" type="text" placeholder="Color of Car"
                        path="color"/>
                </div>
                <div class="mb-4">
                    <form:label class="block text-gray-700 text-sm font-bold mb-2" for="mileage" path="mileage">
                        Mileage (unit - km)
                    </form:label>
                    <form:input
                        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        id="mileage" type="number" placeholder="Mileage of Car with unit km"
                        path="mileage"/>
                </div>
                <div class="mb-4">
                    <form:label class="block text-gray-700 text-sm font-bold mb-2" for="enginePower" path="enginePower">
                        Engine Power (unit - CC)
                    </form:label>
                    <form:input
                        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        id="enginePower" type="number" placeholder="Engine Power of Car"
                        path="enginePower"/>
                </div>
                <div class="mb-4">
                    <form:label class="block text-gray-700 text-sm font-bold mb-2" for="transmission" path="transmission">
                        Transmission
                    </form:label>
                    <form:select class="shadow border rounded w-full py-2 px-3"
                    name="transmission" id="transmission"
                    path="transmission">
                        <form:option value="Manual">Manual</form:option>
                        <form:option value="Auto">Auto</form:option>
                    </form:select>
                </div>
                <div class="mb-4">
                    <form:label class="block text-gray-700 text-sm font-bold mb-2" 
                    for="license" path="license">
                        License
                    </form:label>
                    <form:select class="shadow border rounded w-full py-2 px-3"
                    name="license" id="license" path="license">
                        <form:option value="true">Yes</form:option>
                        <form:option value="false">No</form:option>
                    </form:select>
                </div>
                <div class="mb-4">
                    <form:label class="block text-gray-700 text-sm font-bold mb-2" 
                    for="fuelType" path="fuelType">
                        Fuel Type
                    </form:label>
                    <form:select class="shadow border rounded w-full py-2 px-3"
                    name="fuelType" id="fuelType" path="fuelType">
                        <form:option value="Petrol">Petrol</form:option>
                        <form:option value="Diesel">Diesel</form:option>
                    </form:select>
                </div>
                
                <div class="mb-6">
                    <label class="text-gray-700 text-sm font-bold">
                        <p class="mb-2">Choose Car Image</p>
                        <input type="file" name="file" class="text-sm text-grey-500
                        file:mr-5 file:py-2 file:px-6
                        file:rounded-lg file:border-0
                        file:text-sm file:font-medium
                        file:bg-indigo-500 file:text-white 
                        file:font-bold
                        hover:file:cursor-pointer hover:file:bg-indigo-700
                        hover:file:text-grey-700" />
                    </label>
                </div>
                <input type="hidden" style="display: none;" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <div>
                    <button
                        class="w-full bg-indigo-500 hover:bg-indigo-700 text-white font-bold py-2 
                        px-4 rounded focus:outline-none focus:shadow-outline"
                        type="submit">
                        Post Car
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