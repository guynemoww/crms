<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<!-- 
  - Author(s): lujinbin
  - Date: 2013-10-31
  - Description:TB_SYS_PRODUCT, com.bos.pub.product.TbSysProduct
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/utp/tools/icons/icon.css" />
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
	<%
		//searchMode-> 查询模式 
		//random:无条件查询所有产品
		//legorg:法人代码下所有产品
		String partyId = JspUtil.getParameterHaveSign(request, "partyId");
		String partyTypeCd = JspUtil.getParameterHaveSign(request,
				"partyTypeCd");
		String selectEvery = JspUtil.getParameterHaveSign(request,
				"selectEvery");
		//是否 可以清除[1=可以,0=不可以],默认允许清除 
		boolean clear = "1".equals(JspUtil.getParameter(request, "clear",
				"1"));
	%>
	<div class="nui-fit" style="padding: 6px 12px">
		<div class="nui-fit" style="border: 1px solid #8ba0bc;">
			<ul id="tree1" class="nui-tree" showTreeIcon="true" style="height: 100%" textField="productName" idField="productId" parentField="superiorId" expandOnLoad="true" onnodeclick="nodeclick" dataField="products">
			</ul>
		</div>
		<div class="nui-toolbar" style="text-align: center; border: none">
			<a class="nui-button" iconCls="icon-save" onclick="onOk">确定</a>
			<span style="display: inline-block; width: 25px;"></span>
			<a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" onclick="onCancel">关闭</a>
			<%
				if (clear) {
			%>
			<span style="display: inline-block; width: 25px;"></span>
			<a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" onclick="onClear">清空</a>
			<%
				}
			%>
		</div>
	</div>


<script type="text/javascript">
	nui.parse();
	//从业务性质传过来的值(对公业务) 01表示单笔 07表示低风险
	var bizType = "<%=request.getParameter("bizType") %>";
	var partyTypeCd = "<%=request.getParameter("partyTypeCd") %>";
	var searchMode =
	<%=JspUtil.getParameterHaveSign(request, "searchMode")%>
		;
	var tree = nui.get("tree1");
	var currentNode = null;
	//获取法人代码
	var legOrg ="<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("legorg") %>";
	//tree.loadData(menudata);
	reload();
	//加载产品树
	function reload() {
		var json = nui.encode({"partyId" :<%=partyId%>,"partyTypeCd" :<%=partyTypeCd%>});
		$.ajax({
			url : "com.bos.pub.product.getProductList.biz.ext",
			type : 'POST',
			data : json,
			contentType : 'text/json',
			success : function(text) {
				if (text.msg) {
					nui.alert(text.msg);
				} else {
					var data = text.products;
					var arr = new Array();
					if(searchMode){
						if("legorg"==searchMode){
							for(var i=0;i<data.length;i++){//未测试
								if(!data[i].legOrg || data[i].legOrg == "1"){
								   arr.push(data[i]);
								}
								tree.loadList(arr,"productId","superiorId");
							}
						}else if("random"==searchMode){
							tree.loadList(text.products,"productId","superiorId");
						}
					}else{//只对绵商行对公业务申请 产品树过滤
						if((bizType == "01" || bizType == "02" ||bizType == "07") && legOrg == "9999"){
							if(bizType == "01"){//对公 单笔
								for(var i=0;i<data.length;i++){
									if(data[i].department == "2"||data[i].ywlb == "00"
									||data[i].ywlb == "01"||data[i].ywlb=="05"){
						            	arr.push(data[i]);
						            }
								}
								tree.loadList(arr,"productId","superiorId");
							}else if(bizType == "07"){// 低风险
								if(partyTypeCd == "01"){//对公
									for(var i=0;i<data.length;i++){
										if(data[i].productCd == "01013001"){//单位委托贷款可以做单笔和低风险
											arr.push(data[i]);
										}
										if(data[i].department != "2"){
											arr.push(data[i]);
										}
									}
									tree.loadList(arr,"productId","superiorId");
								}else{//低风险对私 不过滤，让后台来过滤
									tree.loadList(text.products,"productId","superiorId");									
								}
							}else if(bizType == "02"){//综合授信
								for(var i=0;i<data.length;i++){
									if(data[i].department == "1"||data[i].department=="4"||data[i].ywlb == "00"
									||data[i].ywlb == "01"||data[i].ywlb=="03"||data[i].ywlb=="04"){
										arr.push(data[i]);
									}
								}
								tree.loadList(arr,"productId","superiorId");
							}
						}else{//非绵商行、绵商行对私 产品树业务申请的入口
							tree.loadList(text.products,"productId","superiorId");
						}
					}
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				nui.alert(jqXHR.responseText);
			}
		});
		//tree.load("com.bos.pub.product.getProductList.biz.ext");
	}
		function GetData() {
			return nui.clone(currentNode);
		}

		function getData() {
			return nui.clone(currentNode);
		}

		function nodeclick(e) {
			currentNode = e.node;
		}

		function onOk() {
			if (!currentNode) {
				nui.alert("请选择一条记录");
				return;
			}
			if (
	<%=selectEvery%>
		!= "1" && currentNode.children) {
				nui.alert("请选择末级的授信品种");
				return;
			}
			CloseWindow("ok");
		}

		function onCancel() {
			CloseWindow("cancel");
		}

		function onClear() {
			CloseWindow("clear");
		}
	</script>
</body>
</html>
