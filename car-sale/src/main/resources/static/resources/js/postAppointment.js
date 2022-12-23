const host = window.location.hostname;
var token;
var header;
$(document).ready(function(){
    token = $("meta[name='_csrf']").attr("content");
	header = $("meta[name='_csrf_header']").attr("content");
    
	$("#backToPrevious").click(function(){
		history.back();
	})
	
    $("#apForm").submit(function(event){
        event.preventDefault();
        let url = $(this).attr("action");

        $.ajax({
            type : "POST",
            beforeSend : function(request){
                request.setRequestHeader(header, token);
            },
            url : url,
            data : {
                purpose : this.purpose.value,
                biddingPrice : this.biddingPrice.value
            },
            success : function(data){
            	$("#bookingForm").html(`
                        <h1 class="font-bold text-3xl text-indigo-700">Booking has been placed</h1>
                        <div class="p-3">
                            <p class="text-xl font-semibold">
                                Booking with purpose "${data.purpose}" and bidding price - ${data.biddingPrice}, has
                                been placed.
                            </p>
                        </div>
                        `)
            }
        })
    })
})