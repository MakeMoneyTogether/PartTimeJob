$(function(){
	get_label();
});
function get_label(){
	$.get('job/types',function(data){
		jobType = $('#jobType');
		jobType.html('');
		for(i=0;i<data.length;i++){
			jobType.append('<option value="'+data[i].id+'">'+data[i].name+'</option>');
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
	jobStartTime = new Date($('#jobStartTime').val()).getTime();
	jobEndTime = new Date($('#jobEndTime').val()).getTime();
	jobValidateTime = new Date($('#jobValidateTime').val()).getTime();
	if(isNaN(jobStartTime) || isNaN(jobEndTime) || isNaN(jobValidateTime) || jobStartTime > jobEndTime){
		$.alert('请输入正确日期');
		return;
	}
	numPeople = $('#numPeople').val();
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
	jobDesc = $('#jobDesc').val();
	data = {jobTitle:jobTitle, jobType:jobType, paymentType:paymentType, paymentMoney:paymentMoney, jjobStartTime:jobStartTime, jjobEndTime:jobEndTime, numPeople:numPeople, jobDesc:jobDesc, jobAddress:jobAddress, jjobValidateTime:jobValidateTime, connectName:connectName, connectPhone:connectPhone, cityCode:citycode};
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
				$.alert('发布失败');
			}else if(data == 0){
				$.alert('发布成功',function(){
					window.location.href='mchntp/list';
				});
			}
			
		}
	});
}