<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="commonj.sdo.DataObject"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lujinbin
  - Date: 2013-10-31
  - Description:TB_SYS_PRODUCT, com.bos.pub.product.TbSysProduct
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>

<body onload="SetTransferParams()" onmousewheel="return MocseWheel()" >


    <a class="nui-button" onclick="next()" id="next">查看下条影像文档</a>
    <a class="nui-button" onclick="pdflook()">查看PDF格式影像</a>
	<br />
    <object id="ObjUImage" width="100%" height="90%" classid="clsid:17E03D90-5299-474b-A5F0-9CCC0BB094F1"  onmouseout="return ObjMouseOut()" onmouseover="return ObjMocseOver()" >
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
		            	DocRule="http://"+text.conf.DocRule+"/UImageWeb/";
		            }
			});
		}

	initForm();	
	  
		var rowId  ="<%=request.getParameter("rowId") %>";
		var datas;
		var imageDocumentNumber="<%=request.getParameter("imageDocumentNumber") %>";
		function SetData(data) {
			 data = nui.clone(data);
	          datas = data;
		}
    	function next(){
    		rowId= (Number)(rowId)+1;
		 	for(var i =0;i<datas.length;i++){
		 		if(i+1==rowId ){
		 			if(datas[i].imageDocumentNumber == null){
		 				rowId=rowId+1;
				 		//alert(rowId);
		 				continue;
		 			}
		 			imageDocumentNumber =datas[i].imageDocumentNumber;
		 		
					var ObjUImg=document.getElementById("ObjUImage");
					 ObjUImg.RemoveAll();		    		

		            var fileflag=document.getElementById("fileflag");
				    ObjUImg.ShowImageByFileFlag(ip,port,timeout,user,pwd,txtOrg,txtItemNo, imageDocumentNumber);//下载套号
				 	
		 			return;
		 		}
		 	}
    	}	
    		
		function ShowImageByFileFlag()
		{//下载全部
		    var ObjUImg=document.getElementById("ObjUImage");
            var fileflag=document.getElementById("fileflag");
		    ObjUImg.ShowImageByFileFlag(ip, port,timeout,user,pwd,txtOrg,txtItemNo, "<%=request.getParameter("imageDocumentNumber") %>");//下载套号
		    //ObjUImg.DisplayItemNote();
	    }
		function ShowImageByFileSeq()
		{//下载单张
		    var ObjUImg=document.getElementById("ObjUImage");
            var fileflag=document.getElementById("fileflag");
		    ObjUImg.ShowImageByFileSeq(ip, port,timeout,user,pwd,txtOrg,txtItemNo,fileflag.value,seq.value);//单文件下载
		}
		 	
		function SetTransferParams()
		{//默认为2.0缓存服务器
		    var ObjUImg=document.getElementById("ObjUImage");
		    ObjUImg.SetUImageWebMode(true);
			ObjUImg.SetUImageWebURL(DocRule);
		    ObjUImg.ModeFunc ="zoomOut|zoomIn|matchWH|matchH|matchW|matchOld|first|next|prev|last|rotateL|rotateR|rotateM|clear|delete|light|comparison|gray|black|fullScreen|save|print|pdf|note|cut|findBorder|bevel|region|clearRegion|thumbnail|";
		  
			//相应说明：       参数     |采集|替换采集   |放大   |缩小  |自适应匹配|高匹配|宽匹配|原大小|第一张|前一张|后一张|最后张|逆90度|顺90度|水平|清空|删除|亮度 |对比度    |灰度|黑白图|全屏     |保存|打印|生成PDF|批注|裁剪|裁边  |纠偏  |焦点 |清焦       |缩略图。
		    ObjUImg.SetTransferParams("1.4");
		    //ObjUImg.ShowImageByFileSeqL("C:");
		   // ShowImageByFileFlag();
		}
		function SaveAllItems()
		{
		    var ObjUImg=document.getElementById("ObjUImage");
		    alert('默认全部保存在了C盘，更换地址请修改录入的地址');
            var allName=ObjUImage.ItemNames; //获取展示区全部影像名称
            var name=new Array();
            name=allName.split("|");
            for(i=0;i<name.length;i++)
            {//循环处理文件
                 ObjUImage.SelectedItemName=name[i];//指定显示的文件
                 ObjUImage.DoSaveAsImage('C:\\');//循环另存文件
            }
		}
		
	var isobj = false;
    function MocseWheel()
    {
         if(isobj == true)
         {
              return true;
         }
         else
         {
              return false;
         }
    }
    function ObjMouseOut(){
        window.isobj = true;
    }
    function ObjMocseOver(){
        window.isobj = false; 
    }
	ShowImageByFileFlag();
	
	function pdflook(){
        var url = "http://"+DocRule+"/UImageWeb/Service/PDF/"+txtItemNo+"+imageDocumentNumber;
        window.open(url,"_blank");
	}	
    </script>
</body>
  
</html>
