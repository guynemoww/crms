package com.bos.utp.dict;

import com.bos.utp.dict.dataset.EosDictEntry;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.foundation.PageCond;

/**
 * <pre>
 * Title: ������������
 * Description: �����ܵ����� 
 * </pre>
 * @author wangbing (mailto:service6@primeton.com)
 * @version 1.00.00
 * 
 */
/*
 * �޸���ʷ
 * $log$
 */
public interface IEosDictEntryService{

	/**
	 *
	 * @param eosDictEntry EosDictEntry
	 */
	public abstract void addEosDictEntry(EosDictEntry eosDictEntry);

	/**
	 *
	 * @param eosDictEntrys EosDictEntry[]
	 */
	public abstract void deleteEosDictEntry(EosDictEntry[] eosDictEntrys);

	/**
	 *
	 * @param eosDictEntry EosDictEntry[]
	 */
	public abstract void getEosDictEntry(EosDictEntry eosDictEntry);

	/**
	 *
	 * @param criteria CriteriaType
	 * @param page PageCond
	 * @return EosDictEntry[]
	 */
	public abstract EosDictEntry[] queryEosDictEntrys(CriteriaType criteriaType,
			PageCond pageCond);

	/**
	 *
	 * @param eosDictEntry EosDictEntry[]
	 */
	public abstract void updateEosDictEntry(EosDictEntry eosDictEntry);

}
