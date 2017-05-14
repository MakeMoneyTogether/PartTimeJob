$(function(){
	
});

function change(value){
	if(value == 'hour'){
		$('#for_hour').show();
	}else{
		$('#for_hour').hide();
	}
}
function check(){
	jzname = $('#jzname').val();
	if(jzname == ''){
		$.alert('请输入兼职名');
		return;
	}
	
	salary = $('#salary').val();
	if(!salary.match(/^(\d)+(\.(\d)+)?$/)){
		$.alert('请输入正确的薪水');
		return;
	}
	hourOfDay = $('#hourOfDay').val();
	if($('#unit').val() == 'hour' && !hourOfDay.match(/^(\d|\d\d)$/) || hourOfDay > 24){
		$.alert('请输入正确的工作强度');
		return;
	}
	stdate = $('#stdate').val();
	endate = $('#endate').val();
	pausedate = $('#pausedate').val();
	if(stdate == '' || endate == '' || pausedate == '' || pausedate > stdate || stdate > endate){
		$.alert('请输入正确日期');
		return;
	}
	jznum = $('#jznum').val();
	if(!salary.match(/^(\d)+$/)){
		$.alert('请输入正确的招聘人数');
		return;
	}
	jzlocal = $('#jzlocal').val();
	if(jzlocal == ''){
		$.alert('请输入兼职地点');
		return;
	}
	
	connectName = $('#connectName').val();
	if(connectName == ''){
		$.alert('请输入联系人姓名');
		return;
	}
	connectPhone = $('#connectPhone').val();
	if(connectPhone.match(/^(1\d{10})$/)){
		$.alert('请输入正确的手机号码');
		return;
	}
	jzdes = $('#jzdes').val();
	if($('#unit').val() == 'hour'){//calcu by hour
		data = {salary:salary,hourOfDay:hourOfDay,stdate:stdate,endate:endate,pausedate:pausedate,jznum:jznum,jzlocal:jzlocal,connectName:connectName,connectPhone:connectPhone,jzdes:jzdes};
	}else{//calcu by day
		data = {salary:salary,stdate:stdate,endate:endate,pausedate:pausedate,jznum:jznum,jzlocal:jzlocal,connectName:connectName,connectPhone:connectPhone,jzdes:jzdes};
	}
	issue(data);
}
function issue(data){
	$.ajax({
		type:'POST',
		url: 'merurl/json/issue',
		dataType:'json',
		data:data,
		success: function(data){
			if(data.length > 1){
				window.location.href='mchnt/index';
			}else{
				$.alert('余额不足');
			}
			
		}
	});
}