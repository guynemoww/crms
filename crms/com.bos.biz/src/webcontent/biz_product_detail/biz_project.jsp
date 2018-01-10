<%@page pageEncoding="UTF-8"%>
		<div>
			<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
				<a class="nui-button" id="view_add" iconCls="icon-add" onclick="addxm()">增加</a>
				<a class="nui-button" id="view_edit" iconCls="icon-edit" onclick="editxm(0)">编辑</a>
				<a class="nui-button" id="view_edit1" iconCls="icon-node" onclick="editxm(1)">查看</a>
				<a class="nui-button" id="view_remove" iconCls="icon-remove" onclick="removexm()">删除</a>
				<a class="nui-button" id="view_addProject" iconCls="icon-add" onclick="addProjectxm()">引入客户项目</a>
<!-- 				<a class="nui-button" id="view_addProfessional" iconCls="icon-add" onclick="addProfessionalxm()">专业贷款信息</a>
 -->			
 			</div>
				    
			<div id="gridxm" class="nui-datagrid" style="width:100%;height:auto" 
				url="com.bos.bizProductDetail.bizProject.getBizDetailProjectInfo.biz.ext" dataField="projectInfos"
				allowResize="false" showReloadButton="false"
				sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="5" sortMode="client">
				<div property="columns">
					<div type="checkcolumn" >选择</div>
					<div field="PROJECT_NAME" headerAlign="center" allowSort="true" >项目名称</div>
					<div field="PROJECT_TYPE" headerAlign="center" allowSort="true" dictTypeId="CDXY0049">项目类型</div>
					<div field="PROJECT_LEVEL_CD" headerAlign="center" allowSort="true" dictTypeId="XD_XMCD0001">项目级别</div>
					<div field="PROJECT_ADDRESS" headerAlign="center" allowSort="true" >项目地点</div>
					<div field="PROJECT_TOTAL_AMT" headerAlign="center" allowSort="true" >项目总投资</div>
					<div field="CAREER_MYAMT" headerAlign="center" allowSort="true" >自有资金</div>
					<div field="CAREER_MYAMT_PERCENT" headerAlign="center" allowSort="true" >自有资金比例(%)</div>
				</div>
			</div>
		</div>
		
		
