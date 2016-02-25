<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
            <h4>选择查询模板</h4>
            <div class="row border-bottom-2 right-top-padding">
                <div class="col-md-4 col-sm-4 text-center"><a href="javascript:void(0);" class="a-botton active">固定模板</a></div>
                <div class="col-md-4 col-sm-4 text-center"><a href="javascript:void(0);" class="a-botton">自定义模板一</a></div>
                <div class="col-md-4 col-sm-4 text-center"><a href="javascript:void(0);" class="a-botton">自定义模板二</a></div>
            </div>
            <form id="myForm" method="get" action="">
                <div>
                    <div class="col-md-4 col-sm-4 form-group">
                        <label class="col-md-1 col-sm-1 color-red"><b>*</b></label>
                        <div class="input-group">
                            <span class="input-group-addon">姓名</span>
                            <input class="form-control" id="name" name="name" type="text" placeholder="请输入姓名">
                        </div>
                    </div>
                    <div class="col-md-2 col-sm-2 form-group"></div>
                    <div class="col-md-4 col-sm-4 form-group">
                        <label class="col-md-1 col-sm-1 color-red"><b>*</b></label>
                        <div class="input-group">
                            <span class="input-group-addon">身份证号码</span>
                            <input class="form-control" id="cardid" name="cardid" type="text" placeholder="1121*******54545">
                        </div>
                    </div>
                    <div class="col-md-2 col-sm-2 form-group"></div>
                    <div class="col-md-4 col-sm-4 form-group">
                        <label class="col-md-1 col-sm-1 color-red"><b>*</b></label>
                        <div class="input-group">
                            <span class="input-group-addon">手机号码</span>
                            <input class="form-control" id="phone" name="phone" type="text" placeholder="155****5656">
                        </div>
                    </div>
                    <div class="col-md-2 col-sm-2 form-group"></div>
                    <div class="col-md-4 col-sm-4 form-group">
                        <label class="col-md-1 col-sm-1 color-red"><b>*</b></label>
                        <div class="input-group">
                            <span class="input-group-addon">服务密码</span>
                            <input class="form-control" id="serverid" name="serverid" type="text" placeholder="155****5656" >
                        </div>
                    </div>
                    <div class="col-md-2 col-sm-2 form-group"></div>
                    <div class="col-md-12 col-sm-12" style="margin-left:-2px;">
                        <div class="row">
                            <div class="col-md-5 col-sm-5 form-group" >
                                <label class="col-md-1 col-sm-1 color-red"></label>
                                <div class="input-group">
                                    <span class="input-group-addon">银行卡号</span>
                                    <input class="form-control" id="bankid" type="text" placeholder="155****5656">
                                </div>
                            </div>
                            <div class="col-md-2 col-sm-2 form-group">
                                <select class="form-control">
                                    <option>中国银行</option>
                                    <option>华夏银行</option>
                                    <option>招商银行</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-4 form-group">
                        <label class="col-md-1 col-sm-1 sr-only"></label>
                        <div class="input-group">
                            <span class="input-group-addon">工作单位</span>
                            <input class="form-control" id="work" type="text" placeholder="155****5656">
                        </div>
                    </div>
                    <div class="col-md-2 col-sm-2"></div>
                    <div class="col-md-4 col-sm-4 form-group">
                        <label class="col-md-1 col-sm-1 sr-only"></label>
                        <div class="input-group">
                            <span class="input-group-addon">工作地址</span>
                            <input class="form-control" id="workaddress" type="text" placeholder="155****5656">
                        </div>
                    </div>
                    <div class="col-md-2 col-sm-2"></div>
                    <div class="col-md-4 col-sm-4 form-group ">
                        <label class="col-md-1 col-sm-1 sr-only"></label>
                        <div class="input-group">
                            <span class="input-group-addon">工作电话</span>
                            <input class="form-control" id="workphone" type="text" placeholder="155****5656">
                        </div>
                    </div>
                    <div class="col-md-2 col-sm-2"></div>
                    <div class="col-md-4 col-sm-4 form-group">
                        <label class="col-md-1 col-sm-1 sr-only"></label>
                        <div class="input-group">
                            <span class="input-group-addon">淘宝账号</span>
                            <input class="form-control" id="taobaoid" type="text" placeholder="1121*******54545">
                        </div>
                     </div>
                    <div class="col-md-2 col-sm-2"></div>
                    <div class="col-md-4 col-sm-4 form-group">
                       <label class="col-md-1 col-sm-1 sr-only"></label>
                       <div class="input-group">
                           <span class="input-group-addon">淘宝密码</span>
                           <input class="form-control" id="taobaopsw" type="password" placeholder="*******">
                       </div>
                    </div>
                    <div class="col-md-2 col-sm-2"></div>
                    <div class="col-md-4 col-sm-4 form-group">
                        <label class="col-md-1 col-sm-1 sr-only"></label>
                        <div class="input-group">
                            <span class="input-group-addon">京东账号</span>
                            <input class="form-control" id="jdid" type="text" placeholder="1121*******54545">
                        </div>
                    </div>
                   <div class="col-md-2 col-sm-2"></div>
                   <div class="col-md-4 col-sm-4 form-group">
                       <label class="col-md-1 col-sm-1 sr-only"></label>
                       <div class="input-group">
                           <span class="input-group-addon">京东密码</span>
                           <input class="form-control" id="jdpsw" type="password" placeholder="*******">
                       </div>
                   </div>
                    <div class="col-md-12 col-sm-12 bottom-margin-20">
                        <div class="row">
                            <div class="col-md-2 col-sm-2 col-md-offset-5">
                                <input type="submit" class="btn btn-primary" value="提交">
                                <!--<button class="btn btn-primary" type="button">提交</button>-->
                            </div>
                        </div>
                    </div>
                 </div>
            </form>
<script src="sinoway/dk2/js/card.js"></script>
<script src="sinoway/dk2/js/jquery.validate.js"></script>
<script src="sinoway/dk2/js/additional-methods.js"></script>
 <script>
    $(document).ready(function(){
        $("#myForm").validate({
            rules: {
                name: "required",
                cardid:{
                    required: true,
                    isIdCardNo:true,
                },
                phone: {
                    required: true,
                    isMobile:true,
                },
                serverid: {
                    required: true,
                    maxlength:6,
                    minlength: 6
                },
            },
            messages: {
                name: "必填",
                cardid: {
                    required: "必填",
                    isIdCardNo: "身份证号错误"
                },
                phone: {
                    required: "必填",
                    isMobile: "手机号码错误"
                },
                serverid: {
                    required: "必填",
                    minlength: "最少6位",
                    maxlength: "最多6位"
                }
            },
            errorPlacement: function(error, element) {
                if ( element.is(":radio") )
                    error.appendTo( element.parent().next().next() );
                else if ( element.is(":checkbox") )
                    error.appendTo ( element.next() );
                else
                    error.appendTo( element.parent().parent().next() );
            }
        });
    });
</script>
