<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 16:18:58
  - Description:
-->
<head>
<title>客户内部评级信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<div style="margin-top:30px;">评级信息</div>   
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4" >
		<label class="nui-form-label">客户编号：</label>
		<input id="item.partyNum" class="nui-text nui-form-input" name="item.partyNum" style="text-align:left;"/>
				
		<label class="nui-form-label">评级类型：</label>
		<input id="rateTypeText" class="nui-text nui-form-input" name="rateTypeText" style="text-align:left;"/>
		
	</div>
</div>

<div id="form2" style="width:100%;height:auto;overflow:hidden;">
	<div id="grid" class="nui-datagrid" style="width:100%;height:auto" 
		url="com.bos.irm.getProfessionalRateInfo.queryProRatInfo.biz.ext" 
		dataField="proRatInfo"
		allowAlternating="true"
		multiSelect="false"
		sizeList="[10,20,50,100]" pagesize="10">
	     <div property="columns">
	     <div type="checkcolumn"> 选择 </div>
	     <div field="partyNum" headerAlign="center"  allowSort="true"> 客户编号 </div>
	     <div field="partyId" headerAlign="center" visible="false" allowSort="true"> 客户id </div>
	     <div field="projectId" headerAlign="center" visible="false" allowSort="true"> 项目id </div>
	     <div field="judgeRecordId" headerAlign="center" visible="false" allowSort="true"> 判断记录ID </div>
	     <div field="partyName" headerAlign="center"  allowSort="true"> 客户名称 </div>
	     <div field="projectId" headerAlign="center" allowSort="true" > 项目编号 </div>
	     <div field="projectName" headerAlign="center" allowSort="true"> 项目名称 </div>
	     <div field="professionalTypeCd" headerAlign="center" allowSort="true" dictTypeId="XD_PJCD0003" > 专业贷款标识 </div>
	     <div field="judgeState" headerAlign="center" allowSort="true" dictTypeId="XD_PJCD0004"> 标识判断状态 </div>
	</div> 
</div>


<div>
	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
    	borderStyle="border:0;">
    	<a class="nui-button" id="btnJudge" iconCls="icon-add" onclick="judgeTo">创建</a>
	</div>

</div>
<script type="text/javascript">

		nui.parse();
	    var form = new nui.Form("#form1");
	    var form2 = new nui.Form("#form2");
	    var grid = nui.get("grid");
		var corpid = "<%=request.getParameter("corpid")%>";			//客户ID
		var rateType ="<%=request.getParameter("rateType") %>";		//评级类型
		nui.get("rateTypeText").setValue(nui.getDictText("XCH00001",rateType)); //"<%=request.getParameter("rateType") %>"
	
			var json = nui.encode({"partyId":corpid});
		    $.ajax({
		        url: "com.bos.irm.getProfessionalRateInfo.getProRaCusInfo.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        cache: false,
		        success: function (text) {
            		var text = nui.decode(text);
               		form.setData(text);
               		nui.get("rateTypeText").setValue(nui.getDictText("XCH00001",rateType)); 
               		grid.load({partyId:corpid}); 
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	                git.unmask();
	            }
		    });

       function createTo(){
       		var row = grid.getSelected();
       		if(row){
	    	//git.mask();
			var json=nui.encode({"partyId":row.partyId,"projectId":row.projectId});
			 nui.ajax({
                url: "com.bos.irm.insertInfomercial.addRatingAppiy.biz.ext", //创建评级流程
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                     var node=text.node;//弹出审批页面
                	 openSubmitView(node);
                	grid1.reload();
					//git.unmask();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    grid1.reload();
                   // git.unmask();
                }
            });	
		    }else{
		    	alert("请选择一条记录");
		    }
       }
       
       function judgeTo(){
       		var row = grid.getSelected();
       		if(null == row){
       			alert("请选择记录");
       			return;
       		}
       		if(row.judgeState == '4'){
       			return alert('不进行专业贷款评级，请采用客户信用评级。');
       		}
			var json=nui.encode({"partyId":row.partyId,"projectId":row.projectId,"judgeRecordId":row.judgeRecordId});
			 nui.ajax({
	            url: "com.bos.irm.getProfessionalRateInfo.createFlowProfessionRate.biz.ext", //创建专业贷款评级流程
	            data:json,
	            type:"POST",
	            contentType:'text/json',
	            success: function (text) {
					git.unmask();
					var p = nui.decode(text);
					var node = p.node;
					if(text.flag){
						var url = nui.context + "/irm/singleCustom/specialtyRate/irm_profession_tree1.jsp?judgeRecordId=" + row.judgeRecordId + "&partyId=" + row.partyId + "&projectId=" + row.projectId+ "&node=" + nui.encode(node)+ "&applyId=" + text.applyId;
		        		git.go(url); 
					}else{
						alert(text.msg);
					}
	             	//var node=text.node;//弹出审批页面
	                //openSubmitView(node);
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                alert(jqXHR.responseText);
	                git.unmask();
	            }
	        });
            /*
            nui.open({
				url: nui.context + "/irm/singleCustom/specialtyRate/irm_professionJudge.jsp?judgeRecordId=" + row.judgeRecordId + "&partyId=" + row.partyId + "&projectId=" + row.projectId,
	            title: "专业贷款判断选项", 
	            width: 950, 
	        	height: 550,
	        	allowResize:true,
	        	showMaxButton: true,
	            ondestroy: function (action) {
	            },
	            ondestroy: function (action) {
              	    grid.reload();
            	}
            });
            */
       }
       function search(){
       		grid.load({"partyId":corpid});
       }
       /*
       search();
       */
</script>
</body>
</html>
