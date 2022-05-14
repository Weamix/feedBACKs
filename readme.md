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

- Java 17
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

### Fonctionnement depuis la branche base:

#### Pour lancer le projet

Depuis le dossier racine de l'app :
```
docker build -t feedbacks.jar .
docker-compose up
```