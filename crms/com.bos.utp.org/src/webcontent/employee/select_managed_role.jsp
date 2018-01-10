<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): yangyong
  - Date: 2013-02-28 10:14:50
  - Description:
-->
<head>
<title>选择可管理角色</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div class="nui-fit" style="padding:5px;">
	<div id="listbox1" class="nui-listbox" url="com.bos.utp.org.employeeAuth.getEmpAuthorizedRoles.biz.ext" style="width:388px;height:100%;"
        textField="NAME" valueField="ID"   multiSelect="false"
       	dataField="authorizedRoles" showCheckBox="true">
     	<div property="columns">
           <div header="可选角色" field="NAME"></div>
        </div>
    </div>
</div>
<div id="toolBar" class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;visibility:hidden;" 
    borderStyle="border:0;">
    <a class="nui-button"  iconCls="icon-ok" onclick="ok">确定</a>
    <span style="display:inline-block;width:25px;"></span>
    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel"  onclick="cancel">关闭</a>
</div>
<script type="text/javascript">
	 nui.parse();
	
	 var listbox1 = nui.get("listbox1");
    
    $(function(){
		if(window.parent.getCurrentNode){ // 从Tab页进入时
			var node = window.parent.getCurrentNode();
			var parentNode = node;
			window['parentNode'] = parentNode;
		}
	});
	
	// 弹出窗口时调用
	function SetData(data){
		// 显示关闭按钮
		$("#toolBar").css("visibility", "visible");
		data = nui.clone(data);
		if(data && data.parentNode){
			window['parentNode'] = data.parentNode;
		}
        var level="<%=null == request.getParameter("level") ? ((UserObject)session.getAttribute("userObject")).getAttributes().get("orglevel") : request.getParameter("level") %>";
        if (level=="null"||level=="")
        	level="1";
        if(!!level && level != '1'){
        	var listdata = listbox1.getData();
        	console.log('listdata:'+nui.encode(listdata));
	        for(var j=0,len=listdata.length;j<len;j++){
	        	var level2=listdata[j].LEVEL;
	            if(level2 < level || level2 >= 'A'){
	                listbox1.removeItems([listdata[j]]);
	            }
	        }
	        console.log('listdata:'+nui.encode(listbox1.getData()));
        }
		
		
		if(data.ids == "") return;
		var ids = data.ids.split(",");
        if(ids!=""){
	       for(var i=0;i<ids.length;i++){
	          var id = ids[i].split(":");
	          var listdata = listbox1.getData();
	          for(var j=0,len=listdata.length;j<len;j++){
	              if(listdata[j].ID==id[0]){
	                  listbox1.select(listdata[j]);
	                  break;
	              }
	          }
	       }
        }
	}
	
	function GetData() {
		var items = listbox1.getSelecteds();
		var ids = [];
		var texts = [];
		var levels = [];
		for(var i=0;i<items.length;i++){
			ids.push(items[i].ID);
			texts.push(items[i].NAME);
			levels.push(items[i].LEVEL);
		}
        return {id:ids.join(","),text:texts.join(","),level:levels.join(",")};
    }
    
	
	
	function CloseWindow(action) {            
        if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
        else window.close();            
    }
    
    function ok(){
    	var items = listbox1.getSelecteds();
    	if (items && items.length > 1) {
    		alert('请选择一条记录');
    		return;
    	}
    	
        CloseWindow("ok");
    }
    
    function cancel() {
    	CloseWindow("cancel");
    }
</script>
</body>
</html>