function register(){
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
		url: 'user/register',
		dataType:'json',
		data:{phone:phone,pwd:pwd,phone:phone,invitation:invitation,code:code},
		success: function(data){
			console.log(data);
			if(data == 0){
				$.cookie('phone',phone,{expires:30,path:'/'});
				$.cookie('password',pwd,{expires:30,path:'/'});
					$.alert('注册成功',function(){
						window.location.href='userp/me';
					});
			}else if(data == 1){
				$.alert('验证码不正确');
				$('#code_btn').text('获取');
			}else{
				$.alert('注册失败请稍后再试');
				$('#code_btn').text('获取');
			}
		}
	});
}
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
		url: 'util/sendCode',
		dataType:'json',
		data:{phone:phone},
		success: function(data){
			console.log(data);
			if(data == 0){
				$.notification({
					text: '验证码发送成功'
				});
			}else if(data == 1){
				$.alert('手机号已被注册');
				$('#code_btn').text('获取');
			}else{
				$.alert('验证码发送失败');
				$('#code_btn').text('获取');
			}
		}
	});
}