package com.bos.pub;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;

import com.bos.batch.DateUtil;
import com.bos.utp.tools.ChangeUtil;
import com.bos.utp.tools.SystemInfo;
import com.eos.data.xpath.XPathLocator;
import com.eos.foundation.data.DataContextUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.foundation.eoscommon.LogUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.example.excel.UtilConfiguration;

import commonj.sdo.DataObject;

@Bizlet("")
public class WordUtil {

	private XPathLocator xpath = XPathLocator.newInstance();

	private String get(String key, DataObject params) {
		Object obj = null;
		try {
			key = key.replaceAll("\\.", "/");
			if (key.contains("}") && key.contains("${")) {
				if (key.indexOf("}") < key.indexOf("${"))
					key = "${" + key + "}";
				Pattern pattern = Pattern.compile("\\$\\{([^}]+)\\}");
				Matcher matcher = pattern.matcher(key);
				while (matcher.find()) {
					String key2 = matcher.group(1);
					String original = matcher.group();
					Object value = null;
					if (key.startsWith("s:") || key.startsWith("r:") || key.startsWith("f:") || key.startsWith("m:")) {
						try {
							value = DataContextUtil.get(key);
						} catch (Exception e) {// 经测试，无论是s、r、f都无法获取到
							if (key.startsWith("s:")) {
								value = DataContextUtil.get(key.replaceFirst("s", "m"));
							}
						}
					} else {
						if (key2.contains(">") || key2.contains("<") || key2.contains("==")) {
							String op = (key2.contains(">") ? ">" : key2.contains("<") ? "<" : key2.contains("==") ? "==" : "");
							if (op.length() > 0) {
								int idx = key2.indexOf(op);
								String pre = key2.substring(0, idx).trim();
								String sur = key2.substring(idx + 1).trim();
								boolean isTrue = false;
								if (CharUtils.isAsciiAlpha(pre.charAt(0))) {
									Object pre2 = xpath.getValue(params, pre);
									if (pre2 == null) {
										if ("==".equals(op) && sur.equals("null"))
											isTrue = true;
									} else if (pre2 instanceof Number && StringUtils.isAsciiPrintable(sur) && StringUtils.isNumeric(sur)) {
										if ("==".equals(op) && Math.abs(((Number) pre2).doubleValue() - Double.valueOf(sur)) < 0.000001)
											isTrue = true;
										else if ("<".equals(op) && ((Number) pre2).doubleValue() < Double.valueOf(sur))
											isTrue = true;
										else if (">".equals(op) && ((Number) pre2).doubleValue() > Double.valueOf(sur))
											isTrue = true;
									} else {
										if (sur.equals(pre2))
											isTrue = true;
									}
								}
								if (isTrue)
									value = "√";
							} else {
								value = xpath.getValue(params, key2);
							}
						} else {
							// value = xpath.getValue(params, key2);
							if (key2.contains("(") && key2.endsWith(")")) {
								String key3 = key2.substring(key2.indexOf("(") + 1, key2.length() - 1);
								// System.out.println(key2);
								value = xpath.getValue(params, key2.substring(0, key2.indexOf("(")));
								String[] arr = key3.split(",");
								if (value != null && value instanceof BigDecimal) {
									// value =
									// ChangeUtil.formatDouble(((BigDecimal)
									// value)
									// .doubleValue(),
									// Integer.valueOf(arr[0].trim()));
									value = ChangeUtil.formatDoubleWithComma(((BigDecimal) value).doubleValue(), Integer.valueOf(arr[0].trim()));
								}
							} else {
								value = xpath.getValue(params, key2);
							}
						}
					}
					if (null == value)
						value = "";
					if (value instanceof Boolean) {
						if (((Boolean) value).booleanValue() == true)
							value = "√";
						else
							value = "";
					} else {
						key = key.replace(original, value.toString());
					}
				}
				return key;
			}
			if (key.startsWith("s:") || key.startsWith("r:") || key.startsWith("f:") || key.startsWith("m:")) {
				try {
					obj = DataContextUtil.get(key);
				} catch (Exception e) {// 经测试，无论是s、r、f都无法获取到
					if (key.startsWith("s:")) {
						obj = DataContextUtil.get(key.replaceFirst("s", "m"));
					}
				}
				if (null != obj) {
					if (obj instanceof Boolean) {
						if (((Boolean) obj).booleanValue() == true)
							obj = "√";
						else
							obj = "";
						return obj.toString();
					} else {
						return obj.toString();
					}
				}
			}
			if (key.contains(">") || key.contains("<") || key.contains("==")) {
				String op = (key.contains(">") ? ">" : key.contains("<") ? "<" : key.contains("==") ? "==" : "");
				if (op.length() > 0) {
					int idx = key.indexOf(op);
					String pre = key.substring(0, idx).trim();
					String sur = key.substring(idx + ("==".equals(op) ? 2 : 1)).trim();
					boolean isTrue = false;
					if (CharUtils.isAsciiAlpha(pre.charAt(0))) {
						Object pre2 = xpath.getValue(params, pre);
						if (pre2 == null) {
							if ("==".equals(op) && sur.equals("null"))
								isTrue = true;
						} else if (pre2 instanceof Number && StringUtils.isAsciiPrintable(sur) && StringUtils.isNumeric(sur)) {
							if ("==".equals(op) && Math.abs(((Number) pre2).doubleValue() - Double.valueOf(sur)) < 0.000001)
								isTrue = true;
							else if ("<".equals(op) && ((Number) pre2).doubleValue() < Double.valueOf(sur))
								isTrue = true;
							else if (">".equals(op) && ((Number) pre2).doubleValue() > Double.valueOf(sur))
								isTrue = true;
						} else {
							if (sur.equals(pre2))
								isTrue = true;
						}
					}
					if (isTrue)
						obj = "√";
				} else {
					obj = xpath.getValue(params, key);
				}
			} else {
				if (key.contains("(") && key.endsWith(")")) {
					String key2 = key.substring(key.indexOf("(") + 1, key.length() - 1);
					// System.out.println(key2);
					obj = xpath.getValue(params, key.substring(0, key.indexOf("(")));
					String[] arr = key2.split(",");
					if (obj != null && obj instanceof BigDecimal) {
						// obj = ChangeUtil.formatDouble(((BigDecimal) obj)
						// .doubleValue(), Integer.valueOf(arr[0].trim()));
						obj = ChangeUtil.formatDoubleWithComma(((BigDecimal) obj).doubleValue(), Integer.valueOf(arr[0].trim()));
					}
				} else {
					obj = xpath.getValue(params, key);
				}
			}
		} catch (Exception e) {
			obj = null;
			if (key.startsWith("s:")) {
				obj = DataContextUtil.get(key.replaceFirst("s", "m"));
			}
		}
		if (null == obj)
			return "";
		if (obj instanceof Boolean) {
			if (((Boolean) obj).booleanValue() == true)
				obj = "√";
			else
				obj = "";
			return obj.toString();
		}
		return obj.toString();

		// String[] arr = key.split("\\.");
		// if (params.containsKey(arr[0]) == false)
		// return "";
		//
		// Object value = params.get(arr[0]);
		// if (arr.length > 1 && value != null) {
		// try {
		// String str = StringUtils.join(arr, ".");
		// value = PropertyUtils.getProperty(value, str.substring(str
		// .indexOf(".") + 1));
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// if (value == null) {
		// value = "";
		// }
		//
		// return value.toString();
	}

