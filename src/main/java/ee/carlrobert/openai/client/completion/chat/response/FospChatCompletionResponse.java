package ee.carlrobert.openai.client.completion.chat.response;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FospChatCompletionResponse {
    private final String success;
    private final String code;
    private final String message;
    private final String content;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public FospChatCompletionResponse(
            @JsonProperty("success") String success,
            @JsonProperty("code") String code,
            @JsonProperty("message") String message,
            @JsonProperty("content") String content) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public String getSuccess() {
        return success;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getContent() {
        return content;
    }
}
