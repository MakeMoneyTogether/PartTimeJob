$(function(){
	
});
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
	jobStartTime = new Date($('#jobStartTime').val()).getTime()/1000;
	jobEndTime = new Date($('#jobEndTime').val()).getTime()/1000;
	jobValidateTime = new Date($('#jobValidateTime').val()).getTime()/1000;
	if(jobStartTime == '' || jobEndTime == '' || jobValidateTime == '' || jobStartTime > jobEndTime){
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
	csonnectIphone = $('#csonnectIphone').val();
	if(!csonnectIphone.match(/^(1\d{10})$/)){
		$.alert('请输入正确的手机号码');
		return;
	}
	jobDesc = $('#jobDesc').val();
//	data = {jobTitle:jobTitle, jobType:jobType, paymentType:paymentType, paymentMoney:paymentMoney, jobStartTime:jobStartTime, jobEndTime:jobEndTime, numPeople:numPeople, jobDesc:jobDesc, jobAddress:jobAddress, jobValidateTime:jobValidateTime, connectName:connectName, csonnectIphone:csonnectIphone};
	data = {jobTitle:jobTitle, jobType:jobType, paymentType:paymentType, paymentMoney:paymentMoney, jobStartTime:jobStartTime, jobEndTime:jobEndTime, numPeople:numPeople, jobDesc:jobDesc, jobAddress:jobAddress, jobValidateTime:jobValidateTime, connectName:connectName};
	issue(data);
}
function issue(data){
	$.ajax({
		type:'POST',
		url: 'mchnt/podtJob',
		dataType:'json',
		data:data,
		success: function(data){
			if(data.length > 1){
				window.location.href='mchntp/index';
			}else{
				$.alert('余额不足');
			}
			
		}
	});
}