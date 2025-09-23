\begin{minted}{java}
package it.venis.ai.spring.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
...
import it.venis.ai.spring.demo.services.GeminiFromClientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class QuestionFromClientController {

    private final GeminiFromClientService geminiService;

    public QuestionFromClientController(GeminiFromClientService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/client/ask")
    public Answer askQuestionFromClient(@RequestBody Question question) {
        return this.geminiService.getAnswerFromClient(question);
    }

    @PostMapping("/client/definition")
    public Answer getDefinitionFromClient(@RequestBody DefinitionRequest definitionRequest) {
        return this.geminiService.getDefinitionFromClient(definitionRequest);
    }

    @PostMapping("/client/definition/custom")
    public Answer getCustomFormatDefinition(@RequestBody DefinitionRequest definitionRequest) {
        return this.geminiService.getCustomFormatDefinitionFromClient(definitionRequest);
    }

}
\end{minted}