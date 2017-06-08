function push(){
	jobTitle = $('#jobName').val();
	connectName = $('#connectName').val();
	connectPhone = $('#connectPhone').val();
	jobDesc = $('#netdes').summernote('code')
	$.post('job/pushNet',{jobTitle:jobTitle,jobDesc:jobDesc,connectName:connectName,connectPhone:connectPhone},function(data){
		if(data == 0){
			alert('发布成功');
			location.reload();
		}else{
			alert('发布失败');
		}
	})
}

function showInfo(jid){
	$.get('job/netitem/'+jid,function(data){
		$('#ijobName').html(data.jobTitle);
		$('#ijobTitle').html(data.jobTitle);
		$('#iconnectName').html(data.connectName);
		$('#iconnectPhone').html(data.connectPhone);
		$('#ijobDesc').html(data.jobDesc);
		$('#jobInfo').modal('show');
	});
}