	@Bizlet("读取word文档，根据传入的map中的key，替换“${key}”为map中的value，实现word文档修改的目的，返回生成的临时文件路径")
	public String word2007TextReplace(String wordTemplate, DataObject params) throws Exception {
		String formatTime = DateUtil.formatTime();
		System.out.println("formatTime:" + formatTime);
		LogUtil.logDebug("开始生成word文档！", null, null);
		String filename = wordTemplate;
		if (new File(wordTemplate).isFile() == false) {
			if (wordTemplate.startsWith(SystemInfo.FILE_SEPARATOR) == false) {
				wordTemplate = SystemInfo.FILE_SEPARATOR + wordTemplate;
			}
			wordTemplate = SystemInfo.APP_WAR_PATH + "document" + SystemInfo.FILE_SEPARATOR + "docx" + wordTemplate;
		}
		if (new File(wordTemplate).isFile() == false || null == params) {
			throw new Exception("没有传入word模板路径，或路径错误！[" + wordTemplate + "]");
		}
		String tempfile = SystemInfo.APP_WAR_PATH + "document" + SystemInfo.FILE_SEPARATOR + "docx" + SystemInfo.FILE_SEPARATOR + "temp";
		File out = new File(tempfile);
		if (out.exists() == false) {
			out.mkdirs();
		}
		Object userid = null;
		try {
			userid = DataContextUtil.get("m:userObject/userId");
		} catch (Exception e) {
			userid = DataContextUtil.get("s:userObject/userId");
		}
		if (null == userid || userid.toString().length() == 0) {
			userid = System.currentTimeMillis();
		}
		if (null != params.getString("filename") && params.getString("filename").length() > 0) {
			filename = params.getString("filename");
		}
		if (filename.contains("/"))
			filename = filename.substring(filename.lastIndexOf("/") + 1);
		if (filename.contains("\\"))
			filename = filename.substring(filename.lastIndexOf("\\") + 1);
		if (filename.endsWith(".docx") == false) {
			filename += ".docx";
		}
		params.setString("filename", filename);
		if (null != params.getString("isContractDown") && params.getString("isContractDown").equals("1")) {
			tempfile = tempfile + SystemInfo.FILE_SEPARATOR + formatTime + filename;
		} else if (null != params.getString("CHECK_NUM") && !"".equals(params.getString("CHECK_NUM"))) {
			System.out.println("CHECK_NUM--->" + params.getString("CHECK_NUM"));
			tempfile = tempfile + SystemInfo.FILE_SEPARATOR + params.getString("CHECK_NUM") + "_" + formatTime + filename;
		} else if (null != params.getString("CHANGE_NUM") && !"".equals(params.getString("CHANGE_NUM"))) {
			System.out.println("CHANGE_NUM--->" + params.getString("CHANGE_NUM"));
			tempfile = tempfile + SystemInfo.FILE_SEPARATOR + params.getString("CHANGE_NUM") + "_" + formatTime + filename;
		} else {
			tempfile = tempfile + SystemInfo.FILE_SEPARATOR + userid + "_" + formatTime + filename;
		}
		if (params.getString("isDownload") != null && "false".equals(params.getString("isDownload"))) {
			LogUtil.logDebug("开始生成word文档，不下载！", null, null);
			try {
				word2007TextReplace(new FileInputStream(wordTemplate), new FileOutputStream(tempfile), params);
				LogUtil.logDebug("结束生成word文档，返回历史文件路径：" + tempfile + "！", null, null);
			} catch (Exception e) {
				LogUtil.logError("生成word文档发生异常，异常信息为：" + e.getMessage(), e, null);
				throw new Exception(e.getMessage());
			}
		} else {
			LogUtil.logDebug("开始生成word文档，并下载！", null, null);
			try {
				word2007TextReplace(new FileInputStream(wordTemplate), null, params);
				LogUtil.logDebug("结束生成word文档，开始下载！", null, null);
			} catch (Exception e) {
				LogUtil.logError("生成word文档并下载发生异常，异常信息为：" + e.getMessage(), e, null);
				throw new Exception(e.getMessage());
			}
		}
		LogUtil.logDebug("生成word文档结束，文档路径:" + tempfile + "！", null, null);
		return tempfile;
	}

