<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

	<!-- 根元素  -->
	<xsl:template match="/">
		<xsl:apply-templates select="ItemListReport" />
	</xsl:template>

	<!--主模板//-->
	<xsl:template match="ItemListReport">
		<xsl:processing-instruction name="cocoon-format">type="text/xslfo"</xsl:processing-instruction>
		<!--在此可以定义一些全局的风格信息，如字体等-->
		<fo:root font-family="SimSun" xmlns:fo="http://www.w3.org/1999/XSL/Format">
			<!--版面定义//-->
			<fo:layout-master-set>
				<fo:simple-page-master master-name="main" margin-top="1cm" margin-bottom="1cm" margin-left="1cm" margin-right="1cm">
					<!--主体//-->
					<fo:region-body margin-top="1cm" margin-bottom="1cm" />
					<!--页眉//-->
					<fo:region-before extent="1cm" />
					<!--页脚//-->
					<fo:region-after extent="1cm" />
				</fo:simple-page-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="main">
				<!--页眉显示内容-->
				<fo:static-content flow-name="xsl-region-before">
					<fo:block font-size="10pt" text-align="end" line-height="12pt"></fo:block>
				</fo:static-content>
				<!--页面主体内容-->
				<fo:flow flow-name="xsl-region-body">
					<!--报表体(若有多个部分内容，参照下面一行重复)-->
					<xsl:apply-templates select="Table" />
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>

	<!--表格数据//-->
	<xsl:template match="Table">
		<fo:block font-size="12pt">
			<fo:table table-layout="fixed" width="100%" border-collapse="separate"
				text-align="left" border-width="0.5pt" border-style="solid" space-before.optimum="12pt">
				<!-- 定义列（与实际列数严格一致） //-->
				<fo:table-column column-width="3.5cm" />
				<fo:table-column column-width="6cm" />
				<fo:table-column column-width="3.5cm" />
				<fo:table-column column-width="6cm" />
				<!-- 定义表头 //-->
				<fo:table-header text-align="center">
					<fo:table-row font-weight="bold" font-size="20pt" border-width="0.5pt" border-style="solid">
						<fo:table-cell border-color="black" border-width="0.2pt" border-style="solid" number-columns-spanned="4"  height="1.5cm" >
							<fo:block >贷款回单</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-header>
				<!-- 表格数据 //-->
				<fo:table-body >
					<fo:table-row space-before.optimum="3pt"  >
						<fo:table-cell border-color="black" border-width="0.2pt" border-style="solid" number-columns-spanned="4" padding-left="390px">
							<fo:block><xsl:value-of select="Rq" /></fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row space-before.optimum="3pt" >
						<fo:table-cell border-color="black" border-width="0.2pt" border-style="solid">
							<fo:block>借据编号：</fo:block>
						</fo:table-cell>
						<fo:table-cell border-color="black" border-width="0.2pt" border-style="solid">
							<fo:block><xsl:value-of select="Jjbh" /></fo:block>
						</fo:table-cell>
						<fo:table-cell border-color="black" border-width="0.2pt" border-style="solid">
							<fo:block>借款人名称：</fo:block>
						</fo:table-cell>
						<fo:table-cell border-color="black" border-width="0.2pt" border-style="solid">
							<fo:block><xsl:value-of select="Dkrmc" /></fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row space-before.optimum="3pt">
						<fo:table-cell border-color="black" border-width="0.2pt" border-style="solid">
							<fo:block>还款账号：</fo:block>
						</fo:table-cell>
						<fo:table-cell border-color="black" border-width="0.2pt" border-style="solid">
							<fo:block><xsl:value-of select="Hkzh" /></fo:block>
						</fo:table-cell>
						<fo:table-cell border-color="black" border-width="0.2pt" border-style="solid">
							<fo:block>还款户名：</fo:block>
						</fo:table-cell>
						<fo:table-cell border-color="black" border-width="0.2pt" border-style="solid">
							<fo:block><xsl:value-of select="Hkhm" /></fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row space-before.optimum="3pt">
						<fo:table-cell border-color="black" border-width="0.2pt" border-style="solid">
							<fo:block>利率（%）：</fo:block>
						</fo:table-cell>
						<fo:table-cell border-color="black" border-width="0.2pt" border-style="solid">
							<fo:block><xsl:value-of select="Ll" /></fo:block>
						</fo:table-cell>
						<fo:table-cell border-color="black" border-width="0.2pt" border-style="solid">
							<fo:block>偿还本金：</fo:block>
						</fo:table-cell>
						<fo:table-cell border-color="black" border-width="0.2pt" border-style="solid">
							<fo:block><xsl:value-of select="Chbj" /></fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row space-before.optimum="3pt">
						<fo:table-cell border-color="black" border-width="0.2pt" border-style="solid">
							<fo:block>偿还利息：</fo:block>
						</fo:table-cell>
						<fo:table-cell border-color="black" border-width="0.2pt" border-style="solid">
							<fo:block><xsl:value-of select="Chlx" /></fo:block>
						</fo:table-cell>
						<fo:table-cell border-color="black" border-width="0.2pt" border-style="solid">
							<fo:block>偿还罚息：</fo:block>
						</fo:table-cell>
						<fo:table-cell border-color="black" border-width="0.2pt" border-style="solid">
							<fo:block><xsl:value-of select="Chfx" /></fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row space-before.optimum="3pt">
						<fo:table-cell border-color="black" border-width="0.2pt" border-style="solid" number-columns-spanned="4" height="2cm">
							<fo:block>校验说明：</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
		</fo:block>
		<fo:block font-size="12pt" break-after="page">
			<fo:table table-layout="fixed" width="100%" border-collapse="separate">
				<fo:table-column column-width="3cm" />
				<fo:table-column column-width="6cm" />
				<fo:table-column column-width="3cm" />
				<fo:table-column column-width="6cm" />
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell text-align="start">
							<fo:block><xsl:text></xsl:text></fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="start">
							<fo:block><xsl:text></xsl:text></fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="start">
							<fo:block><xsl:text>复核</xsl:text></fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="start">
							<fo:block><xsl:text>经办：</xsl:text><xsl:value-of select="Jb" /></fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
		</fo:block>
	</xsl:template>

</xsl:stylesheet>