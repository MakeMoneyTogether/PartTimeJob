$(function(){
	$.ajax({
		type:'POST',
		url: 'mchnt/getJobList',
		dataType:'json',
		success: function(data){
			for(i=data.length-1;i>=0;i--){
				item = genItem(data[i]);
				switch(data[i].jobSt){
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
					default:
						$('#tab3').append(item);break;
				}
			}
			
		}
	});
});

function genItem(data){
	item = '<a href="mchntp/info/'+data.jobId
		+'" class="weui-media-box weui-media-box_appmsg"><div class="weui-media-box__hd"><div class="i-circle i-type'+data.jobType%26+'">'
		+data.jobTypeName+'</div></div><div class="weui-media-box__bd i-jz"><span class="weui-media-box__title i-jz-title">'
		+data.jobTitle+'</span><br><span class="i-jz-desc">'
		+ timeStamp2day(data.jobStartTime)+' 至 '+timeStamp2day(data.jobEndTime)+'</span><br><span class="i-jz-money">'
		+data.paymentMoney+'元/'
		+(data.paymentType==0?'时':'天')+'</span></div></a>';
	return item;
}