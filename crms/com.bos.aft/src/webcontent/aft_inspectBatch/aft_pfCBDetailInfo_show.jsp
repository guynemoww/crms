<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-28 16:34:13
  - Description:批量检查平台客户的成员检查情况补充
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>平台单户检查</title>
</head>  
<body style="margin-left:10px;">

	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<fieldset>
		  <legend>
		     <span>基本情况:</span>
		  </legend>
		  
		  <div id="datagrid2" class="nui-datagrid" showPager="false" url="com.bos.aft.aft_inspectBatch.queryPfCorpInspectDetail.biz.ext" 
		       idField="id" allowCellSelect="true" dataField="pfCorpDetailInfo"  onselectionchanged="hidepopup">
	        <div property="columns"> 
	        <div type="checkcolumn"></div> 
	            <div field="partyName" width="120" headerAlign="center">客户名称</div>
	            <div field="apptName" width="100"  align="center" headerAlign="center">约见人</div>
	            <div field="apptPosition" width="100"  align="center" headerAlign="center">约见职位</div>
			</div>
		</div>
		</fieldset>
		
		<fieldset>
		  <legend>
		     <span>重大变化情况:</span>
		  </legend>
		     <input class="nui-textarea nui-form-input" name="pfChange" id="pfChange" required="false" style="width:100%;"/>
		</fieldset>
        
        <fieldset>
		  <legend>
		     <span>平台单户审批情况：</span>
		     <a id="delButton" class="nui-button" style="float: right;" iconCls="icon-remove" onclick="remove">删除一行</a>
		     <a class="nui-button" id="write" style="text-align: right;float: right;" iconCls="icon-add" onclick="btnAdd()">点击此处填写</a>
		  </legend>
		<div id="datagrid" class="nui-datagrid" showPager="false" multiSelect="false"
             url="" idField="id" allowCellEdit="true" allowCellSelect="true"  editNextOnEnterKey="true">
	        <div property="columns"> 
	        <div type="checkcolumn"></div>  
	            <div field="approvalInfo" width="120" headerAlign="center">平台单户审批条件
	                <input property="editor" class="nui-textbox" style="width:100%;" minHeight="50"/>
	            </div>
	            <div type="dictcomboboxcolumn" autoShowPopup="true" field="isImp" dictTypeId="XD_0002" width="100"  align="center" headerAlign="center">是否落实
	                <input property="editor" class="nui-dictCombobox" style="width:100%;" dictTypeId="XD_0002" />                
	            </div>
			</div>
		</div>
        </fieldset>
        
        <fieldset>
		  <legend>
		     <span>其他情况：</span>
		  </legend>   
		<div class="nui-dynpanel" columns="4" style="margin-top:20px;">
		    <label>约见人：</label>
			<input name="apptName"  class="nui-textbox nui-form-input" id="apptName" style="width:100%;"/>
	
			<label>约见人职位：</label>
			<div>
			    <input name="apptPosition"  class="nui-textbox nui-form-input" id="apptPosition" style="width:100%;"/>
			    <input name="pfDetailId" id="pfDetailId" class="nui-hidden nui-form-input"  />
			</div>
			<label>履行能力/综合评价：</label>
			<input name="pfAbility" id="pfAbility"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0004" style="width:100%;"/>
			<label>检查结论：</label>
			<input name="pfConclusion" id="pfConclusion" onvaluechanged="check()" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0005" style="width:100%;"/>
			<label>客户资产分类是否调整：</label>
			<div name="pfPropertyAdjust" id="pfPropertyAdjust" onvaluechanged="checkRslt()" required="true" class="mini-dictradiogroup nui-form-input"  dictTypeId="XD_0002" onvaluechanged="valuechanged" style="width:100%;" ></div>
			<div id="pfRiskcontrol" class="nui-dynpanel" colspan="2" columns="2">
		         <label>控制措施：</label>
			     <input name="pfRiskcontrol" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0006" required="true" style="width:100%;"/>
			</div>
			<div id="pfAdjustReason1" class="nui-dynpanel" colspan="4" columns="4">
			     <label>客户资产分类调整为：</label>
			     <input name="pfAdjustType" id="pfAdjustType"  class="nui-dictCombobox  nui-form-input" dictTypeId="XD_FLCD0001" style="width:100%;"/>
	        
	             <label>调整理由：</label>
			     <input name="pfAdjustReason"   width="100%" class="nui-textarea nui-form-input" style="width:100%;"/>
		    </div>
		    
		</div>
		</fieldset>
	</div>
	<div class="" style="border-bottom:0;text-align:right;margin-top: 20px;">
		 <a class="nui-button" id="saveData" iconCls="icon-save" onclick="btnSave()">保存</a>
	</div>


	<script type="text/javascript">
	 var Genders = [{ id: 'y', text: '是' }, { id: 'n', text: '否'}];
		nui.parse();
		var form = new nui.Form("#form1");/* 表单 */
		if("<%=request.getParameter("callback") %>"=="y"){
		    nui.get("write").hide();
		    nui.get("delButton").hide();
			$("#addBtn").hide();
			form.setEnabled(false);
		}
		nui.get("pfRiskcontrol").hide();
		
		var grid = nui.get("datagrid");/* 落实 */
	    var datagrid2 = nui.get("datagrid2");
		var param=<%=request.getParameter("param") %>;
		nui.get("pfAdjustReason1").hide();
		nui.get("pfRiskcontrol").hide();
		var childPfId = "<%=request.getParameter("childPfId") %>";      //当前子平台的检查ID，用户获取成员客户的审批条件信息。
		var callback = "<%=request.getParameter("callback") %>";
		
		if(callback=="y"){
		
		}
	function check(){
		 var check=nui.get("pfConclusion").value;
		 if(check==3){
		   nui.get("pfRiskcontrol").show();
		 }else{
		   nui.get("pfRiskcontrol").hide();
		 }
		}
		
	function checkRslt(){
		 var check=nui.get("pfPropertyAdjust").value;
		 if(check==1){
		   nui.get("pfAdjustReason1").show();
		 }else{
		   nui.get("pfAdjustReason1").hide();
		 }
		 
		}
		datagrid2.load({"param":param});
		
	function btnSave(){/* 保存单户检查信息 */
	        nui.get("saveData").setEnabled(false);
			form.validate();
			
        	if (form.isValid()==false){
        	 alert("请完善填写！");
        	 nui.get("saveData").setEnabled(true);
        	 return;
			}
			//nui.get("pfDetailId")
			var pfMemCons = new Array();
			var rows=grid.findRows();
			
			var json = nui.encode({"formData":form.getData(),"rowsData":rows,"param":param});
		//	alert(json);
		//	alert(nui.encode(grid1.getData()));
            nui.ajax({
                url: "com.bos.aft.aft_inspectBatch.updPfCorpInspectDetail.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                	nui.alert(text.msg);
                	datagrid2.load({"param":param,isCo:1});
					git.unmask();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });				
			nui.get("saveData").setEnabled(true);
		}
	function btnAdd(){
	        nui.get("write").setEnabled(false);
			var index=grid.totalCount
		    var newRow = { name: "New Row" };
            grid.addRow(newRow, index);
            nui.get("write").setEnabled(true);
		}
		
    function remove() {
        nui.get("delButton").setEnabled(false);
        var row = grid.getSelected();
        
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	grid.removeRow(row,false);/* 删除页面行 */
           }); 
        } else {
            nui.alert("请选中一条记录");
        }
        nui.get("delButton").setEnabled(true);
    }
		
	function hidepopup(){/* 单户改变时发生 */
		  var row = datagrid2.getSelected();
		  if(!row){
		     alert("请选择成员客户！");
		     return;
		  }
			var detailId = row.pfDetailId /* 客户编号 */
			var json=nui.encode({"detailId":detailId,"param":param,pfChildId:childPfId});
			
			 nui.ajax({
                url: "com.bos.aft.aft_inspectBatch.queryPfChildMemberDetail.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                     if(text.pfCorpMemberInfo.pfPropertyAdjust==1){
		                nui.get("pfAdjustReason1").show();
		               }
		             if(text.pfCorpMemberInfo.pfConclusion==3){
		                nui.get("pfRiskcontrol").show();
		             }
                    //nui.get("pfConclusion").setValue(text.pfCorpMemberInfo.pfConclusion);
                    //nui.get("pfAdjustType").setValue(text.pfCorpMemberInfo.pfAdjustType);
            		grid.loadData(text.pfCorpMemCons);
        	 		form.setData(text.pfCorpMemberInfo);
					
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });		
			
	}
        
        function valuechanged(){
			var pfPropertyAdjust = nui.get("pfPropertyAdjust");
			var pfAdjustType = nui.get("pfAdjustType");
			var pfAdjustReason = nui.get("pfAdjustReason");
			var i = pfPropertyAdjust.getValue();
			if(i == 1){
				pfAdjustType.setEnabled(true);
				pfAdjustReason.setEnabled(true);
			}else{
				pfAdjustType.setEnabled(false);
				pfAdjustReason.setEnabled(false);
				pfAdjustType.setValue("");
				pfAdjustReason.setValue("");
			}
		}
		
		
		
		
	</script>
</body>
</html>