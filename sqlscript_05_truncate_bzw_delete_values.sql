USE [MovieDB]
GO

-- Truncate endspricht delete ohne where-Klausel
TRUNCATE TABLE movieCollection;
TRUNCATE TABLE movieCharacter;
TRUNCATE TABLE moge;
TRUNCATE TABLE movieRegisseur;

-- DELETE wegen der Fremdschlüssel
DELETE FROM [dbo].[movie];
DELETE FROM [dbo].[actor];
DELETE FROM [dbo].[regisseur];
DELETE FROM [dbo].[genre];
DELETE FROM [dbo].[movieUser];