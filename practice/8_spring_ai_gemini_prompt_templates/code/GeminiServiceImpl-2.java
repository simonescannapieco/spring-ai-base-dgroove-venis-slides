\begin{minted}{java}
    ...
    @Override
    public Answer getAnswer(Question question) {
        return new Answer(getAnswer(question.question()));
    }
    @Value("classpath:templates/get-definition-prompt.st")
    private Resource definitionPrompt;

    @Override
    public Answer getDefinition(DefinitionRequest definitionRequest) {    
        PromptTemplate promptTemplate = new PromptTemplate(definitionPrompt);
        Prompt prompt = promptTemplate.create(Map.of("lemma", definitionRequest.lemma()));
        ChatResponse response = chatModel.call(prompt);
        return new Answer(response.getResult().getOutput().getText());
    }
}
\end{minted}