package com.sinoway.stm.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sinoway.acc.entity.PeopleShowInfo;
import com.sinoway.acc.service.IPoOrganizeinfoService;
import com.sinoway.common.util.JsonBinder;
import com.sinoway.stm.entity.FrotTrnObjInfo;
import com.sinoway.stm.service.IStmManagerService;
import com.yzj.wf.base.action.BaseAction;
import com.yzj.wf.core.model.po.OrganizeInfo;
import com.yzj.wf.core.model.po.wrapper.XPeopleInfo;

public class StmManagerAction extends BaseAction{
	private static final long serialVersionUID = 8111644158158623865L;
	private IStmManagerService stmManagerService;
	private IPoOrganizeinfoService poOrganizeinfoService;
	private String frontObjStr;
	

	/**
	 * 查询当前用户的产品信息
	 * @return
	 */
	@SuppressWarnings({ "null", "rawtypes" ,"unchecked"})
	public String findCurUserPrds(){
		FrotTrnObjInfo frotObj = transStrToObj();
		XPeopleInfo people = this.getCurrentPeople();
		//map<用户,产品,产品名称,产品编码,产品>
		List<Map<String,String>> prds = (List<Map<String,String>>) stmManagerService.queryUserPrds(people.getPeopleCode(),frotObj.getAppcod(),null);
		List<Map> rstList = new ArrayList<Map>();
		//map<产品编码-详细信息>
		Map<String,Object> map = null;
		for(int i=0;i<prds.size();i++){
			map = new HashMap<String,Object>();
			map.put("prdcod", prds.get(i).get("prdcod"));
			map.put("prdnam",prds.get(i).get("prdnam"));
			map.put("isdefult", prds.get(i).get("isdefult"));
			map.put("cretday", prds.get(i).get("cretday"));
			map.put("po", stmManagerService.queryPoByPrdcod(prds.get(i).get("prdcod")));
			//获得当前产品编码的对应交易码信息
			map.put("trns", stmManagerService.queryPrdTrns(prds.get(i).get("prdcod")));
			rstList.add(map);
		}
		frotObj.setObj(rstList);
		tranObjToStr(frotObj);
		return SUCCESS;
	}
	
	
	/**
	 * 保存或者更新产品
	 * @return
	 */
	public String saveOrUpdatePrd(){
		FrotTrnObjInfo frotObj = transStrToObj();
		XPeopleInfo people = this.getCurrentPeople();
		frotObj = stmManagerService.saveOrUpdateStm(people, frotObj);
		tranObjToStr(frotObj);
		return SUCCESS;
	}
	

	/**
	 * 逻辑删除产品
	 * @return
	 */
	public String delPrd(){
		FrotTrnObjInfo frotObj = transStrToObj();
		XPeopleInfo people = this.getCurrentPeople();
		frotObj = stmManagerService.delStm(people, frotObj);
		tranObjToStr(frotObj);
		return SUCCESS;
	}	
	
	
	/**
	 * 查询子机构(团队)
	 * @return
	 */
	public String queryChildOrganizes(){
		FrotTrnObjInfo frontObj = new FrotTrnObjInfo();
		List<OrganizeInfo> organizeInfos = poOrganizeinfoService.queryOrganize(this.getCurrentPeople());
		frontObj.setOrganizeInfos(organizeInfos);
		tranObjToStr(frontObj);
		return SUCCESS;
	}
	
	/**
	 * 查询团队的所有人员
	 * @return
	 */
	public String queryPeoplesByOrgId(){
		FrotTrnObjInfo frontObj = transStrToObj();
		List<PeopleShowInfo> peoples = poOrganizeinfoService.getPeopleByOrgId(frontObj.getOrgSid());
		frontObj.setPeoples(peoples);
		tranObjToStr(frontObj);
		return SUCCESS;
	}
	
	
	
	private FrotTrnObjInfo transStrToObj(){
		return JsonBinder.fromJson(frontObjStr, FrotTrnObjInfo.class);
	}
	
	private void tranObjToStr(FrotTrnObjInfo trnObj){
		frontObjStr=JsonBinder.toJson(trnObj);
	}
	
	
	public void setStmManagerService(IStmManagerService stmManagerService) {
		this.stmManagerService = stmManagerService;
	}

	public String getFrontObjStr() {
		return frontObjStr;
	}

	public void setFrontObjStr(String frontObjStr) {
		this.frontObjStr = frontObjStr;
	}



	public void setPoOrganizeinfoService(
			IPoOrganizeinfoService poOrganizeinfoService) {
		this.poOrganizeinfoService = poOrganizeinfoService;
	}
	
	
}
