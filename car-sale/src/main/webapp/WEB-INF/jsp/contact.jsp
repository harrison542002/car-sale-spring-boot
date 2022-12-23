<style>
    @import url('https://fonts.googleapis.com/css2?family=Roboto+Slab&display=swap');
    .header{
        font-family: 'Roboto Slab', serif;
    }
    .ferr{
        width: 11.5%;
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
    <title>Contact Us | ABC Cars</title>
</head>
<body class="bg-stone-200">
<%@include file="nav.jsp" %>
    <div class="text-4xl bg-gray-900 shadow-lg">
        <p class="p-5 font-bold header text-center text-amber-100">
            Let's Keep On Contact
        </p> 
    </div>
    <div class="lg:grid grid-cols-2">
        <div class="lg:border-r-4 border-indigo-500">
            <h1 class="p-5 text-3xl font-bold header text-center">
                Contact Information
            </h1>
            <div class="p-5 flex justify-center">
                <div class="flex">
                    <div class="w-50 font-semibold text-xl">
                        <p class="p-4"><i class="fa-solid 
                             fa-envelope text-indigo-500 hover:scale-150 ease-in-out duration-300"></i> Email</p>
                        <p class="p-4"><i class="fa-solid fa-phone
                            text-indigo-500 hover:scale-150 ease-in-out duration-300"></i> Hot-line</p>
                        <p class="p-4"><i class="fa-solid fa-location-dot
                            text-indigo-500 hover:scale-150 ease-in-out duration-300"></i> Location</p>
                    </div>
                    <div class="w-50 font-semibold text-xl text-indigo-500">
                        <p class="p-4">- abccarService@abc.com</p>
                        <p class="p-4">- +95 525252525</p>
                        <p class="p-4">- Mandalay , Myanmmar</p>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <h1 class="p-5 text-3xl font-bold header text-center">
                Send Feedback
            </h1>
            <div class="m-20 mt-0 mb-3">
                <form class="pt-6 pb-8 mb-4" id="editProfiles" action="${url}" method="post">
                    <div class="flex mb-4">
                    <div>
                        <label class="block text-gray-700 text-sm font-bold mb-2" for="firstName">
                          First Name
                        </label>
                        <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" 
                        id="firstName" type="text" value="" placeholder="First Name">
                      </div>
                      <div>
                        <label class="block text-gray-700 text-sm font-bold mb-2" for="lastName">
                          Last Name
                        </label>
                        <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 
                        leading-tight focus:outline-none focus:shadow-outline ml-1" 
                        id="lastName" type="text" value="" placeholder="Last Name">
                      </div>
                </div>
              
              <div class="mb-6">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="email">
                    Email Address
                  </label>
                  <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 
                  leading-tight focus:outline-none focus:shadow-outline ml-1" 
                  id="lastName" type="text" value="" placeholder="example@email.com">
              </div>
              <div class="mb-6">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="email">
                    Feedback
                  </label>
                  <textarea class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 
                  leading-tight focus:outline-none focus:shadow-outline ml-1" 
                  id="lastName" type="text" placeholder="Enter feedback ...">
                  </textarea>
              </div>
              <div class="flex items-center">
                <button class="bg-indigo-500 hover:bg-indigo-700 text-white font-bold py-2 px-4 
                rounded focus:outline-none focus:shadow-outline mr-3" 
                type="submit">
                    Send Feedback
                </button>
              </div>
            </form>
            </div>
        </div>
    </div>
    <div class="m-5 mt-0 border-4 border-indigo-500 rounded-lg">
        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d236853.89750310598!2d95.93573297609814!3d21.94061903446499!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x30cb6d23f0d27411%3A0x24146be01e4e5646!2sMandalay!5e0!3m2!1sen!2smm!4v1671765949876!5m2!1sen!2smm" 
        class="w-full rounded-lg" height="450" style="border:0;" allowfullscreen="" loading="lazy" 
        referrerpolicy="no-referrer-when-downgrade"></iframe>
    </div>
    <%@include file="footer.jsp" %>
</body>
</html>