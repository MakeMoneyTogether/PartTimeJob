function loadJob(){
	data = {"jobId":5,"jobTitle":"大马蜂招工蜂","jobType":3,"paymentType":2,"paymentMoney":200,"jobStartTime":1493596800000,"jobEndTime":1494892800000,"numPeople":100,"jobDesc":"哈哈哈哈哈哈哈哈","jobAddress":"可每大学","jobValidateTime":1495584000000,"connectName":"蜂将军","connectPhone":'13222765139',"jobSt":null};
	$('#jobTitle').text(data.jobTitle);
	$('#jobType').text(data.jobType);
	$('#paymentMoney').text(data.paymentMoney+(data.paymentType==0?'/时':'/天'));
	$('#jobStartTime').text(timeStamp2day(data.jobStartTime));
	$('#jobEndTime').text(timeStamp2day(data.jobEndTime));
	$('#numPeople').text(data.numPeople);
	$('#jobDesc').text(data.jobDesc);
	$('#jobAddress').text(data.jobAddress);
	$('#jobValidateTime').text(timeStamp2day(data.jobValidateTime));
	$('#connectName').text(data.connectName);
	$('#connectPhone').text(data.connectPhone);
}