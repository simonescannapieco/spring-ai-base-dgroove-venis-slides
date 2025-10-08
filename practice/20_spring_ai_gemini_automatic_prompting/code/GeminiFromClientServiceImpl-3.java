\begin{minted}{java}
   ...
        
        String orderedListResponse = this.chatClient.prompt()
                .options(ChatOptions.builder()
                .model("gemini-2.0-flash")
                .temperature(0.0)
                //.topP(1.0)
                //.topK(30)
                .maxTokens(2000)
                //.frequencyPenalty(0.1)
                //.presencePenalty(0.1)
                .build())
                .user(u -> u.text(this.orderedPromptAlternativesPrompt)
                        .params(Map.of("metrica", promptEvaluationRequest.metric().getMetric(), 
                                       "lista", listResponse, 
                                       "formato", converter.getFormat())))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('{')
                        .endDelimiterToken('}')
                        .build())
                .call()
                .content();

        return converter.convert(orderedListResponse);

    }

}
\end{minted}