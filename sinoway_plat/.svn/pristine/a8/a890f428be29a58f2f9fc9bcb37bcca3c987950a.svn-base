package com.sinoway.stm.common;

import java.util.Map;

import com.yzj.wf.plat.entity.WfCfgdefPrdinfo;
import com.yzj.wf.plat.entity.WfCfgrefCompusrprd;
import com.yzj.wf.plat.entity.WfCfgrefPrddetil;

/**
 * map和对象之间的相互转化
 * @author xiehao
 *
 */
public class ParseEntity {
	public static Object parseMapToEntity(Map<String,String> map,Object obj){
		if(obj instanceof WfCfgrefCompusrprd){
			((WfCfgrefCompusrprd) obj).setId(map.get("usrprdid"));
			((WfCfgrefCompusrprd) obj).setIsdisp(map.get("isdisp"));
			((WfCfgrefCompusrprd) obj).setPeoplecode(map.get("peoplecode"));
			((WfCfgrefCompusrprd) obj).setPrdcod(map.get("prdcod"));
			((WfCfgrefCompusrprd) obj).setSta(map.get("sta"));
			((WfCfgrefCompusrprd) obj).setUsrtype(map.get("usrtype"));
		}else if(obj instanceof WfCfgdefPrdinfo){
			((WfCfgdefPrdinfo) obj).setId(map.get("id"));
			((WfCfgdefPrdinfo) obj).setPrdcod(map.get("prdcod"));
			((WfCfgdefPrdinfo) obj).setPrdnam(map.get("prdnam"));
			((WfCfgdefPrdinfo) obj).setParntcod(map.get("parntcod"));
			((WfCfgdefPrdinfo) obj).setPrdtyp(map.get("prdtyp"));
			((WfCfgdefPrdinfo) obj).setAppcod(map.get("appcod"));
			((WfCfgdefPrdinfo) obj).setAppnam(map.get("appnam"));
			((WfCfgdefPrdinfo) obj).setCretday(map.get("cretday"));
			((WfCfgdefPrdinfo) obj).setIsdefult(map.get("isdefult"));
			((WfCfgdefPrdinfo) obj).setSta(map.get("sta"));
			((WfCfgdefPrdinfo) obj).setMemo(map.get("memo"));
		}else if(obj instanceof WfCfgrefPrddetil){
			//因为开发需求避免字段重复,此处id变更为prddid
			((WfCfgrefPrddetil) obj).setId(map.get("prddid"));
			((WfCfgrefPrddetil) obj).setPrdcod(map.get("prdcod"));
			((WfCfgrefPrddetil) obj).setPrdnam(map.get("prdnam"));
			((WfCfgrefPrddetil) obj).setTrncod(map.get("trncod"));
			((WfCfgrefPrddetil) obj).setDayexpcnt(map.get("dayexpcnt"));
			((WfCfgrefPrddetil) obj).setMonexpcnt(map.get("monexpcnt"));
			((WfCfgrefPrddetil) obj).setMon3expcnt(map.get("mon3expcnt"));
			((WfCfgrefPrddetil) obj).setWarnfreq(map.get("warnfreq"));
			((WfCfgrefPrddetil) obj).setSta(map.get("sta"));
		}
		return obj;
	}
	
	public static Map<String,String> parseEntityToMap(Map<String,String> map,Object obj){
		if(obj instanceof WfCfgrefPrddetil){
			//因为开发需求避免字段重复,此处id变更为prddid
			map.put("prddid", ((WfCfgrefPrddetil) obj).getId());
			map.put("prdcod", ((WfCfgrefPrddetil) obj).getPrdcod());
			map.put("prdnam", ((WfCfgrefPrddetil) obj).getPrdnam());
			map.put("trncod", ((WfCfgrefPrddetil) obj).getTrncod());
			map.put("dayexpcnt", ((WfCfgrefPrddetil) obj).getDayexpcnt());
			map.put("monexpcnt", ((WfCfgrefPrddetil) obj).getMonexpcnt());
			map.put("mon3expcnt", ((WfCfgrefPrddetil) obj).getMon3expcnt());
			map.put("warnfreq", ((WfCfgrefPrddetil) obj).getWarnfreq());
			map.put("sta", ((WfCfgrefPrddetil) obj).getSta());
		}
		return map;
	}
	
}
