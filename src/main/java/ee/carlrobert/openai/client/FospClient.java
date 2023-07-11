package ee.carlrobert.openai.client;

import ee.carlrobert.openai.client.completion.chat.FospChatCompletionClient;
import ee.carlrobert.openai.client.completion.text.FospTextCompletionClient;

public class FospClient extends Client {

  private final String organization;

  private FospClient(Builder builder) {
    super(builder);
    this.organization = builder.organization;
  }

  public String getOrganization() {
    return organization;
  }

  public static class Builder extends Client.Builder {

    private String organization;

    public Builder(String apiKey) {
      super(apiKey);
    }

    public Builder setOrganization(String organization) {
      this.organization = organization;
      return this;
    }

    @Override
    public FospChatCompletionClient buildChatCompletionClient() {
      return new FospChatCompletionClient(new FospClient(this));
    }

    @Override
    public FospTextCompletionClient buildTextCompletionClient() {
      return new FospTextCompletionClient(new FospClient(this));
    }
  }
}
