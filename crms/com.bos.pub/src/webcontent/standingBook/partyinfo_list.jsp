<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 金磊
  - Date: 2014-05-09 10:55:07
  - Description:
-->
<head>
<title>客户信息台账</title>
<%@include file="/common/nui/common.jsp" %>
<style >
</style>
</head>
<body>
<div id="tabs1"  class="nui-tabs" activeIndex="0" style="width:100%;height:auto;overflow:auto;">
<div title="客户信息台账" >
<center>
<form id="form1" action="com.primeton.example.excel.empManager.flow" method="post" enctype="multipart/form-data" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;margin-bottom:5px;" >
	<div class="nui-dynpanel" columns="6">
		<label>客户名称：</label>
		<input name="map/partyName" required="false" class="nui-textbox nui-form-input"  />
		<label>组织机构代码：</label>
		<input name="map/orgnnum" required="false" class="nui-textbox nui-form-input"  />
		<label>贷款卡号：</label>
		<input name="map/loanCardNum" required="false" class="nui-textbox nui-form-input"  />
		<label>所属机构：</label>
		<input name="map/orgNum" required="false" class="nui-buttonEdit nui-form-input"  allowInput="false" onbuttonclick="selectCodeList" vtype="maxLength:200" />
	</div>

	<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;padding-right:48px" borderStyle="border:0;">
    	<a class="nui-button"  iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
		<%--<a class="nui-button"  onclick="exportEmp" type="submit" />导出信息</a>--%>
	</div>
</form>
	<div id="grid1" class="nui-datagrid" style="width:99.65%;height:auto;margin-top:7px"
		url="com.bos.pub.standingbook.partyInfo.queryPartyInfoList.biz.ext"
		dataField="items"
		allowResize="true" showReloadButton="false" allowAlternating="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
			<div field="orgnnum" headerAlign="center" allowSort="true" >客户组织机构代码</div>
			<div field="licensenum" headerAlign="center" allowSort="true" >营业执照</div>
			<div field="industrialTypeCd" headerAlign="center" allowSort="true" dictTypeId="CDKH0095">行业（大类） </div>
			<div field="fourrEnterpriseSizeCd" headerAlign="center" allowSort="true" dictTypeId="CDKH0025">规模（2011） </div>
			<div field="listingCorporation" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">是否上市公司 </div>
			<div field="dateOfEstablishment" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd" >企业成立日期 </div>
			<div field="registerAssetsCurrencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">注册币种</div>
			<div field="registerAssets" headerAlign="center" allowSort="true" dataType="currency">注册资本</div>
			<div field="loanCardNum" headerAlign="center" allowSort="true" >贷款卡号</div>
			<div field="orgname" headerAlign="center" allowSort="true" >所属机构</div>
			<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">创建人</div>			
			
		</div>
	</div>
</center>
</div>
</div>	
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
	//初始化页面
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
		git.mask("tabs1");
        grid.load(data,function(){
        	git.unmask("tabs1");
        });
        
        //var duebillserialno = "<%=request.getParameter("duebillserialno") %>";
		//var json = {"tbBatchWastebook/duebillserialno":duebillserialno};		
        //grid.load(json);
    }
    //search();
    
    function reset(){
		form.reset();
		//search();
	}
	//导出
    function exportEmp()
    {
     var form = document.getElementById("form1");
		     form.action = "com.primeton.example.excel.empManager.flow?_eosFlowAction=exportFile&importCd=16";
		     form.submit();
    }
	 grid.on("preload",function(e){//客户信息链接
   		if (!e.data || e.data.length < 1)
   			return;
   		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
   			e.data[i]['partyName']='<a href="#" onclick="clickCust(\''
   				+ e.data[i].partyId
   				+ '\');return false;" value="'
   				+ e.data[i].partyId
   				+ '">'+e.data[i]['partyName'] +'</a>';
   		}
   });
   		//客户信息链接
		function clickCust(partyId){//客户信息链接
	     var json = nui.encode({"partyId":partyId});
        $.ajax({
            url: "com.bos.pub.uitl.viewCust.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
                var o = nui.decode(mydata);
                if(o.msg){
                	nui.alert(o.msg);
                }else{
                	nui.open({
            			url: nui.context + o.url,
            			title: "客户信息", 
            			width: 1024, 
        				height: 600,
        				state:"max",
        				allowResize:true,
        				showMaxButton: true,
           				ondestroy: function (action) {
                		
            			}
        			});
                }
            }
        });
	}
	
		
	function selectCodeList(e) {
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
                    }
                }
            }
        });            
    }
	</script>
</body>
</html>