function genPerson(person){
	str = '';
	if(person.status == '0' ||person.status == 0 ){
		str = '<div class="weui-cell weui-cell_access"> <div class="weui-cell__hd"> <i style="color:#999" class="fa fa-twitter"></i> </div> <div class="weui-cell__bd"> <p style="margin-left:10%;">'
			+ person.name
			+ '</p> </div> <div class="">未进行兼职</div> </div>';
	}else{
		str = '<div class="weui-cell weui-cell_access"> <div class="weui-cell__hd"> <i style="color:#99ccff" class="fa fa-twitter"></i> </div> <div class="weui-cell__bd"> <p style="margin-left:10%;">'
			+ person.name
			+'</p> </div> <div class=""> +5.00 元</div> </div>';
	}
	return str;
}

function getInvitation(){
	phone = $.cookie('phone');
	$.ajax({
		type:'POST',
		url: 'user/invitation',
		dataType:'json',
		data:{phone:phone},
		success: function(data){
			$('#inv_person').text('');
			str = '';
			for(i=0;i<data.length;i++){
				str += genPerson(data[i]);
			}
			$('#inv_person').html(str);
		}
	});
}