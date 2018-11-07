CREATE TABLE USUARIO(
    ID INTEGER  PRIMARY KEY GENERATED ALWAYS AS IDENTITY, 
    NOME VARCHAR (150)  NOT NULL, 
    EMAIL VARCHAR (150)  NOT NULL, 
    SENHA VARCHAR (150)  NOT NULL, 
    TIPO_USER VARCHAR (150)  NOT NULL,
    ID_PROX INTEGER
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
    DESCRICAO VARCHAR (1024)  NOT NULL,
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
    ID_END INTEGER NOT NULL,
    FOREIGN KEY (ID_END) REFERENCES ENDERECO_DE_ENTREGA(ID),
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


INSERT INTO usuario(nome,email,senha, tipo_user) values('Aline de Paula Sotte', 'aline', 'aline', 'Gerente');
INSERT INTO usuario(nome,email,senha, tipo_user) values('Yan de Paiva Andrade Pinto', 'yan', 'yan', 'Gerente');
INSERT INTO usuario(nome,email,senha, tipo_user) values('Victor Reis', 'victor', 'victor', 'Gerente');
INSERT INTO usuario(nome,email,senha, tipo_user) values('Fulano', 'fulano', 'fulano', 'ChefeDeCozinha');
INSERT INTO usuario(nome,email,senha, tipo_user) values('Beltrano', 'beltrano', 'beltrano', 'Entregador');


INSERT INTO restaurante(nome,descricao,logradouro,numero,complemento, bairro, cidade, tipo_comida, id_usuario) values('Assunta Forneria', 'O Assunta é um dos restaurantes mais belos do Brasil. Projetado por Hélio Pelegrino, foi concebido para criar um ambiente único e, ao mesmo tempo, sofisticado e informal. Grandioso e aconchegante. A interação com a natureza completa essa experiência única composta ainda por belos mosaicos do piso e lustres exclusivos - feitos à mão pelo arquiteto. Estar aqui é um prazer. Aliado a toda essa beleza, uma gastronomia única para os mais sofisticados gostos. São pizzas gourmet de vários sabores e, agora, uma nova e poderosa cozinha, com exclusiva parrilla, para preparar as melhores carnes do país. Juiz de Fora é um polo gastronômico. E o Assunta tem o maior orgulho de contribuir para isso. Agora, ainda mais.','Ladeira Alexandre Leonel', 201, '', 'São Mateus', 'Juiz de Fora', 'Italiana, Europeia, Opções vegetarianas', 1);
INSERT INTO restaurante(nome,descricao,logradouro,numero,complemento, bairro, cidade, tipo_comida, id_usuario) values('La Caprese Doc', 'O Restaurante La Caprese me seduz com seu cardápio, simples (não pela escolha dos ingredientes, mas pela forma como estes vão para os pratos) e sofisticado. Combinações óbvias e deliciosas que nos deixam confortáveis na hora de escolher um prato. O La Caprese é o típico restaurante para ocasiões especiais, dias de aniversário, comemorações de bodas e todos os brindes cotidianos que gostamos de fazer. É também um bom lugar para transformar uma noite comum em especial.', 'Ladeira Alexandre Leonel', 221, '.', 'São Mateus', 'Juiz de Fora', 'Italiana', 2);
INSERT INTO restaurante(nome,descricao,logradouro,numero,complemento, bairro, cidade, tipo_comida, id_usuario) values('Picanha Pimenta e Pinga', 'Petiscos, carnes e peixes, além de chopes artesanais, em espaço rústico-contemporâneo com playground externo.', 'Av. Eugênio do Nascimento', 310, 'Próximo ao Estadio Municipal', 'Aeroporto', 'Juiz de Fora', 'Brasileira, Grelhados, Sul-americana', 3);


INSERT INTO item(nome,tipo,descricao, preco, disponivel, promocao, id_restaurante) values('item 1', 'Bebida', 'coca-cola', 2.5, true, true, 1);
INSERT INTO item(nome,tipo,descricao, preco, disponivel, promocao, id_restaurante) values('item 2', 'Bebida', 'coca-cola 2',2.5, true, true, 1);
INSERT INTO item(nome,tipo,descricao, preco, disponivel, promocao, id_restaurante) values('item 3', 'Bebida', 'coca-cola 3',2.5, true, true, 1);


INSERT INTO ENDERECO_DE_ENTREGA(logradouro,numero,complemento, bairro, cidade,id_usuario) values('minha casa', 1, 'asd','doideira', 'jf', 1);


INSERT INTO PEDIDO(ESTADO, DATA_PEDIDO, ID_END ,ID_USUARIO) values ('ABERTO','2018-11-10',1, 1);
INSERT INTO PEDIDO(ESTADO, DATA_PEDIDO, ID_END,ID_USUARIO) values ('ABERTO','2018-10-11',1 , 1);


INSERT INTO ITEM_PEDIDO(ID_PEDIDO, ID_ITEM, QUANTIDADE) values (1,1,10);
INSERT INTO ITEM_PEDIDO(ID_PEDIDO, ID_ITEM, QUANTIDADE) values (1,2,10);
INSERT INTO ITEM_PEDIDO(ID_PEDIDO, ID_ITEM, QUANTIDADE) values (2,3,10);

INSERT INTO funcionario(ID_RESTAURANTE,ID_USUARIO) VALUES(1,1);
INSERT INTO funcionario(ID_RESTAURANTE,ID_USUARIO) VALUES(1,4);
INSERT INTO funcionario(ID_RESTAURANTE,ID_USUARIO) VALUES(1,5);
INSERT INTO funcionario(ID_RESTAURANTE,ID_USUARIO) VALUES(2,2);
INSERT INTO funcionario(ID_RESTAURANTE,ID_USUARIO) VALUES(3,3);