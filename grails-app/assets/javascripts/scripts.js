// jojojo
// var toggler = document.getElementsByClassName("caret");
// var i;

// for (i = 0; i < toggler.length; i++) {
//   toggler[i].addEventListener("click", function() {
//     this.parentElement.querySelector(".nested").classList.toggle("active");
//     this.classList.toggle("caret-down");
//   });
// }

function msg() {
	alert('Seems to work.');
}


// $(document).ready(function() {
	
//     $('.ls').one('click', function() {
//     	var ID = $(this).attr('id');	
//         $.ajax({
//             url:"/dir/listSubDirs/",
//             data: {id: ID},
//             success: function(resp) {
//             	console.log(resp);
//                 $('ul > [id=' + ID + ']').append(resp);
//             },
//             error: function(request, status, error) {
//                 alert(error);
//             },

//         });

//     });
//     $('.ls').on('click', function(){
//     	var ID = $(this).attr('id');
//     	$('ul > [id=' + ID + '] > ul').toggle();
//     });
    
// });

$(document).ready(function() {
	lsAjaxCall();
	DirToggler();
});

function lsAjaxCall() {

    $('ul').on('click','.ls', function() {
    		if ($(this).hasClass("stop")) {
    			return false;
    		}
    		$(this).addClass("stop");
	    	ID = $(this).attr('id');	
	        $.ajax({
	            url:"/dir/listSubDirs/",
	            data: {id: ID},
	            success: function(resp) {
	            	console.log(resp);
	                $('ul > [id=' + ID + ']').append(resp);
	                $(this).removeClass("stop");
	            },
	            error: function(request, status, error) {
	                console.log(error);
	            },
	            complete: function() {
	            	$('ul > [id=' + ID + ']');
	            }
	        });
	        // $('ul').off('click', '.ls');
	    });
    
}

function DirToggler() {
    
    $('ul').on('click','.ls', function(){
    	var ID = $(this).attr('id');
    	// console.log('hit')
    	$('ul > [id=' + ID + '] > ul').toggle();
    });
}