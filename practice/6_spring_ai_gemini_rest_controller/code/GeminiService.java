\begin{minted}{java}
package it.venis.ai.spring.demo.services;

import it.venis.ai.spring.demo.model.Answer;
import it.venis.ai.spring.demo.model.Question;

public interface GeminiService {
    
    String getAnswer(String question);

    Answer getAnswer(Question question);

}
\end{minted}