<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): cc
  - Date: 2016-5-10
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<title>房地产新增、编辑、查看</title>
</head>
<body>
	<div id="form1">
		<input name="item.suretyKeyId" id="suretyKeyId" class="nui-hidden"  />
		<div class="nui-dynpanel" columns="4">
			
			<label>建设年份：</label>
			<input name="item.buildYear" id="item.buildYear" required="false" vtype="maxLength:50"class="nui-textbox nui-form-input"  />
			
			<label>不动产证书办理状态：</label>
			<input name="item.housePropStatus" id="item.housePropStatus" required="true" class="nui-dictcombobox nui-form-input"  dictTypeId="XD_YWDB0101" onvaluechanged="propStatusChange"/>
			
			<label>不动产权证书编号：</label>
			<input name="item.housePropNo" id="item.housePropNo" required="true" class="nui-textbox nui-form-input" vtype="maxLength:200" />
			
			<label>登记时间：</label>
			<input name="item.registerDate" id="item.registerDate" required="true" class="nui-datepicker nui-form-input" allowinput="false" maxDate="<%=GitUtil.getBusiDateStr()%>"/>
			
			<label>国家：</label>
			<input name="item.country" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000003" onvaluechanged="nationChange" />
			
			<label>省/直辖区：</label>
			<input name="item.province" required="true" class="nui-combobox nui-form-input" valueField="dictid" 
				id="province" textField="dictname" onvaluechanged="provinceChange" />
				
			<label>城市：</label>
			<input name="item.city" required="true" class="nui-combobox nui-form-input" valueField="dictid"  
				id="city" textField="dictname" onvaluechanged="cityChange" />
	
			<label>区（县）：</label>
			<input name="item.town" id="town" required="true" class="nui-combobox nui-form-input" valueField="dictid" 
				textField="dictname"/>
			
			<label>座落位置：</label>
			<textarea name="item.houseLocation" required="true"  class="nui-textarea" vtype="maxLength:200" 
				style="width:300px;height:60px;" ></textarea>
		</div>
		
		<div class="nui-dynpanel" columns="4" id="table1">
			<label>建筑面积(㎡)：</label>
			<input name="item.houseArea" id="item.houseArea" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:16;" />
			
			<label>房屋结构：</label>
			<input name="item.houseStructure" id="item.houseStructure" required="true" class="nui-dictcombobox nui-form-input"  dictTypeId="XD_YWDB0102" />
			
			<label>土地使用权证号：</label>
			<input name="item.landUseNo" class="nui-textbox nui-form-input" vtype="maxLength:40" />
			
			<label>土地性质：</label>
			<input name="item.landQuale"  class="nui-dictcombobox nui-form-input" id="item.landQuale"  dictTypeId="XD_YWDB0103" />
			
			<label>土地的取得方式：</label>
			<input name="item.landGainWay"  required="true" class="nui-dictcombobox nui-form-input" id="item.landGainWay"  dictTypeId="XD_YWDB0104" />
			
			<label>房屋是否出租：</label>
			<input name="item.houseIsRent" required="true" class="nui-dictcombobox nui-form-input" id="item.houseIsRent"  dictTypeId="XD_0002" onvaluechanged="houseIsRentChange"/>
			
			<label  id="rentAmt">年租金(元)：</label>
			<input name="item.rentAmt"   id="item.rentAmt"required="true"class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:1"  dataType="currency"/>
							
			<label id="rentEndDateLab">出租合同到期日：</label>
			<input name="item.rentEndDate" id="item.rentEndDate"  required="true"class="nui-datepicker nui-form-input" allowinput="false"/>
			
			<label>备注说明 ：</label>
			<textarea name="item.remark" class="nui-textarea" vtype="maxLength:200" emptyText="请输入备注信息..." 
				style="width:300px;height:60px;" ></textarea>
		</div>
	</div>
					
	    
    <script type="text/javascript">
	 	var form1 = new nui.Form("#form1");
		
		//房产证办理状态改变
		function propStatusChange(e){
			if(e.value=="01"){//已办理
				nui.get("item.housePropNo").setRequired(true);
				nui.get("item.registerDate").setRequired(true);
				nui.get("item.houseArea").setRequired(true);
				nui.get("item.houseStructure").setRequired(true);
				if(sortType=="010103"){//农民住房，不动产权证号可以不输
					nui.get("item.housePropNo").setRequired(false);
				}
				form1.validate();
			}else if(e.value=="02"){//未办理
				nui.get("item.housePropNo").setRequired(false);
				nui.get("item.registerDate").setRequired(false);
				nui.get("item.houseArea").setRequired(false);
				nui.get("item.houseStructure").setRequired(false);
				form1.validate();
			}else{
				nui.get("item.housePropStatus").setRequired(true);
				form1.validate();
			}
		}
		
		//房屋是否出租
		function houseIsRentChange(e){
			if(e.value=="1"){//出租
				$("#rentEndDateLab").show();
				nui.get("item.rentEndDate").show();
				$("#rentAmt").show();
				nui.get("item.rentAmt").show();				
			}else{//不出租
				$("#rentEndDateLab").hide();
				$("#rentAmt").hide();
				nui.get("item.rentAmt").hide();
				nui.get("item.rentAmt").setValue();
				nui.get("item.rentEndDate").hide();
				nui.get("item.rentEndDate").setValue();
			}
		}
		/*********************************国家省市县区*******************************/	
		function nationChange(e){
			if(e.sender.getValue()=="CHN"){
				nui.get('city').setRequired(true);
				nui.get('town').setRequired(true);
				nui.get('province').setRequired(true);
			}else{
				nui.get('city').setRequired(false);
				nui.get('town').setRequired(false);
				nui.get('province').setRequired(false);
			}
			git.getDistrictsByParentid(e.sender.getValue(),function(data){
				nui.get('province').setData(data.items);
			});
			nui.get('province').setData(null);//清空“城市”下拉框值
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
