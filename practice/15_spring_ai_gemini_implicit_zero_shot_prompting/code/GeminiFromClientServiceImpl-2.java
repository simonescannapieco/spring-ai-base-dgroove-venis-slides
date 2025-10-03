\begin{minted}{java}
    ...
    
    @Value("classpath:templates/get-artifact-sentiment-prompt.st")
    private Resource artifactSentimentPrompt;

    @Override
    public Answer getSentimentForArtifact(ArtifactRequest artifactRequest) {
        List<String> stopSequences = Stream.of(" ", "\n").collect(Collectors.toList());
        Sentiment chatResponse = Sentiment.valueOf(this.chatClient.prompt()
                .options(ChatOptions.builder()
                .model("gemini-2.0-flash")
                .temperature(0.1)
                //.topP(1.0)
                //.topK(30)
                .maxTokens(10)
                //.frequencyPenalty(0.1)
                //.presencePenalty(0.1)
                .stopSequences(stopSequences)
                .build())
                .user(u -> u.text(this.artifactSentimentPrompt)
                        .params(Map.of("recensione", artifactRequest.artifact().body(), 
                                       "artefatto", artifactRequest.artifact().type())))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('{')
                        .endDelimiterToken('}')
                        .build())
                .call()
                .content()
                );
        return new Answer(chatResponse.getSentiment());

    }
}
\end{minted}