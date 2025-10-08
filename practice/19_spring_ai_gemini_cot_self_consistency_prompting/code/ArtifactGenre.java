\begin{minted}{java}
package it.venis.ai.spring.demo.data;

public enum ArtifactGenre {
    
    STORIA("Storico"),
    FANTASCIENZA("Fantascienza"),
    FANTASY("Fantasy"),
    AVVENTURA("Avventura"),
    ...

    private String artifactGenre;

    ArtifactGenre(String artifactGenre) {
        this.artifactGenre = artifactGenre;
    }

    public String getArtifactGenre() {
        return artifactGenre;
    }

}
\end{minted}