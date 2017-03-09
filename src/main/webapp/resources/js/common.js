
	$(document).ready(function(){
		//tab切换	
		$(".m_r_tab ul li").click(function(){
			$(this).addClass("current").siblings().removeClass("current");
			var tab = $(this).attr("flag");
			$("#"+tab).show().siblings().hide();
		});
		
		//导航选中事件
		$('.menu ul li').each(function(){
			var Ahref = $(this).find('a').attr('href');
			var _url = window.location.href;
			if( _url.indexOf(Ahref) > 0){
				$(this).addClass("current")&&$(this).siblings().removeClass("current");
				if($(this).hasClass("nav")){
					$(".position-list").show();
					var tab = $(this).attr("flag");
					$("#"+tab).show().siblings().hide();
				}
			}
		});
		
		
		
		//导航鼠标滑动事件
		$(".menu").on("mouseover","li",function(){
			if($(this).hasClass("nav")){
				$(".position-list").show();
				$(this).addClass("current").siblings().removeClass("current");
				var tab = $(this).attr("flag");
				$("#"+tab).show().siblings().hide();
			}else{
				//鼠标划中后获得当前颜色
				$(this).addClass("current").siblings().removeClass("current");
			}
		});
	
	//导航选中事件
	$('.m_l_list ul li').each(function(){
		var Ahref = $(this).find('a').attr('href');
		var _url = window.location.href;//alert("Ahref="+Ahref+"--- _url="+_url);
		if( _url.indexOf(Ahref) > 0){//alert("dia");
			$(this).addClass("first")&&$(this).siblings().removeClass("first");
		}
	});
});
	
function showMenuList(obj){
	var menuDiv = $(obj).parent().parent().parent();
	var dd = $(obj).parent();
	var menuId = menuDiv.attr("id");
	$(".position-list").show();
	$('.menuDiv').hide();
	$('#' + menuId).show();
	$(".position-list").mouseleave(function(){
		$('.menuDiv').hide();
		$('#' + menuId).show();
		$('.menu').find('.current').removeClass('current');
		$('#' + menuId + "-parent").addClass("current");
	});
	$('.menu').find('.current').removeClass('current');
	$('#' + menuId + "-parent").addClass("current");
	$('.position-list').find('.current').removeClass('current');
	dd.addClass("current");
}

function slide(button,div){
	$(button).text($(div).is(":hidden") ? "-" : "+");
	 $(div).slideToggle();
}
function showMenuListLvl1(obj){
	var li = $(obj).parent();
	var menuId = li.attr("flag");
	var dd = $('#' + menuId).find('dd:eq(0)');
	$(".position-list").show();
	$('.menuDiv').hide();
	$('#' + menuId).show();
	$(".position-list").mouseleave(function(){
		$('.menuDiv').hide();
		$('#' + menuId).show();
		$('.menu').find('.current').removeClass('current');
		$('#' + menuId + "-parent").addClass("current");
	});
	$('.menu').find('.current').removeClass('current');
	$('#' + menuId + "-parent").addClass("current");
	$('.position-list').find('.current').removeClass('current');
	dd.addClass("current");
	
}

// JavaScript Document
/**
 * ie和fifox兼容性测试
 */
if (!console) {
	var console = {
		debug : function(){return false;},
		info : function(){return false;},
		warn : function(){return false;},
		error : function(){return false;},
		trace : function(){return false;},
		dir : function(){return false;},
		log : function(){return false;}
	};
}

/**
 * 获取复选框选中集合
 */
function getCheckboxValues(key){
	var keyValues="";
	$("input[name='"+key+"']:checkbox:checked").each(
		function(){
			keyValues+=$(this).val()+",";
		}
	);
	if(keyValues.length>0){
		keyValues=keyValues.substring(0,keyValues.length-1);
	}
	return keyValues;
}
/**
 * ajax请求
 */
function doAjax(url,data){
	var result;
	$.ajax({
	     type:"post",
	     url:webRoot+url+"?dt="+new Date().getTime(),
	     data:data,
	     async:false,
	     success:function(msg){
		 	result = msg;
    	 }
	 });
	return result;
}


/**
 * form Post请求(用于url安全)
 */
function doPost(url,data){
	var form = $('<form id="formId" action="'+webRoot+url+'" method="post"></form>');
		if(null!=data){
		for(var key in data){
			var val=data[key];
			form.append("<input type='hidden' name='"+key+"' value='"+val+"'/>");
		}
	}
	form.appendTo("body");
	
	form.submit();
}


function doGet(url,data){
	if(!data)
		window.location.href=url;
	else
		window.location.href=url+'?'+data;
}


