var index_bak = $('#index_bak')
var city_bak = $('#city_bak')
var keys_bak = $('#keys_bak')

var loading = false;

function genItem(one){
	var item = '<a href="userp/info/'+one.jobId+'" class="weui-media-box weui-media-box_appmsg"><div class="weui-media-box__hd"><div class="i-circle i-type'+one.jobType%26+'">'+
				one.jobTypeName+'</div></div><div class="weui-media-box__bd i-jz"><span class="weui-media-box__title i-jz-title">'+
				one.jobTitle+'</span><br><span class="i-jz-desc">'+one.jobAddress+'<br>'+stamp2YMD(one.jobStartTime)+'-'+stamp2YMD(one.jobEndTime)+'</span><br><span class="i-jz-money">'+
				one.paymentMoney+'元/'+(data.paymentType==1?'时':'天')+'</span></div></a>';
	return item;
}

function loadm(){
	if(loading) return;
	loading = true;
	setTimeout(function() {
		city = $.cookie('citycode');
		var index = index_bak.text();
		keys = keys_bak.text();
		data = {keys:keys,cityCode:city};
		$.ajax({
			type: "POST",
			url: 'job/search/'+index+'/3',
			dataType: "json",
			data: data,
			success:function(data){
				for(i=0;i<data.length;i++){
					domhtml = genItem(data[i]);
					$('#jz-infos').append(domhtml);
				}
				if(data.length < 3){
					loadEnd();
				}
				index = parseInt(index) +3;
				index_bak.text(index);
				loading = false;
			}
		});
	}, 100);  
}
$(document.body).infinite().on("infinite",loadm);
function loadEnd(){
	$(document.body).destroyInfinite();
	$('.weui-loadmore').addClass('weui-loadmore_line');
	$('.weui-loadmore').html('<span class="weui-loadmore__tips">加载完毕</span>');
}
function onLoad(){
	city = $.cookie('citycode');
	keys = keys_bak.text();
	data = {keys:keys,city:city};
	$('#jz-infos').text('');
	$.ajax({
		type: "POST",
		url: "job/search/0/9",
		dataType: "json",
		data: data,
		success:function(data){
			for(i=0;i<data.length;i++){
				domhtml = genItem(data[i]);
				$('#jz-infos').append(domhtml);
			}
			if(data.length < 6){
				loadEnd();
			}
			index_bak.text(9);
		}
	});
}
