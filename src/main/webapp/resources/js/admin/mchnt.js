function showInfo(mid){
	$.get('mchnt/info/'+mid,function(data){
		$('#mchntAddress').html(data.mchntAddress);
		$('#mchntName').html(data.mchntName);
		$('#connName').html(data.connName);
		$('#connPhone').html(data.connPhone);
		$('#mchntTitle').html(data.mchntName);
		
		$('#mchntInfo').modal('show');
	});
}