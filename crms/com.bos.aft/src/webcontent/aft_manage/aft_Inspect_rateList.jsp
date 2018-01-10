<%@page pageEncoding="UTF-8"%>

<html>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-26 09:13:06
  - Description:设置待检查客户的检查频率
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>检查频率设置</title>
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;">
	<div title="检查频率设置" >
		<iframe name="exportFrame" id="exportFrame" src="" style="display:none;"></iframe>
	
	<center>
	<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;" >
		<div class="nui-dynpanel" columns="6">
 			<label>客户名称：</label>
			<input name="item.partyName" class="nui-textbox nui-form-input" vtype="maxLength:64"/>
			
			<label>所属机构：</label>
			<input id="item.orgid" name="item.orgid"  allowInput="false" class="nui-buttonEdit" onbuttonclick="selectOrg"/>
			
			<label>信用评级：</label>
			<input name="item.creditRatingCd" class="nui-textbox nui-form-input" vtype="maxLength:64"/>
			
			<label>风险分类：</label>
			<input name="item.classificationResultCd" class="nui-dictcombobox nui-form-input" dictTypeId="XD_FLCD0001"/>
			
			<label>是否异地：</label>
			<input name="item.isDifferentPlace" class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001"/>
			
			<label>预警级别：</label>
			<input name="item.warningLevelCd" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YJJB0001"/>
			
			
		</div>
		
		<div id="div1" class="nui-dynpanel" columns="6">
			<label>监控名单类型：</label>
			<input name="item.listStatus" class="nui-dictcombobox nui-form-input" dictTypeId="XD_JKMD0001"/>
			
			<label>检查频率：</label>
			<input name="item.setRate" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0001"/>
		</div>
		
		<div class="nui-toolbar" style="text-align:right;border:none" >
			<a class="nui-button"   iconCls="icon-search" onclick="query">查询</a>
			<a class="nui-button"   iconCls="icon-reset" onclick="reset">重置</a>
			<a class="nui-button"   iconCls="icon-download" onclick="dc()">导出EXCEL</a>
			
		</div>
	</div>
	
	<div>
		<div align="right" style="margin-top:7px;width:99.5%">设置检查频率：
			<input id="item.rateSet" name="text" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0001" style="margin-right:5px;height:21px;width:13%"/>
			<a class="nui-button" style="margin-right:27px;height:21px" onclick="set">设置</a>
		</div>
	</div>
	<div style="width:99.5%">
	<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;">
		<!-- 
		<a id="addCust" style="margin-left:5px" class="nui-button"  iconCls="icon-add" onclick="add()">新增</a>
		<a id = "query" class="nui-button" iconCls="icon-node" onclick="query()">查看</a>
		<a id="editCust" class="nui-button" iconCls="icon-edit" onclick="edit()">编辑</a>
		<a id="smallCorp" class="nui-button" iconCls="icon-upload" onclick="smallCorpIdentify(0)">发起小企业认定</a>
		 -->
	</div>
