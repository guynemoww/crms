<%@page pageEncoding="UTF-8"%>

<html>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-28 16:34:13
  - Description:批量检查平台客户的综合情况补充
-->
<head>
<title>平台综合情况</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body style="margin-left:10px;">

<div id="form1" style="width:100%;height:auto;">
  <fieldset>
    <legend>
        <span>平台审批落实情况</span>
        <a id="delButton" class="nui-button" style="float: right;" iconCls="icon-remove" onclick="remove">删除一行</a>
        <a id="addBtn" class="nui-button" style="text-align: right;float: right;" iconCls="icon-add" onclick="btnAdd()">点击此处填写</a>
    </legend>
	<div id="datagrid1" class="nui-datagrid" showPager="false" multiSelect="false"
        url="" idField="id" allowCellEdit="true" allowCellSelect="true"  editNextOnEnterKey="true" >
        <div property="columns"> 
       		 <!--ComboBox：本地数据-->  
       		<div type="checkcolumn" id="checkId"></div>      
            <div field="approvalInfo" width="120" headerAlign="center">平台整体审批条件
                <input property="editor" class="nui-textbox" style="width:100%;" minHeight="50"/>
            </div>
            <div type="comboboxcolumn" autoShowPopup="true" name="gender" field="isImp" width="100"  align="center" headerAlign="center">是否落实
                <input property="editor" class="nui-combobox" style="width:100%;" data="Genders" />                
            </div> 
        </div>
     </div>	
  </fieldset>
  <div id="form2">
  <fieldset>
    <legend>
	    <span style="margin-top: 10px;">检查期内平台项下客户群所在区域、所属行业经济运行状况、平台整体变化情况:</span>
	</legend>
	<input class="nui-textarea nui-form-input" name="pfChangeInfo" required="false" style="width:100%;"/>
  </fieldset>		

  <fieldset>
    <legend>
	    <span style="margin-top: 10px;">平台授信方案综述、预测及展望:</span>
	</legend>
	<input class="nui-textarea nui-form-input" name="pfPorecastInfo" required="false" style="width:100%;"/>
  </fieldset>				

  <fieldset>
    <legend>
	    <span style="margin-top: 10px;">检查期内平台管理方整体情况:</span>
	</legend>
	    <input class="nui-textarea nui-form-input" name="pfManageInfo" required="false" style="width:100%;"/>
  </fieldset>	

  <fieldset>
    <legend>
	    <span style="margin-top: 10px;">其他需重点关注事项:</span>
	</legend>
		<input class="nui-textarea nui-form-input" name="pfAttentionInfo" required="false" style="width:100%;"/>
  </fieldset>
  
  <fieldset>
    <legend>
	    <span style="margin-top: 10px;">缺少检查季度内授信客户情况:</span>
	</legend>
		<input class="nui-textarea nui-form-input" name="pfApprovalInfo" required="false" style="width:100%;"/>
  </fieldset>
  </div>		
</div>
	<div class="" style="border-bottom:0;text-align:right;margin-top: 20px;" id="saveBtn">
		 <a class="nui-button" iconCls="icon-save" onclick="btnSave()">保存</a>
	</div>

	<script type="text/javascript">
	nui.parse();
	    var form = new nui.Form("#form1");
	     var form2 = new nui.Form("#form2");
		var grid = nui.get("datagrid1");
		if("<%=request.getParameter("callback") %>"=="y"){
			$("#saveBtn").hide();
			$("#addBtn").hide();
			$("#delButton").hide();
			grid.setAllowCellEdit(false);                                                        //取消表格编辑
			//$("#datagrid1").attr("allowCellEdit","false");
			form.setEnabled(false);
		}
		var Genders = [{ id: 'y', text: '是' }, { id: 'n', text: '否'}];
		var param=<%=request.getParameter("param") %>;
		//var pfId=nui.encode(param.pfId);
		//var partyId=nui.encode(param.partyId);
		nui.parse();
		git.mask(); 
		
		
		var json = nui.encode({"param":param});
		
		function query(){
           nui.ajax({
                url: "com.bos.aft.aft_inspectBatch.queryPfCorpInspect.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                	form.setData(text.pfCorpInspect);
                	grid.loadData(text.pfCorpCons);
					git.unmask();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });		
		}
		query();
		
		function btnAdd(){
		    nui.get("addBtn").setEnabled(false);
			var index=grid.totalCount
		    var newRow = { name: "New Row" };
            grid.addRow(newRow, index);
            nui.get("addBtn").setEnabled(true);
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
		
		function btnSave(){
		
		//	var rows = grid.getSelecteds();
			var rows=grid.findRows();
			/*
			var pfCons=new Array();
			for(var i=0;i<rows.length;i++){
				pfCons[i]={"isPlatform":"n","approvalInfo":rows[i].approvalInfo,"isImp":rows[i].isImp};
			}
			*/
			var jsonData=nui.encode({"rowsList":rows,"formData":form.getData(),"param":param,"rowsData":rows});
			//alert(jsonData);
			//var json = form.getData(); 
 		    nui.ajax({
                url: "com.bos.aft.aft_inspectBatch.updPfCorpInspectInfo.biz.ext",
                data:jsonData,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                	nui.alert(text.msg);
					git.unmask();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
                }
            });     		
		}
	</script>
</body>
</html>