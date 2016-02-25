<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>
<%
	Boolean enableSupervisePeople = (Boolean)request.getAttribute(
			"enableSupervisePeople");
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>转授权预约</title>
<link rel="stylesheet" type="text/css" href="${ctx}/common/css/jquery-ui-1.8.18.base.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/windforce/common/css/am_transfer.css" />
<script type="text/javascript">
	var ctx = "${ctx}";
	var enableSupervisePeople = "<%=enableSupervisePeople%>";
	$(document).ready(function() { 
		if(enableSupervisePeople!="true"){
			$("#tr_sp_code").css("display","none");
			$("#tr_sp_pass").css("display","none");
		}
	});
</script>
<script type="text/javascript" src="${ctx}/common/js/jquery/jquery-ui-1.8.18.custom.js"></script>
<script type="text/javascript" src="${ctx}/windforce/common/js/am/transfer/reserve.js"></script>
</head>

<body>

	<form id="reserveForm" action="#">
		<table id="reserveTable" width="100%">
			<tr>
				<th>
					<label>申请单编号</label>
				</th>
				<td align="left">
					<input type="text" id="applicationFormNo" name="applicationFormNo" value='<s:property value='applicationFormNo'/>'
						name="" class="tpi_input" disabled="disabled" />
				</td>
			</tr>
			<tr>
				<th>
					<label>起始时间</label>
				</th>
				<td align="left">
					<input type="text" id="startDate" class="tpi_input" readonly="readonly" />
					<select id="startTimeHour">
						<option value="00">00</option>
						<option value="01">01</option>
						<option value="02">02</option>
						<option value="03">03</option>
						<option value="04">04</option>
						<option value="05">05</option>
						<option value="06">06</option>
						<option value="07">07</option>
						<option value="08" selected="selected">08</option>
						<option value="09">09</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21">21</option>
						<option value="22">22</option>
						<option value="23">23</option>
					</select>
					时
					<select id="startTimeMinute">
						<option value="00">00</option>
						<option value="01">01</option>
						<option value="02">02</option>
						<option value="03">03</option>
						<option value="04">04</option>
						<option value="05">05</option>
						<option value="06">06</option>
						<option value="07">07</option>
						<option value="08">08</option>
						<option value="09">09</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21">21</option>
						<option value="22">22</option>
						<option value="23">23</option>
						<option value="24">24</option>
						<option value="25">25</option>
						<option value="26">26</option>
						<option value="27">27</option>
						<option value="28">28</option>
						<option value="29">29</option>
						<option value="30" selected="selected">30</option>
						<option value="31">31</option>
						<option value="32">32</option>
						<option value="33">33</option>
						<option value="34">34</option>
						<option value="35">35</option>
						<option value="36">36</option>
						<option value="37">37</option>
						<option value="38">38</option>
						<option value="39">39</option>
						<option value="40">40</option>
						<option value="41">41</option>
						<option value="42">42</option>
						<option value="43">43</option>
						<option value="44">44</option>
						<option value="45">45</option>
						<option value="46">46</option>
						<option value="47">47</option>
						<option value="48">48</option>
						<option value="49">49</option>
						<option value="50">50</option>
						<option value="51">51</option>
						<option value="52">52</option>
						<option value="53">53</option>
						<option value="54">54</option>
						<option value="55">55</option>
						<option value="56">56</option>
						<option value="57">57</option>
						<option value="58">58</option>
						<option value="59">59</option>
					</select>
					分
				</td>
			</tr>
			<tr>
				<th>
					<label>结束时间</label>
				</th>
				<td align="left">
					<input type="text" id="endDate" class="tpi_input" readonly="readonly" />
					<select id="endTimeHour">
						<option value="00">00</option>
						<option value="01">01</option>
						<option value="02">02</option>
						<option value="03">03</option>
						<option value="04">04</option>
						<option value="05">05</option>
						<option value="06">06</option>
						<option value="07">07</option>
						<option value="08" selected="selected">08</option>
						<option value="09">09</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21">21</option>
						<option value="22">22</option>
						<option value="23">23</option>
					</select>
					时
					<select id="endTimeMinute">
						<option value="00">00</option>
						<option value="01">01</option>
						<option value="02">02</option>
						<option value="03">03</option>
						<option value="04">04</option>
						<option value="05">05</option>
						<option value="06">06</option>
						<option value="07">07</option>
						<option value="08">08</option>
						<option value="09">09</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21">21</option>
						<option value="22">22</option>
						<option value="23">23</option>
						<option value="24">24</option>
						<option value="25">25</option>
						<option value="26">26</option>
						<option value="27">27</option>
						<option value="28">28</option>
						<option value="29">29</option>
						<option value="30" selected="selected">30</option>
						<option value="31">31</option>
						<option value="32">32</option>
						<option value="33">33</option>
						<option value="34">34</option>
						<option value="35">35</option>
						<option value="36">36</option>
						<option value="37">37</option>
						<option value="38">38</option>
						<option value="39">39</option>
						<option value="40">40</option>
						<option value="41">41</option>
						<option value="42">42</option>
						<option value="43">43</option>
						<option value="44">44</option>
						<option value="45">45</option>
						<option value="46">46</option>
						<option value="47">47</option>
						<option value="48">48</option>
						<option value="49">49</option>
						<option value="50">50</option>
						<option value="51">51</option>
						<option value="52">52</option>
						<option value="53">53</option>
						<option value="54">54</option>
						<option value="55">55</option>
						<option value="56">56</option>
						<option value="57">57</option>
						<option value="58">58</option>
						<option value="59">59</option>
					</select>
					分
				</td>
			</tr>
			<tr>
				<th>
					<label>接收柜员号</label>
				</th>
				<td align="left">
					<s:if test="rtpPeopleInfos.size>0">
						<s:select list="rtpPeopleInfos" listKey="peopleCode" listValue="peopleCode" id="revicePeopleNo"
							cssClass="tpi_input" />
					</s:if>
					<s:else>
						<select id="revicePeopleNo" cssClass="tpi_input"></select>
					</s:else>
				</td>
			</tr>
			<tr>
				<th>
					<label class="tpi_label">接收柜员密码</label>
				</th>
				<td align="left">
					<input type="password" id="revicePeoplePassword" class="tpi_input" name="" />
				</td>
			</tr>
			<tr id="tr_sp_code">
				<th>
					<label class="tpi_label">监交柜员号</label>
				</th>
				<td align="left">
					<s:if test="rtpPeopleInfos.size>0">
						<s:select list="sameOrgPeopleInfos" listKey="peopleCode" listValue="peopleCode" id="supervisePeopleNo"
							cssClass="tpi_input" />
					</s:if>
					<s:else>
						<select id="supervisePeopleNo" cssClass="tpi_input"></select>
					</s:else>
				</td>
			</tr>
			<tr id="tr_sp_pass">
				<th>
					<label class="tpi_label">监交柜员密码</label>
				</th>
				<td align="left">
					<input type="password" id="supervisePeoplePassword" class="tpi_input" name="" />
				</td>
			</tr>
			<tr>
				<th colspan="2" style="text-align:center;height:25px;">
					<span id="msg">
						<s:property value="result.msg" />
					</span>
				</th>
			</tr>
			<tr>
				<th colspan="2" style="text-align:center;">
					<input type="button" id="saveBtn" value="保存" class="btn" />
					<input type="button" id="resetBtn" value="重置" class="btn" />
					&nbsp;&nbsp;
				</th>
			</tr>
		</table>

	</form>
</body>
</html>
