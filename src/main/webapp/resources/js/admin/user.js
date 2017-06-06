function showInfo(uid){
	$.get('user/info/'+uid,function(data){
		$('#userName').html(data.name);
		$('#name').html(data.name);
		$('#gender').html(data.gender);
		$('#phone').html(data.phone);
		$('#major').html(data.major);
		$('#grade').html(data.grade);
		$('#school').html(data.school);
		$('#balance').html(data.balance);
		userid = $('#op_userid').html(data.uid);
		$('#userInfo').modal('show');

		$.post('user/schedule',{phone:data.phone},function(data){
			sche = $('#schedule');
			sche.html('');
			for(i=0;i<data.length;i++){
				str = '<tr><td>'+data[i].time+'</td><td>'+data[i].type+'</td><td>'+data[i].money;
				if(data[i].status == 0){
					str += '</td><td>无效</td></tr>';
				}
				if(data[i].status == 1){
					str += '</td><td>成功</td></tr>';
				}
				if(data[i].status == 2){
					str += '</td><td>等待中</td></tr>';
				}
				sche.append(str);
			}
		});
	});
}

function freeze(){
	userid = $('#op_userid').html();
	$.post('user/freezeUser',{userId:userid},function(data){
		if(data == 0){
			alert('冻结成功');
		}
	});
}