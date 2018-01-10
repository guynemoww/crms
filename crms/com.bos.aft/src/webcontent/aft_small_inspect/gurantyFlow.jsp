<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<%-- 
  - Author(s): zengfang
  - Date: 2014-02-13 10:16:54
  - Description:
--%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>日常检查</title>

</head>
<body>
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="4">
			<label>客户名称：</label>
			<input name="partyName"  class="nui-text nui-form-input" />
			<label>客户编码：</label>
			<input name="partyNum"  class="nui-text nui-form-input"/>
			<label>ECIF客户编号：</label>
			<input name="ecifPartyNum"  class="nui-text nui-form-input"/>
			<label>客户类型：</label>
			<input name="partyTypeCd"  class="nui-text nui-form-input" dictTypeId="XD_KHCD0219"/>
		</div>
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
  	  <a class="nui-button" onclick="add">创建担保客户贷后检查流程</a>
	</div>

<script type="text/javascript">
	nui.parse();
	var partyId="<%=request.getParameter("corpid") %>";
	var form = new nui.Form("#form1");
	var json=nui.encode({"partyId":partyId});
	var node;
	var msg;
	var bizId;
	function query(){/* 数据加载 */
		    git.mask();
			var json = nui.encode({"param":{"partyId":partyId}});
		    nui.ajax({
                url: "com.bos.aft.dailyInspect.queryCorpInfo.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                	form.setData(text.corpInfo);
                	git.unmask();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
                }
            });      			
		}
		query();
	//创建一个新的检查
	function add(){
		nui.ajax({
			url: "com.bos.aft.aft_small_inspect.createSmallBusiFlow.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	msg=mydata.msg;
            	node=mydata.node;
            	bizId=mydata.bizId;
            	if(msg==1){
            		    var param=nui.encode({"bizId":bizId,"node":node});
            			var url=nui.context+"/aft/aft_small_inspect/aft_dailyIns_tree_go.jsp?param="+param;
            			git.go(url);
            		}else if(msg==2){
            			nui.alert("该客户贷后检查流程还未走完!");
            			return;
            		}else if(msg==3){
            			nui.alert("出现异常!");
            			return;
            		}
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