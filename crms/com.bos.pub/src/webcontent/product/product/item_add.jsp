<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): lujinbin cc
  - Date: 2013-10-31
  - Description:TB_SYS_PRODUCT, com.bos.pub.product.TbSysProduct
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>

</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input name="item._entity" value="com.bos.pub.product.TbSysProduct" class="nui-hidden" />
	<input name="item.superiorId" id="item.superiorId" class="nui-hidden" />
	<input id ="item.productId"name="item.productId" class="nui-hidden"/>
	
	<div class="nui-dynpanel" columns="4">
		
		<label>授信品种代码：</label>
		<input id="productCd" name="item.productCd" required="true" class="nui-textbox nui-form-input" vtype="maxLength:10" enabled="false"/>

		<label>授信品种名称：</label>
		<input name="item.productName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />

		<label>是否显示在产品树中：</label>
		<input id="discountInd" name="item.discountInd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"  emptyText="请选择"/>

		<label>授信品种级别：</label>
		<input id="productLevel" name="item.productLevel" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:9" dictTypeId="XD_GGCD842" enabled="false"/>
		
		<label>业务类型：</label>
		<input name="item.productType" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_GGCD033" emptyText="请选择"/>

<%-- 
		<label>授信品种-控制规则代码：</label>
		<input name="item.tbSysTechProduct.productRuleCd" allowInput="false" required="false" class="nui-buttonEdit" 
			onbuttonclick="selectProductRule" enabled="false"/>
					
		<label>额度循环标志：</label>
		<input name="item.circleInd" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002" emptyText="请选择"/>
		
		<label>合同循环标志：</label>
		<input name="item.circleIndCon" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002" emptyText="请选择"/>
				
		<label>是否适用节假日参数：</label>
		<input name="item.holidayParam" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002" emptyText="请选择"/>
		
		<label>贴息标志：</label>
		<input name="item.tiexiInd" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002" emptyText="请选择"/>
		
		<label>电子渠道支用额度（元）：</label>
		<input name="item.dzqdzyLimit" required="false" class="nui-textbox nui-form-input" dataType="currency" vtype="float;maxLength:20" />
--%> 		
<%-- 
		<label>父品种ID：</label>
		<input name="tbSysProduct.superiorId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>产品相关部门：</label>
		<input  name="item.productDepartment" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_GG20055" emptyText="请选择" />
 --%>
		<label>生效日期：</label>
		<input id="okTime" name="item.validtDate" required="true" allowInput="false" enabled="false" value="<%=GitUtil.getBusiDate()%>" class="nui-datepicker nui-form-input" />
		
 		
		<label class="nui-form-label">经办机构：</label>
		<input text="<%=((UserObject)session.getAttribute("userObject")).getUserOrgName()%>"  required="true" enabled="false" allowInput="false" class="nui-buttonEdit" />
		
		<label class="nui-form-label">经办人：</label>
		<input text="<%=((UserObject)session.getAttribute("userObject")).getUserName()%>" required="true"   enabled="false" allowInput="false" class="nui-buttonEdit" />
		
		<label>经办日期：</label>
		<input class="nui-datepicker nui-form-input" format="yyyy-MM-dd"  required="true" enabled="false" value="<%=GitUtil.getBusiDate()%>"/>
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
			
<script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");

		var productId = "<%=request.getParameter("productId") %>";//父类 id
		var productLevel="<%=request.getParameter("productLevel") %>";// 授信级别
		var productCd="<%=request.getParameter("productCd") %>";// 上级节点ID
		var daLei="<%=request.getParameter("daLei") %>"; // 大类
		var c="<%=request.getParameter("c") %>"; // 标识
		
		function getMaxProduct(){
			var jsons=nui.encode({"product":{"productCd":productCd}});
			$.ajax({
	            url: "com.bos.pub.product.QueryMaxProductCd.biz.ext",
	            type: 'POST',
	            data: jsons,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if(text){
			          nui.get("productCd").setValue(text.proCd[0].productCd);
			       	  nui.get("item.productId").setValue(text.proCd[0].productCd);
	            	} 
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
			});
		}
		getMaxProduct();
		
		if (productId) {
			nui.get("item.superiorId").setValue(productId); 
		}
		if(c=="2"){// 没数据时添加大类
			nui.get("discountInd").setValue("1");
		    nui.get("discountInd").setEnabled(false);
		    nui.get("productLevel").setValue(1);
		}else if(c=="1"){// 已有数据
			if(productLevel=="1"){//有数据时，点击大类添加种类
				 nui.get("discountInd").setValue("1");
				 nui.get("discountInd").setEnabled(false);
				 nui.get("productLevel").setValue(Number(productLevel)+1);// 授信品种级别自增
			}else{// 添加小类
			 	 nui.get("productLevel").setValue(Number(productLevel)+1);// 授信品种级别自增
			}
		}
				
		if(daLei=="1"){//右键菜单添加大类
  			nui.get("discountInd").setValue("1");
		    nui.get("discountInd").setEnabled(false);
		    nui.get("productLevel").setValue(1);
		}	
	
		
		function save() {
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请将信息填写完整");
				return;
			}
			var o=form.getData();
			var json=nui.encode(o);
			$.ajax({
		            url: "com.bos.pub.product.addProduct.biz.ext",
		            type: 'POST',
		            data: json,
		            cache: false,
		            contentType:'text/json',
		            success: function (text) {
		            	if(text.msg){
		            		nui.alert(text.msg);
		            	} else {
		            		CloseWindow("ok");
		            		
		            	}
		            },
		            error: function (jqXHR, textStatus, errorThrown) {
		                nui.alert(jqXHR.responseText);
		            }
				});
		}


function selectProductRule(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/product/product/rule_list.jsp",
            showMaxButton: false,
            title: "选择",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.grid.getSelected();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.productRuleCd);
                        btnEdit.setText(data.productRuleName);
                        // 在此也可做其他操作
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
                	// 在此也可做其他操作
            	}
        	}
        });            
}

</script>
</body>
</html>
