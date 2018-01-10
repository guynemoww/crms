<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/nui/common.jsp" %>
<!-- 
  - Author(s): ljf
  - Date: 2016-05-11
  - Description:维护产品参数信息
-->
<head>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input name="item._entity" value="com.bos.pub.product.TbSysProductParam" class="nui-hidden" />
	<input id="item.pId" name="item.pId" class="nui-hidden" />
	<input id ="item.productId" name="item.productId" class="nui-hidden" value="<%=request.getParameter("productId")%>"/>
	
	<div class="nui-dynpanel" columns="2" id="table">
		
		<label>参数类型：</label>
		<input id="item.paraType" name="item.paraType" required="true" class="nui-combobox" textField="text" valueField="id" emptyText="--请选择--" onvaluechanged="checkUnique"/>

		<label>参数字段：</label>
		<input id="item.paraColumn" name="item.paraColumn" required="true" class="nui-textbox nui-form-input" vtype="maxLength:30" emptyText="请输入映射后数据实体字段，非表字段" onblur="checkUnique"/>

		<label>参数字段名称：</label>
		<input id="item.paraColunmName" name="item.paraColunmName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:200" onblur="checkUnique"/>

		<label>参数运算符号：</label>
		<input id="item.paraCountSign" name="item.paraCountSign" required="true" class="nui-combobox" textField="text" valueField="id" onvaluechanged="checkSign"/>
		
		<label>控制参数一：</label>
		<input id="item.paraContrlLeftval" name="item.paraContrlLeftval" required="true" class="nui-textbox nui-form-input" vtype="maxLength:20" emptyText="请输入最小金额" onblur="loadDict"/>
	
		<label id="l_rval">控制参数二：</label>
		<input id="item.paraContrlRigthval" name="item.paraContrlRigthval" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" emptyText="请输入最大金额"/>
		
		<label id="l_dict">控制参数二：</label>
		<!-- <a id="item.paraDict" name="item.paraDict" valueField="dictId"  textField="dictName" multiSelect="true" class="nui-checkboxlist"></a> -->
		<div id="item.paraDict" name="item.paraDict" required="true" class="nui-combobox"  popupWidth="400" textField="dictName" valueField="dictID" 
		       multiSelect="true" showClose="true" oncloseclick="onCloseClick">     
		     <div property="columns">
		        <div header="字典选项名称" field="dictName"></div>
		     </div>
		</div>
		<label>授权机构：</label>
		<input id="item.authOrgNum" name="item.authOrgNum" required="true" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectOrg"/>
		
		<label>参数状态：</label>
		<input id="item.paraStatus" name="item.paraStatus" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_FLCD0015"/>
		
		<%--<label>演示参数状态：</label>
		<input id="paraStatus" name="paraStatus" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_FLCD0015"/>
		
		<label>演示参数类型：</label>
		<input id="paraType" name="paraType" required="true" class="nui-combobox" textField="dictName" valueField="dictID" emptyText="--请选择--"/>--%>
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a id="save" class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
			
