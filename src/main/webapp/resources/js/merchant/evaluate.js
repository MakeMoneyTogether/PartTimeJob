
$(function(){
	$('.fa-star').on('click',selectStar);
});

function selectStar(){
	$(this).addClass('star');
	value = $(this).attr('value');
	$(this).parent().attr('value',value);
	$(this).nextAll().removeClass('star');
	$(this).prevAll().addClass('star');
}