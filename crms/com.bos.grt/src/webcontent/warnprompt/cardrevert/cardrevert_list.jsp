<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): huangkai
  - Date: 2014-03-30
  - Description:TB_GRT_CARDREVERT, com.bos.dataset.grt.TbGrtCardrevert
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="4">
		
		<label>担保合同号：</label>
		<input name="tbGrtCardrevert.suretyPactNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:50" />
		
		<label>客户名称：</label>
		<input name="tbGrtCardrevert.clientName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:60" />
	<!-- 
		<label>权证预归还日期：</label>
		<input name="tbGrtCardrevert.cardInRevertDate" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />

		<label>权证借出日期：</label>
		<input name="tbGrtCardrevert.cardLoanDate" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />

		<label>权证借出原因：</label>
		<input name="tbGrtCardrevert.cardLoanReason" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		
		<label>押品名称：</label>
		<input name="tbGrtCardrevert.guarantyName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:60" />

		<label>押品类型：</label>
		<input name="tbGrtCardrevert.guarantyType" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>处理日期：</label>
		<input name="tbGrtCardrevert.handleDate" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />

		<label>处理意见：</label>
		<input name="tbGrtCardrevert.handleIdea" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />

		<label>贷款合同号：</label>
		<input name="tbGrtCardrevert.loanPactNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:50" />

		<label>机构名称：</label>
		<input name="tbGrtCardrevert.orgName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:60" />

		<label>提示关闭状态：</label>
		<input name="tbGrtCardrevert.promptCloseState" required="false" class="nui-textbox nui-form-input" vtype="maxLength:2" />

		<label>提示生成日期：</label>
		<input name="tbGrtCardrevert.promptCreateDate" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />

		<label>提示类型：</label>
		<input name="tbGrtCardrevert.promptType" required="false" class="nui-textbox nui-form-input" vtype="maxLength:2" />

		<label>担保品ID：</label>
		<input name="tbGrtCardrevert.suretyId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		
		<label>权证归还时间主键：</label>
		<input name="tbGrtCardrevert.tablePrikey" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>权证归还时间主键：</label>
		<input name="tbGrtCardrevert.warnId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
 -->

	</div>
</div>
				
<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
 <a class="nui-button" iconCls="icon-edit" onclick="handle()">处理</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.grt.warnprompt.cardrevert.getTbGrtCardrevertList.biz.ext"
	dataField="tbGrtCardreverts"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="orgName" headerAlign="center" allowSort="true" >机构名称</div>
		<div field="clientName" headerAlign="center" allowSort="true" >客户名称</div>
		<div field="suretyPactNum" headerAlign="center" allowSort="true" >担保合同号</div>
		<div field="loanPactNum" headerAlign="center" allowSort="true" >贷款合同号</div>
		<div field="cardLoanDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">权证借出日期</div>
		<div field="cardInRevertDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">权证预归还日期</div>
		<div field="guarantyName" headerAlign="center" allowSort="true" >押品名称</div>
		<div field="guarantyType" headerAlign="center" allowSort="true" dictTypeId="XD_DBCD4002">押品类型</div>
		<div field="promptType" headerAlign="center" allowSort="true" dictTypeId="XD_DBCD4001">提示类型</div>
		<div field="cardLoanReason" headerAlign="center" allowSort="true" >权证借出原因</div>
		<div field="promptCreateDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">提示生成日期</div>
		<div field="promptCloseState" headerAlign="center" allowSort="true" dictTypeId="XD_DBCD4003">处理状态</div>
		<div field="handleDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">处理日期</div>
		<div field="handleIdea" headerAlign="center" allowSort="true" >处理意见</div>
		
		<div field="suretyId" name="suretyId" headerAlign="center" allowSort="true" >担保品ID</div>
		<div field="warnId" name="warnId" headerAlign="center" allowSort="true" >权证归还时间主键</div>
		<!-- 
		 -->
		</div>
	</div>
			
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	//隐藏ID列
	grid.hideColumn(grid.getColumn('warnId'));
	grid.hideColumn(grid.getColumn('suretyId'));
	
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    search();
    
    function reset(){
		form.reset();
	}
	
    //处理预警提示信息
    function handle() {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: "warnprompt/cardrevert/cardrevert_handle.jsp?warnId="+row.warnId,
                title: "处理", 
                width: 550, 
        	    height: 450,
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
