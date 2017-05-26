use MovieDB
GO

insert into actor values('Leonardo DiCaprio', 'M')
insert into actor values('Brad Pitt', 'M')
insert into actor values('Morgan Freeman', 'M')
insert into actor values('Samuel Jackson', 'M')
insert into actor values('Angelina Jolie', 'F')

insert into genre values ('Action')
insert into genre values ('Comedy')
insert into genre values ('Horror')
insert into genre values ('Thriller')
insert into genre values ('Adventure')
insert into genre values ('Fantasy')

insert into movie values('Mr. & Mrs. Smith', 2005)
insert into movie values('Inception', 2010)
insert into movie values('Die Verurteilten', 1994)
insert into movie values('Pulp Fiction', 1994)
insert into movie values('Interstellar', 2014)
insert into movie values('Per Anhalter durch die Galaxis', 2005)

insert into moge values(1, 1)
insert into moge values(1, 2)
insert into moge values(2, 4)
insert into moge values(2, 6)
insert into moge values(3, 4)
insert into moge values(4, 4)

insert into movieCharacter values('John Smith', 2, 1)
insert into movieCharacter values('Dominick Cobb', 1, 2)
insert into movieCharacter values('Red', 3, 3)
insert into movieCharacter values('Jules Winnfield', 4, 4)
insert into movieCharacter values('Jane Smith', 5, 1)

insert into regisseur values('Quentin Tarantino')
insert into regisseur values('Christopher Nolan')
insert into regisseur values('Frank Darabont')
insert into regisseur values('Doug Liman')

insert into movieRegisseur values(1, 4)
insert into movieRegisseur values(2, 2)
insert into movieRegisseur values(3, 3)
insert into movieRegisseur values(4, 4)

insert into movieUser values('Ben', '123')

insert into movieCollection values(1, 2)
insert into movieCollection values(1, 4)