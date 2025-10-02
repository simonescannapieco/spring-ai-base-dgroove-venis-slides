\begin{minted}{java}
package it.venis.ai.spring.demo.model;

import java.util.UUID;

public record ArtifactRequest(UUID id, Artifact artifact) {

    public ArtifactRequest(String title, Artifact artifact) {
        this(UUID.randomUUID(), artifact);
    }

}
\end{minted}