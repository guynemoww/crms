/**
 * 
 */
package com.bos.conPrint;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bos.conPrint.product.ConInfoLoad;
import com.bos.conPrint.product.ConInfoLoad_Product;
import com.eos.system.annotation.Bizlet;

/**
 * @author Administrator
 * @date 2017-09-05 17:56:21
 * 
 */
@Bizlet("")
public class ConInfoLoadService {

	private Map<String, ConInfoLoad> bigTypeMap;// 根据大类区分合同

	private List<ConInfoLoad_Product> productList; // 根据产品代码区分

	private Set<String> unprints;// 不打印数据过滤

	public Map<String, Object> loadReport(Map<String, String> param) {
		String conNum = param.get("subContractNum");
		String conType = param.get("subContractType");
		String productType = param.get("productType");
		if (conNum == null) {
			throw new RuntimeException("合同打印错误，没有获取到合同编号");
		}
		if (conType == null) {
			throw new RuntimeException("合同打印错误，没有获取到合同类型");
		}
		IConInfoLoad load = bigTypeMap.get(conType);
		if (load != null) {
			return load.loadReport(param);
		}
		if (unprints.contains(conType) || productType == null) {
			return null;
		}
		for (ConInfoLoad_Product config : productList) {
			if (config.contains(productType)) {
				return config.loadReport(param);
			}
		}
		return null;
	}

	/**
	 * 获取从合同名称
	 * 
	 * @param conType
	 *            从合同类型
	 * @param params
	 * @return
	 */
	public String getReportNameCN(String conType, String... params) {
		ConInfoLoad load = bigTypeMap.get(conType);
		if (load != null) {
			return load.getReportNameCN(params);
		}
		return null;
	}

	/**
	 * 获取主合同名称
	 * 
	 * @param productType
	 *            主合同产品类型
	 * @return
	 */
	public String getReportNameCN(String productType) {
		if (productType == null || (productType = productType.trim()).isEmpty()) {
			return null;
		}
		for (ConInfoLoad_Product config : productList) {
			if (config.contains(productType)) {
				return config.getReportNameCN(productType);
			}
		}
		return null;
	}

	public Map<String, ConInfoLoad> getBigTypeMap() {
		return bigTypeMap;
	}

	public void setBigTypeMap(Map<String, ConInfoLoad> bigTypeMap) {
		this.bigTypeMap = bigTypeMap;
	}

	public List<ConInfoLoad_Product> getProductList() {
		return productList;
	}

	public void setProductList(List<ConInfoLoad_Product> productList) {
		this.productList = productList;
	}

	public Set<String> getUnprints() {
		return unprints;
	}

	public void setUnprints(Set<String> unprints) {
		this.unprints = unprints;
	}

}
