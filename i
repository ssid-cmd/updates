
--Exercice 1
SET SERVEROUTPUT ON;
DECLARE
    -- 1. Déclaration du curseur paramétré

    CURSOR c_presc (p_idcons NUMBER) IS 
        SELECT NomMedicament, Dosage 
        FROM PRESCRIPTIONS 
        WHERE IdCons = p_idcons;

    v_nom PRESCRIPTIONS.NomMedicament%TYPE;
    v_dosage PRESCRIPTIONS.Dosage%TYPE;
    v_trouve BOOLEAN := FALSE;
    
BEGIN
    OPEN c_presc(50);
    
    LOOP
        FETCH c_presc INTO v_nom, v_dosage;
        EXIT WHEN c_presc%NOTFOUND;
        
        DBMS_OUTPUT.PUT_LINE('Médicament : ' || v_nom || ' - Dosage : ' || v_dosage);
        v_trouve := TRUE;
    END LOOP;
    
    CLOSE c_presc;
    
    IF NOT v_trouve THEN
        DBMS_OUTPUT.PUT_LINE('Aucune prescription trouvée pour la consultation 50.');
    END IF;
END;
/

-- Exercice 2 
set SERVEROUTPUT ON
DECLARE
TYPE T_MED_EN_ATTENTE IS TABLE OF VARCHAR2(20);
tab_meds T_MED_EN_ATTENTE :=T_MED_EN_ATTENTE();
CURSOR c_med is
    select m.Nom
    FROM MEDECINS m
    JOIN CONSULTATIONS c ON m.IdMed = c.IdMed
    WHERE c.Statuts = 'EN_ATTENTE'
    GROUP BY m.Nom
    HAVING COUNT(c.IdCons) > 10;
BEGIN
FOR ligne IN c_med LOOP
    tab_meds.extends;
    tab_meds(tab_meds.COUNT):=ligne.Nom;
END LOOP;
DBMS_OUTPUT.PUT_LINE('Nombre de médecins concernés : '||tab_meds.COUNT);
IF tab_meds.COUNT > 0 THEN
    DBMS_OUTPUT.PUT_LINE('Liste des médecins ayont consultés plus que 10 patients');
    FOR i IN 1..tab_meds.COUNT LOOP
        DBMS_OUTPUT.PUT_LINE(tab_meds(i));
    END LOOP;
END IF;
END;
/

--Exercice 3


SET SERVEROUTPUT ON;

DECLARE

    CURSOR c_cons IS
        SELECT IdCons
        FROM CONSULTATIONS
        WHERE Statut = 'EN_ATTENTE'
        AND DateCons < SYSDATE
        FOR UPDATE; 
        
BEGIN

    FOR ligne IN c_cons LOOP
        
        UPDATE CONSULTATIONS
        SET Statut = 'PAYEE'
        WHERE CURRENT OF c_cons;
        
        DBMS_OUTPUT.PUT_LINE('Consultation clôturée et facturée : N° ' || ligne.IdCons);
        
    END LOOP;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Toutes les mises à jour ont été validées (COMMIT).');
END;
/
-------------------------------------------------------------------------------
-----------------------------------------------------------------------------
Exercice 1

1. CURSOR c_emp IS SELECT nom, salaire FROM employes;
Correction : CURSOR c_emp IS SELECT nom FROM employes;

2. Après FETCH c_emp INTO v_nom;
Ajouter : EXIT WHEN c_emp%NOTFOUND;

3. Après END LOOP;
Ajouter : CLOSE c_emp;

Exercice 2

COUNT = 4
LAST = 5

EXTEND(2, 1) ajoute deux copies du premier élément (10).
DELETE(2) supprime le deuxième élément sans modifier les autres indices.

Exercice 3

DECLARE
    CURSOR c_ville(p_ville VARCHAR2) IS
        SELECT nom, salaire FROM employes
        WHERE ville = p_ville;
    v_nom employes.nom%TYPE;
    v_salaire employes.salaire%TYPE;
