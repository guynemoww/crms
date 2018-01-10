<%@page pageEncoding="UTF-8"%>

<html>
<!-- 
  - Author(s): fengjiahui
  - Date: 2014-02-28 16:34:13
  - Description:批量平台客户检查的发起
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>客户情况</title>
</head>
<body>
	<div id="queryform" class="nui-dynpanel" columns="4"  >
		<label>客户全称：</label>
		<input name="partyName" id="partyName" class="nui-text nui-form-input" /> 
		<label>经办单位：</label>
		<input name="orgNum" id="orgNum" dictTypeId="org" class="nui-text nui-form-input"/> 
		<label>所属行业：</label>
		<input name="industrialTypeCd" id="industrialTypeCd" class="nui-text nui-form-input" dictTypeId="XD_KHCD0092"/> 
		<label>最近一期分类结果：</label>
		<input name="" id="" class="nui-text nui-form-input"/> 
		<label>是否纳入预警管理：</label>
		<input class="nui-text" name="isPutWarn" id="isPutWarn"/>
		<label>纳入时具体级别：</label>
		<input class="nui-text" name="earlyWarningLevelCd" id="earlyWarningLevelCd" dictTypeId="XD_YJCD0004">	
		<label>最近一期授信后检查结论：</label>
		<input class="nui-text" name="pfConclusion" id="pfConclusion" dictTypeId="XD_DHCD0005" />
		<label>控制措施：</label>
		<input class="nui-text" name="pfRiskcontrol" id="pfRiskcontrol" dictTypeId="XD_DHCD0006" />
		<label>是否列入重点关注板块：</label>
		<input class="mini-dictradiogroup" name="isPutAttention" id="isPutAttention" dictTypeId="YesOrNo" onvaluechanged="attentionChanged()" />
		<label>纳入时具体板块：</label>
		<input class="nui-dictcombobox nui-form-input" name="attentionModule" id="attentionModule" dictTypeId="XD_DHCD0013" enabled="false"/>
		<input id="partyId" class="nui-hidden nui-form-input" name="partyId" />
		<input id="partyNum" class="nui-hidden nui-form-input" name="partyNum" />
	</div>
	<div class=""  id="saveBtn" style="border-bottom:0;text-align:right;margin-top: 20px;">
		<a class="nui-button" iconCls="icon-save" onclick="btnSave()">保存</a>
	</div>

	<script type="text/javascript">
		var callback="<%=request.getParameter("callback") %>";
		if("<%=request.getParameter("callback") %>"=="y"){
			$("#saveBtn").hide();
			$(".nui-dictcombobox").attr("enabled","false");
			$(".mini-dictradiogroup").attr("enabled","false");
		}
		nui.parse();
		git.mask(); //mask中可加一个参数表示要进行遮罩的页面元素，不加时默认为整个页面。
		var param=<%=request.getParameter("param") %>;

		var form = new nui.Form("#queryform");
		function query(){/* 加载信息 */
		   var jsonData=nui.encode({"param":param});
		   nui.ajax({
                url: "com.bos.aft.aft_spot_inspect.querySpotCorp.biz.ext",
                data:jsonData,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                	if(null!=text.spotCorp){
                		form.setData(text.spotCorp);
                	}
                	
                	if(nui.get("earlyWarningLevelCd").getValue()!=""&&nui.get("earlyWarningLevelCd").getValue()!=null){
                		nui.get("isPutWarn").setValue("是");
                	}else{
                		nui.get("isPutWarn").setValue("否");
                	}
                	if(text.lastInspect!=null&&text.lastInspect.pfConclusion!=null){
                		nui.get("pfConclusion").setValue(text.lastInspect.pfConclusion);
                	}
                	if(text.lastInspect!=null&&text.lastInspect.pfRiskcontrol!=null){
                		nui.get("pfRiskcontrol").setValue(text.lastInspect.pfRiskcontrol);
                	}
                	
                	//form.setData(text.spotCorp);
					git.unmask();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
                }
            });			
		}
		query();
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
            url: "com.bos.aft.aft_spot_inspect.updateSpotAttDetail.biz.ext",
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
</body>
</html>