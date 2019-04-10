package cn.nj.springbootone.utils;

/**
 * Created by hsj on 2017/6/30.
 */
/**
 * 身份证前6位【ABCDEF】为行政区划数字代码（简称数字码）说明（参考《GB/T 2260-2007 中华人民共和国行政区划代码》）：
 * 该数字码的编制原则和结构分析，它采用三层六位层次码结构，按层次分别表示我国各省（自治区，直辖市，特别行政区）、
 * 市（地区，自治州，盟）、县（自治县、县级市、旗、自治旗、市辖区、林区、特区）。
 数字码码位结构从左至右的含义是：
 第一层为AB两位代码表示省、自治区、直辖市、特别行政区；
 第二层为CD两位代码表示市、地区、自治州、盟、直辖市所辖市辖区、县汇总码、省（自治区）直辖县级行政区划汇总码，其中：
 ——01~20、51~70表示市，01、02还用于表示直辖市所辖市辖区、县汇总码；
 ——21~50表示地区、自治州、盟；
 ——90表示省（自治区）直辖县级行政区划汇总码。
 第三层为EF两位表示县、自治县、县级市、旗、自治旗、市辖区、林区、特区，其中：
 ——01~20表示市辖区、地区（自治州、盟）辖县级市、市辖特区以及省（自治区）直辖县级行政区划中的县级市，01通常表示辖区汇总码；
 ——21~80表示县、自治县、旗、自治旗、林区、地区辖特区；
 ——81~99表示省（自治区）辖县级市。
 */



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 类说明:身份证合法性校验
 * </p>
 * <p>
 * --15位身份证号码：第7、8位为出生年份(两位数)，第9、10位为出生月份，第11、12位代表出生日期，第15位代表性别，奇数为男，偶数为女。
 * --18位身份证号码
 * ：第7、8、9、10位为出生年份(四位数)，第11、第12位为出生月份，第13、14位代表出生日期，第17位代表性别，奇数为男，偶数为女。
 * </p>
 */
@SuppressWarnings({ "unchecked", "unused", "all" })
public class IdcardValidator {
    /**
     * 完整的身份证号码
     */
    private final String cardNumber;
    // 缓存身份证是否有效，因为验证有效性使用频繁且计算复杂
    private Boolean cacheValidateResult = null;
    // 缓存出生日期，因为出生日期使用频繁且计算复杂
    private Date cacheBirthDate = null;

    private static final boolean[] regions=new boolean[659005];//存放所有省市县数据

    public boolean isValidC(String idCard) {

        int  sum = 0, regionCode = 0, dateCode = 0;
        char[] arr = idCard.toCharArray();
        //获取省市县片段，5333328=111111*48
        regionCode = arr[0] * 100000 + arr[1] * 10000 + arr[2] * 1000
                + arr[3] * 100    + arr[4] * 10    + arr[5]-5333328;
        //获取日期片段   552333429=11111111*48+19000101
        dateCode =   arr[6] * 10000000 + arr[7] * 1000000 + arr[8] * 100000
                + arr[9] * 10000   + arr[10] * 1000    + arr[11] * 100
                + arr[12]* 10     + arr[13]-552333429;

       boolean r =  regions[regionCode];//最后校验省市县和日期

       return r;

    }

