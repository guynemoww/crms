<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-6-9 8:26:27
  - Description:
-->
<head>
<title>档案信息查询</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0"
		style="width: 100%; height: auto;">
		<div title="档案信息查询">
				<div id="form1" class="nui-form"
					style="width: 99.5%; height: auto; overflow: hidden;">
					<input name="sqlName" class="nui-hidden nui-form-input" value="com.bos.rec.rec.recBaseListAll" />
					<div class="nui-dynpanel" columns="4">
						<label>机构名称：</label> 
						<input name="item.mainBank" class="nui-buttonEdit" text="<%=((UserObject)session.getAttribute("userObject")).getUserOrgName()%>" value="<%=((UserObject)session.getAttribute("userObject")).getUserOrgId()%>" required="true" onbuttonclick="selectEmpOrg" />
						<label>客户名称：</label> 
						<input name="item.partyName" class="nui-textbox nui-form-input" />
						<label>证件类型：</label>
						<input id="item.certType" name="item.certType" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002"  allowInput="false" />
						<label>证件号码：</label>			
						<input id="item.certNum"  class="nui-textbox nui-form-input" name="item.certNum" onvalidation=""  />
						<label>生成时间：</label>
						<input name="item.createDate" class="nui-datepicker nui-form-input" showTime="true" format="yyyy-MM-dd" />
						<label>档案状态：</label> 
						<input name="item.recordStatus" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD3202"/>
						<label>经办客户经理：</label> 
						<input name="item.mainCustManager" class="nui-textbox nui-form-input" />
						<label>档案盒编号：</label> 
						<input name="item.recordNum" class="nui-textbox nui-form-input" />
						
						
					</div>
					<div class="nui-toolbar"
						style="text-align: right; border: 0; padding-right: 20px;">
						<a class="nui-button" iconCls="icon-search" onclick="queryInit()">查询</a>
						<a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>
					</div>
				</div>
				<div id="datagrid1" class="nui-datagrid"
					url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
					allowResize="false" showReloadButton="false"allowAlternating="true"
					sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" onselectionchanged="selectPo"sortMode="client">
					<div property="columns">
						<div type="checkcolumn">选择</div>
						<div type="indexcolumn" width="40px">序号</div>
						<div field="recordType" allowSort="true" headerAlign="center" autoEscape="false" dictTypeId="XD_KHCD3201">档案种类</div>
						<div field="partyName" allowSort="true" headerAlign="center" >客户名称</div>
						<div field=contractNum allowSort="true" headerAlign="center">合同编号</div>
						<div field="bookletNum" allowSort="true" headerAlign="center" >借据编号</div>
						<div field="businessType" allowSort="true" headerAlign="center" dictTypeId="product">业务品种</div>
						<div field="createDate" allowSort="true" headerAlign="center" >生成时间</div>
						<div field="amt" allowSort="true" headerAlign="center" >金额</div>
						<div field="recordStatus" allowSort="true" headerAlign="center" dictTypeId="XD_KHCD3202">档案状态</div>
						<div field="inOutDate" allowSort="true" headerAlign="center" >出入库时间</div>
						<div field="recordNum" allowSort="true"  headerAlign="center" dictTypeId="">档案盒编号</div>
						<div field="lendDeadline" allowSort="true" headerAlign="center" >借用期限(天)</div>
						<div field="comeBackDate" allowSort="true" headerAlign="center" >归还日期</div>
						<div field="transferPerson" allowSort="true" headerAlign="center" >移交人</div>
						<div field="receivePerson" allowSort="true"  headerAlign="center" dictTypeId="">接收人</div>
					</div>
				</div>
				<fieldset>
					<legend>
					  <span>变更情况</span>
					</legend>
					<div id="datagrid2" class="nui-datagrid"
						url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
						allowResize="false" showReloadButton="false" allowAlternating="true"
						sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10"
						sortMode="client">
						<div property="columns">
							<div field="createDate" allowSort="true" width="" headerAlign="center"
								autoEscape="false">日期</div>
							<div field="itemType" allowSort="true" width="" headerAlign="center"
								dictTypeId="XD_KHCD3203">事项</div>
							<div field="managerNum" allowSort="true" width="20%"
								headerAlign="center">管理员</div>
							<div field="transferPerson" allowSort="true" width="" headerAlign="center"
								dictTypeId="">移交人</div>
							<div field="lendPerson" allowSort="true" width="" headerAlign="center"
								dictTypeId="">借用人</div>
						</div>
					</div>
				</fieldset>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		git.mask();
		var form = new nui.Form("#form1");
		var grid = nui.get("datagrid1");
		var grid2 = nui.get("datagrid2");
		function queryInit() {
			//校验
			form.validate();
	        if (form.isValid()==false) {
	        	nui.alert("请输入必填项。");
	        	return;   
	        }
			var o = form.getData();//逻辑流必须返回total
			grid.load(o);
			git.unmask();
		}
		queryInit();

		function reset() {
			form.reset();
			queryInit();
		}

		function selectPo(){
			var row = grid.getSelected();
			grid2.load({"item":row,"sqlName":"com.bos.rec.rec.recOperLog"});
		}
		
		function selectEmpOrg(e) {
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