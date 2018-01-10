<%@page import="com.eos.data.datacontext.UserObject"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<!DOCTYPE html>
<html>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-12
  - Description:TB_IRM_RATING_MODEL, com.bos.dataset.irm.TbIrmRatingModel
-->
<%
UserObject user = (UserObject)session.getAttribute("userObject");
String userName = "";
String orgName = "";
String userId = "";
String orgId = "";
String orgLevel="";
Map map;
if(user!=null){
	 userName = user.getUserName();
	 orgName = user.getUserOrgName();
	 userId = user.getUserId();
	 orgId = user.getUserOrgId();
	 map=user.getAttributes();
	 orgLevel=map.get("orglevel").toString();
	
}
%>

<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="tbIrmModelScale.modelId"  value="<%=request.getParameter("modelId") %>"   class="nui-hidden"  />
	<div class="nui-dynpanel" columns="4">

		<label>模型类型：</label>
		<input name="tbIrmRatingModel.modelTypeCd" id="modelTypeCd" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_PJCD0011"  vtype="maxLength:6" />
		
		<label>模型版本：</label>
		<input name="tbIrmRatingModel.ratingModelVer" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>模型名称：</label>
		<input name="tbIrmRatingModel.modelName" required="false" class="nui-textbox nui-form-input"   vtype="maxLength:20" />

		<!--<label>适用规模：</label>
		<input name="tbIrmRatingModel.modelSuitScale" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="ABF_YESORNO" vtype="maxLength:20" />-->
	
		<label>状态：</label>
		<input name="tbIrmRatingModel.modelState" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_PJCD0012" vtype="maxLength:6" />

	
	</div>
</div>
				
