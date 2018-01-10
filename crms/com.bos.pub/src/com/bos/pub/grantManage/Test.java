package com.bos.pub.grantManage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.bos.pub.ExcelFileReader;

/*
 * 生成人员，机构，角色，岗位sql脚本
 */
public class Test {

	private static Map<String,String>  map = new HashMap<String,String>();
	
	private static Map<String,String>  pmap = new HashMap<String,String>();
	
	private static Map<String,String>  gmap = new HashMap<String,String>();
	
	private  final String readFilePath="E:\\工作资料\\机构人员\\自然人名单.xls";
	private  final String writeFilePath="E:\\工作资料\\机构人员\\对公初始化人员\\";
	private static int empid=1000600;
	
	public Test(){
		//初始化角色参数
		map.put("总行系统管理员","eosadmin");
		map.put("机构管理员","orgadmin");
		map.put("应用管理员","appadmin");
		map.put("分行系统管理员","R1005");
		map.put("公司客户经理","R1006");
		map.put("零售客户经理","R1007");
		map.put("同业客户经理","R1003");
		map.put("资产保全客户经理","R1008");
		map.put("支行行长助理","R1009");
		map.put("支行行长","R1010");
		map.put("支行团队负责人","R1011");
		map.put("管理部总经理","R1012");
		map.put("管理部副总经理","R1013");
		map.put("管理部总经理助理","R1014");
		map.put("监控官","R1015");
		map.put("押品管理员","R1018");
		map.put("授信审批部总经理","R1019");
		map.put("授信审批部副总经理","R1020");
		map.put("授信审批部总经理助理","R1021");
		map.put("独立授信审批官","R1022");
		map.put("国际业务部总经理","R1024");
		map.put("国际业务部副总经理","R1025");
		map.put("国际业务部总经理助理","R1026");
		map.put("企业金融部总经理","R1027");
		map.put("企业金融部副总经理","R1028");
		map.put("企业金融部总经理助理","R1029");
		map.put("零售银行部总经理","R1030");
		map.put("零售银行部副总经理","R1031");
		map.put("零售银行部总经理助理","R1032");
		map.put("金融市场部总经理","R1033");
		map.put("金融市场部副总经理","R1034");
		map.put("金融市场部总经理助理","R1035");
		map.put("合规专员","R1036");
		map.put("董事长","R1037");
		map.put("总行行长","R1038");
		map.put("总行副行长","R1039");
		map.put("总行行长助理","R1040");
		map.put("分行行长","R1041");
		map.put("分行副行长","R1042");
		map.put("分行行长助理","R1043");
		map.put("管理部管理员","R1044");
		map.put("影像质量控制员","R1046");
		map.put("内控稽核员","R1047");
		map.put("档案管理员","R1048");
		map.put("票据中心总经理","R1050");
		map.put("票据中心副总经理","R1052");
		map.put("票据中心总经理助理","R1053");
		map.put("信息维护员","R2002");
		map.put("小微客户经理","R1002");
		map.put("运维管理员","R1054");
		map.put("资产保全中心副总经理","R1056");

		//初始化岗位参数
		pmap.put("客户经理岗","1");
		pmap.put("放款审核岗","2");
		pmap.put("集团联保认定岗","3");
		pmap.put("押品管理岗","4");
		pmap.put("系统管理岗","5");
		pmap.put("行长岗","14");
		pmap.put("独立审批岗","15");
		pmap.put("公共独立审批岗","16");
		pmap.put("授信审查委员会","17");
		pmap.put("贷后跟踪岗","18");
		pmap.put("管理部负责人","19");
		pmap.put("评级认定岗","20");
		pmap.put("资产保全客户经理岗","22");
		pmap.put("资产保全管理岗","23");
		pmap.put("分类岗","24");
		pmap.put("金融市场客户经理岗","25");
		pmap.put("分类委员会","26");
		pmap.put("金融市场部负责人","28");
		pmap.put("业务受理岗","29");
		pmap.put("初次审批岗","30");
		pmap.put("首席独立审批岗","31");


		//初始化机构参数
		gmap.put("绵阳银行总行","10000");
		gmap.put("绵阳分行","10001");
		gmap.put("绵阳分行计财部","10002");
		gmap.put("绵阳分行营业部","10003");
		gmap.put("绵阳分行滨江支行","10004");
		gmap.put("绵阳分行开发区支行","10005");
		gmap.put("绵阳分行白水湖支行","10006");
		gmap.put("绵阳分行三里街支行","10007");
		gmap.put("绵阳分行光华支行","10008");
		gmap.put("绵阳分行湓浦支行","10009");
		gmap.put("绵阳分行甘棠支行","10010");
		gmap.put("绵阳分行柴桑支行","10011");
		gmap.put("绵阳分行大校场支行","10012");
		gmap.put("绵阳分行大中路支行","10013");
		gmap.put("绵阳分行振劳支行","10014");
		gmap.put("绵阳分行滨兴支行","10015");
		gmap.put("绵阳分行长江支行","10016");
		gmap.put("绵阳分行公园支行","10017");
		gmap.put("绵阳分行九龙支行","10018");
		gmap.put("绵阳分行长虹支行","10019");
		gmap.put("绵阳分行十里支行","10020");
		gmap.put("绵阳分行庐山支行","10021");
		gmap.put("绵阳分行瑞昌支行","10022");
		gmap.put("瑞昌支行湓城支行","10023");
		gmap.put("绵阳分行德安支行","10024");
		gmap.put("德安支行蒲亭支行","10025");
		gmap.put("绵阳分行修水支行","10026");
		gmap.put("修水支行城北支行","10027");
		gmap.put("绵阳分行永修支行","10028");
		gmap.put("永修支行建昌支行","10029");
		gmap.put("绵阳分行沙河支行","10030");
		gmap.put("沙河支行江州支行","10031");
		gmap.put("绵阳分行都昌支行","10032");
		gmap.put("绵阳分行彭泽支行","10033");
		gmap.put("绵阳分行武宁支行","10034");
		gmap.put("绵阳分行浔阳支行","10035");
		gmap.put("绵阳分行浔东支行","10036");
		gmap.put("绵阳分行共青支行","10037");
		gmap.put("绵阳分行星子支行","10038");
		gmap.put("绵阳分行湖口支行","10039");
		gmap.put("绵阳分行八里湖支行","10040");
		gmap.put("绵阳银行票据中心","10041");
		gmap.put("绵阳银行企业金融部","10042");
		gmap.put("绵阳湓浦分中心","20013");
		gmap.put("绵阳营业部分中心","20014");
		gmap.put("绵阳武宁分中心","20015");
		gmap.put("绵阳永修分中心","20016");
		gmap.put("绵阳瑞昌分中心","20017");
		gmap.put("绵阳德安分中心","20018");
		gmap.put("绵阳修水分中心","20019");
		gmap.put("零售银行管理总部.业务营销中心","10155");
		gmap.put("景德镇分行","10043");
		gmap.put("景德镇分行营业部","10044");
		gmap.put("景德镇分行乐平支行","10045");
		gmap.put("景德镇分行昌南支行","10046");
		gmap.put("景德镇分行珠山支行","10047");
		gmap.put("景德镇分行新厂支行","10048");
		gmap.put("景德镇分行洎阳支行","10049");
		gmap.put("景德镇乐平分中心","20037");
		gmap.put("景德镇营业部分中心","20038");
		gmap.put("景德镇分行计财部","22");
		gmap.put("景德镇分行浮梁支行","61");
		gmap.put("赣州分行","10050");
		gmap.put("赣州分行营业部","10051");
		gmap.put("赣州分行赣县支行","10052");
		gmap.put("赣州分行于都支行","10053");
		gmap.put("赣州分行信丰支行","10054");
		gmap.put("赣州分行企业业务部一部","10055");
		gmap.put("赣州分行企业业务部二部","10056");
		gmap.put("赣州分行企业业务部三部","10057");
		gmap.put("赣州分行企业业务部四部","10058");
		gmap.put("赣州分行企业业务部五部","10059");
		gmap.put("赣州分行企业业务部六部","10060");
		gmap.put("赣州分行企业业务部七部","10061");
		gmap.put("赣州营业部分中心","20035");
		gmap.put("赣州分行企业金融部","201");
		gmap.put("赣州分行计财部","23");
		gmap.put("赣州分行宁都支行","301");
		gmap.put("赣州分行瑞金支行","10157");
		gmap.put("赣州分行全南支行","10158");
		gmap.put("萍乡分行","10062");
		gmap.put("萍乡分行营业部","10063");
		gmap.put("萍乡分行企业业务部一部","10064");
		gmap.put("萍乡分行企业业务部二部","10065");
		gmap.put("萍乡分行企业业务部三部","10066");
		gmap.put("萍乡分行重点客户中心","10067");
		gmap.put("萍乡分行莲花支行","10068");
		gmap.put("萍乡分行芦溪支行","182");
		gmap.put("萍乡营业部分中心","20040");
		gmap.put("萍乡分行计财部","21");
		gmap.put("萍乡分行企业业务部四部","221");
		gmap.put("萍乡分行湘东支行","10156");
		gmap.put("上饶分行","10069");
		gmap.put("上饶分行企业业务部六部","1");
		gmap.put("上饶分行营业部","10070");
		gmap.put("上饶分行万年支行","10071");
		gmap.put("上饶分行企业业务部二部","10072");
		gmap.put("上饶分行企业业务部四部","10073");
		gmap.put("上饶分行余干支行","10074");
		gmap.put("上饶分行广丰支行","10075");
		gmap.put("上饶营业部分中心","20021");
		gmap.put("上饶分行计财部","24");
		gmap.put("宜春分行","10076");
		gmap.put("宜春分行营业部","10077");
		gmap.put("宜春分行高安支行","10078");
		gmap.put("宜春分行丰城支行","10079");
		gmap.put("宜春分行樟树支行","10080");
		gmap.put("宜春分行上高支行","10081");
		gmap.put("宜春分行袁州支行","10082");
		gmap.put("宜春分行奉新支行","10083");
		gmap.put("宜春分行万载支行","10084");
		gmap.put("宜春分行零售银行部","10085");
		gmap.put("宜春分行国际业务部","121");
		gmap.put("宜春分行资产保全中心","122");
		gmap.put("宜春分行企业金融部","181");
		gmap.put("宜春营业部分中心","20026");
		gmap.put("宜春高安分中心","20027");
		gmap.put("宜春上高分中心","304");
		gmap.put("宜春丰城分中心","20028");
		gmap.put("宜春樟树分中心","20029");
		gmap.put("宜春分行计财部","26");
		gmap.put("广州分行","10086");
		gmap.put("广州分行营业部","10087");
		gmap.put("广州分行荔湾支行","10088");
		gmap.put("广州分行海珠支行","10089");
		gmap.put("广州分行广园支行","10090");
		gmap.put("广州分行环市支行","10091");
		gmap.put("广州分行江南支行","10092");
		gmap.put("广州分行企业业务部一部","10093");
		gmap.put("广州分行企业业务部二部","10094");
		gmap.put("广州分行企业业务部三部","10095");
		gmap.put("广州分行企业业务部四部","10096");
		gmap.put("广州分行企业业务部五部","10097");
		gmap.put("广州分行企业业务部六部","10098");
		gmap.put("广州分行支行筹建一组","10099");
		gmap.put("广州分行企业金融部","101");
		gmap.put("广州分行海印支行","10100");
		gmap.put("广州营业部分中心","20002");
		gmap.put("广州广园分中心","20003");
		gmap.put("广州分行番禺支行","241");
		gmap.put("广州分行计财部","25");
		gmap.put("广州分行支行筹建二组","264");
		gmap.put("合肥分行","10101");
		gmap.put("合肥分行计财部","10102");
		gmap.put("合肥分行营业部","10103");
		gmap.put("合肥分行瑶海支行","10104");
		gmap.put("合肥分行望江西路支行","10105");
		gmap.put("合肥分行庐江支行","10106");
		gmap.put("合肥分行包河支行","10107");
		gmap.put("合肥分行屯溪路支行","10108");
		gmap.put("合肥分行金潜支行","10109");
		gmap.put("合肥分行企业业务部二部","10110");
		gmap.put("合肥分行企业业务部四部","10111");
		gmap.put("合肥分行企业业务部六部","10112");
		gmap.put("合肥望江西路分中心","20005");
		gmap.put("合肥营业部分中心","20006");
		gmap.put("合肥庐江分中心","20007");
		gmap.put("合肥分行肥西支行","10159");
		gmap.put("吉安分行","10113");
		gmap.put("吉安分行计财部","10114");
		gmap.put("吉安分行营业部","10115");
		gmap.put("吉安分行泰和支行","10116");
		gmap.put("吉安分行安福支行","10117");
		gmap.put("吉安分行吉州支行","10118");
		gmap.put("吉安分行吉水支行","10119");
		gmap.put("吉安分行新干支行","10120");
		gmap.put("吉安分行永新支行","10121");
		gmap.put("吉安分行青原支行","10122");
		gmap.put("吉安分行永丰支行","10123");
		gmap.put("吉安分行广场支行","10124");
		gmap.put("吉安分行企业业务一部","10125");
		gmap.put("吉安分行企业业务二部","10126");
		gmap.put("吉安分行企业业务三部","10127");
		gmap.put("吉安分行企业金融部","10128");
		gmap.put("吉安分行遂川支行","10129");
		gmap.put("吉安分行吉安支行","10130");
		gmap.put("吉安泰和分中心","20031");
		gmap.put("吉安安福分中心","20032");
		gmap.put("吉安营业部分中心","20033");
		gmap.put("抚州分行","10131");
		gmap.put("抚州分行计财部","10132");
		gmap.put("抚州分行营业部","10133");
		gmap.put("抚州分行崇仁支行","10134");
		gmap.put("抚州分行临川支行","10135");
		gmap.put("抚州分行南丰支行","10136");
		gmap.put("抚州分行金溪支行","10137");
		gmap.put("抚州分行乐安支行","10138");
		gmap.put("抚州分行东乡支行","10139");
		gmap.put("抚州分行黎川支行","10140");
		gmap.put("抚州分行广昌支行","10141");
		gmap.put("抚州南丰分中心","20023");
		gmap.put("抚州营业部分中心","20024");
		gmap.put("南昌分行","10142");
		gmap.put("南昌分行阳明支行","10143");
		gmap.put("南昌分行洪城支行","10144");
		gmap.put("南昌分行南昌县支行","10145");
		gmap.put("南昌分行洪都大道支行","10146");
		gmap.put("南昌分行进贤县支行","10147");
		gmap.put("南昌分行高新支行","10148");
		gmap.put("南昌分行营业部","10149");
		gmap.put("南昌分行新建县支行","10150");
		gmap.put("南昌分行青云支行","10151");
		gmap.put("南昌分行安义县支行","10152");
		gmap.put("南昌分行八一支行","10153");
		gmap.put("南昌分行北京东路支行","141");
		gmap.put("南昌分行企业金融部","161");
		gmap.put("南昌南昌县分中心","20009");
		gmap.put("南昌红谷滩分中心","20010");
		gmap.put("南昌阳明路分中心","20011");
		gmap.put("绵阳银行总行计财部","10154");
		gmap.put("广东自贸实验区南沙支行","261");
		gmap.put("广东自贸实验区南沙支行营业部","262");
		gmap.put("广东自贸实验区南沙支行企业业务部一部","263");
		gmap.put("新余分行","41");
		gmap.put("新余分行企业金融部","222");
		gmap.put("新余分行营业部","42");
		gmap.put("新余分行企业业务部一部","43");
		gmap.put("新余分行企业业务部二部","44");
		gmap.put("新余分行企业业务部三部","45");
		gmap.put("新余分行抱石公园小微支行","305");
		gmap.put("新余营业部分中心","10160");
		gmap.put("鹰潭分行","62");
		gmap.put("鹰潭营业部分中心","282");
		gmap.put("鹰潭分行营业部","63");
		gmap.put("鹰潭分行计划财务部","64");
		gmap.put("鹰潭分行企业业务部一部","81");
		gmap.put("鹰潭分行企业业务部二部","82");
		gmap.put("鹰潭营业部分中心","282");
		

	}
	
