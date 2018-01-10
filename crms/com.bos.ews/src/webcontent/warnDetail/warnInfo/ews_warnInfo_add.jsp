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
	    	<span>待审核预警信号列表</span>
	    </legend>
		<div id="grid" class="nui-datagrid" style="width:100%;height:auto;margin-top: 7px;" url="com.bos.ews.warnInfo.queryNotAuditInfo.biz.ext" allowCellSelect="true" allowCellEdit="true"  dataField="csmWarnInfo"
			 sizeList="[10,20,30,40]"  pageSize="20" multiSelect="true" allowResize="true" showReloadButton="false" showPageSize="true" multiSelect="false" sortMode="client">
		     <div property="columns">
		         <div type="checkcolumn">  </div>
		         <div field="csmSignalId" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true"> 预警信号 </div>
		         <div field="csmWarningTypeId" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true"> 预警信号类别 </div>
		         <div field="signalSourceCd" headerAlign="center" allowSort="true"  dictTypeId="XD_YJCD0001"> 预警信号来源 </div>
		         <div field="holdDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd" > 预警信号认定日期 </div>
		         <div field="signalStatusCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0002"> 预警信号状态 </div>
		     </div>
		</div>
	</fieldset>
	
	<div class="nui-toolbar" style="border-bottom:0;text-align:right;margin-top: 7px;">
	     <div id="saveData2" class="nui-button" iconCls="icon-remove" onclick="del()">删除信号</div>
	</div>
	  
	 <input id="tbRewLevelAdjust.oldEarlyWarningLevelCd" name="tbRewLevelAdjust.oldEarlyWarningLevelCd" class="nui-hidden" dictTypeId="XD_YJJB0001"/>
	<div id="form" class="nui-dynpanel" columns="4" style="margin-top:20px;text-align:left;border: 0px;">
<!-- 		 <label class="nui-form-label">原级别：</label>
 -->		 
		 
	     <label class="nui-form-label">认定预警级别/上调级别：</label>
		 <input id="tbRewLevelAdjust.earlyWarningLevelCd" required="true"  name="tbRewLevelAdjust.earlyWarningLevelCd" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YJJB0001"/>
	</div>
	
	<div id="toolbar" class="nui-toolbar" style="margin-top:30px;border-bottom:0;text-align:center;">
		 <input id="tbRewCsmEarlyWarning.partyId" class="nui-hidden nui-form-input" name ="tbRewCsmEarlyWarning.partyId"/>
	     <a class="nui-button" iconCls="icon-save" onclick="startFlow()" id="btnSave" style="float: right;">确认</a>
	</div>

