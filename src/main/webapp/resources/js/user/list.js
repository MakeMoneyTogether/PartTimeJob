var index_bak = $('#index_bak')
var city_bak = $('#city_bak')
var keys_bak = $('#keys_bak')

var loading = false;

function genItem(one){
	var item = '<a href="user/info/'+one.jid+'" class="weui-media-box weui-media-box_appmsg"><div class="weui-media-box__hd"><div class="i-circle">'+
				one.jlabel+'</div></div><div class="weui-media-box__bd i-jz"><span class="weui-media-box__title i-jz-title">'+
				one.jname+'</span><br><span class="i-jz-desc">'+one.jlocal+' '+one.jstdate+'开始</span><br><span class="i-jz-money">'+
				one.jmoney+'元/'+one.jtime+'</span></div></a>';
	return item;
}

function loadm(){
	if(loading) return;
	loading = true;
	setTimeout(function() {
		city = city_bak.text();
		var index = index_bak.text();
		keys = keys_bak.text();
		data = {keys:keys,city:city};
		$.ajax({
			type: "POST",
			url: 'jzurl/pages/search/'+index+'/3',
			dataType: "json",
			data: data,
			success:function(data){
				for(i=0;i<data.length;i++){
					domhtml = genItem(data[i]);
					$('#jz-infos').append(domhtml);
				}
				index = parseInt(index) +3;
				index_bak.text(index);
				loading = false;
			}
		});
	}, 100);  
}
$(document.body).infinite().on("infinite",loadm);

function onLoad(){
	city = city_bak.text();
	keys = keys_bak.text();
	data = {keys:keys,city:city};
	$('#jz-infos').text('');
	$.ajax({
		type: "POST",
		url: "jzurl/pages/search/0/9",
		dataType: "json",
		data: data,
		success:function(data){
			for(i=0;i<data.length;i++){
				domhtml = genItem(data[i]);
				$('#jz-infos').append(domhtml);
			}
			index_bak.text(9);
		}
	});
}
