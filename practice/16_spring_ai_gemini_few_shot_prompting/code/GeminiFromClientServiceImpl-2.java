\begin{minted}{java}
    ...
    
    @Value("classpath:templates/get-ner-yaml-prompt.st")
    private Resource artifactNERYAMLPrompt;
    
    @Override
    public Answer getNERinYAMLForArtifact(ArtifactRequest artifactRequest) {
        
        String chatResponse = this.chatClient.prompt()
                .options(ChatOptions.builder()
                .model("gemini-2.0-flash")
                .temperature(0.1)
                .topP(1.0)
                //.topK(30)
                .maxTokens(250)
                //.frequencyPenalty(0.1)
                //.presencePenalty(0.1)
                .build())
                .user(u -> u.text(this.artifactNERYAMLPrompt)
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