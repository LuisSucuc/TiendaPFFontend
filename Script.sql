CREATE TABLE PRODUCTO 
(
  ID 			NUMBER  		NOT NULL PRIMARY KEY,
  NOMBRE 		VARCHAR2(100) 	NOT NULL,
  CANTIDAD 		INT 			NOT NULL,
  PRECIO 		NUMBER(9, 2) 	NOT NULL,
  PRECIO_VENTA 	NUMBER(9, 2) 	NOT NULL,
  ACTIVO 		NUMBER(1) 		DEFAULT 1 NOT NULL 
);

CREATE SEQUENCE SEQ_PRODUCTO;


INSERT INTO PRODUCTO(ID,NOMBRE,CANTIDAD,PRECIO, PRECIO_VENTA) VALUES (SEQ_PRODUCTO.NEXTVAL, 'Doritos barbacoa','4','3.5','9');



CREATE TABLE CLIENTE 
(
  ID 			INT 	        NOT NULL PRIMARY KEY,
  COMERCIO 		VARCHAR2(100) 	NOT NULL, 
  TELEFONO 		NUMBER(8) 		NOT NULL, 
  DIRECCION 	VARCHAR2(150) 	NOT NULL, 
  ACTIVO 		NUMBER(1) 		DEFAULT 1 NOT NULL
);


CREATE SEQUENCE SEQ_CLIENTE;


INSERT INTO CLIENTE(ID,COMERCIO,TELEFONO,DIRECCION) VALUES (SEQ_PRODUCTO.NEXTVAL, 'Juan Perez','78947878','3ra. Avenida B 3-89 Zona 3');



CREATE TABLE ORDEN 
(
  ID                        INT      NOT NULL PRIMARY KEY,
  CLIENTE_ID                INT             NOT NULL,
  FECHA                     DATE            NOT NULL,
  DESCRIPCION               VARCHAR2(400)   NOT NULL,
  ACTIVO                    NUMBER(1)     DEFAULT 1 NOT NULL,
  CONSTRAINT FK_CLIENTE     FOREIGN KEY (CLIENTE_ID) REFERENCES CLIENTE(ID)
);


CREATE SEQUENCE SEQ_ORDEN;


INSERT INTO ORDEN (ID, CLIENTE_ID, FECHA, DESCRIPCION) VALUES (SEQ_ORDENES.NEXTVAL, 1, TO_DATE('2018-11-15 08:09:03', 'YYYY-MM-DD HH24:MI:SS'), 'Descripcion')




CREATE TABLE PRODUCTO_ORDEN (
  ID                        INT             NOT NULL PRIMARY KEY,
  ORDEN_ID                  INT             NOT NULL,
  PRODUCTO_ID               INT             NOT NULL,
  CANTIDAD                  INT             NOT NULL,
  FECHA                     DATE            DEFAULT SYSDATE NOT NULL,
  CONSTRAINT FK_ORDEN_PRODUCTO_ORDEN      FOREIGN KEY (ORDEN_ID) REFERENCES ORDEN(ID),
  CONSTRAINT PRODUCTO_PRODUCTO_ORDEN        FOREIGN KEY (PRODUCTO_ID) REFERENCES PRODUCTO(ID)
);


CREATE SEQUENCE SEQ_PRODUCTO_ORDEN;


INSERT INTO PRODUCTO_ORDEN (ID, ORDEN_ID, PRODUCTO_ID, CANTIDAD) VALUES (SEQ_PRODUCTO_ORDEN.NEXTVAL, 8, 55, 10)