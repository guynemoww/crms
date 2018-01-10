<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!-- 
  - Author(s): caozhe@git.com.cn
  - Date: 2014-02-14
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<fieldset>
  	<legend>
    	<span>预警信号类别</span>
    </legend>

<div id="grid1" class="nui-datagrid" style="margin-top: 7px;"  url="com.bos.ews.dataDict.getTreeCodeList.biz.ext" showTreeIcon="true" showPager="false"
	 onshowrowdetail="onShowRowDetail1" onhideRowDetail="onhideRowDetail" showCheckBox="true" checkRecursive="true" dataField="eosDictEntrys" onload="onload">
     <div property="columns"> 
        <div type="expandcolumn" width="18px"></div>
        <div name="dictname" field="dictname">信号名称</div> 
     </div> 
</div>

<div id="editForm1" style="display:none;"> 
    <div id="editTable" class="nui-datagrid" dataField="eosDictEntrys" allowCellSelect="true" allowCellEdit="true" url="com.bos.ews.dataDict.getTreeCodeList.biz.ext"
         allowResize="true" showPager="false" allowCellValid="true" allowRowSelect="true" multiSelect="true" style="margin-top: -8px;margin-bottom: -8px;" oncellbeginedit="cellbeginedit">
	    <div property="columns">
	         <div type="checkcolumn" width="30px">选择</div> 
	         <div field="dictname">信号名称</div>
             <div field="remark" allowSort="true">
             <input property="editor" required="true" class="nui-textarea nui-form-input" style="width:100%;" />预警信号说明</div>
        </div> 
    </div>
</div>
</fieldset>
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
   <div style="float: right;" class="nui-button" iconCls="icon-save" onclick="save()" id="saveData">保存信号</div>
</div>


<fieldset>
  	<legend>
    	<span>待审核预警信号列表(含自动预警信号)</span>
    </legend>
<div id="grid" class="nui-datagrid" style="width:100%;height:auto;margin-top: 7px;" url="com.bos.ews.warnInfo.querySignalList.biz.ext" allowCellSelect="true" allowCellEdit="true"  dataField="csmWarnInfo"
	 sizeList="[10,20,30,40]"  pageSize="20" multiSelect="true" allowResize="true" showReloadButton="false" showPageSize="true" multiSelect="false" sortMode="client">
     <div property="columns">
         <div type="checkcolumn">  </div>
         <div field="csmSignalId" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true"> 预警信号 </div>
         <div field="csmWarningTypeId" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true"> 预警信号类别 </div>
         <div field="signalSourceCd" headerAlign="center" allowSort="true"   dictTypeId="XD_YJCD0001"> 预警信号来源 </div>
         <!-- <div field="launchDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd" > 信号发起日期 </div> -->
         <div field="holdDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd" > 预警信号认定日期 </div>
         <div field="signalStatusCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0002"> 预警信号状态 </div>
         <div field="signalState" headerAlign="center" allowSort="true">
             <input property="editor" class="nui-textarea nui-form-input" required="true" />预警事项描述
         </div>
     </div>
</div>
</fieldset>
<div class="nui-toolbar" style="border-bottom:0;text-align:center;margin-top: 7px;">
     <!-- <div id="saveData1" class="nui-button" iconCls="icon-save" onclick="update()" style="float: left;">修改预警信号说明</div> -->
     <div id="saveData2" class="nui-button" iconCls="icon-remove" onclick="del()" style="float: left;">删除信号</div>
</div>
 
<!-- <div id="level" class="nui-dynpanel" columns="2" style="margin-top:20px;text-align:left;border: 0px;">
     <div style="text-align:left;font-weight: bolder;">是否发起预警级别认定:</div>
     <input id="startHold" class="nui-radiobuttonlist" repeatLayout="table" repeatDirection="vertical"
	         textField="text" valueField="id" dataField="countrys" />
</div> -->

<div id="level" class="nui-dynpanel" columns="4" style="margin-top:20px;text-align:left;border: 0px;">
	 <label class="nui-form-label">认定预警级别/上调级别:</label>
	 <input id="startHold" required="true" name="tbRewCsmEarlyWarning.warningLevelCd"
					class="nui-dictcombobox nui-form-input" dictTypeId="XD_YJJB0001"/>
</div>

<div id="toolbar" class="nui-toolbar" style="margin-top:30px;border-bottom:0;text-align:center;">
	 <input id="tbRewCsmEarlyWarning.partyId" class="nui-hidden nui-form-input" name ="tbRewCsmEarlyWarning.partyId"/>
     <!-- <a class="nui-button" iconCls="icon-save" onclick="startFlow()" id="btnSave" style="float: right;">确认</a> -->
     <a class="nui-button" iconCls="icon-save" onclick="saveAdjustLevel()" id="btnSave" style="float: right;">确认</a>
