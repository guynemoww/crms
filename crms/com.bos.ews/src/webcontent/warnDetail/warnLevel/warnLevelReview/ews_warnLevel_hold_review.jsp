<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp" %>
<html>
<%-- 
  - Author(s): caozhe@git.com.cn
  - Date: 2014-02-25 10:31:55
  - Description:预警信号关闭
--%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>预警级别复核</title>
</head>
<body>

<div id="title"  style="margin-top:30px;font-weight: bold;font-size: 14px;">预警管理->预警级别复核</div>

<div id="form1" style="margin-top:20px;width:80%;height:auto;overflow:hidden;">
	<input type="hidden" name="item" class="nui-hidden" />
	
    <div class="nui-dynpanel" columns="4" style="text-align:center;">
		<label>客户名称</label>
		<input name="item.partyName" required="false"  class="nui-text nui-form-input"  />

		<label>客户编号</label>
		<input name="item.partyNum" required="false" class="nui-text nui-form-input"  />

		<label>授信额度</label>
		<input name="" required="false" class="nui-text nui-form-input"  />

		<label>授信余额</label>
		<input name="" required="false" class="nui-text nui-form-input"  />

		<label>客户等级</label>
		<input name="" required="false" class="nui-text nui-form-input" />

		<label>最新分类</label>
		<input name="" required="false" class="nui-text nui-form-input" />

		<label>预警级别</label>
		<input name="" required="false" class="nui-text nui-form-input" />

		<label>认定日期</label>
		<input name="" required="false" class="nui-text nui-form-input" />

	</div>
</div>

<div  style="margin-top:30px;font-weight: bold">预警信号关闭列表</div>
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" url="com.bos.ews.ews.getaEwsInfo.biz.ext" dataField="csmWarnInfo"
	allowResize="true" showReloadButton="false" showPageSize="false"  pageSize="10" multiSelect="false" sortMode="client">
     <div property="columns">
		<div type="indexcolumn" >序号</div>
		<div field="csmEarlyWarningId" headerAlign="center" allowSort="true" >预警信号</div>
		<div field="earlyWarningLevelCd" headerAlign="center" allowSort="true" >预警信号类别</div>
		<div field="signalSourceCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0001">预警信号来源</div>
		<div field="launchDate" headerAlign="center" allowSort="true" >信号发起日期</div>
		<div field="confirmDate" headerAlign="center" allowSort="true" >信号认定日期</div>
		<div field="signalStatusCd" headerAlign="center" allowSort="true" dictTypeId="EWS_YJXHZT" >信号状态</div>
		<div field="signalState" headerAlign="center" allowSort="true" >预警备注</div>
	 </div>
</div>
   
<div id="form2" style="margin-top:20px;width:80%;height:auto;overflow:hidden;">
	<input type="hidden" name="item" class="nui-hidden" />
	<table style="margin-top:30px;width:100%;border:1px solid gray ;text-align:center;">
      <tr>
              <td style="width:15%;background-color: #cccccc;">
                  <label >认定级别</label>
              </td>
              <td style="width:85%;text-align:left;">
                  <input id="warninglevel" name="" class="nui-dictcombobox nui-form-input"  dictTypeId="XD_YJCD0004" />

              </td>
      </tr>
      <tr>
              <td style="width:15%;background-color: #cccccc;">
                  <label >认定意见</label>
              </td>
              <td style="width:85%;">
                  <input id="holdsuggest" name=""  required="false" style="width:100%;height:100%;"  class="nui-textarea nui-form-input"  />
              </td>
      </tr>
    </table>
</div>

<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	<a class="nui-button" iconCls="icon-save" onclick="update()" id="btnSave">提交</a>
	<a class="nui-button" iconCls="icon-save" onclick="" id="btnSave">取消</a>
</div>


</body>
<script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1"); 
	    var form2= new nui.Form("#form2");
		var grid = nui.get("grid1");
		form.setEnabled(false);
		
		nui.get("warninglevel").setEnabled(false);
		nui.get("holdsuggest").setEnabled(false);
		var corpid= "<%=request.getParameter("corpid") %>";
function initForm() {
     var json=nui.encode({partyId:corpid});
     $.ajax({
            url: "com.bos.ews.warnInfo.csmWarnInfoQuery.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		var text = nui.decode(text);
            	    //alert(text.item.partyName);
               		 form.setData(text);
                     grid.load({partyId:a});
            	}
            	 git.unmask();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                 git.unmask();
            }
	 });
	
            		
}
initForm();

function update(){
  
    var rows = grid.getSelecteds();
   alert(nui.encode({"ob":rows,status:2}));
     $.ajax({
            url:"com.bos.ews.ews.warningClose.biz.ext",
            type:'POST',
            data: nui.encode({"ob":rows,status:2}),
            cache:false,
            contentType:'text/json',
            success:function(){
                if(text){
                
                   nui.alert(text);
                }else{
                  var text=nui.decode(text);
                  
                
                }
                git.unmask();
              },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                 git.unmask();
            }
     })
}      


	</script>
</html>
