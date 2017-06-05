function mpass(id,e){
	$.post('mchnt/checkcash',{id:id},function(data){
		if(data == 0){
			console.log('提现成功');
			$(e).parent().parent().remove();
		}else{
			alert('批准提现失败');
		}
	});
}
function upass(id,e){
	$.post('user/checkcash',{id:id},function(data){
		if(data == 0){
			console.log('提现成功');
			$(e).parent().parent().remove();
		}else{
			alert('批准提现失败');
		}
	});
}