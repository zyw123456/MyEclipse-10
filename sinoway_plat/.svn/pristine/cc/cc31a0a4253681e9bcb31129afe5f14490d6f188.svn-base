package com.sinoway.common.util;

import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.TransformerException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import com.sinoway.common.exception.DomParseException;
import com.sun.org.apache.xpath.internal.XPathAPI;
import com.yzj.wf.common.WFLogger;

/**
 * 报告详细页面模板加载bean
 * @author yuhui
 * @version 1.0
 * 2016-1-14
 */
public class LoadModel {
	private   WFLogger  logger = WFLogger.getLogger(LoadModel.class);
	private Map<String,String> map = new HashMap<String, String>();
	private static LoadModel instance = null;
	private  String templatefilePath = FileConstant.getWebRootAbsolutePath();
	private LoadModel(){
		try {
			init();
		} catch (DomParseException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @return  bean实体
	 * @throws DomParseException
	 */
	public static LoadModel getInstance() throws DomParseException{
		if(instance == null){
			instance = new LoadModel();
		}
		return instance;
	}

	
	
	private synchronized void init() throws DomParseException{
		if(instance == null){
			Document dom = DocumentUtil.filePToDomByNoDecode(templatefilePath);
			NodeList trdList;
			try {
				trdList = XPathAPI.selectNodeList(dom, "/TEMPLATE//TRADE");
				if( trdList == null)
					return;
				
				
				// 解析 TRADE 节点
				for(int i = 0; i < trdList.getLength(); i++){
					
					Element trdEl = (Element)trdList.item(i);
					JSONObject trdJson = new JSONObject();
					String id = trdEl.getAttribute("id"); // 交易码
					String name = trdEl.getAttribute("name"); // 交易名称
					
					trdJson.put("id", id);
					trdJson.put("name", name);
					
					// 解析 MOUDLE
					NodeList mouList = XPathAPI.selectNodeList(trdEl, ".//MOUDLE");
					
					if(mouList == null )
						continue;
					
					JSONArray mousArray = new JSONArray();
					for(int j = 0 ; j < mouList.getLength(); j++){
						Element mouEl = (Element)mouList.item(j);
						
						JSONObject mouJson = new JSONObject();
						
						String mouId = mouEl.getAttribute("id");
						String mouName = mouEl.getAttribute("name");
						String mouType = mouEl.getAttribute("type");
						String mouFrom = mouEl.getAttribute("from");
						String mouColms = mouEl.getAttribute("columnums");
						
						if(!"".equals(mouColms)){
							try{
								Integer.parseInt(mouColms);
							}catch(Exception e){
								logger.warn(" trdId=" + id + ",mouId=" + mouId + ":columnums 属性不是数字");
							}
							
						}
						
						
						mouJson.put("id", mouId);
						mouJson.put("name", mouName);
						mouJson.put("type", mouType);
						mouJson.put("from", mouFrom);
						mouJson.put("columnums", mouColms);
						
						JSONArray itemsArray = new JSONArray();
						NodeList itemList = null;
						// 字段类型
						if("0".equals(mouType)){
							itemList = XPathAPI.selectNodeList(mouEl,".//KEY");
						// list类型
						}else if("1".equals(mouType)){
							itemList =  XPathAPI.selectNodeList(mouEl,"./LIST//KEY");
						}else{
							logger.warn(" trdId=" + id + ",mouId=" + mouId + ":不支持的type属性值");

						}
						
						if(itemList == null)
							continue;
						
						for(int t = 0; t < itemList.getLength(); t++){
							Element itemEl = (Element) itemList.item(t);
							
							String itemId = itemEl.getAttribute("id");
							String itemName = itemEl.getAttribute("name");
							String itemClass = itemEl.getAttribute("class");
							String itemWidth = itemEl.getAttribute("width");
							String itemFrom = itemEl.getTextContent();
							
							JSONObject itemJson = new JSONObject();
								
							itemJson.put("id", itemId);  
							itemJson.put("name", itemName);
							itemJson.put("width", itemWidth);
							itemJson.put("htmlclass", itemClass);
							itemJson.put("from", itemFrom);
							
							itemsArray.add(itemJson);
							
						}
						
						mouJson.put("items", itemsArray);
						
						mousArray.add(mouJson);
						
					}
					
					trdJson.put("moudles", mousArray);
					map.put(id, trdJson.toString());
				}
				
			} catch (TransformerException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	/**
	 * 
	 * @param chnlCod 预留字段  渠道码
	 * @param usrId 预留字段 用户码
	 * @param trdCod 交易码
	 * @return
	 */
	public String getTradeTemplate(String chnlCod,String usrId,String trdCod){
		if(map == null || "".equals(StringUtil.NullToString(trdCod)))
			return null;
		return map.get(trdCod);
	}
	
	/*
	 *      GETTER  AND  SETTER
	 */
	
	public String getTemplatefilePath() {
		return templatefilePath;
	}
	public void setTemplatefilePath(String templatefilePath) {
		this.templatefilePath = templatefilePath;
	}
	
}
