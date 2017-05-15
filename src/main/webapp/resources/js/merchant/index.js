function imercheck(){
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
			getMeInfo();
		}
	});
}
function getMeInfo(){
	$.ajax({
		type: "POST",
		url: 'mchnt/getMchntInfo',
		dataType: "json",
		success:function(data){
			$('#mname').html(data.mchntName);
			$('#moneyable').html(data.balance);
			$('#moneyice').html(data.balance);
		}
	});
}

function repwd(){
	var npwd = $('#npwd').val();
	var rnpwd =$('#rnpwd').val();
	var pwd = $('#pwd').val();
	
	if(rnpwd != npwd){
		$.alert('两次密码不一致');
		return;
	}
	
	phone = $.cookie('phone');
	$.ajax({
		type:'POST',
		url: 'mchnt/repassword',
		dataType:'json',
		data:{phone:phone,password:pwd,repassword:npwd},
		success: function(data){
			console.log(data);
			if(data == 0){
				$.toast('修改密码成功');
				$.closePopup()
				$.cookie('password',npwd,{expires:30,path:'/'});
				$('#npwd').val('');
				$('#rnpwd').val('');
				$('#pwd').val('');
			}else{
				$.alert("原密码不正确");
			}
		}
	});
}