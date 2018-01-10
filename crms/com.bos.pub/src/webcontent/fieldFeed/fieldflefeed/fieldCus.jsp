<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s):lujinbin
  - Date: 2014-04-15
  - Description:TB_SYS_BUSINESS_TRANSFER, com.bos.pub.sys.TbSysBusinessTransfer
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;">
	<%-- #{hiddenData} --%>
	<div class="nui-dynpanel" columns="2">
	</div>
		<div id="queryForm" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="6" id="queryCsm">
			
			<label>客户名称：</label>
			<input name="map/partyName"  class="nui-textbox nui-form-input" value=""/>
			<label>所属机构：</label>
			<input name="map/orgNum"  allowInput="false" class="nui-buttonEdit" onbuttonclick="selectOrg"/>
			
			<label>所属行业：</label>
			<input id="map/industrialTypeCd" name="map/industrialTypeCd" dictTypeId="CDKH0095" onblur="getType" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectTrade"  />
	
		</div>	
		<div class="nui-toolbar" style="text-align:right;border:none">
		<a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	</div>
		<div id="grid1" class="nui-datagrid" style="width:100%;height:auto;" 
				url="com.bos.pub.fieldflefeed.getFieldCus.biz.ext"
				dataField="cus"
				allowResize="true" showReloadButton="false"
				sizeList="[10,15,20,50,100]" multiSelect="true" pageSize="20" sortMode="client" >
				<div property="columns">
					<div type="checkcolumn" >选择</div>
					<div field="partyName" allowSort="true"  headerAlign="center" >客户姓名</div>  
					<div field="industrialTypeCd" allowSort="true" dictTypeId="CDKH0095" headerAlign="center" >所属行业</div>               
			        <div field="orgNum" allowSort="true" width="" headerAlign="center" dictTypeId="org">所属机构</div> 
			        <div field="userNum" allowSort="true" width="" headerAlign="center" dictTypeId="user">所属用户</div>
				</div>
		</div>
		
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" id="btnSave" onclick="save()">确定</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
	     var grid1 = nui.get("grid1");
	       grid1.load();
	    function search() {
			var data = form.getData(); //获取表单多个控件的数据
        	grid1.load(data);
        	onDrawSummaryCell(grid1)	;
   		 }
   		 
   		  function reset(){
    	
		form.reset();
	}
function save() {
  var row=grid1.getSelecteds();
  if(!row.length){
    alert("请选中一条记录");
  }else{
  	  nui.open({
            url: nui.context+ "/pub/fieldFeed/fieldflefeed/field_InterfacesValues.jsp?ids=01",
            title: "新增", 
            width: 800, 
        	height: 500,
        	allowResize:true,
        	showMaxButton: true,
        	 onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                    iframe.contentWindow.SetData(data);
                },
            ondestroy: function (action) {
                if(action=="ok"){
                }
            }
        });	
  
  }
}
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
                    	self.orglevel=data.orglevel;
                        btnEdit.setValue(data.orgcode);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });      
	}
	
	
		<%--选择行业分类 --%>
	function selectTrade(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=CDKH0095",
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

