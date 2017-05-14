var dises_bak = $('#dises_bak')
var labels_bak = $('#labels_bak')
var dates_bak = $('#dates_bak')
var index_bak = $('#index_bak')
var ncity = $('#ncity')
var loading = false;

function onCheckBtn(){
	$('.checkBtn').unbind();
	$('.checkBtn').click(function(){
		if($(this).is('.checkBtnActive')){
			$(this).removeClass('checkBtnActive');
		}else{
			$(this).addClass('checkBtnActive');
		}
	});
	$('.commitBtn').unbind();
	$('.commitBtn').click(freshList);
}

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
		dises = dises_bak.html();
		labels = labels_bak.html();
		dates = dates_bak.html();
		city = ncity.attr('value');
		index = index_bak.html();
		data = {dises:dises,labels:labels,dates:dates,city:city};
		$.ajax({
			type: "POST",
			url: 'jzurl/pages/select/'+index+'/3',
			dataType: "json",
			data: data,
			success:function(data){
				console.log(index)
				for(i=0;i<data.length;i++){
					domhtml = genItem(data[i]);
					$('#jz-infos').append(domhtml);
				}
				index = parseInt(index) +3;
				index_bak.html(index);
				loading = false;
			}
		});
	}, 100);  
}
$(document.body).infinite().on("infinite",loadm);


function onLoad(){
	dises = dises_bak.html();
	labels = labels_bak.html();
	dates = dates_bak.html();
	city = ncity.attr('value');
	data = {dises:dises,labels:labels,dates:dates,city:city};
	$('#jz-infos').html('');
	$.ajax({
		type: "POST",
		url: "jzurl/pages/select/0/6",
		dataType: "json",
		data: data,
		success:function(data){
			for(i=0;i<data.length;i++){
				domhtml = genItem(data[i]);
				$('#jz-infos').append(domhtml);
			}
			index_bak.html(6);
		}
	});
}

$("#plocal").click(function(){
	$('#slocal').toggle();
	$('#stype').hide();
	$('#stime').hide();
});

$("#ptype").click(function(){
	$('#slocal').hide();
	$('#stype').toggle();
	$('#stime').hide();
});
$("#ptime").click(function(){
	$('#slocal').hide();
	$('#stype').hide();
	$('#stime').toggle();
});


function getDistrict(){
	var ccode = ncity.attr('value');
	$.get('util/city/districts/'+ccode,function(data){
		data = JSON.parse(data);
		var districts = $('#districts');
		districts.html('');
		districts.append('<span class="weui-btn weui-btn_mini weui-btn_defualt checkBtn" value="all">全部</span><br>\n')
		for(i=0;i<data.length;i++){
			districts.append('<span class="weui-btn weui-btn_mini weui-btn_defualt checkBtn" value="'+data[i]['dcode']+'">'+data[i]['dname']+'</span>\n')
		}
		districts.append('<br> <button class="weui-btn weui-btn_mini commitBtn">筛选</button> <br> <div class="weui-panel__hd" style="padding:0;"></div>');
		onCheckBtn();
	});
}
function getLabels(){
	$.get('util/labels',function(data){
		data = JSON.parse(data);
		var labels = $('#labels');
		labels.html('');
		labels.append('<span class="weui-btn weui-btn_mini weui-btn_defualt checkBtn" value="all">全部</span><br>\n')
		for(i=0;i<data.length;i++){
			labels.append('<span class="weui-btn weui-btn_mini weui-btn_defualt checkBtn" value="'+data[i].lcode+'">'+data[i].lname+'</span>\n')
		}
		labels.append('<br> <button class="weui-btn weui-btn_mini commitBtn">筛选</button> <br> <div class="weui-panel__hd" style="padding:0;"></div>');
		onCheckBtn();
	});
}
function freshList(){
	dises = $('#districts');
	labels = $('#labels');
	dates = $('#stime');
	stdate = $('#jzstdate');
	disbtns = dises.find('.checkBtnActive');
	labelbtns = labels.find('.checkBtnActive');
	datesbtns = dates.find('.checkBtnActive');
	dises = 'all';
	labels = 'all';
	dates = 'all';
	flag = true;
	if(disbtns.length >0 && $(disbtns[0]).attr('value') != 'all'){
		for(i=0;i<disbtns.length;i++){
			if(flag){
				dises = $(disbtns[i]).html();
				flag = false;
			}else{
				dises += ','+$(disbtns[i]).html();
			}
		}
	}
	flag = true;
	if(labelbtns.length >0 && $(labelbtns[0]).attr('value') != 'all'){
		for(i=0;i<labelbtns.length;i++){
			if(flag){
				labels = $(labelbtns[i]).html();
				flag = false;
			}else{
				labels += ','+$(labelbtns[i]).html();
			}
		}
	}
	if(datesbtns.length == 0){
		var time = stdate.val();
		if(time != ''){
			dates = time;
		}
	}
	dises_bak.html(dises);
	labels_bak.html(labels);
	dates_bak.html(dates);
	$('#slocal').hide();
	$('#stype').hide();
	$('#stime').hide();
	onLoad();
}
function localset(){
	var t_cityCode = $.cookie('citycode');
	if(t_cityCode !=null&& t_cityCode.length > 5){
		ncity.attr('value',t_cityCode);
		ncity.html($.cookie('cityname'));
	}else{
		$.getScript('http://pv.sohu.com/cityjson?ie=utf-8', function(data){
		    console.log(returnCitySN.cip);
		    cname = returnCitySN.cname.replace(/.*[省|自治区]/,'');
		    if(cname == ''){
		    	alert('定位失败，请手动选择!');
		    }else{
			    ncity.html(cname);
		    }
//		    $.getJSON('http://ip.taobao.com/service/getIpInfo.php?ip='+returnCitySN.cip+'&callback=?',function(data){
//		    	console.log(data);
//		    });
		    console.log(returnCitySN.cip);
		    $.ajax({
		    	dataType:'jsonp',
		    	url:'http://ip.taobao.com/service/getIpInfo.php',
		    	jsonpCallback: 'json',
		    	jsonp:'callback',
		    	data:{ip:returnCitySN.cip},
		    	success: function(data){
		    		console.log(data[data]);
		    	}
		    });
		});
	}
}
function setLocal(){
	cc = $('#city-picker');
	city = cc.val().split(' ')[1];
	ncity.html(city);
	ncity.attr('value',cc.attr('data-code'));
	$.cookie('citycode',cc.attr('data-code'),{expires:30,path:'/'});
	$.cookie('cityname',city,{expires:30,path:'/'});
}