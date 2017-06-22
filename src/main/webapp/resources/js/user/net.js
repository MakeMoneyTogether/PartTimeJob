var index_bak = $('#index_bak')
var loading = false;

function genItem(one){
	var item = '<a href="userp/netinfo/'+one.jobId+'" class="weui-media-box weui-media-box_appmsg"><div class="weui-media-box__hd"><div class="i-circle"><img style="width:100%;" src="static/img/type.png"></div></div><div class="weui-media-box__bd i-jz"><span class="weui-media-box__title i-jz-title">'+
				one.jobTitle+'</div></a>';
	return item;
}
function loadm(){
	if(loading) return;
	loading = true;
	setTimeout(function() {
		all = 'all';
		index = index_bak.text();
		try{
			$.ajax({
				type: "POST",
				url: 'net/'+index+'/3',
				dataType: "json",
				success:function(data){
					console.log(index)
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
		}catch(err){
			loadEnd();
		}
	}, 100);  
}
$(document.body).infinite().on("infinite",loadm);

function loadEnd(){
	$(document.body).destroyInfinite();
	$('.weui-loadmore').addClass('weui-loadmore_line');
	$('.weui-loadmore').html('<span class="weui-loadmore__tips">加载完毕</span>');
}
function onLoad(){
	$('#jz-infos').text('');
	try{
		$.ajax({
			type: "POST",
			url: "job/net/0/9",
			dataType: "json",
			success:function(data){
				for(i=0;i<data.length;i++){
					domhtml = genItem(data[i]);
					$('#jz-infos').append(domhtml);
				}
				if(data.length < 9){
					loadEnd();
				}
				index_bak.text(9);
			}
		});
	}catch(err){
		loadEnd();
	}
}