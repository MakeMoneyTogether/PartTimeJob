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
	mchntAddress = $('#mchntAddress').val();
	mchntName = $('#mchntName').val();
	connName = $('#connName').val();
	pwd = $('#password').val();
	code = $('#code').val();
	
	

	if(!phone.match(/^(1\d{10})$/)){
		$.alert('手机号不正确');
		return;
	}
	if(pwd.length < 6){
		$.alert('密码过短！！');
		return;
	}
	if(mchntAddress == null || mchntAddress == '' ||mchntName == null || mchntName == '' ||connName == null || connName == ''){
		$.alert('请将信息填完整！！');
		return;
	}
	
	$.ajax({
		type:'POST',
		url: 'mchnt/register',
		dataType:'json',
		data:{phone:phone, mchntAddress:mchntAddress, mchntName:mchntName, connName:connName, connPhone:phone,password:pwd,code:code},
		success: function(data){
			console.log(data);
			if(data == 0){
				$.cookie('phone',phone,{expires:30,path:'/'});
				$.cookie('password',pwd,{expires:30,path:'/'});
					$.alert('注册成功',function(){
						window.location.href='mchnt';
					});
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
					text: '【兼职平台】您的兼职平台手机验证码为：320100<br>打死也不要告诉别人。',
					onClick: function(data) {
						$.alert('吓你呢，这只是个测试。');
					},
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