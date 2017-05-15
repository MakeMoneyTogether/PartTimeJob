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
				$.alert("原密码不正确(后台逻辑还没有，所以统一无法修改密码)");
			}
		}
	});
}