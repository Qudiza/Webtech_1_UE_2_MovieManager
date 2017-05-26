USE MovieDB

-- Film
create Table movie(
movieId int NOT NULL Primary Key identity(1,1),
title nvarchar(50),
releaseDate int,
)

-- Genre
create Table genre(
genreId int NOT NULL Primary Key identity(1,1),
genre nvarchar(30)
)

-- n:m zwischen movie und genre
create Table moge(
movieId int NOT NULL foreign Key REFERENCES movie(movieId),
genreId int NOT NULL foreign Key REFERENCES genre(genreId),
PRIMARY KEY (movieId, genreId)
)

-- schauspieler
create Table actor(
actorId int NOT NULL Primary Key identity(1,1),
actorName nvarchar(50),
gender char
)

-- n:m zwischen movie und actor, mit charactername den der Schauspieler in dem Film hat
create Table movieCharacter(
characterName nvarchar(30),
actorId int NOT NULL foreign Key REFERENCES actor(actorId),
movieId int NOT NULL foreign Key REFERENCES movie(movieId),
PRIMARY KEY (movieId, actorId)
)

-- Regisseur
create Table regisseur(
regisseurId int NOT NULL Primary Key identity(1,1),
regisseurName nvarchar(30)
)

-- n:m zwischen regisseur und movie
create Table movieRegisseur(
regisseurId int NOT NULL foreign Key REFERENCES regisseur(regisseurId),
movieId int NOT NULL foreign Key REFERENCES movie(movieId),
PRIMARY KEY (movieId, regisseurId)
)

-- Benutzertabelle des Systems
create Table movieUser(
userId int NOT NULL Primary Key identity(1,1),
userName nvarchar(25) NOT NULL,
userPassword nvarchar(40) NOT NULL
sessionId nvarchar(255)
)

-- n:m zwischen movieUser und movie Filmesammlung
create Table movieCollection(
collectionId int NOT NULL Primary Key identity(1,1),
userId int NOT NULL foreign Key REFERENCES movieUser(userId),
movieId int NOT NULL foreign Key REFERENCES movie(movieId)
)