function update(){
	name = $('#name').val();
	sex = $("input[name='sex']:checked").val();
	birthday = new Date($('#birthday').val()).getTime();
	school = $('#school').val();
	$.ajax({
		type:'POST',
		url: 'user/editcv',
		dataType:'json',
		data:{name:name,gender:sex,lbirthday:birthday,school:school},
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