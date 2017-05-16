var jid_bak = $('#jid_bak');
var opBtn = $('#opBtn');
function getItem(){
	uri = window.location.href;
	uris = uri.split('/');
	jid = uris[uris.length -1];
	jid_bak.text(jid);
	$.get('jzurl/pages/netitem/'+jid,function(data){
		item = $.parseJSON(data); 
		$('#jz-title').text(item.jname);
		$('#jz-name').text(item.jname);
		$('#jz-money').text(item.money);
		$('#jz-label').text(item.jlabel);
		$('#jz-date').text(item.jdate);
	});
}