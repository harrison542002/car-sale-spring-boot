<%@include file="init.jsp" %>
<style>
    @import url('https://fonts.googleapis.com/css2?family=Roboto+Slab&display=swap');
    .header{
        font-family: 'Roboto Slab', serif;
    }
    .ferr{
        width: 11%;
    }
</style> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://kit.fontawesome.com/dfffc10f23.js"
	crossorigin="anonymous"></script>
    <title>Document</title>
</head>
<body>
	<%@include file="nav.jsp" %>
    <div class="car-sale bg-cover bg-center h-80 min-h-full flex justify-center
    bg-gradient-to-r from-violet-500 to-fuchsia-500 rounded-r-lg">
        <div class="rounded-lg w-[60rem] mt-20 justify-center header">
            <p class="text-center font-semibold text-5xl mb-4 text-amber-300">
                In Search Of Luxury
            </p>
            <p class="text-center font-bold text-5xl text-amber-100">
                Come To ABC
            </p>
        </div>
    </div>
    <div class="bg-gradient-to-r from-gray-200 to-gray-500">
        <div class="p-4">
            <p class="text-center text-5xl font-bold text-gray-100 header">
                Trust By
            </p>
        </div>
        <div class="lg:flex justify-center">
            <div class="p-10 font-bold justify-center">
            <img src="<c:url value='/img/800px-Mercedes-Logo.svg.png'/>"class="w-20" alt="">
            </div>
            <div class="p-10 font-bold justify-center">
            <img src="<c:url value='/img/Lamborghini_Logo.svg.png'/>" class="w-20" alt=""></div>
            <div class="p-10 font-bold ferr justify-center">
                <img src="<c:url value='/img/Ferrari-Logo.svg.png'/>" class="ferrari" alt="">
            </div>
        </div>
    </div>
    <div class="mt-4 mb-5">
        <h1 class="text-center text-4xl font-bold header p-4">
            Our Team Member
        </h1>
        <div class="lg:grid grid-cols-3">
            <div class="m-3 shadow-lg hover:scale-90 ease-in-out duration-300
            bg-gradient-to-r h-full from-violet-500 to-fuchsia-500 text-stone-200 rounded-b-lg 
            rounded-t-lg">
                <div>
                    <img src="<c:url value='/img/huan1.jpg'/>" alt="" class="rounded-t-lg">
                </div>
                <div class="
                ">
                    <div class="p-4">
                        <p class="text-xl font-bold mb-3">Kristen Selina</p>
                    <p>
                        Kristen Selina has been working on professional AI industry for about 10 years.
                        She has contributed masses amount of analysis algorithm to 
                        company for user to get best experience of searching the best car.
                    </p> 
                    </div>
                </div>
            </div>
            <div class="m-3 shadow-lg hover:scale-90 ease-in-out duration-300
            bg-gradient-to-r h-full from-violet-500 to-fuchsia-500 text-stone-200 rounded-b-lg
            rounded-t-lg">
                <div>
                    <img src="<c:url value='/img/huan2.jpg'/>" alt="" class="rounded-t-lg">
                </div>
                <div>
                    <div class="p-4">
                        <p class="text-xl font-bold mb-3">Cristina Ronaldo</p>
                    <p>
                       Ronaldo is the founder and CEO of ABC Cars company. In past years, he had sold for
                       about thousand of cars and get back priceless feedback. With past experience, ABC Cars 
                       was born.
                    </p> 
                    </div>
                </div>
            </div>
            <div class="m-3 shadow-lg hover:scale-90 ease-in-out duration-300
            bg-gradient-to-r h-full from-violet-500 to-fuchsia-500 text-stone-200 rounded-b-lg
            rounded-t-lg">
                <div>
                    <img src="<c:url value='/img/huan3.jpg'/>" alt="" class="rounded-t-lg">
                </div>
                <div class="">
                    <div class="p-4">
                        <p class="text-xl font-bold mb-3">Juipler Karki</p>
                    <p>
                        Juipler is a software engineer for building the web portal of ABC Cars.
                        He has hand on experiences of engineering for about decade. With his knowledge, Car portal 
                        can be accessed by everyone.
                    </p> 
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp"%>
</body>
</html>