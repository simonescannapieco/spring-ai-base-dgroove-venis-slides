\begin{minted}{java}
    ...

    @Value("classpath:templates/get-json-user-format-definition-prompt.st")
    private Resource JSONUserFormatDefinitionPrompt;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public Answer getJSONUserFormatDefinitionFromClient(DefinitionRequest definitionRequest) {
        String chatResponse = this.chatClient.prompt()
                .user(u -> u.text(this.JSONUserFormatDefinitionPrompt)
                        .params(Map.of("lemma", definitionRequest.lemma())))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('<')
                        .endDelimiterToken('>')
                        .build())
                .call()
                .content();
        System.out.println(chatResponse);
        String responseString;
        try {
            JsonNode jsonNode = objectMapper.readTree(chatResponse.replace("`","").replaceFirst("json",""));
            responseString = jsonNode.get("definizione").asText();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new Answer(responseString);
    }

}
\end{minted}