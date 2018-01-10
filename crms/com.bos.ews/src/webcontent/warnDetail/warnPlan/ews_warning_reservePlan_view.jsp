<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp" %>
<html>
<%-- 
  - Author(s): caozhe@git.com.cn
  - Date: 2014-02-15 19:54:03
  - Description:新增预警预案
--%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Title</title>
</head>
<body>
<div style="margin-top:30px;">预警管理->待审核预警预案</div>  

<div style="margin-top:30px;">客户基本信息</div>    
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
        <input type="hidden" name="csmPlanBasicInfo" class="nui-hidden" />
    <div class="nui-dynpanel" columns="4" style="text-align:center;">
		<label >客户编号</label>
		<input name="csmPlanBasicInfo.partyNum"  class="nui-text nui-form-input"  style="width:100%;"/>

		<label>客户名称</label>
		<input name="csmPlanBasicInfo.partyName"  class="nui-text nui-form-input"  style="width:100%;"/>

		<label>客户类型</label>
		<input name="csmPlanBasicInfo.partyTypeCd" class="nui-text nui-form-input"  style="width:100%;" dictTypeId="XD_KHCD0219"   />

		<label>信用等级</label>
		<input name="csmPlanBasicInfo.initialCredit"  class="nui-text nui-form-input"  style="width:100%;"/>

		<label>现有银行授信合计</label>
		<input name="csmPlanBasicInfo.creditExposure"  class="nui-text nui-form-input" style="width:100%;"/>

		<label>现有对外担保合计</label>
		<input name="csmPlanBasicInfo.totalMoney"  class="nui-text nui-form-input" style="width:100%;"/>
    </div>
  
    <div style="margin-top:30px;"></div>   
</div>    

<div  style="margin-top:30px;">担保人基本信息</div>
<div id="warrant" class="nui-datagrid" style="width:100%;height:auto" url="com.bos.ews.warnPlan.getCsmWarrantInfo.biz.ext" 
     dataField="csmWarrants" sortMode="client">
     <div property="columns">
     <div type="indexcolumn"> 序号 </div>
     <div field="partyNum" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true"> 担保人编号 </div>
     <div field="partyName" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true"> 担保人名称 </div>
     <div field="creditExposure" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0001"> 现有银行授信合计 </div>
     <div field="totalSumMoney" headerAlign="center" allowSort="true"> 现有对外担保合计 </div>
     </div>
</div>

<div  style="margin-top:30px;">授信业务情况</div>
<div id="BizInfo" class="nui-datagrid" style="width:100%;height:auto" url="com.bos.ews.warnPlan.getCsmBizInfo.biz.ext" dataField="csmBizInfos"
	allowResize="true" showReloadButton="false" showPageSize="false" pageSize="5" multiSelect="false" sortMode="client">
     <div property="columns">
     <div type="indexcolumn"> 序号 </div>
     <div field="creditExposure" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true"> 授信金额 </div>
     <div field="productType" headerAlign="center" dictTypeId="product" allowSort="true"> 授信品种 </div>
     <div field="guarantyType" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1020"> 担保方式 </div>
     <div field="creditTerm" headerAlign="center" allowSort="true"> 授信期限 </div>
     </div>
</div>

<div id="form2" style="width:100%;height:auto;overflow:hidden;">
    <div style="margin-top:30px;">可供保全资产状况</div>   	    
        <input name="preserveStatus" required="true"  class="nui-textarea nui-form-input" style="width:100%;"  />
     
    <div style="margin-top:30px;">诉讼保全方案</div>   	
        <input name="preservePlan" required="true"  class="nui-textarea nui-form-input" style="width:100%;"  />
</div> 
 
<div id="nui-toolbar" class="nui-toolbar" style="border-bottom:0;text-align:right;margin-top:30px;">
	    <a class="nui-button" iconCls="icon-save" onclick="updateData" id="btnSave">保存</a>
</div>
<script type="text/javascript">
      nui.parse();
	    var form = new nui.Form("#form1");                     //客户基本信息
	    var form2 = new nui.Form("#form2");                    //资产保全
	    var BizInfo = nui.get("BizInfo");                      //授信业务
	    var warrant = nui.get("warrant");                      //担保信息
	    var planBizId = "<%=request.getParameter("bizId") %>"; //获取流程载体数据
	    var view = "<%=request.getParameter("view") %>";       //判断是否只读
	    var judge;                                             //用于判断当前岗位是否已经新增过预案，如果新增过预案则只在当前岗位新增的预案基础上做修改不再做新增操作。
        if(view==1){
           nui.get("nui-toolbar").hide();      
           form2.setEnabled(false);
        }
        
function initForm() {
     var json=nui.encode({planBizId:planBizId});
     $.ajax({
            url: "com.bos.ews.warnPlan.getCsmWarnPlanBasicInfo.biz.ext",
            type: 'POST', 
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
                
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		var text = nui.decode(text);
            	    form.setData(text);
            	    form2.setData(text.csmPlanBasicInfo);               //查询并加载客户业务信息
            	    BizInfo.load({partyId:text.csmPlanBasicInfo.partyId});
            	    warrant.load({partyId:text.csmPlanBasicInfo.partyId});
            	    if(text.rslt2==0){
                      nui.get("nui-toolbar").hide();      
                      form2.setEnabled(false);
                    }
            	    git.unmask();
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                git.unmask();
            }
	});
	
            		
}
initForm();

//获取预警预案内容
function getWarnPlanInfo() {
     var json=nui.encode({planBizId:planBizId});
     $.ajax({
            url: "com.bos.ews.warnPlan.getCsmWarnPlanInfo_inFlow.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	    form2.setData(text);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                git.unmask();
            }
	});           		
}

function save(){
    form2.validate();
    if(form2.isValid()==false){
    return;
    }
    git.mask();
    var warnPlan =form2.getData();
    var json=nui.encode({"warnPlan":warnPlan,"judge":judge,flowStatus:"01",planBizId:planBizId});
    $.ajax({
            url: "com.bos.ews.warnPlan.saveCsmWarnPlan.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
                 alert(text.msg);
                 judge = text.outMsg;
                 initForm();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(text.msg);
                nui.alert(jqXHR.responseText);
            }
	});
	
}

//用于以保存数据但是未提交的情况下需要再次修改的时候
function updateData(){
    form2.validate();
    if(form2.isValid()==false){
    return;
    }
    git.mask();
    var warnPlan =form2.getData();                             
    var json=nui.encode({"warnPlan":warnPlan,planBizId:planBizId});        //flowStatus为02表示当前岗位为部门负责人岗。
    $.ajax({
            url: "com.bos.ews.warnPlan.updateCsmPlan.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
                 alert(text.msg);
            	 initForm();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(text.msg);
                nui.alert(jqXHR.responseText);
            }
	});
}

/*查询是否有未走完流程的预案信息 
function checkWarnPlan(){
    var json=nui.encode({"partyId":bizId,flowStatus:"01"});            //flowStatus为02表示当前岗位为部门负责人岗。
    $.ajax({
            url: "com.bos.ews.warnPlan.checkWarnPlan.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
                 if(text.total){
                   updateData();
                  //form2.clear();
            	  initForm();
                 }else{
                  alert("total:"+text.total);
                  save();
                 }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
	});

}*/
	</script>
</body>
</html>