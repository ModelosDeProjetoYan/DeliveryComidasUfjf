CREATE TABLE USUARIO(
    ID INTEGER  PRIMARY KEY GENERATED ALWAYS AS IDENTITY, 
    NOME VARCHAR (150)  NOT NULL, 
    EMAIL VARCHAR (150)  NOT NULL, 
    SENHA VARCHAR (150)  NOT NULL, 
    TIPO_USER VARCHAR (150)  NOT NULL  
);
CREATE TABLE ENDERECO_DE_ENTREGA(
    ID INTEGER  PRIMARY KEY GENERATED ALWAYS AS IDENTITY, 
    LOGRADOURO VARCHAR (150)  NOT NULL, 
    NUMERO INTEGER NOT NULL, 
    COMPLEMENTO VARCHAR (150),
    BAIRRO VARCHAR (150)  NOT NULL,
    CIDADE VARCHAR (150)  NOT NULL,
    ID_USUARIO INTEGER NOT NULL,
    FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID)
);

CREATE TABLE RESTAURANTE(
    ID INTEGER  PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    NOME VARCHAR (150)  NOT NULL,
    LOGRADOURO VARCHAR (150)  NOT NULL, 
    NUMERO INTEGER NOT NULL, 
    COMPLEMENTO VARCHAR (150),
    BAIRRO VARCHAR (150)  NOT NULL,
    CIDADE VARCHAR (150)  NOT NULL,
    TIPO_COMIDA VARCHAR (150)  NOT NULL,
    ID_USUARIO INTEGER NOT NULL,
    FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID)
);


CREATE TABLE ITEM(
    ID INTEGER  PRIMARY KEY GENERATED ALWAYS AS IDENTITY, 
    NOME VARCHAR (150)  NOT NULL, 
    TIPO VARCHAR (150) NOT NULL, 
    DESCRICAO VARCHAR (150),
    PRECO DOUBLE NOT NULL,
    DISPONIVEL BOOLEAN NOT NULL,
    PROMOCAO BOOLEAN NOT NULL,
    ID_RESTAURANTE INTEGER NOT NULL,
    FOREIGN KEY (ID_RESTAURANTE) REFERENCES RESTAURANTE(ID)
    
);


CREATE TABLE FUNCIONARIO(
    ID INTEGER  PRIMARY KEY GENERATED ALWAYS AS IDENTITY, 
    ID_RESTAURANTE INTEGER NOT NULL,
    ID_USUARIO INTEGER NOT NULL,
    FOREIGN KEY (ID_RESTAURANTE) REFERENCES RESTAURANTE(ID),
    FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID)
);


CREATE TABLE PEDIDO(
    ID INTEGER  PRIMARY KEY GENERATED ALWAYS AS IDENTITY, 
    ESTADO VARCHAR (150)  NOT NULL, 
    DATA_PEDIDO DATE NOT NULL,
    ID_USUARIO INTEGER NOT NULL,
    FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID)
);

CREATE TABLE ITEM_PEDIDO(
    ID INTEGER  PRIMARY KEY GENERATED ALWAYS AS IDENTITY, 
    ID_PEDIDO INTEGER NOT NULL,
    ID_ITEM INTEGER NOT NULL,
    QUANTIDADE INTEGER NOT NULL,
    FOREIGN KEY (ID_PEDIDO) REFERENCES PEDIDO(ID),
    FOREIGN KEY (ID_ITEM) REFERENCES ITEM(ID)
);


INSERT INTO usuario(nome,email,senha, tipo_user) values('yan Gerente', 'gerente', 'gerente','Gerente');
INSERT INTO usuario(nome,email,senha, tipo_user) values('aaa', 'aaa', 'aaa','Cliente');
INSERT INTO usuario(nome,email,senha, tipo_user) values('yan Cliente', 'cliente', 'cliente','Cliente');
INSERT INTO usuario(nome,email,senha, tipo_user) values('yan ChefeDeCozinha', 'chefe', 'chefe','ChefeDeCozinha');
INSERT INTO usuario(nome,email,senha, tipo_user) values('yan Entregador', 'entregador', 'entregador','Entregador');

INSERT INTO restaurante(nome,logradouro,numero,complemento, bairro, cidade, tipo_comida, id_usuario) values('AAAA','rua a', 3, 'cs 2','ufjf', 'jf', 'japonesa', 2);
INSERT INTO restaurante(nome,logradouro,numero,complemento, bairro, cidade, tipo_comida, id_usuario) values('BBBB','rua a', 3, 'cs 2','ufjf', 'jf', 'japonesa', 1);



INSERT INTO item(nome,tipo,descricao, preco, disponivel, promocao, id_restaurante) values('item 1', 'Bebida', 'coca-cola', 2.5, true, true, 1);
INSERT INTO item(nome,tipo,descricao, preco, disponivel, promocao, id_restaurante) values('item 2', 'Bebida', 'coca-cola 2',2.5, true, true, 1);
INSERT INTO item(nome,tipo,descricao, preco, disponivel, promocao, id_restaurante) values('item 3', 'Bebida', 'coca-cola 3',2.5, true, true, 1);

INSERT INTO PEDIDO(ESTADO, DATA_PEDIDO, ID_USUARIO) values ('ABERTO','2018-11-10',1);
INSERT INTO PEDIDO(ESTADO, DATA_PEDIDO, ID_USUARIO) values ('ABERTO','2018-10-11',1);

INSERT INTO ITEM_PEDIDO(ID_PEDIDO, ID_ITEM, QUANTIDADE) values (1,1,10);
INSERT INTO ITEM_PEDIDO(ID_PEDIDO, ID_ITEM, QUANTIDADE) values (1,2,10);
INSERT INTO ITEM_PEDIDO(ID_PEDIDO, ID_ITEM, QUANTIDADE) values (2,3,10);



select * from PEDIDO AS P, ITEM_PEDIDO AS IP, ITEM AS i where p.ID = IP.id_pedido and i.id = ip.id_item and id_usuario=1;
