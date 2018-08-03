INSERT INTO `rol` (`id_rol`,`descripcion_rol`) VALUES (1,'Administrador del Sistema');
INSERT INTO `rol` (`id_rol`,`descripcion_rol`) VALUES (2,'Gestor');
INSERT INTO `rol` (`id_rol`,`descripcion_rol`) VALUES (3,'Visitante');

INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`,`orden`) VALUES (1,1,'PRESIDENCIA',5);
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`,`orden`) VALUES (2,1,'VICEPRESIDENCIA 1RA',10);
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`,`orden`) VALUES (3,1,'VICEPRESIDENCIA 2DA',20);
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`,`orden`) VALUES (4,1,'SG MESA DE ENTRADA',30);
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`,`orden`) VALUES (5,1,'SG PROCESO LEGISLATIVO',40);
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`,`orden`) VALUES (6,1,'AUDITORIA INTERNA',50);
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`,`orden`) VALUES (7,1,'DIARIO DE SESIONES',60);
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`,`orden`) VALUES (8,1,'COMUNICACION',70);
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`,`orden`) VALUES (9,1,'CEREMONIAL Y PROTOCOLO',80);
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`,`orden`) VALUES (10,1,'DES. INSTITUCIONAL Y COOP. EXTERNA',90);
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`,`orden`) VALUES (11,1,'TECNOLOGIA DE LA INFORMACION',100);
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`,`orden`) VALUES (12,1,'RELACIONES INTERNACIONALES',110);
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`,`orden`) VALUES (13,1,'RECURSOS HUMANOS',120);
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`,`orden`) VALUES (14,1,'ADMINISTRACION Y FINANZAS',130);
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`,`orden`) VALUES (15,1,'COORDINACION DE COMISIONES',140);
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`,`orden`) VALUES (16,1,'ASESORIA JURIDICA',150);
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`,`orden`) VALUES (17,1,'SEGURIDAD Y CONTROL',160);
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`,`orden`) VALUES (18,1,'DIGITALIZACION LEGISLATIVA (SILpy)',170);
INSERT INTO `dependencia` (`id_dependencia`,`activo`,`descripcion_dependencia`,`orden`) VALUES (19,1,'MESA ENTRADA - HCS',1);

INSERT INTO `usuario` (`id_usuario`,`activo`,`apellidos`,`contrasenha`,`cuenta`,`id_set_preferences`,`login_externo`,`nombres`,`id_dependencia`,`id_rol`) VALUES (1,1,'Ferreira','$2a$12$HTxazgfyb8BMxNQaO9NXweCNAg/eLj0Axxs1Qdyz0bazDI.9C.eBe','jm',NULL,1,'Juan',11,1);
INSERT INTO `usuario` (`id_usuario`,`activo`,`apellidos`,`contrasenha`,`cuenta`,`id_set_preferences`,`login_externo`,`nombres`,`id_dependencia`,`id_rol`) VALUES (2,1,'Gestor','$2a$12$P2qlCklJ/A.lxWMBXwVBsOc8Jh81xDMHJPMmsAFIqjPtgSLyKTIg6','gestor',NULL,1,'Usuario',4,2);
INSERT INTO `usuario` (`id_usuario`,`activo`,`apellidos`,`contrasenha`,`cuenta`,`id_set_preferences`,`login_externo`,`nombres`,`id_dependencia`,`id_rol`) VALUES (3,1,'Bresanobich','$2a$12$2pgoIdUuPQJ9YHSRDeVU0uoqiN02kdV1oAZ04mgAWTGP6Ph5a533a','vbresanovich',NULL,0,'Victor',19,2);
INSERT INTO `usuario` (`id_usuario`,`activo`,`apellidos`,`contrasenha`,`cuenta`,`id_set_preferences`,`login_externo`,`nombres`,`id_dependencia`,`id_rol`) VALUES (4,1,'Sánchez','$2a$12$pJioizVBBOlb71yhLw8lvulBibKS4evJ1fP8dVBW8/RJbOntp/NrC','dsanchez',NULL,0,'Denisse',1,2);
INSERT INTO `usuario` (`id_usuario`,`activo`,`apellidos`,`contrasenha`,`cuenta`,`id_set_preferences`,`login_externo`,`nombres`,`id_dependencia`,`id_rol`) VALUES (5,1,'Fretes','$2a$12$MadnyMTMPZBvGpQ3gsMBa.4uKNP.BKTfKFoMSHi5661RAp6j2CXqu','lfretes',NULL,0,'Leonardo',4,2);
INSERT INTO `usuario` (`id_usuario`,`activo`,`apellidos`,`contrasenha`,`cuenta`,`id_set_preferences`,`login_externo`,`nombres`,`id_dependencia`,`id_rol`) VALUES (6,1,'Rosso','$2a$12$oIKtH/XLsZDum9HprLfNAOI270mWSTwDpbn4ubMlRP1L2usYXG5Jm','crosso',NULL,0,'Carlos',5,2);
INSERT INTO `tipo` (`id_tipo`,`descripcion_tipo`,`orden`) VALUES (1,'TIPO EXPEDIENTE',NULL);
INSERT INTO `tipo` (`id_tipo`,`descripcion_tipo`,`orden`) VALUES (2,'TIPO ESTADO EXPEDIENTE',NULL);
INSERT INTO `tipo` (`id_tipo`,`descripcion_tipo`,`orden`) VALUES (3,'TIPO ESTADO TRAMITE',NULL);


INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (1,'NOTA',NULL,1);
INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (2,'SOLICITUD',NULL,1);
INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (3,'MEMO',NULL,1);
INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (4,'GENERICO',NULL,1);
INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (5,'PROYECTO DE LEY',NULL,1);

INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (5,'EN TRAMITE',NULL,2);
INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (6,'PENDIENTE',NULL,3);
INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (7,'RECIBIDO',NULL,3);
INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (8,'DEVUELTO',NULL,3);
INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (9,'DERIVADO',NULL,3);
INSERT INTO `sub_tipo` (`id_sub_tipo`,`descripcion_sub_tipo`,`orden`,`id_tipo`) VALUES (10,'ARCHIVADO',NULL,3);

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