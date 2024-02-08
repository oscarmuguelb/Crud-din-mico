INSERT INTO `peliculas`.`generos` (`nombre`) VALUES ('Terror');
INSERT INTO `peliculas`.`generos` (`nombre`) VALUES ('Suspenso');
INSERT INTO `peliculas`.`generos` (`nombre`) VALUES ('Ficción');
INSERT INTO `peliculas`.`generos` (`nombre`) VALUES ('Documental');
INSERT INTO `peliculas`.`generos` (`nombre`) VALUES ('Drama');
INSERT INTO `peliculas`.`generos` (`nombre`) VALUES ('Comedia');
INSERT INTO `peliculas`.`generos` (`nombre`) VALUES ('Romance');

INSERT INTO `peliculas`.`peliculas` (`director`, `duracion`, `nombre`, `fk_genero`) VALUES ('James Bond', '130', 'La noche del terror', '1');
INSERT INTO `peliculas`.`peliculas` (`director`, `duracion`, `nombre`, `fk_genero`) VALUES ('Juan Pablo', '120', 'El silencio mortal', '2');
INSERT INTO `peliculas`.`peliculas` (`director`, `duracion`, `nombre`, `fk_genero`) VALUES ('Cristofer Nolan', '200', 'Space', '3');
INSERT INTO `peliculas`.`peliculas` (`director`, `duracion`, `nombre`, `fk_genero`) VALUES ('Jimena Jimenez', '210', 'Romeo y Julieta', '5');
INSERT INTO `peliculas`.`peliculas` (`director`, `duracion`, `nombre`, `fk_genero`) VALUES ('Cristian Hernandez', '210', 'Imaginen cosas Chidas', '4');
