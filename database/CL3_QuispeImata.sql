DROP DATABASE IF EXISTS CL3_QuispeImata;
CREATE DATABASE CL3_QuispeImata;
USE CL3_QuispeImata;

CREATE TABLE Producto(
	ide_pro int auto_increment primary key,
    nom_pro varchar(255) not null,
    des_pro varchar(255) not null,
    fec_pro datetime not null
);

INSERT INTO Producto VALUES (null, "ACEITE PRIMOR 1 LITRO", "El mejor aceite del Perú o eso el lo que dicen xd", now());
INSERT INTO Producto VALUES (null, "YOGURT GLORIA 2 LITROS", "Este es un yougurt grandioso", now());
INSERT INTO Producto VALUES (null, "CELULAR GAMING ASUS", "Mejores celulares de la marca Asus", now());

SELECT * FROM Producto;
