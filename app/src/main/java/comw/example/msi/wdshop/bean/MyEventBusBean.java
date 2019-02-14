package comw.example.msi.wdshop.bean;

/**
 * <p>文件描述：<p>
 * <p>作者：${Ln}<p>
 * <p>创建时间：2019/1/11  11:11<p>
 * <p>更改时间：2019/1/11  11:11<p>
 * <p>版本号：1<p>
 */
public class MyEventBusBean {
    private int userId;
    private String sessionId;
    private int shopid;
    private String name;

    public MyEventBusBean() {

    }

    public MyEventBusBean(int userId, String sessionId, int shopid, String name) {
        this.userId = userId;
        this.sessionId = sessionId;
        this.shopid = shopid;
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyEventBusBean{" +
                "userId=" + userId +
                ", sessionId='" + sessionId + '\'' +
                ", shopid=" + shopid +
                ", name='" + name + '\'' +
                '}';
    }
}
