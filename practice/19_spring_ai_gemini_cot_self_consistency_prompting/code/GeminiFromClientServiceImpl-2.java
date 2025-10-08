\begin{minted}{java}
    ...
    @Value("classpath:templates/get-artifact-genre-prompt.st")
    private Resource artifactGenrePrompt;    

    @Override
    public Answer getGenreForArtifact(ArtifactRequest artifactRequest) {
        List<ArtifactGenreResponse> responses = new ArrayList<ArtifactGenreResponse>();
        for (int i = 0; i <5; i++) {
                List<ArtifactGenreResponse> genreResponse = this.chatClient.prompt()
                .options(ChatOptions.builder()
                .model("gemini-2.0-flash")
                .temperature(2.0)
                //.topP(1.0)
                //.topK(30)
                .maxTokens(1024)
                //.frequencyPenalty(0.1)
                //.presencePenalty(0.1)
                .build())
                .user(u -> u.text(this.artifactGenrePrompt)
                        .params(Map.of("descrizione", artifactRequest.artifact().body(),
                                "artefatto", artifactRequest.artifact().type(),
                                "generi_possibili", ArtifactGenre.values())))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('{')
                        .endDelimiterToken('}')
                        .build())
                .call()
                .entity(new ParameterizedTypeReference<List<ArtifactGenreResponse>>() {});
                responses.addAll(genreResponse);
        }
        return new Answer(responses.stream().collect(Collectors.groupingBy(s -> s.genre(), 
                                                     Collectors.counting())
                                                    ).toString());
    }
}
\end{minted}