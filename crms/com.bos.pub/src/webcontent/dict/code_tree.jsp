<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-10-31
  - Description:TB_SYS_PRODUCT, com.bos.pub.product.TbSysProduct
-->
<head>
<%@include file="/common/nui/common.jsp"%>

</head>

<body>
	<%
		String dicttypeid = JspUtil.getParameterHaveSign(request,
				"dicttypeid");
		//1:选择任意节点，0:默认只能选择子节点
		String selectEvery = JspUtil.getParameterHaveSign(request,
				"selectEvery");
		//是否 可以清除[1=可以,0=不可以],默认允许清除 
		boolean clear = "1".equals(JspUtil.getParameter(request, "clear",
				"1"));
	%>
	<div class="nui-fit" style="padding: 6px 12px">
		<div class="nui-fit" style="border: 1px solid #8ba0bc;" allowResize="false">
			<ul id="tree1" class="nui-tree" style="width: auto; height: auto;" showTreeIcon="true" textField="dictname" idField="dictid" parentField="parentid" expandOnLoad="true" onnodeclick="nodeclick" dataField="dictList">
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
			<a class="nui-button" iconCls="icon-cancel" onclick="onClear">清空</a>
			<%
				}
			%>
		</div>
	</div>

	<script type="text/javascript">
		nui.parse();
		var tree = nui.get("tree1");

		var currentNode = null;

		reload();
		function reload() {
			var json = {
				"dictTypeId" :
	<%=dicttypeid%>
		};
			$
					.ajax({
						url : "com.primeton.components.nui.DictLoader2.getDictData.biz.ext",
						type : 'POST',
						data : nui.encode(json),
						contentType : 'text/json',
						success : function(text) {
							if (text.msg) {
								nui.alert(text.msg);
							} else {
								text.dictList = text.dictList || [];
								$.each(text.dictList,
										function(idx, val) {
											//console.log(idx + ',' + val);
											val.dictid = val.dictid
													|| val.dictID;
											val.dictname = val.dictname
													|| val.dictName;
										});
								tree.setExpandOnLoad(text.dictList.length < 20);//选择项多余20的时候，不展开
								tree.loadList(text.dictList, "dictid",
										"parentid");
								//nodeclick({"node":tree.getRootNode().children[0]});
							}
						},
						error : function(jqXHR, textStatus, errorThrown) {
							nui.alert(jqXHR.responseText);
						}
					});
			//tree.load("com.bos.pub.product.getProductList.biz.ext");
		}

		function nodeclick(e) {
			currentNode = e.node;
		}

		function GetData() {
			return currentNode;
		}

		function onOk() {
			if (!currentNode) {
				nui.alert("请选择一条记录");
				return;
			}
			if (
	<%=selectEvery%>
		!= "1" && currentNode.children) {
				nui.alert("请选择末级的字典项");
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
