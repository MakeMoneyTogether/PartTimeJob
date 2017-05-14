function register(){
	if($('#password').val() != $('#repassword').val()){
		$.alert('两次密码要一致');
		return;
	}
	if($('#code').val().length < 6){
		$.alert('请先获取验证码');
		return;
	}
	commit();
}
function commit(){
	phone= $('#phone').val();
	pwd = $('#password').val()
	phone = $('#phone').val();
	invitation = $('#invitation').val();
	code = $('#code').val();
	$.ajax({
		type:'POST',
		url: 'uurl/register',
		dataType:'json',
		data:{phone:phone,pwd:pwd,phone:phone,invitation:invitation,code:code},
		success: function(data){
			console.log(data);
			if(data == 0){
				$.cookie('phone',phone,{expires:30,path:'/'});
				$.cookie('password',pwd,{expires:30,path:'/'});
					$.alert('注册成功',function(){
						window.location.href='user/me';
					});
			}
		}
	});
}
function get_code(){
	if($('#code_btn').html() == '已发送'){
		return;
	}
	var phone = $('#phone').val();
	if(!phone.match(/^(1\d{10})$/)){
		$.alert('手机号不正确');
		return;
	}
	$('#code_btn').html('已发送');
	$.ajax({
		type:'POST',
		url: 'util/sendCode',
		dataType:'json',
		data:{phone:phone},
		success: function(data){
			console.log(data);
			if(data == 0){
				$.notification({
					text: '【兼职平台】您的兼职平台手机验证码为：320100<br>打死也不要告诉别人。',
					onClick: function(data) {
						$.alert('吓你呢，这只是个测试。');
					},
				});
			}else if(data == 1){
				$.alert('手机号已被注册');
				$('#code_btn').html('获取');
			}else{
				$.alert('验证码发送失败');
				$('#code_btn').html('获取');
			}
		}
	});
}