<div class="nui-toolbar" style="text-align:right;padding-right:20px;border:0;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="add()" >增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
	<a class="nui-button" onclick="copy()">复制模型</a>
	&nbsp;
	&nbsp;
	<a class="nui-button" onclick="setNormal1()">审核模型</a>
	<a class="nui-button" onclick="setNormal()">发布模型</a>
	<!--<a class="nui-button" onclick="setAbNormal()">禁用模型</a>-->
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.irm.model.getTbIrmRatingModelList.biz.ext"
	dataField="tbIrmRatingModels"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>

		<div field="modelTypeCd" headerAlign="center" allowSort="true" dictTypeId="XD_PJCD0011">模型类型</div>
		<div field="ratingModelVer" headerAlign="center" allowSort="true" >模型版本</div>
		<div field="modelName" headerAlign="center" allowSort="true" >模型名称</div>
		<!--<div field="modelSuitScale" headerAlign="center" allowSort="true" >适用规模</div>-->
		<div field="modelState" headerAlign="center" allowSort="true"  dictTypeId="model_model_status" >状态</div>
		<div field="startDate" headerAlign="center" allowSort="true" >启用日期</div>
		<div field="expirationDt" headerAlign="center" allowSort="true" >失效日期</div>
		<div field="createTime" headerAlign="center" allowSort="true" >创建日期</div>
		<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">创建人</div>
		<div field="approvalDate" headerAlign="center" allowSort="true" >审核日期</div>
		<div field="approvalUserNum" headerAlign="center" allowSort="true" dictTypeId="user">审核人</div>
		<div field="deployDate" headerAlign="center" allowSort="true" >发布日期</div>
		<div field="deployUserNum" headerAlign="center" allowSort="true" dictTypeId="user">发布人</div>
		</div>
	</div>
			
    <script type="text/javascript">
 	nui.parse();
 	git.mask();
 	
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	//alert(getDictData("XCH00002","str","C1,C2,C3,C4,C5,C6,C7,C8,C9,F1,F2,F3,F4,F5,P1,P2,P3,S1,S2,98"));
	nui.get("modelTypeCd").setData(getDictData("XD_PJCD0011","str","C1,C2,C3,C4,C5,C6,C7,C8,C9,C10,F1,F2,F3,F4,F5,P1,P2,P3,S1,S2,98"));
	
    function search() {
    	
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
        git.unmask();
    }
    search();
    
    function reset(){
		form.reset();
	}
	
    function add() {
        nui.open({
            url: "<%=request.getContextPath() %>/irm/model/item_add.jsp",
            title: "新增", 
            width: 800, 
        	height: 500,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                if(action=="ok"){
                    search();
                }
            }
        });
    }
	
    function copy() {
      		
        var row = grid.getSelected();
	    if(row){  
	        nui.open({
	            url: "<%=request.getContextPath() %>/irm/model/copyModel.jsp?modelId="+row.modelId,
	            title: "复制模型", 
	            width: 800, 
	        	height: 500,
	        	allowResize:true,
	        	showMaxButton: true,
	            ondestroy: function (action) {
	                if(action=="ok"){
	                    search();
	                }
	            }
	        });
	   	}else {
            alert("请选中一条记录");
        }
    }
    
    
    function edit(v) {
        var row = grid.getSelected();
        
        if (row) {
	        if(row.modelState=='2'&&v=='0'){
	        	alert("不能修改正在使用的模型");
	        	return;
	        }
            nui.open({
                url: "<%=request.getContextPath() %>/irm/model/model_tab.jsp?modelId="+row.modelId+"&modelNum="+row.modelNum+"&view="+v,
                title: "编辑", 
                width: 1024, 
	        	height: 720,
	        	state:"max",
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                    
                    //iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                    if(action=="close"){
                        search();
               	 	}
                }
            });
        } else {
            alert("请选中一条记录");
        }
        
    }
    
    function remove() {
        var row = grid.getSelected();
        if (row) {
	         if(row.modelState=='2'){
	        	alert("不能删除正在使用的模型");
	        	return;
	        }
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            		git.mask();
            	var json=nui.encode({"tbIrmRatingModel":{"modelId":
            		row.modelId,version:row.version}});
                $.ajax({
                     url: "com.bos.irm.model.delTbIrmRatingModel.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}
                        search();
                   		git.unmask();
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    	git.unmask();
                    }
                });
            }); 
        } else {
            alert("请选中一条记录");
        }
    }
    
       function setNormal1() {//审核
            var row = grid.getSelected();
        
            if (row) {
            	if(row.userNum == "<%= userId%>"){
            		alert("创建人和审核人不能相同");
            		return;
            	}
            	if (row.approvalUserNum != null) {
            		nui.alert("模型已审核");
            		return;
            	}
            	
            	nui.confirm("确定模型审核通过吗？","确认",function(action){
	            	if(action!="ok") return;
	            	var data=nui.clone(row);
    			
	            	
	            	var json = nui.encode({"tbIrmRatingModel":data});//nui.alert(json);return;
	                $.ajax({
	                     url: "com.bos.irm.model.getApprovalUserNum.biz.ext",
		                type: 'POST',
		                data: json,
		                cache: false,
		                contentType:'text/json',
	                    success: function (text) {
	                    	if (text.msg) {
	                    		nui.alert(text.msg);
	                    		return;
	                    	}
	                        grid.reload();
	                    },
	                    error: function () {
	                    	nui.alert("操作失败！");
	                    }
	                });
                }); 
            } else {
                nui.alert("请选中一条记录");
            }
        }
    
    
         function setNormal() {//发布
            var row = grid.getSelected();
        
            if (row) {
          		if(row.approvalUserNum == null){
          			alert("请先审核再发布");
          			return;
          		}
            	if (row.deployDate != null) {
            		nui.alert("模型已发布");
            		return;
            	}
            	
            	nui.confirm("确定发布模型吗？","确认",function(action){
	            	if(action!="ok") return;
	            	var data=nui.clone(row);
    				
	            	
	            	var json = nui.encode({"tbIrmRatingModel":data});//nui.alert(json);return;
	                $.ajax({
	                     url: "com.bos.irm.model.getOpeningDt.biz.ext",
		                type: 'POST',
		                data: json,
		                cache: false,
		                contentType:'text/json',
	                    success: function (text) {
	                    	if (text.msg) {
	                    		nui.alert(text.msg);
	                    		return;
	                    	}
	                        grid.reload();
	                    },
	                    error: function () {
	                    	nui.alert("操作失败！");
	                    }
	                });
                }); 
            } else {
                nui.alert("请选中一条记录");
            }
        }
        
        function setAbNormal() {//禁用
            var row = grid.getSelected();
            
            if (row) {
            	if (row.expirationDt != null) {
            		nui.alert("模型已禁用");
            		return;
            	}
            	
            	nui.confirm("确定禁用吗？","确认",function(action){
	            	if(action!="ok") return;
	            	var data=nui.clone(row);
	            	data.modelState='3';
	            	var json = nui.encode({"tbIrmRatingModel":data});//nui.alert(json);return;
	                $.ajax({
	                     url: "com.bos.irm.model.getExpirationDt.biz.ext",
		                type: 'POST',
		                data: json,
		                cache: false,
		                contentType:'text/json',
	                    success: function (text) {
	                    	if (text.msg) {
	                    		nui.alert(text.msg);
	                    		return;
	                    	}
	                        grid.reload();
	                    },
	                    error: function () {
	                    	nui.alert("操作失败！");
	                    }
	                });
                }); 
            } else {
                nui.alert("请选中一条记录");
            }
        }
        
        	//通用获取业务字典方法
	//param1.dictId:业务字典ID; 
	//param2.type:过滤类型(str:指定字符id过滤(多id以","隔开);sub:获取指定字符串子集;top:获取顶级业务字典)
	//param3.指定的字符串(type为top时可以为空,不做处理)
	
	function getDictData(dictId,type,str){
		var dictData = nui.getDictData(dictId);//获取业务字典的数据
		var arr = nui.encode(dictData).split("},");//业务字典数据字符串化，方便处理
		var strArr = new Array();
		//将字符串存入数组
		if(str.indexOf(",") != -1){
			strArr = str.split(",");
		}else{
			strArr.push(str);
		}
		var dictStr = "";//拼接业务字典字符串
		if(type == "str"){//如果是指定字符串过滤
			for(var i = 0;i<arr.length;i++){
				for(var n = 0;n<strArr.length;n++){
					var flag = arr[i].indexOf('"dictID":"'+strArr[n]+'"')!="-1";//如果包含指定的字符串
					if(flag){
						dictStr = contactStr(i,dictStr,arr);
					}
				}
			}
		}else if(type == "sub"){//如果是只获取指定字符串子集
			for(var i = 0;i<arr.length;i++){
				for(var n = 0;n<strArr.length;n++){
					var s = strArr[n];
					//var flag = arr[i].indexOf('"dictID":"'+s)!="-1";//必须为指定字符串及其子项
					//var flag1 = arr[i].indexOf('"dictID":"'+s+'"')=="-1";//不能为父项
					var flag2 = arr[i].indexOf('"parentid":"'+s+'"')!="-1";//必须为子项（不包含子项的子项）
					if(flag2){
						dictStr = contactStr(i,dictStr,arr);
					}
				}
			}
		}else if(type == "top"){//如果是只获取最顶级业务字典
			for(var i = 0;i<arr.length;i++){
				var flag = arr[i].indexOf('"parentid":"null"')!="-1";//必须为顶级业务字典
				if(flag){
					dictStr = contactStr(i,dictStr,arr);
				}
			}
		}
		//如果最后一个字典项不符合条件，则增加结束标识符号“}]”
		if(dictStr.charAt(dictStr.length-1) != "]"){
			dictStr = dictStr + "}]";
		}
		var dict = nui.decode(dictStr);
		return dict;
	}
	
		//根据索引值，字符串和数组值拼接(用于过滤业务字典-getDictData)
	function contactStr(index,str,arr){
		if(index == 0){
			str = str + arr[index];
		}else if(index != (arr.length)){
			if(str == ""){
				str = "[" + arr[index];
			}else{
				str = str + "}," + arr[index];
			}
		}
		return str;
	}
		
	</script>
</body>
</html>
