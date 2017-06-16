function showInfo(mid){
	$.get('mchnt/info/'+mid,function(data){
		$('#mchntAddress').html(data.mchntAddress);
		$('#mchntName').html(data.mchntName);
		$('#connName').html(data.connName);
		$('#connPhone').html(data.connPhone);
		$('#mchntTitle').html(data.mchntName);
		$('#frozenMoney').html(data.frozenMoney);
		$('#balance').html(data.balance);
		
		$('#op_mchntid').html(data.mchntCd);
		
		$('#mchntInfo').modal('show');
		
		$.post('mchnt/schedule',{phone:data.phone},function(data){
			sche = $('#schedule');
			sche.html('');
			for(i=0;i<data.length;i++){
				str = '<tr><td>'+data[i].time+'</td><td>'+data[i].type+'</td><td>'+data[i].money;
				if(data[i].status == 0){
					str += '</td><td>失效</td></tr>';
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
	mchntCd = $('#op_mchntid').html();
	$.post('mchnt/freeze',{mchntCd:mchntCd},function(data){
		if(data == 0){
			alert('冻结成功');
			location.reload();
		}
	});
}

function passit(){
	mchntCd = $('#op_mchntid').html();
	$.post('mchnt/passit',{mchntCd:mchntCd},function(data){
		if(data == 0){
			alert('审核成功');
			location.reload();
		}
	});
}