	@Bizlet("读取word文档，根据传入的map中的key，替换“${key}”为map中的value，实现word文档修改的目的")
	public void word2007TextReplace(String wordTemplate, OutputStream wordResultOutputStream, DataObject params) throws Exception {
		if (new File(wordTemplate).isFile() == false) {
			if (wordTemplate.startsWith(SystemInfo.FILE_SEPARATOR) == false) {
				wordTemplate = SystemInfo.FILE_SEPARATOR + wordTemplate;
			}
			wordTemplate = SystemInfo.APP_WAR_PATH + "document" + SystemInfo.FILE_SEPARATOR + "docx" + wordTemplate;
		}
		if (new File(wordTemplate).isFile() == false || null == wordResultOutputStream || null == params) {
			new Exception("没有传入word模板路径").printStackTrace();
			return;
		}
		word2007TextReplace(new FileInputStream(wordTemplate), wordResultOutputStream, params);
	}

	private void doPara(XWPFParagraph para, DataObject params) {
		List<XWPFRun> runs = para.getRuns();
		// System.out.println("xxx========" + para.getText());
		Pattern pattern = Pattern.compile("\\$\\{([^}]+)\\}");
		Matcher matcher = null;

		final String ch1 = "$";
		final String ch2 = "{";
		final String ch3 = "}";
		int pos1 = -1, pos2 = -1, pos3 = -1;
		for (int i = 0; i < runs.size(); i++) {
			XWPFRun run = runs.get(i);
			String text = run.getText(0);
			// System.out.println(text);
			if (null == text)
				continue;

			String afterreplace = new String(text);
			matcher = pattern.matcher(text);
			boolean find = false;
			while (matcher.find()) {
				// System.out.println(matcher.group()); // 如：${abcd}
				// System.out.println(matcher.group(1));// 如：abcd
				String key = matcher.group(1);
				String valuestr = this.get(key, params).toString();
				String original = matcher.group();
				afterreplace = afterreplace.replace(original, valuestr);
				find = true;
			}
			if (find) {
				setText(run, afterreplace);
			} else {
				if (pos1 == -1 && text.startsWith(ch1)) {
					pos1 = i;
				}
				if (pos1 != -1 && pos2 == -1 && text.contains(ch2)) {
					pos2 = i;
				}
				if (pos1 != -1 && pos2 != -1 && pos3 == -1 && text.endsWith(ch3)) {
					pos3 = i;
				}
				if (pos1 != -1 && pos2 != -1 && pos3 != -1) {
					run = runs.get(pos1);
					String key = "";// runs.get(pos1).getText(0)+runs.get(pos2).getText(0)+runs.get(pos3).getText(0);
					for (int k = pos1; k <= pos3; k++) {
						key += runs.get(k).getText(0);
						runs.get(k).setText("", 0);
					}
					key = key.substring(2, key.length() - 1);
					String valuestr = this.get(key, params).toString();
					setText(runs.get(pos1), valuestr);
					pos1 = -1;
					pos2 = -1;
					pos3 = -1;
				}
			}
		}
	}