<script type="text/javascript">	
	function addxm(){
		var amountDetailId=nui.get("amountDetail.amountDetailId").getValue();
	    var json =nui.encode({"amountDetailId":amountDetailId});
	    $.ajax({
	        url: "com.bos.bizProductDetail.bizProject.getBizInfoByDetailId.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (mydata) {
				addViewxm(mydata.bizInfo.partyId);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
	    });
	}
	//弹出客户添加项目页面
	function addViewxm(partyId){
	    nui.open({
	        url: nui.context + "/csm/corporation/csm_project_info_add.jsp?partyId="+partyId,
	        title: "新增", 
	        width: 800, 
	    	height: 500,
	    	allowResize:true,
	    	showMaxButton: true,
	        ondestroy: function (text) {
	       		var iframe = this.getIFrameEl();
	            var projectId = iframe.contentWindow.data;
	            //客户组返回添加的项目projectId
	            if(projectId!=null && projectId!=''){
		            		addRelatexm(projectId);
		        }
	        }
	    });
	}
	//建立项目与业务申请关系
	function addRelatexm(projectId){
		var BizDetailId=nui.get("amountDetail.amountDetailId").getValue();//发哥说是授信明细ID
	    var json =nui.encode({"BizDetailId":BizDetailId,"projectId":projectId});
	    $.ajax({
	        url: "com.bos.bizProductDetail.bizProject.saveProjectToProduct.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (mydata) {
	        	if(mydata.msg!=null && mydata.msg!=''){
	        		nui.alert(mydata.msg);
	        	}else{
	        		//alert("添加成功");
					var amountDetailId=nui.get("amountDetail.amountDetailId").getValue();
					var json = nui.decode({"amountDetailId":amountDetailId});
					var gridpj = nui.get("gridxm");
					gridpj.load(json);
	        	}
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
	    });
	
	}
	//编辑项目信息
	function editxm(v) {
		var gridpj = nui.get("gridxm");
	    var row = gridpj.getSelected();
	    if (row) {
	        nui.open({
	            url: nui.context+"/csm/corporation/csm_project_info_edit.jsp?itemId="+row.PROJECT_ID+"&view="+v,
	            title: "编辑", 
	            width: 800,
	    		height: 500,
	            allowResize:true,
	    		showMaxButton: true,
	            onload: function () {
	                var iframe = this.getIFrameEl();
	                var data = row;
	                //iframe.contentWindow.SetData(data);
	            },
	            ondestroy: function (action) {
	                if(action=="ok"){
	                    var amountDetailId=nui.get("amountDetail.amountDetailId").getValue();
						var json = nui.decode({"amountDetailId":amountDetailId});
						var gridpj = nui.get("gridxm");
						gridpj.load(json);
	           	 	}
	            }
	        });
	    }else{
	    	alert("请选择项目信息！");
	    }
	}
	//删除关联关系
	function removexm() {
		var gridpj = nui.get("gridxm");
	    var rows = gridpj.getSelected();
		if (null == rows) {
			nui.alert("请选择项目信息！");
			return false;
		}
		var json = nui.encode({"projects":rows});
		nui.confirm("确定删除吗？","确认",function(action){
	    	if(action!="ok") return;
	        $.ajax({
	            url: "com.bos.bizProductDetail.bizProject.delProjectToProduct.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if (text.msg) {
	            		nui.alert(text.msg);
	            		return;
	            	}
	                var amountDetailId=nui.get("amountDetail.amountDetailId").getValue();
					var json = nui.decode({"amountDetailId":amountDetailId});
					var gridpj = nui.get("gridxm");
					gridpj.load(json);
	            },
	            error: function () {
	            	nui.alert("操作失败！");
	            }
	        });
	    }); 
	}
	//引入客户项目
	function addProjectxm(){
		var amountDetailId=nui.get("amountDetail.amountDetailId").getValue();
	    var json =nui.encode({"amountDetailId":amountDetailId});
	    $.ajax({
	        url: "com.bos.bizProductDetail.bizProject.getBizInfoByDetailId.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (mydata) {
	    	    nui.open({
		            url: nui.context + "/csm/corporation/csm_project_info_list.jsp?partyId="+mydata.bizInfo.partyId+"&qote=3",
		            title: "新增", 
		            width: 800, 
		        	height: 500,
		        	allowResize:true,
		        	showMaxButton: true,
		            ondestroy: function (data) {
					    var iframe = this.getIFrameEl();
	                    var projectId = iframe.contentWindow.data;
		                //客户组返回添加的项目projectId
		                if(projectId!=null && projectId!=''){
		            		addRelatexm(projectId);
		                }
	                    var amountDetailId=nui.get("amountDetail.amountDetailId").getValue();
						var json = nui.decode({"amountDetailId":amountDetailId});
						var gridpj = nui.get("gridxm");
						gridpj.load(json);
			        }
		       	 });
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
	    });
	}
	//发起专业贷款流程
	function addProfessionalxm(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择项目信息！");
			return false;
		}
		var bizDetailId=nui.get("bizSgDetail.approveDetailId").getValue();
	    var json =nui.encode({"bizDetailId":bizDetailId});
	    $.ajax({
	        url: "com.bos.bizProductDetail.bizProject.getBizInfoByDetailId.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (mydata) {
	        	//如果有初始信用等级不能再发起
	        	var json =nui.encode({"partyId":mydata.bizInfo.partyId,"projectId":row.projectId});
	        	$.ajax({
	                url: "com.bos.bizProductDetail.bizProject.getProjectIrmInfo.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
	                success: function (text) {
	                	if (text.irmFlag==1) {
	                		//alert("存在初始信用等级，不能发起专业贷款判断！");
	                		var showtype ="2"
	                		//return;
	                	}
	                	addProfessionalViewxm(mydata.bizInfo.applyId,mydata.bizInfo.partyId,row.projectId);
	                    var amountDetailId=nui.get("amountDetail.amountDetailId").getValue();
						var json = nui.decode({"amountDetailId":amountDetailId});
						var gridpj = nui.get("gridxm");
						gridpj.load(json);
	                },
	                error: function () {
	                	nui.alert("操作失败！");
	                }
	       	 	});
				
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
	    });
	}
	//弹出专业贷款页面
	function addProfessionalViewxm(applyId,partyId,projectId){
	    nui.open({
	        url: nui.context+"/irm/singleCustom/specialtyRate/irm_professionJudge.jsp?applyBizId="+applyId+"&projectId="+projectId+"&partyId="+partyId+"&showtype="+showtype,
	        title: "专业贷款", 
	        width: 800, 
	    	height: 500,
	    	allowResize:true,
	    	showMaxButton: true,
	        ondestroy: function (action) {
	        	var amountDetailId=nui.get("amountDetail.amountDetailId").getValue();
				var json = nui.decode({"amountDetailId":amountDetailId});
				var gridpj = nui.get("gridxm");
				gridpj.load(json);
	        }
	    });
	}
</script>