package com.bos.utp.dict;

import com.bos.utp.dict.dataset.EosDictType;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.foundation.PageCond;
import commonj.sdo.DataObject;

public interface IEosDictTypeService{

	/**
	 * @param eosDictType EosDictType
	 */
	public abstract void addEosDictType(DataObject eosDictType);

	/**
	 *
	 * @param eosDictTypes EosDictType[]
	 */
	public abstract void deleteEosDictType(DataObject[] eosDictTypes);

	/**
	 *
	 * @param eosDictType EosDictType[]
	 */
	public abstract void getEosDictType(DataObject eosDictType);

	/**
	 *
	 * @param criteria CriteriaType
	 * @param page PageCond
	 * @return EosDictType[]
	 */
	public abstract EosDictType[] queryEosDictTypes(CriteriaType criteriaType,
			DataObject pageCond);

	/**
	 *
	 * @param eosDictType EosDictType[]
	 */
	public abstract void updateEosDictType(DataObject eosDictType);
	
	
	/**
	 *
	 * @param eosDictType EosDictType[]
	 */
	public abstract void saveEosDictType(DataObject[] eosDictType, DataObject criteria);

}
