<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): caozhe@git.com.cn
  - Date: 2014-04-10
  - Description:
-->
<head>
<title>评级结果查询</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
 <form id="form1" action="" method="post" enctype="multipart/form-data" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
<center>
<!-- 
<div id="form1" style="width:90%;height:auto;overflow:hidden;"> 
 -->
	<fieldset>
  	<legend>
    	<span>查询条件</span>
    </legend>
		
		<div class="nui-dynpanel" columns="4">
			<label>客户名称</label>
			<input ID='partyName' name="map/partyName"  class="nui-textbox nui-form-input" />
	
			<label>客户编号</label>
			<input ID='partyNum' name="map/partyNum"  class="nui-textbox nui-form-input"  />
			 <!-- 
			<label>组织机构代码</label>
			<input ID='orgnNum' name="map/orgnNum"  class="nui-textbox nui-form-input" />
			-->
			<label>客户类型</label>
			<input ID='partyTypeCd' name="map/partyTypeCd" valueField="dictID" textField="dictName" dictTypeId="XD_KHCD0219"
					 emptyText="请选择..." class="nui-dictcombobox"  />
	
			<label>经办人</label>
			<input id='empname' name="map/empname"  class="nui-textbox nui-form-input"  />
	
			<label>经办机构</label>
			<input id='orgname'  name="map/orgname"  required="false" class="nui-buttonEdit nui-form-input"  allowInput="false" onbuttonclick="selectCodeList" vtype="maxLength:200" />
		</div>
	</fieldset>
			<input id="orgCode" name="map/orgcode" class="nui-textbox nui-form-input" visible="false" />	
</center>				
<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	<a class="nui-button" style="margin-right:20px;height:21px" onclick="exportEmp" type="submit" />导出Excel表格</a>
</div>
 </form>

 <div id="editForm1" style="width:100%;"> 
	<div style="margin-top:10px;">评级结果信息</div>   
 		
		<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" url="com.bos.irm.queryRatingResult.queryRatingResultInfos.biz.ext"    
			dataField="ratingResultList"  multiSelect="false" sizeList="[10,15,20,50,100]"  pageSize="10" >
			<div property="columns">
			    <div type="checkcolumn">选择</div>
				<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
				<div field="partyId" headerAlign="center" allowSort="true" visible="false" >客户id</div>
				<div field="iraApplyId" headerAlign="iraApplyId" allowSort="true" visible="false" >评级申请id</div>
				<div field="irrResultId" headerAlign="center" allowSort="true" visible="false" >评级结果id</div>
				<div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>
				<div field="partyTypeCd" headerAlign="center" allowSort="true" dictTypeId="XD_KHCD0219"  >客户类型</div>
				<div field="projectId" headerAlign="center" allowSort="true"  >项目ID</div>
				<div field="projectName" headerAlign="center" allowSort="true"  >项目名称</div>
				<div field="creditRatingCd" headerAlign="center" allowSort="true"   >评级结果</div>
				<div field="orgNum" headerAlign="center" allowSort="true" >组织机构代码</div>
				<div field="ratingState" headerAlign="center" allowSort="true"  dictTypeId="XD_PJCD0005"   >状态</div>
				<div field="effectiveEndDt" headerAlign="center" allowSort="tru e" >评级有效期</div>
				<div field="applyDate" headerAlign="center" allowSort="true" >评级申请日期</div>
				<div field="empname" headerAlign="center" allowSort="true" >经办人</div>
				<div field="orgname" headerAlign="center" allowSort="true" >经办机构</div>
			</div>
		</div>	
 </div>	    
	
	<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" 
		    borderStyle="border:0;">
	    <a class="nui-button" style="margin-right:20px;height:21px" iconCls="icon-add" onclick="check">查看结果报告</a>
	</div>
			
    <script type="text/javascript">
 	nui.parse();
 	
    var form = new nui.Form("#form1"); 
	var grid1 = nui.get("grid1");

	var data = form.getData();
	var partyId ; 
	function init(){
		nui.ajax({//获取当前登录人机构
	        url: "com.bos.irm.queryInfo.queryOrgCode.biz.ext",
	        type: 'POST',
	        cache: false,
		    async:false,        
	        contentType:'text/json',
	        success: function (text) {
	        	if(text.msg){
	        		alert(text.msg);
	        	} else {
	        		var o = nui.decode(text);
					nui.get("orgCode").setValue(o.outOrgCode);
	            }
	        }
	    });
	}
	init();
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
	function check(){
   			var row = grid1.getSelected();
   			if(row){
   				if(row.iraApplyId){
   				}else{
   					return alert("没有可查看的评级报告");
   				}
				nui.open({
		            url:  nui.context + "/irm/financialCustom/financial_view_report.jsp?bizId="+row.iraApplyId,
		            title: "查看评级报告",
		            onload: function () {
		            },
	            	ondestroy: function (action) {
	            		grid1.reload();
	            	}
	        	});
   			}else{
   				return alert("请选择一条评级信息");
   			}
   	}
	
	function query(){
		var partyName = nui.get('partyName').getValue();
		var partyNum = nui.get('partyNum').getValue();
		/*
		if(!partyName){
			if(!partyNum){
				return alert("客户名称和客户编号至少有一个必填");
			}
		}*/
		var data = form.getData();
		grid1.load(data); 
	}

    function reset(){
		form.reset();
	}

    function ratingCreate(){
	    var row ;
		row = grid1.getSelected();
	    if(row){
			if(row.partyTypeCd == '01' || row.partyTypeCd == '05' || row.partyTypeCd == '06'){
		    	git.mask();
				var json=nui.encode({"rowData":row});
				 nui.ajax({
	                url: "com.bos.irm.queryRatingResult.createFlowReAud.biz.ext", //创建再审核流程
	                data:json,
	                type:"POST",
	                contentType:'text/json',
	                success: function (text) {
	                	grid1.reload();
						git.unmask();
						if(text.flag){
		                	//跳转到评级页面
		                	var applyId = text.newIrmAppId;
		                	var flowType = '03';	//再审核流程
		                	//var url=nui.context+"/irm/singleCustom/creditRate/eval_corp_tree.jsp?applyId="+row.iraApplyId+"&reAud="+reAud+"&partyId="+row.partyId+"&newIrmAppId="+newIrmAppId+"&projectId="+row.projectId+"&flowType="+flowType;
		                	var url=nui.context+"/irm/singleCustom/creditRate/eval_corp_tree.jsp?applyId="+applyId+"&partyId="+row.partyId+"&flowType="+flowType;
							git.go(url);
						}else{
							return alert(text.msg);
						}
	                },
	                error: function (jqXHR, textStatus, errorThrown) {
	                    alert(jqXHR.responseText);
	                    grid1.reload();
	                    git.unmask();
	                }
	            });	
			}else{
				alert("只能对公司客户，担保机构和集团客户再审核。");
				return ;
			}
	    }else{
	    	alert("请选择一条记录");
	    }
    }
    
    
    //导出
    function exportEmp() {
    	var rows = grid1.findRows(function(row){
   	 		if(row.partyName != null) return true;
		});
		if(rows != null && rows.length > 0) {//有要导出的记录
			var forms = document.getElementById("form1");
			forms.action = "com.primeton.example.excel.empManager.flow?_eosFlowAction=exportFile&importCd=20";
			forms.submit();
		} else {
			alert('没有要导出的记录');
		}
		
	    
    } 

   </script>
</body>
</html>
