function GetQueryString() {
	var result = new Map();
	if (document.location.search.length > 1) {
		// 最初の1文字 (?記号) を除いた文字列を取得する
	    var query = document.location.search.substring(1);

	    // クエリの区切り記号 (&) で文字列を配列に分割する
	    var parameters = query.split('&');
	    
	    for (var i = 0; i < parameters.length; i++) {
	      // パラメータ名とパラメータ値に分割する
	      var element = parameters[i].split('=');

	      var key = decodeURIComponent(element[0]);
	      var value = decodeURIComponent(element[1]);

	      // パラメータ名をキーとして連想配列に追加する
	       result.set(key, decodeURIComponent(value));
	    }
	}
	  
	return result;
}