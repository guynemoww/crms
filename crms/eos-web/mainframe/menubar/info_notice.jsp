<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-06-26
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<marquee align="left" behavior="scroll" direction="up" 
	loop="-1" scrollamount="5" scrolldelay="100" 
	onMouseOut="this.start()" onMouseOver="this.stop()"
	style="" height="100%">
	<ul id="noticeul">
	<li><a href="#" onclick="return false;">正在加载…</a></li>
	</ul>
</marquee>
    <script type="text/javascript">
	 	nui.parse();
	git.mask();
	var json = nui.encode({item:{infoStatus:"1"}});
	$.ajax({
	            url: "com.bos.pub.noticeinfo.getItemListAll.biz.ext",
	            type: 'POST',
	            data: json,
	            contentType:'text/json',
	            async: true, //异步处理
	            success: function (mydata) {
	                git.unmask();
	                var o = nui.decode(mydata);
	                var its = o.items;
	                
	                var noticeul = $("#noticeul");
	                noticeul.html("");
	                $.each(its, function(idx,ele){
	                	//alert(nui.encode(ele));
	                	$("<li><a href='#' onclick='viewNoticeInfo(\""+ele.INFO_ID+"\")' title='"
	                		+ele.INFO_CONTENT+"'>"
	                		+ele.INFO_TITLE+"</a></li>").appendTo(noticeul);
	                });
	            }
	});
	
function viewNoticeInfo(id) {
    nui.open({
        url: nui.context+"/pub/noticeinfo/item_edit.jsp?itemId="+id+"&view=1",
        title: "查看", 
        width: 800,
		height: 500,
        allowResize:true,
		showMaxButton: true,
        onload: function () {
            var iframe = this.getIFrameEl();
            //iframe.contentWindow.SetData(data);
            //this.max();
        },
        ondestroy: function (action) {
            if(action=="ok"){
       	 	}
        }
    });
}
	</script>
</body>
</html>