    static {
        int[] regionArray={//省市县压缩后数据
                110101,110110,110111,110118,110228,110230,120101,120117,
                120221,120222,120223,120224,120225,120226,130101,130106,130107,130109,
                130121,130122,130123,130134,130181,130186,130201,130206,130207,130210,
                130223,130226,130227,130228,130229,130231,130281,130282,130283,130284,
                130301,130305,130321,130325,130401,130405,130406,130407,130421,130422,
                130423,130436,130481,130482,130501,130504,130521,130536,130581,130583,
                130601,130605,130621,130639,130681,130685,130701,130704,130705,130707,
                130721,130734,130801,130805,130821,130829,130901,130904,130921,130931,
                130981,130985,131001,131004,131022,131027,131028,131029,131081,131083,
                131101,131103,131121,131129,131181,131183,140101,140102,140105,140111,
                140121,140124,140181,140182,140201,140204,140211,140213,140221,140228,
                140301,140304,140311,140312,140321,140323,140401,140403,140411,140412,
                140421,140422,140423,140432,140481,140482,140501,140503,140521,140523,
                140524,140526,140581,140582,140601,140604,140621,140625,140701,140703,
                140721,140730,140781,140782,140801,140803,140821,140831,140881,140883,
                140901,140903,140921,140933,140981,140982,141001,141003,141021,141035,
                141081,141083,141101,141103,141121,141131,141181,141183,150101,150106,
                150121,150126,150201,150208,150221,150224,150301,150305,150401,150405,
                150421,150427,150428,150431,150501,150503,150521,150527,150581,150582,
                150601,150603,150621,150628,150701,150703,150721,150728,150781,150786,
                150801,150803,150821,150827,150901,150903,150921,150930,150981,150982,
                152201,152203,152221,152225,152501,152503,152522,152532,152921,152924,
                210101,210107,210111,210115,210122,210125,210181,210182,210201,210205,
                210211,210214,210224,210225,210281,210284,210301,210305,210311,210312,
                210321,210322,210323,210324,210381,210382,210401,210405,210411,210412,
                210421,210424,210501,210506,210521,210523,210601,210605,210624,210625,
                210681,210683,210701,210704,210711,210712,210726,210728,210781,210783,
                210801,210805,210811,210812,210881,210883,210901,210906,210911,210912,
                210921,210923,211001,211006,211011,211012,211021,211022,211081,211082,
                211101,211104,211121,211123,211201,211203,211204,211205,211221,211222,
                211223,211225,211281,211283,211301,211304,211321,211323,211324,211325,
                211381,211383,211401,211405,211421,211423,211481,211482,220101,220107,
                220112,220113,220122,220123,220181,220184,220201,220205,220211,220212,
                220221,220222,220281,220285,220301,220304,220322,220324,220381,220383,
                220401,220404,220421,220423,220501,220504,220521,220522,220523,220525,
                220581,220583,220601,220603,220604,220606,220621,220624,220625,220626,
                220681,220682,220701,220703,220721,220725,220801,220803,220821,220823,
                220881,220883,222401,222407,222424,222425,222426,222427,230101,230105,
                230106,230113,230123,230130,230181,230185,230201,230209,230221,230222,
                230223,230226,230227,230228,230229,230232,230281,230282,230301,230308,
                230321,230322,230381,230383,230401,230408,230421,230423,230501,230504,
                230505,230507,230521,230525,230601,230607,230621,230625,230701,230717,
                230722,230723,230781,230782,230801,230806,230811,230812,230822,230823,
                230826,230827,230828,230829,230833,230834,230881,230883,230901,230905,
                230921,230922,231001,231006,231024,231026,231081,231082,231083,231086,
                231101,231103,231121,231122,231123,231125,231181,231183,231201,231203,
                231221,231227,231281,231284,232701,232705,232721,232724,310101,310102,
                310103,310111,310112,310121,310230,310231,320101,320108,320111,320112,
                320113,320117,320124,320126,320201,320207,320211,320212,320281,320283,
                320301,320306,320311,320313,320321,320325,320381,320383,320401,320403,
                320404,320406,320411,320413,320481,320483,320501,320510,320581,320586,
                320601,320603,320611,320613,320621,320622,320623,320624,320681,320685,
                320701,320702,320703,320704,320705,320707,320721,320725,320801,320805,
                320811,320812,320826,320827,320829,320832,320901,320904,320921,320926,
                320981,320983,321001,321004,321011,321013,321023,321024,321081,321082,
                321084,321085,321088,321089,321101,321103,321111,321113,321181,321184,
                321201,321204,321281,321285,321301,321303,321311,321312,321322,321325,
                330101,330107,330108,330111,330122,330123,330127,330128,330182,330184,
                330185,330186,330201,330202,330203,330207,330211,330213,330225,330227,
                330281,330284,330301,330305,330322,330323,330324,330325,330326,330330,
                330381,330383,330401,330403,330411,330412,330421,330422,330424,330425,
                330481,330484,330501,330504,330521,330524,330601,330603,330621,330622,
                330624,330625,330681,330684,330701,330704,330723,330724,330726,330728,
                330781,330785,330801,330804,330822,330823,330824,330826,330881,330882,
                330901,330904,330921,330923,331001,331005,331021,331025,331081,331083,
                331101,331103,331121,331128,331181,331182,340101,340105,340111,340112,
                340121,340125,340181,340182,340201,340205,340207,340209,340221,340224,
                340225,340226,340301,340305,340311,340312,340321,340324,340401,340407,
                340421,340422,340501,340505,340506,340507,340521,340524,340601,340605,
                340621,340622,340701,340704,340711,340712,340721,340722,340801,340804,
                340811,340812,340822,340829,340881,340882,341001,341005,341021,341025,
                341101,341104,341122,341123,341124,341127,341181,341183,341201,341205,
                341221,341223,341225,341227,341282,341283,341301,341303,341321,341325,
                341401,341403,341421,341425,341501,341504,341521,341526,341601,341603,
                341621,341624,341701,341703,341721,341724,341801,341803,341821,341826,
                341881,341882,350101,350106,350111,350112,350121,350126,350128,350129,
                350181,350183,350201,350202,350203,350204,350205,350207,350211,350214,
                350301,350306,350322,350323,350401,350404,350421,350422,350423,350431,
                350481,350482,350501,350506,350521,350522,350524,350528,350581,350584,
                350601,350604,350622,350630,350681,350682,350701,350703,350721,350726,
                350781,350785,350801,350803,350821,350826,350881,350882,350901,350903,
                350921,350927,350981,350983,360101,360106,360111,360112,360121,360125,
                360201,360204,360222,360223,360281,360282,360301,360303,360313,360314,
                360321,360324,360401,360404,360421,360422,360423,360431,360481,360483,
                360501,360503,360521,360522,360601,360603,360622,360623,360681,360682,
                360701,360703,360721,360736,360781,360783,360801,360804,360821,360831,
                360881,360882,360901,360903,360921,360927,360981,360984,361001,361003,
                361021,361031,361101,361103,361121,361131,361181,361182,370101,370106,
                370112,370114,370124,370127,370181,370182,370201,370204,370205,370206,
                370211,370215,370281,370286,370301,370307,370321,370324,370401,370407,
                370481,370482,370501,370504,370521,370524,370601,370603,370611,370614,
                370634,370635,370681,370688,370701,370706,370724,370726,370781,370787,
                370801,370803,370811,370812,370826,370833,370881,370884,370901,370904,
                370911,370912,370921,370922,370923,370924,370982,370984,371001,371003,
                371081,371084,371101,371104,371121,371123,371201,371204,371301,371303,
                371311,371313,371321,371330,371401,371403,371421,371429,371481,371483,
                371501,371503,371521,371527,371581,371582,371601,371603,371621,371627,
                371701,371703,371721,371729,410101,410107,410108,410109,410122,410123,
                410181,410186,410201,410206,410211,410212,410221,410226,410301,410308,
                410311,410312,410322,410330,410381,410382,410401,410405,410411,410412,
                410421,410424,410425,410426,410481,410483,410501,410504,410505,410507,
                410522,410524,410526,410528,410581,410582,410601,410604,410611,410612,
                410621,410623,410701,410705,410711,410712,410721,410722,410724,410729,
                410781,410783,410801,410805,410811,410812,410821,410824,410825,410826,
                410881,410884,410901,410903,410922,410924,410926,410929,411001,411003,
                411023,411026,411081,411083,411101,411105,411121,411123,411201,411203,
                411221,411223,411224,411225,411281,411283,411301,411304,411321,411331,
                411381,411382,411401,411404,411421,411427,411481,411482,411501,411504,
                411521,411529,411601,411603,411621,411629,411681,411682,411701,411703,
                411721,411730,419001,419002,420101,420108,420111,420118,420201,420206,
                420222,420223,420281,420282,420301,420304,420321,420326,420381,420382,
                420501,420507,420525,420530,420581,420584,420601,420603,420606,420608,
                420624,420627,420682,420685,420701,420705,420801,420803,420804,420805,
                420821,420823,420881,420882,420901,420903,420921,420924,420981,420983,
                420984,420985,421001,421004,421022,421025,421081,421082,421083,421084,
                421087,421088,421101,421103,421121,421128,421181,421183,421201,421203,
                421221,421225,421281,421282,421301,421304,421321,421322,421381,421382,
                422801,422803,422822,422824,422825,422829,429004,429007,429021,429022,
                430101,430106,430111,430113,430121,430123,430124,430125,430181,430182,
                430201,430205,430211,430212,430221,430222,430223,430226,430281,430282,
                430301,430303,430304,430305,430321,430322,430381,430383,430401,430402,
                430405,430409,430412,430413,430421,430425,430426,430427,430481,430483,
                430501,430504,430511,430512,430521,430526,430527,430530,430581,430582,
                430601,430604,430611,430612,430621,430622,430623,430625,430626,430627,
                430681,430683,430701,430704,430721,430727,430781,430782,430801,430803,
                430811,430812,430821,430823,430901,430904,430921,430924,430981,430982,
                431001,431004,431021,431029,431081,431082,431101,431104,431121,431130,
                431201,431203,431221,431231,431281,431282,431301,431303,431321,431323,
                431381,431383,433101,433102,433122,433128,433130,433131,440101,440108,
                440111,440117,440183,440185,440201,440202,440203,440206,440222,440223,
                440224,440225,440229,440230,440232,440234,440281,440283,440301,440302,
                440303,440309,440401,440405,440501,440502,440507,440508,440511,440516,
                440523,440524,440601,440602,440604,440609,440701,440702,440703,440706,
                440781,440782,440783,440786,440801,440805,440811,440812,440823,440824,
                440825,440826,440881,440884,440901,440904,440923,440924,440981,440984,
                441201,441204,441223,441227,441283,441285,441301,441304,441322,441325,
                441401,441403,441421,441425,441426,441428,441481,441482,441501,441503,
                441521,441522,441523,441524,441581,441582,441601,441603,441621,441626,
                441701,441703,441721,441722,441723,441724,441781,441782,441801,441803,
                441821,441822,441823,441824,441825,441828,441881,441883,445101,445103,
                445121,445123,445201,445203,445221,445223,445224,445225,445281,445282,
                445301,445303,445321,445324,445381,445382,450101,450104,450105,450106,
                450107,450110,450122,450128,450201,450206,450221,450227,450301,450306,
                450311,450312,450321,450333,450401,450402,450403,450406,450421,450424,
                450481,450482,450501,450504,450512,450513,450521,450522,450601,450604,
                450621,450622,450681,450682,450701,450704,450721,450723,450801,450805,
                450821,450822,450881,450882,450901,450903,450921,450925,450981,450982,
                451001,451003,451021,451032,451101,451103,451121,451124,451201,451203,
                451221,451230,451281,451282,451301,451303,451321,451325,451381,451382,
                451401,451403,451421,451426,451481,451482,460101,460102,460105,460109,
                460201,460202,460321,460324,469001,469004,469005,469008,469021,469040,
                500101,500120,500222,500239,500240,500244,500381,500385,510101,510102,
                510104,510109,510112,510116,510121,510123,510124,510125,510129,510130,
                510131,510133,510181,510185,510301,510305,510311,510312,510321,510323,
                510401,510404,510411,510412,510421,510423,510501,510505,510521,510523,
                510524,510526,510601,510602,510603,510604,510623,510624,510626,510627,
                510681,510684,510701,510702,510703,510705,510722,510728,510781,510782,
                510801,510803,510811,510813,510821,510825,510901,510902,510903,510905,
                510921,510924,511001,511003,511011,511012,511024,511026,511028,511029,
                511101,511103,511111,511114,511123,511125,511126,511127,511129,511130,
                511132,511134,511181,511182,511301,511305,511321,511326,511381,511382,
                511401,511403,511421,511426,511501,511504,511521,511530,511601,511603,
                511621,511624,511681,511682,511701,511703,511721,511726,511781,511782,
                511801,511804,511821,511828,511901,511903,511921,511924,512001,512003,
                512021,512023,512081,512082,513221,513234,513321,513339,513401,513402,
                513422,513438,520101,520104,520111,520115,520121,520124,520181,520182,
                520201,520202,520203,520204,520221,520223,520301,520304,520321,520331,
                520381,520383,520401,520403,520421,520426,520501,520503,520521,520528,
                520601,520604,520621,520629,522201,522202,522222,522231,522301,522302,
                522322,522329,522401,522402,522422,522429,522601,522602,522622,522637,
                522701,522703,522722,522724,522725,522733,530101,530104,530111,530115,
                530121,530123,530124,530130,530181,530182,530301,530303,530321,530327,
                530328,530329,530381,530382,530401,530403,530421,530429,530501,530503,
                530521,530525,530601,530603,530621,530631,530701,530703,530721,530725,
                530801,530803,530821,530830,530901,530903,530921,530928,532301,532302,
                532322,532330,532331,532332,532501,532504,532522,532533,532601,532602,
                532621,532629,532801,532802,532822,532824,532901,532902,532922,532933,
                533102,533104,533122,533125,533321,533322,533323,533326,533421,533424,
                540101,540103,540121,540128,542121,542130,542132,542134,542221,542230,
                542231,542234,542301,542302,542322,542339,542421,542431,542521,542528,
                542621,542628,610101,610105,610111,610117,610122,610123,610124,610127,
                610201,610205,610222,610223,610301,610305,610322,610325,610326,610332,
                610401,610405,610422,610432,610481,610482,610501,610503,610521,610529,
                610581,610583,610601,610603,610621,610633,610701,610703,610721,610731,
                610801,610803,610821,610832,610901,610903,610921,610930,611001,611003,
                611021,611027,620101,620106,620111,620112,620121,620124,620201,620202,
                620301,620303,620321,620322,620401,620404,620421,620424,620501,620504,
                620521,620526,620601,620603,620621,620624,620701,620703,620721,620726,
                620801,620803,620821,620827,620901,620903,620921,620925,620981,620983,
                621001,621003,621021,621028,621101,621103,621121,621127,621201,621203,
                621221,621229,622901,622902,622921,622928,623001,623002,623021,623028,
                630101,630106,630121,630124,632121,632124,632126,632129,632221,632225,
                632321,632325,632521,632526,632621,632627,632721,632727,632801,632803,
                632821,632824,640101,640102,640104,640107,640121,640123,640181,640182,
                640201,640203,640205,640206,640221,640222,640301,640304,640323,640325,
                640381,640382,640401,640403,640422,640426,640501,640503,640521,640523,
                650101,650110,650121,650122,650201,650206,652101,652102,652122,652124,
                652201,652202,652222,652224,652301,652304,652323,652326,652327,652329,
                652701,652702,652722,652724,652801,652802,652822,652830,652901,652902,
                652922,652930,653001,653002,653022,653025,653101,653102,653121,653132,
                653201,653202,653221,653228,654002,654004,654021,654029,654201,654203,
                654221,654222,654223,654227,654301,654302,654321,654327,659001,659005};
        for(int i=0,len=regionArray.length/2;i<len;i++){
            for(int j=regionArray[2*i];j<regionArray[2*i+1];j++)//压缩的省市县数据解压
            {regions[j]=true;}
        }
    }

