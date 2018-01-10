<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lujinbin
  - Date: 2014-03-17

  - Description:TB_ORDER_MESSAGE, com.bos.pub.sys.TbOrderMessage-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbOrderMessage" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		
		<label>计分人部门：</label>
		<input name="tbOrderMessage.scoreDepartment" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:60" dictTypeId="XD_BM0023" emptyText="请选择"/>

		<label>计分人姓名：</label>
		<input id="xingming" name="tbOrderMessage.scoreName" required="true"  class="nui-buttonEdit" allowInput="true"  onbuttonclick="selectCustManeger" />

		<label>计分人工号：</label>
		<input id="gonghao" name="tbOrderMessage.scoreNumber" required="true"  class="nui-textbox nui-form-input"  enabled="false"/>
        <label>计分对象岗位：</label>
		<input  name="tbOrderMessage.scoreObjPost" required="true"  class="nui-buttonEdit" onbuttonclick="selectOrgPos" dictTypeId="XD_GGCD33032"/>
		<label>计分人机构名称：</label>
		<input id="orgName" name="tbOrderMessage.scoreOrgName" required="true"  class="nui-textbox" enabled="false"/>
	    <label>计分来源机构：</label>
		<input name="tbOrderMessage.scoreFromOrg" required="false"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_GGCD82021"/>
		<label>计分人机构编号：</label>
		<input id="orgcode" name="tbOrderMessage.scoreOrgNumber" required="true" class="nui-textbox nui-form-input" vtype="maxLength:4" enabled="false"/>

		<label>计分项目名称：</label>
		<input id="projectName" name="tbOrderMessage.scoreProjectName" required="true" class="nui-buttonEdit" allowInput="false"  vtype="maxLength:60" onbuttonclick="selectPorject"/>
		<label>计分事项：</label>
		<input id="projectMatter" name="tbOrderMessage.scoreMatter" required="true" class="nui-TextArea" vtype="maxLength:60" enabled="false"/>
		<label>计分来源的事项：</label>
		<input  name="tbOrderMessage.scoreFromObj" required="true" class="nui-TextArea" vtype="maxLength:60" />
		<label>应计分：</label>
		<input id="score" name="tbOrderMessage.shouldTheScoring" required="true" class="nui-textbox nui-form-input" vtype="maxLength:4" enabled="false"/>
		<label>实计分：</label>
		<input id="shouldScore" name="tbOrderMessage.realScoring" required="true" class="nui-textbox nui-form-input" vtype="maxLength:4;int" />
	    <label>实际计分值与应计计分值不同的原因：</label>
		<input name="tbOrderMessage.actShdDef" required="false" class="nui-TextArea" vtype="maxLength:60" />
		<label>具体情况描述：</label>
		<input name="tbOrderMessage.specificConditionDescribe" required="true" class="nui-TextArea" vtype="maxLength:60" />

		<label>状态：</label>
		<input id="state" name="tbOrderMessage.state" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:60" dictTypeId="XD_ZT002"  />
		
		<label>是否双倍计分：</label>
		<input name="tbOrderMessage.whetherDoubleScore" required="true	" class="nui-dictcombobox nui-form-input" vtype="maxLength:60" dictTypeId="XD_0002" value="2" />
		<label>是否减免计分：</label>
		<input name="tbOrderMessage.isNotScore" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:60" dictTypeId="XD_0002" value="2" />
		
		<label>经办机构：</label>
		<input name="tbOrderMessage.orgNum" required="false" class="nui-buttonEdit" vtype="maxLength:9" dictTypeId="org" enabled="false" />

		<label>经办用户：</label>
		<input name="tbOrderMessage.userNum" required="false" class="nui-buttonEdit" vtype="maxLength:10" dictTypeId="user" enabled="false" />
		<label>经办时间：</label>
		<input id="state" name="tbOrderMessage.time" required="true" class="nui-datepicker nui-form-input" vtype="maxLength:60" enabled="false"  />
		
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1");
	if ("<%=request.getParameter("view") %>"=="1") {
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}

