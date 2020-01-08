import com.watson.apitest.APIFactory;
import jodd.http.HttpResponse;
import org.testng.annotations.Test;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2020/1/83:25 PM
 */
public class HttpTest {

    @Test
    public void testBaiduSearch(){
        String url="";
        HttpResponse httpResponse = APIFactory.createHttpTest().doPost(url);
        System.out.println(httpResponse.body());

    }
}
