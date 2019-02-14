package comw.example.msi.wdshop.mvp;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * <p>文件描述：<p>
 * <p>作者：${Ln}<p>
 * <p>创建时间：2019/1/3  15:05<p>
 * <p>更改时间：2019/1/3  15:05<p>
 * <p>版本号：1<p>
 */
public class Contacts {
    //用于管理所有的接口
    //内网的前缀
    public static final String BASE_URL="http://172.17.8.100/";
    //外网的前缀
    public static final String BASE_URLS="http://mobile.bwstudent.com/";

    //登录
    public static final String BASE_LOGIN="small/user/v1/login";

    //注册
    public static final String BASE_REGISTER="small/user/v1/register";

   /* 3.修改昵称
    接口地址：http：//172.17.8.100/small/user/verify/v1/modifyUserNick
    请求方式：PUT
    接口描述：用户修改自己的昵称操作*/

    /* 4.修改用户密码
    接口地址：http：//172.17.8.100/small/user/verify/v1/modifyUserPwd
    请求方式：PUT
    接口描述：修改用户密码*/

    /*5.用户上传头像
    接口地址：http：//172.17.8.100/small/user/verify/v1/modifyHeadPic
    请求方式：POST
    接口描述：用户上传头像*/

    /*6.根据用户ID查询用户信息
    接口地址：http：//172.17.8.100/small/user/verify/v1/getUserById
    请求方式：GET
    接口描述：根据用户ID查询用户信息，作为用户资料的展示*/

    /*7.收货地址列表
    接口地址：http：//172.17.8.100/small/user/verify/v1/receiveAddressList
    请求方式：GET
    接口描述：用户自身的收货地址列表*/

    /*8.新增收货地址
    接口地址：http：//172.17.8.100/small/user/verify/v1/addReceiveAddress
    请求方式：POST
    接口描述：用户新增收货地址*/

    /*9.设置默认收货地址
    接口地址：http：//172.17.8.100/small/user/verify/v1/setDefaultReceiveAddress
    请求方式：POST
    接口描述：用户将多个收货列表中的一个设置为默认收货地址*/

    /*10.修改收货信息
    接口地址：http：//172.17.8.100/small/user/verify/v1/changeReceiveAddress
    请求方式：PUT
    接口描述：用户对已存在收货地址进行修改操作*/

    /*11.查询用户钱包
    接口地址：http：//172.17.8.100/small/user/verify/v1/findUserWallet
    请求方式：GET
    接口描述：查询用户钱包，用于展示用户的余额与消费明细*/
    //------------------------------------------------------------------------------------------------
    //Xbanner1.banner展示列表
    public static final String BASE_XBANNER="small/commodity/v1/bannerShow";

    //2.首页商品信息列表
    public static final String BASE_CommodityList="small/commodity/v1/commodityList";

    // 根据商品列表归属标签查询商品信息
    public static final String BASE_CommodityListByLabel="small/commodity/v1/findCommodityListByLabel";
    //http://172.17.8.100/small/commodity/v1/findCommodityListByLabel?labelId=1003&page=1&count=5

    //商品详情
    public static final String BASE_CommodityDetailsById="small/commodity/v1/findCommodityDetailsById";
    //http://172.17.8.100/small/commodity/v1/findCommodityDetailsById?userId=18&sessionId=15320748258726$commodityId=6

    //根据关键词查询商品信息
    public static final String BASE_CommodityByKeyword="small/commodity/v1/findCommodityByKeyword";

    //根据二级类目查询商品信息
    public static final String BASE_CommodityByCategory="small/commodity/v1/findCommodityByCategory";

    //我的足迹
    public static final String BASE_BrowseList="small/commodity/verify/v1/browseList";
    //http://172.17.8.100/small/commodity/verify/v1/browseList
    //8. 商品评论列表
    public static final String BASE_CommodityCommentList="small/commodity/v1/CommodityCommentList";

    //9.  商品评论
    public static final String BASE_AddCommodityComment="small/commodity/verify/v1/addCommodityComment";
    //post请求接口描述:  商品评论，商品评论的入口在订单待评论里,用户在未确认收货时，不能对商品进行评论