	/**
	 * 读取excel
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws ParseException
	 * @throws InstantiationException
	 */
	public  Map readExcel() throws IllegalArgumentException, IOException, IllegalAccessException, ParseException, InstantiationException{
		
		
		Map<String,Object>  map = ExcelFileReader.readExcelList(this.readFilePath, null);
		
		return map;
		
	}
	
	/*
	 * 追加往文件中写入字符串
	 */
	public  void writeSqlFile(String filePath,String sql) throws IOException{
		
		File file = new File(filePath);
		if(!file.exists()){
			
			file.createNewFile();
		}
		FileWriter writer = new FileWriter(filePath,true);
		writer.write(sql);
		writer.close();
	}
	
	/**
	 * 删除文件
	 * @param filePath
	 */
	public void deleteSqlFile(String filePath){
		
		File f = new File(filePath);
		if(f.exists()){
			
			f.delete();
		}
	}
	
	/*************************************************************以上为基础操作，以下为业务操作***************************************************************************
	 * 获取sheet页，循环写入文件
	 * @param sheetMap
	 * @return
	 * @throws IOException
	 */
	public String  toWrite(Map<String, List<Map>> sheetMap) throws IOException{
		
		StringBuffer sb = new StringBuffer();
		
		//迭代sheet页
		Set<String> keySet = sheetMap.keySet();
		//生成数据下标
		int index=empid;
		//数据下标临时变更
		int index_bak=0;
		//执行行数
		int row_count=0;
		//记录已经生成的员工
		Map<String,Integer> emap = new HashMap<String,Integer>();
		if(keySet.size()>0){
			System.out.println("--------->生成脚本开始--------------------");
			//删除所有脚本文件
			deleteFileBatch();
			//循环sheet页
			for (String key : keySet) {
				
				List<Map> lt = sheetMap.get(key);
				if(null!=lt && lt.size()>0 ){
					
					String username="";//人员名称
					String userid="";//人员工号
					String orgid="";//机构id
					String roleid="";//角色ID
					String posicode="";//岗位ID
					String personlv="";//人员级别
					//循环取出每一条
					for (int i = 0; i < lt.size(); i++) {
						Map<String,String> temp = lt.get(i);
						username = temp.get("username");
						userid = temp.get("userid");
						orgid = gmap.get(temp.get("orgid"));
						roleid = temp.get("roleid");
						posicode = temp.get("posicode");
						personlv = temp.get("personlv");
						if(null == userid){
							
							continue;
						}else if(null ==orgid || "".equals(orgid)){
							System.out.println("------>机构名称："+temp.get("orgid")+",未找到对应的机构号。---------");
						}else{
							
							//员工不存在list中，才生成员工脚本
							if(!emap.containsKey(userid)){
								
								//每生成一个员工，都要生成该员工对应的清理数据，以保证脚本可循环执行(目前只根据员工，不区分机构)
								String cleanRela = generateEmployeeCleanSql(userid);
								writeSqlFile(this.writeFilePath+"om_employee_clean.sql",cleanRela);
								//生成员工表sql
								String oesql = generateOmEmployee(index,userid,username,orgid,personlv);
								writeSqlFile(this.writeFilePath+"om_employee.sql",oesql);
								//System.out.println("oesql====>"+oesql);
								
								//生成操作员表sql
								String opsql = generateAcOperator(index,userid,username);
								writeSqlFile(this.writeFilePath+"ac_operator.sql",opsql);
								//System.out.println("opsql=====>"+opsql);
								
							}else{
								System.out.println("------>重复的员工："+userid);
								index_bak =index;//保留当前下标
								index = emap.get(userid);//将重复员工的主键序列取出
							}
							
							//生成员工与机构关系sql
							String oegsql = generateOmEmporg(index,orgid);
							writeSqlFile(this.writeFilePath+"om_emporg.sql",oegsql);
							//System.out.println("oegsql=====>"+oegsql);
							
							//生成员工与角色关系
							String oprsql = generateAcOperatorrole(index,roleid,orgid);
							writeSqlFile(this.writeFilePath+"ac_operatorrole.sql",oprsql);
							//System.out.println("oprsql=====>"+oprsql);
							
							//生成员工与岗位关系
							String ompsql = generateOmEmpposition(index,posicode,orgid);
							writeSqlFile(this.writeFilePath+"om_empposition.sql",ompsql);
							//System.out.println("ompsql=====>"+ompsql);
							
						}
						
						//存在，将下标赋回原变更
						if(emap.containsKey(userid)){
							
							index = index_bak;
						//不存在，添加用户，增加下标	
						}else{
							emap.put(userid, index);
							index++;
						}
						
						//行数加1
						row_count++;
					}
				}else{
					sb.append("导入失败,模板数据为空！");
				}
				//清空list
				lt = null;
				
				//打印每一个sheet页执行的行数
				System.out.println("--------->sheet页【"+key+"]共执行了"+row_count+"条数据，其中员工"+emap.keySet().size()+"人---------");
			}
		}
		System.out.println("--------->执行的员工主键："+emap.toString());
		System.out.println("--------->生成脚本结束--------------------");
		return sb.toString();
	}
	
