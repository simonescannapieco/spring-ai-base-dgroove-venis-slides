\begin{minted}{java}
    ...
    @Override
    public Answer getAnswerFromClient(Question question) {
        return new Answer(getAnswerFromClient(question.question()));
    }

    @Value("classpath:templates/get-definition-prompt.st")
    private Resource definitionPrompt;

    @Override
    public Answer getDefinitionFromClient(DefinitionRequest definitionRequest) {
        return new Answer(this.chatClient.prompt()
                .user(u -> u.text(this.definitionPrompt)
                        .params(Map.of("lemma", definitionRequest.lemma())))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('{')
                        .endDelimiterToken('}')
                        .build())
                .call()
                .content());
    }

}
\end{minted}