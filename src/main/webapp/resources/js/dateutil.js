function stamp2YMD(ts){
	time = new Date(ts);
	ymd = time.toLocaleDateString().split('-');
	if(ymd[1].length == 1){
		ymd[1] = '0'+ymd[1];
	}
	if(ymd[2].length == 1){
		ymd[2] = '0'+ymd[2];
	}
	return ymd[0]+'-'+ymd[1]+'-'+ymd[2];
}