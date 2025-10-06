\begin{minted}{java}
    ...      
        String generatedArtifact = this.chatClient.prompt()
                .options(ChatOptions.builder()
                .model("gemini-2.0-flash")
                .temperature(1.0)
                //.topP(1.0)
                //.topK(30)
                .maxTokens(1024)
                //.frequencyPenalty(0.1)
                //.presencePenalty(0.1)
                .build())
                .user(u -> u.text(this.generatedArtifactPrompt)
                        .params(Map.of("lista", listResponse,
                                "numero", numParagraphs,
                                "artefatto", artifactRequest.artifact().type(),
                                "genere", artifactRequest.artifact().genre(),
                                "formato", converter.getFormat())))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('{')
                        .endDelimiterToken('}')
                        .build())
                .call()
                .content();
        
                return converter.convert(generatedArtifact);
    }

}
\end{minted}