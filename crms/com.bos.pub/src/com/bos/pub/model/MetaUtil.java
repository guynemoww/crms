/**
 * 
 */
package com.bos.pub.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.bos.pub.GitUtil;
import com.bos.utp.tools.SystemInfo;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;
import com.eos.system.logging.Logger;
import commonj.sdo.DataObject;

/**
 * @author 王世春
 * @date 2013-10-28 14:44:25
 * 
 */
@Bizlet("单表CRUD相关操作")
public class MetaUtil {

	private Logger log = GitUtil.getLogger(MetaUtil.class);

	/**
	 * @param args
	 * @author 王世春
	 */
	public static void main(String[] args) {
	}

	/**
	 * @param param
	 * @return
	 * @author 王世春
	 */
	@Bizlet("生成CRUD页面，并返回文件夹路径")
	public static String genPage(Map param) {
		String path = SystemInfo.APP_WAR_PATH + "pub/meta/crud/";
		File folder = new File(path);
		if (folder.exists() == false)
			folder.mkdirs();

		String tableName = param.get("tableName").toString(); // 表名称
		String dataName = param.get("dataName").toString(); // 数据模型名称
		DataObject[] cols = (DataObject[]) param.get("items");
		if (StringUtils.isEmpty(tableName) || StringUtils.isEmpty(dataName)
				|| null == cols || cols.length == 0) {
			return "参数不正确或出现其他错误！";
		}

		MetaUtil util = new MetaUtil();
		String pkKey = null;
		for (DataObject col : cols) {
			if ("1".equals(col.get("isPk"))) {
				pkKey = col.getString("colName");
				pkKey = getJavaIdentifier(pkKey);
				break;
			}
		}
		if (null == pkKey) {
			return "未设置主键！";
		}
		util.pkKey = pkKey;
		dataName = dataName.trim();
		if (dataName.contains(".") && dataName.endsWith(".") == false) {
			// 取实体名称
			util.dataObjectName = dataName
					.substring(dataName.lastIndexOf(".") + 1);
		} else {
			if (dataName.contains("."))
				util.dataObjectName = "Item";
			else
				util.dataObjectName = dataName;
		}
		util.dataObjectNameJava = String.valueOf(util.dataObjectName.charAt(0))
				.toLowerCase()
				+ util.dataObjectName.substring(1);
		if (StringUtils.isEmpty(util.dataObjectName)) {
			return "“数据模型名称”格式不正确！";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableName);
		Object[] arr = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.pub.model.meta.selectTableRemark", map);
		util.dataObjectNote = null;
		if (null != arr && arr.length == 1) {
			util.dataObjectNote = (String) ((Map) arr[0]).get("REMARKS");
		}

		util.genList(param);
		util.genAdd(param);
		util.genEdit(param);

		util.genListBizLogic(param);
		util.genAddBizLogic(param);
		util.genGetBizLogic(param);
		util.genDelBizLogic(param);
		util.genEditBizLogic(param);

		return path;
	}

