<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): chenchuan@git.com.cn
  - Date: 2013-11-21
  - Description:TB_CSM_INVESTMENT_INFO, com.bos.dataset.csm.TbCsmInvestmentInfo
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<fieldset>
	  <legend>
	    <span>股东投资情况</span>
	   </legend>
	<input name="item.partyId" id="item.partyId" class="nui-hidden" />
	<input name="item.investCustId" id="item.investCustId" class="nui-hidden" />
	<input id="item.investmentId" name="item.investmentId"   class="nui-hidden"  />
	<div class="nui-dynpanel" columns="4">
		
		<label>被投资客户类型：</label>
		<!--<input id="item.investCustType" name="item.investCustType" dictTypeId="CDKH0034"  class="nui-dictcombobox nui-form-input"
			required="true"  onvaluechanged="clearControlInfo"/>-->
		<input id="item.investCustType" name="item.investCustType" dictTypeId="CDKH0034"  class="nui-dictcombobox nui-form-input"
			required="true"  onvaluechanged=""/>
		
		<label>被投资客户名称：</label>
		<!--<input id="item.investCustName"  name="item.investCustName" required="true" allowInput="false"  class="nui-buttonEdit" onbuttonclick="selectCust"  />-->
		<input id="item.investCustName"  name="item.investCustName" required="true"  class="nui-textbox nui-form-input"  />
		
		<label>证件类型：</label>
		<!--<input id="item.certType" name="item.certType"enabled="false" dValue="202"class="nui-dictcombobox nui-form-input"  dictTypeId="CDKH0002" />-->
		<input id="item.certType" name="item.certType"enabled="" dValue="202"class="nui-dictcombobox nui-form-input"  dictTypeId="XD_ZJLX00021" />
		
		<label>证件号码：</label>			
		<input id="item.orgRegisterNum"  name="item.orgRegisterNum" class="nui-textbox nui-form-input"/>
	
		<label>出资方式：</label>
		<input id="item.investimentMethodCd" name="item.investimentMethodCd" multiSelect="true" required="true" class="nui-checkboxlist nui-form-input" dictTypeId="CDKH0076" />

		<label>币种：</label>
		<input id="item.currecyCd" name="item.currecyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001" dvalue="CNY" nullItemText="true"/>

		<label>投资金额：</label>
		<input id="item.investmentAmt"  name="item.investmentAmt" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency" />

		<label>持股比例（%）：</label>
		<input id="item.shareholdingRatio"  name="item.shareholdingRatio" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:8;range:0,100" />
		
		<label>投资日期：</label>
		<input id="item.investmentDate" name="item.investmentDate" required="true" class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>
	</div>
	</fieldset>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;">
		<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	</div>
</div>
<script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1");
    
    var partyId = "<%=request.getParameter("partyId") %>";
	if (partyId) {
		nui.get("item.partyId").setValue(partyId);
		//字典过滤	   
	    var arr = git.getDictDataUnFilter("CDKH0034","6");
		nui.get("item.investCustType").setData(arr);
		
		var ways = mini.getDictData("CDKH0076"); 
		var datajson=[];
		for(var i=0; i<ways.length; i++){
			var json = {};
			json["id"]=ways[i].dictID;
	    	json["text"]=ways[i].dictName;
	    	datajson[i]=json;
		}
		nui.get('item.investimentMethodCd').setData(datajson);
	}
	
	
	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		} 
		
		var json1 = {
			"investCustId" : nui.get("item.investCustId").getValue(),
			"partyId" : nui.get("item.partyId").getValue()
		};
		msg = exeRule("CUS_INVEST", "1", json1);
		if (null != msg && '' != msg) {
			nui.alert(msg);
			return;
		}
		
		git.mask("form1");
		var o=form.getData();
		var json=nui.encode(o);
		$.ajax({
	            url: "com.bos.csm.corporation.Investment.addTbCsmInvestmentInfo.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask("form1");
	            	if(text.msg){
	            		alert(text.msg);
	            		return;
	            	} else {
	            		alert("保存成功!");
	            		CloseWindow("ok");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	   				git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
	
	//被投资客户选择	
	function selectCust(e) {
	
		var investCustType = nui.get("item.investCustType").getValue();
		if (null == investCustType || '' == investCustType) {
			nui.alert("请先选择被投资客户类型！");
			return;
		}
		nui.open({
	        url: nui.context + "/csm/pub/queryCorpAndNartual.jsp?stockholderTypeCd="+investCustType,
	        showMaxButton: true,
	        title: "选择企业",
	        width: 1000,
	        height: 500,
	        ondestroy: function (action) {            
	            if (action == "ok") {
	                var iframe = this.getIFrameEl();
	                var data = iframe.contentWindow.getData();
	                data = nui.clone(data);
	                if (data) {
                  		nui.get("item.investCustId").setValue(data.partyId);
	                    nui.get("item.investCustName").setText(data.partyName);
	                    nui.get("item.investCustName").setValue(data.partyName);
	                    nui.get("item.orgRegisterNum").setValue(data.orgRegisterCd);
	                }
	            }
	        }
	    }); 
    }
        
        
	<%--选择出资方式 --%> 
	function selectMethodCd(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=CDKH0076",
            title: "选择字典项",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.dictid);
                        btnEdit.setText(data.dictname);
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}
	
	
	function clearControlInfo(){
		var actualControllerType= nui.get("item.investCustType").getValue();
  		if(actualControllerType=="2"||actualControllerType=="3"||actualControllerType=="4"||actualControllerType=="5"){//如类型选择其他，股东名称允许“手工输入”
  			nui.get("item.orgRegisterNum").setEnabled(true);
  			nui.get("item.investCustName").setAllowInput(true);
  		}else{
  			nui.get("item.orgRegisterNum").setEnabled(false);
  			nui.get("item.investCustName").setAllowInput(false);
  		}	
  			nui.get("item.orgRegisterNum").setValue("");
  			nui.get("item.investCustName").setValue();
  			nui.get("item.investCustName").setText();
  			nui.get("item.investCustId").setValue();
	}
</script>
</body>
</html>
