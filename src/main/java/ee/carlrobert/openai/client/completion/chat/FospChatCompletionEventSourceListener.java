package ee.carlrobert.openai.client.completion.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ee.carlrobert.openai.client.completion.ApiResponseError;
import ee.carlrobert.openai.client.completion.CompletionEventListener;
import ee.carlrobert.openai.client.completion.CompletionEventSourceListener;
import ee.carlrobert.openai.client.completion.ErrorDetails;
import ee.carlrobert.openai.client.completion.chat.response.FospChatCompletionResponse;

import java.util.function.Consumer;

public class FospChatCompletionEventSourceListener extends CompletionEventSourceListener {

  public FospChatCompletionEventSourceListener(CompletionEventListener listeners, boolean retryOnReadTimeout, Consumer<String> onRetry) {
    super(listeners, retryOnReadTimeout, onRetry);
  }

  protected String getMessage(String data) throws JsonProcessingException {
    var content = new ObjectMapper()
        .readValue(data, FospChatCompletionResponse.class)
        .getContent();
    if (content != null) {
      return content;
    }
    return "";
  }

  @Override
  protected ErrorDetails getErrorDetails(String data) throws JsonProcessingException {
    return new ObjectMapper().readValue(data, ApiResponseError.class).getError();
  }
}
