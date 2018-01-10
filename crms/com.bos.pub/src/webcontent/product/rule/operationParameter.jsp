<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lujinbin
  - Date: 2013-10-31

  - Description:TB_SYS_TECH_PRODUCT, com.bos.pub.product.TbSysTechProduct-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	
	
	<div class="nui-dynpanel" columns="4" algin="center">
	    <label>业务品种参数：</label>
		<input id="canShu" name="c" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="Oper_Parameter" />

	</div>
	
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">确定</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow('ok')">关闭</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
	    var grid = nui.get("grid1");
 function save() {
       var zhi=nui.get("canShu").getValue();
            if (zhi!="") {
               
               if(zhi=="001"){
                 
                 
                        nui.open({
				                    url: nui.context + "/pub/product/rule/item_edit.jsp?itemId=<%=request.getParameter("itemId") %>&view=<%=request.getParameter("view") %>",
				                    title: "编辑", 
				                    width: 800,
				            		height: 500,
				                    allowResize:true,
				            		showMaxButton: true,
				                    ondestroy: function (action) {
				                        if(action=="ok"){
					                        grid.reload();
					                        
				                   	 	}
				                    }
				                });
                 
                 
               }else if(zhi=="002"){
                   nui.open({
				                    url: nui.context + "/pub/product/rule/item_edit2.jsp?itemId=<%=request.getParameter("itemId") %>&view=<%=request.getParameter("view") %>",
				                    title: "编辑", 
				                    width: 800,
				            		height: 500,
				                    allowResize:true,
				            		showMaxButton: true,
				                    ondestroy: function (action) {
				                        if(action=="ok"){
					                        grid.reload();
				                   	 	}
				                    }
				                });
               
               }else{
               
                 alert("还未开发！");
               }
         
				         
            } else {
                alert("请选中一条记录");
            }
            
               
               
              
               
        }
        

	</script>
</body>
</html>
