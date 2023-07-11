package ee.carlrobert.openai.client.completion.chat.request;

import ee.carlrobert.openai.client.completion.CompletionRequest;
import ee.carlrobert.openai.client.completion.chat.ChatCompletionModel;

public class FospChatCompletionRequest extends CompletionRequest {

    private final String model;
    private final String prompt;
    private final String promptEnc;
    private final String session;

    private FospChatCompletionRequest(Builder builder) {
        super(builder);
        this.model = builder.model;
        this.prompt = builder.prompt;
        this.promptEnc = builder.promptEnc;
        this.session = builder.session;
    }

    public String getPrompt() {
        return prompt;
    }

    public String getPromptEnc() {
        return promptEnc;
    }

    public String getSession() {
        return session;
    }


    public String getModel() {
        return model;
    }

    public static class Builder extends CompletionRequest.Builder {
        private final String prompt;
        private final String promptEnc;
        private final String session;
        private String model = ChatCompletionModel.GPT_3_5.getCode();

        public Builder(String prompt, String promptEnc, String session) {
            this.prompt = prompt;
            this.promptEnc = promptEnc;
            this.session = session;
        }

        public Builder setModel(ChatCompletionModel model) {
            this.model = model.getCode();
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        @Override
        public FospChatCompletionRequest build() {
            return new FospChatCompletionRequest(this);
        }
    }
}
