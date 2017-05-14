function jump(k){
	switch(k){
		case 1:
			window.location.href='mchnt/issue';
			break;
		case 2:
			window.location.href='mchnt/list';
			break;
		case 3:
			window.location.href='mchnt/index';
			break;
		case 4:
			window.location.href='mchnt/edit';
			break;
		case 5:
			window.location.href='mchnt/money';
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
		url: 'uurl/pages/login',
		dataType:'json',
		data:{phone:phone,password:pwd},
		success: function(data){
			if(data == 1){
				$.alert('登录名或密码错误！！');
			}
			if(data == 0){
				$.cookie('phone',phone,{expires:30,path:'/'});
				$.cookie('password',pwd,{expires:30,path:'/'});
				window.location.href='mchnt/index';
			}
		}
	});
}
function mercheck(){
	phone = $.cookie('phone');
	pwd = $.cookie('password');
	
	$.ajax({
		type:'POST',
		url: 'uurl/pages/login',
		dataType:'json',
		data:{phone:phone,password:pwd},
		success: function(data){
			if(data == 1){
				window.location.href='mchnt/login';
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