	private void setText(XWPFRun run, String text) {
		if (text.contains("\r\n")) {
			String[] temps = text.split("\r\n");
			run.setText("", 0);
			for (String t : temps) {
				run.setText(t);
				run.addBreak();
			}
		} else {
			run.setText(text, 0);
		}
	}

	private void doTable(XWPFTable table, DataObject params) {
		try {
			Map<String, Object> dynamicCellsMap = null;
			List<Map<String, Object>> dynamicCellsMapList = new ArrayList<Map<String, Object>>();
			List<String[]> dynamicCells = null;
			String dynamicColor = null;
			int rowscnt = table.getNumberOfRows();
			for (int j = 0; j < rowscnt; j++) {
				XWPFTableRow row = table.getRow(j);
				dynamicCells = null;
				int fromrow = -1;
				List<XWPFTableCell> cells = row.getTableCells();
				for (int k = 0; k < cells.size(); k++) {
					XWPFTableCell cell = cells.get(k);
					{
						List<XWPFTable> listXWPFTable = cell.getTables();
						for (int m = 0; m < listXWPFTable.size(); m++) {
							this.doTable(listXWPFTable.get(m), params);
						}
					}
					String text = cell.getText();
					boolean isDyn = false;
					if (null != text) {
						text = text.replaceAll("\\s", ""); // 删除空白字符
						text = text.replaceAll("\\.", "/");
						if (text.startsWith("${") && text.endsWith("}") && text.indexOf("${", 2) < 0 && text.contains(":") == false) {
							// 判断是否列表输出
							String tmp = text.substring(text.indexOf("${") + 2, text.indexOf("}"));
							String[] arr = tmp.split("/");
							Object obj = params.get(arr[0]);
							if (obj != null) {
								if (obj instanceof List || obj.getClass().isArray()) {
									if (arr.length < 2 || arr[0].length() < 1 || arr[1].length() < 1) {
										isDyn = false;
									} else {
										isDyn = true;
										if (null == dynamicCells) {
											dynamicCells = new ArrayList<String[]>();
											dynamicColor = cell.getColor();
										}
										dynamicCells.add(arr);
										if (null != dynamicCells && fromrow < 0) {
											fromrow = j;
											int fromcell = k;
											dynamicCellsMap = new HashMap<String, Object>();
											dynamicCellsMap.put("dynamicCells", dynamicCells);
											dynamicCellsMap.put("dynamicColor", dynamicColor);
											dynamicCellsMap.put("fromrow", fromrow);
											dynamicCellsMap.put("fromcell", fromcell);
											dynamicCellsMapList.add(dynamicCellsMap);
										}
									}
								}
							}
						}
					}
					if (isDyn == false) {// 非列表输出
						List<XWPFParagraph> listXWPFParagraph = cell.getParagraphs();
						for (int m = 0; m < listXWPFParagraph.size(); m++) {
							this.doPara(listXWPFParagraph.get(m), params);
						}
					}
				}
			}
			for (int len = dynamicCellsMapList.size(), i = len - 1; i >= 0; i--) {
				Map<String, Object> map = dynamicCellsMapList.get(i);
				dynamicCells = (List<String[]>) map.get("dynamicCells");
				if (null == dynamicCells || dynamicCells.size() < 1)
					continue;
				// 在表格中插入新行
				Object param = this.get(dynamicCells.get(0)[0], params);
				boolean listflag = false;
				boolean arrayflag = false;
				for (int j = 0; j < dynamicCells.size(); j++) {
					param = params.get(dynamicCells.get(j)[0]);
					if (param instanceof List || param instanceof DataObject[]) {
						listflag = param instanceof List;
						arrayflag = param instanceof DataObject[];
						break;
					}
				}
				if (param == null || false == (arrayflag || listflag)) {
					continue;// 不是数据也不是list
				}

				int fromrow = (Integer) map.get("fromrow");
				int fromcell = (Integer) map.get("fromcell");
				dynamicColor = (String) map.get("dynamicColor");
				int size = 0;
				if (arrayflag) {
					size = ((DataObject[]) param).length;
				} else if (listflag) {
					size = ((List) param).size();
				}

				CTTcPr cTTcPr = null;
				for (int j = 0; j < size; j++) { // 新增动态行
					XWPFTableRow row = null;
					XWPFTableRow rowPre = null;
					if (j > 0) {
						rowPre = table.getRow(fromrow);
						row = table.insertNewTableRow(++fromrow);
					} else {
						row = table.getRow(fromrow);
						for (int k = 0; k < row.getTableCells().size(); k++)
							for (int m = row.getCell(k).getParagraphs().size() - 1; m >= 0; m--) {
								row.getCell(k).removeParagraph(m);
								this.setCellText(row.getCell(k), "");
							}
					}
					if (null == row)
						break;
					for (int k = fromcell; k < dynamicCells.size(); k++) { // 遍历单元格
						// 在单元格中插入段落
						String[] arr = dynamicCells.get(k);
						if (params.get(arr[0]) == null) {
							if (j == 0) {
								for (int m = row.getCell(k).getParagraphs().size() - 1; m >= 0; m--)
									row.getCell(k).removeParagraph(m);
							} else {
								row.addNewTableCell();
								this.setCellText(row.getCell(k), " ");
							}
							continue;
						}
						Object obj = null;
						try {
							if (params.get(arr[0]) instanceof List == false && params.get(arr[0]).getClass().isArray() == false) {
								if (j > 0) {
									row.addNewTableCell();
								}
								this.setCellText(row.getCell(k), " ");
								continue;
							}
							{
								String[] arr2 = new String[arr.length];
								for (int m = 0; m < arr.length; m++) {
									arr2[m] = arr[m];
								}
								arr2[0] += "[" + (j + 1) + "]";
								// "xpath 从1开始"
								String str = StringUtils.join(arr2, "/");
								obj = this.get(str, params);
								// 列表前加了序号但是数据库查出来没有的，在doc里给序号赋变量arrays*.INDEXS--lpc
								// 20160831
								if (arr2[1].equals("INDEXS") || arr2[1].equals("INDEXS")) {
									obj = j + 1;
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
							continue;
						}

						if (j > 0) {
							row.addNewTableCell();
						}
						if (null == obj)
							obj = " ";
						this.setCellText(row.getCell(k), obj.toString());
						if (null != dynamicColor) {
							row.getCell(k).setColor(dynamicColor);
						}
						if (null != rowPre && rowPre.getTableCells().size() > k && null != rowPre.getCell(k).getCTTc())
							cTTcPr = rowPre.getCell(k).getCTTc().getTcPr();
						if (null == cTTcPr) {
							cTTcPr = (null != row.getCell(k).getCTTc() && null != row.getCell(k).getCTTc().getTcPr()) ? row.getCell(k).getCTTc().getTcPr() : row.getCell(k).getCTTc().addNewTcPr();
						}
						// if (row.getCell(k).getCTTc().isSetTcPr())
						// row.getCell(k).getCTTc().unsetTcPr();
						if (null == row.getCell(k).getCTTc().getTcPr())
							row.getCell(k).getCTTc().setTcPr(cTTcPr);
					}// 遍历单元格end
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Map<String, String> defaultParam;

	private void setPrintDefaultParam(DataObject params) {
		if (defaultParam == null) {
			synchronized ("printDefaultParamInit") {
				if (defaultParam == null) {
					defaultParam = new HashMap<String, String>();
					defaultParam.put("bank_name", ConfigurationUtil.getContributionConfig(UtilConfiguration.CONTRIBUTION_ABFRAME_UTILS, "back_config", "bank_9999", "bank_name"));
				}
			}
		}
		for (Map.Entry<String, String> entry : defaultParam.entrySet()) {
			setDataObjectParam(params, entry.getKey(), entry.getValue());
		}
	}

	private void setDataObjectParam(DataObject param, String key, String value) {
		if (key.contains(".")) {
			String[] keys = key.split("\\.");
			DataObject tempParam;
			for (int i = 0; i < keys.length - 1; i++) {
				tempParam = param.getDataObject(keys[i]);
				if (tempParam == null) {
					tempParam = DataObjectUtil.createDataObject("commonj.sdo.DataObject");
					param.setDataObject(keys[i], tempParam);
				}
				param = tempParam;
			}
			setDataObjectParam(param, keys[keys.length - 1], value);
		} else {
			param.setString(key, value);
		}
	}

	/**
	 * @param wordTemplateInputStream
	 *            word 模板文件，其中要替换的字符串格式为：${字符串}，<br/>
	 *            其中的字符串以点号分隔，第一个点号前表示map中的key，可以为list或普通BO对象
	 * @param wordResultOutputStream
	 *            转换后的word文档将写入此输出流中
	 * @param params
	 *            支持list等对象
	 * @throws Exception
	 * @description 读取word文档，根据传入的map中的key，替换“${key}”为map中的value，实现word文档修改的目的<br/>
	 *              注意：word模板中要替换的字符串要连续书写，从左至右，中间不挪动鼠标/光标位置，不设置格式及字体等。
	 */
	@Bizlet("读取word文档，根据传入的map中的key，替换“${key}”为map中的value，实现word文档修改的目的")
	public void word2007TextReplace(InputStream wordTemplateInputStream, OutputStream wordResultOutputStream, DataObject params) throws Exception {
		setPrintDefaultParam(params);
		XWPFDocument doc = new XWPFDocument(wordTemplateInputStream);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		OutputStream out2 = null;
		try {
			List<IBodyElement> list = doc.getBodyElements();
			for (IBodyElement ele : list) {
				if (ele instanceof XWPFParagraph) {
					XWPFParagraph para = (XWPFParagraph) ele;
					this.doPara(para, params);
				}
			}

			List<XWPFTable> tables = doc.getTables();
			for (int i = 0; i < tables.size(); i++) {
				doTable(tables.get(i), params);
			}
			// -----------页眉处理
			List<XWPFHeader> headers = doc.getHeaderList();
			for (XWPFHeader h : headers) {
				List<XWPFParagraph> paras = h.getListParagraph();
				for (XWPFParagraph p : paras) {
					doPara(p, params);
				}
			}
			// ----------页脚处理
			List<XWPFFooter> footers = doc.getFooterList();
			for (XWPFFooter f : footers) {
				List<XWPFParagraph> paras = f.getListParagraph();
				for (XWPFParagraph p : paras) {
					doPara(p, params);
				}
			}

			doc.write(out);
			if (null != wordResultOutputStream) {
				doc.write(wordResultOutputStream);
				wordResultOutputStream.flush();
				wordResultOutputStream.close();
			} else {
				String filename = "文档.docx";
				if (StringUtils.isNotEmpty(params.getString("filename"))) {
					filename = params.getString("filename");
					if (filename.contains("/"))
						filename = filename.substring(filename.lastIndexOf("/") + 1);
					if (filename.contains("\\"))
						filename = filename.substring(filename.lastIndexOf("\\") + 1);
				}

				// InputStream in3 = new
				// FileInputStream("E:\\bos\\01java\\crms\\eos-web\\document\\pdf\\pay_info.docx");
				ServletResponse res = GitUtil.getResponse();
				res.reset();
				res.resetBuffer();
				// res.setContentType("application/vnd.ms-excel");
				res.setContentType("application/vnd.msword");
				// res.setContentType("application/vnd.ms-word.document.12");
				// res
				// .setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
				// res.setContentType("application/pdf");
				((HttpServletResponse) res).setHeader("Content-disposition", "attachment;filename=\"" + java.net.URLEncoder.encode(filename, "UTF-8") + "\"");
				// ((HttpServletResponse) res).setHeader("Content-disposition",
				// "inline;filename=\""
				// + java.net.URLEncoder.encode(filename, "UTF-8")
				// + "\"");
				out2 = res.getOutputStream();
				out.writeTo(out2);
				// res.setContentLength(in3.available());
				// byte[] arr=new byte[in3.available()];
				// in3.read(arr);
				// out2.write(arr);
				// out2.flush();
				// out2.close();
				// out.flush();
				// out.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		} finally {// 增加关闭输入流的方法，无论成功还是失败，都会关闭数据流
			if (out2 != null) {
				out2.flush();
				out2.close();
			}
			if (out != null) {
				out.flush();
				out.close();
			}
			wordTemplateInputStream.close();
			if (wordResultOutputStream != null) {
				wordResultOutputStream.flush();
				wordResultOutputStream.close();
			}

		}
	}

	private void setCellText(XWPFTableCell cell, String text) {
		if (null == cell)
			return;

		for (int m = cell.getParagraphs().size() - 1; m >= 0; m--)
			cell.removeParagraph(m);

		XWPFParagraph pa = null;
		if (cell.getParagraphs().size() == 0)
			pa = cell.addParagraph();
		else
			pa = cell.getParagraphs().get(0);
		XWPFRun run = pa.createRun();
		run.setText(text, 0);
		pa.setIndentationFirstLine(0);
		// cell.setText(text);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String key = "${aa}";
		key = key.substring(2, key.length() - 1);
		// System.out.println(key);

		// System.out.println(DataContextUtil.get("s:userObject"));
		DataObject params = DataObjectUtil.createDataObject("com.bos.utp.dataset.organizationview.OmEmployeeQuery");
		params.set("abc", 12347890);
		params.set("def", 999);

		new WordUtil().word2007TextReplace("D:\\Primeton\\Platform\\ide\\eclipse\\workspace\\crms\\eos-web\\document\\docx\\test.docx", params);
	}

}
