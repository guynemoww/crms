<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<!-- 
  - Author(s): windows
  - Date: 2014-06-27 16:38:52
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<title>测试图片</title>
</head>
<body>
	<marquee align="left" behavior="scroll" direction="up" 
    			loop="-1" scrollamount="5" scrolldelay="100" 
    			onMouseOut="this.start()" onMouseOver="this.stop()"
    			style="" height="100%">
	    		<ul id="marketul">
	    		<li><a href="#" onclick="return false;">正在加载…</a></li>
	    		</ul>
    		</marquee>
</body>

<script>
	var json = nui.encode({item:{infoStatus:"1"}});
	$.ajax({
	            url: "com.bos.pub.marketinfo.getItemListAll.biz.ext",
	            type: 'POST',
	            data: json,
	            contentType:'text/json',
	            async: true, //异步处理
	            success: function (mydata) {
	                var o = nui.decode(mydata);
	                var its = o.items;
	                //its = its.sort(function (a, b){
	                //	return parseInt(a.orderno) - parseInt(b.orderno);
	                //});
	                
	                var marketul = $("#marketul");
	                marketul.html("");
	                $.each(its, function(idx,ele){
	                	//alert(nui.encode(ele));
	                	$("<li><img src='../images/view1.png'>&nbsp;&nbsp;</img><a href='#' class='indexAsty' onclick='viewMarketInfo(\""+ele.INFO_ID+"\")' title='"
	                		+ele.INFO_CONTENT+"'>"
	                		+ele.INFO_TITLE+"</a></li>").appendTo(marketul);
	                });
	            }
	});
	
	function viewMarketInfo(id) {
    nui.open({
        url: nui.context+"/pub/marketinfo/item_edit.jsp?itemId="+id+"&view=1",
        title: "查看", 
        width: 800,
		height: 500,
        allowResize:true,
		showMaxButton: true,
        onload: function () {
            var iframe = this.getIFrameEl();
            //iframe.contentWindow.SetData(data);
        },
        ondestroy: function (action) {
            if(action=="ok"){
       	 	}
        }
    });
}
</script>
</html>