    public boolean validate() {
        if (null == cacheValidateResult) {
            boolean result = true;
            // 身份证号不能为空
            result = result && (null != cardNumber);
            // 身份证号长度是18(新证)
            result = result && NEW_CARD_NUMBER_LENGTH == cardNumber.length();
            // 身份证号的前17位必须是阿拉伯数字
            for (int i = 0; result && i < NEW_CARD_NUMBER_LENGTH - 1; i++) {
                char ch = cardNumber.charAt(i);
                result = result && ch >= '0' && ch <= '9';
            }
            // 身份证号的第18位校验正确
            result = result
                    && (calculateVerifyCode(cardNumber) == cardNumber
                    .charAt(NEW_CARD_NUMBER_LENGTH - 1));
            // 出生日期不能晚于当前时间，并且不能早于1900年
            try {
                Date birthDate = this.getBirthDate();
                result = result && null != birthDate;
                result = result && birthDate.before(new Date());
                result = result && birthDate.after(MINIMAL_BIRTH_DATE);
                /**
                 * 出生日期中的年、月、日必须正确,比如月份范围是[1,12],日期范围是[1,31]，还需要校验闰年、大月、小月的情况时，
                 * 月份和日期相符合
                 */
                String birthdayPart = this.getBirthDayPart();
                String realBirthdayPart = this.createBirthDateParser().format(
                        birthDate);
                result = result && (birthdayPart.equals(realBirthdayPart));
            } catch (Exception e) {
                result = false;
            }
            // TODO 完整身份证号码的省市县区检验规则
//            result = result && isValidC(cardNumber);
            cacheValidateResult = Boolean.valueOf(result);
        }
        return cacheValidateResult;
    }

