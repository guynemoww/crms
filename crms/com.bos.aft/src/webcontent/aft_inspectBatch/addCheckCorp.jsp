<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): fengjiahui
  - Date: 2014-02-28 16:34:13
  - Description:批量平台客户检查的发起
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>平台客户情况</title>
</head>
<body>
<fieldset style="margin-top: 10px;margin-bottom: 10px;">
 	<legend>
    	     <span>未列入检查的平台客户成员列表</span>
    </legend>
	<div id="datagrid1" class="nui-datagrid" idField="id" allowResize="true" allowResize="true"  sortMode="client" showPager="false" 
	     url="" dataField="platformMembers" allowCellEdit="true" allowCellSelect="true" multiSelect="true" editNextOnEnterKey="true" >
        <div property="columns"> 
            <div type="checkcolumn"></div>
			<div field="partyName">单户名称</div>
			<div field="CREDIT_EXPOSURE" width="100px">授信金额</div>
			<div field="AVAILABLE_EXPOSURE" width="100px">可用余额</div>
			<div field="OCCUPIED_EXPOSURE" width="100px">已用金额</div>
		</div>
	</div>
</fieldset> 
         <a class="nui-button" iconCls="icon-close" style="text-align: right;float: right;" onclick="onOk()" >关闭</a>
		 <a class="nui-button" iconCls="icon-save" style="text-align: right;float: right;" onclick="btnSave()" >保存</a>
        

	<script type="text/javascript">
		nui.parse();
		
		
		git.mask(); //mask中可加一个参数表示要进行遮罩的页面元素，不加时默认为整个页面。
	
		
		var grid = nui.get("datagrid1");
		var param=<%=request.getParameter("param") %>;
		
		var json=nui.encode({"param":param});
		
		function query(){/* 加载信息 */
		   nui.ajax({
                url: "com.bos.aft.aft_inspectBatch.queryMembersCorp.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                	//alert(nui.encode(text.parentOrg));
                	
                	grid.loadData(text.pfMember);

					git.unmask();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });			
		}
		query();
	    
	   
		/* 保存批量平台信息，保存检查单户信息 */
		function btnSave(){
			var rows = grid.getSelecteds();/* 获取当前所以选中行 */
			
			
			if(rows == null || rows.length <= 0) {//没选记录
				alert('请选择成员客户！');
				return;
			}
			//alert(nui.encode(rows));
			var pfMembers = new Array();
			for(var i = 0;i < rows.length;i++){
				pfMembers[i] = {platformMemberId:rows[i].corporationPartyId,partyId:rows[i].platformPartyId,partyName:rows[i].partyName};
			}
			
			var json =nui.encode({"param":param,"pfMembers":pfMembers});/* 检查单户信息 */
			
		    nui.ajax({
                url: "com.bos.aft.aft_inspectBatch.addPfCorpMembers.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                	nui.alert(text.msg);
                	query();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });       		
       		
		}

function onOk() {
        CloseWindow("ok");
    }

		
		/*
		   nui.ajax({
                url: "",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });
		
		*/


	</script>
</body>
</html>