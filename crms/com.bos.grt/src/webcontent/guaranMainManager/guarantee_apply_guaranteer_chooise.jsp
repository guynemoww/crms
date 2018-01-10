<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<!-- 
  - Author(s): ZhuYongLun
  - Date: 2014-05-23 14:15:27
  - Description:
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<script type="text/javascript" src="<%=contextPath%>/grt/grtJS/grt.js"></script>
	<title>选择客户</title>
</head>
<body>
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="4">
			<label>客户名称：</label>
			<input name="party.partyName" class="nui-textbox nui-form-input" vtype="maxLength:64"/>
			<label>客户类型：</label>
			<input id="partyTypeCd" name="party.partyTypeCd" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0219"  required="true"
				onvaluechanged="changepartytype"/>
			<label>证件号码：</label>
			<input name="party.certificateCode" class="nui-textbox nui-form-input"/>
			<label>证件类型：</label>
			<input id="party.certificateTypeCd" name="party.certificateTypeCd" textField="dictname" valueField="dictid" class="nui-buttonEdit nui-form-input" 
				dictTypeId="CDKH0002" onbuttonclick="selectCertList" required="true" allowInput="false" />
		</div>
	</div>
	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
	   	<a class="nui-button" iconCls="icon-search" onclick="query">查询</a>
	   	<a class="nui-button" iconCls="icon-reset" onclick="reset">重置</a>
	   	<!--<a class="nui-button" iconCls="icon-add" onclick="addCust">新增客户</a>-->
	</div>
	<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	   	url="com.bos.csm.pub.crudCustInfo.getCustByIdCardOrName.biz.ext" dataField="items"
	   	allowAlternating="true" multiSelect="false" showEmptyText="true" onshowrowdetail="onShowRowDetail"
	   	emptyText="没有查到数据" showReloadButton="false" onselectionchanged="selectGrid"
	   	onrowdblclick="" allowCellEdit="true" allowCellSelect="true" allowCellWrap="true"
	   	sizeList="[10,20,50,100]" pageSize="10">
	   	<div property="columns">
	       	<div type="checkcolumn">选择</div>
	       	<div field="partyId" name="partyId" allowSort="true" headerAlign="center" autoEscape="false">客户ID</div>
	       	<div field="partyName" allowSort="true" headerAlign="center" autoEscape="false">客户名称</div> 
	       	<div field="partyTypeCd" allowSort="true" headerAlign="center" dictTypeId="XD_KHCD0219">客户类型</div>
	       	<div field="partyNum" allowSort="true" headerAlign="center" autoEscape="false">客户编号</div> 
	       	<div field="certificateTypeCd" allowSort="true" headerAlign="center" dictTypeId="CDKH0002">证件类型</div> 
	       	<div field="certificateCode" allowSort="true" headerAlign="center" >证件号码</div>
	       	<div field="corporationLevelCd" allowSort="true" headerAlign="center" name="corporationLevelCd" dictTypeId="XD_KHCD0189" >公司分类</div>
	    	</div>
	</div>
	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
		<a class="nui-button"  iconCls="icon-ok" onclick="selectType()">确定</a>
		<a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" onclick="CloseWin()">关闭</a>
	</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	var flag;
	grid.hideColumn(grid.getColumn("partyId"));
	nui.get("partyTypeCd").setData(getDictData('XD_KHCD0219','str','01,03,04,06'));
	nui.get("partyTypeCd").setValue("01");//默认为公司客户
	nui.get("party.certificateTypeCd").setValue("20001");//默认为组织机构代码

	function query(){//对公单一客户查询
		if (form.isValid() == false) {
		   nui.alert("请将信息填写完整");
		   return;
		}
		var o = form.getData(false, true);//逻辑流必须返回total
		grid.load(o);
	}
	query();
	
	function reset() {
		//nui.get("party.certificateTypeCd").setText(null);
		//form.setData({});
	}
	
	/**
	 * 选择客户类型 固定证件类型
	 */
	function changepartytype(){
		if(nui.get("partyTypeCd").getValue() == "01"){//公司客户--证件类型=组织机构代码
			nui.get("party.certificateTypeCd").setValue("20001");
			nui.get("party.certificateTypeCd").setRequired(true);
		}else if(nui.get("partyTypeCd").getValue() == "03"){//个人客户--证件类型=身份证
			nui.get("party.certificateTypeCd").setValue("10100");
			nui.get("party.certificateTypeCd").setRequired(true);
		}else if(nui.get("partyTypeCd").getValue() == "04"){//同业客户--证件类型=null
			nui.get("party.certificateTypeCd").setValue(null);
			nui.get("party.certificateTypeCd").setText(null);
			nui.get("party.certificateTypeCd").setRequired(false);
		}
	}
	
	/**
	 * 选择押品类型
	 */
	function selectType(){
		 var row = grid.getSelected();
		 if(row){
		    //客户主键
		 	var partyId = row.partyId;
		 	//定义客户编号
		 	var partyNum = row.partyNum;
		 	//定义客户名称
		 	var partyName = row.partyName;
		 	//定义客户证件类型
		 	var certificateTypeCd = row.certificateTypeCd;
		 	//定义客户证件号码
		 	var certificateCode = row.certificateCode;
		 	//客户类型
		 	var partyTypeCd = row.partyTypeCd;
		 	
		 	if (flag=="1") {
				nui.alert("请重新选择保证人");
				return;
			}
			var str = [partyId,partyNum,partyName,certificateTypeCd,certificateCode,partyTypeCd];
		 	CloseWindow(str);//向父页面传入参与人id
			
		}else{
		 	nui.alert("请选择一条记录！");
		}	     
	}
	
	function CloseWindow(action) {  
	  if(action!="ok" && action!="close"){
	  	window.CloseOwnerWindow(action);
	  }else{
	  	window.CloseOwnerWindow("ok");
	  }	          
	}
	
	function CloseWin(){
		CloseWindow("ok");
	}
	
	function selectCertList(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/grt/collateralparameter/colllsortparameter/selectPartyType.jsp?dicttypeid=CDKH0002",
            title: "选择证件类型",
            width: 800,
            height: 450,
            allowResize:false,
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
        	}
        });            
	}
	
	/**
	 * 新增客户
	 */
	function addCust() {
        nui.open({
            url: nui.context + "/csm/corporation/csm_key_messages_add.jsp",
            title: "新增客户",
            width: 800,
            height: 500,
            allowResize:false,
            ondestroy: function (action) {            
               query();
        	}
        });              
	}	
	
	//客户选中事件
	function selectGrid(){
	   var row = grid.getSelected();
	   flag=0;
	   if(row.partyTypeCd=="06"){		//担保客户
          var json2 = nui.encode({"partyId":row.partyId});
          //担保额度信息
          $.ajax({
			  url:"com.bos.comm.crd.CrdQueryService.getGuateeLimit.biz.ext",
			  type:'POST',
			  data:json2,
			  cache:false,
			  contentType:'text/json',
			  success: function (text) {
				  if(text.msg){
				     nui.alert(text.msg);
				  }else{
					if(text.limit.creditAmt){
				   
				    }else{
				       nui.alert("此担保客户没有担保额度");
				       flag=1;
				    }
				  }
			 },
			 error: function (jqXHR, textStatus, errorThrown) {
				    nui.alert(jqXHR.responseText);
				   }
			});
	    }
	}
	
</script>
</body>
</html>