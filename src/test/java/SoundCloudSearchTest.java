import manage.test.TestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import restful.CustomApiRequest;
import restful.SoundCloud._Collections;
import utils.LogUtils;
import utils.MySoftAsserter;
import utils.ReportUtils;

import static manage.request.Method.GET;

public class SoundCloudSearchTest extends TestBase {

    private static final String userId = "";
    private static final String clientId = "";

    @DataProvider
    public Object[][] testData() {
        return new Object[][] {
                {"Escape From Wonderland", "1", "0", "en"},
                {"Deep Purple", "5", "0", "en"},
                {"каста", "5", "0", "en"}
        };
    }


    @Test(dataProvider = "testData")
    public void searchTest(String searchQuery, String limit, String offset, String appLocale) {
        /*
        *  Set Test Description
        * */
        ReportUtils.setDescription("Test SoundCloud search");
        /*
        *  Initialize Custom Soft Asserter
        * */
        MySoftAsserter soft = new MySoftAsserter.Builder().build();

        /*
        *  Build API request
        * */
        CustomApiRequest api = CustomApiRequest.Builder().method(GET)
                .setDomain("https://api-v2.soundcloud.com")
                .setHeaders(null)
                .setURI("/search?")
                .setURI("q=" + searchQuery)
                .setURI("&user_id=" + userId)
                .setURI("&client_id=" + clientId)
                .setURI("&limit=" + limit)
                .setURI("&offset=" + offset)
                .setURI("&app_locale=" + appLocale)
                .build();

        api.sendRequest();
        api.Validate().statusCodeEquals(200);

        /*
        *  Parse response into the object
        * */
        _Collections collections = api.parseResponse(_Collections.class);

        /*
        *  Assert logic
        * */
        Assert.assertTrue(collections.getCollection().size() == Integer.parseInt(limit), " size equals to limit?");

        collections.getCollection().stream().forEach(c -> {
            LogUtils.bold("Current title: " + c.getTitle());
            soft.assertTrue(c.getTitle().toLowerCase().contains(searchQuery.toLowerCase()), " Title contains search query?");
        });

        soft.assertAll();
    }
}
