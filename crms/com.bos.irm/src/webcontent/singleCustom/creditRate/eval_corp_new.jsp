<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): miaolf
  - Date: 2014-04-08 17:26:18
  - Description:
-->
<head>
<title>客户内部评级信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<fieldset>
  	<legend>
    	<span>基本信息</span>
    </legend>
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">客户编号：</label>
			<input name="item.partyNum" required="false" setEnabled="flase" class="nui-text nui-form-input" vtype="maxLength:100" />
	
			<label class="nui-form-label">客户名称：</label>
			<input name="item.partyName" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
	
			<label class="nui-form-label">所在国家（地区）：</label>
			<input name="item.contryRegionCd" required="false" dictTypeId="CD000003" class="nui-text nui-form-input" vtype="maxLength:20" />
	
			<label class="nui-form-label">公司成立日期：</label>
			<input id="createTime" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
		</div>
	</div>
	</fieldset>
		<div id="project" style="padding-top:10px;" >
			<div class="nui-dynpanel" columns="8">
				<label class="nui-form-label">是否进行专业贷款评级：</label>
				<!--  input id="YN" name="yn"  property="editor" class="nui-checkbox nui-form-input" onclick="showOn(this)"/>-->
				<input id="YN" name="yn"  class="nui-radiobuttonlist"  textField="text" valueField="id" value="" url="../specialtyRate/shiti.txt" dataField="shiti"/>
			</div>
		</div>
		<!--<label>注：若要发起评级，请先在财务报表模块录入客户年度财务报表；若确实无年报，请继续评级发起流程。</label>-->
		</br>
		<a class="nui-button" iconCls="icon-add" onclick="addAndMove">创建</a>
		<a class="nui-button" iconCls="icon-edit" onclick="viewHistory">评级历史</a>
		<div id="grid" class="nui-datagrid" style="width:100%;height:auto;margin-top:10px;" 
			allowAlternating="true" enabled="false"
			multiSelect="false"
			dataField="proRatInfo"
			url="com.bos.irm.getProfessionalRateInfo.queryProRatInfo.biz.ext" 
			sizeList="[10,20,50,100]" pagesize="10">
			<div property="columns">
				<div type="checkcolumn"> 选择 </div>
				<div type="indexcolumn"></div>
				<div field="projectId" headerAlign="center" visible="false" allowSort="true"> 项目id </div>
				<div field="judgeRecordId" headerAlign="center" visible="false" allowSort="true"> 判断记录ID </div>
				<div field="projectId" headerAlign="center" visible="false" allowSort="true" > 项目编号 </div>
				<div field="projectName" headerAlign="center" allowSort="true"> 项目名称 </div>
				<div field="professionalTypeCd" headerAlign="center" allowSort="true" dictTypeId="XD_PJCD0003" > 专业贷款标识 </div>
				<div field="judgeState" headerAlign="center" allowSort="true" dictTypeId="XD_PJCD0004"> 标识判断状态 </div>
			</div>
		</div>
<script type="text/javascript">
	nui.parse();
//	git.mask();

	var partyId="<%=request.getParameter("partyId") %>";//参与人id
	var form = new nui.Form("#form1");
	var grid = nui.get("#grid");
	var flg;//项目有无标识（1：有，2：无）
	var flg2 = 1;//专业贷款标识（1：信用评级，2：专业贷款）
	init();//页面初始化
	function init(){
		var json = nui.encode({"partyId":partyId});
      	nui.ajax({
	        url: "com.bos.irm.queryInfo.queryCsmInfo.biz.ext",
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
	                flg = o.flg;
	                var time = o.createTime.substring(0,10);
	                nui.get("createTime").setValue(time);
	            }
	        }
	    });
	    if (flg == 2){
	  //  $("#form1").hide();
	    	 $("#project").hide();
	    	 $("#grid").hide();
	  	}else{
	  		grid.load({partyId:partyId});
	  	}
	//	git.unmask();
    }
    var isReal = nui.get("YN");
	isReal.on("valuechanged", function (e) {
	    showOn(this.getValue());
	});
    
    
    function showOn(e){
    	var isChoise = nui.get("YN").getValue();
    	if(isChoise == 1){
    		nui.get("grid").setEnabled(true);
    		flg2 = 2;
    	}else{
    		nui.get("grid").setEnabled(false);
    		flg2 = 1;
    	}
    	/*
    	if(e.value=="true"){
    		nui.get("grid").setEnabled(true);
    		flg2 = 2;
    	}else{
    		nui.get("grid").setEnabled(false);
    		flg2 = 1;
    	}*/
    }
    
    function viewHistory(){
    	nui.open({
            url: "irm/singleCustom/specialtyRate/irm_ratingResHisRec.jsp?partyId="+partyId,
            title: "评级历史"
        });
    }
    var projectId;//项目id
    var judgeRecordId;//判断id
    var judgeState;//标识判断状态
    function addAndMove(){
    	nui.confirm("请确认是否已录入最新年度财务报表？若确认则继续评级，否则请到客户模块录入财务报表。","确认",function(action){
	        if(action!="ok"){
	            	return;
	        }else{
		    	if (flg2 == 1){
		    		projectId = null;
		    	}else{
		    		var row = grid.getSelected();
		    		if (row){
		    			projectId = grid.getSelected().projectId;
		    			judgeRecordId = grid.getSelected().judgeRecordId;
		    			judgeState = grid.getSelected().judgeState;
		    			var isProject = row.professionalTypeCd;
		    			if(judgeState != 3){
		    				alert("请选择一个“标识判断状态”为生效的项目");
		    				return;
		    			}
		    			if(isProject == '09'){
		    				alert("不能对专业贷款标识为'非专业贷款'的项目创建流程");
		    				return;
		    			}
		    		}else{
		    			alert("请选择一个项目");
		    			return;
		    		}
		    		
		    	}
			    var json = nui.encode({"partyId":partyId,"projectId":projectId,"flg":flg2,"judgeRecordId":judgeRecordId});
		    	var applyId;
		    	nui.ajax({//插入客户评级申请表
		    		url:"com.bos.irm.insertInfomercial.addRatingApply.biz.ext",
		    		type: 'POST',
		      		data: json,
		      		cache: false,
		      	    contentType:'text/json',
		      	    async:false,
		      	    success: function (txt) {     	
		      	    	if(txt.msg){//已存在参与人评级申请
		      	    		alert(txt.msg);
		      	    	}else{//创建成功则跳转页面
		      	    		var p = nui.decode(txt);
		     	    		var applyId = p.applyId;
		     	    		var node = p.node;
		     	    		var flowType = p.flowType;
		     	    		window.parent.create(applyId,node,flowType); 	    		
		      	    	}
		      	    },
		            error: function (jqXHR, textStatus, errorThrown) {
		                alert(jqXHR.responseText);
		            }
		    	});
	    	}
    	});
    }	
</script>
</body>
</html>