</div>

<script type="text/javascript">
        nui.parse();
        var bizId = "<%=request.getParameter("bizId") %>";                    //获取传入的业务ID
        var dataObject=[];                                                    //申明表单结果集
        var corpid = "<%=request.getParameter("corpid") %>";                  //客户编号
	    window.editForm1HTML=document.getElementById("editForm1").innerHTML;  //取得editForm1的控件 
		var grid = nui.get("grid");                                           //预警信号信息表单
		var grid1 = nui.get("grid1");                                         //预警信号父级表单
		var type = "<%=request.getParameter("type") %>";                      //获取客户类型
		var monitor = "<%=request.getParameter("monitor") %>";                //检查监测岗进入
		var party = <%=request.getParameter("party") %>;
		var node = <%=request.getParameter("node") %>;
		//alert(bizId + "---" + corpid + "---" + type + "---" + monitor + "---" + party + "---" + node);
		var warnInfos={};                                                     //分类前的预警信号集合
		var fatherWarnInfos=[];                                               //分类后的预警信号集合
		if(monitor == 1){                                                     //判断如果是检查监测岗或者是担保客户的话则不发起级别流程
		 nui.get("level").hide();                                             
		 nui.get("toolbar").hide();
		}
		/* var countrys=[ { "id": "1", "text": "是" },{ "id": "0", "text": "否" }];
		nui.get("startHold").setData(countrys); */
		if(type=="06") nui.get("level").hide();
		var node = <%=request.getParameter("node") %>;
	    var callType = "";    
	      git.mask();
                   // getAdjust();
                    grid.load({partyId:corpid,bizId:bizId});
            	    if(type == "06"){
            	         //alert("担保类信号："+type);
            	         grid1.load({dicttypeid:"XD_YJCD0003",dictid:"23____",partyId:corpid,oneQuery:1});//加载担保类型预警信号
            	         callType = "1";//getAllWarnInfo("23%");
            	          git.unmask();	
            	    }else if(type == "05"){
            	         //alert("集团类信号："+type);
            	         grid1.load({dicttypeid:"XD_YJCD0003" ,dictid:"21____",dictid2:"24",partyId:corpid});//加载集团客户预警信号
            	         callType = "2";//getAllWarnInfo("21%","24");
            	          git.unmask();	
            	    }else{
            	           //alert("公司类信号："+type);
            	           var json=nui.encode({"partyId":"<%=request.getParameter("corpid") %>"});
                           $.ajax({                                                         
                           url: "com.bos.ews.warnInfo.getCorpSize.biz.ext",
                           type: 'POST',
                           data: json,
                           cache: false, 
                           contentType:'text/json',
                           success: function (text) {
                           		  if(text.tbRewCsmEarlyWarning != null && text.tbRewCsmEarlyWarning != "" && text.tbRewCsmEarlyWarning.warningLevelCd != "") {
                           		  	nui.get("startHold").setValue(text.tbRewCsmEarlyWarning.warningLevelCd);
                           		  }
                                  if(text.size == 1){
                                      //alert("加载其他类型预警信号");
                                     grid1.load({dicttypeid:"XD_YJCD0003" ,dictid:"22____",dictid2:"25",partyId:corpid});//加载其他类型预警信号（小企业）
                                     callType = "3";//getAllWarnInfo("22%","25");
                                  }else{
                                    //alert("加载大中型企业预警信号");
                                     //grid1.load({dicttypeid:"XD_YJCD0003" ,dictid:"21____",dictid2:"25",partyId:corpid});//加载大中型企业预警信号（大中企业）
                                     callType = "4";//getAllWarnInfo("21%","25");
                                     grid1.load({dicttypeid:"XD_YJCD0003" ,dictid:"21____",dictid2:"22____",partyId:corpid});
                                  }
                                  git.unmask();	
                           }
	                       });
            	    }

            	    
//父表加载后加载子表               	    
function onload() {
 		if(callType=="1") {
 			getAllWarnInfo("23%");
 		} else if(callType=="2") {
 			getAllWarnInfo("21%","24");
 		} else if(callType=="3") {
 			getAllWarnInfo("22%","25");
 		} else if(callType=="4") {
 			//getAllWarnInfo("21%","25");
 			getAllWarnInfo("21%","22");
 		}
 		git.unmask();
 	}   
 	
