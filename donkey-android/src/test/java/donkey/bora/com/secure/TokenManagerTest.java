package donkey.bora.com.secure;

import android.os.Environment;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;

@RunWith(MockitoJUnitRunner.class)
public class TokenManagerTest {

    @Mock
    Environment environment;

    @Test
    public void testBasic() {

        String path = "test/";
        TokenManager.save("123456", path);
        String token = TokenManager.load(path);
        Assert.assertThat(token, is("123456"));
    }
}