<script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
		var v = "<%=request.getParameter("view") %>";//查看事件控制标识
		var pId ="<%=request.getParameter("pId") %>";//参数主键
		//处理pId="null"的情况
		if(null!=pId && ''!=pId && 'null'!=pId){
			nui.get("item.pId").setValue(pId);
		}
		/**页面初始区------------------------------------------------*/
		var paraTypes =[
		{"id":"","text":"--请选择--"},
		{"id":"1","text":"业务申请"},
		{"id":"2","text":"合同签订"},
		{"id":"3","text":"出账放款"}
		];			
		nui.get("item.paraType").setData(paraTypes);
		var paraCountSigns =[
		{"id":"","text":"--请选择--"},
		{"id":"=","text":"="},
		{"id":">","text":">"},
		{"id":"<","text":"<"},
		{"id":">=","text":">="},
		{"id":"<=","text":"<="},
		{"id":"between","text":"between"},
		{"id":"include","text":"包含"},
		{"id":"unclude","text":"不包含"}
		];
		nui.get("item.paraCountSign").setData(paraCountSigns);
		
		//查看时，灰显页面，隐藏保存按钮
		if('1' == v){
			form.setEnabled(false);
			nui.get("save").hide();
		//编辑时，不可编辑参数类型，参数字段，参数字段名称
		}else if('0' == v){
		
			nui.get("item.paraType").setEnabled(false);
			nui.get("item.paraColumn").setEnabled(false);
			nui.get("item.paraColunmName").setEnabled(false);
		}
		
		//初始化页面数据
		$(document).ready(function(){
			var o=form.getData();
			var json=nui.encode(o);
			if(null!=pId && ''!=pId && 'null'!=pId){
				$.ajax({
			            url: "com.bos.pub.productParam.getProductPara.biz.ext",
			            type: 'POST',
			            data: json,
			            cache: false,
			            contentType:'text/json',
			            success: function (text) {
			            		//设置表单值
				            	form.setData(text);
				            	//设置机构显示名称
				            	nui.get("item.authOrgNum").setText(getDictText("org",text.item.authOrgNum));
				            	var sign = text.item.paraCountSign;
				            	//包含，不包含，因为是字典，要单独处理。
				            	if('include'==sign || 'unclude'==sign){
				            		displayRightVal(false);//根据不同的运算符，显示不同的参数
				            		loadDict();//加载字典项
				            		var rightVal = text.item.paraContrlRigthval;
				            		nui.get("item.paraDict").setValue(rightVal);
				            	}else{
				            		displayRightVal(true);//根据不同的运算符，显示不同的参数
				            	}
			            },
			            error: function (jqXHR, textStatus, errorThrown) {
			                nui.alert(jqXHR.responseText);
			            }
				});
			}else{
		            		displayRightVal(true);//根据不同的运算符，显示不同的参数
		            	}
		});
		/**页面控件事件区-------------------------------------------------*/
		//机构选择
		function selectOrg(){
		
			var btnEdit = this;
	        nui.open({
	            url: nui.context + "/pub/sys/select_org_tree.jsp",
	            showMaxButton: true,
	            title: "选择机构",
	            width: 350,
	            height: 450,
	            ondestroy: function (action) {            
	                if (action == "ok") {
	                    var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.GetData();
	                    data = nui.clone(data);
	                    if (data) {
	                        btnEdit.setValue(data.orgcode);
	                        btnEdit.setText(data.orgname);
	                    }
	                }
	            }
	        });      
		}
		
		//根据不同的运算符，提示输入不同的参数
		function checkSign(e){
		
			if(e.value == 'between'){
			
				displayRightVal(true);
				nui.get("item.paraContrlLeftval").setEmptyText("请输入最小金额");
				nui.get("item.paraContrlRigthval").setEmptyText("请输入最大金额");
				nui.get("item.paraContrlRigthval").setRequired(true);
				
			}else if(e.value == 'include'|| e.value == 'unclude'){
			
				displayRightVal(false);
				nui.get("item.paraContrlLeftval").setEmptyText("请输入字典编号");
				
			}else{
				displayRightVal(true);
				nui.get("item.paraContrlLeftval").setEmptyText("请输入数字");
				nui.get("item.paraContrlRigthval").setEmptyText("不需要输入");
				nui.get("item.paraContrlRigthval").setRequired(false);
			}
		}
		
		//当控制参数是字典时，加载字典选项
		function loadDict(){
			var dictId = nui.get("item.paraContrlLeftval").getValue();
			var sign = nui.get("item.paraCountSign").getValue();
			if('include' == sign || 'unclude' == sign){
			
				var  dicts = nui.getDictData(dictId);
				if(null != dicts && dicts.length>0){
					nui.get("item.paraDict").setData(dicts);
				}else{
					nui.alert("输入的字典编号不存在");
					nui.get("item.paraContrlLeftval").setValue(null);
				}
			}
		}
		
		//隐藏或显示右值输入项
		function displayRightVal(flag){
		
			if(flag){
				//显示
				$("#l_rval").css("display","block");
				nui.get("item.paraContrlRigthval").show();
				//隐藏
				$("#l_dict").css("display","none");
				nui.get("item.paraDict").hide();
			}else{
				//隐藏
				$("#l_rval").css("display","none");
				nui.get("item.paraContrlRigthval").hide();
				//显示
				$("#l_dict").css("display","block");
				nui.get("item.paraDict").show();
			}
			nui.get("table").refreshTable();
		}
		
		//清除下拉多选内容
		function onCloseClick(e) {
	            var obj = e.sender;
	            obj.setText("");
	            obj.setValue("");
	    }
	    
	    //校验参数唯一，一个参数只能配置一条规则
	    function checkUnique(){
	    
	    	var paraColumn = nui.get("item.paraColumn").getValue();
	    	var paraType = nui.get("item.paraType").getValue();
	    	var paraColunmName = nui.get("item.paraColunmName").getValue();
	    	if(null == paraType || '' == paraType){
	    	
	    		nui.alert("请先选择参数类型");
	    		nui.get("item.paraColumn").setValue("");
	    		return;
	    	}
	    	
	    	//校验是否保存实际控制人信息
	    	var json = {"paraColumn":paraColumn,"paraType":paraType,"paraColunmName":paraColunmName,"productId":'<%=request.getParameter("productId")%>'};
	   	   	var msg = exeRule("PUB_0022","1",json);
	   	    if(null != msg && '' != msg){
		   	     nui.alert(msg);
		   	     nui.get("item.paraColumn").setValue("");
		   	     nui.get("item.paraColunmName").setValue("");
		   	     return;
	   	     }
	    }
		
		/**按钮事件区-------------------------------------------------*/
		function save() {
		
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请将信息填写完整");
				return;
			}
			
			var o=form.getData();
			var sign = o.item.paraCountSign;
			if('include'==sign || 'unclude'==sign){
				o.item.paraContrlRigthval = o.item.paraDict;
			}
			var json=nui.encode(o);
			$.ajax({
		            url: "com.bos.pub.productParam.saveProductPara.biz.ext",
		            type: 'POST',
		            data: json,
		            cache: false,
		            contentType:'text/json',
		            success: function (text) {
		            	if(text.msg){
		            		nui.alert(text.msg);
		            	} else {
		            		CloseWindow("ok");
		            		
		            	}
		            },
		            error: function (jqXHR, textStatus, errorThrown) {
		                nui.alert(jqXHR.responseText);
		            }
				});
		}
</script>
</body>
</html>