BEGIN
    OPEN c_ville('Casablanca');
    LOOP
        FETCH c_ville INTO v_nom, v_salaire;
        EXIT WHEN c_ville%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(v_nom || ' : ' || v_salaire);
    END LOOP;
    CLOSE c_ville;
END;

Exercice 4

DECLARE
    TYPE t_notes IS TABLE OF NUMBER INDEX BY VARCHAR2(50);
    annuaire_notes t_notes;
    v_nom VARCHAR2(50);
BEGIN
    annuaire_notes('Ali') := 15;
    annuaire_notes('Sara') := 18;
    annuaire_notes('Youssef') := 12;
    v_nom := annuaire_notes.FIRST;
    WHILE v_nom IS NOT NULL LOOP
        DBMS_OUTPUT.PUT_LINE('L''étudiant ' || v_nom || ' a eu ' || annuaire_notes(v_nom) || '.');
        v_nom := annuaire_notes.NEXT(v_nom);
    END LOOP;
END;
=====================================================================================

/*
PARTIE 1 : QCM

1. Quelle est la bonne syntaxe pour déclarer une variable qui copie le type d'une colonne de la table EMPLOYES ?
A) v_salaire EMPLOYES.SALAIRE%TYPE;

2. Dans un curseur explicite, quelle commande permet de récupérer la ligne courante dans des variables ?
B) FETCH

3. Vous avez une Nested Table contenant 5 éléments. Vous exécutez tab.DELETE(3). Quelle affirmation est vraie ?
B) La taille (COUNT) devient 4, mais l'indice 3 n'existe plus (il y a un trou).

4. Quelle méthode de collection renvoie le nombre d'éléments maximum qu'un VARRAY peut contenir ?
C) LIMIT

5. À quoi sert l'instruction RAISE dans un bloc PL/SQL ?
C) À lever (déclencher) manuellement une exception.

6. Dans une table associative (Index-By), comment accéder à la valeur associée à la clé "Ahmed" ?
C) mon_annuaire('Ahmed')

7. Que fait la clause WHERE CURRENT OF nom_curseur ?
B) Elle permet de faire un UPDATE ou DELETE sur la ligne où se trouve actuellement le curseur.

8. Quel est le rôle de la section EXCEPTION dans un bloc PL/SQL ?
B) Intercepter et gérer les erreurs survenues dans le bloc BEGIN.

9. Dans un Package PL/SQL, quelle partie contient le code réel des procédures et des fonctions ?
B) Le Corps (Body)

10. Quelle est la particularité d'un REF CURSOR ?
C) Il peut être associé à différentes requêtes SQL au moment de son ouverture (OPEN ... FOR).

PARTIE 2 : Questions de cours

Question 1: Les Curseurs Implicites vs Explicites
Expliquez la différence fondamentale entre un curseur implicite et un curseur explicite. Dans quels cas le moteur Oracle crée-t-il automatiquement un curseur implicite ?
Un curseur implicite est créé et géré automatiquement par Oracle lors des instructions DML et SELECT INTO. Un curseur explicite est déclaré par le programmeur pour parcourir les résultats d'une requête.

Question 2 : Le verrouillage (FOR UPDATE)
Pourquoi est-il risqué de faire un UPDATE sur une table à l'intérieur d'une boucle qui parcourt un curseur sans utiliser la clause FOR UPDATE ? Que se passe-t-il si un autre utilisateur modifie la même ligne en même temps ?
Sans FOR UPDATE, les lignes ne sont pas verrouillées lors de leur lecture. Un autre utilisateur peut les modifier entre la lecture et la mise à jour. L'UPDATE peut attendre la libération du verrou et risque d'écraser une modification concurrente si le calcul repose sur les anciennes valeurs.

Question 3: Les Exceptions Personnalisées
Décrivez la procédure complète pour créer, lever et intercepter une exception définie par l'utilisateur (appelons-la exc_stock_vide).
On déclare l'exception avec exc_stock_vide EXCEPTION; dans DECLARE, on la déclenche avec RAISE exc_stock_vide; dans BEGIN, puis on la traite dans EXCEPTION avec WHEN exc_stock_vide THEN suivi des instructions de traitement.

