INSERT INTO usuario (id_usuario,apellidos,contrasenha,cuenta,activo,id_set_preferences,login_externo,nombres,id_rol) VALUES (1,'Ferreira','$2a$12$P2qlCklJ/A.lxWMBXwVBsOc8Jh81xDMHJPMmsAFIqjPtgSLyKTIg6','jm',1,NULL,1,'Juan',NULL);

INSERT INTO `tipo` (`id_tipo`,`descripcion_tipo`,`orden`) VALUES (1,'TIPO EXPEDIENTE',NULL);
INSERT INTO `tipo` (`id_tipo`,`descripcion_tipo`,`orden`) VALUES (2,'TIPO ESTADO EXPEDIENTE',NULL);


INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (1,'NOTA',NULL,1);
INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (2,'SOLICITUD',NULL,1);
INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (3,'MEMO',NULL,1);
INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (4,'GENERICO',NULL,1);

INSERT INTO `clasificador` (`id_clasificador`,`descripcion`,`mapeo_numerico`,`id_padre`) VALUES (1,'Computadoras',NULL,NULL);
INSERT INTO `clasificador` (`id_clasificador`,`descripcion`,`mapeo_numerico`,`id_padre`) VALUES (2,'Impresoras',NULL,NULL);
INSERT INTO `clasificador` (`id_clasificador`,`descripcion`,`mapeo_numerico`,`id_padre`) VALUES (3,'Notebooks',NULL,1);
INSERT INTO `clasificador` (`id_clasificador`,`descripcion`,`mapeo_numerico`,`id_padre`) VALUES (4,'Netbooks',NULL,1);
INSERT INTO `clasificador` (`id_clasificador`,`descripcion`,`mapeo_numerico`,`id_padre`) VALUES (5,'PC',NULL,1);
INSERT INTO `clasificador` (`id_clasificador`,`descripcion`,`mapeo_numerico`,`id_padre`) VALUES (6,'Laser',NULL,2);
INSERT INTO `clasificador` (`id_clasificador`,`descripcion`,`mapeo_numerico`,`id_padre`) VALUES (7,'Matricial',NULL,2);
INSERT INTO `clasificador` (`id_clasificador`,`descripcion`,`mapeo_numerico`,`id_padre`) VALUES (8,'INTEL',NULL,3);
INSERT INTO `clasificador` (`id_clasificador`,`descripcion`,`mapeo_numerico`,`id_padre`) VALUES (9,'AMD',NULL,3);
INSERT INTO `clasificador` (`id_clasificador`,`descripcion`,`mapeo_numerico`,`id_padre`) VALUES (10,'CORE I3',NULL,8);
INSERT INTO `clasificador` (`id_clasificador`,`descripcion`,`mapeo_numerico`,`id_padre`) VALUES (11,'CORE I5',NULL,8);
INSERT INTO `clasificador` (`id_clasificador`,`descripcion`,`mapeo_numerico`,`id_padre`) VALUES (12,'ATHLON X4',NULL,8);