	/**
	 * 删除所有要生成的脚本文件
	 */
	public void  deleteFileBatch(){
		
		deleteSqlFile(this.writeFilePath+"om_employee.sql");
		deleteSqlFile(this.writeFilePath+"ac_operator.sql");
		deleteSqlFile(this.writeFilePath+"om_emporg.sql");
		deleteSqlFile(this.writeFilePath+"ac_operatorrole.sql");
		deleteSqlFile(this.writeFilePath+"om_empposition.sql");
		deleteSqlFile(this.writeFilePath+"om_employee_clean.sql");
	}
	
	/**
	 * 生成员工对应的清理数据脚本
	 * @return
	 */
	public String generateEmployeeCleanSql(String userid){
		
		
		String del_operatorrole="delete from ac_operatorrole where operatorid in (select operatorid from ac_operator where userid='"+userid+"');\n";
		String del_emporg="delete from om_emporg where empid in (select operatorid from ac_operator where userid='"+userid+"');\n";
		String del_empposition="delete from om_empposition where empid in (select operatorid from ac_operator where userid='"+userid+"');\n";
		String del_operator="delete from ac_operator where userid='"+userid+"';\n";
		String del_employee="delete from om_employee where userid='"+userid+"';\n";
		return del_operatorrole+del_emporg+del_empposition+del_operator+del_employee;
	}
	
