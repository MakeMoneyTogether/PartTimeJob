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
		url: 'util/editCode',
		dataType:'json',
		data:{phone:phone},
		success: function(data){
			console.log(data);
			if(data == 0){
				$.notification({
					text: '验证码发送成功',
				});
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
	
	data={mchntCd:mchntCd,mchntName:mname,mchntAddress:local,connName:connectname,connPhone:phone,code:code};
	if(citycode != null){
		data.citycode = citycode;
	}
	$.ajax({
		type:'POST',
		url: 'mchnt/update',
		dataType:'json',
		data:data,
		success: function(data){
			if(data == 0){
				$.alert('信息修改成功',function(){
					window.location.href="mchntp/index";
				});
			}else if(data ==1){
				$.alert('验证码出错');
			}else{
				$.alert('简历修改失败',function(){
				});
			}
		}
	});
}