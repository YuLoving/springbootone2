package cn.nj.springbootone.utils;

/**
 * 对应ccq_message 库中的code
 *
 * @author Administrator
 *
 */
public class MessageCode {
	public static final String SUCCESS = "0";
	public static final String FAIL = "1";
	public static final String UNKNOW = "2";
	public static final String  PARAMERRO = "参数异常";
	public static final String  SYSERRO = "系统异常";
	public static final String  SELECTERRO = "查询失败";


	/**
	 * 查询失败
	 */
	public static final String CODE_500 = "500";

	/**
	 * 查询无结果
	 */
	public static final String CODE_200 = "200";

	/**
	 * 请求报文解析错
	 */
	public static final String CODE_201 = "201";

	/**
	 * 查询成功
	 */
	public static final String CODE_000 = "0";

	/**
	 * 查询参数错误，请检查
	 */
	public static final String CODE_205="205";

	/**
	 * 请求报文中存在乱码`
	 */
	public static final String CODE_202="202";
	/**
	 * 用户验证失败，请检查用户参数
	 */
	public static final String CODE_204="204";

	/**
	 *该用户权限不足，无法调用当前接口
	 */
	public static final String CODE_206="206";
	/**
	 *手机号码错误，请检验
	 */
	public static final String CODE_207="207";
	/**
	 *充值失败，请检查请求参数
	 */
	public static final String CODE_208="208";
	/**
	 *账户余额不足，请充值
	 */
	public static final String CODE_209="209";
	/**
	 *接口条数不足
	 */
	public static final String CODE_210="210";
	/**
	 *访问ip受限制
	 */
	public static final String CODE_211="211";

	/**
	 *短时间内重复查询
	 */
	public static final String CODE_212="212";
	/**
	 *apiKey无效,请检查apiKey
	 */
	public static final String CODE_260="260";

	/**
	 * 该接口服务，以停用
	 */
	public static final String CODE_261="261";

	/**
	 * 该用户已经被停用
	 */
	public static final String CODE_262="262";

	/**
	 * 产品尚未设置价格
	 */
	public static final String CODE_263="263";

	/**
	 * 未定义错误
	 */
	public static final String CODE_264="264";
	/**
	 * 车辆通道异常
	 */
	public static final String CODE_265="265";

	/**
	 * 照片大小不符合要求
	 */
	public static final String CODE_400="400";
	
	/**
	 * 驾驶证白名单
	 */
	public static final String CODE_401="401";


	/**
	 *解密失败,请检查您的加密key是否合法
	 */
	public static final String CODE_501="501";

	/**
	 * 请求报文中存在特殊字符串
	 */
	public static final String CODE_203="203";

	/**
	 * 目标机动车不存在[已 自动转为查询任务 , 请 于 5 分钟后重试]
	 */
	public static final String CODE_907="907";
	/**
	 * 手机号类型不匹配
	 */
	public static final String CODE_908="908";
	/**
	 * 查询车辆违章不支持城市
	 */
	public static final String CODE_909="909";
	/**
	 * 手机号未实名
	 */
	public static final String CODE_901="901";
	/**
	 * 渠道查询超过上限
	 */
	public static final String CODE_1001="1001";
	/**
	 * 渠道繁忙，请稍候再试
	 */
	public static final String CODE_508="508";
	/**
	 * 网络连接错误，请重试
	 */
	public static final String CODE_504="504";
	/**
	 * 手机号状态异常
	 */
	public static final String CODE_502="502";
	/**
	 * 核查结果未知
	 */
	public static final String CODE_503="503";
	/**
	 * 输入车牌号或车架号或发动机号有误
	 */
	public static final String CODE_910="910";
	/**
	 * 不支持的车辆类型
	 */
	public static final String CODE_911="911";
	/**
	 * 该省份（城市）不支持异地车牌
	 */
	public static final String CODE_912="912";
	/**
	 * 缺少必要的参数或找不到车牌前缀所匹配的城市
	 */
	public static final String CODE_913="913";
	/**
	 * 同一参数请求次数超限
	 */
	public static final String CODE_1002="1002";

	/**
	 * 渠道/网络繁忙，请重试
	 */
	public static final String CODE_505="505";
	
	/**
	 * 未定义的参数校验
	 */
	public static final String CODE_1400="1400";

}
