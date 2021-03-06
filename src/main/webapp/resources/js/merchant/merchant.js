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
			window.location.href='mchntp/money';
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

function logincheck(){
	phone = $.cookie('phone');
	pwd = $.cookie('password');
	
	if(phone == null || pwd == null){
		return;
	}
	$.ajax({
		type:'POST',
		url: 'mchnt/login',
		dataType:'json',
		data:{phone:phone,password:pwd},
		success: function(data){
			if(data == 0){
				window.location.href='mchntp/index';
			}
		}
	});
}
function logout(){
	phone = $.cookie('phone');
	$.ajax({
		type:'POST',
		url: 'mchnt/logout',
		dataType:'json',
		data:{phone:phone},
		success: function(data){
		}
	});
	$.removeCookie('phone',{path:'/'})
	$.removeCookie('password',{path:'/'})
	window.location.href='mchntp/login';
}

function timeStamp2day(time){
	var datetime = new Date();
	datetime.setTime(time);
	var year = datetime.getFullYear();
	var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
	var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
	return year + "-" + month + "-" + date;
}