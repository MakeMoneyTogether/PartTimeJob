function showInfo(uid){
	$.get('user/info/'+uid,function(data){
		$('#userName').html(data.name);
		$('#name').html(data.name);
		$('#gender').html(data.gender);
		$('#phone').html(data.phone);
		$('#major').html(data.major);
		$('#grade').html(data.grade);
		$('#school').html(data.school);
		$('#userInfo').modal('show');
	});
}