	/**
	 * 生成员工表sql
	 * @return
	 */
	public String generateOmEmployee(int index,String userid,String username,String orgid,String personlv){
		
		String sql = "insert into om_employee (EMPID, EMPCODE, OPERATORID, USERID, EMPNAME, REALNAME, GENDER, BIRTHDATE, POSITION, EMPSTATUS, CARDTYPE,"
		+ " CARDNO, INDATE, OUTDATE, OTEL, OADDRESS, OZIPCODE, OEMAIL, FAXNO, MOBILENO, MSN, HTEL, HADDRESS, HZIPCODE, PEMAIL, PARTY, DEGREE, "
		+ "MAJOR, SPECIALTY, WORKEXP, REGDATE, CREATETIME, LASTMODYTIME, ORGIDLIST, ORGID, REMARK, EDUCATION, LICENSENO, INTOTRADEDATE,"
		+ " RUNMARKDATE, EMPLEVEL, DEPARTMENT_ID)"
		+ "values ("+index+",'"+userid+"',"+index+",'"+userid+"','"+username+"', '', '', null, null, '0', '', '', null, null, '', "
				+ "'', '', '', '', '', '', '', '', '', '', '', '', null, '', '', null, to_date('30-10-2015 00:00:00', 'dd-mm-yyyy hh24:mi:ss'), "
				+ "to_date('30-10-2015 00:00:00', 'dd-mm-yyyy hh24:mi:ss'), '', "+orgid+", '', '', '', null, null, ";
		if(null ==personlv){
			
			sql+="null, '');\n";
		}else{
			
			sql+="'"+personlv+"', '');\n";
		}
		return sql;
	}
	/**
	 * 生成操作员表sql
	 * @return
	 */
	public String generateAcOperator(int index,String userid,String username){
		
		String sql ="insert into ac_operator (OPERATORID, USERID, PASSWORD, INVALDATE, OPERATORNAME, AUTHMODE, STATUS, UNLOCKTIME, MENUTYPE, "
				+ "LASTLOGIN, ERRCOUNT, STARTDATE, ENDDATE, VALIDTIME, MACCODE, IPADDRESS, EMAIL, PASSWORD1)"
				+ "values ("+index+", '"+userid+"', 'ZwsUcorZkCrsujLiL6T2vQ==', to_date('30-10-2015', 'dd-mm-yyyy'), '"+username+"', null, 'init',"
				+ " to_date('30-10-2015 00:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'menubar', to_date('30-10-2015 00:00:00', 'dd-mm-yyyy hh24:mi:ss'),"
				+ " null, to_date('30-10-2015', 'dd-mm-yyyy'), null, null, null, null, null, 'ZwsUcorZkCrsujLiL6T2vQ==');\n";

		return sql;
	}
	/**
	 * 生成员工与机构表sql
	 * @return
	 */
	public String generateOmEmporg(int index,String orgid){
		
		return "insert into om_emporg (ORGID, EMPID, ISMAIN)values ("+orgid+","+index+",'1');\n";
	}
	/**
	 * 生成操作员与角色表sql
	 * @return
	 */
	public String generateAcOperatorrole(int index,String roleid,String orgid){
		
		StringBuffer sb = new StringBuffer();
		if(null != roleid && !"".equals(roleid)){
			
			if(roleid.indexOf("、")!=-1){
				
				String[] roles = roleid.split("、");
				for (int i = 0; i < roles.length; i++) {
					String string = roles[i];
					String role = map.get(string);
					if(null ==role || "".equals(role)){
						
						sb.append("第"+index+"行没有获取到角色ID，角色名称为："+string+"\n");
					}else{
						
						sb.append("insert into ac_operatorrole (OPERATORID, ROLEID, AUTH, ORGID)values ("+index+", '"+role+"', null, "+orgid+");\n");
					}
				}
			}else{
				String role = map.get(roleid);
				if(null ==role || "".equals(role)){
					
					sb.append("第"+index+"行没有获取到角色ID，角色名称为："+roleid+"\n");
				}else{
					
					sb.append("insert into ac_operatorrole (OPERATORID, ROLEID, AUTH, ORGID)values ("+index+", '"+role+"', null, "+orgid+");\n");
				}
			}
		}
		
		return sb.toString();
	}
	/**
	 * 生成员工与岗位表sql
	 * @return
	 */
	public String generateOmEmpposition(int index,String positionid,String orgid){
		StringBuffer sb = new StringBuffer();
		if(null != positionid && !"".equals(positionid)){
			
			if(positionid.indexOf("、")!=-1){
				
				String[] roles = positionid.split("、");
				for (int i = 0; i < roles.length; i++) {
					String string = roles[i];
					String posi = pmap.get(string);
					if(null == posi || "".equals(posi)){
						
						sb.append("第"+index+"行没有获取到岗位ID，岗位名称为："+string+"\n");
					}else{
						
						sb.append("insert into om_empposition (POSITIONID, EMPID, ISMAIN, ORGID, EMPPOSID)values ("+posi+","+index+",null,"+orgid+",'"+getUUID()+"');\n");
					}
				}
			}else{
				String posi = pmap.get(positionid);
				if(null == posi || "".equals(posi)){

					sb.append("第"+index+"行没有获取到岗位ID，岗位名称为："+positionid+"\n");
				}else{
					
					sb.append("insert into om_empposition (POSITIONID, EMPID, ISMAIN, ORGID, EMPPOSID)values ("+posi+","+index+",null,"+orgid+",'"+getUUID()+"');\n");
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 生成机构与角色关系
	 * @return
	 * @throws IOException 
	 */
	public void generateOmPartyRole() throws IOException{
		//一般机构
		String [] orgs={"1","21","22","23","24","25","26","41","42","43","44","45","61","62","63","64","81","82","101","121","122","141","161","181","182","201","221","222","241","261","262","263","264","282","10000","10001","10002","10003","10004","10005","10006","10007","10008","10009","10010","10011","10012","10013","10014","10015","10016","10017","10018","10019","10020","10021","10022","10023","10024","10025","10026","10027","10028","10029","10030","10031","10032","10033","10034","10035","10036","10037","10038","10039","10040","10041","10042","10043","10044","10045","10046","10047","10048","10049","10050","10051","10052","10053","10054","10055","10056","10057","10058","10059","10060","10061","10062","10063","10064","10065","10066","10067","10068","10069","10070","10071","10072","10073","10074","10075","10076","10077","10078","10079","10080","10081","10082","10083","10084","10085","10086","10087","10088","10089","10090","10091","10092","10093","10094","10095","10096","10097","10098","10099","10100","10101","10102","10103","10104","10105","10106","10107","10108","10109","10110","10111","10112","10113","10114","10115","10116","10117","10118","10119","10120","10121","10122","10123","10124","10125","10126","10127","10128","10129","10130","10131","10132","10133","10134","10135","10136","10137","10138","10139","10140","10141","10142","10143","10144","10145","10146","10147","10148","10149","10150","10151","10152","10153","10154","20002","20003","20005","20006","20007","20009","20010","20011","20013","20014","20015","20016","20017","20018","20019","20021","20023","20024","20026","20027","20028","20029","20031","20032","20033","20035","20037","20038","20040"};
		//一般机构角色
		String [] roles ={"R1002","R1003","R1005","R1006","R1007","R1008","R1009","R1010","R1011","R1012","R1013","R1014","R1015","R1016","R1017","R1018","R1019","R1020","R1021","R1022","R1023","R1024","R1025","R1026","R1027","R1028","R1029","R1030","R1031","R1032","R1033","R1034","R1035","R1036","R1037","R1038","R1039","R1040","R1041","R1042","R1043","R1044","R1045","R1046","R1047","R1048","R1049","R1050","R1051","R1052","R1053","R1054","R2002","R2003","R2004","R2005","R2006","R2007","R2008","R2009","R2010","R2011","R2012","R2013","R2014","eosadmin"};
		
		//三级机构
		String [] orgs2={"10133","10134","10135","10136","10137","10138","10139","10140","10141","10143","10144","10145","10146","10147","10148","10149","10150","10151","10152","10153","304","20002","20003","20005","20006","20007","20009","20010","20011","20013","20014","20015","20016","20017","20018","20019","20021","20023","20024","20026","20027","20028","20029","20031","20032","20033","20035","20037","20038","20040","23","1","22","24","25","26","43","45","63","64","81","82","122","141","181","182","201","221","222","241","262","264","282","21","42","44","61","101","121","161","263","10002","10003","10004","10005","10006","10007","10008","10009","10010","10011","10012","10013","10014","10015","10016","10017","10018","10019","10020","10021","10022","10024","10026","10028","10030","10032","10033","10034","10035","10036","10037","10038","10039","10040","10041","10042","10044","10045","10046","10047","10048","10049","10051","10052","10053","10054","10055","10056","10057","10058","10059","10060","10061","10063","10064","10065","10066","10067","10068","10070","10071","10072","10073","10074","10075","10077","10078","10079","10080","10081","10082","10083","10084","10085","10087","10088","10089","10090","10091","10092","10093","10094","10095","10096","10097","10098","10099","10100","10102","10103","10104","10105","10106","10107","10108","10109","10110","10111","10112","10114","10115","10116","10117","10118","10119","10120","10121","10122","10123","10124","10125","10126","10127","10128","10129","10130","10132"};
		//客户经理角色
		String [] roles2 ={"R1002","R1055"};
		System.out.println("-------->开始生成一般机构角色--------------------");
		
		deleteSqlFile(this.writeFilePath+"om_partyrole.sql");
		int sorg=0;
		for (int i = 0; i < orgs.length; i++) {
			String orgid = orgs[i];
			for (int j = 0; j < roles.length; j++) {
				String roleid = roles[j];
				
				String sql="insert into om_partyrole (ROLEID, PARTYTYPE, PARTYID)values ('"+roleid+"', 'org', "+orgid+");\n";
				writeSqlFile(this.writeFilePath+"om_partyrole.sql",sql);
			}
			sorg++;
		}
		System.out.println("--------->共生成了："+sorg+"个机构");
		System.out.println("--------->开始生成小贷中心机构角色------------------");
		int norg=0;
		/*for (int i = 0; i < orgs2.length; i++) {
			String orgid = orgs2[i];
			for (int j = 0; j < roles2.length; j++) {
				String roleid = roles2[j];
				
				String sql="insert into om_partyrole (ROLEID, PARTYTYPE, PARTYID)values ('"+roleid+"', 'org', "+orgid+");\n";
				writeSqlFile(this.writeFilePath+"om_partyrole.sql",sql);
			}
			norg++;
		}*/
		System.out.println("---------->共生成了："+norg+"个机构");
		System.out.println("---------->生成完成-----------------------------");
	}
	
	
	public void generateProductPara(Map<String, List<Map>> sheetMap) throws IOException{
		
		System.out.println("======>开始生成产品脚本");
		int count =1;
		String sql ="insert into tb_sys_product_param (P_ID, PARA_TYPE, PARA_COLUMN, PARA_COLUNM_NAME, PARA_COUNT_SIGN, PARA_CONTRL_LEFTVAL, PARA_CONTRL_RIGTHVAL, PARA_REMARK, PRODUCT_ID, AUTH_ORG_NUM, PARA_STATUS)";
		Set<String>  keys = sheetMap.keySet();
		Iterator<String> its = keys.iterator();
		while(its.hasNext()){
			String key = (String)its.next();
			List<Map> lt = sheetMap.get(key);
			if(null !=lt && lt.size()>0){
				
				for (int i = 0; i < lt.size(); i++) {
					Map<String,Object> temp = lt.get(i);
					String productId = (String)temp.get("productId");//产品ID
					String isShow = (String)temp.get("isShow");//是否显示在产品树中
					String edxh = (String)temp.get("edxh");//额度循环标志
					String htxh = (String)temp.get("htxh");//合同循环标志
					String txbz = (String)temp.get("txbz");//贴息标志
					String ywlx = (String)temp.get("ywlx");//表内表外
					//是否显示在产品树中
					if(null != isShow && !"".equals(isShow)){
						String para0=null;
						if("是".equals(isShow)){
							
							para0 = "update tb_sys_product t set t.discount_ind='1' where product_id='"+productId+"';\n";
						}else{
							para0 = "update tb_sys_product t set t.discount_ind='0' where product_id='"+productId+"';\n";
						}
						writeSqlFile(this.writeFilePath+"tb_sys_product.sql",para0);
					}
					//表内表外
					String para4=null;
					if(null != ywlx && !"".equals(ywlx)){
						
						if("表内".equals(ywlx)){
							
							para4 = "update tb_sys_product t set t.product_type='1' where product_id='"+productId+"';\n";
						}else{
							para4 = "update tb_sys_product t set t.product_type='2' where product_id='"+productId+"';\n";
						}
						
					}else{
						para4 = "update tb_sys_product t set t.product_type=null where product_id='"+productId+"';\n";
					}
					writeSqlFile(this.writeFilePath+"tb_sys_product.sql",para4);
					//额度循环标志
					if(null != edxh && !"".equals(edxh)){
						String para2=null;
						if("是".equals(edxh)){
							
							para2 = sql+"values ('"+getUUID()+"', '1', 'amountDetail.cycleInd', '额度循环标志', 'include', 'YesOrNo', '1,0', null, '"+productId+"', '08001', '1');\n";
						}else{
							para2 = sql+"values ('"+getUUID()+"', '1', 'amountDetail.cycleInd', '额度循环标志', 'include', 'YesOrNo', '0', null, '"+productId+"', '08001', '1');\n";
						}
						writeSqlFile(this.writeFilePath+"tb_sys_product_param.sql",para2);
					}
					
					//合同循环标志
					if(null != htxh && !"".equals(htxh)){
						String para1=null;
						if("是".equals(htxh)){
							
							para1 = sql+"values ('"+getUUID()+"', '1', 'amountDetail.cycleIndCon', '合同循环标志', 'include', 'YesOrNo', '1,0', null, '"+productId+"', '08001', '1');\n";
						}else{
							para1 = sql+"values ('"+getUUID()+"', '1', 'amountDetail.cycleIndCon', '合同循环标志', 'include', 'YesOrNo', '0', null, '"+productId+"', '08001', '1');\n";
						}
						writeSqlFile(this.writeFilePath+"tb_sys_product_param.sql",para1);
					}
					
					//贴息标志
					if(null != txbz && !"".equals(txbz)){
						String para3=null;
						if("是".equals(txbz)){
							
							para3 = sql+"values ('"+getUUID()+"', '1', 'sftx', '贴息标志', 'include', 'YesOrNo', '1,0', null, '"+productId+"', '08001', '1');\n";
						}else{
							para3 = sql+"values ('"+getUUID()+"', '1', 'sftx', '贴息标志', 'include', 'YesOrNo', '0', null, '"+productId+"', '08001', '1');\n";
						}
						writeSqlFile(this.writeFilePath+"tb_sys_product_param.sql",para3);
					}
					count++;
				}
			}
		}
		System.out.println("======>结束生成产品脚本，共生成："+count+" 条数据！");
	}
	
	
	public String getUUID(){
		
		String s = UUID.randomUUID().toString();
		s = s.replaceAll("-", "");
		return s;
	}
	
	public static void main(String[] args) {
		
		Test t = new Test();
		try {
			Map  sheetMap = t.readExcel();
			t.toWrite(sheetMap);
			
			//生成机构角色
			//t.generateOmPartyRole();
			//生成产品
			//t.generateProductPara(sheetMap);
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 
		
	}
}