//保存认定组别
function saveAdjustLevel(){

	var startHold= nui.get("startHold").getValue();
	//alert(startHold);
	if(startHold=="" || startHold=="null"){
		nui.alert("请按规则填写信息");
		return;
	}

	var rows = grid.findRows(function(row){
		if(row.signalSourceCd ==2) 
			return true;
	}); 
	
	/* var rows = grid.getSelecteds();
	
	alert(grid.size());
	if(rows.length>0 && startHold=="0"){
		alert("不认定级别请删除预警信号！");
        git.unmask();
		return;
	}
	
	if(rows.length==0 && startHold!="0"){
		alert("不认定级别请删除预警信号！");
        git.unmask();
		return;
	} */


	var json=nui.encode({"corpid":"<%=request.getParameter("corpid") %>","ob":rows,"bizId":bizId,"startHold":startHold});
	$.ajax({                                                         
		url: "com.bos.ews.warnInfo.saveSingal.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
                
		   if(startHold==1 || startHold==2 || startHold==3 || startHold==4 || startHold==5){//认定级别
		   //levelPage.jsp
		      var url=nui.context+"/ews/warnDetail/warnInfo/levelPage.jsp?party="+nui.encode(party)+"&bizId="+bizId+"&node="+nui.encode(node);
		      git.go(url);
		   }else{
		      alert(text.msg);
		   }
                               
	        if(text.msg){
	           alert(text.msg);
	        }
	        
            grid.load({partyId:corpid,bizId:bizId});
            initForm();//初始化
   	        git.unmask();
	     },
	     
        error: function (text) {
           nui.alert(text.msg);
           git.unmask();
        }
        
	});        
	git.unmask();
}                 	    
            	    
//发起流程
function startFlow(){

		var startHold= nui.get("startHold").getValue();
		alert(startHold);
		if(startHold=="" || startHold=="null"){
			nui.alert("请按规则填写信息");
        	return;
		}

            //var rows=new Array(); 
            var rows = grid.findRows(function(row){
                       if(row.signalSourceCd ==2) return true;
                       });
            // return;
            //var rows = grid.getSelecteds();
            //var startHold=nui.get("startHold").value;
            /*if(rows.length==0&&startHold==0){                                       //没有选中项，表单不提交。
               alert("请选择要认定的预警信号！");
               git.unmask();
			   return;
            }else{*/
               var json=nui.encode({"corpid":"<%=request.getParameter("corpid") %>","ob":rows,"bizId":bizId,"startHold":startHold,"processInstId":processInstId});
               $.ajax({                                                         
                      url: "com.bos.ews.warnInfo.startWarnInoAddFlow.biz.ext",
                      type: 'POST',
                      data: json,
                      cache: false,
                      contentType:'text/json',
                      success: function (text) {
                               
                               /* if(startHold==1){                               //认定级别
                                  var url=nui.context+"/ews/warnDetail/warnInfo/relevantFile.jsp?party="+nui.encode(party)+"&bizId="+bizId+"&node="+nui.encode(node);
                                  git.go(url);
                               }else{
                                  alert(text.msg);
                               } */
                               
                               if(startHold==1 || startHold==2 || startHold==3 || startHold==4 || startHold==5){                               //认定级别
                                  var url=nui.context+"/ews/warnDetail/warnInfo/relevantFile.jsp?party="+nui.encode(party)+"&bizId="+bizId+"&node="+nui.encode(node);
                                  git.go(url);
                               }else{
                                  alert(text.msg);
                               }
                               
                                    if(text.msg){
                                       alert(text.msg);
                                    }
                               grid.load({partyId:corpid,bizId:bizId});
                               initForm();//初始化
               	               git.unmask();
            	        
                               },
                      error: function (text) {
                               nui.alert(text.msg);
                               git.unmask();
                             }
	                  });
	               //}
	               git.unmask();
      }
	    
