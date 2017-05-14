function update(){
	phone = $.cookie('phone');
	pwd = $.cookie('password');
	$.ajax({
		type:'POST',
		url: 'uurl/editcv',
		dataType:'json',
		data:{phone:phone,password:pwd},
		success: function(data){
			$.alert('简历更新成功',function(){
				window.location.href="user/me";
			});
			
		}
	});
}