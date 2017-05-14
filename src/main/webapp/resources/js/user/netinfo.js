var jid_bak = $('#jid_bak');
var opBtn = $('#opBtn');
function getItem(){
	uri = window.location.href;
	uris = uri.split('/');
	jid = uris[uris.length -1];
	jid_bak.html(jid);
	$.get('jzurl/pages/netitem/'+jid,function(data){
		item = $.parseJSON(data); 
		$('#jz-title').html(item.jname);
		$('#jz-name').html(item.jname);
		$('#jz-money').html(item.money);
		$('#jz-label').html(item.jlabel);
		$('#jz-date').html(item.jdate);
	});
}