<script type="text/javascript">
        nui.parse();
        var bizId = "<%=request.getParameter("bizId") %>";                    //获取传入的业务ID
        var corpid = "<%=request.getParameter("corpid") %>";                  //客户编号
		var type = "<%=request.getParameter("type") %>";                      //获取客户类型
		var monitor = "<%=request.getParameter("monitor") %>";                //检查监测岗进入
		var party = <%=request.getParameter("party") %>;
		var node = <%=request.getParameter("node") %>;
		var processInstId = "<%=request.getParameter("processInstId") %>";
		var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
		
	    window.editForm1HTML=document.getElementById("editForm1").innerHTML;  //取得editForm1的控件 
		var grid = nui.get("grid");                                           //预警信号信息表单
		var grid1 = nui.get("grid1");                                         //预警信号父级表单
		var form = new nui.Form("#form");
		var warnInfos={};                                                     //分类前的预警信号集合
        var dataObject=[];                                                    //申明表单结果集
		var fatherWarnInfos=[];     
		var oldEarlyWarningLevelCd;                                          //分类后的预警信号集合
		window.datas={};//保存勾选的数据

		var node = <%=request.getParameter("node") %>;
	    var callType = "";    
	    git.mask();
        grid.load({partyId:corpid,bizId:bizId});
    	var json=nui.encode({"partyId":corpid,"bizId":bizId});
        $.ajax({                                                         
           url: "com.bos.ews.warnInfo.getCorpSize.biz.ext",
           type: 'POST',
           data: json,
           cache: false, 
           contentType:'text/json',
           success: function (text) {
         	  nui.get("tbRewLevelAdjust.earlyWarningLevelCd").setValue(text.tbRewLevelAdjust.earlyWarningLevelCd);
      		  nui.get("tbRewLevelAdjust.oldEarlyWarningLevelCd").setValue(text.tbRewCsmEarlyWarning.warningLevelCd);
      		  if(type == "06"){
       		  //加载担保类型预警信号
                grid1.load({dicttypeid:"XD_YJCD0003",dictid:"23____",partyId:corpid,oneQuery:1});
       	  		callType = "1";
      		  } else if(type == "05"){
     	      	 //加载集团客户预警信号
        	         grid1.load({dicttypeid:"XD_YJCD0003" ,dictid:"21____",dictid2:"24",partyId:corpid});
        	         callType = "2";
           	  } else {
           		//加载其他类型预警信号（小企业）
                  if(text.size == 1){
                     grid1.load({dicttypeid:"XD_YJCD0003" ,dictid:"22____",dictid2:"25",partyId:corpid});
                     callType = "3";
                  }else{
                     callType = "4";
                     grid1.load({dicttypeid:"XD_YJCD0003" ,dictid:"21____",dictid2:"22____",partyId:corpid});
                  }
      		  }
               git.unmask();
           }
        });
            	    
	//获取预警信号的数量---由于grid load数据和warningLevelCheck执行顺序的问题 需要去后台查询
	function getCount(){
		 $.ajax({                                                         
                  url: "com.bos.ews.warnInfo.queryNotAuditInfo.biz.ext",
                  type: 'POST',
                  data: json,
                  cache: false,
                  contentType:'text/json',
                  success: function (text) {
                	 warningLevelCheck(text.total);
                   },
                  error: function (text) {
                       nui.alert(text.msg);
                   }
               });
	}
	  //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
	if("1" != proFlag){
		nui.get("btnSave").hide();
		nui.get("saveData").hide();
		nui.get("saveData2").hide();
		form.setEnabled(false);
	}else{
		getCount();
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
 			getAllWarnInfo("21%","22");
 		}
 		git.unmask();
 	}               	    
            	    
		//发起流程
		function startFlow(){
			var oldEarlyWarningLevelCd = nui.get("tbRewLevelAdjust.oldEarlyWarningLevelCd").getValue();
			var earlyWarningLevelCd = nui.get("tbRewLevelAdjust.earlyWarningLevelCd").getValue();
			
			form.validate();
	        if (form.isValid()==false){
	        	 alert("请按规则填写信息！");
	        	 return;
	        }

			var rows = grid.getRow(0);
	        if( (earlyWarningLevelCd=="" || earlyWarningLevelCd=="0") && typeof(rows) == "undefined"){
			    alert("请选择要增加的信号或更改预警级别！");
			    return;
		    }
	        
	        //1>2>3>4>5>0不认定
	        if(oldEarlyWarningLevelCd == ""  || oldEarlyWarningLevelCd == null){
	         	oldEarlyWarningLevelCd = "0";
	        }
	        if(oldEarlyWarningLevelCd != "0" && earlyWarningLevelCd != "0" && earlyWarningLevelCd > oldEarlyWarningLevelCd) {
		    	if(oldEarlyWarningLevelCd == "0"){
		    		oldEarlyWarningLevelCd = "不认定";
		    	}else{
		    		if(oldEarlyWarningLevelCd=="1"){
		    			oldEarlyWarningLevelCd = "红色预警";
		    		}
		    		if(oldEarlyWarningLevelCd=="2"){
		    			oldEarlyWarningLevelCd = "橙色预警";
		    		}
		    		if(oldEarlyWarningLevelCd=="3"){
		    			oldEarlyWarningLevelCd = "黄色预警";
		    		}
		    		//oldEarlyWarningLevelCd = oldEarlyWarningLevelCd + "级";
		    	}
			    alert("只能调高级别或不认定，原级别【"+oldEarlyWarningLevelCd+"】！");
			    return;
		    }
	        
	        var rows = grid.findRows(function(row){
	                   if(row.signalSourceCd ==2)
	                	   return true;
	           		});
        
           var json=nui.encode({"corpid":corpid,"warnInfos":rows,"bizId":bizId,"earlyWarningLevelCd":earlyWarningLevelCd,"processInstId":processInstId});
           $.ajax({                                                         
                  url: "com.bos.ews.warnInfo.startWarnInoAddFlow.biz.ext",
                  type: 'POST',
                  data: json,
                  cache: false,
                  contentType:'text/json',
                  success: function (text) {
                	  if(text.msg == "success"){
                		  alert("保存成功！");
                		  if( earlyWarningLevelCd!="0"){
	                		 //认定级别,自动进入认定报告页面
	                          var url=nui.context+"/ews/warnDetail/warnInfo/relevantFile.jsp?party="+nui.encode(party)+"&bizId="+bizId+"&node="+nui.encode(node);
	                          git.go(url);
                		  }else{
                			  grid.load({partyId:corpid,bizId:bizId});
                		  }
                	  }else{
                		  alert("保存失败");
                		  return;
                	  }
                   },
                  error: function (text) {
                       nui.alert(text.msg);
                   }
               });
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

 
	 /*隐藏明细项*/
	 function onhideRowDetail(e) {
            var index=e.record._uid;
            var list=nui.get("editTable").getSelecteds();                    
            dataObject[index]=list;
            var data=nui.get("editTable").getData();
            window.datas[e.record.dictid]=data;
 	}    
 
	 /*展开明细项*/    
	 function onShowRowDetail1(e) {
        var row = grid1.getSelected() || grid1.getRow(0);                     //获取当前选中父级项
        var dicttypeid=row.dicttypeid;                                        //取得所选的字典代码
        var dictid=row.dictid;                                                //取得所选的父级项的代码
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
		if (window.datas[row.dictid]) {
			var data=window.datas[row.dictid];
			editTable.setData(fatherWarnInfos[dictid]);//editTable.setData(data);
			editTable.findRows(function(row){   //逐行遍历 如果有值则设置该框为选中
				if (row.remark)
					editTable.select(row);
			});
		} else {
　　    		editTable.setData(fatherWarnInfos[dictid]);
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
                                 }else{
                                  
                                 for(var b=0;b<warnInfos.length;b++){
                                    var index = name[a];
                                    if(warnInfos[b].parentid==fatherWarnInfos[name[a]].dictid){
                                      var obj = warnInfos[b];
                                      list.push(warnInfos[b]);
                                    }
                                  }
                                 }
                                  fatherWarnInfos[name[a]]=list;
                              } 
                           }
	                       });
                    
		}


	/*添加预警信号*/
	function save(){
         var productArray=new Array(); 
         var result = grid1.findRow(function(row){
                    if(row._showDetail== true ){
                     return true;
                     }
                  });
         
         var list=nui.get("editTable").getSelecteds();  //获取表单选中项
            
         if(dataObject.length==0){
              if(list.length==0){
                    alert("请勾选数据！");
                    return;
               }else{  
                    for(var i=0;i<list.length;i++){
                        productArray.push(list[i]);
                    }
               }
           }else{
               if(result){                                         //判断当前客户是否展开分类进行提交数据
	               var index=result._uid;                              //获取行号
	               dataObject[index]=list;                             //将选中数据存入对应的数组中
               }
            }
             var temp;                                       //定义中间数组变量
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
		        return;
	         }       
		    if(productArray.length==0){
			      alert("请勾选选项！！！");
			      return;
		     }
		    
		    //保存数据
		    var json=nui.encode({"corpid":corpid,"warnInfos":productArray,"status":4,bizId:bizId });
		    $.ajax({                                                         
	            url: "com.bos.ews.warnInfo.addWarningInfo.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
                      //alert(text.msg);
                      grid.load({partyId:corpid,bizId:bizId});
              	      git.unmask();
              	      getCount();
	            },
	             error: function (text) {
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
	    for(var i=0;i<rows.length;i++){
	       if(rows[i].signalStatusCd==2){
		       alert("不能删除生效信号，请走信号关闭流程！");
		       git.unmask();
		       return;
	       }
	       if(rows[i].signalStatusCd==3){
		       alert("已关闭的信号无法删除！");
		       git.unmask();
		       return;
	       }
	       if(rows[i].signalStatusCd==5){
		       alert("关闭审核的信号无法删除！");
		       git.unmask();
		       return;
	       }
	    }
	    
	    var json=nui.encode({warnInfos:rows});
	    nui.ajax({
              url: "com.bos.ews.warnMgr.delWarnInfo.biz.ext",
              data:json,
              type:"POST",
              contentType:'text/json',
              async : false,
              success: function (text) {
                  //alert(text.msg);
              	  grid.load({partyId:corpid,bizId:bizId});
              	  getCount();
              }
	    });
	    git.unmask();
	}
	
	 function warningLevelCheck(count){
        if(count>=3&&count<5) {
        	alert("风险预警级别提示[黄色预警]");
            //nui.get("tbRewLevelAdjust.earlyWarningLevelCd").setValue("3");
        }
        if(count>=5&&count<7) {
            alert("风险预警级别提示[橙色预警]");
            //nui.get("tbRewLevelAdjust.earlyWarningLevelCd").setValue("2");
        }
        if(count>=7) {
            alert("风险预警级别提示[红色预警]");
            //nui.get("tbRewLevelAdjust.earlyWarningLevelCd").setValue("1");
        }
        if(count<3) {
        	//nui.get("tbRewLevelAdjust.earlyWarningLevelCd").setValue("0");
        }
    }
   
	</script>
</body>
</html>
