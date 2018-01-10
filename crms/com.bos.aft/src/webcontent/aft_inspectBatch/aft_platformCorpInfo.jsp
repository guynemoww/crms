<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): fengjiahui
  - Date: 2014-02-28 16:34:13
  - Description:批量平台客户检查的发起
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>平台客户情况</title>
</head>
<body>
<fieldset>
<legend>
      <span>基本情况</span>
</legend>
<div id="editForm" class="nui-dynpanel" columns="4"  >
	<label>平台名称：</label>
	<input name="partyName" id="partyName" class="nui-text nui-form-input" /> 
	<label>授信额度：</label>
	<input name="creditTotalAmount" id="creditTotalAmount" class="nui-text nui-form-input"/> 
	<!--
    <label>平台管理方：</label>
	<input name="mgrOrg" id="managementWayCd" class="nui-text nui-form-input" dictTypeId="org"/> 
	 -->
	<label>合作期限：</label>
	<input name="corpDeadLine" id="corpDeadLine" class="nui-text nui-form-input"/> 
	<label>所属分行：</label>
	<input class="nui-text nui-form-input" name="branch" id="branch" dictTypeId="org"/>
	<label>主办机构：</label>
	<input class="nui-text nui-form-input"  name="mgrOrg" id="handlingOrgId" dictTypeId="org"/>
	<label>平台类型：</label>
	<input class="nui-text nui-form-input"  name="platformCsmType" id="platformCsmType" dictTypeId="XD_KHCD0232"/>
	<label>平台管理缓释责任：</label>                                    
	<input class="nui-textbox nui-form-input" name="pfSlowrelayDuty" id="pfSlowrelayDuty" />
	<input id="partyId" class="nui-hidden nui-form-input" name="partyId" />
	<input id="partyNum" class="nui-hidden nui-form-input" name="partyNum" />
</div>
</fieldset>

<fieldset style="margin-top: 10px;margin-bottom: 10px;">
 	<legend>
    	     <span>平台客户授信情况</span>
    	     <a id="addMember" class="nui-button" iconCls="icon-add" style="text-align: right;float: right;" onclick="addCheckCorp" >添加待检成员客户</a>
    	     <a id="delMember" class="nui-button" iconCls="icon-remove" style="text-align: right;float: right;" onclick="delCheckCorp" >移除待检成员客户</a>
    </legend>
	<div id="datagrid1" class="nui-datagrid" idField="id" showPager="false" url="com.bos.aft.aft_inspectBatch.queryPfCorpInfo.biz.ext"
	     dataField="pfMember" allowCellEdit="true" allowCellSelect="true" multiSelect="true" editNextOnEnterKey="true" >
        <div property="columns"> 
			<div type="checkcolumn"></div>
			<div field="partyName">单户名称</div>
			<div field="CREDIT_EXPOSURE" width="150px">授信金额</div>
			<div field="AVAILABLE_EXPOSURE" width="150px">可用余额</div>
			<div field="OCCUPIED_EXPOSURE" width="150px">已用金额</div>
		</div>
	</div>
	<div id="limitInfo" class="" style="margin-top:20px;heigth:50px;">
		<div class="nui-dynpanel" columns="6">
			<label>平台授信余额：</label>
			<input name="balanceAmt" id="usableExposureAmt"  class="nui-text nui-form-input" colspan="2"/>
			
			<label>平台授信敞口：</label>            
			<input name="creditAmt" id="creditTotalExposure" class="nui-text nui-form-input" colspan="2"/>
		</div>
	</div>
