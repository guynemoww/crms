package com.bos.aft;

import java.util.ArrayList;
import java.util.List;


import org.apache.commons.collections.CollectionUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.bos.utp.tools.SystemInfo;
import com.eos.engine.component.ILogicComponent;
import com.eos.system.annotation.Bizlet;
import com.primeton.ext.engine.component.LogicComponentFactory;

@Bizlet("")
public class FindTargetData {
	@Bizlet("根据页面名称，文档名称，返回页面对应的指标代码")
	public String[] getTargetData(String pageName,String fileName){
		ArrayList<String> targetNumList=null;
		
		try {
			 targetNumList=this.readXML02(pageName, fileName);
		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		}
		if(null!=targetNumList&&targetNumList.size()>0){
			 String[] targetNums = new String[targetNumList.size()];
			targetNums=targetNumList.toArray(targetNums);
			return targetNums; 
			
		}
		return null;
	}
	 public  ArrayList<String> readXML02(String pageName,String fileName) throws DocumentException{
		  
		  SAXReader sr = new SAXReader();//获取读取方式
		  String xmlPath = SystemInfo.APP_WAR_PATH + "document"
				+ SystemInfo.FILE_SEPARATOR + "xml" +SystemInfo.FILE_SEPARATOR+fileName;//"targetConfig.xml"
		  Document doc = sr.read(xmlPath);//读取xml文件，并且将数据全部存放到Document中//test:"E:\\eos\\ide\\eclipse\\workspace\\crms\\eos-web\\document\\xml\\targetConfig.xml"
		 
		  Element root = doc.getRootElement();//获取根节点
		  
		  List list = root.elements("page");//根据根节点，将根节点下 row中的所有数据放到list容器中。
		  ArrayList<String> targetNumList=new ArrayList<String>();
		  for(Object obj:list){//这种遍历方式，是jdk1.5以上的版本支持的遍历方式
		   Element row = (Element)obj;
		   List list_row = row.elements("pageName");//获取ENAME节点下所有的内容，存入list_row容器中
		   if(CollectionUtils.isNotEmpty(list_row)){
			   Element xmlPageName=(Element)list_row.get(0);
			   if(!xmlPageName.getText().equals(pageName)){
				   continue;
			   }
		   }else{
			   continue;
		   }
		   List list_targetNum=row.elements("targetNum");
		   for(Object objempno:list_targetNum){
		    
		    Element el_empno = (Element)objempno;
		    targetNumList.add(el_empno.getText());
		   // System.out.println(el_empno.getName()+": "+el_empno.getText());//获取节点下的数据。
		    
		   }
		  }
		  return targetNumList;
		 }
	 @Bizlet("根据对应页面的指标项及结果")
	 public Object getIndexResults(String alcInfoId,String [] indexCds,String aldInfoId,String lastAlcInfoId,String show,String loanSummaryId){
	    	
			//获取流程中的业务数据
	    	
					Object[] result = null; 
					// 逻辑构件名称             
					String componentName = "com.bos.aft.dailyInspect";
					// 逻辑流名称 
					String operationName = "queryOptionCards";
					ILogicComponent logicComponent = LogicComponentFactory
					.create(componentName);
	                //逻辑流的输入参数
					Object[] params = new Object[6];
					params[0] = alcInfoId;
					//params[0] ="{'param':{'pfId'"+pfId+"}}";
					params[1]=indexCds;
					params[2]=aldInfoId;
					params[3]=lastAlcInfoId;
					params[4]=show;
					params[5]=loanSummaryId;
					try {
						result= logicComponent.invoke(operationName, params);
						if(null!=result){
							return result[0];
						}else{
							return null;
						}
					} catch (Throwable e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					}
					return null;
			}
	 public static void main(String [] args){
		 try {
			new FindTargetData().readXML02("corpBaseMessage","targetConfig.xml");
		} catch (DocumentException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		 
	 }
}
