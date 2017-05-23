function genItem(data){
	item = '<div class="weui-cell"><div class="weui-cell__hd" style="color:red;">'
			+data.grade
			+'</div><div class="weui-cell__bd"><p>'
			+data.name
			+'</p></div><div class="weui-cell__ft"><button onclick="refuse('
			+data.user_id
			+')" class="weui-btn weui-btn_mini weui-btn_warn">拒绝</button></div></div>';
	return item;
}

function getPeople(){
	list = $('#list');
	datas = [{user_id:1,name:'张三',grade:4.9},{user_id:2,name:'李四',grade:5.0},{user_id:3,name:'王五',grade:3.2}]
	for(i =0;i<datas.length;i++){
		list.append(genItem(datas[i]));
	}
}

function refuse(e,uid){
	var jid = $('#jid').html();
	$.ajax({
		type:'POST',
		url: 'mchnt/refuse',
		dataType:'json',
		data:{uid:uid,jid:jid},
		success: function(data){
			if(data == 0){
				$(e).parent().parent().remove();
			}
		}
	});
}