<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-13 16:27:30
  - Description:
-->
<head>
<title>日常检查审核</title>
</head>
<body>
 <div class="nui-splitter" style="width:100%;height:100%;" allowResize="false">
    <div size="80%" showCollapseButton="false" style="padding:10px;border: 0px">
        <div id="form1"  style="width:80%;display: inline;">
			<input id="inspect._entity" class="nui-hidden nui-form-input" name="inspect._entity" value="com.bos.dataset.aft.TbAftDailyInspect"/>
			<div>客户基本信息</div>
			<div class="nui-dynpanel" columns="4"  id="corp">
						<label class="nui-form-label" >客户编号：</label>
						<input id="corp.partyId" class="nui-text nui-form-input" name="corp.partyId" required="true"/>
						
						<label class="nui-form-label">组织机构代码：</label>
						<input id="corp.orgnNum" class="nui-text nui-form-input" name="corp.orgnNum" required="true" />
						
						<label class="nui-form-label">客户名称：</label>
						<input id="corp.partyName" class="nui-text nui-form-input" name="corp.partyName" required="true"/>
						
						<label class="nui-form-label">营业执照号码：</label>
						<input id="corp.businessLicenseNum" class="nui-text nui-form-input" name="corp.businessLicenseNum" required="true" />
						
						<label class="nui-form-label">客户类型：</label>
						<input id="corp.customerTypeCd" name="corp.customerTypeCd" data="data" valueField="dictID" 	class="nui-text nui-form-input" dictTypeId="XD_KHCD0098"/>
						
						<label class="nui-form-label">信用等级：</label>
						<input id="" class="nui-text nui-form-input" name="" required="true" />
						
						<label class="nui-form-label">授信金额：</label>
						<input id="" class="nui-text nui-form-input" name="" required="true" />
						
						<label class="nui-form-label">授信余额：</label>
						<input id="" class="nui-text nui-form-input" name="" required="true" />
			</div>			
				
			<div>资金流向信息</div>
			<div class="nui-dynpanel" columns="4" >	
						<label class="nui-form-label">资金流向监控：</label>
						<div><input id="01" name="" required="true" class="nui-checkbox nui-form-input" text="已完成" />
						<input id="02" name="" required="true" class="nui-checkbox nui-form-input" text="尚未完成" /></div>
						
						<label class="nui-form-label">情况说明：</label>
						<input class="nui-text nui-form-input" name="inspect.cashFlowInspectState" required="false" value="反显"/>
					
					
						<label class="nui-form-label">实际流向与计划流向：</label>
						<div><input id="01" name="" required="true" class="nui-checkbox nui-form-input" text="一致" />
						<input id="02" name="" required="true" class="nui-checkbox nui-form-input" text="不一致" /></div>
										
						<label class="nui-form-label">情况说明：</label>
						<input class="nui-text nui-form-input" name="inspect.realFlowPlanFlowState" required="false"  value="反显"/>
										
					
						<label class="nui-form-label">相关交易资料收集情况：</label>
						<div><input id="01" name="" required="true" class="nui-checkbox nui-form-input" text="已收集完成" />
						<input id="02" name="" required="true" class="nui-checkbox nui-form-input" text="尚未收集完成" /></div>
						
						<label class="nui-form-label">情况说明：</label>
						<input class="nui-text nui-form-input" name="inspect.dataCollectionState" required="false"  value="反显"/>
			</div>			
				
			<div>检查信息</div>
			<div class="nui-dynpanel" columns="4" >		
						<label class="nui-form-label">检查方式：</label>
						<div><input id="" name="inspect.inspectWayCd" required="true" class="nui-text nui-form-input" value="约见检查" /></div>
						
						<label class="nui-form-label">检查地点：</label>
						<input id="inspect.inspectAddress"  class="nui-text nui-form-input" name="inspect.inspectAddress" value="现场"/>
					
						<label class="nui-form-label">检查日期：</label>
						<input id="inspect.inspectDate" class="nui-text nui-form-input" name="inspect.inspectDate" value="20140204"/>
						
						<label for="isteam$text">检查次数：</label>
						<input id="inspect.inspectCount" class="nui-text nui-form-input" name="inspect.inspectCount" required="true"  value="1"/>
			</div>	
			<div class="nui-dynpanel" columns="4" style="margin-top: 20px;">		
						<label class="nui-form-label">检查意见：</label>
						<input id="" name="" required="true" colspan="3"  class="nui-text nui-form-input" value="系统反显" />
						
						<label class="nui-form-label">审核结论：</label>
						<div name="" required="true" colspan="3"  class="nui-dictradiogroup nui-form-input" value='1' dictTypeId="CsmYesOrNo" onvaluechanged="" id="b"></div>
					
						<label for="isteam$text">审核意见：</label>
						<input id="inspect.inspectCount" colspan="3" width="100%"  class="nui-textarea nui-form-input" name="inspect.inspectCount" required="true"  value="1"/>
						
						<label for="isteam$text">贷后检查报告质量评分：</label>
						<input name="" required="true" colspan="3"  class="nui-dictradiogroup nui-form-input" value='1' dictTypeId="CsmYesOrNo" onvaluechanged=""/>
			</div>	
			<div class="nui-toolbar" style="text-align:center;padding-top:4px;padding-bottom:5px;" 
		    borderStyle="border:0;">
				 <a class="nui-button" iconCls="icon-ok" onclick="btnSubmit()">提交</a>
				 <a class="nui-button" iconCls="icon-save" onclick="btnSave()">临时保存</a>
				 <a class="nui-button" iconCls="icon-remove" onclick="btnRevoke()">退回修改</a>
			</div>
		</div>
		
    </div>
  
    <div showCollapseButton="false" style="border: 0px">
        <fieldset id="fd1" style="font-size: 12px;font-weight: bold;" >
		 	<legend><span>相关链接</span></legend>
		 	<div><a href="#">客户信息</a></div>
		 	<div><a href="#">相关文档</a></div>
		 	<div><a href="#">过程意见</a></div>
 		</fieldset>
    </div>        
</div>
</body>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#corp");
	var form1 = new nui.Form("#form1");
	
		function btnSubmit(){/* 提交 */
		
		}
		function btnSave(){/* 临时保存 */
		
		}
		function btnRevoke(){/* 撤销 */
		
		}
		/*
				$.ajax({
			            url: "com.bos.aft.aft_manage.updateRate.biz.ext",
			            type: 'POST',
			            data: json,
			            cache: false,
			            contentType:'text/json',
			            success: function (text) {
			            	if(text.msg){
			            		nui.alert(text.msg);
			            		grid.reload();
			            	} else {
			            		nui.alert(text.msg);
			            	}
			            },
			            error: function (jqXHR, textStatus, errorThrown) {
			                nui.alert(jqXHR.responseText);
			            }
					});
		*/
	
</script>
</html>