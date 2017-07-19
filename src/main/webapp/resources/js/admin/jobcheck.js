function showInfo(jid){
	$.get('job/item/'+jid,function(data){
        $('#jobTitle').html(data.jobTitle);
        $('#mchntName').html(data.mchntName);
        $('#connectName').html(data.connectName);
        $('#connectPhone').html(data.connectPhone);
        $('#jobStartTime').html(stamp2YMD(data.jobStartTime));
        $('#jobEndTime').html(stamp2YMD(data.jobEndTime));
        $('#paymentMoney').html(data.paymentMoney+'元/'+(data.paymentType==0?'时':'天'));
        $('#numPeople').html(data.joinNum+"/"+data.numPeople);
        $('#hoursDay').html(data.hoursDay/10);
        $('#sex').html(data.sex);
        $('#jobDesc').html(data.jobDesc);
        
        
        $('#jid').html(data.jobId);
        
		$('#jobInfo').modal('show');
	});
}
function pass(){
	jid =  $('#jid').html();
	$.get('job/passJob/'+jid,function(data){
		$('#'+jid).remove();
	});
}
function refuse(){
	jid =  $('#jid').html();
	$.get('job/refuseJob/'+jid,function(data){
		$('#'+jid).remove();
	});
}
function deleteit(){
	jid =  $('#jid').html();
	$.get('adminp/delete/'+jid,function(data){
		if(data == 0){
			$('#'+jid).remove();
		}else{
			alert('删除失败，请只删除无用的兼职.');
		}
	});
}