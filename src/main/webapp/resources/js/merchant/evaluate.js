
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

function notfound(uid){
	var jid = $('#jid').html();
	$.ajax({
		type:'POST',
		url: 'mchnt/checkUser',
		dataType:'json',
		data:{userId:uid,jobId:jid,status:3},	//工作缺勤
		success: function(data){
			if(data == 0){
				$('#'+uid).parent().remove();
			}
		}
	});
}

function apply(){
	var jid = $('#jid').html();
	userlist = $('.rate');
	users = [];
	for(i=0;i<userlist.length;i++){
		users.push({uid:$(userlist[i]).attr('id'),grade:$(userlist[i]).attr('value')});
	}
	users = JSON.stringify(users);
	$.ajax({
		type:'POST',
		url: 'job/scoreUser',
		dataType:'json',
		data:{users:users,jobId:jid},
		success: function(data){
			if(data == 0){
				window.location.href='mchntp/list';
			}
		}
	});
}