<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): ZhuYongLun
  - Date: 2014-06-09 15:03:55
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div title="担保保证分类" region="west" bodyStyle="overflow:hidden;" width="240" class="sub-sidebar" allowResize="false">
		<ul id="tree1" class="nui-tree" style="width:300px;padding:5px;" 
			showTreeIcon="true" textField="dictname" idField="dictid" parentField="parentid" expandOnLoad="true"
			onnodeclick="nodeclick" dataField="dictList">
		</ul>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
    <a class="nui-button"  iconCls="icon-save" onclick="save">确定</a>
</div>
<script type="text/javascript">
	nui.parse();

	var tree = nui.get("tree1");
	var currentNode = null;
	// 业务申请客户类型
    var bizCustType = "<%=request.getParameter("bizCustType") %>";
    // 业务申请Id
    var applyId = "<%=request.getParameter("applyId") %>";
	function reload() {
		$.ajax({
            url: "com.primeton.components.nui.DictLoader2.getDictData.biz.ext",
            type: 'POST',
            data: '{"dictTypeId":"<%=request.getParameter("dicttypeid")%>"}',
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		text.dictList=text.dictList||[];
            		$.each(text.dictList, function(idx,val){
            			//console.log(idx + ',' + val);
            			val.dictid=val.dictid||val.dictID;
            			val.dictname=val.dictname||val.dictName;
            		});
            		tree.setExpandOnLoad(text.dictList.length < 20);//选择项多余20的时候，不展开
            		tree.loadList(text.dictList,"dictid","parentid");
            		//nodeclick({"node":tree.getRootNode().children[0]});
            		<%-- com.bos.pub.dict.getCodeList --%>
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
		});
	//tree.load("com.bos.pub.product.getProductList.biz.ext");
	}
	reload();
	
	function nodeclick(e) {
			currentNode = e.node;
	}
	
	function save(){
		if (!currentNode) {
			nui.alert("请选择一条记录");
			return;
		}
		if (currentNode.children) {
			nui.alert("请选择末级的字典项");
			return;
		}
		var contitle;
		
		if("0204" == currentNode.dictID){
			contitle="担保保证";
		}else if("020403" == currentNode.dictID){
			contitle="保函保证";
		}else if("020404" == currentNode.dictID){
			contitle="备用信用证";
		}else if("020405" == currentNode.dictID){
			contitle="应收账款买断项下的信用保险";
		}else if("020406" == currentNode.dictID){
			contitle="保证人";
		}else if("020407" == currentNode.dictID){
			contitle="履约责任保证保险";
		}else if("020408" == currentNode.dictID){
			contitle="回购";
		}else if("020409" == currentNode.dictID){
			contitle="代偿";
		}else if("020500" == currentNode.dictID){
			contitle="其他担保";
		}
		//如果是保证人（020406）需要录入详细保证人信息
		if("020406" == currentNode.dictID){
			nui.open({
				url: nui.context+"/grt/guaranMainManager/guarantee_contract_guaranteer_add.jsp?sortType="+currentNode.dictID+
				"&subcontractId=<%=request.getParameter("subcontractId") %>&contractId=<%=request.getParameter("contractId") %>",
				title: "新增"+contitle, 
				width: 800, 
				height: 500,
				allowResize:false,
	        	allowDrag: false,
				showMaxButton: false,
				ondestroy: function (action) {
					if(action=="ok"){
						CloseWindow("ok");
					}
				}
			}); 
		}else if("020405" == currentNode.dictID){//如果是应收账款买断项下的信用保险（020405）需要录入详细应收账款买断项下的信用保险信息
			nui.open({
				url: nui.context+"/grt/guaranMainManager/guarantee_contract_creditsafe_add.jsp?sortType="+currentNode.dictID+
				"&subcontractId=<%=request.getParameter("subcontractId") %>&contractId=<%=request.getParameter("contractId") %>",
				title: "新增"+contitle, 
				width: 800, 
				height: 500,
				allowResize:false,
	        	allowDrag: false,
				showMaxButton: false,
				ondestroy: function (action) {
					if(action=="ok"){
						CloseWindow("ok");
					}
				}
			}); 
		}else{
			nui.open({
				url: nui.context+"/grt/guaranMainManager/guarantee_contract_basic_add.jsp?sortType="+currentNode.dictID+
				"&subcontractId=<%=request.getParameter("subcontractId") %>&contractId=<%=request.getParameter("contractId") %>",
				title: "新增"+contitle, 
				width: 800, 
				height: 500,
				allowResize:false,
	    	    allowDrag: false,
				showMaxButton: false,
				ondestroy: function (action) {
					if(action=="ok"){
						CloseWindow("ok");
					}
				}
			}); 
		}
	}
	function CloseWindow(action) {            
		window.CloseOwnerWindow("ok");
	}
</script>
</body>
</html>