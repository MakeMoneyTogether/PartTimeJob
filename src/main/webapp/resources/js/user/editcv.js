function update(){
	name = $('#name').val();
	sex = $("input[name='sex']:checked").val();
	birthday = new Date($('#birthday').val()).getTime();
	school = $('#school').val();
	major = $('#major').val();
	$.ajax({
		type:'POST',
		url: 'user/editcv',
		dataType:'json',
		data:{name:name,gender:sex,lbirthday:birthday,school:school,major:major},
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
			if(data.gender == '男'){
				$('#man').attr('checked','true');
			}
			$('#birthday').val(data.birthday);
			$('#major').val(data.major);
		}
	});
}