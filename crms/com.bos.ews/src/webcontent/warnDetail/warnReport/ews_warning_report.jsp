<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): caozhe@git.com.cn
  - Date: 2014-02-14
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>


<div  style="margin-top:30px;">预警报告</div>
<div  id="grid1" class="nui-datagrid" style="width:100%;height:auto" url="" dataField="items" allowResize="true" sortMode="client" showPager="false">

	<div property="columns">
	    <div type="checkcolumn" ></div>
		<div type="indexcolumn" >序号</div>
		<div field="csmEarlyWarningId" headerAlign="center" allowSort="true" >预警报告编号</div>
		<div field="earlyWarningLevelCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0004">报告名称</div>
		<div field="signalSourceCd" headerAlign="center" allowSort="true" >文档描述</div>
		<div field="launchDate" headerAlign="center" allowSort="true" >创建人</div>
		<div field="confirmDate" headerAlign="center" allowSort="true" >创建日期</div>
		<div field="signalStatusCd" headerAlign="center" allowSort="true" >最新更新人</div>
		<div field="signalState" headerAlign="center" allowSort="true" >更新日期</div>
	</div>
	
</div>


 
    <div   style="width:50%;border-bottom:0;text-align:center;margin-top:30px;float:left;">
	    <a class="nui-button" style="margin-right:15px;"  onclick="save()" id="btnSave">下载</a>
	    <a class="nui-button" style="margin-right:30px; onclick="CloseWindow()">上传</a>
	    <input id="fileupload1" class="nui-fileupload" name="Fdata" limitType="*.txt"flashUrl="swfupload/swfupload.swf"uploadUrl="upload.jsp"
               onuploadsuccess="onUploadSuccess"/>
   </div>
		  

</body>
</html>