    //10.查询一级商品类目
    public static final String BASE_FindFirstCategory="small/commodity/v1/findFirstCategory";
    //接口描述:查询一级商品类目，用于展示商品一级类目

    //11.查询二级商品类目
    public static final String BASE_FindSecondCategory="small/commodity/v1/findSecondCategory";
    //接口描述:查询二级商品类目，根据商品一级类目，查看其下的二级类目

    //------------------------------------------------------------------------------------------------
    /*1.创建订单
    接口地址：http://172.17.8.100/small/order/verify/v1/createOrder
    请求方式:POST
    接口描述:创建订单*/
    public static final String BASE_CreateOrder="small/order/verify/v1/createOrder";


    /*2.支付
    接口地址：http://172.17.8.100/small/order/verify/v1/pay
    请求方式:POST
    接口描述:支付*/
    public static final String BASE_Pay="small/order/verify/v1/pay";

   /* 3. 根据订单状态查询订单信息
    接口地址：http://172.17.8.100/small/order/verify/v1/findOrderListByStatus
    请求方式:GET
    接口描述: 根据订单状态查询订单信息。客户端需要根据返回数据里的订单状态做出对应的业务处理。具体请参考【必读事项】*/
    public static final String BASE_FindOrderListByStatus="small/order/verify/v1/findOrderListByStatus";

    /*4.  删除订单
    接口地址：http://172.17.8.100/small/order/verify/v1/deleteOrder
    请求方式:DELETE
    接口描述:  删除订单*/
    public static final String BASE_DeleteOrder="small/order/verify/v1/deleteOrder";

    /*5. 收货
    接口地址：http://172.17.8.100/small/order/verify/v1/confirmReceipt
    请求方式:PUT
    接口描述: 收货*/
    public static final String BASE_ConfirmReceipt="small/order/verify/v1/confirmReceipt";

    /*6.查询购物车
    接口地址：http://172.17.8.100/small/order/verify/v1/findShoppingCart
    请求方式:GET
    接口描述:查询购物车*/
    public static final String BASE_FindShoppingCart="small/order/verify/v1/findShoppingCart";

   /* 7.  同步购物车数据
    接口地址：http://172.17.8.100/small/order/verify/v1/syncShoppingCart
    请求方式:PUT
    接口描述:  同步购物车数据*/
    public static final String BASE_SyncShoppingCart="small/order/verify/v1/syncShoppingCart";

    /*8.查询订单明细数据
    接口地址：http://172.17.8.100/small/order/verify/v1/findOrderInfo
    请求方式:GET
    接口描述:查询订单明细数据,使用场景为，查看用户钱包消费明细时调用*/
    public static final String BASE_FindOrderInfo="small/order/verify/v1/findOrderInfo";

    //------------------------------------------------------------------------------------------------
    /*圈子列表
    接口地址：http：//172.17.8.100/small/circle/v1/findCircleList
    请求方式：GET*/
    public static final String BASE_CircleList="small/circle/v1/findCircleList";

    /*发布圈子
    接口地址：http：//172.17.8.100/small/circle/verify/v1/releaseCircle
    请求方式：POST
    接口描述：用户在写订单下商品评论时，如果勾选【同步到圈子】时调用该接口*/

    /*3.删除我发表过的圈子
    接口地址：http：//172.17.8.100/small/circle/verify/v1/deleteCircle
    请求方式：DELETE
    接口描述：删除圈子，用户只能对自己发布的圈子进行删除操作，删除圈子的入口将在我的圈子里，用户在未确认收货与评论时，不允许发布圈子*/

    /*我的圈子
    接口地址：http：//172.17.8.100/small/circle/verify/v1/findMyCircleById
    请求方式：GET
    接口描述：我的圈子，用户查看自己发布的圈子的列表*/

    /*5.圈子点赞
    接口地址：http：//172.17.8.100/small/circle/verify/v1/addCircleGreat
    请求方式：POST
    接口描述：用户在登录状态下，对圈子里其他用户发布的圈子进行点赞的操作*/

    /*6.取消点赞
    接口地址：http：//172.17.8.100/small/circle/verify/v1/cancelCircleGreat
    请求方式：DELETE
    接口描述：取消点赞，用户在登录状态下，对圈子里其他用户发布的圈子进行点赞后，取消点赞的操作*/
}
