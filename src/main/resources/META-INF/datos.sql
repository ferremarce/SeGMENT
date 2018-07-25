INSERT INTO `rol` (`id_rol`,`descripcion_rol`) VALUES (1,'Administrador del Sistema');
INSERT INTO `rol` (`id_rol`,`descripcion_rol`) VALUES (2,'Gestor');
INSERT INTO `rol` (`id_rol`,`descripcion_rol`) VALUES (3,'Visitante');

INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`) VALUES (1,1,'PRESIDENCIA');
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`) VALUES (2,1,'VICEPRESIDENCIA 1RA');
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`) VALUES (3,1,'VICEPRESIDENCIA 2DA');
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`) VALUES (4,1,'SECRETARIA GENERAL');
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`) VALUES (5,1,'SECRETARIA ADMINISTRATIVA');
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`) VALUES (6,1,'DG AUDITORIA INTERNA');
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`) VALUES (7,1,'DG DIARIO DE SESIONES');
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`) VALUES (8,1,'DG COMUNICACION');
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`) VALUES (9,1,'DG CEREMONIAL Y PROTOCOLO');
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`) VALUES (10,1,'DG DES. INSTITUCIONAL Y COOP. EXTERNA');
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`) VALUES (11,1,'DG TECNOLOGIA DE LA INFORMACION');
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`) VALUES (12,1,'DG RELACIONES INTERNACIONALES');
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`) VALUES (13,1,'DG RECURSOS HUMANOS');
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`) VALUES (14,1,'DG ADMINISTRACION Y FINANZAS');
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`) VALUES (15,1,'DG COORDINACION DE COMISIONES');
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`) VALUES (16,1,'DG ASESORIA JURIDICA');
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`) VALUES (17,1,'DG SEGURIDAD Y CONTROL');
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`) VALUES (18,1,'DG DIGITALIZACION LEGISLATIVA (SILpy)');

INSERT INTO `usuario` (`id_usuario`,`activo`,`apellidos`,`contrasenha`,`cuenta`,`id_set_preferences`,`login_externo`,`nombres`,`id_rol`,`id_dependencia`) VALUES (1,1,'Ferreira','$2a$12$P2qlCklJ/A.lxWMBXwVBsOc8Jh81xDMHJPMmsAFIqjPtgSLyKTIg6','jm',NULL,1,'Juan',1,1);
INSERT INTO `usuario` (`id_usuario`,`activo`,`apellidos`,`contrasenha`,`cuenta`,`id_set_preferences`,`login_externo`,`nombres`,`id_rol`,`id_dependencia`) VALUES (2,1,'Gestor','$2a$12$P2qlCklJ/A.lxWMBXwVBsOc8Jh81xDMHJPMmsAFIqjPtgSLyKTIg6','gestor',NULL,1,'Usuario',2,1); 

INSERT INTO `tipo` (`id_tipo`,`descripcion_tipo`,`orden`) VALUES (1,'TIPO EXPEDIENTE',NULL);
INSERT INTO `tipo` (`id_tipo`,`descripcion_tipo`,`orden`) VALUES (2,'TIPO ESTADO EXPEDIENTE',NULL);
INSERT INTO `tipo` (`id_tipo`,`descripcion_tipo`,`orden`) VALUES (3,'TIPO ESTADO TRAMITE',NULL);


INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (1,'NOTA',NULL,1);
INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (2,'SOLICITUD',NULL,1);
INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (3,'MEMO',NULL,1);
INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (4,'GENERICO',NULL,1);

INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (5,'EN TRAMITE',NULL,2);
INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (6,'PENDIENTE',NULL,3);
INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (7,'CONFIRMADO',NULL,3);
INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (8,'DEVUELTO',NULL,3);

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