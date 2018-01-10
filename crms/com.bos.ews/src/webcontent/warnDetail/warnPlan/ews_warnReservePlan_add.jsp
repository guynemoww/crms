<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<%-- 
  - Author(s): caozhe@git.com.cn
  - Date: 2014-02-15 19:54:03
  - Description:新增预警预案
--%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>新增预警预案</title>
</head>
<body>


<!--<div style="margin-top:30px;">预警管理->新增预警预案</div>   <div style="margin-top:30px;">客户基本信息</div> -->  
<strong>黄色预警级别以上的客户需要发起预案</strong> 
<fieldset>
  	<legend>
    	<span>客户基本信息</span>
    </legend>

<div id="form1" style="width:100%;height:auto;overflow:hidden;">
   <div class="nui-dynpanel" columns="4" style="text-align:center;">
		<label >客户编号</label>
		<input name="csmPlanBasicInfo.partyNum"  class="nui-text nui-form-input" style="width:100%;" />

		<label>客户名称</label>
		<input name="csmPlanBasicInfo.partyName"  class="nui-text nui-form-input" style="width:100%;" />

		<label>客户类型</label>
		<input name="csmPlanBasicInfo.partyTypeCd" class="nui-text nui-form-input"  style="width:100%;" dictTypeId="XD_KHCD0219"   />

		<label>信用等级</label>
		<input name="csmPlanBasicInfo.creditRatingCd"  class="nui-text nui-form-input" style="width:100%;" />

		<label>现有银行授信合计</label>
		<input name="csmPlanBasicInfo.creditExposure" dataType="currency" class="nui-text nui-form-input" style="width:100%;"/>

		<label>现有对外担保合计</label>
		<input name="csmPlanBasicInfo.totalMoney" dataType="currency" class="nui-text nui-form-input" style="width:100%;" />
    </div>
  
    <div style="margin-top:30px;"></div>   
</div>    
</fieldset>
<!--  <div  style="margin-top:30px;">担保人基本信息</div> -->
<fieldset>
  	<legend>
    	<span>担保人基本信息</span>
    </legend>

<div id="warrant" class="nui-datagrid" style="width:99.5%;height:auto" url="com.bos.ews.warnPlan.getCsmWarrantInfo.biz.ext" 
     dataField="csmWarrants" sortMode="client">
     <div property="columns">
     <div type="indexcolumn"  > 序号 </div>
     <div field="partyNum" headerAlign="center" allowSort="true"> 担保人编号 </div>
     <div field="partyName" headerAlign="center" allowSort="true"> 担保人名称 </div>
     <div field="creditExposure" headerAlign="center" allowSort="true" dataType="currency"> 现有银行授信合计 </div>
     <div field="totalSumMoney" headerAlign="center" allowSort="true" dataType="currency"> 现有对外担保合计 </div>
     </div>
</div>
</fieldset>


<!--  <div  style="margin-top:30px;">授信业务情况</div> -->
<fieldset>
  	<legend>
    	<span>授信业务情况</span>
    </legend>
<div id="BizInfo" class="nui-datagrid" style="width:99.5%;height:auto" url="com.bos.ews.warnPlan.getCsmBizInfo.biz.ext" 
     dataField="csmBizInfos" allowResize="true" showReloadButton="false" showPageSize="false" pageSize="5" multiSelect="false" sortMode="client">