/*修改单元格*/	    
function cellbeginedit(e) {
                                                             
	var field = e.column.field;                                              //获取当前单元格的文本对象
	var object = e.sender;                                                   //获取表格对象
	var row = object.getRow(e.rowIndex);                                     //取行号
	var isSelected = object.isSelected(row);                                 //获取选中行
	object.commitEdit();                                                     //提交修改
	if (field != 'remark') {
		if (isSelected == false) {
			row.remark_old=row.remark;
			object.updateRow(row, {remark_old:row.remark_old,remark:''});
		} else {
			if (row.remark_old && row.remark_old.length > 0)
				object.updateRow(row, {remark_old:row.remark_old,remark:row.remark_old});
		}
		return;
	}
	if (isSelected == true){
		e.editor.setEnabled(true);
	}else {
		e.editor.setEnabled(false);
		e.cancel = true;
	}
}

 window.datas={};
 
 /*隐藏明细项*/
 function onhideRowDetail(e) {
            //var parentId=e.record.DICTID;
            var index=e.record._uid;
            //alert("index:"+index);
            var list=nui.get("editTable").getSelecteds();                    
            dataObject[index]=list;
            var data=nui.get("editTable").getData();
            //alert("data:"+e.record.dictid);
            window.datas[e.record.dictid]=data;
 }    
 
 /*展开明细项*/    
 function onShowRowDetail1(e) {
        //alert(nui.encode(dataObject[e.record._uid]));
       
        
        var row = grid1.getSelected() || grid1.getRow(0);                     //获取当前选中父级项
       
        var dicttypeid=row.dicttypeid;                                        //取得所选的字典代码
         
        var dictid=row.dictid;                                                //取得所选的父级项的代码
        //alert("父级码"+dictid);
        //将editForm元素，加入行详细单元格内 
       var editForm = document.getElementById("editForm1");                   //获取子选框表单
        if (!editForm) {                                                      //判断子选框表单是否存在不存在就新建
        	editForm = document.createElement("div");
        	editForm.id="editForm1";
        	editForm.innerHTML=window.editForm1HTML;                          //绘制选项框
        	
        	nui.parse(editForm);
        }
        var td = grid1.getRowDetailCellEl(row);                               //获取详细的DOM对象
         
       
        if (!td){
        	return;
        }
        td.appendChild(editForm);                                             //在当前DOM添加子选项框
        editForm.style.display = "";

       
        /**
           关闭并且再次打开一级菜单时，显示之前勾选的选项.
        */
        var editTable = nui.get("editTable");
        editTable.findRow(function(row){
                  editTable.select(row); 
                 });

        var openObj=dataObject[e.record._uid];
       /*if (typeof(openObj) != "undefined"){
              editTable.findRow(function(row){
                            for(var i=0;i<openObj.length;i++){
                             var select=0;
                             if (typeof(openObj[i]) != "undefined"){
                                if(row.dictid==openObj[i].dictid){
                                     editTable.select(row); 
                                 }
                             }
                          
                            }
                  });
         }
        
        */ 
        //alert("dictid:"+dictid);
        //alert(nui.encode(fatherWarnInfos[dictid]));
        //return;
		if (window.datas[row.dictid]) {
			var data=window.datas[row.dictid];
			editTable.setData(fatherWarnInfos[dictid]);//editTable.setData(data);
			editTable.findRows(function(row){   //逐行遍历 如果有值则设置该框为选中
				if (row.remark)
					editTable.select(row);
			});
		} else {
		    
　　    		editTable.setData(fatherWarnInfos[dictid]);//({dicttypeid:dicttypeid,"dictid":dictid+"____",partyId:corpid,oneQuery:1});
            
		}

}



//加载页面时加载所有的预警信号在内存中。
function getAllWarnInfo(e,f){
          var dictid=e;
          var dictid2=f;
          if(dictid2=="24"||dictid2=="25"){
             dictid2=dictid2+"%";
          }
          if(dictid2=="22"){
             dictid2=dictid2+"%";
          }
          var json=nui.encode({dictid:dictid,dictid2:dictid2,dicttypeid:"XD_YJCD0003"});
                    $.ajax({                                                         
                           url: "com.bos.ews.util.getTreeCode.biz.ext",
                           type: 'POST',
                           data: json,
                           cache: false, 
                           contentType:'text/json',
                           success: function (text) {
                              var name = [];                            //存储指标大类名称
                              warnInfos=text.eosDictEntrys;
                              var i=0;
                              grid1.findRows(function(row){
                              name[i] = row.dictid;
                              fatherWarnInfos[name[i]]=row;
                              i++
                              });
                             // alert(nui.encode("length:"+name.length));
                             //return;
                            for(var a=0;a<name.length;a++){
                               
                                 var list=new Array();
                                 if(name[a]=="24"||name[a]=="25"){
                                    for(var b=0;b<warnInfos.length;b++){
                                        var index = name[a];
                                        var str1=warnInfos[b].dictid;
                                        var str=str1.substring(0,2);
                                        var length=str1.length;
                                        if(str=="24"||str=="25"){
                                          if(str1.length!=2){
                                            if(str1.length!=6){
                                              list.push(warnInfos[b]);
                                           }
                                          }
                                          
                                        }
                                        
                                   }
                                  // alert("list:"+nui.encode(list));
                                 }else{
                                  
                                 for(var b=0;b<warnInfos.length;b++){
                                    var index = name[a];
                                    //var c=warnInfos[b].parentid; 调试用
                                    //var d=fatherWarnInfos[index].dictid; 调试用
                                    if(warnInfos[b].parentid==fatherWarnInfos[name[a]].dictid){
                                      var obj = warnInfos[b];
                                      list.push(warnInfos[b]);
                                    }
                                  }
                                 }
                                  fatherWarnInfos[name[a]]=list;
                                  //alert(nui.encode(fatherWarnInfos[name[a]]));
                              }  //alert(fatherWarnInfos[name[1]].length);
                              
                           }
	                       });
                    

}


