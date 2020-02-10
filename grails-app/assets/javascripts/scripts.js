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


$(document).ready(function() {
	
    $('.ls').one('click', function() {
    	var ID = $(this).attr('id');	
        $.ajax({
            url:"/dir/listSubDirs/",
            data: {id: ID},
            success: function(resp) {
            	// alert(resp);
                $('.dir-list-item[id=' + ID + ']').append(resp);
            },
            error: function(request, status, error) {
                alert(error);
            },

        });

    });
    $('.ls').on('click', function(){
    	var ID = $(this).attr('id');
    	$('.dir-list-item[id=' + ID + '] > ul').toggle();
    });
    
});