function initForm() {
	var json=nui.encode({"tbOrderMessage":
		{"scoreMessageId":
		"<%=request.getParameter("scoreMessageId") %>"}});
	$.ajax({
        url: "com.bos.pub.openOrder.getTbOrderMessage.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		form.setData(text);
        		 nui.get("projectName").setValue(text.tbOrderMessage.scoreProjectName);
                 nui.get("projectName").setText(text.tbOrderMessage.scoreProjectName);
                  nui.get("xingming").setText(text.tbOrderMessage.scoreName);
                 
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});
}
initForm();

function save() {
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		return;
	}
	
	
	// 提示
	 var shouldScore,score;
	 shouldScore=nui.get("shouldScore").getValue();//实计分
	  score=nui.get("score").getValue();//应计分
	  if(Number(shouldScore)>Number(score)){
	    alert("实计分不能大于应计分");
	    return;
	 }
	 var json=nui.encode({"shouldScore":shouldScore});
                $.ajax({
                     url: "com.bos.pub.openOrder.findPunish.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}else{
                    	var o=form.getData();
						var jsons=nui.encode(o);
			                    	var temp;
			                    	if(text.punish[0]==null){
			                    	  alert("没有相对应的处罚标准");
			                    	  return;
			                    	}else{
			                    	temp=text.punish[0].PUNISH_MEASURE;
                    			nui.confirm("该人员本次计分生效后将达到【"+temp+"】的处罚标准，是否继续？","确认",function(action){
							            	if(action!="ok") return;
							               $.ajax({
										        url: "com.bos.pub.openOrder.updateTbOrderMessage.biz.ext",
										        type: 'POST',
										        data: jsons,
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
										});
			                    	}
			                    	
                    	}
                    	
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    }
                });
	
}


function selectEmpOrg(e) {
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
                        btnEdit.setValue(data.orgid);
                        btnEdit.setText(data.orgname);
                         // 添加机构编码
                        nui.get("orgcode").setValue(data.orgcode);
                    }
                }
            }
        });            
    }
    
   
    
    function selectCustManeger(e) {
			        nui.open({
			            url: nui.context + "/pub/openOrder/customer.jsp",
			            showMaxButton: true,
			            title: "选择接收人",
			            width: 800,
			            height: 500,
			            ondestroy: function (action) {            
			                if (action == "ok") {
			                    var iframe = this.getIFrameEl();
			                    var data = iframe.contentWindow.getData();
			                    data = nui.clone(data);
			                    if (data) {
			                     //   alert(nui.encode(data));
			                    	nui.get("xingming").setValue(data.empName);
			                    	nui.get("xingming").setText(data.empName);
			                    	nui.get("gonghao").setValue(data.userId);
			                    	nui.get("orgName").setValue(data.orgName);
			                    	  nui.get("orgcode").setValue(data.orgCode);
			                    }
			                }
			            }
			        });   
        }
        // 计分项目查询 
       function selectPorject(e){
		       		 nui.open({
		            url: nui.context + "/pub/openOrder/selectPorject.jsp",
		            showMaxButton: true,
		            title: "计分项目",
		            width: 800,
		            height: 500,
		            ondestroy: function (action) {            
		                if (action == "ok") {
		                    var iframe = this.getIFrameEl();
		                    var data = iframe.contentWindow.getData();
		                    var projectName = iframe.contentWindow.getProjectName();
		                    data = nui.clone(data);
		                      projectName = nui.clone(projectName);
		                    if (data) {
		                    //   alert(nui.encode(data));
		                   //   alert("==="+nui.encode(data)+"=="+nui.encode(projectName));
		                       nui.get("projectName").setValue(projectName);
		                       nui.get("projectName").setText(projectName);
		                         nui.get("projectMatter").setValue(data.scoreMatter);
		                         nui.get("score").setValue(data.scoreStandard);;
		                    }
		                }
		            }
		        });   
       }
       
        
       function selectOrgPos(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=XD_GGCD33032",
            title: "选择字典项",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.dictid);
                        btnEdit.setText(data.dictname);
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}
	</script>
</body>
</html>
