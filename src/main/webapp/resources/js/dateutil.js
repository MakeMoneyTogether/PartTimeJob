function stamp2YMD(ts){
	time = new Date(ts);
	ymd = time.toLocaleDateString().split(/[-/年月日]/);
	if(ymd[1].length == 1){
		ymd[1] = '0'+ymd[1];
	}
	if(ymd[2].length == 1){
		ymd[2] = '0'+ymd[2];
	}
	res = ymd[0]+'-'+ymd[1]+'-'+ymd[2];
	if(time.getHours()<10){
		res += ' 0'+time.getHours();
	}else{
		res += ' '+time.getHours();
	}
	if(time.getMinutes()<10){
		res += ':0'+time.getMinutes();
	}else{
		res += ':'+time.getMinutes();
	}
	return res;
}