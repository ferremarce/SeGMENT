INSERT INTO usuario (id_usuario,apellidos,contrasenha,cuenta,activo,id_set_preferences,login_externo,nombres,id_rol) VALUES (1,'Ferreira','$2a$12$P2qlCklJ/A.lxWMBXwVBsOc8Jh81xDMHJPMmsAFIqjPtgSLyKTIg6','jm',1,NULL,1,'Juan',NULL);

INSERT INTO `tipo` (`id_tipo`,`descripcion_tipo`,`orden`) VALUES (1,'TIPO EXPEDIENTE',NULL);
INSERT INTO `tipo` (`id_tipo`,`descripcion_tipo`,`orden`) VALUES (2,'TIPO ESTADO EXPEDIENTE',NULL);


INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (1,'NOTA',NULL,1);
INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (2,'SOLICITUD',NULL,1);
INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (3,'MEMO',NULL,1);
INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (4,'GENERICO',NULL,1);