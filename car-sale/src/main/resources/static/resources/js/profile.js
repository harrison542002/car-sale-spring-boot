let secrete;
let secreteName;
let profileId;
var token;
var header;
$(document).ready(function () {
	token = $("meta[name='_csrf']").attr("content");
	header = $("meta[name='_csrf_header']").attr("content");
	secreteName = document.getElementById("secrete").name;
	secrete = document.getElementById("secrete").value;
	profileId = $("#profile").data("profileid");
    $(".lnav").click(function () {
        $(".lnav").removeClass("border-gray-500 shadow-md rounded-full text-indigo-500");
        $(this).addClass("border-gray-500 shadow-md rounded-full text-indigo-500");
    })
    $("#editProfile").click(changeInterface);
})

function changeInterface() {
    let location = $("#location").text().trim();
    let name = $("#name").text();
    console.log(name);
    let firstName = $("#firstNamee").text();
    let secondName = $("#lastNamee").text();
    let gender = $("#gender").text().trim();
    let url = "/profile/edit/" + profileId;
    $(".profile-info").html(`<form class="pt-6 pb-8 mb-4" id="editProfiles" action="${url}" method="post">
    	    <div class="flex mb-4">
            <div>
                <label class="block text-gray-700 text-sm font-bold mb-2" for="firstName">
                  First Name
                </label>
                <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" 
                id="firstName" type="text" name="firstName" value="${firstName}" placeholder="First Name">
              </div>
              <div>
                <label class="block text-gray-700 text-sm font-bold mb-2" for="lastName">
                  Last Name
                </label>
                <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 
                leading-tight focus:outline-none focus:shadow-outline ml-1" 
                id="lastName" type="text" value="${secondName}" name="lastName" placeholder="Last Name">
              </div>
        </div>
      
      <div class="mb-6">
        <label class="block text-gray-700 text-sm font-bold mb-2" for="gender">
          Gender
        </label>
        <select class="shadow border rounded w-full py-2 px-3"
            name="gender" id="gender">
                <option value="Male" ${(gender == "Male") ? "selected" : ""}>Male</option>
                <option value="Female" ${(gender == "Female") ? "selected" : ""}>Female</option>
        </select>
      </div>
      <div class="mb-6">
        <label class="block text-gray-700 text-sm font-bold mb-2" for="location">
          Location
        </label>
        <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 
                leading-tight focus:outline-none focus:shadow-outline" name="location"
                id="location" type="text"  placeholder="Location" value="${location}">
      </div>
      <div class="flex items-center">
        <button class="bg-indigo-500 hover:bg-indigo-700 text-white font-bold py-2 px-4 
        rounded focus:outline-none focus:shadow-outline mr-3" 
        type="submit">
            Save Changes
        </button>
        <button class="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" 
        type="button" id="cancel">
            Cancel
        </button>
      </div>
    </form>`);
    
    let form = document.getElementById("editProfiles");
    
    $("#editProfiles").submit(function(event){
    	  event.preventDefault();
    	  firstName = form.firstName.value;
    	  secondName = form.lastName.value;
    	  gender = form.gender.value;
    	  location = form.location.value;
    	  console.log(firstName);
    	  console.log(secondName);
    	  $.ajax({
    		  type : "POST",
    		  beforeSend : function(request){
    			  request.setRequestHeader(header, token);
    		  },
    		  url : url,
    		  data : `firstName=${firstName}&lastName=${secondName}&gender=${gender}&location=${location}`,
        	  success : function(data){
        		  displayProfile(firstName, secondName, location, gender);
        	  }
    	  })
    })
    
    $("#cancel").click(function (e) {
    	displayProfile(firstName, secondName, location, gender);
    })
}

function displayProfile(firstName, secondName, location, gender){
	$(".profile-info").html(`<div class="mt-5">
		    <p class="text-xl font-bold">
		    <span id="firstNamee">${firstName} </span> <span
							id="lastNamee">${secondName}</span>
			</p>
		    <p id="location">
		        ${location}
		    </p>
		    <p id="gender">
		        ${gender}
		    </p>
		</div>
		<div class="mt-3">
		    <button class="bg-gray-900 text-gray-100 rounded-lg p-3 w-full shadow-lg 
		    hover:bg-black text-gray-300" id="editProfile">
		        Edit Profile
		    </button>
		</div>`)
		$("#editProfile").click(changeInterface);
}