$(function(){
	get_label();
});
function get_label(){
	$.get('job/types',function(data){
		jobType = $('#jobType');
		jobType.html('');
		for(i=0;i<data.length;i++){
			jobType.append('<option value="'+data[i].lid+'">'+data[i].name+'</option>');
		}
	});
}
function check(){
	jobTitle = $('#jobTitle').val();
	if(jobTitle == ''){
		$.alert('请输入兼职名');
		return;
	}
	jobType = $('#jobType').val();
	paymentType = $('#paymentType').val();
	paymentMoney = $('#paymentMoney').val();
	if(!paymentMoney.match(/^(\d)+(\.(\d)+)?$/)){
		$.alert('请输入正确的薪水');
		return;
	}
	hoursDay = $('#hourOfDay').val();
	if(!hoursDay.match(/^(\d)+(\.(\d)+)?$/)){
		$.alert('请输入正确的工作强度');
		return;
	}
	hoursDay = parseInt(hoursDay * 10);
	jobStartTime = $('#jobStartTime').val();
	jobEndTime = $('#jobEndTime').val();
	jobValidateTime = $('#jobValidateTime').val();
	numPeople = $('#numPeople').val();
	
	if(jobStartTime.length < 1 || jobEndTime.length < 1 || jobValidateTime.length < 1){
		$.alert('请输入正确日期');
		return;
	}
	
	if(!numPeople.match(/^(\d)+$/)){
		$.alert('请输入正确的招聘人数');
		return;
	}
	jobAddress = $('#jobAddress').val();
	if(jobAddress == ''){
		$.alert('请输入兼职地点');
		return;
	}
	
	connectName = $('#connectName').val();
	if(connectName == ''){
		$.alert('请输入联系人姓名');
		return;
	}
	connectPhone = $('#connectPhone').val();
	if(!connectPhone.match(/^(1\d{10})$/)){
		$.alert('请输入正确的手机号码');
		return;
	}
	citycode = $("#city-picker").attr('data-code');
	jobType = $('#jobType').val();
	jobDesc = $('#jobDesc').val();
	data = {jobType:jobType,jobTitle:jobTitle, jobType:jobType, paymentType:paymentType, paymentMoney:paymentMoney, hoursDay:hoursDay,jjobStartTime:jobStartTime, jjobEndTime:jobEndTime, numPeople:numPeople, jobDesc:jobDesc, jobAddress:jobAddress, jjobValidateTime:jobValidateTime, connectName:connectName, connectPhone:connectPhone, cityCode:citycode};
	issue(data);
}
function issue(data){
	$.ajax({
		type:'POST',
		url: 'mchnt/podtJob',
		dataType:'json',
		data:data,
		success: function(data){
			if(data == 1){
				$.alert('余额不足');
			}else if(data == 0){
				$.alert('发布成功',function(){
					window.location.href='mchntp/list';
				});
			}else if(data == 4){
				$.alert('薪资过低！！');
			}else if(data == 666){
				$.alert('日期不正确！！');
			}
			
		}
	});
}