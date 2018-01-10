<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 
<head>
<title>
影像集中扫描页面
</title>
<%@include file="/common/nui/common.jsp" %>
</head>

<body onload="SetTransferParams()"  >
        <input id="txtNodeNo" type="text" value="01"  class="nui-hidden" type="hidden" />
        <input id="txtNodeText" type="text" value="身份证"  class="nui-hidden" type="hidden"/>
        <input id="txtLeastNo" type="text" value="2"  class="nui-hidden" type="hidden"/>
        <input id="txtFileFlag" type="text" value=""  class="nui-hidden" type="hidden"/>
        
	   <div class="nui-toolbar" style="border-bottom:0;">
		<a class="nui-button"  onclick="upload()" id="scan"  >上传</a>
		
		</div>
       <object id="obj" classid="clsid:0E6C33D0-5EC1-407d-8800-862AF4554292" codebase="SHCImgOcx.CAB#version=0,0,19,0"  width="100%" height="80%">
    	</object>
    
    
     <script type="text/javascript">
  		nui.parse();
			  var ip ;
			  var port;
			  var user;
			  var pwd;
			  var txtOrg;
			  var txtItemNo;
			  var txtSynType;
			  var timeout;
			  var DocRule;
			  
		function initForm() {
			$.ajax({
		            url: "com.bos.pub.image.readConfig.biz.ext",
		            type: 'POST',
		            cache: false,
		            contentType:'text/json',
		            success: function (text) {
		            	ip=text.conf.ip;
		            	port=text.conf.port;
		            	user=text.conf.user;
		            	pwd=text.conf.pwd;
		            	txtOrg=text.conf.txtOrg;
		            	txtItemNo=text.conf.txtItemNo;
		            	txtSynType=text.conf.txtSynType;
		            	timeout=text.conf.timeout;
		            	DocRule="http://"+text.conf.DocRule+"/UImageWeb/dbinter.jsp";
		            }
			});
		}

	initForm();	
	



//循环保存全部展示区的影像
		function SaveAllItems()
		{
		   var ObjUImg=document.getElementById("obj");
 		   var Objvalue=document.getElementById("txtValue").value;
		    alert('默认全部保存在了'+Objvalue+'盘，更换地址请修改录入的地址');
            var allName=obj.ItemNames; //获取展示区全部影像名称
            var name=new Array();
            name=allName.split("|");
            for(i=0;i<name.length;i++)
            {//循环处理文件
                 obj.SelectedItemName=name[i];//指定显示的文件
                 obj.DoSaveAsImage(Objvalue);//循环另存文件
            }
		}
		
		function SetTransferParams()
		{//默认为2.0缓存服务器
		    var ObjUImg=document.getElementById("obj");
			ObjUImg.ModeFunc ="scanParam|scan|scanReplace|zoomOut|zoomIn|matchWH|matchH|matchW|matchOld|first|next|prev|last|rotateL|rotateR|rotateM|clear|delete|fullScreen|save|print|pdf|note|cut|findBorder|bevel|region|clearRegion|thumbnail|";
			ObjUImg.GetDocRule(DocRule);
		}
		
		
	
		function upload(){
			var uimage=document.getElementById("obj");
			  
			  var FileFlag = uimage.PutFileForUImg(ip,port,timeout,user,pwd,txtOrg,txtItemNo,txtSynType,false,2);
			  
			  var strs= new Array(); 
			 strs = FileFlag.split(";");
		var businessNumber = "<%=request.getParameter("businessNumber") %>";
		var csmNum  ="<%=request.getParameter("csmNum") %>";
		var loanOverId="<%=request.getParameter("loanOverId") %>";
		var json;
		if(loanOverId == 'null' ){
			 json=nui.encode({"strs":strs,"businessNumber":businessNumber,"csmNum":csmNum});
		}else{
			 json=nui.encode({"strs":strs,"businessNumber":businessNumber,"csmNum":csmNum,"loanOverId":loanOverId});
		}
			 $.ajax({
			        url: "com.bos.pub.image.updateImageDocs.biz.ext",
			        type: 'POST',
			        data: json,
			        cache: false,
			        contentType:'text/json',
			        success: function (text) {
			        	if(text.msg){
			        		nui.alert(text.msg);
			        	} else {
			        		CloseWindow("ok");
			        	}
			        },
			        error: function (jqXHR, textStatus, errorThrown) {
			            nui.alert(jqXHR.responseText);
			        }
				});
			 
		}

    </script>
</body>
  
</html>
