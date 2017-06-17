function cash(){
	$.prompt({title:'输入提现金额',text:'请给自己留下2元保证金', empty:false,onOK:function(text) {
			postCash(text);
		}
	});
}
function pay(){
	$.prompt({title:'输入充值金额', empty:false,onOK:function(text) {
		postPay(text);
	}
});
}

function postPay(rmb){
	if(!rmb.match(/^(\d)+(\.(\d))?$/)){
		$.alert('请输入数字');
		return;
	}
	phone = $.cookie('phone');
	$.ajax({
		type:'POST',
		url: 'user/pay',
		dataType:'json',
		data:{totalFee:rmb},
		success: function(data){
			if(data == 500){
				$.toast('操作失败，请稍后重试','forbidden');
			}else {
				WeixinJSBridge.invoke('getBrandWCPayRequest', {
					"appId": data.appId,
					"timeStamp": data.timeStamp,
					"nonceStr": data.nonceStr,
					"package": data.wcPackage,
					"signType": "MD5",
					"paySign": data.paySign
				  },function(res){   
			           if(res.err_msg == "get_brand_wcpay_request:ok" ) {
			        	   $.ajax({
			        		   type:'POST',
			        		   url: 'user/checkPay',
			        		   dataType:'json',
			        		   data:{outTradeNo:data.outTradeNO},
			        		   success:function(data){
			        			   $.hideLoading();
			        			   if(data == 0){
			        				   $.alert('支付成功');
			        			   }else{
			        				   $.alert('支付失败');
			        			   }
			        		   }
			        	   });
			        	   $.showLoading();
			        	   $('.weui_loading_toast').children('p').html('平台确认中...');
			           }else{
			        	   $.alert('取消支付或者支付失败!');
			           }
			    });
			}
		}
	});
}

function postCash(rmb){
	if(!rmb.match(/^(\d)+(\.(\d))?$/)){
		$.alert('请输入数字');
		return;
	}
	phone = $.cookie('phone');
	$.ajax({
		type:'POST',
		url: 'user/cash',
		dataType:'json',
		data:{totalFee:rmb},
		success: function(data){
			if(data == 0){
				$.toast('已提交申请');
			}else if(data == 1){
				$.toast('余额不足','forbidden');
			}else{
				$.toast('操作失败，请稍后重试','forbidden');
			}
		}
	});
}

function genPerson(person){
	str = '';
	if(person.status == '0' ||person.status == 0 ){
		str = '<div class="weui-cell weui-cell_access"> <div class="weui-cell__hd"> <i style="color:#999" class="fa fa-twitter"></i> </div> <div class="weui-cell__bd"> <p style="margin-left:10%;">'
			+ person.name
			+ '</p> </div> <div class="">未进行兼职</div> </div>';
	}else{
		str = '<div class="weui-cell weui-cell_access"> <div class="weui-cell__hd"> <i style="color:#99ccff" class="fa fa-twitter"></i> </div> <div class="weui-cell__bd"> <p style="margin-left:10%;">'
			+ person.name
			+'</p> </div> <div class=""> +5.00 元</div> </div>';
	}
	return str;
}

function getInvitation(){
	phone = $.cookie('phone');
	$.ajax({
		type:'POST',
		url: 'user/invitation',
		dataType:'json',
		data:{phone:phone},
		success: function(data){
			$('#inv_person').text('');
			str = '';
			for(i=0;i<data.length;i++){
				str += genPerson(data[i]);
			}
			$('#inv_person').html(str);
		}
	});
}
var AVAILAB=1;
var UNAVAILAB=0;
var UNCHECKED=2;
function genSchedule(sche){
	str = '';
	if(sche.type != '提现'){
		return str;
	}
	if(sche.status == AVAILAB ){
		str = '<div class="weui-cell weui-cell_access"> <div class="weui-cell__hd"> <i style="color:#99ccff" class="fa fa-money"></i> </div> <div class="weui-cell__bd"> <p style="margin-left:10%;">'
			+ sche.money
			+ '元</p> </div> <div class="">提现成功</div> </div>';
	}else if(sche.status == UNCHECKED){
		str = '<div class="weui-cell weui-cell_access"> <div class="weui-cell__hd"> <i style="color:#dccd00" class="fa fa-money"></i> </div> <div class="weui-cell__bd"> <p style="margin-left:10%;">'
			+ sche.money
			+'元</p> </div> <div class=""> 申请中...</div> </div>';
	}else{
		str = '<div class="weui-cell weui-cell_access"> <div class="weui-cell__hd"> <i style="color:#ff0000" class="fa fa-money"></i> </div> <div class="weui-cell__bd"> <p style="margin-left:10%;">'
			+ sche.money
			+'元</p> </div> <div class=""> 平台拒绝，已退回余额</div> </div>';
	}
	return str;
}

function genPY(sche){
	pstr = '';
	if(sche.status == AVAILAB ){
		pstr = '<div class="weui-cell weui-cell_access"> <div class="weui-cell__hd">'
			+ sche.type+'</div> <div class="weui-cell__bd"> <p style="margin-left:10%;">'
			+ sche.money
			+ '元</p> </div><div class="weui-cell__bd">'
			+ sche.time
			+' </div><div class="">成功</div> </div>';
	}else if(sche.status == UNCHECKED){
		pstr = '<div class="weui-cell weui-cell_access"> <div class="weui-cell__hd">'
			+ sche.type+'</div> <div class="weui-cell__bd"> <p style="margin-left:10%;">'
			+ sche.money
			+'元</p> </div><div class="weui-cell__bd">'
			+ sche.time
			+' </div> <div class="">等待...</div> </div>';
	}else{
		pstr = '<div class="weui-cell weui-cell_access"> <div class="weui-cell__hd">'
			+ sche.type+'</div> <div class="weui-cell__bd"> <p style="margin-left:10%;">'
			+ sche.money
			+'元</p><div class="weui-cell__bd">'
			+ sche.time
			+' </div> </div> <div class="">失效</div> </div>';
	}
	return pstr;
}

function getSchedule(){
	phone = $.cookie('phone');
	$.ajax({
		type:'POST',
		url: 'user/schedule',
		dataType:'json',
		data:{phone:phone},
		success: function(data){
			$('#schedule').text('');
			str = '';
			pystr = '';
			for(i=0;i<data.length;i++){
				str += genSchedule(data[i]);
				pystr += genPY(data[i]);
			}
			$('#schedule').html(str);
			$('#pyschedule').html(pystr);
		}
	});
}

function showSechedule(){
	$('#pymodal').popup();
}