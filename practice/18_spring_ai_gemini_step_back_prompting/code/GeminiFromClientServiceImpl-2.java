\begin{minted}{java}
    ...
    @Value("classpath:templates/get-key-settings-for-artifact-system-prompt.st")
    private Resource keySettingsForArtifactSystemPrompt;

    @Value("classpath:templates/get-key-settings-for-artifact-user-prompt.st")
    private Resource keySettingsForArtifactUserPrompt;

    @Value("classpath:templates/get-generated-artifact-prompt.st")
    private Resource generatedArtifactPrompt;   

    @Override
    public Artifact getGeneratedArtifact(ArtifactRequest artifactRequest, Integer numChoices, Integer numParagraphs) {
        BeanOutputConverter<Artifact> converter = new BeanOutputConverter<>(Artifact.class);
        String listResponse = this.chatClient.prompt()
                .options(ChatOptions.builder()
                .model("gemini-2.0-flash")
                .temperature(1.0)
                //.topP(1.0)
                //.topK(30)
                .maxTokens(2000)
                //.frequencyPenalty(0.1)
                //.presencePenalty(0.1)
                .build())
                .system(s -> s.text(this.keySettingsForArtifactSystemPrompt)
                        .params(Map.of("artefatto", artifactRequest.artifact().type().getArtifactType(),
                                       "genere", artifactRequest.artifact().genre())))
                .user(u -> u.text(this.keySettingsForArtifactUserPrompt)
                        .params(Map.of("numero", numChoices)))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('{')
                        .endDelimiterToken('}')
                        .build())
                .call()
                .content();     
        
        ...
\end{minted}