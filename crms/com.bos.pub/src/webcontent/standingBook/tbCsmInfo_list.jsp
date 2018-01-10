<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>客户信息台账</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;">
<div title="客户信息台账" >
<center>
<form id="form1" action="" method="post" class="nui-form" style="height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="4">
			<label>客户编号：</label>
			<input name="tbCsmInfo.partyNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
			<label>客户名称：</label>
			<input name="tbCsmInfo.partyName" required="false" class="nui-textbox nui-form-input"  vtype="maxLength:32" />
			<label>贷款卡编码：</label>
			<input name="tbCsmInfo.loanCardNum" required="false" class="nui-textbox nui-form-input"  vtype="maxLength:32" />
			<label>客户类型：</label>
			<input name="tbCsmInfo.partyTypeCd" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_PJCD0015" vtype="maxLength:32" />
	</div>
	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
	    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
		<a class="nui-button" iconCls="icon-edit" onclick="view()">查看客户信息</a>
	</div>
</form>

<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.standingbook.guarantyaccout.GetTbCsmInfoList.biz.ext"
	dataField="tbCsmInfos"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns" >
			<!-- 基本信息 -->
			<div type="checkcolumn" >选择</div>
			<div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>
			<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
			<div field="orgCertificate" headerAlign="center" allowSort="true" >组织机构代码</div>
			<div field="personCertificate" headerAlign="center" allowSort="true" >工商营业执照</div>
			<div field="partyTypeCd" headerAlign="center" allowSort="true" dictTypeId="XD_PJCD0015">客户类型</div>
			<div field="companyaddress" headerAlign="center" allowSort="true" >省市区</div>
			<div field="registerOrg" headerAlign="center" allowSort="true" >登记机构</div>
			<div field="loanCardNum" headerAlign="center" allowSort="true" >贷款卡编码</div>
			<div field="registerAssetsCurrencyCd" headerAlign="center" allowSort="true" >注册币种</div>
			<div field="registerAssets" headerAlign="center" allowSort="true" >注册资本</div>	
	</div>
</div>
	</center>
</div>
</div>		
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    //search();
	
    
    function reset(){
		form.reset();
	}
	
	//查看客户信息
    function view() {
    var row = grid.getSelected();
    if (row) {
        nui.open({
            url: nui.context + "/pub/standingBook/tbCsmInfo_detail.jsp?partyNum="
            +row.partyNum+"&partyName="+git.toUrlParam(row.partyName)
            +"&orgCertificate="+git.toUrlParam(row.orgCertificate)
            +"&personCertificate="+git.toUrlParam(row.personCertificate)
            +"&partyTypeCd="+git.toUrlParam(row.partyTypeCd)
            +"&registerOrg="+git.toUrlParam(row.registerOrg)
            +"&loanCardNum="+git.toUrlParam(row.loanCardNum)
            +"&registerAssetsCurrencyCd="+git.toUrlParam(row.registerAssetsCurrencyCd)
            +"&registerAssets="+git.toUrlParam(row.registerAssets)
            +"&registerNum="+git.toUrlParam(row.registerNum)
            +"&naturalPartyName="+git.toUrlParam(row.naturalPartyName)
            +"&natureCertificate="+git.toUrlParam(row.natureCertificate)
            +"&registerAssetCurrencyCd="+git.toUrlParam(row.registerAssetCurrencyCd)
            +"&investmentAmt1="+git.toUrlParam(row.investmentAmt1)
            +"&companyaddress="+git.toUrlParam(row.companyaddress)
            +"&investmentCustomerName="+git.toUrlParam(row.investmentCustomerName)
            +"&outsideCertificate="+git.toUrlParam(row.outsideCertificate)
            +"&registerAsseCurrencyCd="+git.toUrlParam(row.registerAsseCurrencyCd)
            +"&investmentAmt="+git.toUrlParam(row.investmentAmt)
            +"&ifinterest="+git.toUrlParam(row.ifinterest)
            +"&advanceflag="+git.toUrlParam(row.advanceflag)
            +"&ifnotgood="+git.toUrlParam(row.ifnotgood)
            +"&ifblack="+git.toUrlParam(row.ifblack)+"&partyId="+row.partyId
            ,
            title: "查看客户详细信息", 
            width: '100%',
    		height: 900,
            allowResize:true,
    		showMaxButton: true,
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = row;
                //iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                if(action=="ok"){
                    search();
           	 	}
            }
        });
    } else {
        alert("请选中一条记录");
    }
}
     
    

	</script>
</body>
</html>