/*添加预警信号*/
function save(){
         //git.mask();
         var productArray=new Array(); 
         var result=  grid1.findRow(function(row){
                    if(row._showDetail== true ){
                     return true;
                     }
                  });
               //alert("result:"+nui.encode(result));
               var list=nui.get("editTable").getSelecteds();  //获取表单选中项
            
         if(dataObject.length==0){
              //alert("1");
              //alert("长度："+dataObject.length);
              if(list.length==0){
                       alert("请勾选数据！");
                       return;
               }else{  
                           //alert(2);
                       for(var i=0;i<list.length;i++){
                           //alert(nui.encode(list[i]));
                           productArray.push(list[i]);
                        }
               }
            }else{
               //alert(3);
               if(result){                                         //判断当前客户是否展开分类进行提交数据
               var index=result._uid;                              //获取行号
               dataObject[index]=list;                             //将选中数据存入对应的数组中
               }
              
              }
              var temp;                                       //定义中间数组变量
              /**
                 将数组的值遍历插入集合中
              */
              //alert("dataObject:"+nui.encode(dataObject)); // alert(nui.encode(list));
              //alert("[1]:"+dataObject[1]);
              for(var i =1;i<dataObject.length;i++){
                temp=dataObject[i];
                if (typeof(temp) != "undefined") { 
                   for(var j=0;j<temp.length;j++){
                      if(typeof(temp[j] != "undefined")){
                         var num=temp[j].dictid;
                         productArray.push(temp[j]);
                      }
                  }
                }  
             }
    if (nui.get("editTable").isValid()==false) {
        //alert("false");
        return;
        }       
    if(productArray.length==0){
      alert("请勾选选项！！！");
      return;
     }
    var json=nui.encode({"corpid":"<%=request.getParameter("corpid") %>","ob":productArray,"status":2,"bizId":bizId });
    $.ajax({                                                         
            //url: "com.bos.ews.warnInfo.addWarningInfo.biz.ext",
            url: "com.bos.ews.warnInfo.addSignalSave.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
                       alert(text.msg);
                       grid.load({partyId:corpid,bizId:bizId});
                       //form3.reset();
                       //initForm();//初始化
                       //reload();
               	       git.unmask();
            	       
            },
             error: function (text) {
                //alert("111");
                nui.alert(text.msg);
                git.unmask();
            }
	});
	
}

//刷新頁面
function reload(){
var url=nui.context+"/ews/warnDetail/warnInfo/ews_warnInfo_add.jsp?bizId="+bizId+"&corpid=<%=request.getParameter("corpid") %>&type=<%=request.getParameter("type") %>&flowType=1&monitor=<%=request.getParameter("monitor") %>&party="+nui.encode(party)+"&node="+nui.encode(node);
git.go(url);
}


//保存修改待认定列表中的备注信息。

function update(){
    git.mask();
    var rows= grid.getSelecteds();
    if(rows.length==0){
       alert("请选择要修改的数据！");
       git.unmask();
       return;
     
    }
    var json=nui.encode({warnInfos:rows});
    nui.ajax({
                url: "com.bos.ews.warnInfo.updateWarnInfo.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                    alert(text.msg);
                    inntForm();
                	
                }
    })
    git.unmask();
}


function del(){
    git.mask();
    var rows= grid.getSelecteds();
    if(rows.length==0){
       alert("请选择要删除的信号！");
       git.unmask();
       return;
    }
    /* for(var i=0;i<rows.length;i++){
       if(rows[i].signalStatusCd==2){
       alert("不能删除自动生效信号，请走信号关闭流程！");
       git.unmask();
       return;
       }
    } */
    var json=nui.encode({warnInfos:rows});
    nui.ajax({
                url: "com.bos.ews.warnMgr.delWarnInfo.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                    alert(text.msg);
                    //reload();
                	grid.load({partyId:corpid,bizId:bizId});
                }
    })
    git.unmask();
}
	</script>
</body>
</html>
