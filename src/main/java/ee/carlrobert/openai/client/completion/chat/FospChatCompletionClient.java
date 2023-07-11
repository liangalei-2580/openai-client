package ee.carlrobert.openai.client.completion.chat;

import ee.carlrobert.openai.client.ClientCode;
import ee.carlrobert.openai.client.FospClient;
import ee.carlrobert.openai.client.completion.CompletionEventListener;
import ee.carlrobert.openai.client.completion.FospCompletionClient;

import java.util.function.Consumer;

public class FospChatCompletionClient extends FospCompletionClient {

  public FospChatCompletionClient(FospClient client) {
    super(client, "/aigc-direct/api/aigc-server-t2/stream/v1/chat/completion");
  }

  @Override
  protected FospChatCompletionEventSourceListener getEventListener(
          CompletionEventListener listeners,
          boolean retryOnReadTimeout,
          Consumer<String> onRetry) {
    return new FospChatCompletionEventSourceListener(listeners, retryOnReadTimeout, onRetry);
  }

  @Override
  public ClientCode getClientCode() {
    return ClientCode.CHAT_COMPLETION;
  }
}
