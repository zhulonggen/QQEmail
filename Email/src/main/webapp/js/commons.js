const baseUrl ="http://localhost:8080/Email/";



//ajax post 请求
function post(params){
	$.ajax({
		   type: "POST",
		   url: baseUrl + params.url,
		   data: params.data,
		   dataType : 'json',
		   success: function(resp){
				if(resp.code == 200){
					try{
						params.execute(resp.data);
					}catch(e){
						console.log(e);
					}
				}else if(resp.code == 1){
				   window.location.href = baseUrl +"/login.html";
				}else{
					alert(resp.msg);
				}
		   }
		});
}

//ajax get 请求
function get(){
	
}


