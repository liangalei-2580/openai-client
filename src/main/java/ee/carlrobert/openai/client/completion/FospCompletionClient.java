package ee.carlrobert.openai.client.completion;

import ee.carlrobert.openai.PropertiesLoader;
import ee.carlrobert.openai.client.FospClient;

import java.util.HashMap;
import java.util.Map;


public abstract class FospCompletionClient extends CompletionClient {

  private static final String BASE_URL = PropertiesLoader.getValue("fosp.baseUrl");

  private static final String OPEN_ID = PropertiesLoader.getValue("fosp.client.open-id");

  private static final String DEVELOPER_SECRET = PropertiesLoader.getValue("fosp.client.developer-secret");

  private static final String SERVICE_CODE = PropertiesLoader.getValue("fosp.service.code");


  private final FospClient client;

  public FospCompletionClient(FospClient client, String path) {
    super(client, BASE_URL , path);
    this.client = client;
  }

  @Override
  protected Map<String, String> getRequiredHeaders() {
    var headers = new HashMap<String, String>(6);
    headers.put("Open-ID", OPEN_ID);
    headers.put("Developer-Secret", DEVELOPER_SECRET);
    headers.put("Service-Code", SERVICE_CODE);
    headers.put("Service-Type", "3");
    headers.put("User-Flag", this.client.getApiKey());
    headers.put("AIGC-Target-DC", "usa");
    return headers;
  }
}