    /**
     * 如果是15位身份证号码，则自动转换为18位
     *
     * @param cardNumber
     */
    public IdcardValidator(String cardNumber) {
        if (null != cardNumber) {
            cardNumber = cardNumber.trim();
            if (OLD_CARD_NUMBER_LENGTH == cardNumber.length()) {
                cardNumber = contertToNewCardNumber(cardNumber);
            }
        }
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getAddressCode() {
        this.checkIfValid();
        return this.cardNumber.substring(0, 6);
    }

    public Date getBirthDate() {
        if (null == this.cacheBirthDate) {
            try {
                this.cacheBirthDate = this.createBirthDateParser().parse(
                        this.getBirthDayPart());
            } catch (Exception e) {
                throw new RuntimeException("身份证的出生日期无效");
            }
        }
        return new Date(this.cacheBirthDate.getTime());
    }

    public boolean isMale() {
        return 1 == this.getGenderCode();
    }

    public boolean isFemal() {
        return false == this.isMale();
    }

    /**
     * 获取身份证的第17位，奇数为男性，偶数为女性
     *
     * @return
     */
    private int getGenderCode() {
        this.checkIfValid();
        char genderCode = this.cardNumber.charAt(NEW_CARD_NUMBER_LENGTH - 2);
        return (((int) (genderCode - '0')) & 0x1);
    }

    private String getBirthDayPart() {
        return this.cardNumber.substring(6, 14);
    }

    private SimpleDateFormat createBirthDateParser() {
        return new SimpleDateFormat(BIRTH_DATE_FORMAT);
    }

    private void checkIfValid() {
        if (false == this.validate()) {
            throw new RuntimeException("身份证号码不正确！");
        }
    }

    // 身份证号码中的出生日期的格式
    private final static String BIRTH_DATE_FORMAT = "yyyyMMdd";
    // 身份证的最小出生日期,1900年1月1日
    private final static Date MINIMAL_BIRTH_DATE = new Date(-2209017600000L);
    private final static int NEW_CARD_NUMBER_LENGTH = 18;
    private final static int OLD_CARD_NUMBER_LENGTH = 15;
    /**
     * 18位身份证中最后一位校验码
     */
    private final static char[] VERIFY_CODE = { '1', '0', 'X', '9', '8', '7',
            '6', '5', '4', '3', '2' };
    /**
     * 18位身份证中，各个数字的生成校验码时的权值
     */
    private final static int[] VERIFY_CODE_WEIGHT = { 7, 9, 10, 5, 8, 4, 2, 1,
            6, 3, 7, 9, 10, 5, 8, 4, 2 };

    /**
     * <li>校验码（第十八位数）：<br/>
     * <ul>
     * <li>十七位数字本体码加权求和公式 S = Sum(Ai * Wi), i = 0...16 ，先对前17位数字的权求和；
     * Ai:表示第i位置上的身份证号码数字值 Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4
     * 2；</li>
     * <li>计算模 Y = mod(S, 11)</li>
     * <li>通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 校验码: 1 0 X 9 8 7 6 5 4 3 2</li>
     * </ul>
     *
     * @param cardNumber
     * @return
     */
    private static char calculateVerifyCode(CharSequence cardNumber) {
        int sum = 0;
        for (int i = 0; i < NEW_CARD_NUMBER_LENGTH - 1; i++) {
            char ch = cardNumber.charAt(i);
            sum += ((int) (ch - '0')) * VERIFY_CODE_WEIGHT[i];
        }
        return VERIFY_CODE[sum % 11];
    }

    /**
     * 把15位身份证号码转换到18位身份证号码<br>
     * 15位身份证号码与18位身份证号码的区别为：<br>
     * 1、15位身份证号码中，"出生年份"字段是2位，转换时需要补入"19"，表示20世纪<br>
     * 2、15位身份证无最后一位校验码。18位身份证中，校验码根据根据前17位生成
     *
     * @param cardNumber
     * @return
     */
    private static String contertToNewCardNumber(String oldCardNumber) {
        StringBuilder buf = new StringBuilder(NEW_CARD_NUMBER_LENGTH);
        buf.append(oldCardNumber.substring(0, 6));
        buf.append("19");
        buf.append(oldCardNumber.substring(6));
        buf.append(IdcardValidator.calculateVerifyCode(buf));
        return buf.toString();
    }
    public static void main(String[] args) {
//        System.out.println(new IdcardValidator("420323197607022013".toUpperCase()).validate());
        long s =System.currentTimeMillis();
        System.out.println(CheckParamUtil.checkIdCard("519236200002226196"));
        System.out.println(System.currentTimeMillis()-s);
    }
}