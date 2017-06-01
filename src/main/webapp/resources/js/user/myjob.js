function genItem(data){
	item = '<a href="userp/info/'+data.jobId
		+'" class="weui-media-box weui-media-box_appmsg"><div class="weui-media-box__hd"><div class="i-circle">'
		+data.jobType+'</div></div><div class="weui-media-box__bd i-jz"><span class="weui-media-box__title i-jz-title">'
		+data.jobTitle+'</span><br><span class="i-jz-desc">'
		+ stamp2YMD(data.jobStartTime)+' 至 '+stamp2YMD(data.jobEndTime)+'</span><br><span class="i-jz-money">'
		+data.paymentMoney+'元/'
		+(data.paymentType==1?'时':'天')+'</span></div></a>';
	return item;
}
$(function(){
	/*
	for(i=0;i<10;i++){
		$('#s0').append('<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg"><div class="weui-media-box__hd"><div class="i-circle">类型</div></div><div class="weui-media-box__bd i-jz"><span class="weui-media-box__title i-jz-title">标题一</span><br><span class="i-jz-desc">江宁 5月1日开始（共10天）</span><br><span class="i-jz-money">100元/天</span></div></a>');
		$('#s1').append('<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg"><div class="weui-media-box__hd"><div class="i-circle">类型</div></div><div class="weui-media-box__bd i-jz"><span class="weui-media-box__title i-jz-title">标题一</span><br><span class="i-jz-desc">江宁 5月1日开始（共10天）</span><br><span class="i-jz-money">100元/天</span></div></a>');
		$('#s2').append('<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg"><div class="weui-media-box__hd"><div class="i-circle">类型</div></div><div class="weui-media-box__bd i-jz"><span class="weui-media-box__title i-jz-title">标题一</span><br><span class="i-jz-desc">江宁 5月1日开始（共10天）</span><br><span class="i-jz-money">100元/天</span></div></a>');
		$('#s3').append('<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg"><div class="weui-media-box__hd"><div class="i-circle">类型</div></div><div class="weui-media-box__bd i-jz"><span class="weui-media-box__title i-jz-title">标题一</span><br><span class="i-jz-desc">江宁 5月1日开始（共10天）</span><br><span class="i-jz-money">100元/天</span></div></a>');
	}
	*/
$.ajax({
	type:'POST',
	url: 'job/sitem',
	dataType:'json',
	success: function(data){
		for(i=0;i<data.length;i++){
			item = genItem(data[i]);
			switch(data[i].jobSt){
				case 0:
					$('#s0').append(item);break;
				case 1:
					$('#s1').append(item);break;
				case 2:
					$('#s2').append(item);break;
				case 3:
					$('#s3').append(item);break;
			}
		}
		
	}
});
});