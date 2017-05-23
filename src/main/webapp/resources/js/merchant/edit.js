mchntCd = -1;
function init(){
	phone = $.cookie('phone');
	$.ajax({
		type:'POST',
		url: 'mchnt/getMchntInfo',
		dataType:'json',
		success: function(data){
			mname = $('#mname').val(data.mchntName);
			local = $('#local').val(data.mchntAddress);
			connectname = $('#connectname').val(data.connName);
			phone = $('#phone').val(data.phone);
			mchntCd = data.mchntCd;
		}
	});
}
function get_code(){
	if($('#code_btn').text() == '已发送'){
		return;
	}
	var phone = $('#phone').val();
	if(!phone.match(/^(1\d{10})$/)){
		$.alert('手机号不正确');
		return;
	}
	$('#code_btn').text('已发送');
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
				$('#code_btn').text('获取');
			}else{
				$.alert('验证码发送失败');
				$('#code_btn').text('获取');
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
	
	data={mchntCd:mchntCd,mchntName:mname,mchntAddress:local,connName:connectname,phone:phone,code:code};
	if(citycode != null){
		data.citycode = citycode;
	}
	$.ajax({
		type:'POST',
		url: 'mchnt/update',
		dataType:'json',
		data:data,
		success: function(data){
			console.log(data);
			if(data == 0){
				$.alert('信息修改成功',function(){
					window.location.href="mchntp/index";
				});
			}else{
				$.alert('简历修改失败',function(){
				});
			}
		}
	});
}