<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): fengjiahui
  - Date: 2014-02-13 16:27:30
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>客户情况</title>
</head>
<body style="margin-left:10px;">
	<div id="queryform" class="nui-dynpanel" columns="4"  >
		<label>客户全称：</label>
		<input name="partyName" id="partyName" class="nui-text nui-form-input" /> 
		<label>企业性质：</label>
		<input name="enterpriseNatureCd" id="enterpriseNatureCd" class="nui-text nui-form-input" dictTypeId="XD_KHCD0166"/> 
		<label>最近一期分类结果：</label>
		<input name="" id="" class="nui-text nui-form-input"/> 
		<div colspan="2"></div>
		<label>是否纳入预警管理：</label>
		<input class="nui-text nui-form-input" name="isPutWarn" id="isPutWarn" dictTypeId="YesOrNo"  />
		<label>纳入时具体级别：</label>
		<input class="nui-text nui-form-input" name="warningLevelCd" id="warningLevelCd" dictTypeId="XD_YJCD0004">	
		
		<label>是否列入重点关注板块：</label>
		<input class="mini-dictradiogroup" name="isPutAttention" id="isPutAttention" dictTypeId="YesOrNo" onvaluechanged="attentionChanged()" />
		<label>纳入时具体板块：</label>
		<input class="nui-dictcombobox nui-form-input" name="attentionModule" id="attentionModule" dictTypeId="XD_DHCD0013" enabled="false"/>
		<label>授信金额（万元）：</label>
		<input class="nui-text nui-form-input" >
		<label>授信余额（万元）：</label>
		<input class="nui-text nui-form-input" >
		<label>敞口余额（万元）：</label>
		<input class="nui-text nui-form-input" >
		<label>到期日（最后一笔授信业务到期日）：</label>
		<input class="nui-text nui-form-input" >
		<label>五级分类：</label>
		<input class="nui-text nui-form-input" >
		<label>担保方式：</label>
		<input class="nui-text nui-form-input" >
		<input id="partyId" class="nui-hidden nui-form-input" name="partyId" />
		<input id="partyNum" class="nui-hidden nui-form-input" name="partyNum" />
	</div>
	<!-- 
	<div class=""  id="saveBtn" style="border-bottom:0;text-align:center;margin-top: 20px;">
		<a class="nui-button" iconCls="icon-save" onclick="btnSave()">保存</a>
	</div>
	 -->
	<div id="datagrid1" class="nui-datagrid" showPager="false" dataField="credits"
	        url="com.bos.aft.aft_spot_inspect.queryCreditMessage.biz.ext" editNextOnEnterKey="true" style="margin-top:20px;" >
		 <div property="columns"> 
		      <div field="productType" dictTypeId="product" headerAlign="center">授信品种
		      </div>      
			  <div field="availableExposure"  headerAlign="center">授信余额
		      </div>                              
		      <div field=""  headerAlign="center">是否便捷贷业务
		      </div>
		      <div field="guarantyType"  headerAlign="center">担保方式
		      </div> 
		      <div field=""  headerAlign="center">保证人
		      </div>
		  </div>
	</div>	
</body>
<script type="text/javascript">
	var callback="<%=request.getParameter("callback") %>";
	if("<%=request.getParameter("callback") %>"=="y"){
			//$("#saveBtn").hide();
			$(".nui-dictcombobox").attr("enabled","false");
			$(".mini-dictradiogroup").attr("enabled","false");
		}
	nui.parse();
	git.mask(); //mask中可加一个参数表示要进行遮罩的页面元素，不加时默认为整个页面。
	var form = new nui.Form("#queryform");
	var param=<%=request.getParameter("param") %>;
	//查询客户信息。
	function queryCorp(){
		var json=nui.encode({"param":param});
        $.ajax({
            url: "com.bos.aft.aft_small_inspect.querydailyInspect.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	if(mydata.msg==1){
            		git.unmask();
            		return nui.alert("指定客户不存在！");
            	}
                var o = nui.decode(mydata.inspectCorp);
                form.setData(o);
                if(nui.get("warningLevelCd").getValue()!=""&&nui.get("warningLevelCd").getValue()!=null){
                	nui.get("isPutWarn").setValue("是");
                }else{
                	nui.get("isPutWarn").setValue("否");
                }
                var smbInspect=mydata.smbInspect;
                if(smbInspect!=null&&smbInspect!=undefined){
                	if(smbInspect.isPutAttention!=null){
                		nui.get("isPutAttention").setValue(smbInspect.isPutAttention);
                	}
                	if(smbInspect.attentionModule!=null){
                		nui.get("attentionModule").setValue(smbInspect.attentionModule);
                	}
                }
                git.unmask();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
            }
        });
	}
	
	queryCorp();
	
	var grid = nui.get("datagrid1");
    grid.load({"partyId":param.partyId});
	
	function attentionChanged(){
		if(nui.get("isPutAttention").getValue()=="1"){
			nui.get("attentionModule").setEnabled(true);
		}else{
			nui.get("attentionModule").setEnabled(false);
		}
	
	}
	function btnSave(){
		git.mask();
		var isPutAttention=nui.get("isPutAttention").getValue();
		var attentionModule=nui.get("attentionModule").getValue();
		var jsonData=nui.encode({"param":param,"isPutAttention":isPutAttention,"attentionModule":attentionModule});
		 $.ajax({
            url: "com.bos.aft.aft_small_inspect.updateSmbAttDetail.biz.ext",
            type: 'POST',
            data: jsonData,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	alert(mydata.msg);
                git.unmask();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
            }
        });
	}
</script>
</html>