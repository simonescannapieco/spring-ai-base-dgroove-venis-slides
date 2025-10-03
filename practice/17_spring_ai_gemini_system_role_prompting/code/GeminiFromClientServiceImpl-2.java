\begin{minted}{java}
    ...
    @Value("classpath:templates/set-advice-system-prompt.st")
    private Resource adviceSystemPrompt;

    @Value("classpath:templates/get-advice-user-prompt.st")
    private Resource adviceUserPrompt;

    @Override
    public Answer getSuggestionForArtifact(ArtifactRequest artifactRequest) {
        String sentiment = getSentimentForArtifact(artifactRequest).answer();
        String ner = getNERinYAMLForArtifact(artifactRequest).answer();
        String chatResponse = this.chatClient.prompt()
                .options(ChatOptions.builder()
                .model("gemini-2.0-flash")
                .temperature(0.3)
                .topP(1.0)
                //.topK(30)
                .maxTokens(500)
                //.frequencyPenalty(0.1)
                //.presencePenalty(0.1)
                .build())
                .system(s -> s.text(this.adviceSystemPrompt)
                        .params(Map.of("sentiment", sentiment, "ner", ner,
                                "artefatto", artifactRequest.artifact().type())))
                .user(u -> u.text(this.adviceUserPrompt)
                        .params(Map.of("titolo", artifactRequest.artifact().title(),
                                "sottotitolo", artifactRequest.artifact().subtitle(),
                                "corpo", artifactRequest.artifact().body())))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('{')
                        .endDelimiterToken('}')
                        .build())
                .call()
                .content();
        return new Answer(chatResponse);
    }

}
\end{minted}