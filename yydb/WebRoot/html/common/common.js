/**
* 收敛方法获取参数
* @param {Object} sHref
* @param {Object} sArgName
*/
function GetArgsFromHref(sHref, sArgName) {
	var args = sHref.split("?");
	var retval = "";

	if(args[0] == sHref) /*参数为空*/ {
		return retval; /*无需做任何处理*/
	}
		var str = args[1];
		args = str.split("&");
		for(var i = 0; i < args.length; i++) {
			str = args[i];
			var arg = str.split("=");
			if(arg.length <= 1) continue;
			if(arg[0] == sArgName) {
				retval = arg[1];
			}
		}
		return retval;
	}
		
//验证用户登录
function isLogin(c){
		
		$.ajax({
			type:"POST",
           	url:"../loginchk.action",
           	data:"",
           	dataType:"json",
           	success:function(json){          		
           		return c(json);
           	},error:function(json){
           		alert("json="+json);
           		return c(false);
           	}
		});
}


//获取随机数
			function GetRandomNum(Min, Max) {
				var Range = Max - Min;
				var Rand = Math.random();
				return(Min + Math.round(Rand * Range));
			}