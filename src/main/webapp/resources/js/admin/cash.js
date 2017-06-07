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

function munpass(id,e){
	$.post('mchnt/notPassCash',{id:id},function(data){
		if(data == 0){
			console.log('拒绝提现完成');
			$(e).parent().parent().remove();
		}else{
			alert('拒绝提现失败');
		}
	});
}

function uunpass(id,e){
	$.post('user/noPassCash',{id:id},function(data){
		if(data == 0){
			console.log('拒绝提现完成');
			$(e).parent().parent().remove();
		}else{
			alert('拒绝提现失败');
		}
	});
}