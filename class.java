
public class Rv {
    // region Update
    // region Update
    // Q1. Quelle est la différence entre un système parallèle et un système distribué ?
    // Réponse : Un système parallèle exécute plusieurs tâches simultanément. Un système distribué utilise plusieurs ordinateurs connectés par un réseau.
    // endregion

    // region Update
    // Q2. C’est quoi un sémaphore ?
    // Réponse : C’est un mécanisme de synchronisation qui contrôle l’accès des threads aux ressources partagées.
    // endregion

    // region Update
    // Q3. Quels sont les types de sémaphores ?
    // Réponse : Deux types : sémaphore binaire (0 ou 1) et sémaphore compteur (plusieurs accès possibles).
    // endregion

    // region Update
    // Q4. Quelle est la différence entre sleep() et wait() ?
    // Réponse : sleep() met le thread en pause sans libérer le verrou. wait() met le thread en attente et libère le verrou de l'objet.
    // endregion

    // region Update
    // Q5. Quels sont les types de tubes de communication ?
    // Réponse : Deux types : tubes anonymes (Pipe) et tubes nommés (FIFO).
    // endregion

    // region Update
    // Q6. Pourquoi utiliser join() ?
    // Réponse : Pour attendre la fin de l'exécution d'un thread avant de continuer.
    // endregion

    // region Update
    // Q7. Pourquoi utiliser wait() ?
    // Réponse : Pour mettre un thread en attente jusqu'à une notification avec notify() ou notifyAll().
    // endregion

    // region Update
    // Q8. Comment déclarer une section critique ?
    // Réponse : En Java, on utilise le mot-clé synchronized.
    //
    // synchronized (verrou) {
    //     // Section critique
    // }
    // endregion

    // region Update
    // Q9. Quels sont les états d’un thread ?
    // Réponse : NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING et TERMINATED.
    // endregion

    // region Update
    // Q10. Quelles sont les architectures d’un système parallèle ?
    // Réponse : Mémoire partagée, mémoire distribuée et architecture hybride.
    //
    // Selon la classification de Flynn : SIMD, MIMD et MISD (rare). SISD représente une architecture séquentielle.
    // endregion

    // region Update
    // Q11. Comment déclarer un thread en Java ?
    // Réponse : En héritant de la classe Thread ou en implémentant l'interface Runnable, puis en utilisant start().
    // endregion

    // region Update
    // Q12. Quelles sont les méthodes de l’interface Runnable ?
    // Réponse : Une seule méthode abstraite : run(), qui contient le code exécuté par le thread.
    // endregion

    // region Update
    // Q13. C’est quoi un mutex ?
    // Réponse : C’est un verrou qui permet à un seul thread à la fois d'accéder à une ressource partagée.
    // endregion
    // endregion

}
