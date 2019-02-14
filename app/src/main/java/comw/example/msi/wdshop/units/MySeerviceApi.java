package comw.example.msi.wdshop.units;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * <p>文件描述：API封装请求方式<p>
 * <p>作者：${Ln}<p>
 * <p>创建时间：2019/1/3  15:17<p>
 * <p>更改时间：2019/1/3  15:17<p>
 * <p>版本号：1<p>
 */
public interface MySeerviceApi {
    //观察者模式
    //get的请求方式8
    /*@GET
    Observable<ResponseBody>get(@Url String url, @QueryMap Map<String,String>map);
    //post的请求方式
    @POST
    Observable<ResponseBody>posts(@Url String url, @QueryMap Map<String,String>map);*/
    @GET
    Observable<ResponseBody>gets(@Url String url, @HeaderMap Map<String,String>headmap ,@QueryMap Map<String,String>bodymap);

    @POST
    Observable<ResponseBody>posts(@Url String url, @HeaderMap Map<String,String>headmap ,@QueryMap Map<String,String>bodymap);

    @PUT
    Observable<ResponseBody>puts(@Url String url, @HeaderMap Map<String,String>headmap ,@QueryMap Map<String,String>bodymap);

    @DELETE
    Observable<ResponseBody>deletes(@Url String url, @HeaderMap Map<String,String>headmap ,@QueryMap Map<String,String>bodymap);


}
