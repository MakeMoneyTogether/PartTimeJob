function get_code(){
	if($('#code_btn').text() == '已发送'){
		return;
	}
	var phone = $('#phone').val();
	if(!phone.match(/^(1\d{10})$/)){
		$.alert('手机号不正确');
		return;
	}
	$('#code_btn').text('已发送');
	$.ajax({
		type:'POST',
		url: 'util/sendForgot',
		dataType:'json',
		data:{phone:phone},
		success: function(data){
			console.log(data);
			if(data == 0){
				$.notification({
					text: '验证码发送成功'
				});
			}else{
				$.alert('验证码发送失败');
				$('#code_btn').text('获取');
			}
		}
	});
}
function reinit(){
	if($('#password').val() != $('#repassword').val() ){
		$.alert('两次密码要一致');
		return;
	}
	if($('#password').val().length < 6){
		$.alert('密码过短！！');
		return;
	}
	if($('#code').val().length < 6){
		$.alert('请先获取验证码');
		return;
	}
	phone= $('#phone').val();
	pwd = $('#password').val()
	phone = $('#phone').val();
	code = $('#code').val();
	
	if(phone.length < 1 ||pwd.length < 1 ||code.length < 1){
		$.alert('请将信息填写完整再进行重置，谢谢');
		return;
	}
	
	$.ajax({
		type:'POST',
		url: 'user/forgot',
		dataType:'json',
		data:{phone:phone,pwd:pwd,code:code,name:name},
		success: function(data){
			console.log(data);
			if(data == 0){
				$.cookie('phone',phone,{expires:30,path:'/'});
				$.cookie('password',pwd,{expires:30,path:'/'});
					$.alert('重置成功',function(){
						window.location.href='userp/me';
					});
			}else if(data == 1){
				$.alert('验证码不正确');
				$('#code_btn').text('获取');
			}else{
				$.alert('重置失败请稍后再试');
				$('#code_btn').text('获取');
			}
		}
	});
}