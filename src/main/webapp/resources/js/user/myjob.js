function genItem(data){
	item = '<a href="userp/info/'+data.jobId
		+'" class="weui-media-box weui-media-box_appmsg"><div class="weui-media-box__hd"><div class="i-circle i-type'+data.jobType%26+'">'
		+data.jobTypeName+'</div></div><div class="weui-media-box__bd i-jz"><span class="weui-media-box__title i-jz-title">'
		+data.jobTitle+'</span><br><span class="i-jz-desc">'
		+ stamp2YMD(data.jobStartTime)+' 至 '+stamp2YMD(data.jobEndTime)+'</span><br><span class="i-jz-money">'
		+data.paymentMoney+'元/'
		+(data.paymentType==0?'时':'天')+'</span></div></a>';
	return item;
}
$(function(){
$.ajax({
	type:'POST',
	url: 'job/sitem',
	dataType:'json',
	success: function(data){
		for(i=0;i<data.length;i++){
			item = genItem(data[i]);
			switch(data[i].userJobStatu){
				case 1:
					$('#s1').append(item);break;
				case 2:
					$('#s2').append(item);break;
				case 3:
					$('#s3').append(item);break;
				case 4:
					$('#s3').append(item);break;
			}
		}
		
	}
});
});