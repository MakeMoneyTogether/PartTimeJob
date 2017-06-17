uid_bak = $('#uid_bak');
inv_bak=$('#invitation_bak');
function jump(p,e){
	switch (p){
		case 0:
			window.location.href="userp/myjob#s22";
			console.log("被拒绝");
			break;
		case 1:
			window.location.href="userp/myjob#s21";
			console.log("已报名");
			break;
		case 2:
			window.location.href="userp/myjob#s23";
			console.log("已结算");
			break;
		case 4:
			console.log("钱包");
			window.location.href="userp/wallet";
			break;
		case 5:
			console.log("修改密码");
			$('#repass').popup();
			break;
		case 6:
			console.log("邀请好友");
			window.location.href="userp/invite";
			break;
		case 7:
			console.log("退出登录");
			$.removeCookie('phone',{path:'/'});
			$.removeCookie('password',{path:'/'});
			window.location.href="userp";
			break;
	}
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
		url: 'user/rpwd',
		dataType:'json',
		data:{phone:phone,pwd:pwd,npwd:npwd},
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

$(function(){
	phone = $.cookie('phone');
	if(phone !=null && phone.length > 6){
		pwd = $.cookie('password');
		$.ajax({
			type:'POST',
			url: 'user/me',
			dataType:'json',
			data:{phone:phone,pwd:pwd},
			success: function(data){
				if(data == 1){
					window.location.href='userp/login';
				}
				$('#uname').text(data.name);
				$('#ugpa').text(data.grade);
				$('#money').text(data.balance);
				$('#invitation_bak').text(data.shareCode);
				$.cookie('password',pwd,{expires:30,path:'/'});
				$.cookie('phone',phone,{expires:30,path:'/'});
			},
			fail: function(){
				window.location.href='userp/login';
			}
		});
	}else{
		window.location.href='userp/login';
	}
});