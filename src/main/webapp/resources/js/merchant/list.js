$(function(){
//		for(i=0;i<10;i++){
//			$('#tab0').append('<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg"><div class="weui-media-box__hd"><div class="i-circle">类型</div></div><div class="weui-media-box__bd i-jz"><span class="weui-media-box__title i-jz-title">标题一</span><br><span class="i-jz-desc">江宁 5月1日开始（共10天）</span><br><span class="i-jz-money">100元/天</span></div></a>');
//			$('#tab1').append('<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg"><div class="weui-media-box__hd"><div class="i-circle">类型</div></div><div class="weui-media-box__bd i-jz"><span class="weui-media-box__title i-jz-title">标题一</span><br><span class="i-jz-desc">江宁 5月1日开始（共10天）</span><br><span class="i-jz-money">100元/天</span></div></a>');
//			$('#tab2').append('<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg"><div class="weui-media-box__hd"><div class="i-circle">类型</div></div><div class="weui-media-box__bd i-jz"><span class="weui-media-box__title i-jz-title">标题一</span><br><span class="i-jz-desc">江宁 5月1日开始（共10天）</span><br><span class="i-jz-money">100元/天</span></div></a>');
//			$('#tab3').append('<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg"><div class="weui-media-box__hd"><div class="i-circle">类型</div></div><div class="weui-media-box__bd i-jz"><span class="weui-media-box__title i-jz-title">标题一</span><br><span class="i-jz-desc">江宁 5月1日开始（共10天）</span><br><span class="i-jz-money">100元/天</span></div></a>');
//		}
	phone = $.cookie('phone');
	$.ajax({
		type:'POST',
		url: 'merurl/json/jzs',
		dataType:'json',
		data:{phone:phone},
		success: function(data){
			for(i=0;i<data.length;i++){
				item = '<a href="mchnt/jzinfo/'+data[i].jid+'" class="weui-media-box weui-media-box_appmsg"><div class="weui-media-box__hd"><div class="i-circle">'+data[i].jlabel+'</div></div><div class="weui-media-box__bd i-jz"><span class="weui-media-box__title i-jz-title">'+data[i].jname+'</span><br><span class="i-jz-desc">'+data[i].jstdate+'</span><br><span class="i-jz-money">'+data[i].jmoney+'元/'+data[i].jtime+'</span></div></a>';
				switch(data[i].jstatu){
					case 0:
						$('#tab0').append(item);break;
					case 1:
						$('#tab1').append(item);break;
					case 2:
						$('#tab2').append(item);break;
					case 3:
						$('#tab3').append(item);break;
					case 4:
						$('#tab3').append(item);break;
				}
			}
			
		}
	});
});