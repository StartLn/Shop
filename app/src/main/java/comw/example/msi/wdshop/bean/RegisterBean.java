package comw.example.msi.wdshop.bean;

/**
 * <p>文件描述：注册需要的实体类<p>
 * <p>作者：${Ln}<p>
 * <p>创建时间：2019/1/3  11:03<p>
 * <p>更改时间：2019/1/3  11:03<p>
 * <p>版本号：1<p>
 */
public class RegisterBean {
    /*http://172.17.8.100/small/user/v1/register*/
    /**
     * message : 注册成功
     * status : 0000
     */

    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
