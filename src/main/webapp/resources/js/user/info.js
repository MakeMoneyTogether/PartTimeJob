var jid_bak = $('#jid_bak');
var opBtn = $('#opBtn');
function getItem(){
	uri = window.location.href;
	uris = uri.split('/');
	jid = uris[uris.length -1];
	jid_bak.text(jid);
	$.get('jzurl/pages/item/'+jid,function(data){
		item = $.parseJSON(data); 
		$('#jz-title').text(item.jname);
		$('#jz-name').text(item.jname);
		$('#jz-money').text(item.jmoney);
		$('#jz-label').text(item.jlabel);
		$('#jz-date').text(item.jstdate);
		$('#jz-local').text(item.jlocal);
	});
}

function getStatu(){
	var jid = jid_bak.text();
	var phone = $.cookie('phone');
	if(phone !=null && phone.length > 6){
		$.get('job/u2j/'+phone+'/'+jid,function(data){
			genBtn(parseInt(data));
		});
	}
}

function genBtn(code){
	if(code > 0){
		opBtn.addClass('weui-btn_disabled');
	}
	switch(code){
		case 0:
			opBtn.attr('onclick','apply();');
			opBtn.text('报名');
			break;
		case 1:
			opBtn.text('已报名');
			break;
		case 2:
			opBtn.text('被拒绝');
			break;
		case 3:
			opBtn.text('缺勤');
			break;
		case 4:
			opBtn.text('已结算');
			break;
	}
}

function apply(){
	
	$.confirm({
		title:'通知',
		text:'<strong>报名需要支付两元押金，在您完成考勤后会返还，如果缺勤或者中途离开会被扣除押金 同时无法获取到薪水，报名请慎重考虑!!</strong>',
		onOK:function(){
			var jid = jid_bak.text();
			var phone = $.cookie('phone');
			$.get('job/apply/'+phone+'/'+jid,function(data){
				$('#jz-num').text(data.applied+'/'+data.all+'人');
				if(data.code == 0){
					$.toast('报名成功');
					opBtn.addClass('weui-btn_disabled');
					opBtn.text('已报名');
					$.alert('当你临时有事是请提前致电商家，请求商家拒绝你的申请，这一点很重要！！！。');
				}
				if(data.code == 1){
					$.toast('报名失败，人数已满','cancel');
				}
				if(data.code == 2){
					$.toast('报名失败，余额不足','forbidden');
				}
				if(data.code == 3){
					$.toast('报名失败，时段重复','forbidden');
				}
			});
		},
		onCancel:function(){
			return;
		}
	});
}