<!-- <div  style="margin-top:30px;">授信业务情况</div> -->
<!-- <div id="BizInfo" class="nui-datagrid" style="width:100%;height:auto" url="com.bos.ews.warnPlan.getCsmBizInfo.biz.ext" 
	 dataField="csmBizInfos" showPager="false" allowResize="true" showReloadButton="false" showPageSize="false" pageSize="5" multiSelect="false" sortMode="client" > -->

     <div property="columns">
     <div type="indexcolumn"> 序号 </div>
     <!-- <div field="creditAmt" headerAlign="center" allowSort="true"> 授信金额 </div>
     <div field="productName" headerAlign="center" dictTypeId="product" allowSort="true"> 授信品种 </div>
     <div field="guarantyType" headerAlign="center" allowSort="true"> 担保方式 </div>
     <div field="date" headerAlign="center" allowSort="true"> 授信期限 </div> -->
     
     <div field="partyName" headerAlign="center" allowSort="true"> 客户名称 </div>
     <div field="contractNum" headerAlign="center" allowSort="true"> 合同编号 </div>
     <div field="contractAmt" headerAlign="center" allowSort="true" dataType="currency"> 授信金额 </div>
     <div field="conBalance" headerAlign="center" allowSort="true" dataType="currency"> 授信余额 </div>
     <div field="productType" headerAlign="center" dictTypeId="product" allowSort="true"> 业务品种 </div>
     <div field="guarantyType" headerAlign="center" allowSort="true"> 担保方式 </div>
     <div field="contractTerm" headerAlign="center" allowSort="true"> 授信期限 </div>
     </div>
</div>
</fieldset>

<div id="form2" style="width:100%;height:auto;overflow:hidden;">
<fieldset>
  	<legend>
    	<span>可供资产保全状况</span>
    </legend>

        <input name="preserveStatus" required="true"  class="nui-textarea nui-form-input" style="width:100%;"  />
</fieldset>

<fieldset>
  	<legend>
    	<span>诉讼保全方案</span>
    </legend>    
    <!-- <div style="margin-top:30px;">诉讼保全方案</div>    -->	
        <input name="preservePlan" required="true"  class="nui-textarea nui-form-input" style="width:100%;"  />

 </fieldset>
 </div>
<div class="nui-toolbar" style="border-bottom:none;text-align:right;margin-top:7px;">
	    <a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存预案</a>
	   <!-- <a class="nui-button" iconCls="icon-save" onclick="clickDownload()">生效预案下载</a> -->
	  <!--    <a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">取消</a>-->
</div>

<iframe name="x" id="x" style="display:none;"></iframe>

<script type="text/javascript">
      nui.parse();
                                                  
	    var form = new nui.Form("#form1");                      //客户基本信息表单
	    var form2 = new nui.Form("#form2");                     //客户预案表单
	    var warrant = nui.get("warrant");                       //客户担保表单
        var BizInfo = nui.get("BizInfo");                       //客户业务表单
        var corpid = "<%=request.getParameter("corpid") %>";    //参与人ID
        var bizId = "<%=request.getParameter("bizId") %>";      //获取预警预案主键ID
        var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
        //alert(warrant + "---" + BizInfo + "---" + corpid + "---" + bizId);//bizId、corpid有值
	function initForm() {
     git.mask();
     warrant.load({partyId:corpid});                            //查询并加载客户担保信息
     BizInfo.load({partyId:corpid});                            //查询授信业务情况
     var json=nui.encode({partyId:corpid,planBizId:bizId});
     $.ajax({
            url: "com.bos.ews.warnPlan.getCsmWarnPlanBasicInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	    var text = nui.decode(text);
            	    if(text.rslt==1){
            	       nui.get("btnSave").hide();
            	    }
            	    form.setData(text);                            //加载客户基本信息数据
            	    form2.setData(text.csmPlanBasicInfo);               //查询并加载客户业务信息
            	    git.unmask();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
              }
	});
	
	  //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("btnSave").hide();
			form.setEnabled(false);
		}
	
            		
}
initForm();

function save(){
    
    form2.validate();
    if(form2.isValid()==false){
    return;
    }
    git.mask();
    var warnPlan =form2.getData();
    warnPlan["partyId"]="<%=request.getParameter("corpid") %>";                              
    var json=nui.encode({"warnPlan":warnPlan,"bizId":bizId});
    $.ajax({
            url: "com.bos.ews.warnPlan.startWarnReservePlan.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	alert(text.msg);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
	});
	git.unmask();
}


	function clickDownload(){
        //alert(corpid); //8a70d1f045f366bb0145f36bae8a0004  partyId="+corpid;
		document.getElementById('x').src="com.bos.ews.fileTemplate.warnPlanDownLoad.biz.ext2?"+
								"partyId="+corpid;
								alert("生成预案后，如需上传至服务器请在左侧的文档管理处上传预案！");
		return;
}
	
	</script>
</body>
</html>