</div>
	<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;"  sizeList="[10,20,50,100]"  showReloadButton="false"
		    multiSelect="true"  url="com.bos.aft.aft_manage.queryRateList2.biz.ext" dataField="rates" pageSize="10" allowAlternating="true">
		<div property="columns">
			<div type="checkcolumn" ></div>
			<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
			<div field="orgNum" headerAlign="center" dictTypeId="org" >所属机构</div>
			<div field="customerTypeCd" headerAlign="center" allowSort="true" dictTypeId="XD_KHCD1001" >客户类型</div> 
			<div field="creditRatingCd" headerAlign="center" allowSort="true" >信用等级</div>
            <div field="classificationResultCd" headerAlign="center" allowSort="true" dictTypeId="XD_FLCD0001" >风险分类</div>
            <div field="isDifferentPlace" headerAlign="center" allowSort="true" dictTypeId="CDGY0001">是否异地</div>
            <div field="warningLevelCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJJB0001" >预警级别</div>
			<div field="listStatus" headerAlign="center" allowSort="true" dictTypeId="XD_JKMD0001" >监控名单类型</div>
			<div field="approveRate" headerAlign="center" allowSort="true" dictTypeId="XD_DHCD0001">检查频率（变更前）</div>		
			<div field="setRate" headerAlign="center" allowSort="true" dictTypeId="XD_DHCD0001">检查频率（变更后）</div>
       </div>
	</div>
	</center>
	</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var rateSet = nui.get("item.rateSet");
		var form = new nui.Form("#form1");
		var grid = nui.get("datagrid1");
		var orgDegree;
		var total;
		$("#div1").css("display","none");
				
		//查询客户检查频率列表
		function query(){
		    var data = form.getData(false, true);//逻辑流必须返回total
	        grid.load(data);
        	query1();
        	
        	 
		}
		//加载调用查询方法
		query();
		
		function query1(){
		
	       var json = nui.encode({});
			$.ajax({
	            url: "com.bos.aft.aft_manage.queryRateListInit.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (mydata) {
	            	orgDegree = mydata.orgDegree;
	            	//alert(orgDegree);
	            	
	            	if(orgDegree == "1") {
	            		$("#div1").css("display","block");
	            		//grid.showColumn(grid.getColumn("isDifferentPlace")); 
	            		//grid.showColumn(grid.getColumn("warningLevelCd")); 
	            	}else {
	            		$("#div1").css("display","none");
	            		//grid.hideColumn(grid.getColumn("isDifferentPlace")); 
	            		//grid.hideColumn(grid.getColumn("warningLevelCd")); 
	            		grid.hideColumn(grid.getColumn(7)); 
	            		grid.hideColumn(grid.getColumn(6)); 
	            	}
	            	
	            	var o = nui.decode(mydata);
 	            	//form.setData(o);
				}
        	}); 
		} 
		
		//重置查询条件
		function reset(){
			form.clear();
		}
		//设置检查频率
		function set(){
			var row = grid.getSelecteds();
			
			if(row.length < 1) return nui.alert("请选中行！");
			
			if(rateSet.getValue()=="") return nui.alert("请选择检查频率！");
			
			var item = new Array();
			for(var i = 0;i < row.length;i++){
				item[i] ={"irId":row[i].irId,"approveRate":row[i].setRate,"setRate":rateSet.getValue(),"partyId":row[i].partyId};
			}
			
			var json = nui.encode({"item":item});
			
			nui.confirm("确定修改选中客户的检查频率？", "确定？",function (action) {            
			    if (action == "ok") {
			    	git.mask(); 
					$.ajax({
			            url: "com.bos.aft.aft_manage.updateRate.biz.ext",
			            type: 'POST',
			            data: json,
			            cache: false,
			            contentType:'text/json',
			            success: function (text) {
			            	if(text.msg==1){
			            		nui.alert("设置客户检查频率成功！");
			            		grid.reload();
			            	} else if(text.msg==0){
			            		nui.alert("设置客户检查频率失败！");
			            	}
					       
							git.unmask();
			            },
			            error: function (jqXHR, textStatus, errorThrown) {
			                nui.alert(jqXHR.responseText);
			                git.unmask();
			            }
					});
			    }
			});
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
		function dc(){
			var jg=nui.get("item.orgid").getValue();	
			if(jg=="10000" ||jg==""){
			
			alert("所属机构必输且不能为总行！");
			return;
			}	
			
		
			git.mask();
				var ifrm = document.getElementById("exportFrame");
		
		 var o = form.getData();//逻辑流必须返回total
  		 
	
		 	 	var json = nui.encode(o);
		 
 		$.ajax({
	            url: "com.bos.csm.pub.ibatis.DHJCPLDownloadEXCEL.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
 	            	if(text.msg=="0000"){
	            	 git.unmask();
	            	 nui.alert("数据量超过50000,请重新选择下级机构！");
	            	 
	            	 return;
	            	}
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
	</script>
</body>
</html>