/**
 * 根据formName动态解析form元素，并返回json对象
 * @param formName
 * @returns {___anonymous1290_1291}
 */
function getFormJson(formName){
	var o = {};
	var a = $(formName).serializeArray();
	$.each(a, function(){
		if(o[this.name] !== undefined){
			if(!o[this.name].push){
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value || "");
		}else{
			o[this.name] = this.value || "";
		}
	});
	return o;
}

/**
 * 根据formId动态解析form元素，并返回json对象
 * @param formId
 * @returns {___anonymous1773_1774}
 */
function getFormJsonById(formId){
	var o = {};
	var a = $("#"+formId).serializeArray();
	$.each(a, function(){
		if(o[this.name] !== undefined){
			if(!o[this.name].push){
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value || "");
		}else{
			o[this.name] = this.value || "";
		}
	});
	return o;
}



/**
 * table全选和全不选
 */
function checkSelect(checkId,keyNm){
	$("#"+checkId).bind("click",function(){
		var obj = $(this);
		var flag = obj["context"].checked;
		if (flag) {
			$("input[name="+keyNm+"]").prop("checked",$(this).prop("checked"));
		}else{
			$("[name="+keyNm+"]:checkbox").attr("checked",null);
		}
	});
}


/**
 * 加载JSON数据到form中，使用方法 $("form").setForm(data)
 */
$.fn.setForm = function(jsonValue){
	var obj = this;
	$.each(jsonValue, function(name, ival){
		var $oinput = obj.find("input[name="+name+"]");
		if($oinput.attr("type") == "radio" || $oinput.attr("type") == "checkbox"){
			$oinput.each(function(){
				if(Object.prototype.toString.apply(ival) == "[object Array]"){
					for(var i=0; i<ival.length; i++){
						if($(this).val() == ival[i])
							$(this).attr("checked","checked");
					}
				}else{
					if($(this).val() == ival)
						$(this).attr("checked","checked");
				}
			});
		}else if($oinput.attr("type") == "textarea"){
			obj.find("[name="+name+"]").html(ival);
		}else if($oinput.attr("type") == "select"){
			obj.find("option[text="+name+"]").attr("selected",true);
		}else{
			obj.find("[name="+name+"]").val(ival);
		}
		
		obj.find("select[name="+name+"] option").each(function(){
			if($(this).text() == ival)
				$(this).attr("selected",true);
		});
		
		obj.find("span[name="+name+"]").html(ival);
	});
};

/**
 * 清空Form
 */
$.fn.clearForm = function(includeHidden){
	return this.each(function(){
		$('input,select,textarea',this).clearFields(includeHidden);
		$('.select2',this).val(null).trigger("change");
	});
};
$.fn.clearFields = $.fn.clearInputs = function(includeHidden){
	var re = /^(?:color|date|datetime|email|month|number|password|range|search|tel|text|time|url|week)$/i;
	return this.each(function(){
		var t = this.type,tag = this.tagName.toLowerCase();
		if(re.test(t) || tag == 'textarea'){
			this.value = '';
		}else if(t == 'checkbox' || t == 'radio'){
			this.checked = false;
		}else if(tag == 'select'){
			this.selectedIndex = -1;
		}else if(t == "file"){
			if(/MSIE/.test(navigator.userAgent)){
				$(this).replaceWith($(this).clone(true));
			}else{
				$(this.val(''));
			}
		}else if(includeHidden){
			if((includeHidden === true && /hidden/.test(t))
					|| (typeof includeHidden == 'string' && $(this).is(includeHidden))){
				this.value='';
			}
		}
	});
};

function getWinHeight() {
	var winHeight = 0;
	// 获取窗口高度
	if (window.innerHeight)
		winHeight = window.innerHeight;
	else if ((document.body) && (document.body.clientHeight))
		winHeight = document.body.clientHeight;
	// 通过深入 Document 内部对 body 进行检测，获取窗口大小
	if (document.documentElement && document.documentElement.clientHeight) {
		winHeight = document.documentElement.clientHeight;
	}
	return winHeight;
}

function getWinWidth() {
	var winWidth = 0;
	// 获取窗口宽度
	if (window.innerWidth)
		winWidth = window.innerWidth;
	else if ((document.body) && (document.body.clientWidth))
		winWidth = document.body.clientWidth;
	
	// 通过深入 Document 内部对 body 进行检测，获取窗口大小
	if (document.documentElement
			&& document.documentElement.clientWidth) {
		winHeight = document.documentElement.clientHeight;
		winWidth = document.documentElement.clientWidth;
	}
	return winWidth;
}

