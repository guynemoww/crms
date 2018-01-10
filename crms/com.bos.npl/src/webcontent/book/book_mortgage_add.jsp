<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-12-30 10:07:49
  - Description:
-->
<head>
<title>以物抵债修改抵债数据</title>
</head>
<body>
<center>
	<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="4" id="">	
			<label class="nui-form-label">客户代码</label>
			<div>	
			<input id="" class="nui-textbox nui-form-input" name="" style="width:63%"/>
			<a id="" name="" class="nui-button" style="width:15%" >查看</a>
			</div>
			
			<label class="nui-hidden" ></label>												
			<input id="" name=""   class="nui-hidden" required="false" />
				
			<label class="nui-form-label">客户名称</label>
			<input id=""  name="" class="nui-textbox nui-form-input"/>

			<label class="nui-form-label">担保人</label>
			<input id=""  name="" class="nui-textbox nui-form-input"/>
			
			<label class="nui-form-label">原贷款金额 (元)</label>
			<input id=""  name="" class="nui-textbox nui-form-input"/>

			<label class="nui-form-label">抵/质押物</label>
			<input id=""  name="" class="nui-textbox nui-form-input"/>
			
			<label class="nui-form-label">五级分类</label>
			<input id=""  name="" class="nui-textbox nui-form-input"/>

			<label class="nui-form-label">贷款期限</label>
			<input id=""  name="" class="nui-textbox nui-form-input"/>
			
			<label class="nui-form-label">(目前债券金额)本金</label>
			<input id=""  name="" class="nui-textbox nui-form-input"/>

			<label class="nui-form-label">(拟抵债金额)本金</label>
			<input id=""  name="" class="nui-textbox nui-form-input"/>
			
			<label class="nui-form-label">(目前债券金额)表内欠息</label>
			<input id=""  name="" class="nui-textbox nui-form-input"/>

			<label class="nui-form-label">(拟抵债金额)表内欠息</label>
			<input id=""  name="" class="nui-textbox nui-form-input"/>
			
			<label class="nui-form-label">(目前债券金额)表外欠息</label>
			<input id=""  name="" class="nui-textbox nui-form-input"/>

			<label class="nui-form-label">(拟抵债金额)表外欠息</label>
			<input id=""  name="" class="nui-textbox nui-form-input"/>
			
			<label class="nui-form-label">(目前债券金额)费用</label>
			<input id=""  name="" class="nui-textbox nui-form-input"/>

			<label class="nui-form-label">(拟抵债金额)费用</label>
			<input id=""  name="" class="nui-textbox nui-form-input"/>
			
			<label class="nui-form-label">(目前债券金额)合计</label>
			<input id=""  name="" class="nui-textbox nui-form-input"/>

			<label class="nui-form-label">(拟抵债金额)合计</label>
			<input id=""  name="" class="nui-textbox nui-form-input"/>
			
			<label class="nui-form-label">拟抵债资产</label>
			<input id=""  name="" class="nui-textbox nui-form-input"/>
			
			<label>权属变更方式</label>
			<input id="" name="" required="false" class="nui-dictcombobox nui-form-input" dictTypeId=""  />
			
			<label class="nui-form-label" >基本情况与抵债原因</label>
			<input  id="" name=""  vtype="maxLength:1500" class="nui-textarea nui-form-input" required="false" />
			
			
			<label class="nui-hidden" ></label>													// 为隐藏字段
			<input id="" name=""   class="nui-hidden" required="false" />
			
			<label class="nui-form-label">备注</label>
			<input id="" name="bizSingle.repaymentDesc"  vtype="maxLength:1500" class="nui-textarea nui-form-input" required="false" />
			
		</div>
		
			<div class="nui-toolbar" style="text-align:right;padding-right:50px;margin-top:3px;border:0">
				<a class="nui-button" iconCls="icon-save" onclick="">保存</a>
				<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
			</div>
	</div>
</center>
</body>
	<script type="text/javascript">
		nui.parse();
	</script>
</html>