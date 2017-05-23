function genItem(data){
	item = '<div class="weui-cell"><div class="weui-cell__hd" style="color:red;">'
			+data.grade
			+'</div><div class="weui-cell__bd"><p>'
			+data.name
			+'</p></div><div class="weui-cell__ft"><button onclick="refuse('
			+data.uid
			+')" class="weui-btn weui-btn_mini weui-btn_warn">拒绝</button></div></div>';
	return item;
}

function getPeople(){
	list = $('#list');
	var jid = $('#jid').html();
	$.ajax({
		type:'GET',
		url: 'job/userOfJob/'+jid,
		dataType:'json',
		success: function(data){
			if(data == 0){
				for(i =0;i<data.length;i++){
					list.append(genItem(data[i]));
				}
			}
		}
	});
	
}

function refuse(e,uid){
	var jid = $('#jid').html();
	$.ajax({
		type:'POST',
		url: 'job/refuseUser',
		dataType:'json',
		data:{userId:uid,jobId:jid},
		success: function(data){
			if(data == 0){
				$(e).parent().parent().remove();
			}
		}
	});
}