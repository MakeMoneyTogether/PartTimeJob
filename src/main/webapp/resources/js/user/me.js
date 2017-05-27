uid_bak = $('#uid_bak');
inv_bak=$('#invitation_bak');
function jump(p,e){
	if(p < 4){
		$('.jz__div_active').removeClass('jz__div_active');
		$(e).addClass('jz__div_active');
		loading_tag();
		flushJZ(p);
	}
	switch (p){
		case 0:
			console.log("已报名");
			break;
		case 1:
			console.log("已录用");
			break;
		case 2:
			console.log("已到岗");
			break;
		case 3:
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
			$.alert();
			$.modal({
				title: '邀请码',
				text: inv_bak.text(),
			});
			break;
		case 7:
			console.log("退出登录");
			$.removeCookie('phone',{path:'/'});
			$.removeCookie('password',{path:'/'});
			window.location.href="userp";
			break;
	}
}
function loading_tag(){
	$('#jz-infos').text('<div class="weui-loadmore"> <i class="weui-loading"></i> <span class="weui-loadmore__tips">正在加载</span> </div>');
}
function genItem(one){
	var item = '<a href="userp/info/'+one.jid+'" class="weui-media-box weui-media-box_appmsg"><div class="weui-media-box__hd"><div class="i-circle">'+
				one.jlabel+'</div></div><div class="weui-media-box__bd i-jz"><span class="weui-media-box__title i-jz-title">'+
				one.jname+'</span><br><span class="i-jz-desc">'+one.jlocal+' '+one.jstdate+'开始</span><br><span class="i-jz-money">'+
				one.jmoney+'元/'+one.jtime+'</span></div></a>';
	return item;
}

function flushJZ(sid){
	uid = uid_bak.text();
	$.ajax({
		type: "GET",
		url: 'jzurl/pages/sitem/'+uid+'/'+sid,
		dataType: "json",
		success:function(data){
			$('#jz-infos').text('');
			for(i=0;i<data.length;i++){
				domhtml = genItem(data[i]);
				$('#jz-infos').append(domhtml);
			}
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
				$('#s0').text(data.s4);
				$('#s1').text(data.s1);
				$('#s2').text(data.s2);
				$('#s3').text(data.s3);
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