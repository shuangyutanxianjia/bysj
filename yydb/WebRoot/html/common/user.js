//检查用户是否登录
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

