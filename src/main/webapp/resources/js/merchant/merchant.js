function jump(k){
	switch(k){
		case 1:
			window.location.href='mchntp/issue';
			break;
		case 2:
			window.location.href='mchntp/list';
			break;
		case 3:
			window.location.href='mchntp/index';
			break;
		case 4:
			window.location.href='mchntp/edit';
			break;
		case 5:
			window.location.href='https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfb5bb3526bdc5da3&redirect_uri=http%3A%2F%2Fwww.mapengju.com%2FPartTimeJob%2Fmchnt%2FredirectUrl&response_type=code&scope=snsapi_base&state=mchnt#wechat_redirect';
			break;
		case 6:
			$('#repass').popup();
			break;
		case 7:
			logout();
	}
}
function merlogin(){
	phone = $('#phone').val();
	pwd = $('#password').val();
	
	$.ajax({
		type:'POST',
		url: 'mchnt/login',
		dataType:'json',
		data:{phone:phone,password:pwd},
		success: function(data){
			if(data == 1){
				$.alert('登录名或密码错误！！');
			}
			if(data == 0){
				$.cookie('phone',phone,{expires:30,path:'/'});
				$.cookie('password',pwd,{expires:30,path:'/'});
				window.location.href='mchntp/index';
			}
			if(data == 500){
				$.alert('系统错误');
			}
		}
	});
}
function mercheck(){
	phone = $.cookie('phone');
	pwd = $.cookie('password');
	
	if(phone == null || pwd == null){
		window.location.href='mchntp/login';
	}
	$.ajax({
		type:'POST',
		url: 'mchnt/login',
		dataType:'json',
		data:{phone:phone,password:pwd},
		success: function(data){
			if(data != 0){
				window.location.href='mchntp/login';
			}
		}
	});
}
function logout(){
	phone = $.cookie('phone');
	$.ajax({
		type:'POST',
		url: 'uurl/pages/logout',
		dataType:'json',
		data:{phone:phone},
		success: function(data){
			$.removeCookie('phone',{path:'/'})
			$.removeCookie('password',{path:'/'})
			window.location.href='mchnt/login';
		}
	});
	$.removeCookie('phone',{path:'/'})
	$.removeCookie('password',{path:'/'})
	window.location.href='mchnt/login';
}