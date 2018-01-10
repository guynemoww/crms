<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-21 18:27:10
  - Description:
-->
<head>
<title>客户额度</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<div id="layout" class="nui-layout" style="width:100%;height:100%;">
	<div title="客户额度" region="west"  width="240" class="sub-sidebar" allowResize="false">
		<ul id="tree" class="nui-tree" style="width:300px;padding:5px;" 
			showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
			onnodeclick="nodeclick">        
		</ul>
	</div>
	<div title="center" region="center" style="border:0;padding-top:5px;">
		<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:98%;height:100%;border:0;" refreshOnClick="true"></div>
    </div>
</div>
<script type="text/javascript">
	nui.parse();
	var menudata;
	var tree = nui.get("tree");
	var limitId ="<%=request.getParameter("limitId") %>"; //客户额度ID
	var partyNum ="<%=request.getParameter("partyNum") %>"; //客户编号
	var processInstId ="<%=request.getParameter("processInstId") %>"; //
	var proFlag ="<%=request.getParameter("proFlag") %>";
	var partyId ="<%=request.getParameter("partyId") %>";//客户id
	var itemType ="<%=request.getParameter("itemType") %>";//合同项目类型
	var partyLimitType='01';
	var o;
	var json = nui.encode({"limitId":limitId});
    $.ajax({
        url: "com.bos.crdApply.crdApply.getPartyCrdByPartyId.biz.ext",
        type: 'POST',
        data: json,
        async: false,
        cache: false,
        contentType:'text/json',
        success: function (text) {
	        o = nui.decode(text);
	        partyLimitType = o.partyLimit.limitType;
	        partyId=o.party.partyId;
	        //对公
	        if("01"==o.partyLimit.limitType){
			menudata = [
				{id:"额度申请信息", text:"额度申请信息",
					children:[
						{id:"客户额度信息",text:"客户额度信息",url:"/crd/crd_apply/crd_info.jsp?limitId="+limitId+"&proFlag="+proFlag}
					]
				}
			];
	        }
	        //同业
	        if("05"==o.partyLimit.limitType){
			menudata = [
				{id:"额度申请信息", text:"额度申请信息",
					children:[
						{id:"客户额度信息",text:"客户额度信息",url:"/crd/crd_apply/crd_info_ty.jsp?limitId="+limitId+"&proFlag="+proFlag}
					]
				}
			];
	        }
	        //担保公司
	        if("08"==o.partyLimit.limitType){
				menudata = [
					{id:"额度申请信息", text:"额度申请信息",
						children:[
							{id:"担保额度信息",text:"担保额度信息",url:"/crd/crd_apply/crd_info_db.jsp?limitId="+limitId+"&proFlag="+proFlag+"&partyId="+partyId}
							//{id:"协办客户经理",text:"协办客户经理",url:"/biz/biz_info/biz_advice.jsp?applyId="+limitId+"&proFlag="+proFlag}
						]
					}
				];
	        }
	        //项目额度
	        if("09"==o.partyLimit.limitType){
	        	var view = 0;
	        	if("-1"==proFlag){
	        		view = 1;
	        	}
				menudata = [
					{id:"额度申请信息", text:"额度申请信息",
						children:[
							{id:"合作项目额度信息",text:"合作项目额度信息",url:"/crd/crd_apply/crd_info_xm.jsp?limitId="+limitId+"&proFlag="+proFlag+"&partyId="+partyId},
							{id:"影像资料",text:"影像资料",url:"/pub/imagePlatform/item_tree.jsp?businessNumber="+limitId+"&csmNum="+partyNum+"&partyTypeCd=01&flowModuleType=110&view="+view}
						]
					}
				];
	        }
						//意见提交
			if("-1"!=proFlag){
				menudata[menudata.length]={id:"意见",text:"意见",url:"/com.bos.bps.service.workFlowAdvice.flow?bizId=<%=request.getParameter("limitId") %>&processInstId=<%=request.getParameter("processInstId")%>&isSrc=2"};
			};
			tree.loadData(menudata);
			nodeclick({"node":menudata[0].children[0]});
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
    });

 	
 		//默认打开
	function nodeclick(e) {
		if(!e.node['url']) {
			return;
		}
		if (e.node['id']=='ecmprint') {
			ecm('print');
			return;
		}
		if(e.node['id']=='意见'){
			if('1' == proFlag){
				//if('08' == partyLimitType || '09' == partyLimitType){
				//授权  对比测算额度与申请额度 
					var json = nui.encode({"limitId":limitId,"processInstId":processInstId});
			     	$.ajax({
			            url: "com.bos.crdApply.crdApply.saveCrdToProcess.biz.ext",
			            type: 'POST',
			            data: json,
			            cache: false,
			            async:false,
			            contentType:'text/json',
			            success: function (text) {
			            }
			        });
				//}
			}
			/* if(partyLimitType =='01'||partyLimitType =='05' ){
				//额度申请信息保存校验
				var json = {"limitId":limitId};
		   	    msg = exeRule("RCRD_0001","1",json);
		   	    if(null != msg && '' != msg){
			   		nui.alert(msg);
			   		return;
		   	    }
			}else if(partyLimitType =='08'){
				//额度申请信息保存校验
				var json = {"limitId":limitId};
		   	    msg = exeRule("RCRD_0002","1",json);
		   	    if(null != msg && '' != msg){
			   		nui.alert(msg);
			   		return;
		   	    }
			}else if(partyLimitType =='09'){
				//额度申请信息保存校验
				var json = {"limitId":limitId};
		   	    msg = exeRule("RCRD_0003","1",json);
		   	    if(null != msg && '' != msg){
			   		nui.alert(msg);
			   		return;
		   	    }
			} */
			
		}
		
		var tabs = nui.get("orgTabs");
		tabs.setTabs([
			{title:e.node.text, url:nui.context+e.node.url}
		]);
		$("#orgTabs").show();
	}
	
	function ecm(op) {
		var json = nui.encode({"op":op,"custnum":"12345"});
        $.ajax({
            url: "com.bos.csm.corp.customerinfo.ecm.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
                var o = nui.decode(mydata);
                window.open(o.url);
            }
        });
	}
</script>
</body>
</html>