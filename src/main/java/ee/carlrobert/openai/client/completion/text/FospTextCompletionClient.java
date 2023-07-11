package ee.carlrobert.openai.client.completion.text;

import ee.carlrobert.openai.client.ClientCode;
import ee.carlrobert.openai.client.FospClient;
import ee.carlrobert.openai.client.completion.CompletionEventListener;
import ee.carlrobert.openai.client.completion.FospCompletionClient;

import java.util.function.Consumer;

public class FospTextCompletionClient extends FospCompletionClient {

  public FospTextCompletionClient(FospClient client) {
    super(client, "/v1/completions");
  }

  @Override
  protected TextCompletionEventSourceListener getEventListener(
          CompletionEventListener listeners,
          boolean retryOnReadTimeout,
          Consumer<String> onRetry) {
    return new TextCompletionEventSourceListener(listeners, retryOnReadTimeout, onRetry);
  }

  @Override
  public ClientCode getClientCode() {
    return ClientCode.TEXT_COMPLETION;
  }
}
