<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): ZhuYongLun
  - Date: 2014-03-28
  - Description:TB_GRT_PROJECTUNDER, com.bos.dataset.grt.TbGrtProjectunder
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>在建工程新增、编辑、查看</title>
</head>
<body>
	<div id="form1" >
		<input name="item.suretyKeyId" id="suretyKeyId" class="nui-hidden"   />
		<div class="nui-dynpanel" columns="4">
			<label>有关批文号：</label><!--BUG #2852  -->
			<input name="item.approvalNo" id="item.approvalNo" required="true" class="nui-textbox nui-form-input" vtype="maxLength:200" />
			
			<label>土地规划许可证号：</label>
			<input name="item.landPlanPerNo" required="true" class="nui-textbox nui-form-input" vtype="maxLength:150" />
			
			<label>建设工程规划许可证号：</label>
			<input name="item.buildPlanPerNo" required="true" class="nui-textbox nui-form-input" vtype="maxLength:150" />
			
			<label>建设工程施工许可证号：</label>
			<input name="item.constPlanPerNo" required="true" class="nui-textbox nui-form-input" vtype="maxLength:150" />
			
			<label>币种：</label>
			<input name="item.currencyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001" />
			
			<label>工程预算造价 ：</label>
			<input name="item.buildBudgetCost" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:16;"  dataType="currency" />
			
			<label>工程起始时间：</label>
			<input name="item.buildBeginDate" required="true" class="nui-datepicker nui-form-input" id="item.buildBeginDate" format="yyyy-MM-dd" allowinput="false" />
			
			<label>预计完工时间：</label>
			<input name="item.exceptEndDate" required="true" class="nui-datepicker nui-form-input" id="item.exceptEndDate" format="yyyy-MM-dd" allowinput="false" />
			
			<label>预计建筑面积（平方米）：</label>
			<input name="item.exceptLandAcreage" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" />
			<label>国家：</label>
			<input name="item.country" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000003" 
				onvaluechanged="nationChange" requiredErrorText="国家地区必选" emptyText="--请选择--"/>
			
			<label>省/直辖区：</label>
			<input name="item.province" required="true" class="nui-combobox nui-form-input" valueField="dictid" 
				id="province" textField="dictname" onvaluechanged="provinceChange" />
				
			<label>城市：</label>
			<input name="item.city" required="true" class="nui-combobox nui-form-input" valueField="dictid"  
				id="city" textField="dictname" onvaluechanged="cityChange" />
	
			<label>区（县）：</label>
			<input name="item.town" id="town" required="true" class="nui-combobox nui-form-input" valueField="dictid" 
				textField="dictname"/>
			
			<label>在建工程座落位置：</label>
			<textarea name="item.buildLocation" required="true"  class="nui-textarea" vtype="maxLength:200" emptyText="请输入地址信息..." 
				style="width:300px;height:60px;" ></textarea>
		</div>
		
		<div class="nui-dynpanel" columns="4" id="table1">
			
			<label>国有土地使用证号：</label>
			<input name="item.landUseNo" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />
			
			<label>土地面积（平方米）：</label>
			<input name="item.landAcreage" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" />
			
			<label>土地性质：</label>
			<input name="item.landQuale" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="XD_YWDB0103" />
			
			<label>土地取得方式：</label>
			<input name="item.landGainWay" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YWDB0104" />
			
			<label>土地使用截止日期：</label>
			<input name="item.useEndDate" required="true" class="nui-datepicker nui-form-input" id="item.buildBeginDate" format="yyyy-MM-dd" allowinput="false" />
			
			<label>施工单位名称：</label>
			<input name="item.buildUnit" required="true" class="nui-textbox nui-form-input" vtype="maxLength:150" />
			
			<label>备注说明：</label>
			<textarea name="item.remark" class="nui-textarea" vtype="maxLength:200" emptyText="请输入备注信息..." 
				style="width:300px;height:60px;" ></textarea>
		</div>
	</div>
					
		    
	<script type="text/javascript">
		var form1 = new nui.Form("#form1");
		
	
		
		/*********************************国家省市县区*******************************/	
		function nationChange(e){
			git.getDistrictsByParentid(e.sender.getValue(),function(data){
				nui.get('province').setData(data.items);
			});
			nui.get('province').setData(null);
			nui.get('city').setData(null);//清空“城市”下拉框值
			nui.get('town').setData(null);//清空“区/县”下拉框值
			nui.get('province').setValue();
			nui.get('city').setValue();//清空“城市”下拉框值
			nui.get('town').setValue();//清空“区/县”下拉框值
		}
		function provinceChange(e){
			git.getDistrictsByParentid(e.sender.getValue(),function(data){
				nui.get('city').setData(data.items);
			});
			nui.get('city').setData(null);//清空“城市”下拉框值
			nui.get('town').setData(null);//清空“区/县”下拉框值
			nui.get('city').setValue();//清空“城市”下拉框值
			nui.get('town').setValue();//清空“区/县”下拉框值
		}
		function cityChange(e){
			git.getDistrictsByParentid(e.sender.getValue(),function(data){
				nui.get('town').setData(data.items);
			});
			nui.get('town').setData(null);//清空“区/县”下拉框值
			nui.get('town').setValue();//清空“区/县”下拉框值
		}
		/*********************************国家省市县区*******************************/
	</script>
</body>
</html>
