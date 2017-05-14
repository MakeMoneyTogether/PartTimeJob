function init(){
	phone = $.cookie('phone');
	$.ajax({
		type:'POST',
		url: 'merurl/json/merme',
		dataType:'json',
		data:{phone:phone},
		success: function(data){
			mname = $('#mname').val(data.mname);
			local = $('#local').val(data.local);
			connectname = $('#connectname').val(data.connectname);
			phone = $('#phone').val(data.phone);
		}
	});
}
function editLocal(){
	$("#city-picker").attr('disabled',false);
	$("#city-picker").cityPicker({
		showDistrict: false
	});
}
function get_code(){
	if($('#code_btn').html() == '已发送'){
		return;
	}
	var phone = $('#phone').val();
	if(!phone.match(/^(1\d{10})$/)){
		$.alert('手机号不正确');
		return;
	}
	$('#code_btn').html('已发送');
	$.ajax({
		type:'POST',
		url: 'util/sendCode',
		dataType:'json',
		data:{phone:phone},
		success: function(data){
			console.log(data);
			if(data == 0){
				$.notification({
					text: '【兼职平台】您的兼职平台手机验证码为：320100<br>打死也不要告诉别人。',
					onClick: function(data) {
						$.alert('吓你呢，这只是个测试。');
					},
				});
			}else if(data == 1){
				$.alert('手机号已被注册');
				$('#code_btn').html('获取');
			}else{
				$.alert('验证码发送失败');
				$('#code_btn').html('获取');
			}
		}
	});
}
function update(){
	mname = $('#mname').val();
	citycode = $("#city-picker").attr('data-code');
	local = $('#local').val();
	connectname = $('#connectname').val();
	phone = $('#phone').val();
	code = $('#code').val();
	
	data={mname:mname,local:local,connectname:connectname,phone:phone,code:code};
	if(citycode != null){
		data.citycode = citycode;
	}
	$.ajax({
		type:'POST',
		url: 'merurl/json/update',
		dataType:'json',
		data:data,
		success: function(data){
			console.log(data);
			if(data == 0){
				$.alert('信息修改成功',function(){
					window.location.href="mchnt/index";
				});
			}else{
				$.alert('简历修改失败',function(){
				});
			}
		}
	});
}