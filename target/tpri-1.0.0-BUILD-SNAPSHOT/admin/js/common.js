function jump(t_url) {
	window.location.href = "./jump?url=" + t_url;
}

function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

function getTimeStr(time) {
	var dateStr = "";
	var date = new Date();
	date.setTime(time);
	dateStr += date.getFullYear();
	dateStr += "-" + getMonth(date);
	dateStr += "-" + getDay(date);
	return dateStr;
}

function getMonth(date) {
	var month = "";
	month = date.getMonth() + 1; //getMonth()得到的月份是0-11  
	if (month < 10) {
		month = "0" + month;
	}
	return month;
}

//返回01-30的日期  
function getDay(date) {
	var day = "";
	day = date.getDate();
	if (day < 10) {
		day = "0" + day;
	}
	return day;
}