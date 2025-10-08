\begin{minted}{java}
    ...
    @Value("classpath:templates/get-lemma-translation-prompt.st")
    private Resource lemmaTanslationPrompt;

    @Override
    public Answer getTranslationForLemma(TranslationRequest translationRequest) {
        
        List<String> stopSequences = Stream.of(" ", "|", "\n").collect(Collectors.toList());

        String chatResponse = this.chatClient.prompt()
                .options(ChatOptions.builder()
                .model("gemini-2.0-flash")
                .temperature(0.1)
                //.topP(1.0)
                //.topK(30)
                .maxTokens(20)
                //.frequencyPenalty(0.1)
                //.presencePenalty(0.1)
                .stopSequences(stopSequences)
                .build())
                .user(u -> u.text(this.lemmaTanslationPrompt)
                        .params(Map.of("lemma", translationRequest.lemma())))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('{')
                        .endDelimiterToken('}')
                        .build())
                .call()
                .content();

        return new Answer(chatResponse);
    }
}
\end{minted}