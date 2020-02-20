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
	mkDirAjaxCall();
	createFileAjaxCall();
	downloadAjaxCall();

});

function createFileAjaxCall() {

	$('ul').on('click', '.addFile', function() {
		var parentid = $(this).attr("id");

		$.ajax({
			url:"/uploadFile/create",
			data: {'dir.id': parentid},
			success: function(resp) {
				var form = $(resp).find('form[action="/uploadFile/save"]');
				$('.addFile[id=' + parentid + '] ~ div').append(form).dialog({
					modal: true,
					close: function() {
						form[0].reset();
						$('li[id=' + parentid + ']').append('<div></div>');

					}
				}, "open");
			},
			error: function(req, stat, err) {
				console.log(err);
			}				
		});
	});

}

function mkDirAjaxCall() {

	$('ul').on('click', '.mkdir', function() {
		var parentid = $(this).attr("id");

		$.ajax({
			url:"/dir/create",
			data: {'dir.id': parentid},
			success: function(resp) {
				var form = $(resp).find('form[action="/dir/save"]');
				$('.mkdir[id=' + parentid + '] ~ div').append(form).dialog({
					modal: true,
					close: function() {
						form[0].reset();
						$('li[id=' + parentid + ']').append('<div></div>');

					}
				}, "open");
			},
			error: function(req, stat, err) {
				console.log(err);
			} 
		});
	});
}

function downloadAjaxCall() {
	$('ul').on('click', '.download', function() {
		var parentid = $(this).attr("id");
		window.open("/uploadFile/download/"+ parentid +"", '_blank');
		// $.ajax({
		// 	url:"/uploadFile/download/" + parentid + "",
		// 	success: function(resp) {
		// 		console.log('yup');
		// 	},
		// 	error: function(req, stat, err) {
		// 		console.log(err);
		// 	}
		// });
	});
}


function lsAjaxCall() {

    $('ul').on('click','.ls', function() {
    		if ($(this).hasClass("dir-stop")) {
    			return false;
    		}
    		console.log(this)
    		$(this).addClass("dir-stop");
	    	var ID = $(this).attr('id');	
	        $.ajax({
	            url:"/dir/listSubDirs",
	            data: {id: ID},
	            success: function(resp) {
	            	// console.log(resp);
	                $('li[class*="dirs"][id=' + ID + ']').append(resp);
	                $(this).removeClass("dir-stop");
	            },
	            error: function(request, status, error) {
	                console.log(error);
	            }
	        });
			if ($(this).hasClass("itm-stop")) {
    			return false;
    		}
    		$(this).addClass("itm-stop");
	    	var ID = $(this).attr('id');	
	        $.ajax({
	            url:"/dir/listItems",
	            data: {id: ID},
	            success: function(resp) {
	            	// console.log(resp);
	                $('ul[class*="dirs"] > [id=' + ID + ']').append(resp);
	                $(this).removeClass("itm-stop");
	            },
	            error: function(request, status, error) {
	                console.log(error);
	            }
	        });
	    });
    
}

function DirToggler() {
    
    $('ul').on('click','.ls', function(){
    	var ID = $(this).attr('id');
    	$('ul > [id=' + ID + '] > ul').toggle();
    });
}