var EGOCart = {
	load : function(){ // 加载购物车数据
		
	},
	itemNumChange : function(){
		/*给增加按钮绑定了一个单击事件*/
		$(".increment").click(function(){//＋
			/*获得文本框中输入的数量*/
			var _thisInput = $(this).siblings("input");
			_thisInput.val(eval(_thisInput.val()) + 1);
			/*
			* url : /cart/update/num/153153486789528/3.html
			* 返回值："OK"
			* */
			/*发送一个ajax请求，带着商品ID和增加后的值给后台进行数据的更新*/
			$.post("/cart/update/num/"+_thisInput.attr("itemId")+"/"+_thisInput.val() + ".html",function(data){
				EGOCart.refreshTotalPrice();
			});
		});
		/*给减少按钮绑定了一个单击事件*/
		$(".decrement").click(function(){//-
			var _thisInput = $(this).siblings("input");
			if(eval(_thisInput.val()) == 1){
				return ;
			}
			_thisInput.val(eval(_thisInput.val()) - 1);
			/*
			 * url : /cart/update/num/153153486789528/3.html
			 * 返回值："OK"
			 * */
			$.post("/cart/update/num/"+_thisInput.attr("itemId")+"/"+_thisInput.val() + ".html",function(data){
				EGOCart.refreshTotalPrice();
			});
		});
		$(".quantity-form .quantity-text").rnumber(1);//限制只能输入数字
		$(".quantity-form .quantity-text").change(function(){
			var _thisInput = $(this);
			/*
			 * url : /cart/update/num/153153486789528/3.html
			 * 返回值："OK"
			 * */
			$.post("/cart/update/num/"+_thisInput.attr("itemId")+"/"+_thisInput.val() + ".html",function(data){
				EGOCart.refreshTotalPrice();
			});
		});
	},
	refreshTotalPrice : function(){ //重新计算总价
		var total = 0;
		$(".quantity-form .quantity-text").each(function(i,e){
			var _this = $(e);
			total += (eval(_this.attr("itemPrice")) * 10000 * eval(_this.val())) / 10000;
		});
		$(".totalSkuPrice").html(new Number(total/100).toFixed(2)).priceFormat({ //价格格式化插件
			 prefix: '￥',
			 thousandsSeparator: ',',
			 centsLimit: 2
		});
	}
};

$(function(){
	EGOCart.load();
	EGOCart.itemNumChange();
});