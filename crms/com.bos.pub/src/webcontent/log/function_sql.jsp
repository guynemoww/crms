<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): chenchuan@git.com.cn
  - Date: 2016-6-23
  - SQL查询,根据输入的SQL语句查询到结果
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<title>SQL查询</title>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:115%;">
<div title="配置查询" >
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="sqlName" value="com.bos.utp.tools.common.common_select" class="nui-hidden" />
		
		<label>查询配置：</label>
		<input id="item.sql" name="item.sql"class="nui-textarea nui-form-input" required="false"
		style="width:90%;height:150px"/>		
	
	<div class="nui-toolbar" style="text-align:center;padding-top:10px;padding-right:25px;"borderStyle="border:0;">
	    <a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
			
		<label>查询结果：</label>
		<input id="result" name="result"class="nui-textarea nui-form-input" 
			style="width:90%;height:300px"/>	
		
	<div class="nui-toolbar" style="text-align:center;padding-top:10px;padding-right:25px;"borderStyle="border:0;">
	    <a id = "btnClose" class="nui-button" iconCls="icon-close"  onclick="CloseWindow('ok')">关闭</a>
	</div>	
</div>	
</div>	</div>			
<script type="text/javascript">

	nui.parse();
	var form = new nui.Form("#form1");

	function query(){
	
    if (nui.get("item.sql").getValue()==""){
    	nui.get("result").setValue("请先填写SQL！");
        return ;
    }
    var sql=nui.get("item.sql").getValue();
    if(sql.indexOf("update")!=-1||sql.indexOf("insert")!=-1||sql.indexOf("alter")!=-1||sql.indexOf("delete")!=-1){
   		nui.get("result").setValue("只能填写查询语句！");
        return ;    
    } 
	var o=form.getData();
	var json=nui.encode(o);
	$.ajax({
				url: "com.bos.pub.log.getQuerySqlList.biz.ext",
				type: 'POST',
				data: json,
				cache: false,
				contentType:'text/json',
				success: function (text) {
				if(text.msg){
					nui.get("result").setValue(text.msg);
				}else{
					 var result="查询到"+text.items.length+"条记录:\n";
					 result+=nui.encode(text.items).replace("},{","},\n{");//将查询结果换行，并去掉[]
					 result=result.replace("[","");
					 result=result.replace("]","");
					nui.get("result").setValue(result);
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				git.unmask();
				nui.alert(jqXHR.responseText);
			}
		});
	}

	function reset() {
		form.reset();
		search();
	}
</script>
</body>
</html>