</fieldset> 
		 <a id="saveBtn" class="nui-button" iconCls="icon-save" style="text-align: right;float: right;" onclick="btnSave()" >保存</a>


	<script type="text/javascript">
		nui.parse();
		
		if("<%=request.getParameter("callback") %>"=="y"){
			nui.get("saveBtn").hide();
			nui.get("addMember").hide();
            nui.get("delMember").hide();
		}
		git.mask(); //mask中可加一个参数表示要进行遮罩的页面元素，不加时默认为整个页面。
	
		var form = new nui.Form("#editForm");
		var limitInfo = new nui.Form("#limitInfo");
		var grid = nui.get("datagrid1");
		var param=<%=request.getParameter("param") %>;
		//alert(param);
		//var json = nui.encode({"item.partyId":"<%=request.getParameter("corpid") %>"});
		//var json = nui.encode({"item/partyId":"94967ca944e254a50144e3e5f3310014"});
		var json=nui.encode({"param":param});
		var startD="",endD="";
		
		function delCheckCorp(){
		   var rows= grid.getSelecteds();
           if(rows.length==0){
              alert("请选择要移除的成员！");
              git.unmask();
              return;
           }
           var pfMembers = new Array();
			for(var i = 0;i < rows.length;i++){
				pfMembers[i] = {pfDetailId:rows[i].pfDetailId};
			}
           var json =nui.encode({"members":pfMembers});/* 检查单户信息 */
			
		    nui.ajax({
                url: "com.bos.aft.dailyInspect.delMembers.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                	nui.alert(text.msg);
                	query();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });    
		   
		}
		
		function query(){/* 加载信息 */
		   nui.ajax({
                url: "com.bos.aft.aft_inspectBatch.queryPfCorpInfo.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                    
                	//alert(nui.encode(text.parentOrg));
                	form.setData(text.pfcorpInfo);
                	nui.get("branch").setValue(text.orgCode);
                	limitInfo.setData(text.pfcorpInfo);
                	
                	if(null!=text.pfcorpInfo.balanceAmt){
                		nui.get("usableExposureAmt").setValue(text.pfcorpInfo.balanceAmt);
                		nui.get("creditTotalExposure").setValue(text.pfcorpInfo.balanceAmt);
                	}
                	nui.get("creditTotalAmount").setValue(text.pfcorpInfo.creditAmt);
                	if(null!=text.pfcorpInfo.creditAmt){
                		nui.get("creditTotalAmount").setValue(text.pfcorpInfo.creditAmt);
                	}                                           
                	
                	if(null!=text.pfcorpInfo.pfSlowrelayDuty){
                	    nui.get("pfSlowrelayDuty").setValue(text.pfcorpInfo.pfSlowrelayDuty);
                	}
                	if(null!=text.pfcorpInfo.startDate){
                		startD=text.pfcorpInfo.startDate;
                	}
                	if(null!=text.pfcorpInfo.endDate){
                		endD=text.pfcorpInfo.endDate;
                	}
                	//nui.get("corpDeadLine").setValue(startD+"~"+endD);
                	if(null!=text.parentOrg&&null!=text.parentOrg.parentOrgId){
                		if("10001"==text.parentOrg.parentOrgId){
                			nui.get("branch").setValue(text.parentOrg.orgId);
                		}else{
                			nui.get("branch").setValue(text.parentOrg.parentOrgId);
                		}
                	}
                	grid.load({"param":param});
                	grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
       			e.data[i]['partyName']='<a href="#" onclick="clickCust(\''
       				+ e.data[i].partyId+'\');return false;" value="'
       				+ e.data[i].partyId
       				+ '">'+e.data[i]['partyName']+'</a>';
       		}
       });

					git.unmask();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });			
		}
		query();
	    
	    function clickCust(e){
	    var liId;
	    var corpType;
		var  dataJson=nui.encode({"partyId":e});
		 nui.ajax({
                url: "com.bos.aft.checkReport.getLastCheckId.biz.ext",
                data:dataJson,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                  liId=text.liId;
                  corpType=text.corpType;
                
            if(corpType==1){
              var infourl = nui.context + "/aft/dailyInspect/newSmlCheckReport.jsp?bizId="+liId+"&partyId="+e+"&onlyRead=1";
             nui.open({
	            url:infourl,
	            showMaxButton: true,
	            title: "",
	            width: 1024,
	            height: 768,
	            state:"max",
	            onload: function(e){
	            	var iframe = this.getIFrameEl();
	            	var text = iframe.contentWindow.document.body.innerText;
	            	//alert(text);
	            },
	            ondestroy: function (action) {
	                query();
	            }
      	        });
            }else if(corpType==0){
              var infourl = nui.context + "/aft/dailyInspect/newCheckReport.jsp?bizId="+liId+"&partyId="+e+"&onlyRead=1";
             nui.open({
	            url:infourl,
	            showMaxButton: true,
	            title: "",
	            width: 1024,
	            height: 768,
	            state:"max",
	            onload: function(e){
	            	var iframe = this.getIFrameEl();
	            	var text = iframe.contentWindow.document.body.innerText;
	            	//alert(text);
	            },
	            ondestroy: function (action) {
	                query();
	            }
      	        });
            }else if(!liId){
                alert("暂无检查报告！");
            }else{
               alert("目前只有大中/小微客户贷后检查报告！");
            }
		},
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });	
            
	}
	    
	    /*新增待检查成员客户*/
	    function addCheckCorp(){
	     var partyId=param.partyId;
	     nui.open({
                url:nui.context+"/aft/aft_inspectBatch/addCheckCorp.jsp?param="+nui.encode(param),                          
                title: "多选列表",
                width: 650,
                height: 380,
                ondestroy: function () {
                    query();
                }
            });      
	    
	    }
	    
	    
		/* 保存批量平台信息，保存检查单户信息 */
		function btnSave(){
			nui.get("saveBtn").setEnabled(false);
			//{"platformMembers":[{"platformMemberId":"1","partyNum":"1402020024","platformPartyId":"94967ca94494fb800144956ad2270001","partyName":"XXX有限公司","_id":1,"_uid":1}]}
			var pfCorpInfo ={"pfSlowrelayDuty":nui.get("pfSlowrelayDuty").value,"partyId":nui.get("partyId").value,"partyNum":nui.get("partyNum").value,"pfCreditMoney":nui.get("usableExposureAmt").value,"pfCreditExposure":nui.get("creditTotalExposure").value,"pfId":param.pfId};
			//var json=nui.encode({"pfCorpInfo":pfCorpInfo});
			var json =nui.encode({"pfCorpInfo":pfCorpInfo,"param":param});/* 检查单户信息 */
			//alert(json);
			/*var messageid = nui.loading("数据保存中请稍后...", "保存");
			alert(json);
       		setTimeout(function () {
           		nui.hideMessageBox(messageid);
       		}, 2500);*/
		    nui.ajax({
                url: "com.bos.aft.aft_inspectBatch.updatePfCorpInfo.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                	nui.alert(text.msg);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });       		
       		nui.get("saveBtn").setEnabled(true);
		}

		
		/*
		   nui.ajax({
                url: "",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });
		
		*/


	</script>
</body>
</html>