	private void genDelBizLogic(Map param) {
		String path = SystemInfo.APP_WAR_PATH + "pub/meta/crud/";
		String srcPath = path + "template/delItem.xml";
		String destPath = path + "del" + this.dataObjectName + ".bizx";
		try {
			BufferedReader src = new BufferedReader(new InputStreamReader(
					new FileInputStream(srcPath), Charset.forName("utf-8")));
			OutputStreamWriter fw = new OutputStreamWriter(
					new FileOutputStream(destPath), Charset.forName("utf-8"));
			String line = null;
			// DataObject[] cols = (DataObject[]) param.get("items");
			while ((line = src.readLine()) != null) {
				String date = new java.sql.Date(System.currentTimeMillis())
						.toString();
				if (line.contains("#{createTime}")) {
					line = line.replace("#{createTime}", date);
					line = line.replace("#{createTime}", date);
					line = line.replace("#{detailDescription}", "删除："
							+ this.dataObjectNote);
					if (StringUtils.isNotEmpty(this.dataObjectNote)) {
						line = line.replace("#{name}", "删除："
								+ this.dataObjectNote);
					} else {
						line = line.replace("#{name}", "删除");
					}
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{bizLogicName}")) {
					line = line.replace("#{bizLogicName}", "del"
							+ this.dataObjectName);
					line = line
							.replace("#{modelName}", this.dataObjectNameJava);
					line = line.replace("#{detailDescription}", "删除"
							+ this.dataObjectNote);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{detailDescription}")) {
					line = line.replace("#{detailDescription}", "删除："
							+ this.dataObjectNote);
					line = line.replace("#{createTime}", date);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{createTime}")) {
					line = line.replace("#{createTime}", date);
					line = line.replace("#{createTime}", date);
					line = line.replace("#{detailDescription}", "删除："
							+ this.dataObjectNote);
					if (StringUtils.isNotEmpty(this.dataObjectNote)) {
						line = line.replace("#{name}", "删除："
								+ this.dataObjectNote);
					} else {
						line = line.replace("#{name}", "删除");
					}
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{modelType}")) {
					line = line.replace("#{modelType}", param.get("dataName")
							.toString().trim());
					line = line
							.replace("#{modelName}", this.dataObjectNameJava);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{bizLogicName}")) {
					line = line.replace("#{bizLogicName}", "del"
							+ this.dataObjectName);
					line = line.replace("#{modelName}", StringUtils
							.capitalize(this.dataObjectNameJava));
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{modelName}")) {
					line = line
							.replace("#{modelName}", this.dataObjectNameJava);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				fw.write(line);
				fw.write("\n");
			}

			src.close();
			fw.close();
		} catch (Exception e) {
			log.error("生成删除逻辑流时出错！", e);
		}
	}

	private void genGetBizLogic(Map param) {
		String path = SystemInfo.APP_WAR_PATH + "pub/meta/crud/";
		String srcPath = path + "template/getItem.xml";
		String destPath = path + "get" + this.dataObjectName + ".bizx";
		try {
			BufferedReader src = new BufferedReader(new InputStreamReader(
					new FileInputStream(srcPath), Charset.forName("utf-8")));
			OutputStreamWriter fw = new OutputStreamWriter(
					new FileOutputStream(destPath), Charset.forName("utf-8"));
			String line = null;
			// DataObject[] cols = (DataObject[]) param.get("items");
			while ((line = src.readLine()) != null) {
				String date = new java.sql.Date(System.currentTimeMillis())
						.toString();
				if (line.contains("#{createTime}")) {
					line = line.replace("#{createTime}", date);
					line = line.replace("#{createTime}", date);
					if (StringUtils.isNotEmpty(this.dataObjectNote)) {
						line = line.replace("#{name}", "查询："
								+ this.dataObjectNote);
					} else {
						line = line.replace("#{name}", "查询");
					}
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{bizLogicName}")) {
					line = line.replace("#{bizLogicName}", "get"
							+ this.dataObjectName);
					line = line
							.replace("#{modelName}", this.dataObjectNameJava);
					line = line.replace("#{detailDescription}", "查询"
							+ this.dataObjectNote);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{detailDescription}")) {
					line = line.replace("#{detailDescription}", "查询："
							+ this.dataObjectNote);
					line = line.replace("#{createTime}", date);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{createTime}")) {
					line = line.replace("#{createTime}", date);
					line = line.replace("#{createTime}", date);
					line = line.replace("#{detailDescription}", "查询："
							+ this.dataObjectNote);
					if (StringUtils.isNotEmpty(this.dataObjectNote)) {
						line = line.replace("#{name}", "查询："
								+ this.dataObjectNote);
					} else {
						line = line.replace("#{name}", "查询");
					}
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{modelType}")) {
					line = line.replace("#{modelType}", param.get("dataName")
							.toString().trim());
					line = line
							.replace("#{modelName}", this.dataObjectNameJava);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{bizLogicName}")) {
					line = line.replace("#{bizLogicName}", "get"
							+ this.dataObjectName);
					line = line.replace("#{modelName}", StringUtils
							.capitalize(this.dataObjectNameJava));
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{modelName}")) {
					line = line
							.replace("#{modelName}", this.dataObjectNameJava);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				fw.write(line);
				fw.write("\n");
			}

			src.close();
			fw.close();
		} catch (Exception e) {
			log.error("生成查询逻辑流时出错！", e);
		}
	}

	private void genListBizLogic(Map param) {
		String path = SystemInfo.APP_WAR_PATH + "pub/meta/crud/";
		String srcPath = path + "template/getItemList.xml";
		String destPath = path + "get" + this.dataObjectName + "List.bizx";
		try {
			BufferedReader src = new BufferedReader(new InputStreamReader(
					new FileInputStream(srcPath), Charset.forName("utf-8")));
			OutputStreamWriter fw = new OutputStreamWriter(
					new FileOutputStream(destPath), Charset.forName("utf-8"));
			String line = null;
			// DataObject[] cols = (DataObject[]) param.get("items");
			while ((line = src.readLine()) != null) {
				String date = new java.sql.Date(System.currentTimeMillis())
						.toString();
				if (line.contains("#{createTime}")) {
					line = line.replace("#{createTime}", date);
					line = line.replace("#{createTime}", date);
					if (StringUtils.isNotEmpty(this.dataObjectNote)) {
						line = line.replace("#{name}", "列表查询："
								+ this.dataObjectNote);
					} else {
						line = line.replace("#{name}", "列表查询");
					}
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{bizLogicName}")) {
					line = line.replace("#{bizLogicName}", "get"
							+ this.dataObjectName + "List");
					line = line
							.replace("#{modelName}", this.dataObjectNameJava);
					line = line.replace("#{detailDescription}", "列表查询"
							+ this.dataObjectNote);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{detailDescription}")) {
					line = line.replace("#{detailDescription}", "列表查询："
							+ this.dataObjectNote);
					line = line.replace("#{createTime}", date);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{createTime}")) {
					line = line.replace("#{createTime}", date);
					line = line.replace("#{createTime}", date);
					line = line.replace("#{detailDescription}", "列表查询："
							+ this.dataObjectNote);
					if (StringUtils.isNotEmpty(this.dataObjectNote)) {
						line = line.replace("#{name}", "列表查询："
								+ this.dataObjectNote);
					} else {
						line = line.replace("#{name}", "列表查询");
					}
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{modelType}")) {
					line = line.replace("#{modelType}", param.get("dataName")
							.toString().trim());
					line = line
							.replace("#{modelName}", this.dataObjectNameJava);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{modelName}")) {
					line = line
							.replace("#{modelName}", this.dataObjectNameJava);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				fw.write(line);
				fw.write("\n");
			}

			src.close();
			fw.close();
		} catch (Exception e) {
			log.error("生成列表查询逻辑流时出错！", e);
		}
	}

	private void genEditBizLogic(Map param) {
		String path = SystemInfo.APP_WAR_PATH + "pub/meta/crud/";
		String srcPath = path + "template/updateItem.xml";
		String destPath = path + "update" + this.dataObjectName + ".bizx";
		try {
			BufferedReader src = new BufferedReader(new InputStreamReader(
					new FileInputStream(srcPath), Charset.forName("utf-8")));
			OutputStreamWriter fw = new OutputStreamWriter(
					new FileOutputStream(destPath), Charset.forName("utf-8"));
			String line = null;
			// DataObject[] cols = (DataObject[]) param.get("items");
			while ((line = src.readLine()) != null) {
				String date = new java.sql.Date(System.currentTimeMillis())
						.toString();
				if (line.contains("#{createTime}")) {
					line = line.replace("#{createTime}", date);
					line = line.replace("#{createTime}", date);
					line = line.replace("#{detailDescription}", "更新："
							+ this.dataObjectNote);
					if (StringUtils.isNotEmpty(this.dataObjectNote)) {
						line = line.replace("#{name}", "更新："
								+ this.dataObjectNote);
					} else {
						line = line.replace("#{name}", "更新");
					}
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{bizLogicName}")) {
					line = line.replace("#{bizLogicName}", "update"
							+ this.dataObjectName);
					line = line
							.replace("#{modelName}", this.dataObjectNameJava);
					line = line.replace("#{detailDescription}", "更新"
							+ this.dataObjectNote);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{detailDescription}")) {
					line = line.replace("#{detailDescription}", "更新："
							+ this.dataObjectNote);
					line = line.replace("#{createTime}", date);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{createTime}")) {
					line = line.replace("#{createTime}", date);
					line = line.replace("#{createTime}", date);
					line = line.replace("#{detailDescription}", "更新："
							+ this.dataObjectNote);
					if (StringUtils.isNotEmpty(this.dataObjectNote)) {
						line = line.replace("#{name}", "更新："
								+ this.dataObjectNote);
					} else {
						line = line.replace("#{name}", "更新");
					}
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{modelType}")) {
					line = line.replace("#{modelType}", param.get("dataName")
							.toString().trim());
					line = line
							.replace("#{modelName}", this.dataObjectNameJava);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{bizLogicName}")) {
					line = line.replace("#{bizLogicName}", "add"
							+ this.dataObjectName);
					line = line.replace("#{modelName}", StringUtils
							.capitalize(this.dataObjectNameJava));
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{modelName}")) {
					line = line
							.replace("#{modelName}", this.dataObjectNameJava);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				fw.write(line);
				fw.write("\n");
			}

			src.close();
			fw.close();
		} catch (Exception e) {
			log.error("生成更新逻辑流时出错！", e);
		}
	}

	private void genAddBizLogic(Map param) {
		String path = SystemInfo.APP_WAR_PATH + "pub/meta/crud/";
		String srcPath = path + "template/addItem.xml";
		String destPath = path + "add" + this.dataObjectName + ".bizx";
		try {
			BufferedReader src = new BufferedReader(new InputStreamReader(
					new FileInputStream(srcPath), Charset.forName("utf-8")));
			OutputStreamWriter fw = new OutputStreamWriter(
					new FileOutputStream(destPath), Charset.forName("utf-8"));
			String line = null;
			DataObject[] cols = (DataObject[]) param.get("items");
			while ((line = src.readLine()) != null) {
				String date = new java.sql.Date(System.currentTimeMillis())
						.toString();
				if (line.contains("#{createTime}")) {
					line = line.replace("#{createTime}", date);
					line = line.replace("#{createTime}", date);
					if (StringUtils.isNotEmpty(this.dataObjectNote)) {
						line = line.replace("#{name}", "新增："
								+ this.dataObjectNote);
					} else {
						line = line.replace("#{name}", "新增");
					}
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{bizLogicName}")) {
					line = line.replace("#{bizLogicName}", "add"
							+ this.dataObjectName);
					line = line
							.replace("#{modelName}", this.dataObjectNameJava);
					line = line.replace("#{detailDescription}", "新增："
							+ this.dataObjectNote);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{detailDescription}")) {
					line = line.replace("#{detailDescription}", "新增："
							+ this.dataObjectNote);
					line = line.replace("#{createTime}", date);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{createTime}")) {
					line = line.replace("#{createTime}", date);
					line = line.replace("#{createTime}", date);
					line = line.replace("#{detailDescription}", "新增："
							+ this.dataObjectNote);
					if (StringUtils.isNotEmpty(this.dataObjectNote)) {
						line = line.replace("#{name}", "新增："
								+ this.dataObjectNote);
					} else {
						line = line.replace("#{name}", "新增");
					}
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{modelType}")) {
					line = line.replace("#{modelType}", param.get("dataName")
							.toString().trim());
					line = line
							.replace("#{modelName}", this.dataObjectNameJava);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{bizLogicName}")) {
					line = line.replace("#{bizLogicName}", "add"
							+ this.dataObjectName);
					line = line.replace("#{modelName}", StringUtils
							.capitalize(this.dataObjectNameJava));
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{modelName}")) {
					line = line
							.replace("#{modelName}", this.dataObjectNameJava);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{assignBeforeInsert}")) {
					for (DataObject col : cols) {
						String colZhname = col.getString("colZhname");
						String colName = col.getString("colName");
						if (null != colZhname && null != colName) {
							if (colZhname.endsWith("日期")
									|| colZhname.endsWith("日")
									|| colZhname.endsWith("时间")) {
								line = "    <process:copy>\n"
										+ "      <process:from type=\"query\" pattern=\"reference\">time</process:from>\n"
										+ "      <process:to type=\"query\">"
										+ this.dataObjectName + "/" + colName
										+ "</process:to>\n"
										+ "    </process:copy>";
								fw.write(line);
								fw.write("\n");
								continue;
							}
							if (colZhname.endsWith("人员")
									|| colZhname.endsWith("人")
									|| colZhname.endsWith("人员编号")
									|| colZhname.endsWith("人编号")
									|| colZhname.endsWith("人员ID")
									|| colZhname.endsWith("人ID")
									|| colZhname.endsWith("人员CD")
									|| colZhname.endsWith("人CD")) {
								line = "    <process:copy>\n"
										+ "      <process:from type=\"query\" pattern=\"reference\">m:userObject/userId</process:from>\n"
										+ "      <process:to type=\"query\">"
										+ this.dataObjectName + "/" + colName
										+ "</process:to>\n"
										+ "    </process:copy>";
								fw.write(line);
								fw.write("\n");
								continue;
							}
							if (colZhname.endsWith("机构")
									|| colZhname.endsWith("行")
									|| colZhname.endsWith("机构ID")
									|| colZhname.endsWith("行ID")
									|| colZhname.endsWith("机构CD")
									|| colZhname.endsWith("行CD")
									|| colZhname.endsWith("机构编号")
									|| colZhname.endsWith("行编号")) {
								line = "    <process:copy>\n"
										+ "      <process:from type=\"query\" pattern=\"reference\">m:userObject/attributes/orgcode</process:from>\n"
										+ "      <process:to type=\"query\">"
										+ this.dataObjectName + "/" + colName
										+ "</process:to>\n"
										+ "    </process:copy>";
								fw.write(line);
								fw.write("\n");
								continue;
							}
						}
					}

					fw.write("\n");
					continue;
				}
				fw.write(line);
				fw.write("\n");
			}

			src.close();
			fw.close();
		} catch (Exception e) {
			log.error("生成新增逻辑流时出错！", e);
		}
	}

	private void genEdit(Map param) {
		String path = SystemInfo.APP_WAR_PATH + "pub/meta/crud/";
		String srcPath = path + "template/item_edit.jsp";
		String destPath = path + "item_edit.jsp";
		try {
			BufferedReader src = new BufferedReader(new InputStreamReader(
					new FileInputStream(srcPath), Charset.forName("utf-8")));
			OutputStreamWriter fw = new OutputStreamWriter(
					new FileOutputStream(destPath), Charset.forName("utf-8"));
			String line = null;
			DataObject[] cols = (DataObject[]) param.get("items");
			while ((line = src.readLine()) != null) {
				if (line.contains("#{date}")) {
					line = line.replace("#{date}", new java.sql.Date(System
							.currentTimeMillis()).toString());
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{description}")) {
					line = line.replace("#{description}", param
							.get("tableName")
							+ ", " + param.get("dataName"));
					fw.write("\n");
					fw.write(line);
					continue;
				}
				if (line.contains("#{hiddenData}")) {
					line = line.replace("#{hiddenData}", ""); // 前导空格
					fw.write(line);
					fw.write("<input type=\"hidden\" name=\""
							+ this.dataObjectNameJava
							+ "\" class=\"nui-hidden\" />");
					// fw
					// .write("<input type=\"hidden\" name=\"item._entity\"
					// value=\""
					// + param.get("dataName")
					// + "\" class=\"nui-hidden\" />");
					fw.write("\n");
					continue;
				}
				if (line.contains("#{editData}")) {
					line = line.replace("#{editData}", ""); // 前导空格
					for (DataObject col : cols) {
						if (!"1".equals(col.get("showInEdit")))
							continue;

						fw.write(genCol(col, line));
						fw.write("\n");
					}
					continue;
				}
				if (line.contains("#{modelName}")) {
					line = line.replace("#{modelName}", this.dataObjectName);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{modelName2}")) {
					line = line.replace("#{modelName2}",
							this.dataObjectNameJava);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{pkKey}")) {
					line = line.replace("#{pkKey}", this.pkKey);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				fw.write(line);
				fw.write("\n");
			}

			src.close();
			fw.close();
		} catch (Exception e) {
			log.error("生成编辑页面时出错！", e);
		}
	}

	private void genAdd(Map param) {
		String path = SystemInfo.APP_WAR_PATH + "pub/meta/crud/";
		String srcPath = path + "template/item_add.jsp";
		String destPath = path + "item_add.jsp";
		try {
			BufferedReader src = new BufferedReader(new InputStreamReader(
					new FileInputStream(srcPath), Charset.forName("utf-8")));
			OutputStreamWriter fw = new OutputStreamWriter(
					new FileOutputStream(destPath), Charset.forName("utf-8"));
			String line = null;
			DataObject[] cols = (DataObject[]) param.get("items");
			while ((line = src.readLine()) != null) {
				if (line.contains("#{date}")) {
					line = line.replace("#{date}", new java.sql.Date(System
							.currentTimeMillis()).toString());
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{description}")) {
					line = line.replace("#{description}", param
							.get("tableName")
							+ ", " + param.get("dataName"));
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{modelName}")) {
					line = line.replace("#{modelName}", this.dataObjectName);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				// if (line.contains("#{hiddenData}")) {
				// line = line.replace("#{hiddenData}", ""); // 前导空格
				// fw.write(line);
				// fw
				// .write("<input type=\"hidden\" name=\"item._entity\"
				// value=\""
				// + param.get("dataName")
				// + "\" class=\"nui-hidden\" />");
				// fw.write("\n");
				// continue;
				// }
				if (line.contains("#{addData}")) {
					line = line.replace("#{addData}", ""); // 前导空格
					for (DataObject col : cols) {
						if (!"1".equals(col.get("showInAdd")))
							continue;

						fw.write(genCol(col, line));
						fw.write("\n");
					}
					continue;
				}
				fw.write(line);
				fw.write("\n");
			}

			src.close();
			fw.close();
		} catch (Exception e) {
			log.error("生成新增页面时出错！", e);
		}
	}

	private void genList(Map param) {
		String path = SystemInfo.APP_WAR_PATH + "pub/meta/crud/";
		String srcPath = path + "template/item_list.jsp";
		String destPath = path + "item_list.jsp";
		try {
			BufferedReader src = new BufferedReader(new InputStreamReader(
					new FileInputStream(srcPath), Charset.forName("utf-8")));
			OutputStreamWriter fw = new OutputStreamWriter(
					new FileOutputStream(destPath), Charset.forName("utf-8"));
			String line = null;
			DataObject[] cols = (DataObject[]) param.get("items");
			while ((line = src.readLine()) != null) {
				if (line.contains("#{date}")) {
					line = line.replace("#{date}", new java.sql.Date(System
							.currentTimeMillis()).toString());
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{description}")) {
					line = line.replace("#{description}", param
							.get("tableName")
							+ ", " + param.get("dataName"));
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{queryHiddenCondition}")) {
					line = line.replace("#{queryHiddenCondition}", ""); // 前导空格
					fw.write(line);
					// fw
					// .write("<input type=\"hidden\" name=\"item._entity\"
					// value=\""
					// + param.get("dataName")
					// + "\" class=\"nui-hidden\" />");
					fw.write("\n");
					continue;
				}
				if (line.contains("#{queryCondition}")) {
					line = line.replace("#{queryCondition}", ""); // 前导空格
					for (DataObject col : cols) {
						if (!"1".equals(col.get("showInList"))
								|| "NUMBER".equals(col.get("colType")))
							continue;

						fw.write(genCol(col, line));
						fw.write("\n");
					}
					continue;
				}
				if (line.contains("#{queryList}")) {
					line = line.replace("#{queryList}", ""); // 前导空格
					for (DataObject col : cols) {
						if (!"1".equals(col.get("showInList")))
							continue;

						fw.write(genColForGrid(col, line));
						fw.write("\n");
					}
					continue;
				}
				if (line.contains("#{modelName}")) {
					line = line.replace("#{modelName}", this.dataObjectName);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{modelName2}")) {
					line = line.replace("#{modelName2}",
							this.dataObjectNameJava);
					line = line.replace("#{pkKey}", this.pkKey);
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{modelNames}")) {
					line = line.replace("#{modelNames}",
							this.dataObjectNameJava + "s");
					fw.write(line);
					fw.write("\n");
					continue;
				}
				if (line.contains("#{pkKey}")) {
					line = line.replace("#{pkKey}", this.pkKey);
					fw.write(line);
					fw.write("\n");
					continue;
				}

				if (line.contains("</script>")) {
					fw.write(renderer.toString());
					fw.write("\n");
				}
				fw.write(line);
				fw.write("\n");
			}

			src.close();
			fw.close();
		} catch (Exception e) {
			log.error("生成查询页面时出错！", e);
		}
	}

	private StringBuffer renderer = new StringBuffer();

	private String pkKey = null;

	private String dataObjectName = null;

	private String dataObjectNameJava = null;

	private String dataObjectNote = null;

	private String genColForGrid(DataObject col, String pre) {
		StringBuilder sb = new StringBuilder();
		String dictTypeId = col.getString("dictTypeId");
		String name = getJavaIdentifier(col.getString("colName"));

		sb.append(pre).append("<div field=\"").append(name).append(
				"\" headerAlign=\"center\" allowSort=\"true\" ");
		String colType = col.getString("colType");
		colType = colType == null ? "" : colType;
		if (StringUtils.isNotEmpty(dictTypeId)) {
			sb.append(" dictTypeId=\"").append(dictTypeId).append("\" ");
			// renderer.append("\nfunction render_").append(name).append(
			// "(e){return nui.getDictText(\"").append(dictTypeId).append(
			// "\", e.row.").append(name).append(")}\n");
			// sb.append("renderer=\"render_").append(name).append("\"");
		} else if (colType.toUpperCase().startsWith("DATE")
				|| colType.toUpperCase().startsWith("TIMESTAMP")) {
			sb.append(" dateFormat=\"yyyy-MM-dd\" ");
		}
		sb.append(">").append(col.getString("colZhname")).append("</div>");

		return sb.toString();
	}

	private String genCol(DataObject col, String pre) {
		StringBuilder sb = new StringBuilder();
		String dictTypeId = col.getString("dictTypeId");
		String colType = col.getString("colType");
		colType = colType == null ? "" : colType;
		String colZhname = col.getString("colZhname");
		sb.append(pre).append("<label>").append(colZhname)
				.append("：</label>\n");
		if (StringUtils.isNotEmpty(dictTypeId)) {
			sb
					.append(pre)
					.append("<input name=\"")
					.append(this.dataObjectNameJava)
					.append(".")
					.append(getJavaIdentifier(col.getString("colName")))
					.append(
							"\" required=\"true\" class=\"nui-dictcombobox nui-form-input\" dictTypeId=\"")
					.append(dictTypeId).append("\" />\n");
		} else if (colType.toUpperCase().startsWith("DATE")
				|| colType.toUpperCase().startsWith("TIMESTAMP")) {
			sb
					.append(pre)
					.append("<input name=\"")
					.append(this.dataObjectNameJava)
					.append(".")
					.append(getJavaIdentifier(col.getString("colName")))
					.append(
							"\" required=\"true\" class=\"nui-datepicker nui-form-input\" />\n");
		} else {
			sb
					.append(pre)
					.append("<input name=\"")
					.append(this.dataObjectNameJava)
					.append(".")
					.append(getJavaIdentifier(col.getString("colName")))
					.append(
							"\" required=\"false\" class=\"nui-textbox nui-form-input\" vtype=\"maxLength:")
					.append(col.getLong("colLength")).append("\" />\n");
		}

		return sb.toString();
	}

	private static String getJavaIdentifier(String name) {
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		for (int i = 0, len = name.length(); i < len; i++) {
			Character ch = name.charAt(i);
			if ('_' == ch) {
				flag = true;
				continue;
			}
			if (flag) {
				sb.append(Character.toUpperCase(ch));
				flag = false;
				continue;
			}
			sb.append(Character.toLowerCase(ch));
		}

		return sb.toString();
	}

	/**
	 * @param src
	 * @param dest
	 * @return
	 */
	@Bizlet("将数据库字段属性复制为Bean属性")
	public static DataObject copyDbProperties(DataObject src, DataObject dest) {
		if (null == src || null == dest)
			return dest;
		for (int i = 0; i < 300; i++) {
			// src.getInstanceProperties().
		}

		return dest;
	}
}
