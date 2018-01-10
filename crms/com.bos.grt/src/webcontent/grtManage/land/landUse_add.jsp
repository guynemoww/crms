<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2014-07-07
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>土地使用权新增、编辑、查看</title>
</head>
<body>
	<div id="form1">
		<input name="item.suretyKeyId" id="suretyKeyId" class="nui-hidden"   />
		<div class="nui-dynpanel" columns="4">
			<label>不动产权证书编号：</label><!--BUG #2874  -->
			<input name="item.landUseNo" id="item.landUseNo" required="true" class="nui-textbox nui-form-input" vtype="maxLength:150" />
			
			<label>土地使用权证发证机关：</label>
			<input name="item.certiIssue" required="true" class="nui-textbox nui-form-input" vtype="maxLength:150" />
			
			<label>土地性质：</label>
			<input name="item.landQuale" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="XD_YWDB0103" />
			
			<label>土地取得方式：</label>
			<input name="item.landGainWay" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YWDB0104" />
			
			<label>土地使用面积（平方米）：</label>
			<input name="item.landArea" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:16;" />
			
			<label>土地使用权终止日：</label>
			<input name="item.useEndDate" required="true" class="nui-datepicker nui-form-input" id="item.buildBeginDate" format="yyyy-MM-dd" allowinput="false" />
			
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
			
			<label>土地座落位置：</label>
			<textarea name="item.landLocation" required="true"  class="nui-textarea" vtype="maxLength:200" emptyText="请输入地址信息..." 
				style="width:300px;height:60px;" ></textarea>
		</div>
		
		<div class="nui-dynpanel" columns="4" id="table1">
			
			<label>土地现状：</label>
			<input name="item.landActuality" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YWDB0105" />
			
			<label>土地取得日期：</label>
			<input name="item.landGainDate" required="true" class="nui-datepicker nui-form-input" id="item.landGainDate" format="yyyy-MM-dd" allowinput="false" />
			
			<label>使用权年限：</label>
			<input name="item.usePowerLimit" required="true" class="nui-textbox nui-form-input" vtype="int;maxLength:3;" />
			
			<label>是否已全额付清土地出让金：</label>
			<input name="item.ifAllPay" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" />
			
			<label>是否存在影响抵押物处置的土地附着物：</label>
			<input name="item.ifAffect" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" />
			
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
			nui.get('city').setData(null);//清空“城市”下拉框值
			nui.get('town').setData(null);//清空“区/县”下拉框值
			
		}
		function provinceChange(e){
			git.getDistrictsByParentid(e.sender.getValue(),function(data){
				var parentid = e.sender.getValue();
				if(parentid=='110000000000'||parentid=='120000000000'||parentid=='310000000000'||parentid=='500000000000'){
					var cityDate = nui.encode([{"dictid":parentid,"dictname":e.sender.getText()}]);
					nui.get('city').setData(cityDate);
				}else{
					nui.get('city').setData(data.items);
				}
			});
			nui.get('town').setData(null);//清空“区/县”下拉框值
		}
		function cityChange(e){
			git.getDistrictsByParentid(e.sender.getValue(),function(data){
				nui.get('town').setData(data.items);
			});
		}
		/*********************************国家省市县区*******************************/
	</script>
</body>
</html>
