package cn.nj.springbootone.utils;


import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 姓名,身份证,银行卡参数校验工具类
 * Created by chenjian on 2017/1/9.
 */
public class CheckParamUtil {

    /**
     * 校验二次号核验日期
     * @param date
     * @return
     */
    public static boolean checkTwicePhoneDate(String date) {
        if (StringUtils.isEmpty(date)){
            return true;
        }
        Pattern pattern = Pattern.compile("^\\d{8}$");
        if (pattern.matcher(date).matches()){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 校验常用联系人月份
     * @param month
     * @return
     */
    public static boolean frequentContacts(String month) {
        if (StringUtils.isEmpty(month)){
            return true;
        }
        Pattern pattern = Pattern.compile("^\\d{6}$");
        if (pattern.matcher(month).matches()){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 校验信用卡cvn2码
     * @param cvn2
     * @return
     */
    public static boolean checkCvn2(String cvn2) {
        if (StringUtils.isEmpty(cvn2)){
            return true;
        }
        Pattern pattern = Pattern.compile("^\\d{3}$");
        if (pattern.matcher(cvn2).matches()){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 校验信用卡有效
     * @param expired
     * @return
     */
    public static boolean checkBankExpired(String expired) {
        if (StringUtils.isEmpty(expired)){
            return true;
        }
        Pattern pattern = Pattern.compile("^\\d{4}$");
        if (pattern.matcher(expired).matches()){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 校验身份证有效起始时间
     * @param beginDate
     * @return
     */
    public static boolean checkIdCardBeginDate(String beginDate) {
        if (StringUtils.isEmpty(beginDate)){
            return true;
        }
        Pattern pattern = Pattern.compile("^\\d{8}$");
        if (pattern.matcher(beginDate).matches()){
            return false;
        }else {
            return true;
        }
    }
    /**
     * 校验身份证有效起始时间(包含判断月份和日期及闰年闰月日期)
     * @param beginDate
     * @return
     */
    public static boolean checkIdCardBeginDateLeap(String beginDate) {
        if (StringUtils.isEmpty(beginDate)){
            return true;
        }
        Pattern pattern = Pattern.compile("^\\d{8}$");
        if (pattern.matcher(beginDate).matches()){
            String yearStr=beginDate.substring(0,4);
            String month=beginDate.substring(4,6);
            String day=beginDate.substring(6);
            List<String> monthList= Arrays.asList("01","02","03","04","05","06","07","08","09","10","11","12");
            List<String> dayList= Arrays.asList("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31");
            if (monthList.contains(month)){//月份格式正确
                if (dayList.contains(day)){//天格式正确
                    if (("04".equals(month)||"06".equals(month)||"09".equals(month)||"11".equals(month)) && "31".equals(day)){
                        return true;
                    }
                    if ("02".equals(month)){
                        if ("30".equals(day)||"31".equals(day)){
                            return true;
                        }
                        int year=Integer.parseInt(yearStr);
                        if(!(year % 4 == 0 && year % 100 != 0 || year % 400 == 0)){
                        //不是闰年
                            if ("29".equals(day)){
                                return true;
                            }
                        }
                    }
                }else {//天格式错误
                    return true;
                }
            }else {//月份格式错误
                return true;
            }
            return false;
        }else {
            return true;
        }
    }
    /**
     * 验证车架号
     * @param carCode
     * @return
     */
    public static boolean checkCarCode(String carCode) {
        if (StringUtils.isEmpty(carCode) || carCode.trim().length() < 6){
            return true;
        }
        return false;
    }

    /**
     * 验证发动机号
     * @param carDriveNumber
     * @return
     */
    public static boolean checkCarDriveNumber(String carDriveNumber) {
        if (StringUtils.isEmpty(carDriveNumber) || carDriveNumber.trim().length() < 6){
            return true;
        }
        return false;
    }
    /**
     * 验证城市区号（首位不能为0）
     * @param areaCode
     * @return
     */
    public static boolean checkAreaCode(String areaCode){
        if (StringUtils.isEmpty(areaCode)) {
            return true;
        }
        Pattern pattern = Pattern.compile("[1-6][0-9]{5}");
        Matcher matcher = pattern.matcher(areaCode.trim());
        if (matcher.matches()) return false;
        return true;
    }
    /**
     * 验证城市区号（数景）
     * @param areaCode
     * @return
     */
    public static boolean checkAreaCodeBySJ(String areaCode){
        if (StringUtils.isEmpty(areaCode)) {
            return true;
        }
        Pattern pattern = Pattern.compile("[1-9][0-9]{5}");
        Matcher matcher = pattern.matcher(areaCode.trim());
        if (matcher.matches()) return false;
        return true;
    }


    public static boolean checkRangeCode(String rangeCode){
        //验证范围是否在1-12之间
        try {
            int rangeCodeTmp = Integer.parseInt(rangeCode.trim());
            if (rangeCodeTmp < 1 || rangeCodeTmp > 12) {
                return true;
            }
        } catch (NumberFormatException e) {
           return true;
        }
        return false;
    }

    /**
     * 银行卡校验规则必须全部是数字
     * @param bankCard
     * @return
     */
    public static boolean checkBankCard(String bankCard) {
        //银行卡长度必须大于15位
        if (StringUtils.isEmpty(bankCard) || bankCard.trim().length() < 13) {
            return true;
        }
        Pattern pattern=Pattern.compile("[0-9]*");
        Matcher match=pattern.matcher(bankCard.trim());
        if (match.matches() == false) {
            //银行卡号含有非数字则不匹配
            return true;
        }else {
            //银行卡号全部是数字验证通过
            return false;
        }
    }

    /**
     * 身份证校验规则最后一位是字母则必须大写,长度15或者18位
     *
     * @param idCard
     * @return
     */
    public static boolean checkIdCard(String idCard) {
        //非空判断
        if (StringUtils.isEmpty(idCard)) {
            return true;
        }
        idCard = idCard.toUpperCase();
        //身份证号码如果最后一位是字母,必须是大写
        String regex = "[1-9]\\d{13,16}[X|0-9]{1}";
        if (idCard.trim().length() != 15 && idCard.trim().length() != 18) {
            return true;
        }
        if(!new IdcardValidator(idCard.trim().toUpperCase()).validate()){
            return true;
        }
        if (Pattern.matches(regex, idCard)==false) {
            //如果最后一位是字母且不是大写,校验不通过
            return true;
        }else {
            //规则匹配,校验通过
            return false;
        }
    }

    /**
     * 姓名校验规则：1到8个字的汉字,不能含有英文
     * @param name
     * @return
     */
    public static boolean checkName(String name) {
        if(StringUtils.isEmpty(name)){
            return true;
        }
        if(name.equals("null")){
            return true;
        }
        //姓名不能包括特殊字符^(([\u4e00-\u9fa5]{2,8}))$
//        if(name.length()<=1){
//            return true;
//        }
//        String regex = "([\\u4E00-\\u9FA5,\\u9FA6-\\u9FCB,\\u3400-\\u4DB5,\\x{20000}-\\x{2A6D6},\\x{2A700}-\\x{2B734},\\x{2B740}-\\x{2B81D},\\x{2B820}-\\x{2CEA1},\\x{2CEB0}-\\x{2EBE0}]{1,8}" +
//                "(?:·[\\u4E00-\\u9FA5,\\u9FA6-\\u9FCB,\\u3400-\\u4DB5,\\x{20000}-\\x{2A6D6},\\x{2A700}-\\x{2B734},\\x{2B740}-\\x{2B81D},\\x{2B820}-\\x{2CEA1},\\x{2CEB0}-\\x{2EBE0}]{1,8})*)|([a-zA-Z]{3,10})";
//        if (Pattern.matches(regex, name) == false) {
//            //姓名验证不通过
//            return true;
//        } else {
//            //姓名验证通过
//            return false;
//        }
        return false;
    }

    /**
     * 判断字符是全角状态还是半角状态
     * @param param
     * @return
     */
    public static boolean checkParamStatus(String param) {
        if(StringUtils.isEmpty(param)){
            return true;
        }
        //字符全角状态匹配规则
        String regex = "[\\u4E00-\\u9FA5]*";
        if (Pattern.matches(regex, param)==true) {
            //该字符是全角,不合法
            return true;
        } else {
            //该字符是半角,合法
            return false;
        }
    }

    /**
     * 手机号码校验
     * @param phone
     * @return
     */
    public static boolean checkPhone(String phone) {
        if(StringUtils.isEmpty(phone)){
            return true;
        }
        //手机号码11位,并且只能是数字
        String regex = "^[1][3,4,5,6,7,8,9][0-9]{9}$";
        if (Pattern.matches(regex, phone) == false) {
            //号码不合法
            return true;
        } else {
            //号码合法
            return false;
        }
    }
    /**
     * 15位商户编号校验
     * @param
     * @return
     */
    public static boolean checkShopNo(String mid) {
        if(StringUtils.isEmpty(mid)){
            return true;
        }
        //手机号码11位,并且只能是数字
        String regex = "^[0-9]{15}$";
        if (Pattern.matches(regex, mid) == false) {
            //号码不合法
            return true;
        } else {
            //号码合法
            return false;
        }
    }
    /**
     * 匹配（年-月-日）格式日期
     * @param date
     * @return
     */
    public static boolean checkDate(String date) {
        if(StringUtils.isEmpty(date)){
            return true;
        }
        //必须输入格式为(年-月-日)
        String regex = "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))";
        if (Pattern.matches(regex, date) == false) {
            //匹配不成功
            return true;
        } else {
            //输入的格式为(年-月-日),匹配成功
            return false;
        }
    }
    /**
     * 月份校验
     * @param month
     * @return
     */
    public static boolean checkMonth(String month) {
        if (StringUtils.isEmpty(month)){
            return true;
        }
        String regex = "\\d{4}((0[1-9])|(1[0-2]))";
        if (Pattern.matches(regex, month) == false) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 车牌号码校验
     * @param carNo
     * @return
     */
    public static boolean checkCarNo(String carNo) {
        if (StringUtils.isEmpty(carNo)){
            return true;
        }
        String regex = "^[\\u4e00-\\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$";
        if (Pattern.matches(regex, carNo) == false) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 校验车架号
     * @param vin
     * @return
     */
    public static boolean checkCarVin(String vin) {
        //非空判断
        if (StringUtils.isEmpty(vin)){
            return true;
        }
        vin = vin.toUpperCase();
        //17位完整车架号
        String regex = "[A-Z0-9]{17}";

        if (Pattern.matches(regex, vin)==false) {
            return true;
        }else {
            //规则匹配,校验通过
            return false;
        }
    }

    /**
     * 校验驾驶证档案编号(必须是12位数字)
     * @param dabh
     * @return
     */
    public static boolean checkDabh(String dabh) {
        if (StringUtils.isEmpty(dabh)){
            return true;
        }
        String regex = "^\\d{12}$";
        if (Pattern.matches(regex, dabh) == false) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 准驾车型校验（不能包括特殊字符）
     * @param zjcx
     * @return
     */
    public static boolean checkZjcx(String zjcx) {
        if (StringUtils.isEmpty(zjcx)){
            return true;
        }
        String regex = "\\w+$";
        if (Pattern.matches(regex, zjcx)==false) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 校验证件类型
     * @param certType
     * @return
     */
    public static boolean checkCardType(String certType) {
        if (StringUtils.isEmpty(certType)){
            return true;
        }
        //证件种类
        String regex = "^([0][1][0][1-7]|[0][1][9][9]|[0][9][9][9])$";
        if (Pattern.matches(regex, certType) == false) {
            //号码不合法
            return true;
        } else {
            //号码合法
            return false;
        }
    }
    /**
     * 校验是否为数字
     * @param certType
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return true; //不是数字
        }
        return false;//是数字
    }

    /**
     * 半角转全角
     * @param input String.
     * @return 全角字符串.
     */
    public static String ToSBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                c[i] = '\u3000';
            } else if (c[i] < '\177') {
                c[i] = (char) (c[i] + 65248);


            }
        }
        return new String(c);
    }

    /**
     * 全角转半角
     * @param input String.
     * @return 半角字符串
     */
    public static String ToDBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\u3000') {
                c[i] = ' ';
            } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                c[i] = (char) (c[i] - 65248);


            }
        }
        String returnString = new String(c);
        return returnString;
    }

    public static boolean  check(String param, String... rules) {
        boolean flag = true;
        ros: for (String rule : rules) {
            switch (rule) {
                case "identityCard":
                    if (!new IdcardValidator(param==null?null:param.trim().toUpperCase()).validate()) {
                        flag = false;
                        break ros;
                    }
                    break;
                case "phone":
                    if (param!=null&&CheckParamUtil.checkPhone(param)) {
                        flag = false;
                        break ros;
                    }
                    break;
                case "name":
                    if (param!=null&&CheckParamUtil.checkName(param)) {
                        flag = false;
                        break ros;
                    }
                    break;
                case "bankCard":
                    if (param!=null&&CheckParamUtil.checkBankCard(param)) {
                        flag = false;
                        break ros;
                    }
                    break;
                case "rangeCode":
                    if (param!=null&&CheckParamUtil.checkRangeCode(param)) {
                        flag = false;
                        break ros;
                    }
                    break;
                default:
                    throw new ZezsException(MessageCode.CODE_1400, "未定义的参数校验");
            }
        }
        return flag;
    }

    /**
     * 校验是否为时间段
     * @param "2015-07-11--2016-07-11"  注意是按照-- 分隔的
     * @return
     */
    public static boolean isDatequjian(String str){
    	try {
    		String a = str.substring(0, 10);
    		String b= str.substring(12,22);
    		String c=str.substring(10, 12);
    		//如果是时间的话，checkDate返回false
    		if( !checkDate(a) && !checkDate(b) && c.equals("--")){
    			return false;//是时间段
    		}
    		return true;//不是时间段
			
		} catch (Exception e) {
			//只要不符合2015-07-11--2016-07-11这种格式，全部不是时间段
			return true;
		}
    }
    
    
    public static void main(String[] args) throws ParseException {
       System.out.println(isNumeric("22"));
//        System.out.println(checkIdCardBeginDateLeap("00160229"));
//        System.out.println(checkCarNo("塑AYQ122"));
       System.out.println(isDatequjian("2015-07-11--2016-07-11"));
       
  
       
       
       
    }

}
