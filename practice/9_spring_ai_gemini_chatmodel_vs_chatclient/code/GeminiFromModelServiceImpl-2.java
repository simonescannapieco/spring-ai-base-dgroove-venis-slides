\begin{minted}{java}
    ...
    @Override
    public Answer getAnswerFromModel(Question question) {
        return new Answer(getAnswerFromModel(question.question()));
    }

    @Value("classpath:templates/get-definition-prompt.st")
    private Resource definitionPrompt;

    @Override
    public Answer getDefinitionFromModel(DefinitionRequest definitionRequest) {
        PromptTemplate promptTemplate = new PromptTemplate(this.definitionPrompt);
        Prompt prompt = promptTemplate.create(Map.of("lemma", definitionRequest.lemma()));
        ChatResponse response = this.chatModel.call(prompt);
        return new Answer(response.getResult().getOutput().getText());
    }
\end{minted}