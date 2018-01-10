<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-6-3 8:26:27
  - Description:
-->
<head>
<title>监控台账</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
	<iframe name="exportFrame" id="exportFrame" src="" style="display:none;"></iframe>
	<!--<div id="tabs1" class="nui-tabs" activeIndex="0"
		style="width: 99.5%; height: auto; overflow: auto;">-->
		<div title="监控台账">
			<center>
				<div id="form1" class="nui-form"
					style="width: 99.5%; height: auto; overflow: hidden;">
					<input name="sqlName" class="nui-hidden nui-form-input" value="item.dz" />
					
					<div class="nui-dynpanel" columns="4">
						<label class="nui-form-label">机构名称：</label>
						<input id="item.orgId" name="item.orgId" text="<%=((UserObject)session.getAttribute("userObject")).getUserOrgName()%>" value="<%=((UserObject)session.getAttribute("userObject")).getUserOrgId()%>" allowInput="false" required="false" class="nui-buttonEdit" onbuttonclick="selectOrg"/>
						<label>监控类型：</label>
						<input id="item.jklx" name="item.jklx" class="nui-dictcombobox nui-form-input"   dictTypeId="XD_JKTZ001"  allowInput="false" required="false" />
						<label>客户名称：</label>
						<input id="item.partyName" name="item.partyName" required="false" class="nui-textbox nui-form-input" />
						<label>证件类型：</label>
						<input id="item.certType" name="item.certType" class="nui-dictcombobox nui-form-input"   dictTypeId="CDKH0002"  allowInput="false" />
						<label>证件号码：</label>
						<input id="item.certNum" name="item.certNum" required="false" class="nui-textbox nui-form-input"/>
						
						<label>贷款品种：</label>			
						<input id="item.productType"  class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectProduct"  name="item.productType" dictTypeId="product" />
						
						<label>合同编号：</label>
						<input id="item.contractNum" name="item.contractNum" required="false" class="nui-textbox nui-form-input" />
						
						<label>借据编号：</label>
						<input id="item.summaryNum" name="item.summaryNum" required="false" class="nui-textbox nui-form-input" />
						
						<label  class="nui-form-label">借据起期：</label>
						<div colspan="1">
						<input id="item.date1" class="nui-DatePicker nui-form-input" style="width:100px;" name="item.date1" required="false" />-
						<input id="item.date2" class="nui-DatePicker nui-form-input" style="width:100px;" name="item.date2" required="false" />
						</div>
						
						<label  class="nui-form-label">借据止期：</label>
						<div colspan="1">
						<input id="item.date3" class="nui-DatePicker nui-form-input" style="width:100px;" name="item.date3" required="false" />-
						<input id="item.date4" class="nui-DatePicker nui-form-input" style="width:100px;" name="item.date4" required="false" />
						</div>
						
						<label>金额(万元)：</label>
						<input id="item.summaryAmt1" name="item.summaryAmt1" class="nui-dictcombobox nui-form-input"   dictTypeId="XD_JKJE0526"  allowInput="false" />
						
						<label>天数：</label>
						<input id="item.ts" name="item.ts" class="nui-dictcombobox nui-form-input"   dictTypeId="XD_JKTS0526"  allowInput="false" />
						
						<!--  
						<label  class="nui-form-label">金额：</label>
						<div colspan="1">
						<input id="item.summaryAmt1" name="item.summaryAmt1" required="false" style="width:100px;" class="nui-textbox nui-form-input"/>-<input id="item.summaryAmt2" name="item.summaryAmt2" required="false" style="width:100px;" class="nui-textbox nui-form-input"/>
						</div>		
								
						<label class="nui-form-label">天数：</label>
						<div colspan="1">
						<input id="item.ts1" name="item.ts1" required="false" style="width:100px;" class="nui-textbox nui-form-input" />-
						<input id="item.ts2" name="item.ts2" required="false" style="width:100px;" class="nui-textbox nui-form-input" />
						</div>
						-->
						<% UserObject user = (UserObject)session.getAttribute("userObject");
						String manage = "";
						DataObject[] roles =  (DataObject[]) user.getAttributes().get("roles");
						if (null != roles && roles.length > 0) {
							for (int i=0; i<roles.length; i++) {
									DataObject role = roles[i];
									if ("R1002".equals(role.get("roleid"))||"R1003".equals(role.get("roleid"))||
										"R1159".equals(role.get("roleid"))||"R1153".equals(role.get("roleid"))||
										"R1147".equals(role.get("roleid"))||
									"R1006".equals(role.get("roleid"))||"R1007".equals(role.get("roleid"))){
										manage="true";
									}else{
										continue;
									}
								}	        			
						}
						if(manage.equals("true")){
					%>
						<input id="item.userNum"  class="nui-hidden nui-form-input" name="item.userNum"  value="<%=((UserObject)session.getAttribute("userObject")).getUserId()%>" />
					<% 
						}
					%>
						
					</div>

					
					<div class="nui-toolbar"
						style="text-align: right; border: 0; padding-right: 10px;">
						<a class="nui-button" iconCls="icon-search" onclick="queryInit()">查询</a>
						<a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>
						<!-- <a class="nui-button" iconCls="icon-download" onclick="dc()">导出EXCEL</a> -->
					</div>
					
				</div>
				<div id="datagrid1" class="nui-datagrid" style="width: 99.5%; height: auto" 
					url="com.bos.csm.pub.ibatis.GetItem1.biz.ext" dataField="items"
					allowResize="true" showReloadButton="false" allowAlternating="true"
					sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" onselectionchanged="" sortMode="client">
					<div property="columns">
						<div field="orgNum" allowSort="true" width="" headerAlign="center"
							autoEscape="false" dictTypeId="org">机构名称</div>
						<div field="partyName" allowSort="true" width="" headerAlign="center"
							dictTypeId="">客户名称</div>
						<div field="productType" allowSort="true" width="" headerAlign="center"
							autoEscape="false" dictTypeId="product">业务品种</div>
						<div field="contractNum" allowSort="true" width="" headerAlign="center"
							dictTypeId="">合同编号</div>
						<div field="summaryNum" allowSort="true" width="" headerAlign="center"
							dictTypeId="">借据编号</div>
						<div field="summaryCurrencyCd" allowSort="true" width="" headerAlign="center"
							dictTypeId="CD000001">币种</div>
						<div field="summaryAmt" allowSort="true" width="" headerAlign="center"
							dictTypeId="">借据金额</div>
						<div field="jjye" allowSort="true" width="" headerAlign="center"
							dictTypeId="">借据余额</div>
						<div field="beginDate" allowSort="true" width="" headerAlign="center"
							dictTypeId="">借据起期</div>
						<div field="endDate" allowSort="true" width="" headerAlign="center"
							dictTypeId="">借据止期</div>
						<div name="fljg" field="fljg" allowSort="true" width="" headerAlign="center"
							dictTypeId="XD_FLCD0001">分类 </div>
					    <div name ="yqts" field="yqts" allowSort="true" width="" headerAlign="center"
							dictTypeId="">逾期天数 </div>
							
						<div name ="jjyqbj" field="jjyqbj" allowSort="true" width="" headerAlign="center"
							dictTypeId="">逾期本金 </div>
							
						<div name ="normalItr" field="normalItr" allowSort="true" width="" headerAlign="center"
							dictTypeId="">正常利息</div>
						<div name ="arrearItr" field="arrearItr" allowSort="true" width="" headerAlign="center"
							dictTypeId="">拖欠利息</div>
						<div name ="punishItr" field="punishItr" allowSort="true" width="" headerAlign="center"
							dictTypeId="">罚息</div>
							
						<div name ="zh" field="zh" allowSort="true" width="" headerAlign="center"
							dictTypeId="">还款账号</div>
							
						<div name="qx" field="dftItr" allowSort="true" width="" headerAlign="center"
							dictTypeId="">欠息 </div>
						<div name="dqts" field="dqts" allowSort="true" width="" headerAlign="center"
							dictTypeId="">到期天数 </div>	
						<div name="fkts" field="fkts" allowSort="true" width="" headerAlign="center"
							dictTypeId="">已发放天数 </div>
						
						<div name="padUpPrn" field="padUpPrn" allowSort="true" width="" headerAlign="center"
							dictTypeId="">收回金额 </div>
						<div name="rcvDate" field="rcvDate" allowSort="true" width="" headerAlign="center"
							dictTypeId="">收回日期 </div>
						
						
						<div name="userNum" field="userNum" allowSort="true" width="" headerAlign="center"
							dictTypeId="user">经办人</div>
					</div>
				</div>
				
			</center>
		</div>
	<!--  </div>-->
	<script type="text/javascript">
		
		nui.parse();
		//git.mask();
		var form = new nui.Form("#form1");		
		var grid = nui.get("datagrid1");
		
		/*
		var arr = git.getDictDataFilter("XD_JKTZ001","1,2,3,4,5");
		nui.get("item.jklx").setData(arr);*/
		nui.get("item.jklx").setValue("1");
		function dc(){
			git.mask();
			var ifrm = document.getElementById("exportFrame");
			var o = form.getData();//逻辑流必须返回total
			
			var jklx = nui.get("item.jklx").getValue();	
			
			if (jklx == null || jklx==''){
				o.item.jklx = '1';
				o.item.dz = "com.bos.account.jiankong.yuqi";
				o.sqlName = "com.bos.account.jiankong.yuqi";
			}
			else if(jklx=='1'){
				o.item.dz = "com.bos.account.jiankong.yuqi";
				o.sqlName = "com.bos.account.jiankong.yuqi";
			}
			else if(jklx=='2'){
				o.item.dz = "com.bos.account.jiankong.daoqi";
				o.sqlName = "com.bos.account.jiankong.daoqi";
			}
			else if(jklx=='3'){
				o.item.dz = "com.bos.account.jiankong.fangkuan";
				o.sqlName = "com.bos.account.jiankong.fangkuan";
			}
			else if(jklx=='4'){
				o.item.dz = "com.bos.account.jiankong.huishou";
				o.sqlName = "com.bos.account.jiankong.huishou";
			}
			else{
				o.item.dz = "com.bos.account.jiankong.qianxi";
				o.sqlName = "com.bos.account.jiankong.qianxi";
			}
			
	  		var json = nui.encode(o);
			 
	 		$.ajax({
	            url: "com.bos.csm.pub.ibatis.downloadEXCELJKTZ.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
            	git.unmask("form1");
            	if(text.msg){
				git.unmask();
					
	           	ifrm.src=nui.context +"/pub/io/file/download.jsp?deleteFile=true";
		            	
	            	}else{
	            	git.unmask();
	            	 nui.alert("下载数据有误！");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	       			git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
			});
		}
		
		function queryInit() {
			//git.mask();
			
			/*
			if (form.isValid()==false) {
	        	nui.alert("请输入必填项。");
	        	git.unmask();
	        	return;   
	        }*/
		
			var o = form.getData();//逻辑流必须返回total

			var jklx = o.item.jklx;
			/*
			if (jklx == null || jklx==''){
				nui.alert("监控类型为必填项。");
				git.unmask();
				return
			}*/

			if (jklx == null || jklx==''){
				o.item.jklx = '1';
				o.item.dz = "com.bos.account.jiankong.yuqi";
				o.sqlName = "com.bos.account.jiankong.yuqi";
			}
			else if(jklx=='1'){
				o.item.dz = "com.bos.account.jiankong.yuqi";
				o.sqlName = "com.bos.account.jiankong.yuqi";
			}
			else if(jklx=='2'){
				o.item.dz = "com.bos.account.jiankong.daoqi";
				o.sqlName = "com.bos.account.jiankong.daoqi";
			}
			else if(jklx=='3'){
				o.item.dz = "com.bos.account.jiankong.fangkuan";
				o.sqlName = "com.bos.account.jiankong.fangkuan";
			}
			else if(jklx=='4'){
				o.item.dz = "com.bos.account.jiankong.huishou";
				o.sqlName = "com.bos.account.jiankong.huishou";
			}
			else{
				o.item.dz = "com.bos.account.jiankong.qianxi";
				o.sqlName = "com.bos.account.jiankong.qianxi";
			}
			
			var jklx = nui.get("item.jklx").getValue();
	   		if(jklx=='' || jklx == null || jklx == '1' ){
	   		
	   			grid.showColumn(grid.getColumn("yqts"));
	   			grid.showColumn(grid.getColumn("jjyqbj"));
	     		grid.showColumn(grid.getColumn("normalItr"));
	     		grid.showColumn(grid.getColumn("arrearItr"));
	     		grid.showColumn(grid.getColumn("punishItr"));
	     		grid.showColumn(grid.getColumn("zh"));
	   			
	     		grid.hideColumn(grid.getColumn("qx"));		
	     		grid.hideColumn(grid.getColumn("fljg"));
	     		grid.hideColumn(grid.getColumn("dqts"));
	     		grid.hideColumn(grid.getColumn("fkts"));
	     		grid.hideColumn(grid.getColumn("rcvDate"));
	     		grid.hideColumn(grid.getColumn("padUpPrn"));
	   		}else if (jklx == '2'){
	   			//alert(nui.get("item.date1").getValue());
	   			
	     		grid.showColumn(grid.getColumn("dqts"));
	     		grid.showColumn(grid.getColumn("fljg"));
	     		
	     		grid.hideColumn(grid.getColumn("yqts"));
	     		grid.hideColumn(grid.getColumn("jjyqbj"));
	     		grid.hideColumn(grid.getColumn("normalItr"));
	     		grid.hideColumn(grid.getColumn("arrearItr"));
	     		grid.hideColumn(grid.getColumn("punishItr"));
	     		grid.hideColumn(grid.getColumn("zh"));
	     		grid.hideColumn(grid.getColumn("qx"));
	     		grid.hideColumn(grid.getColumn("fkts"));
	     		grid.hideColumn(grid.getColumn("rcvDate"));
	     		grid.hideColumn(grid.getColumn("padUpPrn"));
	   		}else if (jklx == '3'){
	   			
	     		grid.showColumn(grid.getColumn("fkts"));
	     		grid.showColumn(grid.getColumn("fljg"));
	     		
	     		grid.hideColumn(grid.getColumn("yqts"));
	     		grid.hideColumn(grid.getColumn("jjyqbj"));
	     		grid.hideColumn(grid.getColumn("normalItr"));
	     		grid.hideColumn(grid.getColumn("arrearItr"));
	     		grid.hideColumn(grid.getColumn("punishItr"));
	     		grid.hideColumn(grid.getColumn("zh"));
	     		grid.hideColumn(grid.getColumn("qx"));
	     		grid.hideColumn(grid.getColumn("dqts"));
	     		grid.hideColumn(grid.getColumn("rcvDate"));
	     		grid.hideColumn(grid.getColumn("padUpPrn"));
	   		}else if (jklx == '5'){
	   			grid.showColumn(grid.getColumn("yqts"));	
	     		grid.showColumn(grid.getColumn("normalItr"));
	     		grid.showColumn(grid.getColumn("arrearItr"));
	     		grid.showColumn(grid.getColumn("punishItr"));
	     		grid.showColumn(grid.getColumn("fljg"));
	     		
	   			grid.hideColumn(grid.getColumn("zh"));
	   			grid.hideColumn(grid.getColumn("jjyqbj"));
	     		grid.hideColumn(grid.getColumn("qx"));		
	     		grid.hideColumn(grid.getColumn("userNum"));	
	     		grid.hideColumn(grid.getColumn("dqts"));
	     		grid.hideColumn(grid.getColumn("fkts"));
	     		grid.hideColumn(grid.getColumn("rcvDate"));
	     		grid.hideColumn(grid.getColumn("padUpPrn"));
	   		
	   		}else {
	   		
	   			grid.showColumn(grid.getColumn("rcvDate"));
	   			grid.showColumn(grid.getColumn("padUpPrn"));
	   			grid.showColumn(grid.getColumn("fljg"));
	     		
	     		grid.hideColumn(grid.getColumn("yqts"));
	     		grid.hideColumn(grid.getColumn("normalItr"));
	     		grid.hideColumn(grid.getColumn("arrearItr"));
	     		grid.hideColumn(grid.getColumn("punishItr"));
	     		grid.hideColumn(grid.getColumn("jjyqbj"));
	     		grid.hideColumn(grid.getColumn("userNum"));	
	     		grid.hideColumn(grid.getColumn("zh"));
	     		grid.hideColumn(grid.getColumn("qx"));
	     		grid.hideColumn(grid.getColumn("dqts"));
	     		grid.hideColumn(grid.getColumn("fkts"));
	   		}

			grid.load(o);
			git.unmask();
		}
		queryInit();

		function reset() {
			form.reset();
			nui.get("item.jklx").setValue("1");
			queryInit();
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
	                        btnEdit.setValue(data.orgid);
	                        btnEdit.setText(data.orgname);
	                    }
	                }
	            }
	        });      
		}
		
		
		//产品树
	function selectProduct(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/product/product/select_product_tree.jsp",
            title: "选择",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.productCd);
                        btnEdit.setText(data.productName);
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}
		
		
		grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['partyName']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].partyId+ '\');">'+e.data[i]['partyName']+'</a>';
				e.data[i]['contractNum']='<a href="#" onclick="bizView3231(\''+ e.data[i].contractNum+ '\');">'+e.data[i]['contractNum']+'</a>';
				e.data[i]['summaryNum']='<a href="#" onclick="bizView3231(\''+ e.data[i].summaryNum+ '\');">'+e.data[i]['summaryNum']+'</a>';
			}
		});
		
	</script>
</body>
</html>