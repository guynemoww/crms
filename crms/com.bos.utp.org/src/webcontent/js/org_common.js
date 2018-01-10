(function(){
	Utils = {version:"1.0"};
	
	Utils.reg = {
		phone : /^[0-9\-]+$/
	}
	
	 nui.VTypes["phoneErrorText"] = "电话号码中只能包含数字和中划线";
     nui.VTypes["phone"] = function(v){
     	if(v){
	         if (Utils.reg.phone.test(v)) return true;
	         return false;
     	}
     	return true;
     }
	
	window['Utils'] = Utils;
})();
/**
 * 在全局范围内查找指定title名称的window对象
 */
function findWindowByTitle(win, titleName) {
	if(!titleName) return null;

	win = win || window;
	
	if(win.document.title == titleName) {
		return win;
	}
	
	var resultWin;
	
	if(win.parent && win.parent != win) {
		resultWin = findWindowByTitle(win.parent, titleName);
	}
	
	if(resultWin) {
		return resultWin;
	}
	
	var iframes = win.document.getElementsByTagName("iframe"); 

	for(var i=0; i < iframes.length; i++) { 
		if(iframes[i].contentWindow.document.title == titleName) {
			return iframes[i].contentWindow;
		}
	}
	
	return null;
}

/**
 * 在全局范围内查找"__orgTree__"页面的window对象
 */
function findOrgTreeWindow() {
	return findWindowByTitle(window, "__orgTree__");
}
/**
* 打开弹出日历控件
*/
function openCalendar(e){
 	var calendar = this;
 	var data = calendar.getValue() || calendar.getText();
 	mini.open({
         url: bootPath + "/utp/org/common/calendar.jsp",
         showMaxButton: false,
         allowResize: false,
         //width: 222,
        // height: 211,
         width: 222,
         height: 211,
         onload:function(){
             var iframe = this.getIFrameEl();
             iframe.contentWindow.SetData(data);
         },
         ondestroy:function (){
             var iframe = this.getIFrameEl();
             var data = iframe.contentWindow.GetData();
             data = nui.clone(data);
             if(data){
             	var date = nui.formatDate(data,"yyyy-MM-dd");
             	calendar.setText(date);
             	calendar.setValue(date);
             }
         }
     });
 }

function CloseWindow(action) {
     if (action == "close" && form.isChanged()) {
         if (confirm("数据被修改了，是否先保存？")) {
             return false;
         }
     }
     if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
     else window.close();            
 }
 
function cancel(){
	CloseWindow("cancel");
}
