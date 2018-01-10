<%@page pageEncoding="UTF-8"%>
<div id='sq' class="nui-dynpanel"  columns="4">
	<label><a href="#" onclick="toGoCust();">委托人：</a></label>
	<input id="productDetail.wtr" name="productDetail.wtr" class="nui-buttonEdit nui-form-input" required="true" 
			allowInput="false" onbuttonclick="selectWtr"  style="width:80%;float:left"  emptyText="--请选择--" />
	<label id="wtxmmc">委托项目名称：</label>
	<input id="wtxmmc1" name="wtxmmc1"  class="nui-text nui-form-input"/>
	<label>证件类型：</label>
	<input id="zjlx" name="zjlx"  required="true"  class="nui-text nui-form-input" dictTypeId="CDKH0002"/>
	<label>证件号码：</label>
	<input id="zjhm" name="zjhm"  required="true"  class="nui-text nui-form-input"/>
	<label>委托人类型：</label>
	<input name="productDetail.wtrlx" id="productDetail.wtrlx" required="true" data="data" valueField="dictID" 
	class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1063"  emptyText="--请选择--" enabled="false"/>
	<label>委托贷款性质：</label>
	<input name="productDetail.wtdkxz" id="productDetail.wtdkxz" required="true" data="data" valueField="dictID" 
	class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0206"  emptyText="--请选择--"/>
</div>
<div id='ht' class="nui-dynpanel"  columns="4" style="width:99.5%;">
	<label>贷款种类：</label>
	<input id="conDetail.dkzl" name="conDetail.dkzl" required="true" align="center" class="nui-textbox nui-form-input" dictTypeId="product"/>
	<label>贷款项目：</label>
	<input id="conDetail.dkxm" name="conDetail.dkxm"  required="true"  class="nui-textbox nui-form-input" />
	<label>抵质押物及权证保管方：</label>
	<input id="conDetail.dzywjqzbgf" name="conDetail.dzywjqzbgf"  class="nui-textbox nui-form-input"  required="true"/>
</div>
<div id='fk' class="nui-dynpanel"  columns="4" style="width:99.5%;">
</div>
<script type="text/javascript">
	$("#sq").css("display","none");
	$("#ht").css("display","none");
	$("#fk").css("display","none");
	var stepFlag =  "<%=request.getParameter("stepFlag") %>";//阶段标志
	if(stepFlag=='biz'){
		$("#sq").css("display","block");
	}
	if(stepFlag=='con'){
		$("#ht").css("display","block");
	}
	if(stepFlag=='pay'){
		$("#fk").css("display","block");
	}
	//选委托人
	/* function selectWtr(e) {
        var btnEdit = this;
        nui.open({
	        url: nui.context + "/csm/pub/third_cust_query.jsp?thirdCustTypeCd=4",
	        showMaxButton: true,
	        title: "选择委托人",
	        width: 800,
	        height: 500,
	        ondestroy: function (action) {            
	            if (action == "ok") {
	                var iframe = this.getIFrameEl();
	                var data = iframe.contentWindow.getData();
	                data = nui.clone(data);
	                if (data) {
                    	btnEdit.setValue(data.partyId);
	                    btnEdit.setText(data.partyName);
	                    nui.get("zjlx").setValue(data.certType);
	                    nui.get("zjlx").validate();
	                    nui.get("zjhm").setValue(data.certNum);
	                    nui.get("zjhm").validate();
	                    if(data.certType == '202'){
	                    	nui.get("productDetail.wtrlx").setValue('3');
	                    }else{
	                    	nui.get("productDetail.wtrlx").setValue('4');
	                    }
	                }
	            }
	        }
	    }); 
	} */
	function toGoCust(){
		var wtr = nui.get("productDetail.wtr").getValue();
		if(wtr){
			toGoCustDetail(wtr);
		}else{
			alert("请先选择委托人！");
		}
	}
</script>