<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): miaolf
  - Date: 2014-04-08 18:58:16
  - Description:
-->
<head>
<title>评级调整</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input id="item" class="nui-hidden nui-form-input" name="item"/>
	<fieldset>
  	<legend>
    	<span>基本信息</span>
    </legend>
	<div class="nui-dynpanel" columns="4">
		<label class="nui-form-label">客户编号：</label>
		<input name="party.partyNum" required="false" setEnabled="flase" class="nui-text nui-form-input" vtype="maxLength:100" />

		<label class="nui-form-label">客户名称：</label>
		<input id ='party.partyName' name="party.partyName" required="false" enabled='false' class="nui-text nui-form-input" vtype="maxLength:100" />
		
		<label class="nui-form-label">客户类型：</label>
		<input name="party.partyTypeCd" required="false" valueField="dictID" dictTypeId="XD_KHCD1001" class="nui-text nui-form-input"  vtype="maxLength:100" />	
		
  
		 
		<label class="nui-form-label">客户行业：</label>
		<input name="corporation.industrialTypeCd" required="false" valueField="dictID" dictTypeId="CDKH0095" class="nui-text nui-form-input" vtype="maxLength:10" />
		
		<label class="nui-form-label">银行认定企业规模：</label>
		<input name="corporation.bankScaleIdentify" required="false" valueField="dictID" dictTypeId="CDKH0025" class="nui-text nui-form-input" vtype="maxLength:30" />

		<label class="nui-form-label">所属机构：</label>
		<input name="orgNum" required="false" valueField="dictID" dictTypeId="org" class="nui-text nui-form-input" vtype="maxLength:20" />

 

		

		<label class="nui-form-label">评级模型：</label>
		<input name="irmApply.ratingModelCd" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
		
		<!--  
		<label class="nui-form-label">引用财报日期：</label>
		<input name="Financedate" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
		-->
		
		<!--<label class="nui-form-label">评级类型：</label>
		<input name= "irmApply.ratingTypeCd" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />-->
		
		<label class="nui-form-label">评级日期：</label>
		<input name="irmApply.applyDate" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
		
		<label class="nui-form-label">上次评级结果：</label>
		<input name="irmApply.lastCreditRatingCd" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
		
		<label class="nui-form-label">上次评级起始日：</label>
		<input name="irmApply.lastEffectiveStartDt" required="false" class="nui-text nui-form-input"   vtype="maxLength:100" />
	
		<label class="nui-form-label">上次评级到期日：</label>
		<input name="irmApply.lastEffectiveEndDt" required="false" class="nui-text nui-form-input"   vtype="maxLength:100" />
	</div>
	</fieldset>
	<div id="projectInfo" style="margin-top: 20px;">
		<fieldset>
		  	<legend>
		    	<span>项目基本信息</span>
		    </legend>
				<div  class="nui-toolbar" style="border-bottom:0;">
					<a id="query" class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
				</div>
			    
				<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
					url="com.bos.irm.queryInfo.queryCustProjectInfo.biz.ext" dataField="projectInfo"
					allowResize="false" showReloadButton="false" showPager="false"
					sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
					<div property="columns">
						<div type="checkcolumn"> 选择 </div>
						<div field="projectName" headerAlign="center" allowSort="true" >项目名称</div>
						<div field="projectType" headerAlign="center" allowSort="true" dictTypeId="CDXY0049">项目类型</div>
						<div field="projectLevelCd" headerAlign="center" allowSort="true" dictTypeId="XD_KHCD0186">项目级别</div>
						<div field="state" headerAlign="center" visible ='false' allowSort="true" >备注</div>
						<div field="createTime" headerAlign="center" allowSort="true" >登记时间</div>
					</div>
				</div>
		</fieldset>
	</div>	
		
</div>						
<script type="text/javascript">
    nui.parse();
	var form = new nui.Form("#form1");
	var applyId = "<%=request.getParameter("applyId") %>";//评级申请id;
	var partyId = "<%=request.getParameter("partyId") %>";//参与人id
	var flowType="<%=request.getParameter("flowType") %>";//评级类型
	var reAud="<%=request.getParameter("reAud") %>"	
	var grid = nui.get("grid1");	
	var pjlx ="<%=request.getParameter("pjlx") %>";  
	var projectId;
	var isFinance;
	
    init();
    function init(){
	    var json = nui.encode({"partyId":partyId,"applyId":applyId,"pjlx":pjlx});   	
   	    nui.ajax({//获取客户基本信息
	        url: "com.bos.irm.queryInfo.queryCustInfoJj2.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
		    async:false,        
	        contentType:'text/json',
	        success: function (text) {
	        	if(text.msg){
	        		alert(text.msg);
	        	} else {
	                var o = nui.decode(text);
	                form.setData(o);
	                //	nui.get("dateOfEstablishment").setValue = o.dateOfEstablishment;
	                isFinance=o.isFinance;
	            }
	        }
	    });
	    nui.ajax({//获取评级申请信息
	        url: "com.bos.irm.queryInfo.queryRatingApplyInfo.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
		    async:false,        
	        contentType:'text/json',
	        success: function (text) {
	        	if(text.msg){
	        		alert(text.msg);
	        	} else {
	                var o = nui.decode(text);
	                projectId = o.ratingApplyInfo.projectId;
	                if(projectId){
	                	grid.load({"projectId":projectId,"partyId":partyId});
	                }else{
	                	$("#projectInfo").hide();
	                }
	            }
	        }
	    });
    }
 
    function edit(v) {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: nui.context + "/csm/corporation/csm_project_info_edit.jsp?itemId="+row.projectId+"&view="+1,
                title: "查看项目信息", 
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
                        git.mask();
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