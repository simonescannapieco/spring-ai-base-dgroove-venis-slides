\begin{minted}{java}
    ...
    @Value("classpath:templates/set-prompt-alternatives-system-prompt.st")
    private Resource promptAlternativesSystemPrompt;

    @Value("classpath:templates/get-prompt-alternatives-user-prompt.st")
    private Resource promptAlternativesUserPrompt;

    @Value("classpath:templates/get-ordered-prompt-alternatives-prompt.st")
    private Resource orderedPromptAlternativesPrompt; 

    @Override
    public PromptEvaluationResponse getEvaluatedPrompts(PromptEvaluationRequest promptEvaluationRequest) {
        BeanOutputConverter<PromptEvaluationResponse> converter = 
                           new BeanOutputConverter<>(PromptEvaluationResponse.class);
        String listResponse = this.chatClient.prompt()
                .options(ChatOptions.builder()
                .model("gemini-2.0-flash")
                .temperature(2.0)
                //.topP(1.0)
                //.topK(30)
                .maxTokens(2000)
                //.frequencyPenalty(0.1)
                //.presencePenalty(0.1)
                .build())
                .system(s -> s.text(this.promptAlternativesSystemPrompt)
                        .params(Map.of("numero", 10)))
                .user(u -> u.text(this.promptAlternativesUserPrompt)
                        .params(Map.of("prompt", promptEvaluationRequest.prompt())))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('{')
                        .endDelimiterToken('}')
                        .build())
                .call()
                .content();
        
   ...
\end{minted}