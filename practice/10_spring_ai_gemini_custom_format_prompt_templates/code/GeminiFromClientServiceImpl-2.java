\begin{minted}{java}
    ...
    @Override
    public Answer getAnswerFromClient(Question question) {
        return new Answer(getAnswerFromClient(question.question()));
    }

    @Value("classpath:templates/get-definition-prompt.st")
    private Resource definitionPrompt;

    ...

    @Value("classpath:templates/get-custom-format-definition-prompt.st")
    private Resource customFormatDefinitionPrompt;

    @Override
    public Answer getCustomFormatDefinitionFromClient(DefinitionRequest definitionRequest) {
        return new Answer(this.chatClient.prompt()
                .user(u -> u.text(this.customFormatDefinitionPrompt)
                        .params(Map.of("lemma", definitionRequest.lemma())))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('<')
                        .endDelimiterToken('>')
                        .build())
                .call()
                .content());
    }
    
}
\end{minted}