$(document).ready(function() {
		token = $("meta[name='_csrf']").attr("content");
		header = $("meta[name='_csrf_header']").attr("content");
	$(".approve").each(function() {
		$(this).click(function() {
			modifyCar(this);
		});
	})

	$(".deny").each(function() {
		$(this).click(function() {
			modifyCar(this);
		});
	})

	$("#userInfo").click(function(){
    url = $("#userInfo").data("url");
    $(".info-head").text("User Info")
    $.ajax({
        type : "GET",
        beforeSend : function(request){
            request.setRequestHeader(header, token);
        },
        url : url,
        success : function(data){
            let table = `<table class="w-full text-sm text-left text-gray-500 dark:text-gray-400 mt-5">
                <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                    <tr>
                        <th scope="col" class="py-3 px-6">
                            User Name
                        </th>
                        <th scope="col" class="py-3 px-6">
                            Email Address
                        </th>
                        <th scope="col" class="py-3 px-6">
                            Authority
                        </th>
                        <th scope="col" class="py-3 px-6">
                            Action
                        </th>
                    </tr>
                </thead>
                <tbody class="text-gray-900">`
            data.forEach(element => {
                table +=`<tr>
                <th scope="row" class="py-4 px-6 font-medium whitespace-nowrap">
                    ${element.username}
                </th>
                <td class="py-4 px-6">
                    ${element.emailAddress}
                </td>
                <td class="py-4 px-6" id="auth${element.userId}">
                    ${element.status == "true" ? "ADMIN" : "USER"}
                </td>
                <td class="py-4 px-6">
                    <div class="">
                        <button class="p-2 bg-red-500 rounded-lg font-medium w-35
                        text-stone-200 hover:underline assign-auth 
                        hover:bg-red-800" id="user${element.userId}" data-status="${element.status}" data-userId="${element.userId}">
                        ${element.status == "true" ? "REMOVE AUTHORITY" : "ASSIGN AS ADMIN"}
                        </button>
                    </div>
                </td>
            </tr>`
            })

            table += "</tbody></table>";
            $(".carLists").html(table);
            $(".assign-auth").each(function(){
                $(this).click(function(){
                    let status = $(this).data("status");
                    let userId = $(this).data("userid");
                    let destination = `/assignAuthority`;
                    $.ajax({
                        type : "GET",
                        beforeSend : function(request){
                            request.setRequestHeader(header, token);
                        },
                        url : destination,
                        data : `status=${status}&userId=${userId}`,
                        success : function(data){
                            $(`#user${userId}`).data("status", data);
                            status = $(`#user${userId}`).data("status");
                            $(`#user${userId}`).text(
                                `${data == "true" ? "REMOVE AUTHORITY" : "ASSIGN AS ADMIN"}`
                            )
                            $(`#auth${userId}`).text(
                            		`${data == "true" ? "ADMIN" : "USER"}`)
                        }
                    })
                })
            })
        }
})
})
})

function assignAuthority(userIdd){
	console.log(userIdd);
	let status = $(`#user${userIdd}`).data("status");
    let userId = $(`#user${userIdd}`).data("userid");
    let destination = `/assignAuthority`;
    $.ajax({
        type : "GET",
        beforeSend : function(request){
            request.setRequestHeader(header, token);
        },
        url : destination,
        data : `status=${status}&userId=${userId}`,
        success : function(data){
            $(this).data("status", data);
            $(`#user${userId}`).text(
                `${data == "true" ? "REMOVE AUTHORITY" : "ASSIGN AS ADMIN"}`
            )
            $(`#auth${userId}`).text(
            		`${data == "true" ? "ADMIN" : "USER"}`)
        }
    })
}

function modifyCar(element) {
	url = $(element).data("url");
	car = $(element).data("car");
	$.ajax({
		type : "GET",
		beforeSend : function(request) {
			request.setRequestHeader(header, token);
		},
		url : url,
		success : function(data) {
			$(`#${car}`).remove();
			console.log(data)
		}
	})
}