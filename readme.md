# FeedBacks

## Projet

### Description du projet:

Application web de demande de feedbacks aux collègues.

- Création d'un formulaire avec ajouts de questions
- Partage du formulaire à un ou plusieurs collègues
- Celles-ci peuvent y répondre.

### Équipe

- Maxime VITSE
- Axel LEBAS

### Langage / Framework utilisé

Java SpringBoot

Lombook (Getters/Setters auto généré avec une annotation au début des classes)

### Niveau sur le langage / framework utilisé

- Maxime VITSE:
    - J'ai testé SpringBoot en faisant une API très simple (ADD, GET ALL, GET BY ID) , il y a déjà 2 ans de ça : https://github.com/Weamix/SpringBootAPI 
    - Stack PHP/Symfony au départ mais je suis passé au Java depuis Septembre 2020
- Axel LEBAS:
    - Aucune expérience avec SpringBoot
    - Compétences limitées en Java

## Installation

### Recquis

//TODO : dockeriser pour lancer rapidement l'APP et plus aucun soucis de version de JAVA?
- Java 8 (bien vérifier JAVA_HOME, avec `java --version`)
- Bash/Zsh

### Run

Depuis le terminal :
```
./mvnw spring-boot:run
```

Depuis le plugin maven :
```
mvn spring-boot:run
```

Kill a port if already in used
```
npx kill-port 3000
```

### Links

Swagger : http://localhost:3000/swagger-ui/#/

App : http://localhost:3000/

Postman available in directory : postman/feedbacks.json

### Comment ça marche depuis la branche base:

#### Pour lancer l'app

On run pareil qu'avant (il y a juste un dockerfile où on mettra le jar final pour le rendu à Clément, comme ça il aura juste à faire un docker run pour lancer l'app)

#### Pour lancer la base psql

`docker-compose up` dans le dossier racine de l'app

#### Résumé

- Tu lance la base psql
- Tu start l'app

Il y a sûrement moyen de faire ça en une seule commande, mais osef c'est déjà bien comme ça.