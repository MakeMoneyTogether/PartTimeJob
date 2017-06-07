function init(){
	phone = $.cookie('phone');
	$.ajax({
		type: "POST",
		url: 'mchnt/getMchntInfo',
		dataType: "json",
		success:function(data){
			$('#moneyable').text(data.balance);
			$('#moneyice').text(data.frozenMoney);
		}
	});
	var AVAILAB=1;
	var UNAVAILAB=0;
	var UNCHECKED=2;
	$.ajax({
		type: "POST",
		url: 'mchnt/schedule',
		dataType: "json",
		data:{phone:phone},
		success:function(data){
			n = data.length;
			for(i = 0;i<n;i++){
				cellpre = '<div onclick="$.alert(\''+data[i].id+'\',\'交易单号\');" class="weui-cell"><div class="weui-cell__hd"><i class="fa fa-euro"></i></div><div class="weui-cell__bd"><p style="margin-left:10%;">提现 '+data[i].money+'</p></div><div class="weui-cell__ft">';
				if(data[i].status == UNCHECKED){
					$('#out_moneys').append(cellpre+'待审核<i class="weui-icon-waiting"></i></div></div>');
				}else if(data[i].status == UNAVAILAB){
					$('#out_moneys').append(cellpre+'被拒绝<i class="weui-icon-warn"></i></div></div>');
				}else{
					$('#out_moneys').append(cellpre+'成功<i class="weui-icon-success"></i></div></div>');
				}
			}
		}
	});
}
function cash(){
	$.prompt({
		title: '提现',
		text: '请输入提现金额',
		input: '100.0',
		empty: false, // 是否允许为空
		onOK: function (input) {
			postCash(input);
		},
		onCancel: function () {
			//点击取消
		}
	});
}

function charge(){
	$.prompt({
		title: '充值',
		text: '请输入充值金额',
		input: '100',
		empty: false, // 是否允许为空
		onOK: function (input) {
			postCharge(input);
		},
		onCancel: function () {
			//点击取消
		}
	});
}

function postCharge(rmb){
	if(!rmb.match(/^(\d)+$/)){
		$.alert('请输入数字');
		return;
	}
	phone = $.cookie('phone');
	$.ajax({
		type:'POST',
		url: 'mchnt/pay',
		dataType:'json',
		data:{phone:phone,totalFee:rmb},
		success: function(data){
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
		        		   url: 'mchnt/checkPay',
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
	});
}

function postCash(rmb){
	if(!rmb.match(/^(\d)+\.(\d)$/)){
		$.alert('请输入数字');
		return;
	}
	phone = $.cookie('phone');
	$.ajax({
		type:'POST',
		url: 'mchnt/cash',
		dataType:'json',
		data:{totalFee:rmb},
		success: function(data){
			if(data == 0){
				$.toast('已提交申请');
				init();
			}else if(data == 1){
				$.toast('余额不足','forbidden');
			}else{
				$.toast('操作失败，请稍后重试','forbidden');
			}
		}
	});
}