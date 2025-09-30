\begin{minted}{java}
    ...

    @Value("classpath:templates/get-json-output-converter-format-definition-prompt.st")
    private Resource JSONOutputCOnverterFormatDefinitionPrompt;

    @Override
    public DefinitionResponse getJSONOutputConverterFormatDefinitionFromClient(DefinitionRequest definitionRequest) {
        
        BeanOutputConverter<DefinitionResponse> converter = new BeanOutputConverter<>(DefinitionResponse.class);

        String format = converter.getFormat();

        System.out.println(format);
        
        String chatResponse = this.chatClient.prompt()
                .user(u -> u.text(this.JSONOutputCOnverterFormatDefinitionPrompt)
                        .params(Map.of("lemma", definitionRequest.lemma(),
                                       "agente", definitionRequest.agent(),
                                       "formato", format)))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('{')
                        .endDelimiterToken('}')
                        .build())
                .call()
                .content();

        return converter.convert(Objects.requireNonNull(chatResponse));

    }

}
\end{minted}