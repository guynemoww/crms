package com.bos.pub;

import java.util.ArrayList;
import java.util.List;

public class EntityLogTrade {
	/**
	 * 交易名称
	 */
	private String name;

	/**
	 * 显示名称
	 */
	private String displayName;

	/**
	 * 交易涉及的表
	 */
	private List<Table> tables = new ArrayList<Table>();

	public Table createTable() {
		return new Table();
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Table> getTables() {
		return tables;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

	/**
	 * 表
	 */
	public class Table {
		/**
		 * 物理名称
		 */
		private String name;

		/**
		 * 显示名称
		 */
		private String displayName;

		private String sql;

		/**
		 * 列
		 */
		private List<Column> cols = new ArrayList<Column>();

		public Column createColumn() {
			return new Column();
		}

		public List<Column> getCols() {
			return cols;
		}

		public void setCols(List<Column> cols) {
			this.cols = cols;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSql() {
			return sql;
		}

		public void setSql(String sql) {
			this.sql = sql;
		}
	}

	/**
	 * 字段
	 */
	public class Column {
		/**
		 * 字段名称
		 */
		private String name;

		/**
		 * 显示名称
		 */
		private String displayName;

		/**
		 * 业务字典类型
		 */
		private String dictTypeId;

		public String getDictTypeId() {
			return dictTypeId;
		}

		public void setDictTypeId(String dictTypeId) {
			this.dictTypeId = dictTypeId;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}
}
