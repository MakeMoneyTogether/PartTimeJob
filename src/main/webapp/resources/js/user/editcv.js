function update(){
	phone = $.cookie('phone');
	pwd = $.cookie('password');
	$.ajax({
		type:'POST',
		url: 'user/editcv',
		dataType:'json',
		data:{phone:phone,password:pwd},
		success: function(data){
			$.alert('简历更新成功',function(){
				window.location.href="userp/me";
			});
			
		}
	});
}
function getcv(){
	phone = $.cookie('phone');
	pwd = $.cookie('password');
	$.ajax({
		type:'POST',
		url: 'user/me',
		dataType:'json',
		data:{phone:phone,pwd:pwd},
		success: function(data){
			$('#name').val(data.name);
			$('#school').val(data.school);
		}
	});
}